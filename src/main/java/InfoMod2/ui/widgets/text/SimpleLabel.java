package InfoMod2.ui.widgets.text;

import InfoMod2.ui.widgets.AbstractWidget;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;

public class SimpleLabel extends AbstractWidget<SimpleLabel> {
    protected String text;
    protected BitmapFont font;
    protected float textWidth, textHeight;
    protected Color textColor;

    public SimpleLabel(String text, Color textColor) {
        this(text, textColor, FontHelper.tipBodyFont);
    }

    public SimpleLabel(String text, Color textColor, BitmapFont font) {
        this.font = font;
        this.textColor = textColor;

        setText(text);
    }

    public void setText(String text) {
        this.text = text;

        // These are in resolution scaled coordinates already (NOT necessarily in our 1080p basis)
        this.textWidth = FontHelper.getWidth(font, text, 1.0f);
        this.textHeight = FontHelper.getHeight(font, text, 1.0f);
    }

    @Override public float getPreferredContentWidth() { return textWidth / Settings.scale; }
    @Override public float getPreferredContentHeight() { return textHeight / Settings.scale; }

    @Override
    public void render(SpriteBatch sb) {
        FontHelper.renderFontLeftDownAligned(sb, font, text, getContentLeft() * Settings.scale, getContentBottom() * Settings.scale, textColor);
    }
}
