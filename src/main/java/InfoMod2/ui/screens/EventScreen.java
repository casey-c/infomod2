package InfoMod2.ui.screens;

import InfoMod2.data.EventDatabase;
import InfoMod2.utils.graphics.ExtraColors;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

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

    private HashMap<String, Integer> seenEvents = new HashMap<>();

    // Doesn't need to create new labels or move UI elements as they've been built in the initialize(); just needs to
    // update their colors and such
    private void computeActive() {
        // Update the events seen in this current run
        seenEvents.clear();
        ArrayList<HashMap> x = CardCrawlGame.metricData.event_choices;

        System.out.println("Checking seen events; count is: " + x.size());

        for (HashMap choice : x) {
            if (!choice.containsKey("event_name") || !choice.containsKey("floor") )
                continue;

            String name = (String) choice.get("event_name");

            // In game it's stored as an int, but if you save/quit/reload a save, it comes back as a double
            // So you have a chance to get back either one without knowing which (and both types can exist on a single
            // run). Since java will crash if you just do a cast raw (e.g. (int)double), you have to use one of the
            // 30 more explicit alternatives, e.g. the following:
            int floor = -1;
            Object unknownType = choice.get("floor");

            if (unknownType instanceof Double)
                floor = ((Double) unknownType).intValue();
            else if (unknownType instanceof Integer)
                floor = (Integer) unknownType;

//            double floor_double = (double)choice.get("floor");
//            int floor = Math.toIntExact(Math.round(floor_double));

            //int floor = (int) choice.get("floor");

            System.out.println("\tEvent: " + name + " (floor " + floor + ")");

            if (floor != -1)
                seenEvents.put(name, floor);
        }

        // Use the seen events (and reexamine other reqs like current floor, etc.) to update the act cards
        act1.computeActive(seenEvents);

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
        // Make sure to figure out which events are possible; update all colors appropriately
        computeActive();

        act1.show();
    }

    @Override
    public void hide() {
        act1.hide();
    }

    @Override
    public void update() {
        // Right click to close logic
        super.update();

        act1.update();
    }
}
