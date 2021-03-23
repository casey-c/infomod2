package InfoMod2.ui.screens;

import InfoMod2.data.EventDatabase;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class EventScreen extends TitledScreen<EventScreen> {
    private EventCard act1, act2, act3, shrines;
    private LinkedList<EventCard> cards = new LinkedList<>();

    public EventScreen() {
        super(new Texture("InfoMod2/screens/event_background.png"),
                "Event Overview",
                "Right click anywhere to close");

        initialize();
    }

    private static final float SPACING = 60.0f;

    // Loads event details from JSON database, constructs the act cards, aligns everything into the proper position, etc.
    // Should be performed one time after game initially boots up
    private void initialize() {
        float act1Top = getMainContentTop() - 36;
        act1 = new EventCard(getMainContentLeft(), act1Top, "Act I", EventDatabase.act1_events.values());

        float act2Top = act1Top - act1.getPreferredContentHeight() - SPACING;
        act2 = new EventCard(getMainContentLeft(), act2Top, "Act II", EventDatabase.act2_events.values());

        float act3Top = act2Top - act2.getPreferredContentHeight() - SPACING;
        act3 = new EventCard(getMainContentLeft(), act3Top, "Act III", EventDatabase.act3_events.values());

        float shrinesLeft = getMainContentLeft() + 661;
        shrines = new EventCard(shrinesLeft, act1Top, "Shrines", EventDatabase.shrine_events.values());

        // Convenience
        cards.addAll(Arrays.asList(act1, act2, act3, shrines));
    }

    // Called whenever we need to show this screen (recomputes the active status of all events - based on requirement
    // satisfiability and filtering out events that have been already seen in the current run)

    // Doesn't need to create new labels or move UI elements as they've been built in the initialize(); just needs to
    // update their colors and such
    private void computeActive() {
        // Update the events seen in this current run
        final HashMap<String, Integer> seenEvents = new HashMap<>();

        System.out.println("Seen Events: " + CardCrawlGame.metricData.event_choices.size());
        for (HashMap choice : CardCrawlGame.metricData.event_choices) {
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

            System.out.println("\tEvent: " + name + " (floor " + floor + ")");

            if (floor != -1)
                seenEvents.put(name, floor);
        }
        System.out.println("-----\n");

        // Use the seen events (and reexamine other reqs like current floor, etc.) to update the act cards
        for (EventCard card : cards)
            card.computeActive(seenEvents);
    }

    @Override
    protected void renderWidgets(SpriteBatch sb) {
        for (EventCard card : cards)
            card.render(sb);
    }

    @Override
    protected void renderWidgetToolTips(SpriteBatch sb) {
        for (EventCard card : cards)
            card.renderHovers(sb);
    }

    @Override
    public void show() {
        // Make sure to figure out which events are possible; update all colors appropriately
        computeActive();

        for (EventCard card : cards)
            card.show();
    }

    @Override
    public void hide() {
        for (EventCard card : cards)
            card.hide();
    }

    @Override
    public void update() {
        // Inherit right click to close logic from screen
        super.update();

        for (EventCard card : cards)
            card.update();
    }
}
