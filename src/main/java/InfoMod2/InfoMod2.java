package InfoMod2;

import InfoMod2.top.PanelItem;
import InfoMod2.ui.ThiccToolTip;
import basemod.BaseMod;
import basemod.interfaces.PostInitializeSubscriber;
import basemod.interfaces.RenderSubscriber;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

@SpireInitializer
public class InfoMod2 implements PostInitializeSubscriber {
    public static void initialize() { new InfoMod2(); }
    public InfoMod2() {
        BaseMod.subscribe(this);
    }

    private PanelItem panelItem;

    @Override
    public void receivePostInitialize() {
        System.out.println("InfoMod2 reporting for duty");
        panelItem = new PanelItem();
        BaseMod.addTopPanelItem(panelItem);
    }
}
