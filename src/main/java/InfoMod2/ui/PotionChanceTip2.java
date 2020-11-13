package InfoMod2.ui;

import InfoMod2.utils.ExtraColors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;

public class PotionChanceTip2 extends TitledToolTip<PotionChanceTip2> {
    public PotionChanceTip2(float width, float height, String... titleRows) {
        super(width, height, titleRows);
    }

    @Override
    public void renderDetails(SpriteBatch sb) {
        //float detailsLeft = (getContentLeft() + 120) * Settings.scale;
        //float fiveBottom = getContentBottom() + 41;
        float vertSpacing = 47;

        float descLeft = (getContentLeft() + 60) * Settings.scale;
        float percLeft = (getContentLeft() + 209) * Settings.scale;
        float twoTop = detailsTop - 15;

//        FontHelper.renderFontLeftDownAligned(sb, FontHelper.tipBodyFont, "2 Fights: 70.0%", detailsLeft, (fiveBottom + 3 * vertSpacing) * Settings.scale, ExtraColors.TEXT_LIGHT_GREEN );
//        FontHelper.renderFontLeftDownAligned(sb, FontHelper.tipBodyFont, "3 Fights: 88.0%", detailsLeft, (fiveBottom + 2 * vertSpacing) * Settings.scale, ExtraColors.TEXT_LIGHT_GREEN );
//        FontHelper.renderFontLeftDownAligned(sb, FontHelper.tipBodyFont, "4 Fights: 96.4%", detailsLeft, (fiveBottom + vertSpacing) * Settings.scale, ExtraColors.TEXT_BLUE_GREEN );
//        FontHelper.renderFontLeftDownAligned(sb, FontHelper.tipBodyFont, "5 Fights: 99.3%", detailsLeft, (fiveBottom) * Settings.scale, ExtraColors.TEXT_LIGHT_BLUE );

        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "2 Fights:", descLeft, (twoTop) * Settings.scale, ExtraColors.TEXT_CREAM );
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "3 Fights:", descLeft, (twoTop - vertSpacing) * Settings.scale, ExtraColors.TEXT_LIGHT_GREEN );
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "4 Fights:", descLeft, (twoTop - 2 * vertSpacing) * Settings.scale, ExtraColors.TEXT_BLUE_GREEN );
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "5 Fights:", descLeft, (twoTop - 3 * vertSpacing) * Settings.scale, ExtraColors.TEXT_LIGHT_BLUE );

        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "70.0 %", percLeft, (twoTop) * Settings.scale, ExtraColors.TEXT_CREAM );
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "88.0 %", percLeft, (twoTop - vertSpacing) * Settings.scale, ExtraColors.TEXT_LIGHT_GREEN );
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "96.4 %", percLeft, (twoTop - 2 * vertSpacing) * Settings.scale, ExtraColors.TEXT_BLUE_GREEN );
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "99.3 %", percLeft, (twoTop - 3 * vertSpacing) * Settings.scale, ExtraColors.TEXT_LIGHT_BLUE );
    }
}
