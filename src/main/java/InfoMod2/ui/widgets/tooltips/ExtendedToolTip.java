package InfoMod2.ui.widgets.tooltips;

import InfoMod2.ui.widgets.AbstractWidget;
import InfoMod2.utils.graphics.color.ColorManager;
import InfoMod2.utils.graphics.DynamicTextureBox;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class ExtendedToolTip<T extends ExtendedToolTip<T>> extends AbstractWidget<T> {

    private final DynamicTextureBox textureBox = new DynamicTextureBox("InfoMod2/dtb/mainToolTips.atlas")
            .withColors(ColorManager.MAIN_TOOLTIP_DTB_OUTER(), ColorManager.MAIN_TOOLTIP_DTB_INNER(), ColorManager.MAIN_TOOLTIP_DTB_BASE());

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
