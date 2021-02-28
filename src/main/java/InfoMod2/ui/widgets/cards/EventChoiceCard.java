package InfoMod2.ui.widgets.cards;

import InfoMod2.data.EventChoice;
import InfoMod2.data.EventEffect;
import InfoMod2.ui.widgets.AbstractWidget;
import InfoMod2.ui.widgets.AnchorPosition;
//import InfoMod2.ui.widgets.text.SimpleLabel;
//import InfoMod2.ui.widgets.text.SmartLabel;
import InfoMod2.ui.widgets.text.v2.NewSmartLabel;
import InfoMod2.utils.graphics.ExtraColors;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;

public class EventChoiceCard extends AbstractWidget<EventChoiceCard> {
    private NewSmartLabel choiceNameLabel;
    //private SimpleLabel choiceNameLabel;
    //private SmartLabel effectsLabel;
    private NewSmartLabel effectsLabel;

    private float desiredNameWidth;

    public EventChoiceCard(EventChoice choice, boolean wide) {
        choiceNameLabel = new NewSmartLabel(choice.getName(), Color.GRAY);

        StringBuilder sb = new StringBuilder();
        for (EventEffect effect : choice.getEffects()) {
            sb.append(effect.getText());
            sb.append(" ");
        }

//        if (wide)
//            effectsLabel = new SmartLabel(sb.toString(), ExtraColors.TEXT_EVENT_DESC, FontHelper.tipBodyFont, 450.0f, 28.0f);
//        else
//            effectsLabel = new SmartLabel(sb.toString(), ExtraColors.TEXT_EVENT_DESC, FontHelper.tipBodyFont, 350.0f, 28.0f);

        float lineWidth = wide ? 450.0f : 350.0f;
        effectsLabel = new NewSmartLabel(sb.toString(), FontHelper.tipBodyFont, ExtraColors.TEXT_EVENT_DESC, lineWidth, 28.0f);

//        if (wide)
//            effectsLabel = new NewSmartLabel(sb.toString(), FontHelper.tipBodyFont, ExtraColors.TEXT_EVENT_DESC, 450.0f, 28.0f);
//        else
//            effectsLabel = new NewSmartLabel(sb.toString(), FontHelper.tipBodyFont, ExtraColors.TEXT_EVENT_DESC, 350.0f, 28.0f);

//        System.out.println("***********************");
//        System.out.println("Made event choice card: " + choice.getName());
//        System.out.println();
//        System.out.println("NON SCALED--------");
//        System.out.println("prefWidth: " + getPreferredContentWidth());
//        System.out.println("prefHeight: " + getPreferredContentHeight());
//        System.out.println();
//        System.out.println("Child choiceNameLabel has prefWidth: " + choiceNameLabel.getPreferredContentWidth());
//        System.out.println("Child choiceNameLabel has prefHeight: " + choiceNameLabel.getPreferredContentHeight());
//        System.out.println();
//        System.out.println("Child effectsLabel has prefWidth: " + effectsLabel.getPreferredContentWidth());
//        System.out.println("Child effectsLabel has prefHeight: " + effectsLabel.getPreferredContentHeight());
//        System.out.println();
//        System.out.println("SCALED--------");
//        System.out.println("prefWidth: " + getPreferredContentWidth() * Settings.scale);
//        System.out.println("prefHeight: " + getPreferredContentHeight() * Settings.scale);
//        System.out.println();
//        System.out.println("Child choiceNameLabel has prefWidth: " + choiceNameLabel.getPreferredContentWidth() * Settings.scale);
//        System.out.println("Child choiceNameLabel has prefHeight: " + choiceNameLabel.getPreferredContentHeight() * Settings.scale);
//        System.out.println();
//        System.out.println("Child effectsLabel has prefWidth: " + effectsLabel.getPreferredContentWidth() * Settings.scale);
//        System.out.println("Child effectsLabel has prefHeight: " + effectsLabel.getPreferredContentHeight() * Settings.scale);
//        System.out.println("***********************");
//        System.out.println();
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

//        sb.setColor(ExtraColors.DEBUG_COLOR);
//        sb.draw(ImageMaster.WHITE_SQUARE_IMG, getContentLeft(), getContentTop(), getPreferredContentWidth(), 4);
//
//        sb.setColor(ExtraColors.DEBUG_COLOR_2);
//        sb.draw(ImageMaster.WHITE_SQUARE_IMG, getContentLeft(), getContentBottom(), desiredNameWidth, 4);

    }
}
