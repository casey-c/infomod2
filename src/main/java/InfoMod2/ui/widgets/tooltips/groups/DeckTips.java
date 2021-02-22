package InfoMod2.ui.widgets.tooltips.groups;

import InfoMod2.ui.widgets.AnchorPosition;
import InfoMod2.ui.widgets.tooltips.deck.CardDropsTip;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DeckTips {
    private static CardDropsTip cardDropsTip;

//    private static final float CARD_TIPS_LEFT = 1552; //1575;
    private static final float CARD_TIPS_RIGHT = 1920 - 52;

    public static void renderCustomDeckTips(SpriteBatch sb) {
        if (cardDropsTip == null)
            cardDropsTip = new CardDropsTip().anchoredAt(CARD_TIPS_RIGHT, 1080.0f - 89.0f, AnchorPosition.RIGHT_TOP);

        cardDropsTip.render(sb);
    }

}
