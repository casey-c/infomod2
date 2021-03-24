package InfoMod2.ui.widgets.tooltips.groups;

import InfoMod2.ui.widgets.AnchorPosition;
import InfoMod2.ui.widgets.tooltips.map.BossToolTip;
import InfoMod2.ui.widgets.tooltips.map.EventChanceToolTip;
import InfoMod2.utils.math.EventChanceHelper;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Json;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;

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
    }

    public static void updateEventChanceTip() {
        ensureExists();
        eventChanceToolTip.updateHelper();
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
