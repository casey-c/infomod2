package InfoMod2.ui;

import InfoMod2.ui.widgets.AnchorPosition;
import InfoMod2.ui.widgets.tooltips.EventChanceToolTip;
import InfoMod2.ui.widgets.tooltips.ExtendedToolTip;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class MapTips {
//    private static EventChanceTip eventChanceTip;
//    private static BossTip bossTip;

    private static EventChanceToolTip eventChanceToolTip;
    private static final float MAP_TIPS_LEFT = 1575; // 1600

    public static void renderCustomMapTips(SpriteBatch sb) {

//        this.left = left;
//        //this.bottom = (float) Settings.HEIGHT - 89.0f - height;
//        this.bottom = 1080.0f - 89.0f - height;

        if (eventChanceToolTip == null)
            eventChanceToolTip = new EventChanceToolTip().anchoredAt(MAP_TIPS_LEFT, 1080.0f - 89.0f, AnchorPosition.LEFT_TOP);

        eventChanceToolTip.render(sb);

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

    public static void updateBossTip(String next) {
//        if (bossTip == null)
//            return;
//
//        ArrayList<String> bosses = new ArrayList<>(bossTip.getBosses());
//        bosses.add(next);
//        bossTip.setBosses(bosses);
    }

    public static void resetBossTip() {
//        if (bossTip == null)
//            return;
//
//        bossTip.setBosses("Hexaghost");
    }
}
