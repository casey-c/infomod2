package InfoMod2.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Arrays;
import java.util.List;

public class EventEffect {
//    private enum EffectType {
//        REGULAR, ASC_SCALED
//    }

    @SerializedName("text") @Expose
    private String text;

//    @SerializedName("type") @Expose
//    private EffectType type;

//    @SerializedName("asc_scaling") @Expose
//    private List<EventScalingInteger> asc_scaling;
    @SerializedName("asc_scaling") @Expose
    private List<AscensionScaledStringGroup> asc_scaling;

    public String getText() {
        String output = text;
        if (asc_scaling != null) {
            for (AscensionScaledStringGroup g : asc_scaling)
                output = g.replacingInputWithAscensionScaled(output);
        }
        return output;
    }

//    public String getText() {
//        if (type == EffectType.REGULAR) {
//            return text;
//        }
//        else {
//            // Need to replace all of the $ in the text string with the values of the asc_scaling
//            // TODO
//
//            // E.g.:
//            // 'Lose $ gold (Required minimum).'
//            // -> ['Lose ', ' gold (Required minimum).']
//
//            String[] split = text.split("\\$");
//
//            String ascValue = "" + getValForCurrentAsc();
//
//            return String.join(ascValue, split);
//        }
//    }

    // Finds the smallest value in the ascension scaled list such that the ascension level is properly obtained.
    // TODO: still bugged lol
//    public int getValForCurrentAsc() {
//        if (!CardCrawlGame.isInARun())
//            return -1;
//
//        int currentAscLevel = AbstractDungeon.ascensionLevel;
//
//        int val = -1;
//        int lowestAscSceen = 100;
//
//        for (EventScalingInteger scalingInteger : asc_scaling) {
//            int asc = scalingInteger.getMinAsc();
//
//            if (asc >= currentAscLevel && asc < lowestAscSceen) {
//                lowestAscSceen = asc;
//                val = scalingInteger.getVal();
//            }
//        }
//
//        return val;
//    }
}
