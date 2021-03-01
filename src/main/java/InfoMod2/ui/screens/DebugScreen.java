package InfoMod2.ui.screens;

import InfoMod2.ui.widgets.AnchorPosition;
import InfoMod2.ui.widgets.text.SmartLabel;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class DebugScreen implements IScreen {
    private SmartLabel label;

    private static final Color DEBUG_COLOR = new Color(0.1f, 0.9f, 0.1f, 0.6f);
    private static final Color DEBUG_BLACK = new Color(0.0f, 0.0f, 0.0f, 0.3f);

    public DebugScreen() {
        label = new SmartLabel("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                FontHelper.tipBodyFont,
                Settings.CREAM_COLOR,
                300.0f,
                32.0f );

        label.anchoredAt(1350.0f, 800.0f, AnchorPosition.LEFT_TOP);
    }

    @Override public void update() { }
    @Override public void show() { }
    @Override public void hide() { }

    @Override
    public void render(SpriteBatch sb) {
        // Hide everything else
        sb.setColor(DEBUG_BLACK);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0, 0, Settings.WIDTH, Settings.HEIGHT);

        // Draw bounding area
        sb.setColor(DEBUG_COLOR);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG,
                label.getContentLeft() * Settings.xScale,
                label.getContentBottom() * Settings.yScale,
                label.getPreferredContentWidth() * Settings.xScale,
                label.getPreferredContentHeight() * Settings.yScale);

        // Print the text
        label.render(sb);
    }
}
