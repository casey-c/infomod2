package InfoMod2.ui.widgets.text;

import InfoMod2.ui.widgets.AbstractWidget;
import InfoMod2.utils.graphics.ExtraFonts;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;

public class SmartLabel extends AbstractWidget<SmartLabel> {
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

    public SmartLabel(String text) {
        this(text,
                FontHelper.tipBodyFont,
                Settings.CREAM_COLOR,
                10000.0f,
                32.0f);
    }

    public SmartLabel(String text, BitmapFont font, Color fontColor) {
        this(text,
                font,
                fontColor,
                10000.0f,
                32.0f);
    }

    public SmartLabel(String text, Color fontColor) {
        this(text,
                FontHelper.tipBodyFont,
                fontColor,
                10000.0f,
                32.0f);
    }

    public SmartLabel(String text, BitmapFont font, Color fontColor, float lineWidth, float lineSpacing) {
        this.font = font;
        this.fontColor = fontColor;

        this.scaledLineWidth = lineWidth * Settings.xScale;
        this.scaledLineSpacing = lineSpacing * Settings.yScale;

        setText(text);
    }

    // Also updates the label's preferred width/height
    public void setText(String text) {
        this.text = text;

        ExtraFonts.SizeHelper s = ExtraFonts.computeSmartSize(text, font, scaledLineWidth, scaledLineSpacing);
        this.textBlockWidth = s.blockWidth;
        this.textBlockHeight = s.blockHeight;
    }

    public void setFontColor(Color newColor) {
        this.fontColor = newColor;
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
