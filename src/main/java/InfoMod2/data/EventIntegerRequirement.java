package InfoMod2.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventIntegerRequirement {
    private enum IntegerRequirementType {
        AT_LEAST_X_GOLD, AT_LEAST_X_CURR_HP, AT_LEAST_X_MAX_HP, AT_LEAST_X_CARDS
    }

    @SerializedName("type") @Expose
    private IntegerRequirementType type;

    @SerializedName("val") @Expose
    private int val;
}
