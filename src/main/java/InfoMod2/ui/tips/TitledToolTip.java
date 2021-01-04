package InfoMod2.ui.tips;

import InfoMod2.utils.ExtraColors;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class TitledToolTip<T extends TitledToolTip<T>> extends BiggerToolTip<T> {
    //protected String title = "Placeholder";
    protected ArrayList<String> titles = new ArrayList<>();
    protected float totalTitleHeight;
    protected BitmapFont titleFont = FontHelper.tipBodyFont;


    protected static final int titleVertPadding = 24;
    protected static final int titleVertSpacing = 30;
    protected static final int additionalDividerSpacing = 8;

    protected float detailsTop;

    public TitledToolTip(float width, float height) {
        super(width, height);
    }

//    public TitledToolTip(float width, float height, String title) {
//        super(width, height);
//        setTitle(title);
//    }

    public TitledToolTip(float width, float height, String... titleRows) {
        super(width, height);
        setTitles(titleRows);
    }


    // --------------------------------------------------------------------------------

    public TitledToolTip withTitles(String... titleRows) {
        setTitles(titleRows);
        return this;
    }

    public void setTitles(String... titleRows) {
        this.titles.clear();
        this.titles.addAll(Arrays.asList(titleRows));

        if (titles.isEmpty()) {
            System.out.println("InfoMod2 WARNING: TitledToolTip has no title");
            titles.add("[Placeholder]");
        }

        int numTitles = titles.size();
        this.totalTitleHeight = numTitles * (titleFont.getLineHeight()) + (numTitles - 1) * titleVertSpacing;
        System.out.println("total title height is " + totalTitleHeight);
        System.out.println("total line height is " + titleFont.getLineHeight());
        System.out.println("content top" + getContentTop());
    }


    // --------------------------------------------------------------------------------

    protected void renderTitlesAndDivider(SpriteBatch sb) {
        float y = getContentTop() - titleVertPadding;

        for (String title : titles) {
            FontHelper.renderFontCentered(sb, FontHelper.tipBodyFont, title, getContentCenterX() * Settings.scale, y * Settings.scale, ExtraColors.TEXT_CREAM);
            y -= titleVertSpacing;
        }

        // Divider
        sb.setColor(ExtraColors.DIVIDER_COLOR);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, getContentLeft() * Settings.scale, (y - additionalDividerSpacing) * Settings.scale, getContentWidth() * Settings.scale, 3 * Settings.scale);

        detailsTop = y - additionalDividerSpacing - titleVertPadding;
    }

    // To be overridden
    public abstract void renderDetails(SpriteBatch sb);


    @Override
    protected void renderForeground(SpriteBatch sb) {
        renderTitlesAndDivider(sb);
        renderDetails(sb);
    }
}
