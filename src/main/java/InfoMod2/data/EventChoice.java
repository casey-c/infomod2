package InfoMod2.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventChoice {
    @SerializedName("name") @Expose
    private String name;

    @SerializedName("effects") @Expose
    private List<EventEffect> effects;

    public String getName() { return "[" + name + "]"; }
    public List<EventEffect> getEffects() { return effects; }
}
