package InfoMod2.patches;

import InfoMod2.ui.widgets.tooltips.groups.MapTips;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.helpers.EventHelper;
import com.megacrit.cardcrawl.random.Random;

public class EventChancePatches {
    private static void update() {
        MapTips.updateEventChanceTip();
    }

    // Update whenever we roll to find what an event is
    @SpirePatch( clz = EventHelper.class, method = "roll", paramtypez = { Random.class } )
    public static class EventHelperRollPatch {
        @SpirePostfixPatch public static void Postfix() { update(); }
    }

    // Update whenever we reset probabilities
    @SpirePatch( clz = EventHelper.class, method = "resetProbabilities" )
    public static class EventHelperResetProbabilitiesPatch {
        @SpirePostfixPatch public static void Postfix() { update(); }
    }

    // Update whenever we manually set probabilities (e.g. loading from save file)
    @SpirePatch( clz = EventHelper.class, method = "setChances" )
    public static class EventHelperSetChancesPatch {
        @SpirePostfixPatch public static void Postfix() { update(); }
    }
}
