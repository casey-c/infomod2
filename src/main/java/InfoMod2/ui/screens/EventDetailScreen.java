package InfoMod2.ui.screens;

import InfoMod2.data.EventDatabase;
import InfoMod2.ui.widgets.cards.EventGroupCard;
import InfoMod2.utils.graphics.ExtraColors;
import InfoMod2.utils.graphics.ScreenHelper;
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
    private static final Texture TEX_BG = new Texture("InfoMod2/screens/screen_event.png");
    private static final Texture SCREEN_GLOW = new Texture("InfoMod2/screens/screen_glow.png");

    private EventGroupCard act1, act2, act3;

    private static final float ACT_CARD_SPACING = 32.0f;

    public EventDetailScreen() {
        // TODO: do something fancier to center it automatically?
        float left = 407.0f;
        float topAct1 = 811.0f;

        act1 = new EventGroupCard(left, topAct1, "Act I", EventDatabase.act1_events.values());

        float topAct2 = topAct1 - act1.getPreferredContentHeight() - ACT_CARD_SPACING;
        act2 = new EventGroupCard(left, topAct2, "Act II", EventDatabase.act2_events.values());

        float topAct3 = topAct2 - act2.getPreferredContentHeight() - ACT_CARD_SPACING;
        act3 = new EventGroupCard(left, topAct3, "Act III", EventDatabase.act3_events.values());
    }

    // --------------------------------------------------------------------------------

    private boolean rightClickStarted;

    @Override
    public void update() {
        // Update all child widgets
        act1.update();
        act2.update();
        act3.update();

        // Let this screen close itself
        if (InputHelper.isMouseDown_R) {
            rightClickStarted = true;
        } else if (rightClickStarted) {
            rightClickStarted = false;
            ScreenHelper.closeAllCustomScreens();
        }
    }

    // --------------------------------------------------------------------------------

    public void renderBackground(SpriteBatch sb) {
        // Dim background
        sb.setColor(ExtraColors.SCREEN_DIM_V2);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0, 0, Settings.WIDTH, Settings.HEIGHT);

        // Glow
        sb.setColor(Color.WHITE);
        sb.draw(SCREEN_GLOW,
                (Settings.WIDTH - (SCREEN_GLOW.getWidth() * Settings.scale)) * 0.5f,
                (Settings.HEIGHT - (SCREEN_GLOW.getHeight() * Settings.scale)) * 0.5f,
                SCREEN_GLOW.getWidth() * Settings.scale,
                SCREEN_GLOW.getHeight() * Settings.scale
        );

        // Main texture
        sb.setColor(Color.WHITE);
        sb.draw(TEX_BG,
                (Settings.WIDTH - (TEX_BG.getWidth() * Settings.scale)) * 0.5f,
                (Settings.HEIGHT - (TEX_BG.getHeight() * Settings.scale)) * 0.5f,
                TEX_BG.getWidth() * Settings.scale,
                TEX_BG.getHeight() * Settings.scale
        );
    }

    public void renderForeground(SpriteBatch sb) {
        FontHelper.renderFontLeftDownAligned(sb, FontHelper.tipBodyFont, "Events", 928.0f * Settings.scale, 885.0f * Settings.scale, ExtraColors.EVENT_SCREEN_CARD_ACTIVE);

        act1.render(sb);
        act2.render(sb);
        act3.render(sb);

        act1.renderHovers(sb);
        act2.renderHovers(sb);
        act3.renderHovers(sb);
    }

    @Override
    public void render(SpriteBatch sb) {
        renderBackground(sb);
        renderForeground(sb);
    }

    // --------------------------------------------------------------------------------

    @Override
    public void show() {
        // TODO: need to compute the extra label stuff, like which events are possible, etc.

        // Show all child widgets
        act1.show();
        act2.show();
        act3.show();
    }

    @Override
    public void hide() {
        // Hide all child widgets
        act1.hide();
        act2.hide();
        act3.hide();
    }
}
