package InfoMod2.ui.widgets.tooltips.potions;

import InfoMod2.ui.widgets.tooltips.TitledToolTip;
import InfoMod2.utils.ExtraColors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;

public class PotionChanceTip extends TitledToolTip<PotionChanceTip> {
    private static final float SPACING = 37;

    public PotionChanceTip() {
        super(234, 299, "Potion Chance", "After multiple floors");
    }

    // TODO: setters etc.
    private String oneFightText = "40%";
    private String twoFightText = "0.0%";
    private String threeFightText = "0.0%";
    private String fourFightText = "0.0%";
    private String fiveFightText = "0.0%";

    public void updatePotionChance(int chance) {
        float f = ((float) chance) / 100.0f;

        float after2 = (1.0f - f) * (0.9f - f);
        float after3 = after2 * (0.8f - f);
        float after4 = after3 * (0.7f - f);
        float after5 = after4 * (0.6f - f);

        if (AbstractDungeon.player.hasRelic("Sozu")) {
            after2 = after3 = after4 = after5 = 1.0f;
        }

        this.oneFightText = chance + "%";
        this.twoFightText = String.format("%.01f%%", ((1.0f - after2) * 100.0f));
        this.threeFightText = String.format("%.01f%%", ((1.0f - after3) * 100.0f));
        this.fourFightText = String.format("%.01f%%", ((1.0f - after4) * 100.0f));
        this.fiveFightText = String.format("%.01f%%", ((1.0f - after5) * 100.0f));
    }

    public String getMainChanceText() { return oneFightText; }

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
