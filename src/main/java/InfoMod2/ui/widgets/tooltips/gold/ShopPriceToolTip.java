package InfoMod2.ui.widgets.tooltips.gold;

import InfoMod2.ui.widgets.tooltips.TitledToolTip;
import InfoMod2.utils.graphics.ExtraColors;
import InfoMod2.utils.graphics.ExtraFonts;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.shop.ShopScreen;

public class ShopPriceToolTip extends TitledToolTip<ShopPriceToolTip> {
    public ShopPriceToolTip() {
        super(636, 334, "Shop Prices", "Sale price is 50% off. Colorless cards are 20% more expensive.");
    }

    // TODO
    // NOTES: Asc. 16+ has "shops are more costly" to also take into account
    // to future me: don't forget things like the 50 gold remove, courier, and membership card as well
    // also: discounts with ceramic fish?

    private String cardRemovalText,
            commonRelicText, commonCardText, commonPotionText,
            uncCardText, uncRelicText, uncPotionText,
            rareCardText, rareRelicText, rarePotionText,
            shopRelicText ;

    // TODO
    private Color rareRelicColor, uncRelicColor, commonRelicColor;
    private Color rareCardColor, uncCardColor, commonCardColor;
    private Color rarePotionColor, uncPotionColor, commonPotionColor;

//    private String cardRemovalText = "Card Removal: 75";

    // (150/150, 250, 300) +/- 5%
//    private String commonRelicText = "143 - 158";
//    private String shopRelicText = "143 - 158";
//    private String uncRelicText = "238 - 263";
//    private String rareRelicText = "285 - 315";

    // (50, 75, 150) +/- 10%
//    private String commonCardText = "45 - 55";
//    private String uncCardText = "67 - 83";
//    private String rareCardText = "135 - 165";

    // (50, 75, 100) +/- 5%
//    private String commonPotionText = "47 - 53";
//    private String uncPotionText = "71 - 79";
//    private String rarePotionText = "95 - 105";

    private String formatRange(float base, float adjust, float multiplier) {
        final int min = MathUtils.round(base * (1.0f - adjust) * multiplier);
        final int max = MathUtils.round(base * (1.0f + adjust) * multiplier);

        return String.format("%d - %d", min, max);
    }

    private Color setAffordabilityColor(float base, float adjust, float multiplier, Color ifAffordable) {
        float price = base * (1.0f + adjust) * multiplier;
        if (AbstractDungeon.player != null && AbstractDungeon.player.gold >= MathUtils.round(price))
            return ifAffordable;
        else
            return ExtraColors.DISABLED_GRAY;
    }

