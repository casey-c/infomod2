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

    @Override
    protected void renderContent(SpriteBatch sb, float left, float top) {
        final float detailLeft = left + 123;

        float currY = top - 30;

        final Color eventColor = getEventColor();
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Event", left * Settings.scale, currY * Settings.scale, eventColor);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, helper.getEventChance(), detailLeft * Settings.scale, currY * Settings.scale, eventColor);
        currY -= SPACING;

        final Color shrineColor = getShrineColor();
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Shrine", left * Settings.scale, currY * Settings.scale, shrineColor);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, helper.getShrineChance(), detailLeft * Settings.scale, currY * Settings.scale, shrineColor);
        currY -= SPACING;

        final Color fightColor = getFightColor();
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Fight", left * Settings.scale, currY * Settings.scale, fightColor);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, helper.getFightChance(), detailLeft * Settings.scale, currY * Settings.scale, fightColor);
        currY -= SPACING;

        final Color shopColor = getShopColor();
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Shop", left * Settings.scale, currY * Settings.scale, shopColor);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, helper.getShopChance(), detailLeft * Settings.scale, currY * Settings.scale, shopColor);
        currY -= SPACING;

        final Color treasureColor = getTreasureColor();
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Treasure", left * Settings.scale, currY * Settings.scale, treasureColor);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, helper.getTreasureChance(), detailLeft * Settings.scale, currY * Settings.scale, treasureColor);
    }
}
