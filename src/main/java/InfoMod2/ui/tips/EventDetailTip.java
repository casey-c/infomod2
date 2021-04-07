package InfoMod2.ui.tips;

import InfoMod2.data.EventChoice;
import InfoMod2.data.EventDetail;
import InfoMod2.data.EventRequirement;
import InfoMod2.ui.widgets.AbstractWidget;
import InfoMod2.ui.widgets.AnchorPosition;
import InfoMod2.ui.widgets.cards.EventChoiceCard;
import InfoMod2.ui.widgets.text.SmartLabel;
import InfoMod2.utils.graphics.color.ColorManager;
import InfoMod2.utils.graphics.DynamicTextureBox;
import InfoMod2.utils.graphics.ExtraFonts;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

// jesus parker you really are a freak (this is the worst class i've ever written.... so far)
// it's so bad and in dire need of refactoring (see: a nuke dropped on it) that i don't even want to think about this
// pile of turd meat. seriously wtf who wrote this garbage? i have no recollection of this...
public class EventDetailTip extends AbstractWidget<EventDetailTip> {
    private EventDetail detail;
    private DynamicTextureBox textureBox;

    private SmartLabel titleLabel;

    private SmartLabel floorLabel;
    private ArrayList<SmartLabel> reqLabels = new ArrayList<>();

    private List<EventChoiceCard> choiceCards = new LinkedList<>();

    private SmartLabel notesLabel;

    private float tooltipWidth;
    private float totalHeightChoiceCards;

    private static final float REQ_LABEL_SPACING = 10.0f;
    private static final float CHOICE_CARD_SPACING = 20.0f;

    public EventDetailTip(EventDetail detail) {
        this.detail = detail;
        this.titleLabel = new SmartLabel(detail.name, ColorManager.EVENT_DETAIL_TOOLTIP_TITLE());

        initializeTooltip();

        textureBox = new DynamicTextureBox("InfoMod2/dtb/eventToolTip.atlas")
                .withColors(ColorManager.EVENT_DETAIL_TOOLTIP_DTB_BASE(), ColorManager.EVENT_DETAIL_TOOLTIP_DTB_TRIM(), ColorManager.EVENT_DETAIL_TOOLTIP_DTB_BASE());
    }

    private void initializeTooltip() {
        // Floors: x - y
        if (detail.shouldHandleUsingActsInsteadOfFloors()) {
            floorLabel = new SmartLabel("Act: " + detail.activeDuringAct.toString(), ExtraFonts.smallItalicFont(), Color.WHITE);
        }
        else {
            floorLabel = new SmartLabel("Floor: " + detail.min_floor + " - " + detail.max_floor, ExtraFonts.smallItalicFont(), Color.WHITE);
        }

        reqLabels.add(floorLabel);

        // Other requirements (e.g. requires 35 gold)
        if (detail.hasRequirements()) {
            for (EventRequirement req : detail.requirements)
                reqLabels.add(new SmartLabel(req.getText(), ExtraFonts.smallItalicFont(), Color.WHITE));
        }

        // Choices
        choiceCards.clear();
        float maxCardNameWidth = 0f;

        for (EventChoice choice : detail.choices) {
            EventChoiceCard card = new EventChoiceCard(choice, detail.wide);

            float cardNameWidth = card.getNameWidth();

            maxCardNameWidth = Math.max(cardNameWidth, maxCardNameWidth);

            choiceCards.add(card);
        }

        for (EventChoiceCard card : choiceCards) {
            card.setDesiredNameWidth(maxCardNameWidth + 10.0f);
        }

        // Compute/cache some values to help render the dynamically sized box
        float maxWidth = titleLabel.getPreferredContentWidth();

        float reqLabelTotalWidth = 0.0f;
        for (SmartLabel label : reqLabels) {
            reqLabelTotalWidth += label.getPreferredContentWidth() + REQ_LABEL_SPACING;
        }

        if (reqLabelTotalWidth > maxWidth)
            maxWidth = reqLabelTotalWidth;

        for (EventChoiceCard card : choiceCards) {
            float w = card.getPreferredContentWidth();
            if (w > maxWidth)
                maxWidth = w;
        }

        this.tooltipWidth = maxWidth;

        float sum = 0.0f;
        for (EventChoiceCard card : choiceCards) {
            sum += card.getPreferredContentHeight() + CHOICE_CARD_SPACING;
        }
        this.totalHeightChoiceCards = sum;


        // Notes
        if (detail.hasNotes()) {
            this.notesLabel = new SmartLabel(detail.notes, ExtraFonts.medItalicFontNoShadow(), Color.GRAY, tooltipWidth, 28.0f);
        }
    }

    // --------------------------------------------------------------------------------

    // TODO: fake the floor number by a floor or so on the boss chest floor (to show upcoming act information)
    //    e.g. on floor 17 you can see act 2 but technically still in act 1 on the chest
    public boolean isFloorNumSatisfied() {
        if (CardCrawlGame.isInARun()) {
            int floor = AbstractDungeon.floorNum + 1;

            if (detail.shouldHandleUsingActsInsteadOfFloors()) {
                return detail.activeDuringAct.isFloorActive(floor);
            }
            else {
                // Special case: previewing next act map from boss chest floors
                // NOTE: most certainly not the proper way to do this. (Only doing this way because the JSON data
                // shows the accurate floor numbers - i.e. they reflect the idea that you are guaranteed a fight at the
                // start of acts, and events are further up. I'd rather not lose that information there, so the
                // hackyness needs to come somewhere, so why not here)
                if (floor == 18 || floor == 35)
                    ++floor;

                return floor >= detail.min_floor && floor <= detail.max_floor;
            }
        }
        return false;
    }

