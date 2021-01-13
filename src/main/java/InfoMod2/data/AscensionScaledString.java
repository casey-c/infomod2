package InfoMod2.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// This is basically a glorified pair - min_asc is just the lowest ascension level where this text is used. Larger
// min_asc in other members of an AscensionScaledStringGroup will simply replace the text with their own values as
// needed, letting you quickly swap out the actual text you need for the proper information.
public class AscensionScaledString {
    @SerializedName("asc") @Expose
    protected int min_asc;

    @SerializedName("text") @Expose
    protected String text;
}
