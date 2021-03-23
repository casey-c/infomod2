package InfoMod2.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventDetail {
    @SerializedName("name") @Expose
    public String name;

    @SerializedName("id") @Expose
    public String id;

    @SerializedName("min_floor") @Expose
    public int min_floor;

    @SerializedName("max_floor") @Expose
    public int max_floor;

    @SerializedName("requirements") @Expose
    public List<EventRequirement> requirements;

    @SerializedName("choices") @Expose
    public List<EventChoice> choices;

    @SerializedName("notes") @Expose
    public String notes;

    @SerializedName("wide") @Expose
    public boolean wide;

    // These are probably not necessary though originally intended to avoid null crashes
    public boolean hasNotes() {
        return !(notes == null || notes.equals(""));
    }
    public boolean hasRequirements() {
        return requirements != null;
    }
}
