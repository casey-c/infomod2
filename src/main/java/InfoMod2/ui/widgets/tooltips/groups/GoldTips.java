package InfoMod2.ui.widgets.tooltips.groups;

import InfoMod2.ui.widgets.AnchorPosition;
import InfoMod2.ui.widgets.tooltips.gold.ShopPriceToolTip;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GoldTips {
    private static ShopPriceToolTip shopPriceToolTip;

    private static void ensureExists() {
        if (shopPriceToolTip == null)
            shopPriceToolTip = new ShopPriceToolTip();
    }

    public static void render(SpriteBatch sb, float centerX) {
        ensureExists();

        shopPriceToolTip.anchoredAt(centerX, 1080.0f - 89.0f, AnchorPosition.CENTER_TOP);
        shopPriceToolTip.render(sb);
    }

    // TODO: update functions
    public static void updateShopPrices() {
        ensureExists();
        shopPriceToolTip.updatePrices();
    }
}
