package InfoMod2.ui;

import InfoMod2.utils.DynamicTextureBox;
import InfoMod2.utils.ExtraColors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;

public class ThiccToolTip {
    private DynamicTextureBox box = new DynamicTextureBox("InfoMod2/tooltip/")
            .withColors(ExtraColors.TOOL_TIP_OUTER_BEVEL, ExtraColors.TOOL_TIP_INNER_BEVEL, ExtraColors.TOOL_TIP_BASE);

    protected float left, bottom, width, height;

    public ThiccToolTip(float left, float width, float height) {
        this.left = left;
        this.width = width;
        this.height = height;

        // TODO: compute
        //this.bottom = (float) Settings.HEIGHT - 89.0f * Settings.scale - height * Settings.scale;
        this.bottom = 1080.0f - 89.0f - height;
        //this.bottom = bottom;
    }

    protected void renderBackground(SpriteBatch sb) {
        //box.render(sb, 1383, 691, 400, 300);
        box.render(sb, left, bottom, width, height);
    }

    protected void renderForeground(SpriteBatch sb) { }

    public void render(SpriteBatch sb) {
        renderBackground(sb);
        renderForeground(sb);
    }
}
