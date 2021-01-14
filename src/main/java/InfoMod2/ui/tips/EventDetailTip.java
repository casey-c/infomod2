package InfoMod2.ui.tips;

import InfoMod2.data.EventChoice;
import InfoMod2.data.EventDetail;
import InfoMod2.data.EventIntegerRequirement;
import InfoMod2.ui.widgets.AbstractWidget;
import InfoMod2.ui.widgets.AnchorPosition;
import InfoMod2.ui.widgets.cards.EventChoiceCard;
import InfoMod2.ui.widgets.text.SimpleLabel;
import InfoMod2.utils.DynamicTextureBox;
import InfoMod2.utils.ExtraColors;
import InfoMod2.utils.ExtraFonts;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputHelper;

import java.util.LinkedList;
import java.util.List;

public class EventDetailTip extends AbstractWidget<EventDetailTip> {
    private EventDetail detail;
    private DynamicTextureBox textureBox;

    private SimpleLabel titleLabel;
    private LinkedList<SimpleLabel> reqLabels = new LinkedList<>();

    private List<EventChoiceCard> choiceCards = new LinkedList<>();
//    private List<AbstractWidget> choiceNameLabels = new LinkedList<>();
//    private ArrayList<LinkedList<SmartLabel>> choiceEffectLabels = new ArrayList<>();

    private float tooltipWidth;
    private float totalHeightChoiceCards;

    private static final float REQ_LABEL_SPACING = 10.0f;
    private static final float CHOICE_CARD_SPACING = 10.0f;

    public EventDetailTip(EventDetail detail) {
        setDetail(detail);
        textureBox = new DynamicTextureBox("InfoMod2/event_tooltip/").withColors(ExtraColors.EVENT_TOOLTIP_BASE, ExtraColors.EVENT_TOOLTIP_TRIM, ExtraColors.EVENT_TOOLTIP_BASE);
    }

    public void setDetail(EventDetail detail) {
        this.detail = detail;
        this.titleLabel = new SimpleLabel(detail.getName(), ExtraColors.EVENT_TOOLTIP_TITLE_TEXT);
        updateDetails();
    }

    // TODO: can probably just keep these details around instead of remaking them. We'll see if this needs optimizing
    public void updateDetails() {
        reqLabels.clear();
//        choiceNameLabels.clear();
//        choiceEffectLabels.clear();

        // Floors: x - y
        reqLabels.add(new SimpleLabel(detail.getFloorString(), detail.getFloorNumStringTextColor(), ExtraFonts.smallItalicFont()));

        // Other requirements (e.g. requires 35 gold)
        for (EventIntegerRequirement req : detail.getRequirements()) {
            reqLabels.add(new SimpleLabel(req.getText(), req.getTextColor(), ExtraFonts.smallItalicFont()));
        }

        // Choices
        choiceCards.clear();
        float maxCardNameWidth = 0f;

        for (EventChoice choice : detail.getChoices()) {
            EventChoiceCard card = new EventChoiceCard(choice, detail.isWide());

            float cardNameWidth = card.getNameWidth();
            if (cardNameWidth > maxCardNameWidth)
                maxCardNameWidth = cardNameWidth;

            choiceCards.add(card);
        }

        for (EventChoiceCard card : choiceCards) {
            card.setDesiredNameWidth(maxCardNameWidth + 10.0f);
        }

        // Compute/cache some values to help render the dynamically sized box
        float maxWidth = titleLabel.getPreferredContentWidth();

        float reqLabelTotalWidth = 0.0f;
        for (SimpleLabel label : reqLabels) {
            reqLabelTotalWidth += label.getPreferredContentWidth() + REQ_LABEL_SPACING;
        }

        if (reqLabelTotalWidth > maxWidth)
            maxWidth = reqLabelTotalWidth;

        System.out.println("Looking at choice cards (starting at maxwidth of " + maxWidth + ")");
        for (EventChoiceCard card : choiceCards) {
            float w = card.getPreferredContentWidth();
            System.out.println("Card name width: " + card.getNameWidth());
            System.out.println("Card pref width: " + card.getPreferredContentWidth());
            if (w > maxWidth)
                maxWidth = w;
        }

        System.out.println("Final max width: " + maxWidth);

        this.tooltipWidth = maxWidth;

        float sum = 0.0f;
        for (EventChoiceCard card : choiceCards) {
            sum += card.getPreferredContentHeight() + CHOICE_CARD_SPACING;
        }
        this.totalHeightChoiceCards = sum;


    }

    @Override public float getPreferredContentWidth() { return 0; }
    @Override public float getPreferredContentHeight() { return 0; }

    private static final float LINE_HEIGHT = 28.0f;

    @Override
    public void render(SpriteBatch sb) {
        // TODO: dynamic size
        //float w = 372;
        //float h = 208;
        float w = tooltipWidth + 46.0f;

        float left = InputHelper.mX + 40.0f;
        float top = InputHelper.mY - 50.0f;

        float textLeft = left + 23.0f;

        // TODO: dynamic size
        //float bottom = top - h;

        float titleBottom = top - 46.0f;
        float reqBottom = titleBottom - 30.0f;
        float dividerBottom = reqBottom - 17.0f;

        // Figure out the bottom of the tool tip
        float realBottom = dividerBottom - 32.0f - totalHeightChoiceCards;
        float h = top - realBottom;

        textureBox.render(sb, left, realBottom, w, h);

        // Render the name of the event
        titleLabel.anchoredAt(textLeft, titleBottom, AnchorPosition.LEFT_BOTTOM);
        titleLabel.render(sb);

        // Render all the requirements for this event
        float currX = textLeft;

        for (SimpleLabel label : reqLabels) {
            label.anchoredAt(currX, reqBottom, AnchorPosition.LEFT_BOTTOM);
            label.render(sb);
            currX += label.getPreferredContentWidth() + REQ_LABEL_SPACING;
        }

        // Render the divider line
        final float DIVIDER_OFFSET = 23.0f;
        sb.setColor(ExtraColors.DIVIDER_COLOR);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, (left + DIVIDER_OFFSET) * Settings.scale, dividerBottom * Settings.scale, (w - (2 * DIVIDER_OFFSET)) * Settings.scale, 3.0f);

        // Choice cards
        //float currY = dividerBottom - 16.0f;
        float currY = dividerBottom - 22.0f;
        //System.out.println("\n\n==============================");
        //System.out.println("Choice start: currY = " + currY);

        for (EventChoiceCard choiceCard : choiceCards) {
            choiceCard.anchoredAt(textLeft, currY, AnchorPosition.LEFT_TOP);
            choiceCard.render(sb);

            //System.out.println("Choice pref content height is " + choiceCard.getPreferredContentHeight());

            currY -= (choiceCard.getPreferredContentHeight() + 10.0f);
            //System.out.println("New currY: " + currY);
        }

        //System.out.println("-----------------\n\n");

//        int index = 0;
//        for (AbstractWidget label : choiceNameLabels) {
//            label.anchoredAt(textLeft, currY, AnchorPosition.LEFT_BOTTOM);
//            label.render(sb);
//
//            // Render effects of this choice
//            LinkedList<SmartLabel> effects = choiceEffectLabels.get(index++);
//
//            currX = textLeft + maxChoiceNameWidth + 15.0f;
//            for (SmartLabel effect : effects) {
//                effect.anchoredAt(currX, currY, AnchorPosition.LEFT_TOP);
//                effect.render(sb);
//
//                currX += effect.getPreferredContentWidth() + 10.0f;
//            }
//
//            currY -= LINE_HEIGHT;
//        }

    }
}
