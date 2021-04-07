package InfoMod2.ui.widgets.tooltips.settings;

import InfoMod2.ui.widgets.tooltips.TitledToolTip;
import InfoMod2.utils.graphics.color.ColorManager;
import InfoMod2.utils.graphics.ExtraFonts;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class MiscStatsTip extends TitledToolTip<MiscStatsTip> {
    private String cardPlaysTurn, cardPlaysCombat, cardPlaysRun;
    private String avgCardPlaysCombat, avgCardPlaysRun;

    private int numCardsTurn, numCardsCombat, numCardsRun;
    private int numTurnsCombat, numTurnsRun;

    private static final float SPACING = 37;

    public MiscStatsTip() {
        super(431, 264, "Misc. Stats", "Current Turn: 0");

        reset();
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
        reset();

        updateCardPlaysText();
    }

    private void reset() {
        numCardsCombat = 0;
        numCardsTurn = 0;
        numCardsRun = 0;

        numTurnsCombat = 0;
        numTurnsRun = 0;
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

    public String getSlayTheRelicsFormattedString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Cards played: NL ");
        sb.append(" Turn: ");
        sb.append(cardPlaysTurn);
        sb.append(" NL ");
        sb.append(" Combat: ");
        sb.append(cardPlaysCombat);
        sb.append(" NL ");
        sb.append(" Run: ");
        sb.append(cardPlaysRun);
        sb.append(" NL NL ");
        sb.append("Avg Cards / Turn: NL ");
        sb.append(" Combat: ");
        sb.append(avgCardPlaysCombat);
        sb.append(" NL ");
        sb.append(" Run: ");
        sb.append(avgCardPlaysRun);

        return sb.toString();
    }

    // --------------------------------------------------------------------------------

    @Override
    protected void renderContent(SpriteBatch sb, float left, float top) {
        float turnLeft = left + 146;
        float combatLeft = turnLeft + 85;
        float runLeft = combatLeft + 85;

        float currY = top - 30;

        // Headers
        FontHelper.renderFontLeftTopAligned(sb, ExtraFonts.smallItalicFont(), "Turn", turnLeft * Settings.xScale, currY * Settings.yScale, ColorManager.LIGHT_GRAY());
        FontHelper.renderFontLeftTopAligned(sb, ExtraFonts.smallItalicFont(), "Combat", combatLeft * Settings.xScale, currY * Settings.yScale, ColorManager.LIGHT_GRAY());
        FontHelper.renderFontLeftTopAligned(sb, ExtraFonts.smallItalicFont(), "Run", runLeft * Settings.xScale, currY * Settings.yScale, ColorManager.LIGHT_GRAY());

        currY -= SPACING;

        // Card Plays
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Cards Played:", left * Settings.xScale, currY * Settings.yScale, ColorManager.LIGHT_GRAY());
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, cardPlaysTurn, turnLeft * Settings.xScale, currY * Settings.yScale, ColorManager.QUAL_GREEN());
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, cardPlaysCombat, combatLeft * Settings.xScale, currY * Settings.yScale, ColorManager.QUAL_BLUE());
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, cardPlaysRun, runLeft * Settings.xScale, currY * Settings.yScale, ColorManager.QUAL_YELLOW());

        currY -= SPACING;

        // Avg Card Per Turn
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Avg. Cards Per Turn:", left * Settings.xScale, currY * Settings.yScale, ColorManager.LIGHT_GRAY());
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, avgCardPlaysCombat, combatLeft * Settings.xScale, currY * Settings.yScale, ColorManager.QUAL_BLUE());
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, avgCardPlaysRun, runLeft * Settings.xScale, currY * Settings.yScale, ColorManager.QUAL_YELLOW());
    }


    // --------------------------------------------------------------------------------
    // TODO: should the combat stats (currently persistent between floors while in game, but intentionally lost between
    //    saves/loads) actually be this way? originally: i intended the combat stats to stick around after battles
    //    (resets only on the start of the next battle) so you'll be able to check it

    public JsonObject serialize() {
        JsonObject obj = new JsonObject();

        boolean isCurrentlyFighting = false; //TODO

        AbstractRoom room = AbstractDungeon.getCurrRoom();
        if (room != null && room.phase == AbstractRoom.RoomPhase.COMBAT)
            isCurrentlyFighting = true;

        // Need to subtract out the current combat stats, since saving/loading will restart the fight
        if (isCurrentlyFighting) {
            obj.add("numCardsRun", new JsonPrimitive(numCardsRun - numCardsCombat));
            obj.add("numTurnsRun", new JsonPrimitive(numTurnsRun - numTurnsCombat));
        }
        // We shouldn't subtract out the current combat stuff, since the Run totals will be fixed
        else {
            obj.add("numCardsRun", new JsonPrimitive(numCardsRun));
            obj.add("numTurnsRun", new JsonPrimitive(numTurnsRun));
        }

        System.out.println("\n******************");
        System.out.println("SERIALIZING MISC STATS TIP: ");
        System.out.println(obj.toString());
        System.out.println("******************");
        System.out.println("Current status: " + toString());
        System.out.println("******************\n");

        return obj;
    }

    public void deserialize(JsonObject obj) {
        System.out.println("Deserializing (PRE reset): " + toString());
        reset();
        System.out.println("Deserializing (POST reset): " + toString());

        if (obj.has("numCardsRun"))
            this.numCardsRun = obj.get("numCardsRun").getAsInt();

        if (obj.has("numTurnsRun"))
            this.numTurnsRun = obj.get("numTurnsRun").getAsInt();

        updateCardPlaysText();
    }

    // --------------------------------------------------------------------------------
    // DEBUG

    @Override
    public String toString() {
        return "MiscStatsTip{" +
                "cardPlaysTurn='" + cardPlaysTurn + '\'' +
                ", cardPlaysCombat='" + cardPlaysCombat + '\'' +
                ", cardPlaysRun='" + cardPlaysRun + '\'' +
                ", avgCardPlaysCombat='" + avgCardPlaysCombat + '\'' +
                ", avgCardPlaysRun='" + avgCardPlaysRun + '\'' +
                ", numCardsTurn=" + numCardsTurn +
                ", numCardsCombat=" + numCardsCombat +
                ", numCardsRun=" + numCardsRun +
                ", numTurnsCombat=" + numTurnsCombat +
                ", numTurnsRun=" + numTurnsRun +
                '}';
    }
}
