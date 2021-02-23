package InfoMod2.patches;

import InfoMod2.utils.graphics.ScreenHelper;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.ui.panels.TopPanel;

// Special thanks to blanktheevil (infinitespire)
public class ScreenPatches {
    @SpirePatch( clz = TopPanel.class, method = "update" )
    public static class TopPanelSuppressInputPatch {
        @SpirePrefixPatch
        public static SpireReturn<Void> Prefix(TopPanel __instance) {
            if (ScreenHelper.isScreenUp())
                return SpireReturn.Return(null);
            else
                return SpireReturn.Continue();
        }
    }

    @SpirePatch( clz = TipHelper.class, method = "render" )
    public static class TipSuppressorPatch {
        @SpirePrefixPatch
        public static SpireReturn<Void> Prefix(SpriteBatch sb) {
            if (ScreenHelper.isScreenUp())
                return SpireReturn.Return(null);
            else
                return SpireReturn.Continue();
        }
    }

    @SpirePatch( clz = AbstractDungeon.class, method = "update" )
    public static class AbstractDungeonUpdateSuppressPatch {
        @SpirePrefixPatch
        public static SpireReturn<Void> Prefix(AbstractDungeon __instance) {
            ScreenHelper.update();

            if (ScreenHelper.isScreenUp())
                return SpireReturn.Return(null);
            else
                return SpireReturn.Continue();
        }
    }
}
