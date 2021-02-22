package InfoMod2.ui.widgets.tooltips.potions;

import InfoMod2.ui.widgets.tooltips.TitledToolTip;
import InfoMod2.utils.ExtraColors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;

public class PotionChanceTip extends TitledToolTip<PotionChanceTip> {
    private static final float SPACING = 37;

    public PotionChanceTip() {
        super(234, 299, "Potion Chance", "After multiple floors");
    }

    // TODO: setters etc.
    private String twoFightText = "70.0%";
    private String threeFightText = "88.0%";
    private String fourFightText = "96.4%";
    private String fiveFightText = "99.3%";

    @Override
    protected void renderContent(SpriteBatch sb, float left, float top) {
        final float detailsLeft = left + 104;

        float currY = top - 30;

        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "2 Fights", left * Settings.scale, currY * Settings.scale, ExtraColors.TOOLTIP_TEXT_POTION_2);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, twoFightText, detailsLeft * Settings.scale, currY * Settings.scale, ExtraColors.TOOLTIP_TEXT_POTION_2);
        currY -= SPACING;

        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "3 Fights", left * Settings.scale, currY * Settings.scale, ExtraColors.TOOLTIP_TEXT_POTION_3);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, threeFightText, detailsLeft * Settings.scale, currY * Settings.scale, ExtraColors.TOOLTIP_TEXT_POTION_3);
        currY -= SPACING;

        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "4 Fights", left * Settings.scale, currY * Settings.scale, ExtraColors.TOOLTIP_TEXT_POTION_4);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, fourFightText, detailsLeft * Settings.scale, currY * Settings.scale, ExtraColors.TOOLTIP_TEXT_POTION_4);
        currY -= SPACING;

        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "5 Fights", left * Settings.scale, currY * Settings.scale, ExtraColors.TOOLTIP_TEXT_POTION_5);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, fiveFightText, detailsLeft * Settings.scale, currY * Settings.scale, ExtraColors.TOOLTIP_TEXT_POTION_5);
    }
}
