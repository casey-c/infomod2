package InfoMod2.ui.widgets.text;

import InfoMod2.data.EventDetail;
import InfoMod2.ui.widgets.AbstractWidget;
import InfoMod2.ui.widgets.AnchorPosition;
import InfoMod2.utils.ExtraColors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.helpers.ImageMaster;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class HoverableLabelGroup extends AbstractWidget<HoverableLabelGroup> {
    private final float maxWidth;
    private float labelHeight;
    private final LinkedList<HoverableEventLabel> labels = new LinkedList<>();
    private final Set<MultiHitboxEventLabel> labelConnections = new HashSet<>();

    // TODO: probably should not make these hardcoded but based on the font instead.
    private static final float ROW_HEIGHT = 40.0f; // spacing between labels on the Y axis (includes font height)

    private static final float GAP_BETWEEN_WORDS_OF_SAME_LABEL = 4.0f; // spacing between words in the same label on the X axis
    private static final float GAP_BETWEEN_LABELS = 4.0f; // additional spacing between labels on the X axis

    // --------------------------------------------------------------------------------

    public HoverableLabelGroup(float maxWidth) {
        this.maxWidth = maxWidth;
    }

    // --------------------------------------------------------------------------------
    // Layout algorithm
    // --------------------------------------------------------------------------------

    // A simple helper struct for building the layout correctly
    private static class WordHelper {
        private final int wordID;
        private final EventDetail detail;
        private final MultiHitboxEventLabel connected;

        private final String word;
        private final float width;

        private WordHelper(int wordID, EventDetail detail, MultiHitboxEventLabel connected, String word) {
            this.wordID = wordID;

            this.detail = detail;
            this.connected = connected;

            this.word = word;
            this.width = HoverableEventLabel.getPredictedWidth(word);
        }
    }

    // Takes the events, e.g.: "Big Fish", "The Cleric", ... and turns them into a list of just their words.
    // E.g.: output = ["Big", "Fish", "The", "Cleric", ...]
    // This output is in the form of the WordHelper struct, which essentially helps save the extra meta data lost from
    // splitting the words into many items, in order to recombine them later as the final UI label.
    private LinkedList<WordHelper> getAllWords(Collection<EventDetail> details) {
        int totalLabels = details.size();
        int currIndex = 0;

        LinkedList<WordHelper> words = new LinkedList<>();

        int id = 0;
        for (EventDetail detail : details) {
            String full = detail.getName();
            if (currIndex++ != totalLabels - 1) {
                full = full + ",";
            }

            MultiHitboxEventLabel connected = new MultiHitboxEventLabel(detail);
            labelConnections.add(connected);

            for (String word : full.split(" ")) {
                words.add(new WordHelper(id, detail, connected, word));
            }

            id++;
        }

        return words;
    }

    // Take the individual words (with metadata): ["Big", "Fish", "The", "Cleric", ...]
    // and puts them into lines no wider than the max width of the label group.
    // example output (where the second part of "The Cleric" won't fit on the first line):
    // [
    //   ["Big", "Fish", "The"]
    //   ["Cleric"]
    // ]
    private LinkedList<LinkedList<WordHelper>> getWordHelperLines(LinkedList<WordHelper> words) {
        LinkedList<LinkedList<WordHelper>> lines = new LinkedList<>();
        LinkedList<WordHelper> currLine = new LinkedList<>();
        lines.add(currLine);

        float currLineWidth = 0.0f;
        int lastID = 0;

        for (WordHelper wordHelper : words) {
            float spacing = 0.0f;
            if (!currLine.isEmpty()) {
                spacing += GAP_BETWEEN_WORDS_OF_SAME_LABEL;

                if (lastID != wordHelper.wordID)
                    spacing += GAP_BETWEEN_LABELS;
            }


            if (currLineWidth + spacing + wordHelper.width > maxWidth) {
                // Need to start a new line
                currLine = new LinkedList<>();
                lines.add(currLine);

                currLine.add(wordHelper);
                currLineWidth = wordHelper.width;
            }
            else {
                // Fits on this line
                currLine.add(wordHelper);
                currLineWidth += spacing + wordHelper.width;
            }
        }

        return lines;
    }

    // This step will recombine the split up words that lie on the same line into one combined string.
    // e.g. if the input was:
    // [
    //   ["Big", "Fish", "The"]
    //   ["Cleric"]
    // ]
    // Since "Big" and "Fish" originate from the same EventDetail object, we can combine them as:
    // [
    //   ["Big Fish", "The"]
    //   ["Cleric"]
    // ]
    private LinkedList<LinkedList<WordHelper>> combineWordsOfSameLabel(LinkedList<LinkedList<WordHelper>> lines) {
        LinkedList<LinkedList<WordHelper>> finalCombinedWordLines = new LinkedList<>();

        for (LinkedList<WordHelper> line : lines) {
            int currID = -1;

            LinkedList<WordHelper> finalCombinedWords = new LinkedList<>();
            finalCombinedWordLines.add(finalCombinedWords);

            LinkedList<String> wordsToCombine = new LinkedList<>();

            EventDetail currDetail = null;
            MultiHitboxEventLabel currConnected = null;

            for (WordHelper wordHelper : line) {
                if (currID == -1)
                    currID = wordHelper.wordID;

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
            finalCombinedWords.add(new WordHelper(currID, currDetail, currConnected, finalCombined));
        }

        return finalCombinedWordLines;
    }

    // The final step is to take our WordHelpers and make them into the actual HoverableEventLabel UI objects. This is
    // the reason why we've stored all the extra metadata in the WordHelper when doing all the preprocessing, since we
    // want the final labels to be linked together when they are the same event, even if they are two separate labels
    // with a line break in between.
    private void makeTheLabels(LinkedList<LinkedList<WordHelper>> finalCombinedWordLines) {
        float currX;
        float currY = getContentTop();

        for (LinkedList<WordHelper> line : finalCombinedWordLines) {
            currX = getContentLeft();

            int prevID = -1;

            for (WordHelper wh : line) {
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

        // A bit hacky, but we need to recompute the (x,y) bottom left corner position with the now known height
        // (the extra offset is basically arbitrary/experimental lol - i didn't do enough close inspection to figure out why it's needed)
        // TODO: do a closer inspection
        this.anchoredAt(getContentLeft(), currY + 12.0f, AnchorPosition.LEFT_BOTTOM);
    }

    // Call after anchoredAt()
    public HoverableLabelGroup withItems(Collection<EventDetail> details) {
        // Split all the events up into their individual words in the initial word helper form
        LinkedList<WordHelper> words = getAllWords(details);

        // Use the raw words to place them into lines (capped at maxWidth)
        LinkedList<LinkedList<WordHelper>> lines = getWordHelperLines(words);

        // Combine all the words resulting from the same event src into one potential label on each line
        LinkedList<LinkedList<WordHelper>> finalCombinedWordLines = combineWordsOfSameLabel(lines);

        // Make the actual labels
        makeTheLabels(finalCombinedWordLines);

        return this;
    }

    // --------------------------------------------------------------------------------

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

        // TODO: remove debug
//        sb.setColor(ExtraColors.DEBUG_COLOR);
//        sb.draw(ImageMaster.WHITE_SQUARE_IMG, getContentLeft(), getContentBottom(), getPreferredContentWidth(), getPreferredContentHeight());
    }

    public void renderHovers(SpriteBatch sb) {
        for (MultiHitboxEventLabel connection : labelConnections)
            connection.renderHover(sb);
//        for (HoverableEventLabel label : labels)
//            label.renderHover(sb);
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
