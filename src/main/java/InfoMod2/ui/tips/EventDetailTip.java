package InfoMod2.ui.tips;

import InfoMod2.data.EventDetail;
import InfoMod2.ui.widgets.AbstractWidget;
import InfoMod2.utils.DynamicTextureBox;
import InfoMod2.utils.ExtraColors;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputHelper;

public class EventDetailTip extends AbstractWidget<EventDetailTip> {
    private EventDetail detail;
    private DynamicTextureBox textureBox;

    public EventDetailTip(EventDetail detail) {
        this.detail = detail;

        textureBox = new DynamicTextureBox("InfoMod2/event_tooltip/").withColors(ExtraColors.EVENT_TOOLTIP_BASE, ExtraColors.EVENT_TOOLTIP_TRIM, ExtraColors.EVENT_TOOLTIP_BASE);
    }

    @Override public float getPreferredContentWidth() { return 0; }
    @Override public float getPreferredContentHeight() { return 0; }

    private static final float LINE_HEIGHT = 28.0f;

    @Override
    public void render(SpriteBatch sb) {
        float w = 372;
        float h = 208;

        float left = InputHelper.mX + 40.0f;
        float bottom = InputHelper.mY - h - 50.0f;

        textureBox.render(sb, left, bottom, w, h);



//        sb.setColor(ExtraColors.DEBUG_COLOR);
//        sb.draw(ImageMaster.WHITE_SQUARE_IMG, x * Settings.scale, y * Settings.scale, w * Settings.scale, h * Settings.scale);
//
        float textLeft = left + 23.0f;

        float titleBottom = bottom + 162.0f;
        FontHelper.renderFontLeftDownAligned(sb, FontHelper.tipBodyFont, detail.getName(), textLeft * Settings.scale, titleBottom * Settings.scale, ExtraColors.EVENT_TOOLTIP_TITLE_TEXT);

        // TODO: do it for all requirements dynamically and set the colors appropriately
        float requirementsBottom = titleBottom - LINE_HEIGHT;
        FontHelper.renderFontLeftDownAligned(sb, FontHelper.tipBodyFont, detail.getFloorString(), textLeft * Settings.scale, requirementsBottom * Settings.scale, ExtraColors.EVENT_TOOLTIP_REQ_FAILED);
    }
}
