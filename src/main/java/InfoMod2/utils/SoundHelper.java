package InfoMod2.utils;

import com.megacrit.cardcrawl.core.CardCrawlGame;

public class SoundHelper {
    public static void cawCaw() {
        CardCrawlGame.sound.play("VO_CULTIST_1A");
    }

    public static void screenOpenSound() {
        CardCrawlGame.sound.play("DECK_OPEN");
    }

    public static void screenCloseSound() {
        CardCrawlGame.sound.play("DECK_CLOSE");
    }
}
