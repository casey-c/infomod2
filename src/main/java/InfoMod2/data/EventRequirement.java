package InfoMod2.data;

import InfoMod2.utils.ExtraColors;
import com.badlogic.gdx.graphics.Color;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;
import java.util.List;

public class EventRequirement {
    // TODO: deeply nested reqs won't be rendered nicely since I've only implemented it for one depth of ANY (for Moai Head)
    //   and most of these are literally unused/overengineered anyway RIP. (only use gold, relic, leq%HP, and cards i think?
    //   maybe 1 or two more but i forget)
    private enum RequirementType {
        AT_LEAST_X_GOLD, AT_LEAST_X_CURR_HP, LEQ_X_PERCENT_HP, AT_LEAST_X_MAX_HP, AT_LEAST_X_CARDS, HAS_RELIC, AT_LEAST_X_PERCENT_HP, ANY, NONE;

        private boolean isIntegerRequirement() {
            return this == AT_LEAST_X_GOLD || this == AT_LEAST_X_CURR_HP || this == AT_LEAST_X_MAX_HP || this == AT_LEAST_X_CARDS;
        }

        private boolean isFloatRequirement() {
            return this == AT_LEAST_X_PERCENT_HP || this == LEQ_X_PERCENT_HP;
        }

        private boolean isAnyRequirement() {
            return this == ANY;
        }

        private boolean isNoneRequirement() {
            return this == NONE;
        }
    }


    @SerializedName("type") @Expose
    private RequirementType type;

    @SerializedName("val") @Expose
    private String val;

    @SerializedName("of") @Expose
    private List<EventRequirement> children;

    // --------------------------------------------------------------------------------
    // Versions of val that are used for certain types
    // (NOTE: this is a very poor way of handling the deserialization, but it's easier than writing a custom deserializer)
    // --------------------------------------------------------------------------------

    // TODO: checking type.isXYZ is probably redundant if we just assume asInteger is always an integer type when called

    private int asInteger() {
        if (type.isIntegerRequirement()) {
            try {
                return Integer.parseInt(val);
            }
            catch (Exception e) {
                System.out.println("ERROR: not a valid integer for requirement " + type + " and val " + val);
                return 0;
            }
        }
        else {
            return 0;
        }
    }
    private float asFloat() {
        if (type.isFloatRequirement()) {
            try {
                return Float.parseFloat(val);
            }
            catch (Exception e) {
                System.out.println("ERROR: not a valid float for requirement " + type + " and val " + val);
                return 0.0f;
            }
        }
        else {
            return 0.0f;
        }
    }

    private String asPercentString() {
        if (type.isFloatRequirement()) {
            return String.format("%.1f", asFloat() * 100.0f) + "%";
        }
        else {
            return "0.0%";
        }
    }

    // --------------------------------------------------------------------------------

    public boolean isRequirementSatisfied() {
        if (type == RequirementType.AT_LEAST_X_GOLD)
            return atLeastXGoldSatisfied();
        else if (type == RequirementType.AT_LEAST_X_CURR_HP)
            return atLeastXCurrHPSatisfied();
        else if (type == RequirementType.AT_LEAST_X_MAX_HP)
            return atLeastXMaxHPSatisfied();
        else if (type == RequirementType.AT_LEAST_X_CARDS)
            return atLeastXCardsSatisfied();
        else if (type == RequirementType.AT_LEAST_X_PERCENT_HP)
            return atLeastXPercentHPSatisfied();
        else if (type == RequirementType.HAS_RELIC)
            return hasRelicSatisfied();
        else if (type == RequirementType.ANY)
            return anyChildSatisfied();
        else if (type == RequirementType.NONE)
            return noChildSatisfied();
        else if (type == RequirementType.LEQ_X_PERCENT_HP)
            return leqXPercentHPSatisfied();
        else
            return false;
    }

    // --------------------------------------------------------------------------------

    private boolean atLeastXGoldSatisfied() {
        if (CardCrawlGame.isInARun()) {
            if (AbstractDungeon.player != null) {
                return AbstractDungeon.player.gold >= asInteger();
            }
        }
        return false;
    }

