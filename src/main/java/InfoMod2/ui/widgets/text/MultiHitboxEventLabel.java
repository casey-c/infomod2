package InfoMod2.ui.widgets.text;

import InfoMod2.data.EventDetail;
import InfoMod2.ui.tips.EventDetailTip;
import InfoMod2.utils.graphics.color.ColorManager;
import com.badlogic.gdx.graphics.Color;
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

        // TODO: account for inactive in current act / not in current act as originally intended
        Color base = isActive ?
                ColorManager.EVENT_SCREEN_ACTIVE_EVENT() : ColorManager.EVENT_SCREEN_INACTIVE_EVENT();
        Color highlight = isActive ?
                ColorManager.EVENT_SCREEN_ACTIVE_EVENT_HIGHLIGHT() : ColorManager.EVENT_SCREEN_INACTIVE_EVENT_HIGHLIGHT();

        for (HoverableEventLabel label : labels)
            label.setTextColor(base, highlight);

        return isActive;
    }
}
