package InfoMod2.utils.graphics;

import InfoMod2.ui.screens.IScreen;
import InfoMod2.ui.widgets.AbstractWidget;
import InfoMod2.utils.SoundHelper;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// TODO: not really super generic, but good enough for simple non-hierarchical screens
//
// Can call openCustomScreen() when you wish to open a screen - this will halt all other updates in the game due to the
// ScreenPatches in place
public class ScreenHelper {
    private static boolean screenUp;
    private static AbstractWidget currentScreen;

    public static boolean isScreenUp() {
        return screenUp;
    }

    public static void openCustomScreen(AbstractWidget screen) {
        // TODO: smarter logic to close existing screens / allow multiple up at once? idk - something better than this
        if (screenUp) currentScreen.hide();
        else SoundHelper.screenOpenSound();

        screenUp = true;
        currentScreen = screen;
        currentScreen.show();
    }

    public static void closeAllCustomScreens() {
        if (screenUp) {
            SoundHelper.screenCloseSound();
            currentScreen.hide();
            currentScreen = null;

            screenUp = false;
        }
    }

    public static void update() {
        if (currentScreen != null) {
            currentScreen.update();
        }
    }

    public static void render(SpriteBatch sb) {
        if (currentScreen != null) {
            currentScreen.render(sb);
        }
    }
}
