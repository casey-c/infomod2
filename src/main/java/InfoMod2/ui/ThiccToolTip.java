package InfoMod2.ui;

import InfoMod2.utils.DynamicTextureBox;
import InfoMod2.utils.ExtraColors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ThiccToolTip {
    private DynamicTextureBox box = new DynamicTextureBox("InfoMod2/tooltip/")
            .withColors(ExtraColors.TOOL_TIP_OUTER_BEVEL, ExtraColors.TOOL_TIP_INNER_BEVEL, ExtraColors.TOOL_TIP_BASE);

    protected void renderBackground(SpriteBatch sb) {
        box.render(sb, 1383, 691, 400, 300);
    }

    protected void renderForeground(SpriteBatch sb) {

    }

    public void render(SpriteBatch sb) {
        renderBackground(sb);
        renderForeground(sb);
    }
}
