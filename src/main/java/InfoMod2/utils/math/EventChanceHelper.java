package InfoMod2.utils.math;

import InfoMod2.utils.integration.SlayTheRelicsIntegration;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.EventHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.PowerTip;

import java.util.ArrayList;
import java.util.Vector;

public class EventChanceHelper {
    private float prMonster;
    private float prShop;
    private float prTreasure;
    private float prEvent;

    private String event, shrine, monster, shop, treasure;

    // --------------------------------------------------------------------------------

    public String getEventChance() { return event; }
    public String getShrineChance() { return shrine; }
    public String getFightChance() { return monster; }
    public String getShopChance() { return shop; }
    public String getTreasureChance() { return treasure; }

    private void updateTextCache() {
        this.event = String.format("%.02f%%", ((prEvent * 0.75f) * 100.0f));
        this.shrine = String.format("%.02f%%", ((prEvent * 0.25f) * 100.0f));
        this.monster = String.format("%.02f%%", (prMonster * 100.0f));
        this.shop = String.format("%.02f%%", (prShop * 100.0f));
        this.treasure = String.format("%.02f%%", (prTreasure * 100.0f));
    }

    // --------------------------------------------------------------------------------

    public void update() {
        // Compute probabilities
        ArrayList<Float> eventChanceList = AbstractDungeon.loading_post_combat ? EventHelper.getChancesPreRoll() : EventHelper.getChances();
        Vector<Float> eventChanceVec = new Vector<>(eventChanceList);

        // eventChanceVec: elite (useless), monster, shop, treasure
        this.prMonster = eventChanceVec.get(1);
        this.prShop = eventChanceVec.get(2);
        this.prTreasure = eventChanceVec.get(3);
        this.prEvent = 1.0f - prMonster - prShop - prTreasure;

        // JUZU BRACELET fix
        AbstractPlayer player = AbstractDungeon.player;
        if (player != null && player.hasRelic("Juzu Bracelet")) {
            this.prEvent += prMonster;
            this.prMonster = 0.0f;
        }

        updateTextCache();
    }
}