    // Returns true if this event is possible to see on the next floor
    public boolean computeActive(HashMap<String, Integer> seenEvents) {
        boolean isActive = true;

        // Check if this event passes the floor requirements
        if (isFloorNumSatisfied()) {
            floorLabel.setFontColor(ColorManager.EVENT_DETAIL_TOOLTIP_REQ_SUCCESS());
        }
        else {
            floorLabel.setFontColor(ColorManager.EVENT_DETAIL_TOOLTIP_REQ_FAILED());
            isActive = false;
        }

        // Check if this event passes all other requirements
        int currentReq = 1; // note: starts at 1 because floor num is handled separately
        if (detail.requirements != null) {
            for (EventRequirement x : detail.requirements) {
                assert currentReq < reqLabels.size();

                if (x.isRequirementSatisfied()) {
                    reqLabels.get(currentReq).setFontColor(ColorManager.EVENT_DETAIL_TOOLTIP_REQ_SUCCESS());
                }
                else {
                    reqLabels.get(currentReq).setFontColor(ColorManager.EVENT_DETAIL_TOOLTIP_REQ_FAILED());
                    isActive = false;
                }

                ++currentReq;
            }
        }

        // Check if this event was already seen
        if (seenEvents.containsKey(detail.id)) {
            int seenFloor = seenEvents.get(detail.id);
            titleLabel.setText(detail.name + " (Seen on floor " + seenFloor + ")");

            isActive = false;
        }
        else {
            titleLabel.setText(detail.name);
        }

        return isActive;
    }

    // --------------------------------------------------------------------------------

    @Override public float getPreferredContentWidth() { return 0; }
    @Override public float getPreferredContentHeight() { return 0; }

    private static final float LINE_HEIGHT = 28.0f;
    private static final float DIVIDER_CHOICE_GAP = 32.0f;
    private static final float CHOICES_NOTES_GAP = 28.0f;

    @Override
    public void render(SpriteBatch sb) {
        float w = tooltipWidth + 46.0f;

        // Need 1080p space on the mouse coordinates
        float left = (InputHelper.mX / Settings.xScale) + 40.0f;
        float top = (InputHelper.mY / Settings.yScale) - 50.0f;

        float textLeft = left + 23.0f;

        float titleBottom = top - 46.0f;
        float reqBottom = titleBottom - 30.0f;
        float dividerBottom = reqBottom - 17.0f;

        // Figure out the bottom of the tool tip
        float realBottom = dividerBottom - DIVIDER_CHOICE_GAP - totalHeightChoiceCards;
        if (notesLabel != null)
            realBottom -= (CHOICES_NOTES_GAP + notesLabel.getPreferredContentHeight());

        // Extra padding?
        realBottom -= 20.0f;

        // Compute the vertical offset (to make sure tips don't run off the bottom of the screen)
        float verticalOffset = 0.0f;
        if (realBottom < 10.0f)
            verticalOffset = 10.0f - realBottom;

        // Figure out the right side of the tool tip
        float realRight = left + tooltipWidth;

        // Compute the horizontal offset (to make sure tips don't run off the right side of the screen)
        float horizontalOffset = 0.0f;
        final float horizontalRefPoint = 1840.0f;
        if (realRight > horizontalRefPoint)
            horizontalOffset = horizontalRefPoint - realRight;

        float h = top - realBottom;
        textureBox.render(sb, left + horizontalOffset, realBottom + verticalOffset, w, h);

        // Render the name of the event
        titleLabel.anchoredAt(textLeft + horizontalOffset, titleBottom + verticalOffset, AnchorPosition.LEFT_BOTTOM);
        titleLabel.render(sb);

        // Render all the requirements for this event
        float currX = textLeft;

        for (SmartLabel label : reqLabels) {
            label.anchoredAt(currX + horizontalOffset, reqBottom + verticalOffset, AnchorPosition.LEFT_BOTTOM);
            label.render(sb);
            currX += label.getPreferredContentWidth() + REQ_LABEL_SPACING;
        }

        // Render the divider line
        final float DIVIDER_OFFSET = 23.0f;
        sb.setColor(ColorManager.UI_DIVIDER());
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, (left + horizontalOffset + DIVIDER_OFFSET) * Settings.xScale, (dividerBottom + verticalOffset) * Settings.yScale, (w - (2 * DIVIDER_OFFSET)) * Settings.xScale, 3.0f);

        // Choice cards
        float currY = dividerBottom - 22.0f;

        for (EventChoiceCard choiceCard : choiceCards) {
            choiceCard.anchoredAt(textLeft + horizontalOffset, currY + verticalOffset, AnchorPosition.LEFT_TOP);
            choiceCard.render(sb);

            currY -= (choiceCard.getPreferredContentHeight() + CHOICE_CARD_SPACING);
        }

        // Render the notes label
        if (notesLabel != null) {
            currY -= CHOICES_NOTES_GAP;
            notesLabel.anchoredAt(textLeft + horizontalOffset, currY + verticalOffset, AnchorPosition.LEFT_TOP);
            notesLabel.render(sb);
        }
    }
}
