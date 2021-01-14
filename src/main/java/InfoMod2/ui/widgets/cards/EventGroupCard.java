package InfoMod2.ui.widgets.cards;

import InfoMod2.data.EventDetail;
import InfoMod2.ui.widgets.AbstractWidget;
import InfoMod2.ui.widgets.AnchorPosition;
import InfoMod2.ui.widgets.text.HoverableLabelGroup;
import InfoMod2.ui.widgets.text.SimpleLabel;
import InfoMod2.utils.ExtraColors;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Collection;

public class EventGroupCard extends AbstractWidget<EventGroupCard> {
    private SimpleLabel groupTitleLabel;
    private SimpleLabel numAvailableLabel;
    private HoverableLabelGroup group;

    private static final float prefGroupWidth = 500.0f;
    private static final float titleGroupSpacing = 28.0f; // TODO

    public EventGroupCard(float left, float top, String titleText, Collection<EventDetail> items) {
        // We align the title labels (e.g. the name of the group and the # of events left in its pool) along a bottom
        // edge - the 28.0f should probably not be hardcoded but based on the font but whatever
        float titleBottom = top - 28.0f;

        this.groupTitleLabel = new SimpleLabel(titleText, ExtraColors.EVENT_SCREEN_CARD_TITLE)
                .anchoredAt(left, titleBottom, AnchorPosition.LEFT_BOTTOM);

        // TODO: dynamic text
        float labelRight = left + prefGroupWidth;
        this.numAvailableLabel = new SimpleLabel("0 / 0", ExtraColors.EVENT_SCREEN_CARD_NUM)
                .anchoredAt(labelRight, titleBottom, AnchorPosition.RIGHT_BOTTOM);

        float groupTop = top - groupTitleLabel.getPreferredContentHeight() - titleGroupSpacing;

        this.group = new HoverableLabelGroup(prefGroupWidth)
                .anchoredAt(left, groupTop, AnchorPosition.LEFT_TOP)
                .withItems(items);

        // probably not needed to ever call this since i've broken the pattern RIP
        //anchoredAt(left, top, AnchorPosition.LEFT_TOP);
    }

    @Override
    public float getPreferredContentWidth() {
        return 520.0f;
    }

    @Override
    public float getPreferredContentHeight() {
        return numAvailableLabel.getPreferredContentHeight() + titleGroupSpacing + group.getPreferredContentHeight();
    }

    @Override
    public void render(SpriteBatch sb) {
        groupTitleLabel.render(sb);
        numAvailableLabel.render(sb);
        group.render(sb);
    }

    public void renderHovers(SpriteBatch sb) {
        group.renderHovers(sb);
    }

    @Override
    public void update() {
        group.update();
    }

    @Override
    public void show() {
        group.show();
    }

    @Override
    public void hide() {
        group.hide();
    }
}
