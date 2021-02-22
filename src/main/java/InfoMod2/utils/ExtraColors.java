package InfoMod2.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;

public class ExtraColors {
    public static final Color TOOL_TIP_OUTER_BEVEL = new Color(0.333f, 0.427f, 0.443f, 1.0f);
    public static final Color TOOL_TIP_INNER_BEVEL = new Color(0.443f, 0.522f, 0.541f, 1.0f);
    public static final Color TOOL_TIP_BASE = new Color(0.133f, 0.165f, 0.169f, 1.0f);

    public static final Color TEXT_CREAM = new Color(0.957f, 0.929f, 0.835f, 1.0f);
    public static final Color TEXT_LIGHT_GREEN = new Color(0.804f, 0.925f, 0.698f, 1.0f);
    public static final Color TEXT_BLUE_GREEN = new Color(0.573f, 0.882f, 0.729f, 1.0f);
    public static final Color TEXT_LIGHT_BLUE = new Color(0.424f, 0.682f, 0.851f, 1.0f);

    // TODO
    public static final Color TEXT_BORDER_COLOR = new Color(0.0f, 0.0f, 0.0f, 0.2f);

    public static final Color DIVIDER_COLOR = new Color(1f, 1f, 1f, 0.15f);

    public static final Color SCREEN_DIM = new Color(0f, 0f, 0f, 0.8f);

    public static Color rainbowColor() {
        float r = (MathUtils.cosDeg((float) (System.currentTimeMillis() / 10L % 360L)) + 1.25F) / 2.3F;
        float g = (MathUtils.cosDeg((float)((System.currentTimeMillis() + 1000L) / 10L % 360L)) + 1.25F) / 2.3F;
        float b = (MathUtils.cosDeg((float)((System.currentTimeMillis() + 2000L) / 10L % 360L)) + 1.25F) / 2.3F;
        return new Color(r, g, b, 1.0f);
    }

    public static final Color DEBUG_COLOR = new Color(0.6f, 0.4f, 0.0f, 0.6f);
    public static final Color DEBUG_COLOR_2 = new Color(0.3f, 0.5f, 0.3f, 0.6f);

    public static final Color EVENT_TOOLTIP_BASE = new Color(0.118f, 0.125f, 0.145f, 1.0f);
    public static final Color EVENT_TOOLTIP_TRIM = new Color(0.267f, 0.275f, 0.361f, 1.0f);
    public static final Color EVENT_TOOLTIP_TITLE_TEXT = new Color(0.780f, 0.859f, 0.925f, 1.0f);

    public static final Color EVENT_TOOLTIP_REQ_FAILED = new Color(0.796f, 0.267f, 0.357f, 1.0f);
    public static final Color EVENT_TOOLTIP_REQ_SUCCESS = new Color(0.380f, 0.663f, 0.463f, 1.0f);

    //public static final Color EVENT_INACTIVE = new Color(0.502f, 0.502f, 0.502f, 1.0f);

    public static final Color EVENT_ACTIVE = new Color(0.659f, 0.667f, 0.627f, 1.0f);
    public static final Color EVENT_INACTIVE = new Color(0.329f, 0.188f, 0.216f, 1.0f);

    public static final Color TEXT_EVENT_DESC = new Color(0.431f, 0.455f, 0.471f, 1.0f);

    // --------------------------------------------------------------------------------
    // Event screen color scheme (WIP)
    // --------------------------------------------------------------------------------

    public static final Color EVENT_SCREEN_CARD_TITLE = new Color(0.667f, 0.522f, 0.173f, 1.0f);
    public static final Color EVENT_SCREEN_CARD_NUM = new Color(0.478f, 0.451f, 0.376f, 1.0f);

    public static final Color EVENT_SCREEN_CARD_ACTIVE = new Color(0.706f, 0.702f, 0.706f, 1.0f);
    public static final Color EVENT_SCREEN_CARD_ACTIVE_HOVER = new Color(0.231f, 0.420f, 0.235f, 1.0f);

    public static final Color EVENT_SCREEN_CARD_INACTIVE = new Color(0.243f, 0.251f, 0.282f, 1.0f);
    public static final Color EVENT_SCREEN_CARD_INACTIVE_HOVER = new Color(0.255f, 0.169f, 0.220f, 1.0f);

    // --------------------------------------------------------------------------------
    // Tool tips (v2)
    // --------------------------------------------------------------------------------
    public static final Color TOOLTIP_OUTER = new Color(0.318f, 0.345f, 0.365f, 1.000f);
    public static final Color TOOLTIP_INNER = new Color(0.376f, 0.455f, 0.514f, 1.000f);
    public static final Color TOOLTIP_BASE = new Color(0.129f, 0.153f, 0.169f, 1.000f);

    public static final Color TOOLTIP_TEXT_CREAM = new Color(0.945f, 0.914f, 0.824f, 1.000f);
    public static final Color TOOLTIP_TEXT_GRAY = new Color(0.494f, 0.494f, 0.494f, 1.000f);

    public static final Color TOOLTIP_TEXT_POTION_2 = new Color(0.694f, 0.843f, 0.765f, 1.000f);
    public static final Color TOOLTIP_TEXT_POTION_3 = new Color(0.627f, 0.812f, 0.812f, 1.000f);
    public static final Color TOOLTIP_TEXT_POTION_4 = new Color(0.529f, 0.682f, 0.753f, 1.000f);
    public static final Color TOOLTIP_TEXT_POTION_5 = new Color(0.435f, 0.545f, 0.714f, 1.000f);

    // Qualitative colors
    public static final Color QUAL_TEAL = new Color(0.545f, 0.855f, 0.808f, 1.000f);
    public static final Color QUAL_BLUE = new Color(0.055f, 0.667f, 0.824f, 1.000f);
    public static final Color QUAL_RED = new Color(0.824f, 0.345f, 0.251f, 1.000f);
    public static final Color QUAL_YELLOW = new Color(0.953f, 0.776f, 0.369f, 1.000f);
    public static final Color QUAL_GREEN = new Color(0.608f, 0.835f, 0.396f, 1.000f);
    public static final Color QUAL_PURPLE = new Color(0.890f, 0.502f, 0.890f, 1.000f);
}
