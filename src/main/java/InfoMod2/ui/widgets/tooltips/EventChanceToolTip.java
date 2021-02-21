package InfoMod2.ui.widgets.tooltips;

import InfoMod2.utils.ExtraColors;
import InfoMod2.utils.ExtraFonts;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class EventChanceToolTip extends ExtendedToolTip<EventChanceToolTip> {
    public EventChanceToolTip() {
        super(293, 338);
    }

    // TODO: use config settings to enable/disable qualitative color scheme
    private Color getEventColor() { return ExtraColors.QUAL_TEAL; }
    private Color getShrineColor() { return ExtraColors.QUAL_BLUE; }
    private Color getFightColor() { return ExtraColors.QUAL_RED; }
    private Color getShopColor() { return ExtraColors.QUAL_YELLOW; }
    private Color getTreasureColor() { return ExtraColors.QUAL_GREEN; }

    @Override
    public void renderForeground(SpriteBatch sb) {
        final float textLeft = getContentLeft() + 34;

        // Title/Subtitle
        final float titleTop = getContentTop() - 31;
        final float subtitleTop = titleTop - 34;

        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Next [?] Floor", textLeft, titleTop, ExtraColors.TOOLTIP_TEXT_CREAM);
        FontHelper.renderFontLeftTopAligned(sb, ExtraFonts.smallItalicFont(), "Right-Click for full details", textLeft, subtitleTop, ExtraColors.TOOLTIP_TEXT_GRAY);

        // Horizontal line
        final float hruleTop = subtitleTop - 40;
        final float hruleInset = 6; // ?

        sb.setColor(ExtraColors.TOOLTIP_TEXT_GRAY);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, (getContentLeft() + hruleInset) * Settings.scale, (hruleTop * Settings.scale), (getPreferredContentWidth() - (2 * hruleInset)) * Settings.scale, 2.0f);

        // Main Information
        final float infoSpacing = 37;

        final float eventTop = hruleTop - 32;
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Event", textLeft, eventTop, getEventColor());

        final float shrineTop = eventTop - infoSpacing;
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Shrine", textLeft, shrineTop, getShrineColor());

        final float fightTop = shrineTop - infoSpacing;
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Fight", textLeft, fightTop, getFightColor());

        final float shopTop = fightTop - infoSpacing;
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Shop", textLeft, shopTop, getShopColor());

        final float treasureTop = shopTop - infoSpacing;
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Treasure", textLeft, treasureTop, getTreasureColor());
    }
}
