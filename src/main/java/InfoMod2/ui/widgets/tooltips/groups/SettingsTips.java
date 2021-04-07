package InfoMod2.ui.widgets.tooltips.groups;

import InfoMod2.ui.widgets.AnchorPosition;
import InfoMod2.ui.widgets.tooltips.settings.MiscStatsTip;
import InfoMod2.utils.integration.SlayTheRelicsIntegration;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.gson.JsonObject;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.PowerTip;

import java.util.ArrayList;

public class SettingsTips {
    private static MiscStatsTip miscStatsTip;

    public static boolean SHOULD_RENDER = false;

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
        updateSlayTheRelics();
    }

    public static void startRun() {
        ensureExists();
        miscStatsTip.startRun();
        updateSlayTheRelics();
    }

    public static void startCombat() {
        ensureExists();
        miscStatsTip.startCombat();
        updateSlayTheRelics();
    }

    public static void startTurn() {
        ensureExists();
        miscStatsTip.startTurn();
        updateSlayTheRelics();
    }

    // --------------------------------------------------------------------------------

    // Slay the Relics integration
    private static ArrayList<PowerTip> slayTheRelicsTips = new ArrayList<>();
    private static PowerTip mainTip;
    private static Hitbox slayTheRelicsHitbox;

    public static void initializeSlayTheRelics() {
        mainTip = new PowerTip("Misc. Stats (InfoMod)", "");

        slayTheRelicsTips.clear();
        slayTheRelicsTips.add(mainTip);

        slayTheRelicsHitbox = new Hitbox(1835, 1015, 85, 65);
    }

    public static void updateSlayTheRelics() {
        if (mainTip == null)
            initializeSlayTheRelics();

        mainTip.body = miscStatsTip.getSlayTheRelicsFormattedString();

        SlayTheRelicsIntegration.update("miscStats", slayTheRelicsHitbox, slayTheRelicsTips);
    }

    // --------------------------------------------------------------------------------

    public static JsonObject serialize() {
        if (miscStatsTip == null || !CardCrawlGame.isInARun())
            return new JsonObject();
        else {
            return miscStatsTip.serialize();
        }
    }

    public static void deserialize(JsonObject obj) {
        ensureExists();
        miscStatsTip.deserialize(obj);
    }
}
