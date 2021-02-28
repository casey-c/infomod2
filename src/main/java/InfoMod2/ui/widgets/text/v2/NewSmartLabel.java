package InfoMod2.ui.widgets.text.v2;

import InfoMod2.ui.widgets.AbstractWidget;
import InfoMod2.utils.graphics.ExtraFonts;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;

public class NewSmartLabel extends AbstractWidget<NewSmartLabel> {
    protected String text;
    protected BitmapFont font;
    protected Color fontColor;

    // These are stored in screen space (already scaled!)
    protected float scaledLineWidth;
    protected float scaledLineSpacing;

    // ... although these are both in 1080p space - so need scaling after using
    protected float textBlockWidth;
    protected float textBlockHeight;

    // --------------------------------------------------------------------------------

    public NewSmartLabel(String text) {
        this(text,
                FontHelper.tipBodyFont,
                Settings.CREAM_COLOR,
//                10000.0f,
//                32.0f);
        500.0f,
                32.0f);
    }

    public NewSmartLabel(String text, BitmapFont font, Color fontColor, float lineWidth, float lineSpacing) {
        this.font = font;
        this.fontColor = fontColor;

        this.scaledLineWidth = lineWidth * Settings.xScale;
        this.scaledLineSpacing = lineSpacing * Settings.yScale;

        setText(text);
    }

    // Also updates the label's preferred width/height
    public void setText(String text) {
        this.text = text;
        computeSmartSize();
    }

    // --------------------------------------------------------------------------------

    // A slight fork of the original base game FontHelper.getSmartWidth() / Height()
    // functions - this includes better scaling considerations and computing the width
    // and height of the entire text block.
    //
    // NOTE: These computations occur in screen space (i.e. after scaling) as the layout
    // helpers are already scaled and it's easier that way

    private void computeSmartSize() {
        System.out.println("************ System information ****************");
        System.out.println("Settings.scale: " + Settings.scale);
        System.out.println();
        System.out.println("Settings.xScale: " + Settings.xScale);
        System.out.println("Settings.yScale: " + Settings.yScale);
        System.out.println();
        System.out.println("Settings.WIDTH: " + Settings.WIDTH);
        System.out.println("Settings.HEIGHT: " + Settings.HEIGHT);
        System.out.println("************************************************");


        System.out.println("---------------------");
        System.out.println("Computing smart size for: " + text);

        float currWidth = 0.0f;

        GlyphLayout layout = FontHelper.layout;

        // Figure out how wide a " " space character is between words
        layout.setText(font, " ");
        float spaceWidth = layout.width;

        // Figure out how tall the font is usually
        // NOTE: this string was taken from base game and I'm not sure how robust it is
        layout.setText(font, "gl0!");
        float lineHeight = layout.height;


        // DEBUG-----------------------------
        System.out.println("The spaceWidth is: " + spaceWidth);
        System.out.println("The lineHeight of gl0! is: " + lineHeight);

        System.out.println();

        System.out.println("Our desired lineWidth is: " + scaledLineWidth);
        System.out.println("Our desired lineSpacing is: " + scaledLineSpacing);
        System.out.println("---------------------");
        // ----------------------------------


        float blockWidth = 0.0f;
        float blockHeight = lineHeight; // TODO: verify this does what I think it did (i.e. a fix for single line labels)

        System.out.println("\nNow time to figure out the word locations\n");

        for (String word : text.split(" ")) {
            System.out.println("Looking at word: " + word);
            System.out.println("Current width is " + currWidth);
            System.out.println("Current blockWidth is " + blockWidth);
            System.out.println("Current blockHeight is " + blockHeight);
            System.out.println();

            if (word.equals("NL")) {
                blockWidth = Math.max(currWidth, blockWidth);
                currWidth = 0.0f;

                blockHeight += scaledLineSpacing;
            }
            else if (word.equals("TAB")){
                currWidth += (spaceWidth * 5.0f);
            }
            else {
                // Strip out leading color info, e.g. #rRed -> Red
                if (ExtraFonts.wordStartsWithPoundColor(word))
                    word = word.substring(2);

                layout.setText(font, word);

                if (currWidth + layout.width > scaledLineWidth) {
                    currWidth = layout.width + spaceWidth;
                    blockWidth = Math.max(currWidth, blockWidth);

                    blockHeight += scaledLineSpacing;

                    System.out.println("TOO WIDE - made new line");
                }
                else {
                    currWidth += (layout.width + spaceWidth);
                    blockWidth = Math.max(currWidth, blockWidth);

                    System.out.println("Fits on existing line");
                }

                // DEBUG
                System.out.println("Current width is " + currWidth);
                System.out.println("Current blockWidth is " + blockWidth);
                System.out.println("Current blockHeight is " + blockHeight);
                System.out.println();
            }
        }

        System.out.println("DONE...");
        System.out.println("Final blockWidth is " + blockWidth);
        System.out.println("Final blockHeight is " + blockHeight);

        this.textBlockHeight = blockHeight / Settings.yScale;
        this.textBlockWidth = blockWidth / Settings.xScale;
    }

    // --------------------------------------------------------------------------------

    @Override public float getPreferredContentWidth() { return textBlockWidth; }
    @Override public float getPreferredContentHeight() { return textBlockHeight; }

    @Override
    public void render(SpriteBatch sb) {
        // NOTE: lineWidth and spacing were already scaled when stored!
        FontHelper.renderSmartText(sb,
                font,
                text,
                getContentLeft() * Settings.xScale,
                getContentTop() * Settings.yScale,
                scaledLineWidth,
                scaledLineSpacing,
                fontColor);
    }
}
