package InfoMod2.ui.widgets.text;

import InfoMod2.data.EventDetail;
import InfoMod2.ui.tips.EventDetailTip;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;

public class HoverableEventLabel extends HoverableLabel {
    private EventDetail detail;
    private MultiHitboxEventLabel connected;

    public HoverableEventLabel(String text, EventDetail detail, MultiHitboxEventLabel connected) {
        super(text);
        this.detail = detail;
        this.connected = connected;
        connected.add(this);
    }

    @Override
    protected void renderText(SpriteBatch sb) {
        Color debugColor = connected.anyHovered() ? Color.GREEN : fontColor;
        FontHelper.renderFontLeftDownAligned(sb, font, text, getContentLeft(), getContentBottom(), debugColor);
    }
}
