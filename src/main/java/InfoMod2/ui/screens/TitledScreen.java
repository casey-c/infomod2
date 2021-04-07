package InfoMod2.ui.screens;

import InfoMod2.ui.widgets.AbstractWidget;
import InfoMod2.ui.widgets.AnchorPosition;
import InfoMod2.ui.widgets.text.SmartLabel;
import InfoMod2.utils.graphics.color.ColorManager;
import InfoMod2.utils.graphics.ExtraFonts;
import InfoMod2.utils.graphics.ScreenHelper;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputHelper;

public class TitledScreen<T extends TitledScreen<T>> extends AbstractWidget<T> {
    private Texture TEX_BG;
    private float texWidth, texHeight;

    private SmartLabel titleLabel, subtitleLabel;

    public TitledScreen(Texture texBG, String title, String subtitle, Color titleColor, Color subtitleColor) {
        this.TEX_BG = texBG;

        this.texWidth = texBG.getWidth();
        this.texHeight = texBG.getHeight();

        this.anchorCenteredOnScreen();

        // Title and subtitle creation/alignment
        this.titleLabel = new SmartLabel(title, ExtraFonts.screenTitle(), titleColor);
        this.subtitleLabel = new SmartLabel(subtitle, ExtraFonts.screenSubtitle(), subtitleColor);

        float titleTop = getContentTop() - 45;
        float subTitleTop = titleTop - 33;

        titleLabel.anchoredAt(getMainContentLeft() - 10, titleTop, AnchorPosition.LEFT_TOP);
        subtitleLabel.anchoredAt(getMainContentLeft() - 10, subTitleTop, AnchorPosition.LEFT_TOP);
    }

    // horizontal padding = 40px, vertical padding = 95px. note: top also accounts 120px for title/subtitle area
    protected float getMainContentLeft() { return getContentLeft() + 95.0f; }
    protected float getMainContentRight() { return getContentRight() - 95.0f; }
    protected float getMainContentBottom() { return getContentBottom() + 40.0f; }
    protected float getMainContentTop() { return getContentTop() - 160.0f; }

    protected float getMainContentWidth() { return getMainContentRight() - getMainContentLeft(); }
    protected float getMainContentHeight() { return getMainContentTop() - getMainContentBottom(); }

    @Override public float getPreferredContentWidth() { return texWidth; }
    @Override public float getPreferredContentHeight() { return texHeight; }

    protected void renderBackground(SpriteBatch sb) {
        sb.setColor(ColorManager.SCREEN_BACKGROUND_DIM());
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0, 0, Settings.WIDTH, Settings.HEIGHT);

        sb.setColor(ColorManager.SCREEN_OPACITY());
        sb.draw(TEX_BG,
                getContentLeft() * Settings.xScale,
                getContentBottom() * Settings.yScale,
                texWidth * Settings.xScale,
                texHeight * Settings.yScale);
    }

    protected void renderTitles(SpriteBatch sb) {
        titleLabel.render(sb);
        subtitleLabel.render(sb);
    }

    protected void renderWidgets(SpriteBatch sb) { }
    protected void renderWidgetToolTips(SpriteBatch sb) { }

    @Override
    public void render(SpriteBatch sb) {
        renderBackground(sb);
        renderTitles(sb);
        renderWidgets(sb);
        renderWidgetToolTips(sb);
    }

    private boolean rightClickStarted = false;

    @Override
    public void update() {
        // Let this screen close itself
        if (InputHelper.isMouseDown_R) {
            rightClickStarted = true;
        } else if (rightClickStarted) {
            rightClickStarted = false;
            ScreenHelper.closeAllCustomScreens();
        }
    }
}
