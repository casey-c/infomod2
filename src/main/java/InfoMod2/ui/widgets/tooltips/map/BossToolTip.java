package InfoMod2.ui.widgets.tooltips.map;

import InfoMod2.ui.widgets.AnchorPosition;
import InfoMod2.ui.widgets.text.SimpleLabel;
import InfoMod2.ui.widgets.tooltips.ExtendedToolTip;
import InfoMod2.utils.ExtraColors;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class BossToolTip extends ExtendedToolTip<BossToolTip> {
    //public BossToolTip() { super(293, 128); }
    public BossToolTip() { super(261, 128); }

    private ArrayList<String> bossNames = new ArrayList<>();
    private ArrayList<SimpleLabel> bossLabels = new ArrayList<>();

    private static final float individualBossLabelHeight = 37.0f;

    public void setBosses(ArrayList<String> bossNames) {
        bossLabels.clear();
        this.bossNames = bossNames;

        for (int i = 0; i < bossNames.size(); ++i) {
            Color labelColor = (i == bossNames.size() - 1) ? ExtraColors.QUAL_PURPLE : ExtraColors.TOOLTIP_TEXT_GRAY;
            bossLabels.add( new SimpleLabel(bossNames.get(i), labelColor) );
        }


        // We need to shift the entire box down a bit (since anchors are standardized from bottom left)
        float oldTop = getContentTop();
        final float totalBossHeight = bossNames.size() * individualBossLabelHeight;

        // Padding above the first boss + height of N combined boss labels + padding below last boss
        prefHeight = 32.0f + totalBossHeight + 14.0f;

        anchoredAt(getContentLeft(), oldTop - prefHeight, AnchorPosition.LEFT_BOTTOM);
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
