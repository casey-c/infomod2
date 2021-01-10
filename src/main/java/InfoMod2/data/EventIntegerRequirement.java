package InfoMod2.data;

import InfoMod2.ui.widgets.AbstractWidget;
import InfoMod2.utils.ExtraColors;
import com.badlogic.gdx.graphics.Color;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class EventIntegerRequirement {
    private enum IntegerRequirementType {
        AT_LEAST_X_GOLD, AT_LEAST_X_CURR_HP, AT_LEAST_X_MAX_HP, AT_LEAST_X_CARDS
    }

    @SerializedName("type") @Expose
    private IntegerRequirementType type;

    @SerializedName("val") @Expose
    private int val;

    public boolean isRequirementSatisfied() {
        if (type == IntegerRequirementType.AT_LEAST_X_GOLD)
            return isGoldRequirementSatisfied();
        else if (type == IntegerRequirementType.AT_LEAST_X_CURR_HP)
            return isCurrHPSatisfied();
        else if (type == IntegerRequirementType.AT_LEAST_X_MAX_HP)
            return isMaxHPSatisfied();
        else if (type == IntegerRequirementType.AT_LEAST_X_CARDS)
            return isDeckSizeMinSatisfied();
        else
            return false;
    }

    private boolean isGoldRequirementSatisfied() {
        if (CardCrawlGame.isInARun()) {
            if (AbstractDungeon.player != null) {
                return AbstractDungeon.player.gold >= val;
            }
        }
        return false;
    }

    private boolean isCurrHPSatisfied() {
        return false;
    }

    private boolean isMaxHPSatisfied() {
        return false;
    }

    private boolean isDeckSizeMinSatisfied() {
        return false;
    }

    public Color getTextColor() {
        return (isRequirementSatisfied()) ? ExtraColors.EVENT_TOOLTIP_REQ_SUCCESS : ExtraColors.EVENT_TOOLTIP_REQ_FAILED;
    }

    public String getText() {
        switch (type) {
            case AT_LEAST_X_GOLD:
                return "Requires " + val + " gold. ";
            case AT_LEAST_X_CURR_HP:
                return "Requires " + val + " current HP. ";
            case AT_LEAST_X_MAX_HP:
                return "Requires " + val + " max HP. ";
            case AT_LEAST_X_CARDS:
                return "Requires at least " + val + " cards. ";
            default:
                return "ERROR: Not a valid requirement - please tell ojb!";
        }

    }
}
