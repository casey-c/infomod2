package InfoMod2.ui.widgets.tooltips.groups;

import InfoMod2.ui.widgets.AnchorPosition;
import InfoMod2.ui.widgets.tooltips.map.BossToolTip;
import InfoMod2.ui.widgets.tooltips.map.EventChanceToolTip;
import InfoMod2.utils.integration.SlayTheRelicsIntegration;
import InfoMod2.utils.math.EventChanceHelper;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.gson.JsonObject;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.PowerTip;

import java.util.ArrayList;

public class MapTips {
    private static EventChanceToolTip eventChanceToolTip;
    private static BossToolTip bossToolTip;

    private static final float MAP_TIPS_RIGHT = 1920 - 52;

    public static boolean SHOULD_RENDER = false;

    private static void ensureExists() {
        if (eventChanceToolTip == null)
            eventChanceToolTip = new EventChanceToolTip().anchoredAt(MAP_TIPS_RIGHT, 1080.0f - 89.0f, AnchorPosition.RIGHT_TOP);

        if (bossToolTip == null)
            bossToolTip = new BossToolTip().anchoredAt(MAP_TIPS_RIGHT, 1080.0f - 89.0f - 350.0f, AnchorPosition.RIGHT_TOP);
    }

    public static void renderCustomMapTips(SpriteBatch sb) {
        ensureExists();

        eventChanceToolTip.render(sb);
        bossToolTip.render(sb);
    }


    public static void addBoss(String name, boolean isA20SecondBoss) {
        ensureExists();
        bossToolTip.addBoss(name, isA20SecondBoss);
    }

    public static void resetBossTip() {
        ensureExists();
        bossToolTip.reset();
    }

    public static void refreshBossTip() {
        ensureExists();
        bossToolTip.updateLabels();

        updateSlayTheRelics();
    }

    public static void updateEventChanceTip() {
        ensureExists();
        eventChanceToolTip.updateHelper();

        updateSlayTheRelics();
    }

    public static void print() {
        ensureExists();
        System.out.println(bossToolTip);
    }

    public static EventChanceHelper getEventChanceHelper() {
        ensureExists();
        return eventChanceToolTip.getHelper();
    }

    // --------------------------------------------------------------------------------

    // Slay the Relics integration
    private static ArrayList<PowerTip> slayTheRelicsTips = new ArrayList<>();
    private static PowerTip mainTip;
    private static PowerTip bossTip;
    private static Hitbox slayTheRelicsHitbox;
    // TODO boss tip also

    public static void initializeSlayTheRelics() {
        mainTip = new PowerTip("Next [?] Floor (InfoMod)", "");
        bossTip = new PowerTip("Upcoming Bosses (InfoMod)", "");

        slayTheRelicsTips.clear();
        slayTheRelicsTips.add(mainTip);
        slayTheRelicsTips.add(bossTip);

        // TODO: pretty sure hitboxes are centered or something weird (probably need to shift left and down half size)
        //   (can't remember right now)
        // update: they seem to be working on at least 1 stream (1080p works, idk about other resolutions)
        slayTheRelicsHitbox = new Hitbox(1687, 1015, 85, 65);
    }

    public static void updateSlayTheRelics() {
        if (mainTip == null)
            initializeSlayTheRelics();

        EventChanceHelper helper = eventChanceToolTip.getHelper();

        // Event tip
        StringBuilder sb = new StringBuilder("Event: ");
        sb.append(helper.getEventChance());
        sb.append(" NL ");

        sb.append("Shrine: ");
        sb.append(helper.getShrineChance());
        sb.append(" NL ");

        sb.append("Fight: ");
        sb.append(helper.getFightChance());
        sb.append(" NL ");

        sb.append("Shop: ");
        sb.append(helper.getShopChance());
        sb.append(" NL ");

        sb.append("Treasure: ");
        sb.append(helper.getTreasureChance());

        mainTip.body = sb.toString();

        // Boss tip
        bossTip.body = bossToolTip.labelsToFormattedSlayTheRelicsString();

        // Update the integration
        SlayTheRelicsIntegration.update("eventChances", slayTheRelicsHitbox, slayTheRelicsTips);
    }

    // --------------------------------------------------------------------------------

    public static JsonObject serialize() {
        if (bossToolTip == null || !CardCrawlGame.isInARun())
            return new JsonObject();
        else {
            return bossToolTip.serialize();
        }
    }

    public static void deserialize(JsonObject obj) {
        ensureExists();
        bossToolTip.deserialize(obj);
    }
}
