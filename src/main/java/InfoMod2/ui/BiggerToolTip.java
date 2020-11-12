package InfoMod2.ui;

import InfoMod2.utils.DynamicTextureBox;
import InfoMod2.utils.ExtraColors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;

public class BiggerToolTip<T extends BiggerToolTip<T>> {
    private DynamicTextureBox box = new DynamicTextureBox("InfoMod2/tooltip/").withColors(ExtraColors.TOOL_TIP_OUTER_BEVEL, ExtraColors.TOOL_TIP_INNER_BEVEL, ExtraColors.TOOL_TIP_BASE);
    private final int bevelSize = 10;
    private final int margins = 10;

    private float left, bottom, width, height;

    public BiggerToolTip(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public T anchoredAt(float left, float bottom) {
        this.left = left;
        this.bottom = bottom;
        return (T)this;
    }

    public T anchoredAtTop(float left) {
        this.left = left;
        this.bottom = (float) Settings.HEIGHT - 89.0f * Settings.scale - height * Settings.scale;
        return (T)this;
    }

    // --------------------------------------------------------------------------------

    protected float getContentWidth() { return width - 2 * (bevelSize + margins); }
    protected float getContentHeight() { return height - 2 * (bevelSize + margins); }

    protected float getContentLeft() { return left + bevelSize + margins; }
    protected float getContentRight() { return getContentLeft() + getContentWidth(); }

    protected float getContentBottom() { return bottom + bevelSize + margins; }
    protected float getContentTop() { return getContentBottom() + getContentHeight(); }

    protected float getContentCenterX() { return getContentLeft() + 0.5f * getContentWidth(); }
    protected float getContentCenterY() { return getContentBottom() + 0.5f * getContentHeight(); }

    // --------------------------------------------------------------------------------

    protected void renderBackground(SpriteBatch sb) { box.render(sb, left, bottom, width, height); }
    protected void renderForeground(SpriteBatch sb) { }

    public void render(SpriteBatch sb) {
        renderBackground(sb);
        renderForeground(sb);
    }
}
