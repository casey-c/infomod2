package InfoMod2.ui.widgets.text;

import InfoMod2.data.EventDetail;
import InfoMod2.ui.widgets.AbstractWidget;
import InfoMod2.ui.widgets.AnchorPosition;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

public class HoverableLabelGroup extends AbstractWidget<HoverableLabelGroup> {
    private float maxWidth;
    private float labelHeight;
    private LinkedList<HoverableEventLabel> labels = new LinkedList<>();

    public HoverableLabelGroup(float maxWidth) {
        this.maxWidth = maxWidth;
    }

    // TODO: probably should not make these hardcoded but experimental, oh well
    private static final float ROW_HEIGHT = 40.0f; // spacing between labels on the Y axis (includes font height)

    private static final float INTERNAL_SPACING = 4.0f; // spacing between words in the same label on the X axis
    private static final float HORIZONTAL_SPACING = 4.0f; // additional spacing between labels on the X axis

    // Call after anchoredAt()
    public HoverableLabelGroup withItems(Collection<EventDetail> details) {
        float currX = getContentLeft();
        float currY = getContentTop();

        System.out.println("-------------------------------");
        System.out.println("Starting x: " + currX);
        System.out.println("Starting y: " + currY);
        System.out.println("Max width: " + maxWidth);
        System.out.println("-------------------------------");


        // TODO: need to make hitboxes for the spaces between words in a label (it's weird to go in the center and "miss")
        // Construct all the hoverable labels from the events
        int totalLabels = details.size();
        int currIndex = 0;

        for (EventDetail detail : details) {
            String full = detail.getName();
            if (currIndex++ != totalLabels - 1) {
                full = full + ",";
            }

            // too tired right now to implement RIP :(
            // TODO: should probably make a greedy algorithm instead
            //   1. split full into words
            //   2. take as many words as will fit in a line, recombine that group and make it its own label
            //   3. repeat 2 with the remaining words until no words are left
//            String[] words = full.split(" ");
//
//            LinkedList<String> remaining = new LinkedList<>();
//            remaining.addAll(Arrays.asList(words));
//
//            MultiHitboxEventLabel connected = new MultiHitboxEventLabel();
//            while (true) {
//                String word = remaining.peek();
//                HoverableEventLabel label = new HoverableEventLabel(word, detail, connected);
//            }


            MultiHitboxEventLabel connected = new MultiHitboxEventLabel();

            for (String word : full.split(" ")) {
                HoverableEventLabel label = new HoverableEventLabel(word, detail, connected);
                float labelWidth = label.getPreferredContentWidth();
                System.out.println("Adding word " + word + " with width " + labelWidth);

                // Start a new row
                if (currX + labelWidth - INTERNAL_SPACING > getContentLeft() + maxWidth) {
                    currX = getContentLeft();
                    currY -= ROW_HEIGHT;
                }

                // Set the label position and remember it
                label.anchoredAt(currX, currY, AnchorPosition.LEFT_TOP);
                labels.add(label);

                // Update the position for the next label
                currX += labelWidth + INTERNAL_SPACING;
            }

            currX += HORIZONTAL_SPACING;
        }

        // Remember the height of the labels
        this.labelHeight = getContentTop() - currY;

        return this;
    }

    @Override
    public float getPreferredContentWidth() {
        return maxWidth;
    }

    // TODO: could be subtle bugs, as we don't set labelHeight to not be zero until AFTER the anchoredAt(), which uses
    //   the 0 as a "preferred height". this is probably ok for this use, but definitely a code smell.
    @Override
    public float getPreferredContentHeight() {
        return labelHeight;
    }

    @Override
    public void render(SpriteBatch sb) {
        for (HoverableEventLabel label : labels)
            label.render(sb);
    }

    @Override
    public void show() {
        for (HoverableEventLabel label : labels)
            label.show();
    }

    @Override
    public void hide() {
        for (HoverableEventLabel label : labels)
            label.hide();
    }

    @Override
    public void update() {
        for (HoverableEventLabel label : labels)
            label.update();
    }
}
