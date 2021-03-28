package InfoMod2.ui.widgets.tooltips.settings;

import InfoMod2.ui.widgets.tooltips.TitledToolTip;
import InfoMod2.utils.graphics.ExtraColors;
import InfoMod2.utils.graphics.ExtraFonts;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;

public class MiscStatsTip extends TitledToolTip<MiscStatsTip> {
    private String cardPlaysTurn, cardPlaysCombat, cardPlaysRun;
    private String avgCardPlaysCombat, avgCardPlaysRun;

    private int numCardsTurn = 0;
    private int numCardsCombat = 0;
    private int numCardsRun = 0;

    private int numTurnsCombat = 0;
    private int numTurnsRun = 0;

    private static final float SPACING = 37;

    public MiscStatsTip() {
        super(431, 264, "Misc. Stats", "Current Turn: 0");
        updateCardPlaysText();
    }

    // --------------------------------------------------------------------------------
    // TODO: verify the logic here (not really thinking too hard at the moment - 99% chance of mistakes)

    public void playCard() {
        ++numCardsTurn;
        ++numCardsCombat;
        ++numCardsRun;

        updateCardPlaysText();
    }

    public void startTurn() {
        ++numTurnsCombat;
        ++numTurnsRun;

        numCardsTurn = 0;

        this.subtitle = "Current Turn: " + numTurnsCombat;

        updateCardPlaysText();
    }

    public void startCombat() {
        numCardsCombat = 0;
        numTurnsCombat = 0;

        startTurn();
    }

    public void startRun() {
        numCardsCombat = 0;
        numCardsTurn = 0;
        numCardsRun = 0;

        numTurnsCombat = 0;
        numTurnsRun = 0;

        updateCardPlaysText();
    }

    // --------------------------------------------------------------------------------

    // Update the Strings that we display in the render
    public void updateCardPlaysText() {
        this.cardPlaysTurn = String.format("%d", numCardsTurn);
        this.cardPlaysCombat = String.format("%d", numCardsCombat);
        this.cardPlaysRun = String.format("%d", numCardsRun);

        float avgCombat = (numTurnsCombat > 0) ? (float)numCardsCombat / (float)numTurnsCombat : 0;
        this.avgCardPlaysCombat = String.format("%.2f", avgCombat);

        float avgRun = (numTurnsRun > 0) ? (float)numCardsRun / (float)numTurnsRun : 0;
        this.avgCardPlaysRun = String.format("%.2f", avgRun);
    }

    // --------------------------------------------------------------------------------

    @Override
    protected void renderContent(SpriteBatch sb, float left, float top) {
        float turnLeft = left + 146;
        float combatLeft = turnLeft + 85;
        float runLeft = combatLeft + 85;

        float currY = top - 30;

        // Headers
        FontHelper.renderFontLeftTopAligned(sb, ExtraFonts.smallItalicFont(), "Turn", turnLeft * Settings.xScale, currY * Settings.yScale, ExtraColors.TOOLTIP_TEXT_GRAY);
        FontHelper.renderFontLeftTopAligned(sb, ExtraFonts.smallItalicFont(), "Combat", combatLeft * Settings.xScale, currY * Settings.yScale, ExtraColors.TOOLTIP_TEXT_GRAY);
        FontHelper.renderFontLeftTopAligned(sb, ExtraFonts.smallItalicFont(), "Run", runLeft * Settings.xScale, currY * Settings.yScale, ExtraColors.TOOLTIP_TEXT_GRAY);

        currY -= SPACING;

        // Card Plays
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Cards Played:", left * Settings.xScale, currY * Settings.yScale, ExtraColors.TOOLTIP_TEXT_GRAY);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, cardPlaysTurn, turnLeft * Settings.xScale, currY * Settings.yScale, ExtraColors.QUAL_GREEN);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, cardPlaysCombat, combatLeft * Settings.xScale, currY * Settings.yScale, ExtraColors.QUAL_BLUE);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, cardPlaysRun, runLeft * Settings.xScale, currY * Settings.yScale, ExtraColors.QUAL_YELLOW);

        currY -= SPACING;

        // Avg Card Per Turn
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Avg. Cards Per Turn:", left * Settings.xScale, currY * Settings.yScale, ExtraColors.TOOLTIP_TEXT_GRAY);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, avgCardPlaysCombat, combatLeft * Settings.xScale, currY * Settings.yScale, ExtraColors.QUAL_BLUE);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, avgCardPlaysRun, runLeft * Settings.xScale, currY * Settings.yScale, ExtraColors.QUAL_YELLOW);
    }
}
