package InfoMod2.ui.widgets.tooltips.groups;

import InfoMod2.ui.widgets.AnchorPosition;
import InfoMod2.ui.widgets.tooltips.deck.CardDropsTip;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DeckTips {
    private static CardDropsTip cardDropsTip;
    public static boolean SHOULD_RENDER = false;

//    private static final float CARD_TIPS_LEFT = 1552; //1575;
    private static final float CARD_TIPS_RIGHT = 1920 - 52;

    private static void ensureExists() {
        if (cardDropsTip == null)
            cardDropsTip = new CardDropsTip().anchoredAt(CARD_TIPS_RIGHT, 1080.0f - 89.0f, AnchorPosition.RIGHT_TOP);
    }

    public static void renderCustomDeckTips(SpriteBatch sb) {
        ensureExists();
        cardDropsTip.render(sb);
    }

    public static void updateCardDrops() {
        ensureExists();
        cardDropsTip.updateCardChances(AbstractDungeon.cardBlizzRandomizer);
    }

}
