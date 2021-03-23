package InfoMod2.data;

import InfoMod2.utils.graphics.ExtraColors;
import com.badlogic.gdx.graphics.Color;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

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

//    // Getters
//    public String getName() { return name; }
//    public String getFloorString() {
//        return "Floors " + min_floor + " - " + max_floor + ". ";
//    }
//
//    public List<EventRequirement> getRequirements() { return requirements; }
//    public List<EventChoice> getChoices() { return choices; }
//    public String getNotes() { return notes; }

//    // Checkers
//    // TODO: may need to do something fancy for start of act2/3 events - e.g. since you're still on the boss chest floor
//    //   and looking at the upcoming map, you should be able to see the act2/3 events as possible even if they say no
//    public boolean isFloorNumSatisfied() {
//        if (CardCrawlGame.isInARun()) {
//            int floor = AbstractDungeon.floorNum;
//            return floor >= min_floor && floor <= max_floor;
//        }
//        return false;
//    }
//
//    // Checks if all requirements (including floor number) are met
//    public boolean isEventPossible() {
//        if (!isFloorNumSatisfied())
//            return false;
//        else {
//            if (requirements != null) {
//                for (EventRequirement req : requirements) {
//                    if (!req.isRequirementSatisfied())
//                        return false;
//                }
//            }
//        }
//
//        return true;
//    }
//
//    public Color getFloorNumStringTextColor() {
//        return (isFloorNumSatisfied()) ? ExtraColors.EVENT_TOOLTIP_REQ_SUCCESS : ExtraColors.EVENT_TOOLTIP_REQ_FAILED;
//    }
//
//    public boolean isWide() {
//        return wide;
//    }

    public boolean hasNotes() {
        return !(notes == null || notes.equals(""));
    }

    public boolean hasRequirements() {
        return requirements != null;
    }

}
