package InfoMod2.ui.widgets.text;

import InfoMod2.data.EventDetail;
import InfoMod2.ui.tips.EventDetailTip;
import InfoMod2.utils.graphics.ExtraColors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;
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

    public boolean computeActive(HashMap<String, Integer> seenEvents) {
        boolean isActive = tip.computeActive(seenEvents);
        System.out.println("Result from tip.computeActive: " + isActive);

        if (isActive) {
            for (HoverableEventLabel label : labels) {
                label.setTextColor(ExtraColors.SCREEN_ACTIVE_EVENT, ExtraColors.SCREEN_ACTIVE_EVENT_HIGHLIGHT);
            }
        }
        else {
            for (HoverableEventLabel label : labels) {
                label.setTextColor(ExtraColors.SCREEN_INACTIVE_EVENT_WRONG_ACT, ExtraColors.SCREEN_INACTIVE_EVENT_HIGHLIGHT);
            }
        }

        return isActive;
    }
}
