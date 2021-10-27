package InfoMod2.utils.graphics.color;

import com.badlogic.gdx.graphics.Color;

/***
 * Color schemes are managed by the ColorManager class and shouldn't be accessed directly. The default settings for
 * ColorScheme should be considered as the recommended color settings, although I plan on having a few more optional
 * schemes that extend this class and use their own values.
 *
 * This is a bit overengineered for this particular mod, but underengineered for future plans - I mostly wanted a nice
 * testing ground / foundation for some upcoming mods that will deal with color a bit better. Long term goals are to
 * make this (de)serializeable through JSON to make testing not require a recompile. (I might even do this for InfoMod
 * - haven't decided yet).
 */
public class ColorScheme {

    // --------------------------------------------------------------------------------
    // Qualitative colors (useful for visually highlighting different, unrelated categories)
    // --------------------------------------------------------------------------------

    protected Color QUAL_RED = new Color(0.824f, 0.345f, 0.251f, 1.000f);
    protected Color QUAL_BLUE = new Color(0.055f, 0.667f, 0.824f, 1.000f);
    protected Color QUAL_GREEN = new Color(0.608f, 0.835f, 0.396f, 1.000f);

    protected Color QUAL_TEAL = new Color(0.545f, 0.855f, 0.808f, 1.000f);
    protected Color QUAL_YELLOW = new Color(0.953f, 0.776f, 0.369f, 1.000f);
    protected Color QUAL_PURPLE = new Color(0.890f, 0.502f, 0.890f, 1.000f);
    protected Color QUAL_BEIGE = new Color(0.694f, 0.667f, 0.584f, 1.000f);


    // --------------------------------------------------------------------------------
    // Sequential colors (useful for showing a progressing sequence)
    // --------------------------------------------------------------------------------

    protected Color SEQ_0 = new Color(0.694f, 0.843f, 0.765f, 1.000f);
    protected Color SEQ_1 = new Color(0.627f, 0.812f, 0.812f, 1.000f);
    protected Color SEQ_2 = new Color(0.529f, 0.682f, 0.753f, 1.000f);
    protected Color SEQ_3 = new Color(0.435f, 0.545f, 0.714f, 1.000f);

    // --------------------------------------------------------------------------------
    // UI: Main tool tips
    // --------------------------------------------------------------------------------

    protected Color MAIN_TOOLTIP_DTB_OUTER = new Color(0.318f, 0.345f, 0.365f, 1.000f);
    protected Color MAIN_TOOLTIP_DTB_INNER = new Color(0.376f, 0.455f, 0.514f, 1.000f);
    protected Color MAIN_TOOLTIP_DTB_BASE = new Color(0.129f, 0.153f, 0.169f, 1.000f);

    // --------------------------------------------------------------------------------
    // UI: Event screen colors
    // --------------------------------------------------------------------------------

//    protected Color EVENT_SCREEN_TITLE = new Color(0.686f, 0.753f, 0.855f, 1.000f);
//    protected Color EVENT_SCREEN_SUBTITLE = new Color(0.373f, 0.455f, 0.557f, 1.000f);

    protected Color EVENT_SCREEN_TITLE = Color.valueOf("b0aaa8ff");
    protected Color EVENT_SCREEN_SUBTITLE = Color.valueOf("6c6e70ff");

    protected Color EVENT_SCREEN_ACT_SECTION_TITLE = new Color(0.612f, 0.600f, 0.608f, 1.000f);

    protected Color EVENT_SCREEN_ACTIVE_EVENT = new Color(0.525f, 0.439f, 0.286f, 1.000f);
    protected Color EVENT_SCREEN_INACTIVE_EVENT = new Color(0.212f, 0.259f, 0.286f, 1.000f);

    protected Color EVENT_SCREEN_INACTIVE_EVENT_HIGHLIGHT = new Color(0.486f, 0.275f, 0.314f, 1.000f);
    protected Color EVENT_SCREEN_ACTIVE_EVENT_HIGHLIGHT = new Color(0.800f, 0.698f, 0.518f, 1.000f);

    protected Color EVENT_SCREEN_PERCENTAGE_BOX_TEXT = new Color(0.667f, 0.694f, 0.737f, 1.000f);

    // --------------------------------------------------------------------------------
    // UI: Event screen detail tooltips
    // --------------------------------------------------------------------------------
    protected Color EVENT_DETAIL_TOOLTIP_DTB_BASE = new Color(0.118f, 0.125f, 0.145f, 1.0f);
    protected Color EVENT_DETAIL_TOOLTIP_DTB_TRIM = new Color(0.267f, 0.275f, 0.361f, 1.0f);

    protected Color EVENT_DETAIL_TOOLTIP_TITLE = new Color(0.780f, 0.859f, 0.925f, 1.0f);
    protected Color EVENT_DETAIL_TOOLTIP_DESC = new Color(0.431f, 0.455f, 0.471f, 1.0f);

    protected Color EVENT_DETAIL_TOOLTIP_REQ_FAILED = new Color(0.796f, 0.267f, 0.357f, 1.0f);
    protected Color EVENT_DETAIL_TOOLTIP_REQ_SUCCESS = new Color(0.380f, 0.663f, 0.463f, 1.0f);

    // --------------------------------------------------------------------------------

    // Slay the Spire specific
    protected Color RARE = new Color(0.953f, 0.776f, 0.369f, 1.000f); // syn. with QUAL_YELLOW
    protected Color UNCOMMON = new Color(0.627f, 0.812f, 0.812f, 1.000f); // syn. with SEQ_1
    protected Color COMMON = new Color(0.694f, 0.667f, 0.584f, 1.000f); // syn. with QUAL_BEIGE

}
