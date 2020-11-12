package InfoMod2;

import InfoMod2.top.PotionPanelItem;
import basemod.BaseMod;
import basemod.interfaces.PostInitializeSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

@SpireInitializer
public class InfoMod2 implements PostInitializeSubscriber {
    public static void initialize() { new InfoMod2(); }
    public InfoMod2() {
        BaseMod.subscribe(this);
    }

    private PotionPanelItem potionPanelItem;

    @Override
    public void receivePostInitialize() {
        System.out.println("InfoMod2 reporting for duty");
        potionPanelItem = new PotionPanelItem();
        BaseMod.addTopPanelItem(potionPanelItem);
    }
}
