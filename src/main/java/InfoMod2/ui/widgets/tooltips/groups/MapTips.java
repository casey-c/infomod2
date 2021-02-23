package InfoMod2.ui.widgets.tooltips.groups;

import InfoMod2.ui.widgets.AnchorPosition;
import InfoMod2.ui.widgets.tooltips.map.BossToolTip;
import InfoMod2.ui.widgets.tooltips.map.EventChanceToolTip;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Arrays;

public class MapTips {
    private static EventChanceToolTip eventChanceToolTip;
    private static BossToolTip bossToolTip;

//    private static final float MAP_TIPS_LEFT = 1575; // 1600
    private static final float MAP_TIPS_RIGHT = 1920 - 52;

    public static void renderCustomMapTips(SpriteBatch sb) {
        if (eventChanceToolTip == null)
            eventChanceToolTip = new EventChanceToolTip().anchoredAt(MAP_TIPS_RIGHT, 1080.0f - 89.0f, AnchorPosition.RIGHT_TOP);

        if (bossToolTip == null) {
            bossToolTip = new BossToolTip().anchoredAt(MAP_TIPS_RIGHT, 1080.0f - 89.0f - 350.0f, AnchorPosition.RIGHT_TOP);
            //bossToolTip.setBosses(new ArrayList<>(Arrays.asList("Hexaghost", "The Champ")));
        }

        eventChanceToolTip.render(sb);
        bossToolTip.render(sb);
    }

    // TODO: remove DEBUG
//    public static void updateBossTip(String next) {
//        if (bossToolTip == null)
//            return;
//
//        ArrayList<String> bosses = bossToolTip.getBossNames();
//        bosses.add(next);
//        bossToolTip.setBosses(bosses);
//    }
//
//    public static void resetBossTip() {
//        if (bossToolTip != null)
//            bossToolTip.setBosses(new ArrayList<>());
//    }

    public static void addBoss(String name, boolean isA20SecondBoss) {
        if (bossToolTip == null)
            bossToolTip = new BossToolTip().anchoredAt(MAP_TIPS_RIGHT, 1080.0f - 89.0f - 350.0f, AnchorPosition.RIGHT_TOP);

        bossToolTip.addBoss(name, isA20SecondBoss);
    }

    public static void resetBossTip() {
        if (bossToolTip == null)
            bossToolTip = new BossToolTip().anchoredAt(MAP_TIPS_RIGHT, 1080.0f - 89.0f - 350.0f, AnchorPosition.RIGHT_TOP);

        bossToolTip.reset();
    }

    public static void refreshBossTip() {
        bossToolTip.updateLabels();
    }
}
