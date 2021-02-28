package InfoMod2.utils.graphics;

import InfoMod2.utils.graphics.ExtraColors;
import basemod.ReflectionHacks;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.LocalizedStrings;

import java.util.HashMap;

import static com.megacrit.cardcrawl.ui.panels.ExhaustPanel.fontScale;

public class ExtraFonts {
    private static BitmapFont SMALL_ITALIC_FONT;

    public static BitmapFont smallItalicFont() {
        if (SMALL_ITALIC_FONT == null) {
            SMALL_ITALIC_FONT = prepFont(16.0f,
                    false,
                    0.9f,
                    0,
                    0,
                    ExtraColors.TEXT_BORDER_COLOR,
                    false,
                    1.0f,
                    0.9f,
                    Settings.QUARTER_TRANSPARENT_BLACK_COLOR.cpy(),
                    (int)(3.0f * Settings.scale),
                    (int)(3.0f * Settings.scale),
                    "font/ZillaSlab-RegularItalic.otf");
        }

        return SMALL_ITALIC_FONT;
    }

    private static BitmapFont MED_ITALIC_FONT_NO_SHADOW;
    public static BitmapFont medItalicFontNoShadow() {
        if (MED_ITALIC_FONT_NO_SHADOW == null) {
            MED_ITALIC_FONT_NO_SHADOW = prepFont(18.0f,
                    false,
                    0.9f,
                    0,
                    0,
                    ExtraColors.TEXT_BORDER_COLOR,
                    false,
                    1.0f,
                    0.9f,
                    new Color(0f, 0f, 0f, 0f),
                    (int)(3.0f * Settings.scale),
                    (int)(3.0f * Settings.scale),
                    "font/ZillaSlab-RegularItalic.otf");
        }

        return MED_ITALIC_FONT_NO_SHADOW;
    }
//
//    public static BitmapFont largeNumberFont() {
//        if (LARGE_NUMBER_FONT == null) {
//            LARGE_NUMBER_FONT = prepFont(210.0f,
//                    false,
//                    0.9f,
//                    0,
//                    0,
//                    ExtraColors.PINK_BORDER_COLOR,
//                    false,
//                    8.0f,
//                    0.9f,
//                    Settings.QUARTER_TRANSPARENT_BLACK_COLOR.cpy(),
//                    (int)(3.0f * Settings.scale),
//                    (int)(3.0f * Settings.scale),
//                    "font/Kreon-Bold.ttf");
//        }
//        return LARGE_NUMBER_FONT;
//    }

    private static BitmapFont prepFont(float size,
                                       boolean isLinearFiltering,
                                       float gamma,
                                       int spaceX,
                                       int spaceY,
                                       Color borderColor,
                                       boolean borderStraight,
                                       float borderWidth,
                                       float borderGamma,
                                       Color shadowColor,
                                       int shadowOffsetX,
                                       int shadowOffsetY,
                                       String font
    ) {
        FreeTypeFontGenerator g;

        HashMap<String, FreeTypeFontGenerator> generators = (HashMap<String, FreeTypeFontGenerator>) ReflectionHacks.getPrivateStatic(FontHelper.class, "generators");
        //FileHandle fontFile = (FileHandle) ReflectionHacks.getPrivateStatic(FontHelper.class, "fontFile");

        FileHandle fontFile = Gdx.files.internal(font);

        if (generators.containsKey(fontFile.path())) {
            g = (FreeTypeFontGenerator)generators.get(fontFile.path());
        } else {
            System.out.println("ERROR: this shouldn't occur!");
            // TODO: throw an exception I guess

            g = new FreeTypeFontGenerator(fontFile);
            generators.put(fontFile.path(), g);
        }

        if (Settings.BIG_TEXT_MODE) {
            size *= 1.2F;
        }

        return prepFont(g, size, isLinearFiltering, gamma, spaceX, spaceY, borderColor, borderStraight, borderWidth, borderGamma, shadowColor, shadowOffsetX, shadowOffsetY);
    }


    private static BitmapFont prepFont(FreeTypeFontGenerator g,
                                       float size,
                                       boolean isLinearFiltering,
                                       float gamma,
                                       int spaceX,
                                       int spaceY,
                                       Color borderColor,
                                       boolean borderStraight,
                                       float borderWidth,
                                       float borderGamma,
                                       Color shadowColor,
                                       int shadowOffsetX,
                                       int shadowOffsetY
    ) {
        FreeTypeFontGenerator.FreeTypeFontParameter p = new FreeTypeFontGenerator.FreeTypeFontParameter();
        p.characters = "";
        p.incremental = true;
        p.size = Math.round(size * fontScale * Settings.scale);

        p.gamma = gamma;
        p.spaceX = spaceX;
        p.spaceY = spaceY;

        p.borderColor = borderColor;
        p.borderStraight = borderStraight;
        p.borderWidth = borderWidth;
        p.borderGamma = borderGamma;

        p.shadowColor = shadowColor;
        p.shadowOffsetX = shadowOffsetX;
        p.shadowOffsetY = shadowOffsetY;

        if (isLinearFiltering) {
            p.minFilter = Texture.TextureFilter.Linear;
            p.magFilter = Texture.TextureFilter.Linear;
        } else {
            p.minFilter = Texture.TextureFilter.Nearest;
            p.magFilter = Texture.TextureFilter.MipMapLinearNearest;
        }

        g.scaleForPixelHeight(p.size);
        BitmapFont font = g.generateFont(p);
        font.setUseIntegerPositions(!isLinearFiltering);
        font.getData().markupEnabled = true;
        if (LocalizedStrings.break_chars != null) {
            font.getData().breakChars = LocalizedStrings.break_chars.toCharArray();
        }

        return font;
    }

