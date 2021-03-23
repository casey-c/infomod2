package InfoMod2.ui.widgets.text;

import InfoMod2.data.EventDetail;
import InfoMod2.utils.graphics.ExtraColors;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;

public class HoverableEventLabel extends HoverableLabel {
    private EventDetail detail;
    private MultiHitboxEventLabel connected;

    //private Color textColor = ExtraColors.SCREEN_INACTIVE_EVENT_WRONG_ACT;
    private Color baseTextColor = Color.WHITE;
    private Color highlightTextColor = Color.GREEN;

    public HoverableEventLabel(String text, EventDetail detail, MultiHitboxEventLabel connected) {
        super(text);
        this.detail = detail;
        this.connected = connected;
        connected.add(this);
    }

    public void setTextColor(Color base, Color highlighted) {
        this.baseTextColor = base;
        this.highlightTextColor = highlighted;
    }

//    public Color getTextColor() {
//        Color textColor;
//        if (connected.anyHovered()) {
//            //textColor = (detail.isEventPossible()) ? Color.GREEN : Color.RED;
//            textColor = (detail.isEventPossible()) ? ExtraColors.EVENT_SCREEN_CARD_ACTIVE_HOVER : ExtraColors.EVENT_SCREEN_CARD_INACTIVE_HOVER;
//        }
//        else {
//            //textColor = (detail.isEventPossible()) ? ExtraColors.EVENT_ACTIVE : ExtraColors.EVENT_INACTIVE;
//            textColor = (detail.isEventPossible()) ? ExtraColors.EVENT_SCREEN_CARD_ACTIVE : ExtraColors.EVENT_SCREEN_CARD_INACTIVE;
//        }
//
//        return textColor;
//    }

    @Override
    protected void renderText(SpriteBatch sb) {
        Color textColor = connected.anyHovered() ? highlightTextColor : baseTextColor;
        FontHelper.renderFontLeftDownAligned(sb, font, text, getContentLeft() * Settings.xScale, getContentBottom() * Settings.yScale, textColor);
    }
}
