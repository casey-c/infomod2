package InfoMod2.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Arrays;
import java.util.List;

public class EventEffect {
    @SerializedName("text") @Expose
    private String text;

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
}
