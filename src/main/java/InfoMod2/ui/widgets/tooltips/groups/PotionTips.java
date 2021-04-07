package InfoMod2.ui.widgets.tooltips.groups;

import InfoMod2.InfoMod2;
import InfoMod2.ui.widgets.AnchorPosition;
import InfoMod2.ui.widgets.tooltips.potions.PotionChanceTip;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.helpers.PowerTip;

import java.util.ArrayList;

public class PotionTips {
    private static PotionChanceTip potionChanceTip;

    private static void ensureExists() {
        if (potionChanceTip == null)
            potionChanceTip = new PotionChanceTip();
    }

    public static void render(SpriteBatch sb, float centerX) {
        ensureExists();

        potionChanceTip.anchoredAt(centerX, 1080.0f - 89.0f, AnchorPosition.CENTER_TOP);
        potionChanceTip.render(sb);
    }

    public static void updatePotionChance(int chance) {
        ensureExists();
        potionChanceTip.updatePotionChance(chance);

        InfoMod2.updateSlayTheRelicsPotionTips(potionChanceTip.getSlayTheRelicsTips());
    }

    public static String getMainPotionChance() {
        ensureExists();
        return potionChanceTip.getMainChanceText();
    }
}
