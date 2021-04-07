package InfoMod2.utils.graphics.color;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;

/***
 * Use this manager to use colors to ensure that colors are pulled from the current palette.
 */
public class ColorManager {
    // --------------------------------------------------------------------------------
    // Shared colors (scheme independent)
    // NOTE: static functions instead of direct static access only for consistency with
    //   color scheme dependent colors (this is probably a code smell)
    // --------------------------------------------------------------------------------

    public static Color CREAM() { return Settings.CREAM_COLOR; }

    private static final Color LIGHT_GRAY = new Color(0.494f, 0.494f, 0.494f, 1.000f);
    public static Color LIGHT_GRAY() { return LIGHT_GRAY; }

    private static final Color DARK_GRAY = new Color(0.320f, 0.320f, 0.320f, 1.000f);
    public static Color DARK_GRAY() { return DARK_GRAY; }

    //private static final Color SCREEN_BACKGROUND_DIM = new Color(0.000f, 0.000f, 0.000f, 0.700f);
    private static final Color SCREEN_BACKGROUND_DIM = new Color(0.000f, 0.000f, 0.000f, 0.600f);
    public static Color SCREEN_BACKGROUND_DIM() { return SCREEN_BACKGROUND_DIM; }

    private static final Color SCREEN_OPACITY = new Color(1.000f, 1.000f, 1.000f, 0.950f);
    public static Color SCREEN_OPACITY() { return SCREEN_OPACITY; }

    private static final Color UI_DIVIDER = new Color(1.000f, 1.000f, 1.000f, 0.150f);
    public static Color UI_DIVIDER() { return UI_DIVIDER; }

    // Debug
    private static final Color DEBUG_0 = new Color(0.1f, 0.2f, 0.3f, 0.3f);
    public static Color DEBUG_0() { return DEBUG_0; }

    private static final Color DEBUG_1 = new Color(0.1f, 0.3f, 0.2f, 0.3f);
    public static Color DEBUG_1() { return DEBUG_1; }

    private static final Color DEBUG_2 = new Color(0.2f, 0.1f, 0.3f, 0.3f);
    public static Color DEBUG_2() { return DEBUG_2; }

    // --------------------------------------------------------------------------------
    // Primary color palettes
    // --------------------------------------------------------------------------------

    // Qualitative
    public static Color QUAL_RED() { return currentColorScheme.QUAL_RED; }
    public static Color QUAL_BLUE() { return currentColorScheme.QUAL_BLUE; }
    public static Color QUAL_GREEN() { return currentColorScheme.QUAL_GREEN; }

    public static Color QUAL_YELLOW() { return currentColorScheme.QUAL_YELLOW; }
    public static Color QUAL_PURPLE() { return currentColorScheme.QUAL_PURPLE; }
    public static Color QUAL_BEIGE() { return currentColorScheme.QUAL_BEIGE; }
    public static Color QUAL_TEAL() { return currentColorScheme.QUAL_TEAL; }

    // Sequential
    public static Color SEQ_0() { return currentColorScheme.SEQ_0; }
    public static Color SEQ_1() { return currentColorScheme.SEQ_1; }
    public static Color SEQ_2() { return currentColorScheme.SEQ_2; }
    public static Color SEQ_3() { return currentColorScheme.SEQ_3; }

    public static Color RARE() { return currentColorScheme.RARE; }
    public static Color UNCOMMON() { return currentColorScheme.UNCOMMON; }
    public static Color COMMON() { return currentColorScheme.COMMON; }

    // --------------------------------------------------------------------------------
    // UI specific colors
    // --------------------------------------------------------------------------------

    // Main tooltips
    public static Color MAIN_TOOLTIP_DTB_OUTER() { return currentColorScheme.MAIN_TOOLTIP_DTB_OUTER; }
    public static Color MAIN_TOOLTIP_DTB_INNER() { return currentColorScheme.MAIN_TOOLTIP_DTB_INNER; }
    public static Color MAIN_TOOLTIP_DTB_BASE() { return currentColorScheme.MAIN_TOOLTIP_DTB_BASE; }

    // Event screen
    public static Color EVENT_SCREEN_TITLE() { return currentColorScheme.EVENT_SCREEN_TITLE; }
    public static Color EVENT_SCREEN_SUBTITLE() { return currentColorScheme.EVENT_SCREEN_SUBTITLE; }

    public static Color EVENT_SCREEN_ACT_SECTION_TITLE() { return currentColorScheme.EVENT_SCREEN_ACT_SECTION_TITLE; }

    public static Color EVENT_SCREEN_ACTIVE_EVENT() { return currentColorScheme.EVENT_SCREEN_ACTIVE_EVENT; }
    public static Color EVENT_SCREEN_INACTIVE_EVENT() { return currentColorScheme.EVENT_SCREEN_INACTIVE_EVENT; }

    public static Color EVENT_SCREEN_ACTIVE_EVENT_HIGHLIGHT() { return currentColorScheme.EVENT_SCREEN_ACTIVE_EVENT_HIGHLIGHT; }
    public static Color EVENT_SCREEN_INACTIVE_EVENT_HIGHLIGHT() { return currentColorScheme.EVENT_SCREEN_INACTIVE_EVENT_HIGHLIGHT; }

    public static Color EVENT_SCREEN_PERCENTAGE_BOX_TEXT() { return currentColorScheme.EVENT_SCREEN_PERCENTAGE_BOX_TEXT; }

    // Event detail tips
    public static Color EVENT_DETAIL_TOOLTIP_DTB_BASE() { return currentColorScheme.EVENT_DETAIL_TOOLTIP_DTB_BASE; }
    public static Color EVENT_DETAIL_TOOLTIP_DTB_TRIM() { return currentColorScheme.EVENT_DETAIL_TOOLTIP_DTB_TRIM; }

    public static Color EVENT_DETAIL_TOOLTIP_TITLE() { return currentColorScheme.EVENT_DETAIL_TOOLTIP_TITLE; }
    public static Color EVENT_DETAIL_TOOLTIP_DESC() { return currentColorScheme.EVENT_DETAIL_TOOLTIP_DESC; }

    public static Color EVENT_DETAIL_TOOLTIP_REQ_FAILED() { return currentColorScheme.EVENT_DETAIL_TOOLTIP_REQ_FAILED; }
    public static Color EVENT_DETAIL_TOOLTIP_REQ_SUCCESS() { return currentColorScheme.EVENT_DETAIL_TOOLTIP_REQ_SUCCESS; }

    // --------------------------------------------------------------------------------

    public static Color rainbowColor() {
        float r = (MathUtils.cosDeg((float) (System.currentTimeMillis() / 10L % 360L)) + 1.25F) / 2.3F;
        float g = (MathUtils.cosDeg((float)((System.currentTimeMillis() + 1000L) / 10L % 360L)) + 1.25F) / 2.3F;
        float b = (MathUtils.cosDeg((float)((System.currentTimeMillis() + 2000L) / 10L % 360L)) + 1.25F) / 2.3F;
        return new Color(r, g, b, 1.0f);
    }

    // --------------------------------------------------------------------------------
    // The actual color scheme is defined all the way down here, in case the scheme wants to refer to the shared colors

    //private static final ColorScheme currentColorScheme = new ColorScheme();
    //private static final ColorScheme currentColorScheme = new MonochromaticColorScheme();
    private static final ColorScheme currentColorScheme = new VibrantColorScheme();

    // TODO: be able to set the current color scheme using the config menu, etc.
}