    private boolean atLeastXCurrHPSatisfied() {
        if (CardCrawlGame.isInARun()) {
            if (AbstractDungeon.player != null) {
                return AbstractDungeon.player.currentHealth >= asInteger();
            }
        }
        return false;
    }

    private boolean atLeastXMaxHPSatisfied() {
        if (CardCrawlGame.isInARun()) {
            if (AbstractDungeon.player != null) {
                return AbstractDungeon.player.maxHealth >= asInteger();
            }
        }
        return false;
    }

    private boolean atLeastXCardsSatisfied() {
        if (CardCrawlGame.isInARun()) {
            if (AbstractDungeon.player != null && AbstractDungeon.player.masterDeck != null) {
                return AbstractDungeon.player.masterDeck.size() >= asInteger();
            }
        }
        return false;
    }

    private boolean atLeastXPercentHPSatisfied() {
        if (CardCrawlGame.isInARun()) {
            if (AbstractDungeon.player != null) {
                float percentHP = (float)AbstractDungeon.player.currentHealth / (float)AbstractDungeon.player.maxHealth;
                return percentHP >= asFloat();
            }
        }
        return false;
    }

    private boolean leqXPercentHPSatisfied() {
        if (CardCrawlGame.isInARun()) {
            if (AbstractDungeon.player != null) {
                float percentHP = (float)AbstractDungeon.player.currentHealth / (float)AbstractDungeon.player.maxHealth;
                return percentHP <= asFloat();
            }
        }
        return false;
    }

    private boolean hasRelicSatisfied() {
        if (CardCrawlGame.isInARun()) {
            if (AbstractDungeon.player != null) {
                ArrayList<AbstractRelic> relics = AbstractDungeon.player.relics;
                if (relics != null) {
                    for (AbstractRelic relic : relics) {
                        if (relic.name.equals(val))
                            return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean anyChildSatisfied() {
        if (children != null) {
            for (EventRequirement req : children) {
                if (req.isRequirementSatisfied())
                    return true;
            }
        }

        return false;
    }

    private boolean noChildSatisfied() {
        if (children != null) {
            for (EventRequirement req : children) {
                if (req.isRequirementSatisfied())
                    return false;
            }
        }

        return true;
    }

    // --------------------------------------------------------------------------------

    public Color getTextColor() {
        return (isRequirementSatisfied()) ? ExtraColors.EVENT_TOOLTIP_REQ_SUCCESS : ExtraColors.EVENT_TOOLTIP_REQ_FAILED;
    }

    private String getAnyText() {
        if (children != null) {
            StringBuilder sb = new StringBuilder("Any of: [ ");
            for (EventRequirement c : children) {
                sb.append(c.getText());
            }
            sb.append("]. ");
            return sb.toString();
        }
        else {
            return "ERROR: Malformed requirement - please inform ojb!";
        }
    }

    private String getNoneText() {
        if (children != null) {
            StringBuilder sb = new StringBuilder("None of: [ ");
            for (EventRequirement c : children) {
                sb.append(c.getText());
            }
            sb.append("]. ");
            return sb.toString();
        }
        else {
            return "ERROR: Malformed requirement - please inform ojb!";
        }
    }

    public String getText() {
        switch (type) {
            case AT_LEAST_X_GOLD:
                return "Requires " + val + " gold. ";
            case AT_LEAST_X_CURR_HP:
                return "Requires " + val + " current HP. ";
            case LEQ_X_PERCENT_HP:
                return "No more than " + asPercentString() + " HP. ";
            case AT_LEAST_X_MAX_HP:
                return "Requires " + val + " max HP. ";
            case AT_LEAST_X_CARDS:
                return "Requires at least " + val + " cards. ";
            case HAS_RELIC:
                return "Has relic: " + val + ". ";
            case AT_LEAST_X_PERCENT_HP:
                return "At least " + asPercentString() + " HP. ";
            case ANY:
                return getAnyText();
            case NONE:
                return getNoneText();
            default:
                return "ERROR: not a valid requirement. Please inform ojb!";
        }

    }
}