    // --------------------------------------------------------------------------------
    // Slightly reworked base game code

    // TODO: make sure any changes to the list of acceptable colors used in identifyColor are reflected here as well
    //   (Should refactor to ensure this statically, but right now no plans to update these colors and I'm lazy)
    //   I.E. ****DON'T JUST BLINDLY UPDATE THIS: future dummy version of me****
    public static boolean wordStartsWithPoundColor(String word) {
        if (word.startsWith("#b"))
            return true;
        else if (word.startsWith("#g"))
            return true;
        else if (word.startsWith("#p"))
            return true;
        else if (word.startsWith("#r"))
            return true;
        else if (word.startsWith("#y"))
            return true;
        else
            return false;
    }

    // TODO: for Bestiary (a past mod of mine), I actually customized this entirely to remove crashes with trailing #
    //  and to support a larger range of custom characters. Because this project is only interested in a better
    //  FontHelper.getSmartHeight() [for now, at least], I'm leaving this as is.
    //
    // TODO: make sure any changes to the list of acceptable colors used in wordStartsWithPoundColor are reflected here as well
    //   I.E. ****DON'T JUST BLINDLY UPDATE THIS: future dummy version of me****
    public static Color identifyColor(String word) {
        if (word.length() > 0 && word.charAt(0) == '#') {
            switch(word.charAt(1)) {
                case 'b':
                    return Settings.BLUE_TEXT_COLOR;
                case 'g':
                    return Settings.GREEN_TEXT_COLOR;
                case 'p':
                    return Settings.PURPLE_COLOR;
                case 'r':
                    return Settings.RED_TEXT_COLOR;
                case 'y':
                    return Settings.GOLD_COLOR;
                default:
                    return Color.WHITE;
            }
        } else {
            return Color.WHITE;
        }
    }

    // A helper struct to store a bit more detail that the base game getSmartHeight ignores.
    // This actually converts back into 1080p space here, since I'll have to do that everywhere anyway
    public static class BetterBlockDetails {
        public float fullBlockWidth;
        public float fullBlockHeight;

        public BetterBlockDetails() {}

        public BetterBlockDetails(float w, float h) {
            this.fullBlockWidth = w * Settings.scale;
            this.fullBlockHeight = h * Settings.scale;
        }
    }

    public static BetterBlockDetails getSmartSize(BitmapFont font, String msg, float lineWidth, float lineSpacing) {
        if (msg == null) {
            return new BetterBlockDetails();
        }
        // Localization related for non english languages (e.g. ZHT, JPN - though not ENG which is why I'm ignoring it)
        else if (Settings.lineBreakViaCharacter) {
            //return -getHeightForCharLineBreak(font, msg, lineWidth, lineSpacing);
            return new BetterBlockDetails();
        } else {
            float curWidth = 0.0F;
            float curHeight = 0.0F;

            GlyphLayout layout = FontHelper.layout;

            layout.setText(font, " ");
            float spaceWidth = layout.width;
            String[] var4 = msg.split(" ");
            int var5 = var4.length;

            // keep track of this now
            float maxWidth = 0.0f;

            for(int var6 = 0; var6 < var5; ++var6) {
                String word = var4[var6];
                if (word.equals("NL")) {

                    if (curWidth > maxWidth)
                        maxWidth = curWidth;

                    curWidth = 0.0F;
                    curHeight -= lineSpacing;
                } else if (word.equals("TAB")) {
                    curWidth += spaceWidth * 5.0F;
                } else {
                    // My stuff ignores the "orbs" which are basically inline images (e.g. the energy icon with #e)
                    //orb = identifyOrb(word);
                    //if (orb == null) {
                    if (!identifyColor(word).equals(Color.WHITE)) {
                        word = word.substring(2, word.length());
                    }

                    layout.setText(font, word);
                    if (curWidth + layout.width > lineWidth) {
                        curHeight -= lineSpacing;

                        if (curWidth > maxWidth)
                            maxWidth = curWidth;

                        curWidth = layout.width + spaceWidth;
                    } else {
                        curWidth += layout.width + spaceWidth;
                    }
//                    } else if (curWidth + CARD_ENERGY_IMG_WIDTH > lineWidth) {
//                        curHeight -= lineSpacing;
//                        curWidth = CARD_ENERGY_IMG_WIDTH + spaceWidth;
//                    } else {
//                        curWidth += CARD_ENERGY_IMG_WIDTH + spaceWidth;
//                    }
                }
            }

            if (curWidth > maxWidth)
                maxWidth = curWidth;

            // Figure out total height based on how far curHeight went down from start (since it's an offset originally)
            // Basically, account for the final row as well by taking one more linespacing out and then negate it all
            float totalHeight = -(curHeight - lineSpacing);

            return new BetterBlockDetails(maxWidth, totalHeight);
        }
    }
}
