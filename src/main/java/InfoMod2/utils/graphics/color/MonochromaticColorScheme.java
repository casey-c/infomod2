package InfoMod2.utils.graphics.color;

public class MonochromaticColorScheme extends ColorScheme {
    public MonochromaticColorScheme() {
        this.QUAL_RED = this.QUAL_BLUE = this.QUAL_GREEN = this.QUAL_TEAL = this.QUAL_YELLOW = this.QUAL_PURPLE
                = this.QUAL_BEIGE = ColorManager.CREAM();
        this.SEQ_0 = this.SEQ_1 = this.SEQ_2 = this.SEQ_3 = ColorManager.CREAM();

        this.COMMON = this.UNCOMMON = this.RARE = ColorManager.CREAM();

        this.EVENT_SCREEN_ACTIVE_EVENT = ColorManager.CREAM();
        this.EVENT_SCREEN_INACTIVE_EVENT = ColorManager.DARK_GRAY();
        this.EVENT_SCREEN_ACT_SECTION_TITLE = ColorManager.CREAM();
    }
}
