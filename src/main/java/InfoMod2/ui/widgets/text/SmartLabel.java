package InfoMod2.ui.widgets.text;

import InfoMod2.ui.widgets.AbstractWidget;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;

public class SmartLabel extends AbstractWidget<SmartLabel> {
    protected String text;
    protected BitmapFont font;
    protected float textWidth, textHeight;
    protected Color textColor;

    protected float lineWidth;
    protected float lineSpacing;

    public SmartLabel(String text, Color textColor) {
        this(text, textColor, FontHelper.tipBodyFont);
    }
    public SmartLabel(String text, Color textColor, BitmapFont font) { this(text, textColor, font, 100000.0f, 40.0f); }

    public SmartLabel(String text, Color textColor, BitmapFont font, float lineWidth, float lineSpacing) {
        this.font = font;
        this.textColor = textColor;

        this.lineWidth = lineWidth;
        this.lineSpacing = lineSpacing;

        setText(text);
    }

    public void setText(String text) {
        this.text = text;

        // These are in resolution scaled coordinates already (NOT necessarily in our 1080p basis)
        //this.textWidth = FontHelper.getSmartWidth(font, text, lineWidth, lineSpacing);

        // this doesn't work as i thought it did
        //this.textHeight = FontHelper.getSmartHeight(font, text, lineWidth, lineSpacing);

        //this.textWidth = FontHelper.getSmartWidth(font, text, lineWidth, lineSpacing);
        // NOTE: this isn't what i wanted either, as this is the width of the last row of the text, e.g. if the text was:
        //
        // Lorem ipsum sic
        // dolerat
        //
        // ... then the smart width is only of the last line (dolerat). i assume this is because the devs wanted a quick
        // way to append text to existing "smart" text. like, the original getSmartHeight gives a vertical offset from
        // the starting Y, and the getSmartWidth gives you the offset from the starting X.
        //
        // it turns out i've had a misunderstanding about this process for literal months. RIP

        // Fixed versions (because I want the width/height of the entire block, not the last line/offsets):
        this.textHeight = lineSpacing - (FontHelper.getSmartHeight(font, text, lineWidth, lineSpacing) / Settings.scale);
        float smartWidth = FontHelper.getSmartWidth(font, text, lineWidth, lineSpacing) / Settings.scale;

        // Check if at least one row went up to the line width
        this.textWidth = (textHeight > lineSpacing) ? lineWidth : smartWidth;



        System.out.println("Setting smart label text to: " + text);
        System.out.println("Text height is apparently: " + textHeight + "\n");
    }

    // Already in 1080p space
    @Override public float getPreferredContentWidth() { return textWidth; }
    @Override public float getPreferredContentHeight() { return textHeight; }

    @Override
    public void render(SpriteBatch sb) {
        FontHelper.renderSmartText(sb, font, text, getContentLeft(), getContentTop(), lineWidth, lineSpacing, textColor);
    }
}
