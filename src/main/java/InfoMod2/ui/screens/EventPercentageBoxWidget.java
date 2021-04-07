package InfoMod2.ui.screens;

import InfoMod2.ui.widgets.AbstractWidget;
import InfoMod2.ui.widgets.AnchorPosition;
import InfoMod2.ui.widgets.text.SmartLabel;
import InfoMod2.ui.widgets.tooltips.groups.MapTips;
import InfoMod2.utils.graphics.color.ColorManager;
import InfoMod2.utils.math.EventChanceHelper;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;

import java.util.Arrays;
import java.util.LinkedList;

public class EventPercentageBoxWidget extends AbstractWidget<EventPercentageBoxWidget> {
    private final SmartLabel eventPercentLabel, shrinePercentLabel, fightPercentLabel, shopPercentLabel, treasurePercentLabel;

    private static final Texture TEX_BG = new Texture("InfoMod2/screens/event_percentage_box_v2.png");

    private LinkedList<SmartLabel> labels = new LinkedList<>();

    private static final float SPACING = 40.0f;

    private float texLeft, texBottom;

    public EventPercentageBoxWidget(float left, float top) {
        this.texLeft = left;
        this.texBottom = top - TEX_BG.getHeight();

        // Where the text should start (Left column = text labels, right column = percent labels)
        left = left + 80;
        float percentLeft = left + 126.0f;

        float currY = top - 60;

        //Color color = ColorManager.EVENT_SCREEN_PERCENTAGE_BOX_TEXT();
        // Testing out using the actual qualitative colors here instead of just off-white for all
        Color eventColor = ColorManager.QUAL_TEAL();
        Color shrineColor = ColorManager.QUAL_BLUE();
        Color fightColor = ColorManager.QUAL_RED();
        Color shopColor = ColorManager.QUAL_YELLOW();
        Color treasureColor = ColorManager.QUAL_GREEN();

        SmartLabel eventTextLabel = new SmartLabel("Event", eventColor).anchoredAt(left, currY, AnchorPosition.LEFT_TOP);
        eventPercentLabel = new SmartLabel("???", eventColor).anchoredAt(percentLeft, currY, AnchorPosition.LEFT_TOP);
        currY -= SPACING;

        SmartLabel shrineTextLabel = new SmartLabel("Shrine", shrineColor).anchoredAt(left, currY, AnchorPosition.LEFT_TOP);
        shrinePercentLabel = new SmartLabel("???", shrineColor).anchoredAt(percentLeft, currY, AnchorPosition.LEFT_TOP);
        currY -= SPACING;

        SmartLabel fightTextLabel = new SmartLabel("Fight", fightColor).anchoredAt(left, currY, AnchorPosition.LEFT_TOP);
        fightPercentLabel = new SmartLabel("???", fightColor).anchoredAt(percentLeft, currY, AnchorPosition.LEFT_TOP);
        currY -= SPACING;

        SmartLabel shopTextLabel = new SmartLabel("Shop", shopColor).anchoredAt(left, currY, AnchorPosition.LEFT_TOP);
        shopPercentLabel = new SmartLabel("???", shopColor).anchoredAt(percentLeft, currY, AnchorPosition.LEFT_TOP);
        currY -= SPACING;

        SmartLabel treasureTextLabel = new SmartLabel("Treasure", treasureColor).anchoredAt(left, currY, AnchorPosition.LEFT_TOP);
        treasurePercentLabel = new SmartLabel("???", treasureColor).anchoredAt(percentLeft, currY, AnchorPosition.LEFT_TOP);

        // Convenience
        labels.addAll(Arrays.asList(
                eventTextLabel,
                eventPercentLabel,
                shrineTextLabel,
                shrinePercentLabel,
                fightTextLabel,
                fightPercentLabel,
                shopTextLabel,
                shopPercentLabel,
                treasureTextLabel,
                treasurePercentLabel));
    }

    @Override public float getPreferredContentWidth() { return 0; }
    @Override public float getPreferredContentHeight() { return 0; }

    public void updateProbabilities() {
        EventChanceHelper chanceHelper = MapTips.getEventChanceHelper();

        eventPercentLabel.setText(chanceHelper.getEventChance());
        shrinePercentLabel.setText(chanceHelper.getShrineChance());
        fightPercentLabel.setText(chanceHelper.getFightChance());
        shopPercentLabel.setText(chanceHelper.getShopChance());
        treasurePercentLabel.setText(chanceHelper.getTreasureChance());
    }

    @Override
    public void show() {
        for (SmartLabel label : labels)
            label.show();
    }

    @Override
    public void hide() {
        for (SmartLabel label : labels)
            label.hide();
    }

    @Override
    public void update() {
        for (SmartLabel label : labels)
            label.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        // Render background
        sb.setColor(Color.WHITE);
        sb.draw(TEX_BG, texLeft * Settings.xScale, texBottom * Settings.yScale, TEX_BG.getWidth() * Settings.xScale, TEX_BG.getHeight() * Settings.yScale);

        for (SmartLabel label : labels)
            label.render(sb);
    }
}
