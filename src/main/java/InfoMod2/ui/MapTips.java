package InfoMod2.ui;

import InfoMod2.ui.widgets.AnchorPosition;
import InfoMod2.ui.widgets.tooltips.BossToolTip;
import InfoMod2.ui.widgets.tooltips.EventChanceToolTip;
import InfoMod2.ui.widgets.tooltips.ExtendedToolTip;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MapTips {
//    private static EventChanceTip eventChanceTip;
//    private static BossTip bossTip;

    private static EventChanceToolTip eventChanceToolTip;
    private static BossToolTip bossToolTip;

    private static final float MAP_TIPS_LEFT = 1575; // 1600

    public static void renderCustomMapTips(SpriteBatch sb) {

//        this.left = left;
//        //this.bottom = (float) Settings.HEIGHT - 89.0f - height;
//        this.bottom = 1080.0f - 89.0f - height;

        if (eventChanceToolTip == null)
            eventChanceToolTip = new EventChanceToolTip().anchoredAt(MAP_TIPS_LEFT, 1080.0f - 89.0f, AnchorPosition.LEFT_TOP);

        if (bossToolTip == null) {
            bossToolTip = new BossToolTip().anchoredAt(MAP_TIPS_LEFT, 1080.0f - 89.0f - 350.0f, AnchorPosition.LEFT_TOP);
            bossToolTip.setBosses(new ArrayList<>(Arrays.asList("Hexaghost", "The Champ")));
        }

        eventChanceToolTip.render(sb);
        bossToolTip.render(sb);

//        if (eventChanceTip == null)
//            eventChanceTip = new EventChanceTip().anchoredAtTop(1469);
//
//        if (bossTip == null) {
//            bossTip = new BossTip().anchoredAtTopLeft(1469, eventChanceTip.getBottom() - 10);
//            bossTip.setBosses("Hexaghost");
//        }
//
//        eventChanceTip.render(sb);
//        bossTip.render(sb);
    }

    // TODO: remove DEBUG
    public static void updateBossTip(String next) {
        if (bossToolTip == null)
            return;

        ArrayList<String> bosses = bossToolTip.getBossNames();
        bosses.add(next);
        bossToolTip.setBosses(bosses);
    }

    public static void resetBossTip() {
        if (bossToolTip != null)
            bossToolTip.setBosses(new ArrayList<>());
            //bossToolTip.setBosses(new ArrayList<>(Arrays.asList("Hexaghost", "The Champ")));
    }
}
