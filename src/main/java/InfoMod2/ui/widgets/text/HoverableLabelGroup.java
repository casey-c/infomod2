package InfoMod2.ui.widgets.text;

import InfoMod2.data.EventDetail;
import InfoMod2.ui.widgets.AbstractWidget;
import InfoMod2.ui.widgets.AnchorPosition;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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

    private static final float GAP_BETWEEN_WORDS_OF_SAME_LABEL = 4.0f; // spacing between words in the same label on the X axis
    private static final float GAP_BETWEEN_LABELS = 4.0f; // additional spacing between labels on the X axis

    // Call after anchoredAt()

    private static class WordHelper {
        private int wordID;
        private EventDetail detail;
        private MultiHitboxEventLabel connected;

        private String word;
        private float width;

        private WordHelper(int wordID, EventDetail detail, MultiHitboxEventLabel connected, String word) {
            this.wordID = wordID;

            this.detail = detail;
            this.connected = connected;

            this.word = word;
            this.width = HoverableEventLabel.getPredictedWidth(word);
        }
    }

    public HoverableLabelGroup withItems(Collection<EventDetail> details) {

//        System.out.println("-------------------------------");
//        System.out.println("Starting x: " + currX);
//        System.out.println("Starting y: " + currY);
//        System.out.println("Max width: " + maxWidth);
//        System.out.println("-------------------------------");


        // TODO: need to make hitboxes for the spaces between words in a label (it's weird to go in the center and "miss")
        // Construct all the hoverable labels from the events
        int totalLabels = details.size();
        int currIndex = 0;

        LinkedList<WordHelper> words = new LinkedList<>();

        // Preprocess
        System.out.println("----------------");
        System.out.println("Preprocessing...");
        int id = 0;
        for (EventDetail detail : details) {
            String full = detail.getName();
            if (currIndex++ != totalLabels - 1) {
                full = full + ",";
            }

            System.out.println("Full detail: " + full + " | id: " + id);

            MultiHitboxEventLabel connected = new MultiHitboxEventLabel();
            for (String word : full.split(" ")) {
                System.out.println("Adding word " + word);
                words.add(new WordHelper(id, detail, connected, word));
            }

            id++;
        }
        System.out.println("=====================");


        // Greedily put the words into lines

        LinkedList<LinkedList<WordHelper>> lines = new LinkedList<>();
        LinkedList<WordHelper> currLine = new LinkedList<>();
        LinkedList<Float> lineWidths = new LinkedList<>();
        lines.add(currLine);

        float currLineWidth = 0.0f;
        int lastID = 0;

        for (WordHelper wordHelper : words) {
            System.out.println("Trying to add word " + wordHelper.word + " with width " + wordHelper.width);
            System.out.println("Current line width: " + currLineWidth);
            System.out.println("Last id: " + lastID + " | word id: " + wordHelper.wordID);

            float spacing = 0.0f;
            if (!currLine.isEmpty()) {
                spacing += GAP_BETWEEN_WORDS_OF_SAME_LABEL;

                if (lastID != wordHelper.wordID)
                    spacing += GAP_BETWEEN_LABELS;
            }

            System.out.println("Spacing: " + spacing);

            System.out.println("The result of (clw + spacing + ww) = " + (currLineWidth + spacing + wordHelper.width) + " [max width is " + maxWidth + "]");
            if (currLineWidth + spacing + wordHelper.width > maxWidth) {
                System.out.println("Need to start a new line");
                // Need to start a new line
                lineWidths.add(currLineWidth);

                currLine = new LinkedList<>();
                lines.add(currLine);

                currLine.add(wordHelper);
                currLineWidth = wordHelper.width;
            }
            else {
                System.out.println("Fits on this line");
                // Fits on this line
                currLine.add(wordHelper);
                currLineWidth += spacing + wordHelper.width;
            }
        }

        lineWidths.add(currLineWidth);

        // DEBUG
        System.out.println("There are " + lines.size() + " lines");
        for (LinkedList<WordHelper> line : lines) {
            System.out.println("\tLine with size " + line.size());
        }

        System.out.println("There are " + lineWidths.size() + " lineWidths");
        for (float lineWidth : lineWidths) {
            System.out.println("\tLine with width " + lineWidth);
        }

        System.out.println("The words are: ");
        for (LinkedList<WordHelper> line : lines) {
            for (WordHelper word : line) {
                System.out.print(word.word + "    ");
            }
            System.out.println();
        }

        System.out.println("Combining step: ");
        // Combine all the words with the same ID on a line into one final label
        LinkedList<LinkedList<WordHelper>> finalCombinedWordLines = new LinkedList<>();

        for (LinkedList<WordHelper> line : lines) {
            System.out.println("Line--------");
            int currID = -1;

            LinkedList<WordHelper> finalCombinedWords = new LinkedList<>();
            finalCombinedWordLines.add(finalCombinedWords);

            LinkedList<String> wordsToCombine = new LinkedList<>();

            EventDetail currDetail = null;
            MultiHitboxEventLabel currConnected = null;

            for (WordHelper wordHelper : line) {
                if (currID == -1) {
                    currID = wordHelper.wordID;
                }

                System.out.println("\tThe word we're looking at is: '" + wordHelper.word + "' with ID " + wordHelper.wordID);
                // Can add this to the existing word we're building
                if (wordHelper.wordID == currID) {
                    wordsToCombine.add(wordHelper.word);
                    currDetail = wordHelper.detail;
                    currConnected = wordHelper.connected;
                }
                // Time to start a new word
                else {
                    // Finish the previous word
                    String finalCombined = String.join(" ", wordsToCombine);
                    System.out.println("Finished a word: " + finalCombined);
                    finalCombinedWords.add(new WordHelper(currID, currDetail, currConnected, finalCombined));

                    // Start the next one
                    wordsToCombine.clear();
                    wordsToCombine.add(wordHelper.word);
                    currDetail = wordHelper.detail;
                    currConnected = wordHelper.connected;
                    currID = wordHelper.wordID;
                }
            }

            // Don't forget the final word on the line
            String finalCombined = String.join(" ", wordsToCombine);
            System.out.println("Finished a word: " + finalCombined);
            finalCombinedWords.add(new WordHelper(currID, currDetail, currConnected, finalCombined));
        }

        // Debug final output
        System.out.println("Total lines: " + finalCombinedWordLines.size());
        for (LinkedList<WordHelper> line : finalCombinedWordLines) {
            System.out.println("Words on line: " + line.size());
        }

        System.out.println("Final results: ");
        for (LinkedList<WordHelper> line : finalCombinedWordLines) {
            for (WordHelper wh : line) {
                System.out.print(wh.word + " [" + wh.wordID + "] ");
            }
            System.out.println();
        }


            /*
            // originally inside for (detail)
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
             */

        // Make the actual labels
        float currX = getContentLeft();
        float currY = getContentTop();

        for (LinkedList<WordHelper> line : finalCombinedWordLines) {
            currX = getContentLeft();

            int prevID = -1;

            for (WordHelper wh : line) {
//                if (prevID == -1)
//                    prevID = wh.wordID;

                HoverableEventLabel label = new HoverableEventLabel(wh.word, wh.detail, wh.connected);

                label.anchoredAt(currX, currY, AnchorPosition.LEFT_TOP);
                labels.add(label);

                // Update the layout for the next word
                currX += label.getPreferredContentWidth() + GAP_BETWEEN_WORDS_OF_SAME_LABEL;

                if (wh.wordID != prevID)
                    currX += GAP_BETWEEN_LABELS;

                prevID = wh.wordID;
            }

            currY -= ROW_HEIGHT;
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
