package InfoMod2.patches;

import InfoMod2.ui.widgets.tooltips.groups.DeckTips;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class CardDropPatches {
    private static void update() {
        DeckTips.updateCardDrops();
    }

    // --------------------------------------------------------------------------------

    // Initialize on loading seeds
    @SpirePatch( clz = AbstractDungeon.class, method = "loadSeeds" )
    public static class AbstractDungeonLoadSeedsPatch {
        @SpirePostfixPatch public static void Postfix() { update(); }
    }

    // Initialize on loading a save
    @SpirePatch( clz = AbstractDungeon.class, method = "loadSave" )
    public static class AbstractDungeonLoadSavePatch {
        @SpirePostfixPatch public static void Postfix() { update(); }
    }

    // Reinitialize every time the dungeon is reset
    @SpirePatch( clz = AbstractDungeon.class, method = "reset" )
    public static class AbstractDungeonResetPatch {
        @SpirePostfixPatch public static void Postfix() { update(); }
    }

    // --------------------------------------------------------------------------------

    // Recompute after generating colorless card rewards
    @SpirePatch( clz = AbstractDungeon.class, method = "getColorlessRewardCards" )
    public static class AbstractDungeonGetColorlessRewardCardsPatch {
        @SpirePostfixPatch public static void Postfix() { update(); }
    }

    // Recompute after generating a card reward
    @SpirePatch( clz = AbstractDungeon.class, method = "getRewardCards" )
    public static class AbstractDungeonGetRewardCardsPatch {
        @SpirePostfixPatch public static void Postfix() { update(); }
    }

    // --------------------------------------------------------------------------------

    // Relic obtain patches
    @SpirePatch( clz = AbstractRelic.class, method = "reorganizeObtain")
    public static class AbstractRelicReorganizeObtainPatch {
        @SpirePostfixPatch public static void Postfix() { update(); }
    }

    //public void instantObtain(AbstractPlayer p, int slot, boolean callOnEquip) {
    @SpirePatch( clz = AbstractRelic.class, method = "instantObtain", paramtypez = {AbstractPlayer.class, int.class, boolean.class} )
    public static class AbstractRelicInstantObtainParamsPatch {
        @SpirePostfixPatch public static void Postfix() { update(); }
    }

    @SpirePatch( clz = AbstractRelic.class, method = "instantObtain", paramtypez = {} )
    public static class AbstractRelicInstantObtainNoParamsPatch {
        @SpirePostfixPatch public static void Postfix() { update(); }
    }

    @SpirePatch( clz = AbstractRelic.class, method = "obtain")
    public static class AbstractRelicObtainPatch {
        @SpirePostfixPatch public static void Postfix() { update(); }
    }

    // Edge case: need to worry about removing relics using Nloth's Gift event
    @SpirePatch( clz = AbstractPlayer.class, method = "loseRelic" )
    public static class NlothGiftLoseRelicPatch {
        @SpirePostfixPatch public static void Postfix() { update(); }
    }

}
