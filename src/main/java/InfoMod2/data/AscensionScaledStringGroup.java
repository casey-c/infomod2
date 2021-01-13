package InfoMod2.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.List;

public class AscensionScaledStringGroup {
    @SerializedName("replaces") @Expose
    private String replaces;

    @SerializedName("values") @Expose
    private List<AscensionScaledString> values;

    public String replacingInputWithAscensionScaled(String input) {
        if (!CardCrawlGame.isInARun())
            return input;

        int currAscLevel = AbstractDungeon.ascensionLevel;

        // Find the AscensionScaledString with the lowest valid ascension level
        // e.g. if the values are for ascension levels [0, 5, 17], and curr = a6, then
        // we want 5.

        int max = 0;
        String text = "";

        for (AscensionScaledString s : values) {
            // Check if this is a valid scaling
            if (s.min_asc <= currAscLevel) {
                // The largest so far
                if (s.min_asc >= max) {
                    max = s.min_asc;
                    text = s.text;
                }
            }
        }

        // Now replace the string correctly
        return input.replace(replaces, text);
    }
}
