package InfoMod2.top;

import InfoMod2.ui.widgets.tooltips.groups.PotionTips;
import InfoMod2.utils.graphics.color.ColorManager;
import InfoMod2.utils.integration.SlayTheRelicsIntegration;
import basemod.TopPanelItem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;

public class PotionPanelItem extends TopPanelItem {
    private boolean currentlyHovering = false;

//    private static final Texture tex = new Texture("InfoMod2/panel.png");
//    private static final Texture tex = new Texture("InfoMod2/panel.png");
    private static final Texture TEX_NULL = new Texture("InfoMod2/panel/null.png");
    private static final Texture TEX_REGULAR = new Texture("InfoMod2/panel/regular.png");
    private static final Texture TEX_HOVER = new Texture("InfoMod2/panel/hover.png");

    public PotionPanelItem() {
        super(TEX_NULL, "ojb_InfoMod2_panel");

        // Basically ignore the constructor above and just set the ClickableUIElement (parent of TopPanelItem) details for real
        //   since the TopPanelItem constructor is garbo

        this.hb_w = TEX_NULL.getWidth() * Settings.scale;
        this.hb_h = TEX_NULL.getHeight() * Settings.scale;

        this.image = TEX_NULL;
        this.hitbox = new Hitbox(this.x, this.y, this.hb_w, this.hb_h);
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);

        // Render the actual texture here instead since the super.render() i decided to just ignore by drawing a null in there
        float halfWidth = (float)this.image.getWidth() / 2.0F;
        float halfHeight = (float)this.image.getHeight() / 2.0F;

        Texture tex = currentlyHovering ? TEX_HOVER : TEX_REGULAR;

        sb.setColor(Color.WHITE);
        sb.draw(tex,
                this.x - halfWidth + halfWidth * Settings.scale,
                this.y - halfHeight + halfHeight * Settings.scale,
                halfWidth,
                halfHeight,
                (float)this.image.getWidth(),
                (float)this.image.getHeight(),
                Settings.scale,
                Settings.scale,
                this.angle,
                0,
                0,
                this.image.getWidth(),
                this.image.getHeight(),
                false,
                false);

//        if (currentlyHovering)
//            sb.draw(TEX_HOVER, x * Settings.xScale, y * Settings.yScale, hb_w, hb_h);
//        else
//            sb.draw(TEX_REGULAR, x * Settings.xScale, y * Settings.yScale, hb_w, hb_h);

        String text = PotionTips.getMainPotionChance();

        // Slight shift left for 100% being wider than the others (might need 0% to shift right as well, need to check)
        // TODO: do a shift right for 0% as well!
        // TODO: make the shifts better for different resolution scaling?
        float offset = text.equals("100%") ? -6 * Settings.scale : 0;

        float textLeft = x + offset + 68 * Settings.scale;
        float textBottom = y + 21 * Settings.scale;

        // TODO: config option?
        Color textColor = text.equals("80%") ? ColorManager.rainbowColor() : Settings.CREAM_COLOR;

        FontHelper.renderFontLeftDownAligned(sb,
                FontHelper.topPanelAmountFont,
                text,
                textLeft,
                textBottom,
                textColor
        );

        if (currentlyHovering) {
            float centerX =  (x + 0.5f * hb_w) / Settings.xScale;
            PotionTips.render(sb, centerX);
        }
    }

    protected void onHover() {
        this.tint.a = 0.25F;

        if (!currentlyHovering) {
            currentlyHovering = true;
            CardCrawlGame.sound.play("UI_HOVER");
        }

    }

    protected void onUnhover() {
        this.tint.a = 0.0F;
        currentlyHovering = false;
    }

    @Override
    protected void onClick() {
        CardCrawlGame.sound.play("DECK_OPEN");

//        SlayTheRelicsIntegration.print();

//        System.out.println("********** System Information **********");
//        System.out.println("Settings.WIDTH: " + Settings.WIDTH);
//        System.out.println("Settings.HEIGHT: " + Settings.HEIGHT);
//        System.out.println();
//        System.out.println("Settings.scale: " + Settings.scale);
//        System.out.println("Settings.xScale: " + Settings.xScale);
//        System.out.println("Settings.yScale: " + Settings.yScale);
//        System.out.println("****************************************\n");
    }
}
