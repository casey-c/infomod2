package InfoMod2.ui.widgets.tooltips.potions;

import InfoMod2.ui.widgets.tooltips.TitledToolTip;
import InfoMod2.utils.graphics.color.ColorManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;

import java.util.ArrayList;

public class PotionChanceTip extends TitledToolTip<PotionChanceTip> {
    private static final float SPACING = 37;

    private String oneFightText = "40%";
    private String twoFightText = "0.0%";
    private String threeFightText = "0.0%";
    private String fourFightText = "0.0%";
    private String fiveFightText = "0.0%";

    // Slay the Relics integration
    private ArrayList<PowerTip> slayTheRelicsTips = new ArrayList<>();
    private PowerTip mainTip;

    public PotionChanceTip() {
        super(234, 299, "Potion Chance", "After multiple floors");

        mainTip = new PowerTip("Potion Chance (InfoMod)", "");
        slayTheRelicsTips.add(mainTip);
    }

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

        // Update Slay the Relics tips
        StringBuilder sb = new StringBuilder("2 Fights: ");
        sb.append(twoFightText);
        sb.append(" NL ");

        sb.append("3 Fights: ");
        sb.append(threeFightText);
        sb.append(" NL ");

        sb.append("4 Fights: ");
        sb.append(fourFightText);
        sb.append(" NL ");

        sb.append("5 Fights: ");
        sb.append(fiveFightText);

        mainTip.body = sb.toString();
    }

    public String getMainChanceText() { return oneFightText; }

    public ArrayList<PowerTip> getSlayTheRelicsTips() {
        return slayTheRelicsTips;
    }


    @Override
    protected void renderContent(SpriteBatch sb, float left, float top) {
        final float detailsLeft = left + 104;

        float currY = top - 30;

        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "2 Fights", left * Settings.xScale, currY * Settings.yScale, ColorManager.SEQ_0());
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, twoFightText, detailsLeft * Settings.xScale, currY * Settings.yScale, ColorManager.SEQ_0());
        currY -= SPACING;

        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "3 Fights", left * Settings.xScale, currY * Settings.yScale, ColorManager.SEQ_1());
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, threeFightText, detailsLeft * Settings.xScale, currY * Settings.yScale, ColorManager.SEQ_1());
        currY -= SPACING;

        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "4 Fights", left * Settings.xScale, currY * Settings.yScale, ColorManager.SEQ_2());
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, fourFightText, detailsLeft * Settings.xScale, currY * Settings.yScale, ColorManager.SEQ_2());
        currY -= SPACING;

        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "5 Fights", left * Settings.xScale, currY * Settings.yScale, ColorManager.SEQ_3());
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, fiveFightText, detailsLeft * Settings.xScale, currY * Settings.yScale, ColorManager.SEQ_3());
    }
}
