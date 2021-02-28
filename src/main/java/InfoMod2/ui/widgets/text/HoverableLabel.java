package InfoMod2.ui.widgets.text;

import InfoMod2.ui.widgets.AbstractWidget;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;

public class HoverableLabel extends AbstractWidget<HoverableLabel> {
    protected String text;

    protected Hitbox hb;
    protected float textWidth, textHeight;

    // TODO: customizable in constructor to make it more generic (currently not needed)
    protected static final BitmapFont font = FontHelper.tipBodyFont;
    protected static final Color fontColor = Settings.CREAM_COLOR;

    public HoverableLabel(String text) {
        setText(text);
    }

    public void setText(String text) {
        this.text = text;

        // These are in resolution scaled coordinates already (NOT necessarily in our 1080p basis)
        this.textWidth = FontHelper.getWidth(font, text, 1.0f);
        this.textHeight = FontHelper.getHeight(font, text, 1.0f);

        if (hb != null) {
            hb.width = textWidth;
            hb.height = textHeight;
        }
        else {
            hb = new Hitbox(textWidth, textHeight);
        }
    }

    // --------------------------------------------------------------------------------

    // Helper: returns the width that the label would take up in 1080p space
    public static float getPredictedWidth(String text) {
        return FontHelper.getWidth(font, text, 1.0f) / Settings.xScale;
    }

    // --------------------------------------------------------------------------------

    // Note that we are "fixing" the scaling back into our 1080p basis, since tW and tH are prescaled by FontHelper.
    @Override public float getPreferredContentWidth() { return textWidth / Settings.xScale; }
    @Override public float getPreferredContentHeight() { return textHeight / Settings.yScale; }

    // --------------------------------------------------------------------------------

    protected void renderText(SpriteBatch sb) {
        Color debugColor = hb.hovered ? Color.GREEN : fontColor;
        FontHelper.renderFontLeftDownAligned(sb, font, text, getContentLeft() * Settings.xScale, getContentBottom() * Settings.yScale, debugColor);
    }

    // Subclasses can override this for more useful behavior
    public void renderHover(SpriteBatch sb) { }

    @Override
    public void render(SpriteBatch sb) {
        renderText(sb);
        //renderHover(sb);
        hb.render(sb);
    }

    @Override public void update() { hb.update(); }

    // --------------------------------------------------------------------------------

    @Override public void show() { hb.move(getContentCenterX() * Settings.xScale, getContentCenterY() * Settings.yScale); }
    @Override public void hide() { hb.move(-10000.0f, -10000.0f); }
}
