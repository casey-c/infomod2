package InfoMod2.ui;

import InfoMod2.utils.ExtraColors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class MapTip extends ThiccToolTip {

    public MapTip() {
        super(1469, 400, 363);
    }

    @Override
    protected void renderForeground(SpriteBatch sb) {
        FontHelper.renderFontLeftDownAligned(sb,
                FontHelper.tipBodyFont,
                "Right click to see all available options",
                (left + 32) * Settings.scale,
                (bottom + 303) * Settings.scale,
                ExtraColors.TEXT_CREAM);

        float titleLeft = (left + 99) * Settings.scale;
        float detailsLeft = (left + 231) * Settings.scale;

        float treasureBottom = bottom + 35;
        float vertSpacing = 47;

        FontHelper.renderFontLeftDownAligned(sb, FontHelper.tipBodyFont, "Event:", titleLeft,  (treasureBottom + 4 * vertSpacing) * Settings.scale, ExtraColors.TEXT_CREAM);
        FontHelper.renderFontLeftDownAligned(sb, FontHelper.tipBodyFont, "Shrine:", titleLeft,  (treasureBottom + 3 * vertSpacing) * Settings.scale, ExtraColors.TEXT_CREAM);
        FontHelper.renderFontLeftDownAligned(sb, FontHelper.tipBodyFont, "Fight:", titleLeft,  (treasureBottom + 2 * vertSpacing) * Settings.scale, ExtraColors.TEXT_CREAM);
        FontHelper.renderFontLeftDownAligned(sb, FontHelper.tipBodyFont, "Shop:", titleLeft,  (treasureBottom + vertSpacing) * Settings.scale, ExtraColors.TEXT_CREAM);
        FontHelper.renderFontLeftDownAligned(sb, FontHelper.tipBodyFont, "Treasure:", titleLeft,  (treasureBottom) * Settings.scale, ExtraColors.TEXT_CREAM);

        FontHelper.renderFontLeftDownAligned(sb, FontHelper.tipBodyFont, "63.75 %", detailsLeft,  (treasureBottom + 4 * vertSpacing) * Settings.scale, ExtraColors.TEXT_LIGHT_GREEN);
        FontHelper.renderFontLeftDownAligned(sb, FontHelper.tipBodyFont, "21.25 %", detailsLeft,  (treasureBottom + 3 * vertSpacing) * Settings.scale, ExtraColors.TEXT_LIGHT_GREEN);
        FontHelper.renderFontLeftDownAligned(sb, FontHelper.tipBodyFont, "10.00 %", detailsLeft,  (treasureBottom + 2 * vertSpacing) * Settings.scale, ExtraColors.TEXT_LIGHT_GREEN);
        FontHelper.renderFontLeftDownAligned(sb, FontHelper.tipBodyFont, "3.00 %", detailsLeft,  (treasureBottom + vertSpacing) * Settings.scale, ExtraColors.TEXT_LIGHT_GREEN);
        FontHelper.renderFontLeftDownAligned(sb, FontHelper.tipBodyFont, "2.00 %", detailsLeft,  (treasureBottom) * Settings.scale, ExtraColors.TEXT_LIGHT_GREEN);

        // Divider
        sb.setColor(ExtraColors.DIVIDER_COLOR);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, (left + 37) * Settings.scale, (bottom + 267) * Settings.scale, 327 * Settings.scale, 3 * Settings.scale);
    }

    private static MapTip instance;
    public static void renderCustomMapTip(SpriteBatch sb) {
        if (instance == null) {
            instance = new MapTip();
        }

        instance.render(sb);
    }
}
