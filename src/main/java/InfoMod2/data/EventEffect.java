package InfoMod2.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventEffect {
    private enum EffectType {
        REGULAR, ASC_SCALED
    }

    @SerializedName("text") @Expose
    private String text;

    @SerializedName("type") @Expose
    private EffectType type;

    @SerializedName("asc_scaling") @Expose
    private List<EventScalingInteger> asc_scaling;
}
