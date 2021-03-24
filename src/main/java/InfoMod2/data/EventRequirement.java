package InfoMod2.data;

import InfoMod2.utils.graphics.ExtraColors;
import com.badlogic.gdx.graphics.Color;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;
import java.util.List;

public class EventRequirement {
    // TODO: deeply nested reqs won't be rendered nicely since I've only implemented it for one depth of ANY (for Moai Head)
    //   and most of these are literally unused/overengineered anyway RIP. (only use gold, relic, leq%HP, and cards i think?
    //   maybe 1 or two more but i forget)
    private enum RequirementType {
        AT_LEAST_X_GOLD,
        AT_LEAST_X_CURR_HP,
        LEQ_X_PERCENT_HP,
        AT_LEAST_X_MAX_HP,
        AT_LEAST_X_CARDS,
        AT_LEAST_X_RELICS,
        HAS_RELIC,
        AT_LEAST_X_PERCENT_HP,
        SPECIAL_NOTE_FOR_YOURSELF,
        HAS_REMOVABLE_CURSE,
        PLAYTIME_OVER_X,
        ANY,
        NONE;

        private boolean isIntegerRequirement() {
            return this == AT_LEAST_X_GOLD || this == AT_LEAST_X_CURR_HP || this == AT_LEAST_X_MAX_HP || this == AT_LEAST_X_CARDS || this == AT_LEAST_X_RELICS;
        }

        private boolean isFloatRequirement() { return this == AT_LEAST_X_PERCENT_HP || this == LEQ_X_PERCENT_HP || this == PLAYTIME_OVER_X;}
        private boolean isAnyRequirement() { return this == ANY; }
        private boolean isNoneRequirement() { return this == NONE; }

        private boolean isSpecialNoteForYourself() { return this == SPECIAL_NOTE_FOR_YOURSELF; }
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

    private String asPlaytimeString() {
        if (type.isFloatRequirement()) {
            float totalSeconds = asFloat();
            float minutes = totalSeconds / 60.0f;

            int minutesInteger = (int) minutes;
            int secondsInteger = (int)totalSeconds - (minutesInteger * 60);

            return String.format("%d minutes %d seconds", minutesInteger, secondsInteger);
        }
        else {
            return "0 seconds";
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
        else if (type == RequirementType.AT_LEAST_X_RELICS)
            return atLeastXRelicsSatisfied();
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
        else if (type == RequirementType.SPECIAL_NOTE_FOR_YOURSELF)
            return specialNoteForYourselfSatisfied();
        else if (type == RequirementType.HAS_REMOVABLE_CURSE)
            return hasRemovableCurse();
        else if (type == RequirementType.PLAYTIME_OVER_X)
            return playtimeOverX();
        else
            return false;
    }

    // --------------------------------------------------------------------------------

    private boolean hasRemovableCurse() {
        if (CardCrawlGame.isInARun()) {
            if (AbstractDungeon.player != null) {
                return AbstractDungeon.player.isCursed();
            }
        }

        return false;
    }

    private boolean playtimeOverX() {
        if (CardCrawlGame.isInARun()) {
            return CardCrawlGame.playtime >= asFloat();
        }
        return false;
    }

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

    private boolean atLeastXRelicsSatisfied() {
        if (CardCrawlGame.isInARun()) {
            if (AbstractDungeon.player != null && AbstractDungeon.player.relics != null) {
                return AbstractDungeon.player.relics.size() >= asInteger();
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

    private boolean specialNoteForYourselfSatisfied() {
        if (CardCrawlGame.isInARun()) {
            if (AbstractDungeon.player != null) {
                int ascensionLevel = AbstractDungeon.ascensionLevel;

                // disabled due to daily run
                if (Settings.isDailyRun) {
                    return false;
                }
                // disabled beyond a15
                if (ascensionLevel >= 15) {
                    return false;
                }
                // enabled on no asc
                if (ascensionLevel == 0) {
                    return true;
                }
                // enabled as it's less than highest unlocked asc
                if (ascensionLevel < AbstractDungeon.player.getPrefs().getInteger("ASCENSION_LEVEL")) {
                    return true;
                }
            }
        }

        return false;
    }

    // --------------------------------------------------------------------------------

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
            case AT_LEAST_X_RELICS:
                return "Requires at least " + val + " relics. ";
            case HAS_RELIC:
                return "Has relic: " + val + ". ";
            case AT_LEAST_X_PERCENT_HP:
                return "At least " + asPercentString() + " HP. ";
            case ANY:
                return getAnyText();
            case NONE:
                return getNoneText();
            case SPECIAL_NOTE_FOR_YOURSELF:
                return "Note for Yourself Enabled. ";
            case HAS_REMOVABLE_CURSE:
                return "Has removable Curse. ";
            case PLAYTIME_OVER_X:
                return "Requires at least " + asPlaytimeString() + " of playtime. ";
            default:
                return "ERROR: not a valid requirement. Please inform ojb!";
        }

    }
}
