package InfoMod2.top;

import InfoMod2.ui.*;
import InfoMod2.ui.widgets.tooltips.groups.MapTips;
import InfoMod2.ui.widgets.tooltips.groups.PotionTips;
import InfoMod2.utils.KeyHelper;
import basemod.TopPanelItem;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;

public class PotionPanelItem extends TopPanelItem {
//    private static final float WIDTH = 64.0F;
//    private static final float HEIGHT = 64.0F;
//    private String ID;

    private boolean currentlyHovering = false;

    private static final Texture tex = new Texture("InfoMod2/panel_v4.png");
    //private ThiccToolTip toolTip = new ThiccToolTip();
    //private PotionChanceTip potionChanceTip = new PotionChanceTip();

    //TitledToolTip potionChanceTip = new TitledToolTip(400, 300, "Multiline line", "test lorem ipsum").anchoredAtTop(1383);
    //TitledToolTip potionChanceTip = new TitledToolTip(400, 300, "Single line test").anchoredAtTop(1383);
//    private PotionChanceTip potionChanceTip;

    public PotionPanelItem() {
        super(tex, "ojb_InfoMod2_panel");

        // Basically ignore the constructor above and just set the ClickableUIElement (parent of TopPanelItem) details for real
        //   since the TopPanelItem constructor is garbo

        this.hb_w = tex.getWidth() * Settings.scale;
        this.hb_h = tex.getHeight() * Settings.scale;

        this.image = tex;
        this.hitbox = new Hitbox(this.x, this.y, this.hb_w, this.hb_h);

        // Set up the tool tip
//        potionChanceTip = new PotionChanceTip(400, 360, "Chance to see at least one", "potion after multiple fights:").anchoredAtTop(1383);
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);

        //float textLeft = (x + 68) * Settings.scale;
        //float textBottom = (y + 21) * Settings.scale;
        float textLeft = x + 68 * Settings.scale;
        float textBottom = y + 21 * Settings.scale;

        FontHelper.renderFontLeftDownAligned(sb,
                FontHelper.topPanelAmountFont,
                "40%",
                textLeft,
                textBottom,
                //ExtraColors.rainbowColor()
                Settings.CREAM_COLOR
        );

        if (currentlyHovering)
            PotionTips.render(sb);
    }

    protected void onHover() {
//        this.angle = MathHelper.angleLerpSnap(this.angle, 15.0F);
        this.tint.a = 0.25F;

        if (!currentlyHovering) {
            currentlyHovering = true;
            CardCrawlGame.sound.play("UI_HOVER");
        }

    }

    protected void onUnhover() {
//        this.angle = MathHelper.angleLerpSnap(this.angle, 0.0F);
        this.tint.a = 0.0F;
        currentlyHovering = false;
    }

    @Override
    protected void onClick() {
        CardCrawlGame.sound.play("DECK_OPEN");

        // Debug
        if (KeyHelper.isShiftPressed())
            MapTips.resetBossTip();
        else
            MapTips.updateBossTip("Champ");
    }
}
