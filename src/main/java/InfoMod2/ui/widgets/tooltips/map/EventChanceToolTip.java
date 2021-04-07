package InfoMod2.ui.widgets.tooltips.map;

import InfoMod2.ui.widgets.tooltips.TitledToolTip;
import InfoMod2.utils.graphics.color.ColorManager;
import InfoMod2.utils.math.EventChanceHelper;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;

public class EventChanceToolTip extends TitledToolTip<EventChanceToolTip> {
    private static final float SPACING = 37;

    public EventChanceToolTip() {
        super(261, 338, "Next [?] Floor", "Right-Click for full details");
    }

    // TODO: use config settings to enable/disable qualitative color scheme
    private Color getEventColor() { return ColorManager.QUAL_TEAL(); }
    private Color getShrineColor() { return ColorManager.QUAL_BLUE(); }
    private Color getFightColor() { return ColorManager.QUAL_RED(); }
    private Color getShopColor() { return ColorManager.QUAL_YELLOW(); }
    private Color getTreasureColor() { return ColorManager.QUAL_GREEN(); }

    // TODO: write update functions etc.
    private EventChanceHelper helper = new EventChanceHelper();
    public EventChanceHelper getHelper() { return helper; }

    public void updateHelper() {
        helper.update();
    }

    @Override
    protected void renderContent(SpriteBatch sb, float left, float top) {
        final float detailLeft = left + 123;

        float currY = top - 30;

        final Color eventColor = getEventColor();
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Event", left * Settings.xScale, currY * Settings.yScale, eventColor);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, helper.getEventChance(), detailLeft * Settings.xScale, currY * Settings.yScale, eventColor);
        currY -= SPACING;

        final Color shrineColor = getShrineColor();
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Shrine", left * Settings.xScale, currY * Settings.yScale, shrineColor);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, helper.getShrineChance(), detailLeft * Settings.xScale, currY * Settings.yScale, shrineColor);
        currY -= SPACING;

        final Color fightColor = getFightColor();
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Fight", left * Settings.xScale, currY * Settings.yScale, fightColor);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, helper.getFightChance(), detailLeft * Settings.xScale, currY * Settings.yScale, fightColor);
        currY -= SPACING;

        final Color shopColor = getShopColor();
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Shop", left * Settings.xScale, currY * Settings.yScale, shopColor);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, helper.getShopChance(), detailLeft * Settings.xScale, currY * Settings.yScale, shopColor);
        currY -= SPACING;

        final Color treasureColor = getTreasureColor();
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Treasure", left * Settings.xScale, currY * Settings.yScale, treasureColor);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, helper.getTreasureChance(), detailLeft * Settings.xScale, currY * Settings.yScale, treasureColor);
    }
}
