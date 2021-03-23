package InfoMod2.ui.screens;

import InfoMod2.data.EventDatabase;
import InfoMod2.utils.graphics.ExtraColors;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class EventScreen extends TitledScreen<EventScreen> {
    private EventCard act1;

    public EventScreen() {
        super(new Texture("InfoMod2/screens/event_background.png"),
                "Event Overview",
                "Right click anywhere to close");

        initialize();
    }

    // Loads event details from JSON database, constructs the act cards, aligns everything into the proper position, etc.
    // Should be performed one time after game initially boots up
    private void initialize() {
        float act1Top = getMainContentTop();
        act1 = new EventCard(getMainContentLeft(), act1Top, "Act I", EventDatabase.act1_events.values());
    }

    // Called whenever we need to show this screen (recomputes the active status of all events - based on requirement
    // satisfiability and filtering out events that have been already seen in the current run)

    // Doesn't need to create new labels or move UI elements as they've been built in the initialize(); just needs to
    // update their colors and such
    public void computeActive() {
        // TODO
    }

    @Override
    protected void renderWidgets(SpriteBatch sb) {
        act1.render(sb);

//        sb.setColor(ExtraColors.DEBUG_0);
//        sb.draw(ImageMaster.WHITE_SQUARE_IMG,
//                getMainContentLeft() * Settings.xScale,
//                getMainContentBottom() * Settings.yScale,
//                getMainContentWidth() * Settings.xScale,
//                getMainContentHeight() * Settings.yScale);
    }

    @Override
    protected void renderWidgetToolTips(SpriteBatch sb) {
        act1.renderHovers(sb);
    }

    @Override
    public void show() {
        act1.show();
    }

    @Override
    public void hide() {
        act1.hide();
    }

    @Override
    public void update() {
        act1.update();
    }
}
