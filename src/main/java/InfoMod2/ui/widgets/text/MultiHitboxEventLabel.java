package InfoMod2.ui.widgets.text;

import java.util.LinkedList;

public class MultiHitboxEventLabel {
    private LinkedList<HoverableEventLabel> labels = new LinkedList<>();

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
}
