package InfoMod2.ui.widgets.tooltips.settings;

import InfoMod2.ui.widgets.tooltips.groups.SettingsTips;
import basemod.BaseMod;
import basemod.interfaces.*;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

@SpireInitializer
public class MiscStatsUpdater implements OnCardUseSubscriber,
        OnStartBattleSubscriber,
        PostEnergyRechargeSubscriber,
        PostDungeonInitializeSubscriber {

    public static void initialize() { new MiscStatsUpdater(); }
    public MiscStatsUpdater() {
        BaseMod.subscribe(this);
    }

    // --------------------------------------------------------------------------------

    @Override public void receivePostDungeonInitialize() { SettingsTips.startRun(); }
    @Override public void receiveOnBattleStart(AbstractRoom abstractRoom) { SettingsTips.startCombat(); }
    @Override public void receivePostEnergyRecharge() { SettingsTips.startTurn(); }

    @Override public void receiveCardUsed(AbstractCard abstractCard) { SettingsTips.playCard(); }
}
