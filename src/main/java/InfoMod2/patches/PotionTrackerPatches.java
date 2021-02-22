package InfoMod2.patches;

import InfoMod2.ui.widgets.tooltips.groups.PotionTips;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class PotionTrackerPatches {
    public static void updatePotionChance() {
        if (!CardCrawlGame.isInARun() || AbstractDungeon.player == null)
            return;

        int chance = 40 + AbstractRoom.blizzardPotionMod;

        // Account for relics
        if ( AbstractDungeon.player.hasRelic("White Beast Statue") )
            chance = 100;
        if ( AbstractDungeon.player.hasRelic("Sozu") )
            chance = 0;

        // Send the updated chance to the potion tracker tooltip
        PotionTips.updatePotionChance(chance);
    }

    // Fires whenever a potion is dropped
    @SpirePatch( clz = AbstractRoom.class, method = "addPotionToRewards", paramtypez = {} )
    public static class AbstractRoomAddPotionToRewardPatch {
        @SpirePostfixPatch public static void Postfix() { updatePotionChance(); }
    }

    // Initialize on loading seeds
    @SpirePatch( clz = AbstractDungeon.class, method = "loadSeeds" )
    public static class AbstractDungeonLoadSeedsPatch {
        @SpirePostfixPatch public static void Postfix() { updatePotionChance(); }
    }

    // Initialize on loading a save
    @SpirePatch( clz = AbstractDungeon.class, method = "loadSave" )
    public static class AbstractDungeonLoadSavePatch {
        @SpirePostfixPatch public static void Postfix() { updatePotionChance(); }
    }

    // --------------------------------------------------------------------------------

    // Relic obtain patches
    @SpirePatch( clz = AbstractRelic.class, method = "reorganizeObtain")
    public static class AbstractRelicReorganizeObtainPatch {
        @SpirePostfixPatch public static void Postfix() { updatePotionChance(); }
    }

    //public void instantObtain(AbstractPlayer p, int slot, boolean callOnEquip) {
    @SpirePatch( clz = AbstractRelic.class, method = "instantObtain", paramtypez = {AbstractPlayer.class, int.class, boolean.class} )
    public static class AbstractRelicInstantObtainParamsPatch {
        @SpirePostfixPatch public static void Postfix() { updatePotionChance(); }
    }

    @SpirePatch( clz = AbstractRelic.class, method = "instantObtain", paramtypez = {} )
    public static class AbstractRelicInstantObtainNoParamsPatch {
        @SpirePostfixPatch public static void Postfix() { updatePotionChance(); }
    }

    @SpirePatch( clz = AbstractRelic.class, method = "obtain")
    public static class AbstractRelicObtainPatch {
        @SpirePostfixPatch public static void Postfix() { updatePotionChance(); }
    }

    // --------------------------------------------------------------------------------

    // Edge case: need to worry about removing relics using Nloth's Gift event
    @SpirePatch( clz = AbstractPlayer.class, method = "loseRelic" )
    public static class NlothGiftLoseRelicPatch {
        @SpirePostfixPatch public static void Postfix() { updatePotionChance(); }
    }

    // Edge case: need to update on dungeon transition (e.g. on map screen starting an act after boss chest)
    // TODO: verify that this works with testing
    @SpirePatch( clz = AbstractDungeon.class, method = "dungeonTransitionSetup" )
    public static class AbstractDungeonTransitionPatch {
        @SpirePostfixPatch public static void Postfix() { updatePotionChance(); }
    }

}
