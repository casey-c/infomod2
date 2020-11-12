package InfoMod2;

import InfoMod2.ui.ThiccToolTip;
import basemod.BaseMod;
import basemod.interfaces.PostInitializeSubscriber;
import basemod.interfaces.RenderSubscriber;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputHelper;

@SpireInitializer
public class InfoMod2 implements PostInitializeSubscriber, RenderSubscriber {
    public static void initialize() { new InfoMod2(); }

    private ThiccToolTip toolTip;

    public InfoMod2() {
        BaseMod.subscribe(this);
    }


    @Override
    public void receivePostInitialize() {
        System.out.println("InfoMod2 reporting for duty");
        toolTip = new ThiccToolTip();
    }

    @Override
    public void receiveRender(SpriteBatch sb) {
        toolTip.render(sb);
    }
}
