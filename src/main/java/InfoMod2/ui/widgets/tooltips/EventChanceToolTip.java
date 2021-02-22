package InfoMod2.ui.widgets.tooltips;

import InfoMod2.utils.ExtraColors;
import InfoMod2.utils.ExtraFonts;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;

//public class EventChanceToolTip extends ExtendedToolTip<EventChanceToolTip> {
public class EventChanceToolTip extends TitledToolTip<EventChanceToolTip> {
    public EventChanceToolTip() {
        super(293, 338, "Next [?] Floor", "Right-Click for full details");
    }

    // TODO: use config settings to enable/disable qualitative color scheme
    private Color getEventColor() { return ExtraColors.QUAL_TEAL; }
    private Color getShrineColor() { return ExtraColors.QUAL_BLUE; }
    private Color getFightColor() { return ExtraColors.QUAL_RED; }
    private Color getShopColor() { return ExtraColors.QUAL_YELLOW; }
    private Color getTreasureColor() { return ExtraColors.QUAL_GREEN; }

    // TODO: write update functions etc.
    private String eventText = "63.75%";
    private String shrineText = "21.25%";
    private String fightText = "10.00%";
    private String shopText = "3.00%";
    private String treasureText = "2.00%";

    @Override
    protected void renderContent(SpriteBatch sb, float left, float top) {
        final float textLeft = left;
        final float detailLeft = left + 123;

        final float infoSpacing = 37;
        final float eventTop = top - 32;

        final Color eventColor = getEventColor();
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Event", textLeft, eventTop, eventColor);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, eventText, detailLeft, eventTop, eventColor);

        final float shrineTop = eventTop - infoSpacing;
        final Color shrineColor = getShrineColor();
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Shrine", textLeft, shrineTop, shrineColor);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, shrineText, detailLeft, shrineTop, shrineColor);

        final float fightTop = shrineTop - infoSpacing;
        final Color fightColor = getFightColor();
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Fight", textLeft, fightTop, fightColor);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, fightText, detailLeft, fightTop, fightColor);

        final float shopTop = fightTop - infoSpacing;
        final Color shopColor = getShopColor();
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Shop", textLeft, shopTop, shopColor);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, shopText, detailLeft, shopTop, shopColor);

        final float treasureTop = shopTop - infoSpacing;
        final Color treasureColor = getTreasureColor();
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Treasure", textLeft, treasureTop, treasureColor);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, treasureText, detailLeft, treasureTop, treasureColor);
    }

    //    @Override
//    public void renderForeground(SpriteBatch sb) {
//        final float textLeft = getContentLeft() + 34;
//        final float detailLeft = textLeft + 123;
//
//        // Title/Subtitle
//        final float titleTop = getContentTop() - 31;
//        final float subtitleTop = titleTop - 34;
//
//        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Next [?] Floor", textLeft, titleTop, ExtraColors.TOOLTIP_TEXT_CREAM);
//        FontHelper.renderFontLeftTopAligned(sb, ExtraFonts.smallItalicFont(), "Right-Click for full details", textLeft, subtitleTop, ExtraColors.TOOLTIP_TEXT_GRAY);
//
//        // Horizontal line
//        final float hruleTop = subtitleTop - 40;
//        final float hruleInset = 6; // ?
//
//        //sb.setColor(ExtraColors.TOOLTIP_TEXT_GRAY);
//        sb.setColor(ExtraColors.TOOLTIP_OUTER);
//        sb.draw(ImageMaster.WHITE_SQUARE_IMG, (getContentLeft() + hruleInset) * Settings.scale, (hruleTop * Settings.scale), (getPreferredContentWidth() - (2 * hruleInset)) * Settings.scale, 2.0f);
//
//        // Main Information
//        final float infoSpacing = 37;
//
//        final float eventTop = hruleTop - 32;
//
//        final Color eventColor = getEventColor();
//        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Event", textLeft, eventTop, eventColor);
//        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, eventText, detailLeft, eventTop, eventColor);
//
//        final float shrineTop = eventTop - infoSpacing;
//        final Color shrineColor = getShrineColor();
//        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Shrine", textLeft, shrineTop, shrineColor);
//        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, shrineText, detailLeft, shrineTop, shrineColor);
//
//        final float fightTop = shrineTop - infoSpacing;
//        final Color fightColor = getFightColor();
//        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Fight", textLeft, fightTop, fightColor);
//        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, fightText, detailLeft, fightTop, fightColor);
//
//        final float shopTop = fightTop - infoSpacing;
//        final Color shopColor = getShopColor();
//        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Shop", textLeft, shopTop, shopColor);
//        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, shopText, detailLeft, shopTop, shopColor);
//
//        final float treasureTop = shopTop - infoSpacing;
//        final Color treasureColor = getTreasureColor();
//        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Treasure", textLeft, treasureTop, treasureColor);
//        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, treasureText, detailLeft, treasureTop, treasureColor);
//    }
}
