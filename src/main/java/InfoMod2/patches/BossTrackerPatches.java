package InfoMod2.patches;

import InfoMod2.ui.widgets.tooltips.groups.MapTips;
import basemod.BaseMod;
import basemod.abstracts.CustomSavableRaw;
import basemod.interfaces.OnStartBattleSubscriber;
import basemod.interfaces.PreStartGameSubscriber;
import basemod.interfaces.StartGameSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.google.gson.JsonElement;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.util.ArrayList;

public class BossTrackerPatches {

    public static void handleBoss(String act, int index) {
        if (!CardCrawlGame.isInARun())
            return;

        ArrayList<String> bossList = AbstractDungeon.bossList;
        if (bossList != null && bossList.size() > index) {
            String desiredBoss = bossList.get(index);

            MapTips.addBoss(desiredBoss, index != 0);
        }
    }

    // --------------------------------------------------------------------------------

    @SpirePatch( clz = Exordium.class, method = "initializeBoss")
    public static class ExordiumBossPatch {
        @SpirePostfixPatch public static void Postfix() { handleBoss("Exordium", 0); }
    }

    @SpirePatch( clz = TheCity.class, method = "initializeBoss")
    public static class TheCityBossPatch {
        @SpirePostfixPatch public static void Postfix() { handleBoss("The City", 0); }
    }

    @SpirePatch( clz = TheBeyond.class, method = "initializeBoss")
    public static class TheBeyondBossPatch {
        @SpirePostfixPatch
        public static void Postfix() {
            handleBoss("The Beyond", 0);

            // Second boss for A20
            if (AbstractDungeon.ascensionLevel >= 20) {
                handleBoss("The Beyond (Boss 2)", 1);
            }
        }
    }

    @SpirePatch( clz = TheEnding.class, method = "initializeBoss")
    public static class TheEndingBossPatch {
        @SpirePostfixPatch public static void Postfix() { handleBoss("The Ending", 0); }
    }

    // --------------------------------------------------------------------------------

    // TODO: save / load this data into the run file

    @SpireInitializer
    public static class BossWatcher implements PreStartGameSubscriber, OnStartBattleSubscriber, StartGameSubscriber {
        public static void initialize() { new BossWatcher(); }
        public BossWatcher() { BaseMod.subscribe(this); }

        // Reset the tool tip at the start of a run
        @Override
        public void receivePreStartGame() {
            MapTips.resetBossTip();
        }

        // Edge case: A20 second boss requires us to refresh the boss tip
        @Override
        public void receiveOnBattleStart(AbstractRoom abstractRoom) {
            if (CardCrawlGame.isInARun() && AbstractDungeon.floorNum == 51 && AbstractDungeon.ascensionLevel == 20) {
                MapTips.refreshBossTip();
            }
        }

        @Override
        public void receiveStartGame() {
            MapTips.refreshBossTip();
        }
    }

    // Ensure the data is properly saved/loaded between game boots
    @SpireInitializer
    public static class BossSaveableHelper implements CustomSavableRaw {
        public static void initialize() { new BossSaveableHelper(); }

        public BossSaveableHelper() {
            BaseMod.addSaveField("OJB_INFOMOD_BOSSES", this);
        }

        @Override
        public JsonElement onSaveRaw() {
            return MapTips.serialize();
        }

        @Override
        public void onLoadRaw(JsonElement jsonElement) {
            if (jsonElement != null && jsonElement.isJsonObject())
                MapTips.deserialize(jsonElement.getAsJsonObject());
        }
    }

}
