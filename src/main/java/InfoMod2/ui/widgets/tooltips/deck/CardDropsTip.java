package InfoMod2.ui.widgets.tooltips.deck;

import InfoMod2.ui.widgets.tooltips.TitledToolTip;
import InfoMod2.utils.graphics.color.ColorManager;
import InfoMod2.utils.math.CardDropHelper;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;

public class CardDropsTip extends TitledToolTip<CardDropsTip> {
    public CardDropsTip() {
        super(331, 300, "Card Drops (Next Combat)", "Chance to see at least one");
    }

    private static final float SPACING = 37;

    // TODO: setters etc.
    CardDropHelper helper = new CardDropHelper();

    public void updateCardChances(int rareCBR) {
        helper.setCBR(rareCBR);
    }

    @Override
    protected void renderContent(SpriteBatch sb, float left, float top) {
        float col2 = left + 120;
        float col3 = col2 + 85;

        float currY = top - 30;

        // Table headers
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Regular", col2 * Settings.xScale, currY * Settings.yScale, ColorManager.LIGHT_GRAY());
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Elite", col3 * Settings.xScale, currY * Settings.yScale, ColorManager.LIGHT_GRAY());

        currY -= SPACING;

        // Rare
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Rare", left * Settings.xScale, currY * Settings.yScale, ColorManager.RARE());
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, helper.getRareChance(), col2 * Settings.xScale, currY * Settings.yScale, ColorManager.RARE());
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, helper.getRareChanceElite(), col3 * Settings.xScale, currY * Settings.yScale, ColorManager.RARE());

        currY -= SPACING;

        // Uncommon
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Uncommon", left * Settings.xScale, currY * Settings.yScale, ColorManager.UNCOMMON());
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, helper.getUncommonChance(), col2 * Settings.xScale, currY * Settings.yScale, ColorManager.UNCOMMON());
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, helper.getUncommonChanceElite(), col3 * Settings.xScale, currY * Settings.yScale, ColorManager.UNCOMMON());

        currY -= SPACING;

        // Common
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Common", left * Settings.xScale, currY * Settings.yScale, ColorManager.COMMON());
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, helper.getCommonChance(), col2 * Settings.xScale, currY * Settings.yScale, ColorManager.COMMON());
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, helper.getCommonChanceElite(), col3 * Settings.xScale, currY * Settings.yScale, ColorManager.COMMON());
    }
}
