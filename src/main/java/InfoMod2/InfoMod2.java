package InfoMod2;

import InfoMod2.data.EventDatabase;
import InfoMod2.top.PotionPanelItem;
import InfoMod2.ui.screens.EventScreen;
import InfoMod2.utils.graphics.ScreenHelper;
import basemod.BaseMod;
import basemod.interfaces.PostInitializeSubscriber;
import basemod.interfaces.RenderSubscriber;
import basemod.interfaces.StartGameSubscriber;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

@SpireInitializer
public class InfoMod2 implements PostInitializeSubscriber, RenderSubscriber, StartGameSubscriber {
    public static void initialize() { new InfoMod2(); }
    public InfoMod2() {
        BaseMod.subscribe(this);
    }

    private PotionPanelItem potionPanelItem;

    public static EventScreen eventScreen;

    @Override
    public void receivePostInitialize() {
        potionPanelItem = new PotionPanelItem();
        BaseMod.addTopPanelItem(potionPanelItem);

        // Load event database from json
        EventDatabase.load("/InfoMod2/data/act1.json");
        EventDatabase.load("/InfoMod2/data/act2.json");
        EventDatabase.load("/InfoMod2/data/act3.json");
        EventDatabase.load("/InfoMod2/data/shrines.json");

        eventScreen = new EventScreen();
    }

    @Override
    public void receiveRender(SpriteBatch sb) {
        if (ScreenHelper.isScreenUp())
            ScreenHelper.render(sb);
    }

    @Override
    public void receiveStartGame() {
        System.out.println("Starting game. Asc level is: " + AbstractDungeon.ascensionLevel);

        // Recompute layouts
        eventScreen.initialize();
    }
}
