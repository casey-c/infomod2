package InfoMod2.ui.tips;

import InfoMod2.data.EventDetail;
import InfoMod2.data.EventIntegerRequirement;
import InfoMod2.ui.widgets.AbstractWidget;
import InfoMod2.ui.widgets.AnchorPosition;
import InfoMod2.ui.widgets.text.SimpleLabel;
import InfoMod2.utils.DynamicTextureBox;
import InfoMod2.utils.ExtraColors;
import InfoMod2.utils.ExtraFonts;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputHelper;

import java.util.LinkedList;
import java.util.List;

public class EventDetailTip extends AbstractWidget<EventDetailTip> {
    private EventDetail detail;
    private DynamicTextureBox textureBox;

    private SimpleLabel titleLabel;
    private LinkedList<SimpleLabel> reqLabels = new LinkedList<>();

    private static final float REQ_LABEL_SPACING = 10.0f;
    private LinkedList<Float> reqLabelPositionOffsets = new LinkedList<>();

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

        reqLabels.add(new SimpleLabel(detail.getFloorString(), detail.getFloorNumStringTextColor(), ExtraFonts.smallItalicFont()));

        for (EventIntegerRequirement req : detail.getRequirements()) {
            reqLabels.add(new SimpleLabel(req.getText(), req.getTextColor(), ExtraFonts.smallItalicFont()));
        }

        // Cache the label positions for later
        reqLabelPositionOffsets.clear();

        float currX = 0.0f;

        for (SimpleLabel label : reqLabels) {
            reqLabelPositionOffsets.push(currX);

        }
    }

    @Override public float getPreferredContentWidth() { return 0; }
    @Override public float getPreferredContentHeight() { return 0; }

    private static final float LINE_HEIGHT = 28.0f;

    @Override
    public void render(SpriteBatch sb) {
        // TODO: dynamic size
        float w = 372;
        float h = 208;

        float left = InputHelper.mX + 40.0f;
        float top = InputHelper.mY - 50.0f;
        float right = left + w;

        float textLeft = left + 23.0f;

        // TODO: dynamic size
        float bottom = top - h;

        float titleBottom = top - 46.0f;
        float reqBottom = titleBottom - 30.0f;
        float dividerBottom = reqBottom - 17.0f;

        textureBox.render(sb, left, bottom, w, h);

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

        //float bottom = InputHelper.mY - h - 50.0f;



//        textureBox.render(sb, left, bottom, w, h);
//
//        float textLeft = left + 23.0f;
//
//        float titleBottom = bottom + 162.0f;
//        FontHelper.renderFontLeftDownAligned(sb, FontHelper.tipBodyFont, detail.getName(), textLeft * Settings.scale, titleBottom * Settings.scale, ExtraColors.EVENT_TOOLTIP_TITLE_TEXT);
//
//        // TODO: do it for all requirements dynamically and set the colors appropriately
//        float requirementsBottom = titleBottom - LINE_HEIGHT;
//        FontHelper.renderFontLeftDownAligned(sb, ExtraFonts.smallItalicFont(), detail.getFloorString(), textLeft * Settings.scale, requirementsBottom * Settings.scale, ExtraColors.EVENT_TOOLTIP_REQ_FAILED);
    }
}
