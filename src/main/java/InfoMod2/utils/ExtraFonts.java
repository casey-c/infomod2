package InfoMod2.utils;

import basemod.ReflectionHacks;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
}
