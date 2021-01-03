package InfoMod2.patches;

import InfoMod2.ui.DeckTip;
import InfoMod2.ui.EventChanceTip;
import InfoMod2.ui.MapTips;
import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputActionSet;
import com.megacrit.cardcrawl.ui.panels.TopPanel;

public class ToolTipPatches {
    private static boolean HIDE_TIPS = false;

    @SpirePatch(
            clz = TopPanel.class,
            method = "renderTopRightIcons"
    )
    public static class TopPanelRenderTopRightPreventionPatch {
        private static final float TOP_RIGHT_TIP_X = 1550.0f * Settings.scale;
        private static final float TIP_Y = (float)Settings.HEIGHT - 120.0f * Settings.scale;

        @SpirePrefixPatch
        public static void Prefix(TopPanel _instance, SpriteBatch _sb) {
            HIDE_TIPS = true;
        }

        @SpirePostfixPatch
        public static void Postfix(TopPanel instance, SpriteBatch sb) {
            HIDE_TIPS = false;

            // Force show the settings tool tip

            //private boolean settingsButtonDisabled = true;
            boolean settingsButtonDisabled = (boolean) ReflectionHacks.getPrivate(instance, TopPanel.class, "settingsButtonDisabled");
            boolean mapButtonDisabled = (boolean) ReflectionHacks.getPrivate(instance, TopPanel.class, "mapButtonDisabled");
            boolean deckButtonDisabled = (boolean) ReflectionHacks.getPrivate(instance, TopPanel.class, "deckButtonDisabled");

            if (!settingsButtonDisabled && instance.settingsHb.hovered && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.SETTINGS && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.INPUT_SETTINGS) {
                TipHelper.renderGenericTip(TOP_RIGHT_TIP_X, TIP_Y, TopPanel.LABEL[0] + " (" + InputActionSet.cancel.getKeyString() + ")", TopPanel.MSG[0]);
            }

            // Show my custom map tool tip
            if (!mapButtonDisabled && instance.mapHb.hovered && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.MASTER_DECK_VIEW) {
                MapTips.renderCustomMapTips(sb);
            }

            // Show my custom deck tool tip
            if (!deckButtonDisabled && instance.deckHb.hovered && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.MASTER_DECK_VIEW) {
                DeckTip.renderCustomMapTip(sb);
            }
        }

    }

    @SpirePatch(
            clz = TipHelper.class,
            method = "renderGenericTip"
    )
    public static class TipHelperPreventionPatch {
        @SpirePrefixPatch
        public static SpireReturn<Void> Prefix(float _x, float _y, String _header, String _body) {
            return (HIDE_TIPS) ? SpireReturn.Return(null) : SpireReturn.Continue();
        }
    }

    //public static void renderGenericTip(float x, float y, String header, String body) {
}
