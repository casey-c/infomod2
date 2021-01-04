package InfoMod2.utils;

import InfoMod2.ui.screens.IScreen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// TODO: not really super generic, but good enough for simple non-hierarchical screens
//
// Can call openCustomScreen() when you wish to open a screen - this will halt all other updates in the game due to the
// ScreenPatches in place
public class ScreenHelper {
    private static boolean screenUp;
    private static IScreen currentScreen;

    public static boolean isScreenUp() {
        return screenUp;
    }

    public static void openCustomScreen(IScreen screen) {
        if (!screenUp)
            SoundHelper.screenOpenSound();

        screenUp = true;
        currentScreen = screen;
    }

    public static void closeAllCustomScreens() {
        if (screenUp)
            SoundHelper.screenCloseSound();

        screenUp = false;
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
