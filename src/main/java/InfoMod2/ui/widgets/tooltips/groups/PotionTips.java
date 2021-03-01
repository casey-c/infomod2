package InfoMod2.ui.widgets.tooltips.groups;

import InfoMod2.ui.widgets.AnchorPosition;
import InfoMod2.ui.widgets.tooltips.potions.PotionChanceTip;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PotionTips {
    private static PotionChanceTip potionChanceTip;

    private static final float POTION_TIPS_LEFT = 1480; //1452; // 1575; // 1600;

    private static void ensureExists() {
        if (potionChanceTip == null)
            potionChanceTip = new PotionChanceTip();//.anchoredAt(POTION_TIPS_LEFT, 1080.0f - 89.0f, AnchorPosition.LEFT_TOP);
    }

    public static void render(SpriteBatch sb, float centerX) {
        ensureExists();

        potionChanceTip.anchoredAt(centerX, 1080.0f - 89.0f, AnchorPosition.CENTER_TOP);
        potionChanceTip.render(sb);
    }

    public static void updatePotionChance(int chance) {
        ensureExists();
        potionChanceTip.updatePotionChance(chance);
    }

    public static String getMainPotionChance() {
        ensureExists();
        return potionChanceTip.getMainChanceText();
    }
}
