package InfoMod2.data;

import InfoMod2.utils.ExtraColors;
import com.badlogic.gdx.graphics.Color;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.LinkedList;
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
    public String getFloorString() {
        return "Floors " + min_floor + " - " + max_floor + ". ";
    }

    public List<EventIntegerRequirement> getRequirements() { return requirements; }
    public List<EventChoice> getChoices() { return choices; }
    public String getNotes() { return notes; }

    // Checkers
    // TODO: may need to do something fancy for start of act2/3 events - e.g. since you're still on the boss chest floor
    //   and looking at the upcoming map, you should be able to see the act2/3 events as possible even if they say no
    public boolean isFloorNumSatisfied() {
        if (CardCrawlGame.isInARun()) {
            int floor = AbstractDungeon.floorNum;
            return floor >= min_floor && floor <= max_floor;
        }
        return false;
    }

    // Checks if all requirements (including floor number) are met
    public boolean isEventPossible() {
        if (!isFloorNumSatisfied())
            return false;
        else {
            for (EventIntegerRequirement req : requirements) {
                if (!req.isRequirementSatisfied())
                    return false;
            }
        }

        return true;
    }

    public Color getFloorNumStringTextColor() {
        return (isFloorNumSatisfied()) ? ExtraColors.EVENT_TOOLTIP_REQ_SUCCESS : ExtraColors.EVENT_TOOLTIP_REQ_FAILED;
    }

}
