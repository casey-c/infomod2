package InfoMod2.utils.math;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.Vector;

public class CardDropHelper {
    private int cbr;

    private double prRare;
    private double prRareElite;

    private double prUnc;
    private double prUncElite;

    private double prCommon;
    private double prCommonElite;

    // Cache
    private String rareText, rareEliteText, uncText, uncEliteText, commonText, commonEliteText;

    // --------------------------------------------------------------------------------

    public void setCBR(int cbr) {
        this.cbr = cbr;
        recompute();
    }

    private void updateTextCache() {
        this.rareText = String.format("%.1f%%", prRare * 100.0);
        this.rareEliteText = String.format("%.1f%%", prRareElite * 100.0);

        this.uncText = String.format("%.1f%%", prUnc * 100.0);
        this.uncEliteText = String.format("%.1f%%", prUncElite * 100.0);

        this.commonText = String.format("%.1f%%", prCommon * 100.0);
        this.commonEliteText = String.format("%.1f%%", prCommonElite * 100.0);
    }

    public String getRareChance() { return rareText; }
    public String getRareChanceElite() { return rareEliteText; }

    public String getUncommonChance() { return uncText; }
    public String getUncommonChanceElite() { return uncEliteText; }

    public String getCommonChance() { return commonText; }
    public String getCommonChanceElite() { return commonEliteText; }

    // --------------------------------------------------------------------------------

    private void recompute() {
        int numCards = 3;
        int numCardsElite = 3;

        int rareChance = 3;
        int rareChanceElite = 10;

        // Relic overrides
        AbstractPlayer player = AbstractDungeon.player;
        if (player != null) {
            if (player.hasRelic("Question Card")) {
                numCards += 1;
                numCardsElite += 1;
            }
            if (player.hasRelic("Busted Crown")) {
                numCards -= 2;
                numCardsElite -= 2;
            }
            if (player.hasRelic("Prayer Wheel")) {
                numCards *= 2;
            }
            if (player.hasRelic("Nloth's Gift")) {
                rareChance *= 3;
                rareChanceElite *= 3;
            }
        }

        // TODO: definitely room for improvement on this godawful algorithm
        //   -- just looking at it briefly, we probably don't need to do the recursive generation of the (worst case:
        //      6500) possible cases repeatedly, but do it once and look through once updating ALL the probabilities as
        //      we go. but i'm lazy, and this seems to work without too much performance hit so far, so this terrible,
        //      seemingly working approach is going to stay for now.
        this.prRare = computeCardProb(cbr, numCards, rareChance, CARD_RARITY.CARD_RARE);
        this.prRareElite = computeCardProb(cbr, numCardsElite, rareChanceElite, CARD_RARITY.CARD_RARE);

        this.prUnc = computeCardProb(cbr, numCards, rareChance, CARD_RARITY.CARD_UNCOMMON);
        this.prUncElite = computeCardProb(cbr, numCardsElite, rareChanceElite, CARD_RARITY.CARD_UNCOMMON);

        this.prCommon = computeCardProb(cbr, numCards, rareChance, CARD_RARITY.CARD_COMMON);
        this.prCommonElite = computeCardProb(cbr, numCardsElite, rareChanceElite, CARD_RARITY.CARD_COMMON);

        updateTextCache();
    }

    // --------------------------------------------------------------------------------

    private enum CARD_RARITY {
        CARD_COMMON,
        CARD_UNCOMMON,
        CARD_RARE
    }

    private static Vector<CARD_RARITY> recursiveTripleGen(int len) {
        Vector<CARD_RARITY> result = new Vector<>();

        if (len <= 1) {
            result.add(CARD_RARITY.CARD_COMMON);
            result.add(CARD_RARITY.CARD_UNCOMMON);
            result.add(CARD_RARITY.CARD_RARE);
        }
        else {
            Vector<CARD_RARITY> lower = recursiveTripleGen(len - 1);

            int stride = len - 1;
            for (int i = 0; i < lower.size(); i += stride) {
                Vector<CARD_RARITY> strided = new Vector<>();

                for (int j = 0; j < stride; ++j)
                    strided.add(lower.get(i + j));

                result.add(CARD_RARITY.CARD_COMMON);
                result.addAll(strided);

                result.add(CARD_RARITY.CARD_UNCOMMON);
                result.addAll(strided);

                result.add(CARD_RARITY.CARD_RARE);
                result.addAll(strided);
            }
        }

        return result;
    }

    private static double calcProbSequence(ArrayList<CARD_RARITY> sequence, int cbr, int rareChance) {
        double result = 1.0;

        int uncommonChance = 37;
        int combined_chance = rareChance + uncommonChance;

        for (CARD_RARITY r : sequence) {
            double pr_rare = ((double)rareChance - (double)cbr) / 100.0;
            if (pr_rare < 0.0) pr_rare = 0.0;

            double pr_uncommon = (((double)combined_chance - (double)cbr) / 100.0) - pr_rare;
            double pr_common = 1.0 - (pr_rare + pr_uncommon);

            switch(r) {
                case CARD_COMMON:
                    result *= pr_common;
                    cbr -= 1;
                    if (cbr < -40)
                        cbr = -40;
                    break;
                case CARD_UNCOMMON:
                    result *= pr_uncommon;
                    break;
                case CARD_RARE:
                    result *= pr_rare;
                    cbr = 5;
                    break;
            }
        }

        return result;
    }

    /*
      exponential brute force algorithm. generate all possibilities (e.g. common, common, common or common, rare,
      uncommon), then compute the probabilities of each, looking for only the sequences that contain the target rarity.
      this is expensive as hell, but easier to reason about than the pure probability approach (which is over my head)
      luckily the exponential run time doesn't matter when numCards <= 8 at maximum, with the all vector containing
      3^8 = ~6500 entries in that case. we try to not run this every frame obviously, but only when the rare chance
      changes (e.g. upon generating a card)
    */
    private static double computeCardProb(int cbr, int numCards, int rareChance, CARD_RARITY rarity) {
        double final_prob = 0.0;

        Vector<CARD_RARITY> all = recursiveTripleGen(numCards);

        for (int i = 0; i < all.size(); i += numCards) {
            // Get the current sequence using a stride of length numCards
            ArrayList<CARD_RARITY> sequence = new ArrayList<>();

            for (int j = 0; j < numCards; ++j)
                sequence.add(all.get(i + j));

            // Verify that this sequence contains the target rarity type
            if (!sequence.contains(rarity))
                continue;

            // Calculate the probability of this sequence occuring
            final_prob += calcProbSequence(sequence, cbr, rareChance);
        }

        return final_prob;
    }
}
