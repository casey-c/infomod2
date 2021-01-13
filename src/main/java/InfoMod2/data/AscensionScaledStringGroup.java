package InfoMod2.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.List;

public class AscensionScaledStringGroup {
    // This is for simple pattern matching on the input string (basically, it's an arbitrary identifier letting us
    // pick out places inside the text to replace with other text based on ascension level)
    // E.g., we can have a string like "Lose X gold" have a replaces of "X" which will replace the character X with the
    // proper values for each ascension level; for example, our final output might be "Lose 35 gold." on low ascensions,
    // but for ascensions past 15 it might be "Lose 50 gold.". This is a reasonably simple solution to accomplish this.
    @SerializedName("replaces") @Expose
    private String replaces;

    @SerializedName("values") @Expose
    private List<AscensionScaledString> values;

    public String replacingInputWithAscensionScaled(String input) {
        if (!CardCrawlGame.isInARun() || values == null)
            return input;

        int currAscLevel = AbstractDungeon.ascensionLevel;

        // Find the AscensionScaledString with the highest valid ascension level
        // e.g. if the values are for ascension levels [0, 5, 17], and curr = a6, then
        // we want the value for 5.

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
