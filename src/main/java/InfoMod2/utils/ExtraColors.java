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

    public static final Color DIVIDER_COLOR = new Color(1f, 1f, 1f, 0.2f);

    public static final Color SCREEN_DIM = new Color(0f, 0f, 0f, 0.8f);

    public static Color rainbowColor() {
        float r = (MathUtils.cosDeg((float) (System.currentTimeMillis() / 10L % 360L)) + 1.25F) / 2.3F;
        float g = (MathUtils.cosDeg((float)((System.currentTimeMillis() + 1000L) / 10L % 360L)) + 1.25F) / 2.3F;
        float b = (MathUtils.cosDeg((float)((System.currentTimeMillis() + 2000L) / 10L % 360L)) + 1.25F) / 2.3F;
        return new Color(r, g, b, 1.0f);
    }
}
