package InfoMod2.patches;

import InfoMod2.ui.widgets.tooltips.groups.GoldTips;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.SuicideAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.shop.ShopScreen;

public class GoldTrackerPatches {
    public static void updateShopPrices() {
        if (!CardCrawlGame.isInARun() || AbstractDungeon.player == null)
            return;

        GoldTips.updateShopPrices();
    }

    // Initialize on loading seeds
    @SpirePatch( clz = AbstractDungeon.class, method = "loadSeeds" )
    public static class AbstractDungeonLoadSeedsPatch {
        @SpirePostfixPatch public static void Postfix() { updateShopPrices(); }
    }

    // Initialize on loading a save
    @SpirePatch( clz = AbstractDungeon.class, method = "loadSave" )
    public static class AbstractDungeonLoadSavePatch {
        @SpirePostfixPatch public static void Postfix() { updateShopPrices(); }
    }

    // --------------------------------------------------------------------------------

    // Relic obtain patches
    @SpirePatch( clz = AbstractRelic.class, method = "reorganizeObtain")
    public static class AbstractRelicReorganizeObtainPatch {
        @SpirePostfixPatch public static void Postfix() { updateShopPrices(); }
    }

    //public void instantObtain(AbstractPlayer p, int slot, boolean callOnEquip) {
    @SpirePatch( clz = AbstractRelic.class, method = "instantObtain", paramtypez = {AbstractPlayer.class, int.class, boolean.class} )
    public static class AbstractRelicInstantObtainParamsPatch {
        @SpirePostfixPatch public static void Postfix() { updateShopPrices(); }
    }

    @SpirePatch( clz = AbstractRelic.class, method = "instantObtain", paramtypez = {} )
    public static class AbstractRelicInstantObtainNoParamsPatch {
        @SpirePostfixPatch public static void Postfix() { updateShopPrices(); }
    }

    @SpirePatch( clz = AbstractRelic.class, method = "obtain")
    public static class AbstractRelicObtainPatch {
        @SpirePostfixPatch public static void Postfix() { updateShopPrices(); }
    }

    // --------------------------------------------------------------------------------

    // Edge case: need to worry about removing relics using Nloth's Gift event
    @SpirePatch( clz = AbstractPlayer.class, method = "loseRelic" )
    public static class NlothGiftLoseRelicPatch {
        @SpirePostfixPatch public static void Postfix() { updateShopPrices(); }
    }

    // --------------------------------------------------------------------------------

    // Whenever the player buys the "remove card" option in a shop
    @SpirePatch( clz = ShopScreen.class, method = "purgeCard")
    public static class UpdateAfterPurgePatch {
        @SpirePostfixPatch public static void Postfix() { updateShopPrices(); }
    }

    // Probably unnecessary, but whenever the purge is reset back to 75 we can update the prices
    @SpirePatch( clz = ShopScreen.class, method = "resetPurgeCost")
    public static class ResetPurgeCostPatch {
        @SpirePostfixPatch public static void Postfix() { updateShopPrices(); }
    }

    // --------------------------------------------------------------------------------
    // Update whenever gold is changed (e.g. pickup gold/lose gold/etc)

    // Thieves stealing gold
    @SpirePatch( clz = DamageAction.class, method = "stealGold")
    public static class StealGoldPatch {
        @SpirePostfixPatch public static void Postfix() { updateShopPrices(); }
    }

    // Go commit die
    @SpirePatch( clz = SuicideAction.class, method = "update")
    public static class SeppukuPatch {
        @SpirePostfixPatch public static void Postfix() { updateShopPrices(); }
    }

    // Lose gold
    @SpirePatch( clz = AbstractPlayer.class, method = "loseGold")
    public static class LoseGoldPatch {
        @SpirePostfixPatch public static void Postfix() { updateShopPrices(); }
    }

    // Gain gold
    @SpirePatch( clz = AbstractPlayer.class, method = "gainGold")
    public static class GainGoldPatch {
        @SpirePostfixPatch public static void Postfix() { updateShopPrices(); }
    }

}
