package InfoMod2.ui.widgets.tooltips;

import InfoMod2.ui.widgets.AbstractWidget;
import InfoMod2.utils.DynamicTextureBox;
import InfoMod2.utils.ExtraColors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class ExtendedToolTip<T extends ExtendedToolTip<T>> extends AbstractWidget<T> {

    private DynamicTextureBox textureBox = new DynamicTextureBox("InfoMod2/tooltip2/")
            .withColors(ExtraColors.TOOLTIP_OUTER, ExtraColors.TOOLTIP_INNER, ExtraColors.TOOLTIP_BASE);

    protected float prefWidth;
    protected float prefHeight;

    public ExtendedToolTip(float width, float height) {
        this.prefWidth = width;
        this.prefHeight = height;
    }

    @Override public float getPreferredContentWidth() { return prefWidth; }
    @Override public float getPreferredContentHeight() { return prefHeight; }

    public void renderBackground(SpriteBatch sb) {
        textureBox.render(sb, getContentLeft(), getContentBottom(), getPreferredContentWidth(), getPreferredContentHeight());
    }

    public abstract void renderForeground(SpriteBatch sb);

    @Override
    public void render(SpriteBatch sb) {
        renderBackground(sb);
        renderForeground(sb);
    }
}
