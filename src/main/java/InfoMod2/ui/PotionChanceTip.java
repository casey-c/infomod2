package InfoMod2.ui;

import InfoMod2.utils.ExtraColors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class PotionChanceTip extends ThiccToolTip {
    public PotionChanceTip() {
        super(1383, 400, 347);
        //box.render(sb, 1383, 691, 400, 300);
    }

    @Override
    protected void renderForeground(SpriteBatch sb) {
        //FontHelper.renderFontLeftDownAligned(sb, FontHelper.tipBodyFont, "Test", 1450, 800, Settings.CREAM_COLOR);
        float title1left = (left + 55) * Settings.scale;
        float title1bottom = (bottom + 287) * Settings.scale;

        float title2left = (left + 132) * Settings.scale;
        float title2bottom = (bottom + 259) * Settings.scale;

        float detailsLeft = (left + 120) * Settings.scale;

        float fiveBottom = (bottom + 41);
        float vertSpacing = 47;

        FontHelper.renderFontLeftDownAligned(sb, FontHelper.tipBodyFont, "Seeing at least one potion after", title1left, title1bottom, ExtraColors.TEXT_CREAM );
        FontHelper.renderFontLeftDownAligned(sb, FontHelper.tipBodyFont, "multiple fights:", title2left, title2bottom, ExtraColors.TEXT_CREAM );

        FontHelper.renderFontLeftDownAligned(sb, FontHelper.tipBodyFont, "2 Fights: 70.0%", detailsLeft, (fiveBottom + 3 * vertSpacing) * Settings.scale, ExtraColors.TEXT_LIGHT_GREEN );
        FontHelper.renderFontLeftDownAligned(sb, FontHelper.tipBodyFont, "3 Fights: 88.0%", detailsLeft, (fiveBottom + 2 * vertSpacing) * Settings.scale, ExtraColors.TEXT_LIGHT_GREEN );
        FontHelper.renderFontLeftDownAligned(sb, FontHelper.tipBodyFont, "4 Fights: 96.4%", detailsLeft, (fiveBottom + vertSpacing) * Settings.scale, ExtraColors.TEXT_BLUE_GREEN );
        FontHelper.renderFontLeftDownAligned(sb, FontHelper.tipBodyFont, "5 Fights: 99.3%", detailsLeft, (fiveBottom) * Settings.scale, ExtraColors.TEXT_LIGHT_BLUE );

        // Divider
        sb.setColor(ExtraColors.DIVIDER_COLOR);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, (left + 37) * Settings.scale, (bottom + 222) * Settings.scale, 327 * Settings.scale, 3 * Settings.scale);
    }
}
