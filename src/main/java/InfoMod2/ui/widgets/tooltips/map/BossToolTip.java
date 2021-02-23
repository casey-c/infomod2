package InfoMod2.ui.widgets.tooltips.map;

import InfoMod2.ui.widgets.AnchorPosition;
import InfoMod2.ui.widgets.text.SimpleLabel;
import InfoMod2.ui.widgets.tooltips.ExtendedToolTip;
import InfoMod2.utils.ExtraColors;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class BossToolTip extends ExtendedToolTip<BossToolTip> {
    //public BossToolTip() { super(293, 128); }
    public BossToolTip() { super(261, 128); }

    private ArrayList<String> bossNames = new ArrayList<>();
    private String a20SecondBossName;
    private boolean hasA20SecondBoss = false;

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

        // TODO: this was very lazy and is bad code and needs refactoring badly but whatever
        if (hasA20SecondBoss && AbstractDungeon.floorNum == 51) {
            for (String name : bossNames)
                bossLabels.add( new SimpleLabel(name, ExtraColors.TOOLTIP_TEXT_GRAY) );

            bossLabels.add( new SimpleLabel(a20SecondBossName, ExtraColors.QUAL_PURPLE) );
        }
        else if (hasA20SecondBoss && AbstractDungeon.floorNum > 51) {
            for (int i = 0; i < bossNames.size() - 1; ++i)
                bossLabels.add( new SimpleLabel(bossNames.get(i), ExtraColors.TOOLTIP_TEXT_GRAY) );

            bossLabels.add( new SimpleLabel(a20SecondBossName, ExtraColors.TOOLTIP_TEXT_GRAY) );
            bossLabels.add( new SimpleLabel(bossNames.get(bossNames.size() - 1), ExtraColors.QUAL_PURPLE) );
        }
        else {
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

    public void setBosses(ArrayList<String> bossNames) {
        bossLabels.clear();
        this.bossNames = bossNames;

        for (int i = 0; i < bossNames.size(); ++i) {
            Color labelColor = (i == bossNames.size() - 1) ? ExtraColors.QUAL_PURPLE : ExtraColors.TOOLTIP_TEXT_GRAY;
            bossLabels.add( new SimpleLabel(bossNames.get(i), labelColor) );
        }

    }

    public ArrayList<String> getBossNames() { return bossNames; }

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
}
