package InfoMod2;

import InfoMod2.data.EventDatabase;
import InfoMod2.top.PotionPanelItem;
import InfoMod2.utils.ScreenHelper;
import basemod.BaseMod;
import basemod.interfaces.PostInitializeSubscriber;
import basemod.interfaces.RenderSubscriber;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

@SpireInitializer
public class InfoMod2 implements PostInitializeSubscriber, RenderSubscriber {
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

        // Load event database from json
        EventDatabase.load("/InfoMod2/data/act1.json");
    }

    @Override
    public void receiveRender(SpriteBatch sb) {
        if (ScreenHelper.isScreenUp())
            ScreenHelper.render(sb);
    }
}
