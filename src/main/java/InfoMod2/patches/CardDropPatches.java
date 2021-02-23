package InfoMod2.patches;

import InfoMod2.ui.widgets.tooltips.groups.DeckTips;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class CardDropPatches {
    private static void update() {
        DeckTips.updateCardDrops();
    }

    @SpirePatch( clz = AbstractDungeon.class, method = "loadSeeds" )
    public static class AbstractDungeonLoadSeedsPatch {
        @SpirePostfixPatch public static void Postfix() { update(); }
    }

    @SpirePatch( clz = AbstractDungeon.class, method = "getColorlessRewardCards" )
    public static class AbstractDungeonGetColorlessRewardCardsPatch {
        @SpirePostfixPatch public static void Postfix() { update(); }
    }

    @SpirePatch( clz = AbstractDungeon.class, method = "getRewardCards" )
    public static class AbstractDungeonGetRewardCardsPatch {
        @SpirePostfixPatch public static void Postfix() { update(); }
    }

    @SpirePatch( clz = AbstractDungeon.class, method = "reset" )
    public static class AbstractDungeonResetPatch {
        @SpirePostfixPatch public static void Postfix() { update(); }
    }
}
