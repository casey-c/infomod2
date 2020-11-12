package InfoMod2.ui;

import InfoMod2.utils.ExtraColors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;

public class PotionChanceTip extends ThiccToolTip {
    public PotionChanceTip() {
        super(1383, 400, 300);
        //box.render(sb, 1383, 691, 400, 300);
    }

    @Override
    protected void renderForeground(SpriteBatch sb) {
        //FontHelper.renderFontLeftDownAligned(sb, FontHelper.tipBodyFont, "Test", 1450, 800, Settings.CREAM_COLOR);
        FontHelper.renderFontLeftDownAligned(sb, FontHelper.tipBodyFont, "Seeing at least one potion after", (left + 56) * Settings.scale, (bottom + 250) * Settings.scale, ExtraColors.TEXT_CREAM );
        FontHelper.renderFontLeftDownAligned(sb, FontHelper.tipBodyFont, "multiple fights:", (left + 130) * Settings.scale, (bottom + 222) * Settings.scale, ExtraColors.TEXT_CREAM );

        FontHelper.renderFontLeftDownAligned(sb, FontHelper.tipBodyFont, "2 Fights: 70.0%", (left + 120) * Settings.scale, (bottom + 155) * Settings.scale, ExtraColors.TEXT_LIGHT_GREEN );
        FontHelper.renderFontLeftDownAligned(sb, FontHelper.tipBodyFont, "3 Fights: 88.0%", (left + 120) * Settings.scale, (bottom + 123) * Settings.scale, ExtraColors.TEXT_LIGHT_GREEN );
        FontHelper.renderFontLeftDownAligned(sb, FontHelper.tipBodyFont, "4 Fights: 96.4%", (left + 120) * Settings.scale, (bottom + 91) * Settings.scale, ExtraColors.TEXT_BLUE_GREEN );
        FontHelper.renderFontLeftDownAligned(sb, FontHelper.tipBodyFont, "5 Fights: 99.3%", (left + 120) * Settings.scale, (bottom + 59) * Settings.scale, ExtraColors.TEXT_LIGHT_BLUE );
    }
}
