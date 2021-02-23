package InfoMod2.ui.widgets.text;

import InfoMod2.ui.widgets.AbstractWidget;
import InfoMod2.utils.graphics.ExtraFonts;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

        ExtraFonts.BetterBlockDetails size = ExtraFonts.getSmartSize(font, text, lineWidth, lineSpacing);
        this.textWidth = size.fullBlockWidth;
        this.textHeight = size.fullBlockHeight;
    }

    // Already in 1080p space
    @Override public float getPreferredContentWidth() { return textWidth; }
    @Override public float getPreferredContentHeight() { return textHeight; }

    @Override
    public void render(SpriteBatch sb) {
        FontHelper.renderSmartText(sb, font, text, getContentLeft(), getContentTop(), lineWidth, lineSpacing, textColor);
    }
}
