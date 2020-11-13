package InfoMod2.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class MapTips {
    private static EventChanceTip eventChanceTip;
    private static BossTip bossTip;

    public static void renderCustomMapTips(SpriteBatch sb) {
        if (eventChanceTip == null)
            eventChanceTip = new EventChanceTip().anchoredAtTop(1469);

        if (bossTip == null) {
            bossTip = new BossTip().anchoredAtTopLeft(1469, eventChanceTip.getBottom() - 10);
            bossTip.setBosses("Hexaghost");
        }

        eventChanceTip.render(sb);
        bossTip.render(sb);
    }

    public static void updateBossTip(String next) {
        if (bossTip == null)
            return;

        ArrayList<String> bosses = new ArrayList<>(bossTip.getBosses());
        bosses.add(next);
        bossTip.setBosses(bosses);
    }

    public static void resetBossTip() {
        if (bossTip == null)
            return;

        bossTip.setBosses("Hexaghost");
    }
}
