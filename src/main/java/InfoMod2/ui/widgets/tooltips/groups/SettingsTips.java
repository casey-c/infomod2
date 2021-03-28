package InfoMod2.ui.widgets.tooltips.groups;

import InfoMod2.ui.widgets.AnchorPosition;
import InfoMod2.ui.widgets.tooltips.settings.MiscStatsTip;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.gson.JsonObject;
import com.megacrit.cardcrawl.core.CardCrawlGame;

public class SettingsTips {
    private static MiscStatsTip miscStatsTip;

    public static boolean SHOULD_RENDER = false;

    //    private static final float CARD_TIPS_LEFT = 1552; //1575;
    private static final float CARD_TIPS_RIGHT = 1920 - 52;

    private static void ensureExists() {
        if (miscStatsTip == null)
            miscStatsTip = new MiscStatsTip().anchoredAt(CARD_TIPS_RIGHT, 1080.0f - 89.0f, AnchorPosition.RIGHT_TOP);
    }

    public static void renderCustomTips(SpriteBatch sb) {
        ensureExists();
        miscStatsTip.render(sb);
    }

    // --------------------------------------------------------------------------------

    public static void playCard() {
        ensureExists();
        miscStatsTip.playCard();
    }

    public static void startRun() {
        ensureExists();
        miscStatsTip.startRun();
    }

    public static void startCombat() {
        ensureExists();
        miscStatsTip.startCombat();
    }

    public static void startTurn() {
        ensureExists();
        miscStatsTip.startTurn();
    }

    // --------------------------------------------------------------------------------

    public static JsonObject serialize() {
        System.out.println("SettingsTips: serialize");

        if (miscStatsTip == null || !CardCrawlGame.isInARun())
            return new JsonObject();
        else {
            return miscStatsTip.serialize();
        }
    }

    public static void deserialize(JsonObject obj) {
        System.out.println("SettingsTips: deserialize");

        ensureExists();
        miscStatsTip.deserialize(obj);
    }
}
