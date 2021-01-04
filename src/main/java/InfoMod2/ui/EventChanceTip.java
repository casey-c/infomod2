package InfoMod2.ui;

import InfoMod2.ui.tips.TitledToolTip;
import InfoMod2.utils.ExtraColors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;

public class EventChanceTip extends TitledToolTip<EventChanceTip> {
    public EventChanceTip() {
        //super(width, height, titleRows);
        // TODO: definitely need to reword this to explain what this panel actually means
        //   (e.g. Next ? floor chances. Right click to see all available events.)
        super(400, 400, "Next [?] floor chances. Right ", "click to see all available events");
    }

    @Override
    public void renderDetails(SpriteBatch sb) {
        float titleLeft = (getContentLeft() + 99 - 20) * Settings.scale;
        float detailsLeft = (getContentLeft() + 231 - 20) * Settings.scale;

        float eventTop = detailsTop - 15;
        float vertSpacing = 47;

        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Event:", titleLeft,  eventTop * Settings.scale, ExtraColors.TEXT_CREAM);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Shrine:", titleLeft,  (eventTop - vertSpacing) * Settings.scale, ExtraColors.TEXT_CREAM);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Fight:", titleLeft,  (eventTop - 2 * vertSpacing) * Settings.scale, ExtraColors.TEXT_CREAM);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Shop:", titleLeft,  (eventTop - 3 * vertSpacing) * Settings.scale, ExtraColors.TEXT_CREAM);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Treasure:", titleLeft,  (eventTop - 4 * vertSpacing) * Settings.scale, ExtraColors.TEXT_CREAM);

        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "63.75 %", detailsLeft,  eventTop * Settings.scale, ExtraColors.TEXT_LIGHT_GREEN);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "21.25 %", detailsLeft,  (eventTop - vertSpacing) * Settings.scale, ExtraColors.TEXT_LIGHT_GREEN);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "10.00 %", detailsLeft,  (eventTop - 2 * vertSpacing) * Settings.scale, ExtraColors.TEXT_LIGHT_GREEN);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "3.00 %", detailsLeft,  (eventTop - 3 * vertSpacing) * Settings.scale, ExtraColors.TEXT_LIGHT_GREEN);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "2.00 %", detailsLeft,  (eventTop - 4 * vertSpacing) * Settings.scale, ExtraColors.TEXT_LIGHT_GREEN);
    }
}

