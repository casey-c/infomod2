package InfoMod2.ui.widgets.text;

import InfoMod2.data.EventDetail;
import InfoMod2.ui.tips.EventDetailTip;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.LinkedList;

public class MultiHitboxEventLabel {
    private LinkedList<HoverableEventLabel> labels = new LinkedList<>();
    private final EventDetailTip tip;

    public MultiHitboxEventLabel(EventDetail detail) {
        this.tip = new EventDetailTip(detail);
    }

    public void add(HoverableEventLabel label) {
        labels.add(label);
    }

    public boolean anyHovered() {
        for (HoverableEventLabel label : labels) {
            if (label.hb.hovered)
                return true;
        }

        return false;
    }

    public void renderHover(SpriteBatch sb) {
        if (anyHovered())
            tip.render(sb);
    }
}
