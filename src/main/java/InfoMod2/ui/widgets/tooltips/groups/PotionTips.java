package InfoMod2.ui.widgets.tooltips.groups;

import InfoMod2.ui.widgets.AnchorPosition;
import InfoMod2.ui.widgets.tooltips.BossToolTip;
import InfoMod2.ui.widgets.tooltips.EventChanceToolTip;
import InfoMod2.ui.widgets.tooltips.PotionChanceTip;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Arrays;

public class PotionTips {
    private static PotionChanceTip potionChanceTip;
//    private static BossToolTip bossToolTip;

    private static final float POTION_TIPS_LEFT = 1452; // 1575; // 1600;

    public static void render(SpriteBatch sb) {
        if (potionChanceTip == null)
            potionChanceTip = new PotionChanceTip().anchoredAt(POTION_TIPS_LEFT, 1080.0f - 89.0f, AnchorPosition.LEFT_TOP);

        potionChanceTip.render(sb);
    }

//    // TODO: remove DEBUG
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
}
