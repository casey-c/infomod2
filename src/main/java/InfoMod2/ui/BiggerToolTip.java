package InfoMod2.ui;

import InfoMod2.utils.DynamicTextureBox;
import InfoMod2.utils.ExtraColors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;

public class BiggerToolTip<T extends BiggerToolTip<T>> {
    private DynamicTextureBox box = new DynamicTextureBox("InfoMod2/tooltip/").withColors(ExtraColors.TOOL_TIP_OUTER_BEVEL, ExtraColors.TOOL_TIP_INNER_BEVEL, ExtraColors.TOOL_TIP_BASE);
    protected final int bevelSize = 10;
    protected final int margins = 10;

    protected float left, bottom, width, height;

    public BiggerToolTip(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public T anchoredAt(float left, float bottom) {
        this.left = left;
        this.bottom = bottom;
        return (T)this;
    }

    public T anchoredAtTopLeft(float left, float top) {
        this.left = left;
        this.bottom = top - height;
        return (T)this;
    }

    public T anchoredAtTop(float left) {
        this.left = left;
        this.bottom = (float) Settings.HEIGHT - 89.0f * Settings.scale - height * Settings.scale;
        return (T)this;
    }

    public void adjustBottomGivenNewHeight(float newHeight) {
        bottom = bottom - (height - newHeight);
        height = newHeight;
    }

    // --------------------------------------------------------------------------------

    public float getBottom() { return bottom; }
    public float getLeft() { return left; }
    public float getRight() { return left + width; }
    public float getTop() { return bottom + height; }

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

    // --------------------------------------------------------------------------------

    public void print() {
        System.out.println("BiggerToolTip: ");
        System.out.println("\tleft: " + getLeft());
        System.out.println("\tbottom: " + getBottom());
        System.out.println("\tright: " + getRight());
        System.out.println("\ttop: " + getTop());

        System.out.println("\tcontentleft: " + getContentLeft());
        System.out.println("\tcontentbottom: " + getContentBottom());
        System.out.println("\tcontentright: " + getContentRight());
        System.out.println("\tcontenttop: " + getContentTop());

        System.out.println("\twidth: " + width);
        System.out.println("\theight: " + height);
        System.out.println("\tcontentwidth: " + getContentWidth());
        System.out.println("\tcontentheight: " + getContentHeight());
    }

    @Override
    public String toString() {
        return "BiggerToolTip{" +
                "bevelSize=" + bevelSize +
                ", margins=" + margins +
                ", left=" + left +
                ", bottom=" + bottom +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
