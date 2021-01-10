package InfoMod2.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventScalingInteger {
    @SerializedName("asc") @Expose
    private int min_asc;

    @SerializedName("val") @Expose
    private int val;

    public int getMinAsc() { return min_asc; }
    public int getVal() { return val; }
}
