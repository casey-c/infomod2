package InfoMod2.ui.widgets.tooltips;

import InfoMod2.utils.ExtraColors;
import InfoMod2.utils.ExtraFonts;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class TitledToolTip<T extends TitledToolTip<T>> extends ExtendedToolTip<T> {
    private boolean hasSubtitle = false;
    private String title;
    private String subtitle;

    private static final float DIVIDER_X_INSET = 6;

    // --------------------------------------------------------------------------------

    public TitledToolTip(float width, float height, String title) {
        super(width, height);
        this.title = title;
    }

    public TitledToolTip(float width, float height, String title, String subtitle) {
        super(width, height);
        this.title = title;
        this.subtitle = subtitle;
        this.hasSubtitle = true;
    }

    // --------------------------------------------------------------------------------


    protected float renderTitle(SpriteBatch sb, float left, float top) {
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, title, left * Settings.scale, top * Settings.scale, ExtraColors.TOOLTIP_TEXT_CREAM);
        return top - 34;
    }

    protected float renderSubtitle(SpriteBatch sb, float left, float top) {
        FontHelper.renderFontLeftTopAligned(sb, ExtraFonts.smallItalicFont(), subtitle, left * Settings.scale, top * Settings.scale, ExtraColors.TOOLTIP_TEXT_GRAY);
        return top - 34; // TODO: technically font dependent
    }

    protected float renderDivider(SpriteBatch sb, float top) {
        float left = getContentLeft() + DIVIDER_X_INSET;
        float width = getPreferredContentWidth() - (2 * DIVIDER_X_INSET);

        sb.setColor(ExtraColors.TOOLTIP_OUTER);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, left * Settings.scale, top * Settings.scale, width * Settings.scale, 2);

        return top - 4;
    }

    protected float renderHeader(SpriteBatch sb, float left, float top) {
        float subtitleTop = renderTitle(sb, left, top);
        float dividerTop = hasSubtitle ? renderSubtitle(sb, left, subtitleTop) : subtitleTop;
        return renderDivider(sb, dividerTop);
    }

    // TODO: override as needed
    protected void renderContent(SpriteBatch sb, float left, float top) { }

    @Override
    public void renderForeground(SpriteBatch sb) {
        final float left = getContentLeft() + 34;
        float contentTop = renderHeader(sb, left, getContentTop() - 31);
        renderContent(sb, left, contentTop);
    }
}
