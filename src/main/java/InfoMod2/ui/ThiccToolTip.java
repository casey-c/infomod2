package InfoMod2.ui;

import InfoMod2.utils.DynamicTextureBox;
import InfoMod2.utils.ExtraColors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.input.InputHelper;

public class ThiccToolTip {
    private DynamicTextureBox box = new DynamicTextureBox("InfoMod2/tooltip/")
            .withColors(ExtraColors.TOOL_TIP_OUTER_BEVEL, ExtraColors.TOOL_TIP_INNER_BEVEL, ExtraColors.TOOL_TIP_BASE);

    public void render(SpriteBatch sb) {
//        sb.setColor(Color.BLACK);
//        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0, 0, Settings.WIDTH, Settings.HEIGHT);

        if (CardCrawlGame.isInARun() && InputHelper.mX < 200 && InputHelper.mY < 200)
            box.render(sb, 300, 300, 500, 500);
    }
}
