package InfoMod2.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventDetail {
    @SerializedName("name") @Expose
    protected String name;

    @SerializedName("id") @Expose
    protected String id;

    @SerializedName("min_floor") @Expose
    protected int min_floor;

    @SerializedName("max_floor") @Expose
    protected int max_floor;

    @SerializedName("requirements") @Expose
    protected List<EventIntegerRequirement> requirements;

    @SerializedName("choices") @Expose
    protected List<EventChoice> choices;

    @SerializedName("notes") @Expose
    protected String notes;

    // Getters
    public String getName() { return name; }
}
