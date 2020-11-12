package InfoMod2.ui;

import InfoMod2.utils.ExtraColors;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TitledToolTip extends BiggerToolTip<TitledToolTip> {
    //protected String title = "Placeholder";
    protected ArrayList<String> titles = new ArrayList<>();
    protected float totalTitleHeight;
    protected BitmapFont titleFont = FontHelper.tipBodyFont;


    //protected static final int titleHorizPadding = 30;
    //protected static final int titleVertPadding = 4;
    protected static final int titleVertPadding = 24;
    protected static final int titleVertSpacing = 30;
    protected static final int additionalDividerSpacing = 8;

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

    //public void setTitle(String title) { this.title = title; }
//    public TitledToolTip withTitle(String title) {
//        setTitle(title);
//        return this;
//    }

    public void setTitles(String... titleRows) {
        this.titles.clear();
        this.titles.addAll(Arrays.asList(titleRows));

        if (titles.isEmpty()) {
            System.out.println("InfoMod2 WARNING: TitledToolTip has no title");
            titles.add("[Placeholder]");
        }

        //Collections.reverse(titles);

        int numTitles = titles.size();
        this.totalTitleHeight = numTitles * (titleFont.getLineHeight()) + (numTitles - 1) * titleVertSpacing;
        System.out.println("total title height is " + totalTitleHeight);
        System.out.println("total line height is " + titleFont.getLineHeight());
        System.out.println("content top" + getContentTop());

    }

    // --------------------------------------------------------------------------------

    protected void renderTitle(SpriteBatch sb) {
        float y = getContentTop() - titleVertPadding;


//        sb.setColor(Color.RED);
//        sb.draw(ImageMaster.WHITE_SQUARE_IMG, getContentLeft(), y, getContentWidth(), 2);

        for (String title : titles) {
            FontHelper.renderFontCentered(sb, FontHelper.tipBodyFont, title, getContentCenterX() * Settings.scale, y * Settings.scale, ExtraColors.TEXT_CREAM);
            y -= titleVertSpacing;
        }

        // Divider
        sb.setColor(ExtraColors.DIVIDER_COLOR);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, getContentLeft() * Settings.scale, (y - additionalDividerSpacing) * Settings.scale, getContentWidth() * Settings.scale, 3 * Settings.scale);
    }


    @Override
    protected void renderForeground(SpriteBatch sb) {
        renderTitle(sb);
    }
}
