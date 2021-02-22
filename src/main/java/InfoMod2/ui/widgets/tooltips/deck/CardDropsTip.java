package InfoMod2.ui.widgets.tooltips.deck;

import InfoMod2.ui.widgets.tooltips.TitledToolTip;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CardDropsTip extends TitledToolTip<CardDropsTip> {
    public CardDropsTip() {
        super(315, 284, "Card Drops (Next Combat)", "Chance to see at least one");
    }

    @Override
    protected void renderContent(SpriteBatch sb, float left, float top) {
        // TODO
    }
}
