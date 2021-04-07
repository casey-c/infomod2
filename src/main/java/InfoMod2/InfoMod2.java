package InfoMod2;

import InfoMod2.data.EventDatabase;
import InfoMod2.top.PotionPanelItem;
import InfoMod2.ui.screens.EventScreen;
import InfoMod2.ui.widgets.tooltips.groups.DeckTips;
import InfoMod2.ui.widgets.tooltips.groups.MapTips;
import InfoMod2.ui.widgets.tooltips.groups.SettingsTips;
import InfoMod2.utils.graphics.ScreenHelper;
import InfoMod2.utils.integration.SlayTheRelicsIntegration;
import basemod.BaseMod;
import basemod.interfaces.PostInitializeSubscriber;
import basemod.interfaces.RenderSubscriber;
import basemod.interfaces.StartGameSubscriber;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;

import java.util.ArrayList;

@SpireInitializer
public class InfoMod2 implements PostInitializeSubscriber, RenderSubscriber, StartGameSubscriber {
    public static void initialize() { new InfoMod2(); }
    public InfoMod2() {
        BaseMod.subscribe(this);
    }

    private static PotionPanelItem potionPanelItem;
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

        BaseMod.registerModBadge(new Texture("InfoMod2/badge.png"),  "InfoMod",  "ojb",  "Adds additional information to tool tips.", null);
    }

    @Override
    public void receiveRender(SpriteBatch sb) {
        if (ScreenHelper.isScreenUp())
            ScreenHelper.render(sb);
        else {
            // Bugfix: you can mouse over the top right icons while saving and quitting and still have the tool tips
            // render
            // TODO: should probably handle this better (maybe force the SHOULD_RENDER bools to be unset?)
            if (!CardCrawlGame.isInARun())
                return;

            // Render all the extra tips (doing it here so they render on TOP of just about everything but the mouse)
            if (MapTips.SHOULD_RENDER)
                MapTips.renderCustomMapTips(sb);

            if (DeckTips.SHOULD_RENDER)
                DeckTips.renderCustomDeckTips(sb);

            if (SettingsTips.SHOULD_RENDER)
                SettingsTips.renderCustomTips(sb);
        }
    }

    @Override
    public void receiveStartGame() {
        // Recompute layouts
        eventScreen.initialize();
    }

    // Messy messy messy spaghetti trying to backfit this in
    public static void updateSlayTheRelicsPotionTips(ArrayList<PowerTip> tips) {
        SlayTheRelicsIntegration.update("potionTips", potionPanelItem.getHitbox(), tips);
    }
}
