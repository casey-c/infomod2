package InfoMod2.utils.integration;

import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.PowerTip;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SpireInitializer
public class SlayTheRelicsIntegration {
    public static ArrayList<Hitbox> slayTheRelicsHitboxes = new ArrayList<>();
    public static ArrayList<ArrayList<PowerTip>> slayTheRelicsPowerTips = new ArrayList<>();

    // Needed for slay the relics to realize this exists (tho we don't have to instantiate anything actually)
    public static void initialize() {}

    private static final HashMap<String, Map.Entry<Hitbox, ArrayList<PowerTip>>> tipMap = new HashMap<>();

    // DEBUG
    public static void print() {
        System.out.println( "OJB: SlayTheRelics Integration -- map size " + tipMap.size() + " | hitbox list size " + slayTheRelicsHitboxes.size() + " | powertips " + slayTheRelicsPowerTips.size() );

        System.out.println("HITBOXES:");
        for (Hitbox h : slayTheRelicsHitboxes) {
            System.out.println("h.x: " + h.x + ", h.y: " + h.y + ", h.w: " + h.width + ", h.h: " + h.height + ", h.cx: " + h.cX + ", h.cy: " + h.cY);
        }

        System.out.println("\nTIPS:");
        for (ArrayList<PowerTip> list : slayTheRelicsPowerTips) {
            for (PowerTip tip : list) {
                System.out.println("tip: " + tip.header + " | " + tip.body);
            }
        }
    }

    public static void update(String id, Hitbox hb, ArrayList<PowerTip> tips) {
        // Update the hash map
        Map.Entry<Hitbox,ArrayList<PowerTip>> entry = new AbstractMap.SimpleEntry<>(hb, tips);
        tipMap.put(id, entry);

        // Update the lists
        clear();

        for (Map.Entry<Hitbox, ArrayList<PowerTip>> map_entry : tipMap.values()) {
            Hitbox map_hb = map_entry.getKey();
            ArrayList<PowerTip> map_tip = map_entry.getValue();
            add(map_hb, map_tip);
        }
    }

    // TODO: call this on character change or return to main menu?
    public static void reset() {
        tipMap.clear();
        clear();
    }

    private static void clear() {
        slayTheRelicsHitboxes.clear();
        slayTheRelicsPowerTips.clear();
    }

    private static void add(Hitbox hb, ArrayList<PowerTip> tips) {
        slayTheRelicsHitboxes.add(hb);
        slayTheRelicsPowerTips.add(tips);
    }
}
