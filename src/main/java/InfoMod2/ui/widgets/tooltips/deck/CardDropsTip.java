package InfoMod2.ui.widgets.tooltips.deck;

import InfoMod2.ui.widgets.tooltips.TitledToolTip;
import InfoMod2.utils.ExtraColors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;

public class CardDropsTip extends TitledToolTip<CardDropsTip> {
    public CardDropsTip() {
        super(331, 300, "Card Drops (Next Combat)", "Chance to see at least one");
    }

    private static final float SPACING = 37;

    // TODO: setters etc.
    private String rareRegular = "70.0%";
    private String rareElite = "70.0%";

    private String uncommonRegular = "88.0%";
    private String uncommonElite = "88.0%";

    private String commonRegular = "96.4%";
    private String commonElite = "96.4%";

    @Override
    protected void renderContent(SpriteBatch sb, float left, float top) {
        float col2 = left + 120;
        float col3 = col2 + 85;

        float currY = top - 30;

        // Table headers
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Regular", col2 * Settings.scale, currY * Settings.scale, ExtraColors.TOOLTIP_TEXT_GRAY);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Elite", col3 * Settings.scale, currY * Settings.scale, ExtraColors.TOOLTIP_TEXT_GRAY);

        currY -= SPACING;

        // Rare
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Rare", left * Settings.scale, currY * Settings.scale, ExtraColors.QUAL_YELLOW);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, rareRegular, col2 * Settings.scale, currY * Settings.scale, ExtraColors.QUAL_YELLOW);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, rareElite, col3 * Settings.scale, currY * Settings.scale, ExtraColors.QUAL_YELLOW);

        currY -= SPACING;

        // Uncommon
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Uncommon", left * Settings.scale, currY * Settings.scale, ExtraColors.TOOLTIP_TEXT_POTION_3);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, uncommonRegular, col2 * Settings.scale, currY * Settings.scale, ExtraColors.TOOLTIP_TEXT_POTION_3);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, uncommonElite, col3 * Settings.scale, currY * Settings.scale, ExtraColors.TOOLTIP_TEXT_POTION_3);

        currY -= SPACING;

        // Common
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Common", left * Settings.scale, currY * Settings.scale, ExtraColors.QUAL_BEIGE);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, commonRegular, col2 * Settings.scale, currY * Settings.scale, ExtraColors.QUAL_BEIGE);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, commonElite, col3 * Settings.scale, currY * Settings.scale, ExtraColors.QUAL_BEIGE);
    }
}
