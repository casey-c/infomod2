package InfoMod2.ui.widgets.tooltips;

import InfoMod2.ui.widgets.AnchorPosition;
import InfoMod2.ui.widgets.text.SimpleLabel;
import InfoMod2.utils.ExtraColors;
import InfoMod2.utils.ExtraFonts;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;

import java.util.ArrayList;

public class BossToolTip extends ExtendedToolTip<BossToolTip> {
    public BossToolTip() { super(293, 128); }

    private ArrayList<String> bossNames = new ArrayList<>();
    private ArrayList<SimpleLabel> bossLabels = new ArrayList<>();

    private static final float individualBossLabelHeight = 37.0f;

    public void setBosses(ArrayList<String> bossNames) {
        bossLabels.clear();
        this.bossNames = bossNames;

        //for (int i = bossNames.size() - 1; i >= 0; --i) {
        for (int i = 0; i < bossNames.size(); ++i) {
            Color labelColor = (i == bossNames.size() - 1) ? ExtraColors.QUAL_PURPLE : ExtraColors.TOOLTIP_TEXT_GRAY;
            bossLabels.add( new SimpleLabel(bossNames.get(i), labelColor) );
        }

        // TODO: update prefHeight
        final float totalBossHeight = bossNames.size() * individualBossLabelHeight;

        // We need to shift the entire box down a bit (since anchors are standardized from bottom left)
        float oldTop = getContentTop();
        // Title/Divider Area + Padding above the first boss + height of N combined boss labels + padding below last boss
        //prefHeight = 71.0f + 32.0f + totalBossHeight + 17.0f;

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

//        // Title
//        final float titleTop = getContentTop() - 31;
//        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Bosses", textLeft, titleTop, ExtraColors.TOOLTIP_TEXT_CREAM);
//
//        // Horizontal line
//        final float hruleTop = titleTop - 40;
//        final float hruleInset = 6; // ?
//
//        sb.setColor(ExtraColors.TOOLTIP_TEXT_GRAY);
//        sb.draw(ImageMaster.WHITE_SQUARE_IMG, (getContentLeft() + hruleInset) * Settings.scale, (hruleTop * Settings.scale), (getPreferredContentWidth() - (2 * hruleInset)) * Settings.scale, 2.0f);

        // Bosses
        //float currTop = hruleTop - 32;
        float currTop = getContentTop() - 31;
        final float infoSpacing = 37.0f;

        for (SimpleLabel label : bossLabels) {
            label.anchoredAt(textLeft, currTop, AnchorPosition.LEFT_TOP);
            label.render(sb);

            currTop -= infoSpacing;
        }
    }
}
