package InfoMod2.ui.widgets.tooltips.map;

import InfoMod2.ui.widgets.AnchorPosition;
import InfoMod2.ui.widgets.text.SmartLabel;
import InfoMod2.ui.widgets.tooltips.ExtendedToolTip;
import InfoMod2.utils.graphics.color.ColorManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class BossToolTip extends ExtendedToolTip<BossToolTip> {
    public BossToolTip() { super(261, 128); }

    protected ArrayList<String> bossNames = new ArrayList<>();
    protected String a20SecondBossName;
    protected boolean hasA20SecondBoss = false;

    //private ArrayList<SimpleLabel> bossLabels = new ArrayList<>();
    private ArrayList<SmartLabel> bossLabels = new ArrayList<>();

    private static final float individualBossLabelHeight = 37.0f;

    public void addBoss(String bossName, boolean isAsc20SecondBoss) {
        if (isAsc20SecondBoss) {
            a20SecondBossName = bossName;
            hasA20SecondBoss = true;
        }
        else {
            bossNames.add(bossName);
        }

        updateLabels();
    }

    public void updateLabels() {
        bossLabels.clear();

        // TODO: this was very lazy and is bad code and needs refactoring badly but whatever
        if (hasA20SecondBoss && AbstractDungeon.floorNum == 51) {
            for (String name : bossNames)
                bossLabels.add( new SmartLabel(name, ColorManager.LIGHT_GRAY()) );

            bossLabels.add( new SmartLabel(a20SecondBossName, ColorManager.QUAL_PURPLE()) );
        }
        else if (hasA20SecondBoss && AbstractDungeon.floorNum > 51) {
            for (int i = 0; i < bossNames.size() - 1; ++i)
                bossLabels.add( new SmartLabel(bossNames.get(i), ColorManager.LIGHT_GRAY()) );

            bossLabels.add( new SmartLabel(a20SecondBossName, ColorManager.LIGHT_GRAY()) );
            bossLabels.add( new SmartLabel(bossNames.get(bossNames.size() - 1), ColorManager.QUAL_PURPLE()) );
        }
        else {
            for (int i = 0; i < bossNames.size(); ++i) {
                Color labelColor = (i == bossNames.size() - 1) ? ColorManager.QUAL_PURPLE() : ColorManager.LIGHT_GRAY();
                bossLabels.add( new SmartLabel(bossNames.get(i), labelColor) );
            }
        }


        // We need to shift the entire box down a bit (since anchors are standardized from bottom left)
        float oldTop = getContentTop();
        float totalBossHeight = bossNames.size() * individualBossLabelHeight;

        if (hasA20SecondBoss && AbstractDungeon.floorNum >= 51)
            totalBossHeight += individualBossLabelHeight;

        // Padding above the first boss + height of N combined boss labels + padding below last boss
        prefHeight = 32.0f + totalBossHeight + 14.0f;

        anchoredAt(getContentLeft(), oldTop - prefHeight, AnchorPosition.LEFT_BOTTOM);
    }

    public void reset() {
        hasA20SecondBoss = false;
        bossLabels.clear();
        bossNames.clear();
    }

    // --------------------------------------------------------------------------------
    // TODO: probably should've used Gson here/automated it, but whatever

    public JsonObject serialize() {
        JsonObject obj = new JsonObject();

        if (hasA20SecondBoss) {
            obj.add("a20SecondBossName", new JsonPrimitive(a20SecondBossName));
        }

        JsonArray arr = new JsonArray();
        for (String boss : bossNames)
            arr.add(boss);

        obj.add("bossNames", arr);

        return obj;
    }

    public void deserialize(JsonObject obj) {
        reset();

        if (obj.has("bossNames") && obj.get("bossNames").isJsonArray()) {
            JsonArray list = obj.get("bossNames").getAsJsonArray();

            for (JsonElement s : list) {
                if (s.isJsonPrimitive())
                    this.bossNames.add(s.getAsString());
            }

            // A20 second boss
            if (obj.has("a20SecondBossName") && obj.get("a20SecondBossName").isJsonPrimitive()) {
                this.a20SecondBossName = obj.get("a20SecondBossName").getAsString();
                this.hasA20SecondBoss = true;
            }
        }

        updateLabels();
    }


    // --------------------------------------------------------------------------------


    @Override
    public void renderBackground(SpriteBatch sb) {
        if (!bossLabels.isEmpty())
            super.renderBackground(sb);
    }

    @Override
    public void renderForeground(SpriteBatch sb) {
        if (bossLabels.isEmpty())
            return;

        final float textLeft = getContentLeft() + 34;

        // Bosses
        float currTop = getContentTop() - 31;
        final float infoSpacing = 37.0f;

        for (SmartLabel label : bossLabels) {
            label.anchoredAt(textLeft, currTop, AnchorPosition.LEFT_TOP);
            label.render(sb);

            currTop -= infoSpacing;
        }
    }

    @Override
    public String toString() {
        return "BossToolTip{" +
                "bossNames=" + bossNames +
                ", a20SecondBossName='" + a20SecondBossName + '\'' +
                ", hasA20SecondBoss=" + hasA20SecondBoss +
                ", bossLabels=" + bossLabels +
                '}';
    }
}