    public void updatePrices() {
        if (!CardCrawlGame.isInARun())
            return;

        final float commonRelicCost = 150;
        final float uncommonRelicCost = 250;
        final float rareRelicCost = 300;
        final float relicAdjust = 0.05f;

        final float commonCardCost = 50;
        final float uncommonCardCost = 75;
        final float rareCardCost = 150;
        final float cardAdjust = 0.10f;

        final float commonPotionCost = 50;
        final float uncommonPotionCost = 75;
        final float rarePotionCost = 100;
        final float potionAdjust = 0.05f;

        // TODO: might need to pull directly from ShopScreen.purgeCost and compute ourselves, depending on the update
        //   order
        float purgeCost = ShopScreen.purgeCost;

        // Calculate relic discounts / asc specific prices etc.
        float additionalPriceAdjust = 1.0f;
        if (AbstractDungeon.ascensionLevel >= 16)
            additionalPriceAdjust = 1.1f;

        AbstractPlayer player = AbstractDungeon.player;
        if (player != null) {
            if (player.hasRelic("The Courier")) {
                additionalPriceAdjust *= 0.8f;
                purgeCost *= 0.8f;
            }
            if (player.hasRelic("Membership Card")) {
                additionalPriceAdjust *= 0.5f;
                purgeCost *= 0.5f;
            }
            if (player.hasRelic("Smiling Mask"))
                purgeCost = 50;
        }

        // Update the strings
        this.commonCardText = formatRange(commonCardCost, cardAdjust, additionalPriceAdjust);
        this.uncCardText = formatRange(uncommonCardCost, cardAdjust, additionalPriceAdjust);
        this.rareCardText = formatRange(rareCardCost, cardAdjust, additionalPriceAdjust);

        this.shopRelicText = this.commonRelicText = formatRange(commonRelicCost, relicAdjust, additionalPriceAdjust);
        this.uncRelicText = formatRange(uncommonRelicCost, relicAdjust, additionalPriceAdjust);
        this.rareRelicText = formatRange(rareRelicCost, relicAdjust, additionalPriceAdjust);

        this.commonPotionText = formatRange(commonPotionCost, potionAdjust, additionalPriceAdjust);
        this.uncPotionText = formatRange(uncommonPotionCost, potionAdjust, additionalPriceAdjust);
        this.rarePotionText = formatRange(rarePotionCost, potionAdjust, additionalPriceAdjust);

        this.cardRemovalText = String.format("Card Removal: %d", MathUtils.round(purgeCost));

        // Update colors
        // TODO: show different color if player can afford
        // TODO: would then need to update whenever gold amt changes
        commonRelicColor = setAffordabilityColor(commonRelicCost, relicAdjust, additionalPriceAdjust, ExtraColors.QUAL_YELLOW);
        uncRelicColor = setAffordabilityColor(uncommonRelicCost, relicAdjust, additionalPriceAdjust, ExtraColors.QUAL_YELLOW);
        rareRelicColor = setAffordabilityColor(rareRelicCost, relicAdjust, additionalPriceAdjust, ExtraColors.QUAL_YELLOW);

        commonCardColor = setAffordabilityColor(commonCardCost, cardAdjust, additionalPriceAdjust, ExtraColors.QUAL_GREEN);
        uncCardColor = setAffordabilityColor(uncommonCardCost, cardAdjust, additionalPriceAdjust, ExtraColors.QUAL_GREEN);
        rareCardColor = setAffordabilityColor(rareCardCost, cardAdjust, additionalPriceAdjust, ExtraColors.QUAL_GREEN);

        commonPotionColor = setAffordabilityColor(commonPotionCost, potionAdjust, additionalPriceAdjust, ExtraColors.QUAL_BLUE);
        uncPotionColor = setAffordabilityColor(uncommonPotionCost, potionAdjust, additionalPriceAdjust, ExtraColors.QUAL_BLUE);
        rarePotionColor = setAffordabilityColor(rarePotionCost, potionAdjust, additionalPriceAdjust, ExtraColors.QUAL_BLUE);

//        rareRelicColor = uncRelicColor = commonRelicColor = ExtraColors.QUAL_YELLOW;
//        rareCardColor = uncCardColor = commonCardColor = ExtraColors.QUAL_GREEN;
//        rarePotionColor = uncPotionColor = commonPotionColor = ExtraColors.QUAL_BLUE;
    }


    @Override
    protected float renderTitle(SpriteBatch sb, float left, float top) {
        float cardRemovalLeft = left + 387 + 51;

        FontHelper.renderFontLeftTopAligned(sb,
                ExtraFonts.smallItalicFont(),
                cardRemovalText,
                cardRemovalLeft * Settings.xScale,
                (top - 5.0f) * Settings.yScale,
                ExtraColors.QUAL_RED);

        return super.renderTitle(sb, left, top);
    }

    private static final float HORIZONTAL_GAP = 128 + 39;
    private static final float VERTICAL_GAP = 47;

