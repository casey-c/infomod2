package InfoMod2.utils.graphics;

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

    private static BitmapFont SCREEN_SUBTITLE;
    public static BitmapFont screenSubtitle() {
        if (SCREEN_SUBTITLE == null) {
            SCREEN_SUBTITLE = prepFont(18.0f,
                    false,
                    0.9f,
                    0,
                    0,
                    //new Color(0f, 0f, 0f, 1f),
                    ExtraColors.SCREEN_SUBTITLE_TEXT,
                    //ExtraColors.TEXT_BORDER_COLOR,
                    false,
                    0.0f,
                    1.0f,
                    new Color(0f, 0f, 0f, 0f),
                    //Settings.QUARTER_TRANSPARENT_BLACK_COLOR.cpy(),
                    (int)(3.0f * Settings.scale),
                    (int)(3.0f * Settings.scale),
                    "font/ZillaSlab-RegularItalic.otf");
        }

        return SCREEN_SUBTITLE;
    }

    private static BitmapFont SCREEN_TITLE;
    public static BitmapFont screenTitle() {
        if (SCREEN_TITLE == null) {
            SCREEN_TITLE = prepFont(28.0f,
                    false,
                    0.9f,
                    0,
                    0,
                    ExtraColors.TEXT_BORDER_COLOR,
                    false,
                    0.0f,
                    0.9f,
                    //new Color(0f, 0f, 0f, 0f),
                    new Color(0f, 0f, 0f, 0.62f),
                    //Settings.QUARTER_TRANSPARENT_BLACK_COLOR.cpy(),
                    (int)(2.0f * Settings.scale),
                    (int)(2.0f * Settings.scale),
                    "font/Kreon-Regular.ttf");
        }

        return SCREEN_TITLE;
    }

    private static BitmapFont EVENT_ACT_TITLE;
    public static BitmapFont eventActTitle() {
        if (EVENT_ACT_TITLE == null) {
            EVENT_ACT_TITLE = prepFont(30.0f,
                    false,
                    0.9f,
                    0,
                    0,
                    ExtraColors.TEXT_BORDER_COLOR,
                    false,
                    0.0f,
                    0.9f,
                    //new Color(0f, 0f, 0f, 0f),
                    new Color(0f, 0f, 0f, 1.00f),
                    //Settings.QUARTER_TRANSPARENT_BLACK_COLOR.cpy(),
                    (int)(2.0f * Settings.scale),
                    (int)(2.0f * Settings.scale),
                    "font/Kreon-Regular.ttf");
        }

        return EVENT_ACT_TITLE;
    }

    private static BitmapFont EVENT_ACTIVE_COUNT;
    public static BitmapFont eventActiveCount() {
        if (EVENT_ACTIVE_COUNT == null) {
            EVENT_ACTIVE_COUNT = prepFont(24.0f,
                    false,
                    0.9f,
                    0,
                    0,
                    //new Color(0f, 0f, 0f, 1f),
                    ExtraColors.TEXT_BORDER_COLOR,
                    false,
                    0.0f,
                    1.0f,
                    new Color(0f, 0f, 0f, 0f),
                    //Settings.QUARTER_TRANSPARENT_BLACK_COLOR.cpy(),
                    (int)(3.0f * Settings.scale),
                    (int)(3.0f * Settings.scale),
                    "font/ZillaSlab-RegularItalic.otf");
        }

        return EVENT_ACTIVE_COUNT;
    }

    private static BitmapFont SMALLER_TIP_BODY;
    public static BitmapFont smallerTipBody() {
        if (SMALLER_TIP_BODY == null) {
            SMALLER_TIP_BODY = prepFont(18.0f,
                    false,
                    0.9f,
                    0,
                    0,
                    ExtraColors.TEXT_BORDER_COLOR,
                    false,
                    0.0f,
                    0.9f,
                    new Color(0f, 0f, 0f, 0.05f),
                    //new Color(0f, 0f, 0f, 1.00f),
                    //Settings.QUARTER_TRANSPARENT_BLACK_COLOR.cpy(),
                    (int)(3.0f * Settings.scale),
                    (int)(3.0f * Settings.scale),
                    "font/Kreon-Regular.ttf");
        }

        return SMALLER_TIP_BODY;
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

    // --------------------------------------------------------------------------------

    public static class SizeHelper {
        public float blockWidth;
        public float blockHeight;

        public SizeHelper(float w, float h) {
            this.blockWidth = w;
            this.blockHeight = h;
        }
    }

    // A slight fork of the original base game FontHelper.getSmartWidth() / Height()
    // functions - this includes better scaling considerations and computing the width
    // and height of the entire text block.
    //
    // NOTE: These computations occur in screen space (i.e. after scaling) as the layout
    // helpers are already scaled and it's easier that way
    public static SizeHelper computeSmartSize(String text, BitmapFont font, float scaledLineWidth, float scaledLineSpacing) {
        float currWidth = 0.0f;

        GlyphLayout layout = FontHelper.layout;

        // Figure out how wide a " " space character is between words
        layout.setText(font, " ");
        float spaceWidth = layout.width;

        // Figure out how tall the font is usually
        // NOTE: this string was taken from base game and I'm not sure how robust it is
        layout.setText(font, "gl0!");
        float lineHeight = layout.height;

        float blockWidth = 0.0f;
        float blockHeight = lineHeight; // TODO: verify this does what I think it did (i.e. a fix for single line labels)

        for (String word : text.split(" ")) {
            if (word.equals("NL")) {
                blockWidth = Math.max(currWidth, blockWidth);
                currWidth = 0.0f;

                blockHeight += scaledLineSpacing;
            }
            else if (word.equals("TAB")){
                currWidth += (spaceWidth * 5.0f);
            }
            else {
                // Strip out leading color info, e.g. #rRed -> Red
                if (wordStartsWithPoundColor(word))
                    word = word.substring(2);

                layout.setText(font, word);

                if (currWidth + layout.width > scaledLineWidth) {
                    currWidth = layout.width + spaceWidth;
                    blockWidth = Math.max(currWidth, blockWidth);

                    blockHeight += scaledLineSpacing;

                }
                else {
                    currWidth += (layout.width + spaceWidth);
                    blockWidth = Math.max(currWidth, blockWidth);

                }
            }
        }

        return new SizeHelper(blockWidth / Settings.xScale, blockHeight / Settings.yScale);
    }
}
