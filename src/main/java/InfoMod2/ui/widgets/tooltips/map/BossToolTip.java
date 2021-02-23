package InfoMod2.ui.widgets.tooltips.map;

import InfoMod2.ui.widgets.AnchorPosition;
import InfoMod2.ui.widgets.text.SimpleLabel;
import InfoMod2.ui.widgets.tooltips.ExtendedToolTip;
import InfoMod2.utils.ExtraColors;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Json;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class BossToolTip extends ExtendedToolTip<BossToolTip> {
    //public BossToolTip() { super(293, 128); }
    public BossToolTip() { super(261, 128); }

    protected ArrayList<String> bossNames = new ArrayList<>();
    protected String a20SecondBossName;
    protected boolean hasA20SecondBoss = false;

    private ArrayList<SimpleLabel> bossLabels = new ArrayList<>();

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

        System.out.println("Updating labels. Current status of this obj looks like:");
        System.out.println(this);

        System.out.println();
        System.out.println("AbstractDungeon.floorNum: " + AbstractDungeon.floorNum);

        // TODO: this was very lazy and is bad code and needs refactoring badly but whatever
        if (hasA20SecondBoss && AbstractDungeon.floorNum == 51) {
            System.out.println("Floor 51");

            for (String name : bossNames)
                bossLabels.add( new SimpleLabel(name, ExtraColors.TOOLTIP_TEXT_GRAY) );

            bossLabels.add( new SimpleLabel(a20SecondBossName, ExtraColors.QUAL_PURPLE) );
        }
        else if (hasA20SecondBoss && AbstractDungeon.floorNum > 51) {
            System.out.println("Greater than Floor 51");

            for (int i = 0; i < bossNames.size() - 1; ++i)
                bossLabels.add( new SimpleLabel(bossNames.get(i), ExtraColors.TOOLTIP_TEXT_GRAY) );

            bossLabels.add( new SimpleLabel(a20SecondBossName, ExtraColors.TOOLTIP_TEXT_GRAY) );
            bossLabels.add( new SimpleLabel(bossNames.get(bossNames.size() - 1), ExtraColors.QUAL_PURPLE) );
        }
        else {
            System.out.println("Else branch");

            for (int i = 0; i < bossNames.size(); ++i) {
                Color labelColor = (i == bossNames.size() - 1) ? ExtraColors.QUAL_PURPLE : ExtraColors.TOOLTIP_TEXT_GRAY;
                bossLabels.add( new SimpleLabel(bossNames.get(i), labelColor) );
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

        System.out.println("Current obj looks like: ");
        System.out.println(this);

        System.out.println("Serializing...");
        System.out.println(obj);
        System.out.println();

        return obj;
    }

    public void deserialize(JsonObject obj) {
        reset();

        System.out.println("Current obj looks like: ");
        System.out.println(this);

        System.out.println("Deserializing...");
        System.out.println(obj);
        System.out.println();

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

        for (SimpleLabel label : bossLabels) {
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
