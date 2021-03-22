package InfoMod2.ui.screens;

import InfoMod2.ui.widgets.AbstractWidget;
import InfoMod2.ui.widgets.AnchorPosition;
import InfoMod2.ui.widgets.text.SmartLabel;
import InfoMod2.utils.graphics.ExtraColors;
import InfoMod2.utils.graphics.ExtraFonts;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;

import java.util.LinkedList;

// v2
public class TitledScreen<T extends TitledScreen<T>> extends AbstractWidget<T> {
    private Texture TEX_BG;
    private float texWidth, texHeight;

    private LinkedList<AbstractWidget> widgets = new LinkedList<>();

    private SmartLabel titleLabel, subtitleLabel;

    public TitledScreen(Texture texBG, String title, String subtitle) {
        this.TEX_BG = texBG;

        this.texWidth = texBG.getWidth();
        this.texHeight = texBG.getHeight();

        this.anchorCenteredOnScreen();
        setMargins(70.0f, 0.0f);

        // TODO: actual fonts (most likely shooting for Kreon 21 and Italic No Border 14)
        this.titleLabel = new SmartLabel(title, FontHelper.tipBodyFont, ExtraColors.SCREEN_TITLE_TEXT);
        this.subtitleLabel = new SmartLabel(subtitle, ExtraFonts.screenSubtitle(), ExtraColors.SCREEN_SUBTITLE_TEXT);

        float titleTop = getAbsoluteTop() - 39;
        float subTitleTop = titleTop - 33;

        titleLabel.anchoredAt(getContentLeft(), titleTop, AnchorPosition.LEFT_TOP);
        subtitleLabel.anchoredAt(getContentLeft(), subTitleTop, AnchorPosition.LEFT_TOP);
    }

    @Override public float getPreferredContentWidth() { return texWidth; }
    @Override public float getPreferredContentHeight() { return texHeight; }

    protected void renderBackground(SpriteBatch sb) {
        sb.setColor(ExtraColors.SCREEN_DIM_V3);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0, 0, Settings.WIDTH, Settings.HEIGHT);

        sb.setColor(ExtraColors.SCREEN_OPACITY);
        sb.draw(TEX_BG,
                getAbsoluteLeft() * Settings.xScale,
                getAbsoluteBottom() * Settings.yScale,
                texWidth * Settings.xScale,
                texHeight * Settings.yScale);
//        sb.draw(TEX_BG,
//                (Settings.WIDTH - (texWidth * Settings.xScale)) * 0.5f,
//                (Settings.HEIGHT - (texHeight * Settings.yScale)) * 0.5f,
//                texWidth * Settings.xScale,
//                texHeight * Settings.yScale
//        );
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
}
