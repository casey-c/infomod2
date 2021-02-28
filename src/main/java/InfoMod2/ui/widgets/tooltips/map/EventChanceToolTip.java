package InfoMod2.ui.widgets.tooltips.map;

import InfoMod2.ui.widgets.tooltips.TitledToolTip;
import InfoMod2.utils.graphics.ExtraColors;
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
    private Color getEventColor() { return ExtraColors.QUAL_TEAL; }
    private Color getShrineColor() { return ExtraColors.QUAL_BLUE; }
    private Color getFightColor() { return ExtraColors.QUAL_RED; }
    private Color getShopColor() { return ExtraColors.QUAL_YELLOW; }
    private Color getTreasureColor() { return ExtraColors.QUAL_GREEN; }

    // TODO: write update functions etc.
    EventChanceHelper helper = new EventChanceHelper();

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
