package InfoMod2.ui.widgets.tooltips.settings;

import InfoMod2.ui.widgets.tooltips.groups.SettingsTips;
import basemod.BaseMod;
import basemod.interfaces.OnCardUseSubscriber;
import basemod.interfaces.OnStartBattleSubscriber;
import basemod.interfaces.PostEnergyRechargeSubscriber;
import basemod.interfaces.StartGameSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

@SpireInitializer
public class MiscStatsUpdater implements OnCardUseSubscriber, StartGameSubscriber, OnStartBattleSubscriber, PostEnergyRechargeSubscriber {
    public static void initialize() { new MiscStatsUpdater(); }

    public MiscStatsUpdater() {
        BaseMod.subscribe(this);
    }

    @Override
    public void receiveCardUsed(AbstractCard abstractCard) {
        SettingsTips.playCard();

    }

    @Override
    public void receiveStartGame() {
        //SettingsTips.startRun();
        System.out.println("RECV START GAME");
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
        SettingsTips.startCombat();
    }

    @Override
    public void receivePostEnergyRecharge() {
        SettingsTips.startTurn();
    }
}
