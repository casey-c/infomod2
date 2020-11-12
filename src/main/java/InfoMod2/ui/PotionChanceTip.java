package InfoMod2.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;

public class PotionChanceTip extends ThiccToolTip {
    public PotionChanceTip() {
        super(1383, 691, 400, 300);
        //box.render(sb, 1383, 691, 400, 300);
    }

    @Override
    protected void renderForeground(SpriteBatch sb) {
        FontHelper.renderFontLeftDownAligned(sb, FontHelper.tipBodyFont, "Test", 1450, 800, Settings.CREAM_COLOR);
    }
}
