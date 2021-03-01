package InfoMod2.ui.widgets.cards;

import InfoMod2.data.EventChoice;
import InfoMod2.data.EventEffect;
import InfoMod2.ui.widgets.AbstractWidget;
import InfoMod2.ui.widgets.AnchorPosition;
import InfoMod2.ui.widgets.text.v2.NewSmartLabel;
import InfoMod2.utils.graphics.ExtraColors;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.helpers.FontHelper;

public class EventChoiceCard extends AbstractWidget<EventChoiceCard> {
    private NewSmartLabel choiceNameLabel;
    private NewSmartLabel effectsLabel;

    private float desiredNameWidth;

    public EventChoiceCard(EventChoice choice, boolean wide) {
        choiceNameLabel = new NewSmartLabel(choice.getName(), Color.GRAY);

        StringBuilder sb = new StringBuilder();
        for (EventEffect effect : choice.getEffects()) {
            sb.append(effect.getText());
            sb.append(" ");
        }

        float lineWidth = wide ? 450.0f : 350.0f;
        effectsLabel = new NewSmartLabel(sb.toString(), FontHelper.tipBodyFont, ExtraColors.TEXT_EVENT_DESC, lineWidth, 28.0f);
    }

    public float getNameWidth() {
        return choiceNameLabel.getPreferredContentWidth();
    }

    public void setDesiredNameWidth(float w) {
        this.desiredNameWidth = w;
    }

    @Override public float getPreferredContentWidth() { return desiredNameWidth + effectsLabel.getPreferredContentWidth(); }
    @Override public float getPreferredContentHeight() { return Math.max(choiceNameLabel.getPreferredContentHeight(), effectsLabel.getPreferredContentHeight()); }

    @Override
    public void render(SpriteBatch sb) {
        choiceNameLabel.anchoredAt(getContentLeft(), getContentTop(), AnchorPosition.LEFT_TOP);
        choiceNameLabel.render(sb);

        effectsLabel.anchoredAt(getContentLeft() + desiredNameWidth, getContentTop(), AnchorPosition.LEFT_TOP);
        effectsLabel.render(sb);
    }
}
