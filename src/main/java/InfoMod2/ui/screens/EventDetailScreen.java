package InfoMod2.ui.screens;

import InfoMod2.ui.widgets.AnchorPosition;
import InfoMod2.ui.widgets.text.HoverableLabel;
import InfoMod2.utils.ExtraColors;
import InfoMod2.utils.ScreenHelper;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputHelper;

// TODO: eventually most of this will be extracted into a more generic TitledScreen, but this is the only screen
//   I plan on having for the initial release so I haven't bothered.
public class EventDetailScreen implements IScreen {
    private static final Texture TEX_BG = new Texture("InfoMod2/screens/events.png");

    // TODO: actual HoverableLabelList widget, layouts, etc. - not raw widgets on this screen (just testing for now)
    private HoverableLabel label;

    public EventDetailScreen() {
        label = new HoverableLabel("Hello World").anchoredAt(408f, 705f, AnchorPosition.LEFT_BOTTOM);
    }

    // --------------------------------------------------------------------------------

    private boolean rightClickStarted;
    @Override
    public void update() {
        // Update all child widgets
        label.update();

        // Let this screen close itself
        if (InputHelper.isMouseDown_R) {
            rightClickStarted = true;
        }
        else if (rightClickStarted) {
            rightClickStarted = false;
            ScreenHelper.closeAllCustomScreens();
        }
    }

    // --------------------------------------------------------------------------------

    public void renderBackground(SpriteBatch sb) {
        sb.setColor(ExtraColors.SCREEN_DIM);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0, 0, Settings.WIDTH, Settings.HEIGHT);

        sb.setColor(Color.WHITE);
        sb.draw(TEX_BG,
                (Settings.WIDTH - (TEX_BG.getWidth() * Settings.scale)) * 0.5f,
                (Settings.HEIGHT - (TEX_BG.getHeight() * Settings.scale)) * 0.5f,
                TEX_BG.getWidth() * Settings.scale,
                TEX_BG.getHeight() * Settings.scale
        );
    }

    public void renderForeground(SpriteBatch sb) {
        FontHelper.renderFontLeftDownAligned(sb, FontHelper.tipBodyFont, "Events", 928.0f * Settings.scale, 885.0f * Settings.scale, Settings.CREAM_COLOR);

        label.render(sb);
    }

    @Override
    public void render(SpriteBatch sb) {
        renderBackground(sb);
        renderForeground(sb);
    }

    // --------------------------------------------------------------------------------

    @Override public void show() {
        // Show all child widgets
        label.show();
    }
    @Override public void hide() {
        // Hide all child widgets
        label.hide();
    }
}