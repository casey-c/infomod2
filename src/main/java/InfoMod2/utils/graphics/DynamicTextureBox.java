package InfoMod2.utils.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class DynamicTextureBox {
    private Texture TEX_CORNER_OUTER_BEVEL;
    private Texture TEX_CORNER_INNER_BEVEL;
    private Texture TEX_CORNER_BASE;

    private Texture TEX_EDGE_OUTER_BEVEL;
    private Texture TEX_EDGE_INNER_BEVEL;
    private Texture TEX_EDGE_BASE;

    private int CORNER_SIZE;
    private int CENTER_OFFSET;

    private Color outerBevelColor, innerBevelColor, baseColor;

    public DynamicTextureBox(String folder) {
        this.TEX_CORNER_OUTER_BEVEL = new Texture(folder + "cornerOuterBevel.png");
        this.TEX_CORNER_INNER_BEVEL = new Texture(folder + "cornerInnerBevel.png");
        this.TEX_CORNER_BASE = new Texture(folder + "cornerBase.png");
        this.TEX_EDGE_OUTER_BEVEL = new Texture(folder + "edgeOuterBevel.png");
        this.TEX_EDGE_INNER_BEVEL = new Texture(folder + "edgeInnerBevel.png");
        this.TEX_EDGE_BASE = new Texture(folder + "edgeBase.png");

        this.CORNER_SIZE = TEX_CORNER_BASE.getWidth();
        this.CENTER_OFFSET = CORNER_SIZE / 2;
    }

    public DynamicTextureBox withColors(Color outerBevel, Color innerBevel, Color baseColor) {
        this.outerBevelColor = outerBevel;
        this.innerBevelColor = innerBevel;
        this.baseColor = baseColor;
        return this;
    }

    // Rendered in order OUTER BEVEL -> INNER BEVEL -> BASE (on top, last)
    public void renderCorner(SpriteBatch sb, float left, float bottom, float rot, boolean flipX, boolean flipY) {
        sb.setColor(outerBevelColor);
        sb.draw(TEX_CORNER_OUTER_BEVEL, left * Settings.scale, bottom * Settings.scale, CENTER_OFFSET * Settings.scale, CENTER_OFFSET * Settings.scale, CORNER_SIZE * Settings.scale, CORNER_SIZE * Settings.scale, 1, 1, rot, 0, 0, CORNER_SIZE, CORNER_SIZE, flipX, flipY);

        sb.setColor(innerBevelColor);
        sb.draw(TEX_CORNER_INNER_BEVEL, left * Settings.scale, bottom * Settings.scale, CENTER_OFFSET * Settings.scale, CENTER_OFFSET * Settings.scale, CORNER_SIZE * Settings.scale, CORNER_SIZE * Settings.scale, 1, 1, rot, 0, 0, CORNER_SIZE, CORNER_SIZE, flipX, flipY);

        sb.setColor(baseColor);
        sb.draw(TEX_CORNER_BASE, left * Settings.scale, bottom * Settings.scale, CENTER_OFFSET * Settings.scale, CENTER_OFFSET * Settings.scale, CORNER_SIZE * Settings.scale, CORNER_SIZE * Settings.scale, 1, 1, rot, 0, 0, CORNER_SIZE, CORNER_SIZE, flipX, flipY);
    }

    public void renderEdge(SpriteBatch sb, float left, float bottom, float width, float height, float rot, boolean flipX, boolean flipY) {
        sb.setColor(outerBevelColor);
        sb.draw(TEX_EDGE_OUTER_BEVEL, left * Settings.scale, bottom * Settings.scale, CENTER_OFFSET * Settings.scale, CENTER_OFFSET * Settings.scale, width * Settings.scale, height * Settings.scale, 1, 1, rot, 0, 0, CORNER_SIZE, CORNER_SIZE, flipX, flipY);

        sb.setColor(innerBevelColor);
        sb.draw(TEX_EDGE_INNER_BEVEL, left * Settings.scale, bottom * Settings.scale, CENTER_OFFSET * Settings.scale, CENTER_OFFSET * Settings.scale, width * Settings.scale, height * Settings.scale, 1, 1, rot, 0, 0, CORNER_SIZE, CORNER_SIZE, flipX, flipY);

        sb.setColor(baseColor);
        sb.draw(TEX_EDGE_BASE, left * Settings.scale, bottom * Settings.scale, CENTER_OFFSET * Settings.scale, CENTER_OFFSET * Settings.scale, width * Settings.scale, height * Settings.scale, 1, 1, rot, 0, 0, CORNER_SIZE, CORNER_SIZE, flipX, flipY);
    }

    // TODO: probably should cache some of this because it doesn't really need to do all these calculations per frame
    //    but it probably doesnae matter because it's not super expensive anyway
    public void render(SpriteBatch sb, float left, float bottom, float width, float height) {
        float innerLeft = left + CORNER_SIZE;
        float innerBottom = bottom + CORNER_SIZE;
        float innerRight = left + width - CORNER_SIZE;
        float innerTop = bottom + height - CORNER_SIZE;

        float edgeWidth = innerRight - innerLeft;
        float edgeHeight = innerTop - innerBottom;

        renderCorner(sb, left, bottom, 0, false, true); // bottom left
        renderCorner(sb, left, innerTop, 0, false, false); // top left
        renderCorner(sb, innerRight, innerTop, 0, true, false); // top right
        renderCorner(sb, innerRight, bottom, -90, true, false); // bottom right

        renderEdge(sb, innerLeft, bottom, edgeWidth, CORNER_SIZE, 0, false, true); // bottom edge
        renderEdge(sb, innerLeft, innerTop, edgeWidth, CORNER_SIZE, 0, false, false); // top edge
        renderEdge(sb, left, innerBottom, edgeHeight, CORNER_SIZE, 90, false, false); // left edge
        renderEdge(sb, innerRight, innerBottom + edgeHeight - CORNER_SIZE, edgeHeight, CORNER_SIZE, -90, false, false); // right edge

        sb.setColor(baseColor);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, innerLeft * Settings.scale, innerBottom * Settings.scale, edgeWidth * Settings.scale, edgeHeight * Settings.scale);
    }
}
