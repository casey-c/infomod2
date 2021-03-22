package InfoMod2.ui.screens;

import com.badlogic.gdx.graphics.Texture;

public class EventScreen extends TitledScreen<EventScreen> {
    public EventScreen() {
        super(new Texture("InfoMod2/screens/event_background.png"),
                "Event Overview",
                "Right click anywhere to close");
    }
}
