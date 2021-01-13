package InfoMod2.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AscensionScaledString {
    @SerializedName("asc") @Expose
    protected int min_asc;

    @SerializedName("text") @Expose
    protected String text;
}
