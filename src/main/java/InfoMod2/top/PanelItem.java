package InfoMod2.top;

import InfoMod2.ui.PotionChanceTip;
import InfoMod2.ui.ThiccToolTip;
import basemod.ClickableUIElement;
import basemod.TopPanelItem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.Hitbox;

public class PanelItem extends TopPanelItem {
//    private static final float WIDTH = 64.0F;
//    private static final float HEIGHT = 64.0F;
//    private String ID;

    private boolean currentlyHovering = false;

    private static final Texture tex = new Texture("InfoMod2/panel.png");
    //private ThiccToolTip toolTip = new ThiccToolTip();
    private PotionChanceTip potionChanceTip = new PotionChanceTip();

    public PanelItem() {
        super(tex, "ojb_InfoMod2_panel");

        // Basically ignore the constructor above and just set the ClickableUIElement (parent of TopPanelItem) details for real
        //   since the TopPanelItem constructor is garbo

        this.hb_w = tex.getWidth() * Settings.scale;
        this.hb_h = tex.getHeight() * Settings.scale;

        this.image = tex;
//        this.x = x * Settings.scale;
//        this.y = y * Settings.scale;
//        if (y < 0.0F) {
//            this.y += (float)Settings.HEIGHT;
//        }

//        this.hb_w = hb_w * Settings.scale;
//        this.hb_h = hb_h * Settings.scale;
        this.hitbox = new Hitbox(this.x, this.y, this.hb_w, this.hb_h);
        //this.clickable = true;
    }

//    public Texture getImage() {
//        return this.image;
//    }
//
//    public Hitbox getHitbox() {
//        return this.hitbox;
//    }
//
//    public String getID() {
//        return this.ID;
//    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);

        if (currentlyHovering)
            potionChanceTip.render(sb);
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
    }
}
