package InfoMod2.ui;

import InfoMod2.ui.tips.BiggerToolTip;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;

import java.util.ArrayList;

public class BossTip extends BiggerToolTip<BossTip> {
    private ArrayList<String> bosses = new ArrayList<>();

    private static final float vertSpacing = 12;
    private static final float extraPaddingTop = 28;
    private static final float extraPaddingBottom = 14;

    private BitmapFont font = FontHelper.tipBodyFont;
    private float textHeight = font.getLineHeight();


    public BossTip() {
        super(400, 200);
    }

    public void setBosses(ArrayList<String> bossList) {
        bosses.clear();
        bosses.addAll(bossList);

        System.out.println("ORIGINAL");
        print();

        float top = getTop();

        // problem: textHeight is already scaled for the current resolution. the rest of the values are not scaled yet,
        // so the when the final scaling occurs, the totalTextHeight is wrong as a piece of it gets scaled twice.
        // solution: unscale it back to 1080p (fonts only have integer heights, hence the floor function)
        float descaledTextHeight = (float) Math.floor(textHeight / Settings.scale);
        float totalTextHeight = (bosses.size() * descaledTextHeight) + (bosses.size() - 1) * vertSpacing;

        System.out.println("There are " + bosses.size() + " bosses totalling " + totalTextHeight);
        this.height = Math.max(totalTextHeight + 2 * (margins + bevelSize) + extraPaddingBottom + extraPaddingTop, 128.0f);

        anchoredAtTopLeft(left, top);

        System.out.println("POST ADD");
        print();
    }

    public void setBosses(String... bossNames) {
        ArrayList<String> tmp = new ArrayList<>();
        for (String b : bossNames)
            tmp.add(b);

        setBosses(tmp);
    }

    public ArrayList<String> getBosses() { return bosses; }

    @Override
    protected void renderForeground(SpriteBatch sb) {
        float left = (getContentLeft() + 35) * Settings.scale;
        float y = (getContentTop() - extraPaddingTop);

        // Make the single boss a little lower to be more centered (the tip box mins at 128)
        if (bosses.size() == 1)
            y -= 5;

        for (int i = 0; i < bosses.size(); ++i) {
            Color c = (i == bosses.size() - 1) ? Settings.GREEN_TEXT_COLOR : Settings.CREAM_COLOR;
            FontHelper.renderFontLeftTopAligned(sb, font, bosses.get(i), left, y * Settings.scale, c);
            y -= (vertSpacing + (float)Math.floor(textHeight / Settings.scale));
        }
    }
}
