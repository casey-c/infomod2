package InfoMod2.utils.graphics.color;

import com.badlogic.gdx.graphics.Color;

public class VibrantColorScheme extends ColorScheme {
    public VibrantColorScheme() {
        this.EVENT_SCREEN_ACTIVE_EVENT = new Color(0.933f, 0.749f, 0.420f, 1.000f);
        this.EVENT_SCREEN_INACTIVE_EVENT = new Color(0.318f, 0.333f, 0.345f, 1.000f);

        this.EVENT_SCREEN_ACTIVE_EVENT_HIGHLIGHT = new Color(0.973f, 0.902f, 0.773f, 1.000f);

        this.EVENT_SCREEN_ACT_SECTION_TITLE = new Color(0.745f, 0.745f, 0.745f, 1.000f);
    }
}