    @Override
    protected void renderContent(SpriteBatch sb, float left, float top) {
        final float commonLeft = left + 135;
        final float uncommonLeft = commonLeft + HORIZONTAL_GAP;
        final float rareLeft = uncommonLeft + HORIZONTAL_GAP;

        final float descTop = top - 30;
        final float relicTop = descTop - VERTICAL_GAP;
        final float cardTop = relicTop - VERTICAL_GAP;
        final float potionTop = cardTop - VERTICAL_GAP;
        //final float shopTop = potionTop - VERTICAL_GAP;

//        // Top Header
//        FontHelper.renderFontLeftTopAligned(sb, ExtraFonts.smallItalicFont(), "Relics", relicLeft * Settings.xScale, descTop * Settings.yScale, ExtraColors.TOOLTIP_TEXT_GRAY);
//        FontHelper.renderFontLeftTopAligned(sb, ExtraFonts.smallItalicFont(), "Cards", cardLeft * Settings.xScale, descTop * Settings.yScale, ExtraColors.TOOLTIP_TEXT_GRAY);
//        FontHelper.renderFontLeftTopAligned(sb, ExtraFonts.smallItalicFont(), "Potions", potionLeft * Settings.xScale, descTop * Settings.yScale, ExtraColors.TOOLTIP_TEXT_GRAY);
//
//        // Rare row
//        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Rare:", left * Settings.xScale, rareTop * Settings.yScale, ExtraColors.QUAL_YELLOW);
//        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, rareRelicText, relicLeft * Settings.xScale, rareTop * Settings.yScale, ExtraColors.QUAL_YELLOW);
//        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, rareCardText, cardLeft * Settings.xScale, rareTop * Settings.yScale, ExtraColors.QUAL_YELLOW);
//        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, rarePotionText, potionLeft * Settings.xScale, rareTop * Settings.yScale, ExtraColors.QUAL_YELLOW);
//
//        // Uncommon row
//        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Uncommon:", left * Settings.xScale, uncommonTop * Settings.yScale, ExtraColors.TOOLTIP_TEXT_POTION_3);
//        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, uncRelicText, relicLeft * Settings.xScale, uncommonTop * Settings.yScale, ExtraColors.TOOLTIP_TEXT_POTION_3);
//        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, uncCardText, cardLeft * Settings.xScale, uncommonTop * Settings.yScale, ExtraColors.TOOLTIP_TEXT_POTION_3);
//        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, uncPotionText, potionLeft * Settings.xScale, uncommonTop * Settings.yScale, ExtraColors.TOOLTIP_TEXT_POTION_3);
//
//        // Common row
//        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Common:", left * Settings.xScale, commonTop * Settings.yScale, ExtraColors.QUAL_BEIGE);
//        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, commonRelicText, relicLeft * Settings.xScale, commonTop * Settings.yScale, ExtraColors.QUAL_BEIGE);
//        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, commonCardText, cardLeft * Settings.xScale, commonTop * Settings.yScale, ExtraColors.QUAL_BEIGE);
//        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, commonPotionText, potionLeft * Settings.xScale, commonTop * Settings.yScale, ExtraColors.QUAL_BEIGE);
//
//        // Shop relic
//        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Shop:", left * Settings.xScale, shopTop * Settings.yScale, ExtraColors.QUAL_GREEN);
//        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, shopRelicText, relicLeft * Settings.xScale, shopTop * Settings.yScale, ExtraColors.QUAL_GREEN);

        // Top Header
        FontHelper.renderFontLeftTopAligned(sb, ExtraFonts.smallItalicFont(), "Common / Shop", commonLeft * Settings.xScale, descTop * Settings.yScale, ExtraColors.TOOLTIP_TEXT_GRAY);
        FontHelper.renderFontLeftTopAligned(sb, ExtraFonts.smallItalicFont(), "Uncommon", uncommonLeft * Settings.xScale, descTop * Settings.yScale, ExtraColors.TOOLTIP_TEXT_GRAY);
        FontHelper.renderFontLeftTopAligned(sb, ExtraFonts.smallItalicFont(), "Rare", rareLeft * Settings.xScale, descTop * Settings.yScale, ExtraColors.TOOLTIP_TEXT_GRAY);

        // Relics row
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Relics:", left * Settings.xScale, relicTop * Settings.yScale, ExtraColors.QUAL_YELLOW);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, commonRelicText, commonLeft * Settings.xScale, relicTop * Settings.yScale, commonRelicColor);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, uncRelicText, uncommonLeft * Settings.xScale, relicTop * Settings.yScale, uncRelicColor);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, rareRelicText, rareLeft * Settings.xScale, relicTop * Settings.yScale, rareRelicColor);

        // Cards row
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Cards:", left * Settings.xScale, cardTop * Settings.yScale, ExtraColors.QUAL_GREEN);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, commonCardText, commonLeft * Settings.xScale, cardTop * Settings.yScale, commonCardColor);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, uncCardText, uncommonLeft * Settings.xScale, cardTop * Settings.yScale, uncCardColor);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, rareCardText, rareLeft * Settings.xScale, cardTop * Settings.yScale, rareCardColor);

        // Potions row
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Potions:", left * Settings.xScale, potionTop * Settings.yScale, ExtraColors.QUAL_BLUE);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, commonPotionText, commonLeft * Settings.xScale, potionTop * Settings.yScale, commonPotionColor);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, uncPotionText, uncommonLeft * Settings.xScale, potionTop * Settings.yScale, uncPotionColor);
        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, rarePotionText, rareLeft * Settings.xScale, potionTop * Settings.yScale, rarePotionColor);

//        // Shop relic
//        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, "Shop:", left * Settings.xScale, shopTop * Settings.yScale, ExtraColors.QUAL_GREEN);
//        FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, shopRelicText, commonLeft * Settings.xScale, shopTop * Settings.yScale, ExtraColors.QUAL_GREEN);
    }
}
