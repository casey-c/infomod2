package InfoMod2.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MapTip extends ThiccToolTip {

    public MapTip() {
        super(1469, 664, 400, 345);
    }

    private static MapTip instance;
    public static void renderCustomMapTip(SpriteBatch sb) {
        if (instance == null) {
            instance = new MapTip();
        }

        instance.render(sb);
    }
}
