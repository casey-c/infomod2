package InfoMod2.utils.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class DynamicTextureBox {
//    private final Texture TEX_FULL;
//
//    private final TextureRegion TOP_LEFT_CORNER_INNER, TOP_LEFT_CORNER_OUTER, TOP_LEFT_CORNER_BASE;
//    private TextureRegion TOP_EDGE_INNER, TOP_EDGE_OUTER, TOP_EDGE_BASE;
//    private TextureRegion LEFT_EDGE_INNER, LEFT_EDGE_OUTER, LEFT_EDGE_BASE;

    private final TextureRegion TOP_LEFT_CORNER_INNER, TOP_LEFT_CORNER_OUTER, TOP_LEFT_CORNER_BASE;
    private final TextureRegion TOP_EDGE_INNER,TOP_EDGE_BASE;
    private final TextureRegion LEFT_EDGE_INNER, LEFT_EDGE_BASE;
    private final TextureRegion EDGE_OUTER;

    private final int SIZE;

    private final int CENTER_X, CENTER_Y;

//    private Texture TEX_CORNER_OUTER_BEVEL;
//    private Texture TEX_CORNER_INNER_BEVEL;
//    private Texture TEX_CORNER_BASE;
//
////    private Texture TEX_EDGE_OUTER_BEVEL;
////    private Texture TEX_EDGE_INNER_BEVEL;
////    private Texture TEX_EDGE_BASE;
//
//    private Texture TEX_EDGE_TOP_BOTTOM_OUTER_BEVEL;
//    private Texture TEX_EDGE_TOP_BOTTOM_INNER_BEVEL;
//    private Texture TEX_EDGE_TOP_BOTTOM_BASE;
//
//    private Texture TEX_EDGE_LEFT_RIGHT_OUTER_BEVEL;
//    private Texture TEX_EDGE_LEFT_RIGHT_INNER_BEVEL;
//    private Texture TEX_EDGE_LEFT_RIGHT_BASE;

//    private int CORNER_SIZE;
//    private int CENTER_OFFSET;

//    private int CENTER_X;
//    private int CENTER_Y;

    private Color outerBevelColor, innerBevelColor, baseColor;

    private final TextureAtlas atlas;

    public DynamicTextureBox(String atlasInternalPath) {
//        this.TEX_FULL = new Texture(path);
        this.atlas = new TextureAtlas(atlasInternalPath);

        // Cache textures from the atlas
        this.TOP_LEFT_CORNER_INNER = atlas.findRegion("topLeftCornerInner");
        this.TOP_LEFT_CORNER_OUTER = atlas.findRegion("topLeftCornerOuter");
        this.TOP_LEFT_CORNER_BASE = atlas.findRegion("topLeftCornerBase");

        this.EDGE_OUTER = atlas.findRegion("edgeOuter");

        this.TOP_EDGE_INNER = atlas.findRegion("topEdgeInner");
        this.TOP_EDGE_BASE = atlas.findRegion("topEdgeBase");

        this.LEFT_EDGE_INNER = atlas.findRegion("leftEdgeInner");
        this.LEFT_EDGE_BASE = atlas.findRegion("leftEdgeBase");

        // Use one of the textures to set the size of each piece of the box
        this.SIZE = EDGE_OUTER.getRegionWidth();
        this.CENTER_X = this.CENTER_Y = SIZE / 2;

//        this.TOP_LEFT_CORNER_OUTER = new TextureRegion(TEX_FULL, 0, 2 * textureSize, SIZE, SIZE);
//        this.TOP_LEFT_CORNER_INNER = new TextureRegion(TEX_FULL, textureSize, 2 * textureSize, SIZE, SIZE);
//        this.TOP_LEFT_CORNER_BASE = new TextureRegion(TEX_FULL, 2 * textureSize, 2 * textureSize, SIZE, SIZE);
//
//        this.TOP_EDGE_OUTER = new TextureRegion(TEX_FULL, 0, textureSize, SIZE, SIZE);
//        this.TOP_EDGE_INNER = new TextureRegion(TEX_FULL, textureSize, textureSize, SIZE, SIZE);
//        this.TOP_EDGE_BASE = new TextureRegion(TEX_FULL, 2 * textureSize, textureSize, SIZE, SIZE);
//
//        this.LEFT_EDGE_OUTER = new TextureRegion(TEX_FULL, 0, 0, SIZE, SIZE);
//        this.LEFT_EDGE_INNER = new TextureRegion(TEX_FULL, textureSize, 0, SIZE, SIZE);
//        this.LEFT_EDGE_BASE = new TextureRegion(TEX_FULL, 2 * textureSize, 0, SIZE, SIZE);

//        TextureAtlas atlas = new TextureAtlas("");
//
//        TextureAtlas.AtlasRegion s = atlas.findRegion("cornerInnerBevel");

//        this.TEX_CORNER_OUTER_BEVEL = new Texture(folder + "cornerOuterBevel.png");
//        this.TEX_CORNER_INNER_BEVEL = new Texture(folder + "cornerInnerBevel.png");
//        this.TEX_CORNER_BASE = new Texture(folder + "cornerBase.png");
//        this.TEX_EDGE_OUTER_BEVEL = new Texture(folder + "edgeOuterBevel.png");
//        this.TEX_EDGE_INNER_BEVEL = new Texture(folder + "edgeInnerBevel.png");
//        this.TEX_EDGE_BASE = new Texture(folder + "edgeBase.png");
//
//        this.CORNER_SIZE = TEX_CORNER_BASE.getWidth();
//
//        this.CENTER_X = this.CENTER_Y = CORNER_SIZE / 2;
    }

    public DynamicTextureBox withColors(Color outerBevel, Color innerBevel, Color baseColor) {
        this.outerBevelColor = outerBevel;
        this.innerBevelColor = innerBevel;
        this.baseColor = baseColor;
        return this;
    }

    public void renderCorner(SpriteBatch sb, float left, float bottom, float rot, float wScale, float hScale) {
        sb.setColor(outerBevelColor);

        //sb.draw(TOP_LEFT_CORNER_OUTER, left * Settings.xScale, bottom * Settings.yScale, CENTER_X, CENTER_Y, SIZE * Settings.xScale, SIZE * Settings.yScale, 1, 1, rot);
        sb.draw(TOP_LEFT_CORNER_OUTER, left * Settings.xScale, bottom * Settings.yScale, 0, 0, SIZE * wScale, SIZE * hScale, 1, 1, rot);

//        sb.draw(TOP_LEFT_CORNER_OUTER,
//                left * Settings.xScale, bottom * Settings.yScale,
//                CENTER_X, CENTER_Y,
//                SIZE * Settings.xScale, SIZE * Settings.yScale,
//                1, 1,
//                0,
//                0, 0,
//                SIZE, SIZE,
//                flipX, flipY);
    }

    public void render(SpriteBatch sb, float left, float bottom, float width, float height) {
        float innerLeft = left + SIZE;
        float innerBottom = bottom + SIZE;
        float innerRight = left + width - SIZE;
        float innerTop = bottom + height - SIZE;

        float edgeWidth = innerRight - innerLeft;
        float edgeHeight = innerTop - innerBottom;

        renderCorner(sb, left, innerTop, 0, Settings.xScale, Settings.yScale); // top left
        renderCorner(sb, left + SIZE, bottom, 90, Settings.yScale, Settings.xScale); // bottom left
        renderCorner(sb, innerRight + SIZE, bottom + SIZE, 180, Settings.xScale, Settings.yScale); // bottom right
        renderCorner(sb, innerRight, innerTop + SIZE, -90, Settings.yScale, Settings.xScale); // top right

//        renderCorner(sb, left, innerTop, false, false); // top left
//        renderCorner(sb, innerRight, innerTop, true, false); // top right
//        renderCorner(sb, innerRight, bottom, true, true); // bottom right
    }

//    // Rendered in order OUTER BEVEL -> INNER BEVEL -> BASE (on top, last)
//    public void renderCorner(SpriteBatch sb, float left, float bottom, boolean flipX, boolean flipY) {
//        sb.setColor(outerBevelColor);
//        sb.draw(TEX_CORNER_OUTER_BEVEL, left * Settings.xScale, bottom * Settings.yScale, CENTER_X, CENTER_Y, CORNER_SIZE * Settings.xScale, CORNER_SIZE * Settings.yScale, 1, 1, 0, 0, 0, CORNER_SIZE, CORNER_SIZE, flipX, flipY);
//
//        sb.setColor(innerBevelColor);
//        sb.draw(TEX_CORNER_INNER_BEVEL, left * Settings.xScale, bottom * Settings.yScale, CENTER_X, CENTER_Y, CORNER_SIZE * Settings.xScale, CORNER_SIZE * Settings.yScale, 1, 1, 0, 0, 0, CORNER_SIZE, CORNER_SIZE, flipX, flipY);
//
//        sb.setColor(baseColor);
//        sb.draw(TEX_CORNER_BASE, left * Settings.xScale, bottom * Settings.yScale, CENTER_X, CENTER_Y, CORNER_SIZE * Settings.xScale, CORNER_SIZE * Settings.yScale, 1, 1, 0, 0, 0, CORNER_SIZE, CORNER_SIZE, flipX, flipY);
//    }
//
//    public void renderBottomTopEdge(SpriteBatch sb, float left, float bottom, float width, boolean flipY) {
//        sb.setColor(outerBevelColor);
//        sb.draw(TEX_EDGE_OUTER_BEVEL, left * Settings.xScale, bottom * Settings.yScale, CENTER_X, CENTER_Y, CORNER_SIZE * Settings.xScale, CORNER_SIZE * Settings.yScale, 1, 1, 0, 0, 0, CORNER_SIZE, CORNER_SIZE, flipX, flipY);
//
//        sb.setColor(innerBevelColor);
//        sb.draw(TEX_CORNER_INNER_BEVEL, left * Settings.xScale, bottom * Settings.yScale, CENTER_X, CENTER_Y, CORNER_SIZE * Settings.xScale, CORNER_SIZE * Settings.yScale, 1, 1, 0, 0, 0, CORNER_SIZE, CORNER_SIZE, flipX, flipY);
//
//        sb.setColor(baseColor);
//        sb.draw(TEX_CORNER_BASE, left * Settings.xScale, bottom * Settings.yScale, CENTER_X, CENTER_Y, CORNER_SIZE * Settings.xScale, CORNER_SIZE * Settings.yScale, 1, 1, 0, 0, 0, CORNER_SIZE, CORNER_SIZE, flipX, flipY);
//    }
//
////    public void renderEdge(SpriteBatch sb, float left, float bottom, float width, float height, float rot, boolean flipX, boolean flipY) {
////        sb.setColor(outerBevelColor);
////        sb.draw(TEX_EDGE_OUTER_BEVEL, left * Settings.xScale, bottom * Settings.yScale, CENTER_X, CENTER_Y, width * Settings.xScale, height * Settings.yScale, 1, 1, rot, 0, 0, CORNER_SIZE, CORNER_SIZE, flipX, flipY);
////
////        sb.setColor(innerBevelColor);
////        sb.draw(TEX_EDGE_INNER_BEVEL, left * Settings.xScale, bottom * Settings.yScale, CENTER_X, CENTER_Y, width * Settings.xScale, height * Settings.yScale, 1, 1, rot, 0, 0, CORNER_SIZE, CORNER_SIZE, flipX, flipY);
////
////        sb.setColor(baseColor);
////        sb.draw(TEX_EDGE_BASE, left * Settings.xScale, bottom * Settings.yScale, CENTER_X, CENTER_Y, width * Settings.xScale, height * Settings.yScale, 1, 1, rot, 0, 0, CORNER_SIZE, CORNER_SIZE, flipX, flipY);
////    }
//
//    // TODO: probably should cache some of this because it doesn't really need to do all these calculations per frame
//    //    but it probably doesnae matter because it's not super expensive anyway
//    public void render(SpriteBatch sb, float left, float bottom, float width, float height) {
//        float innerLeft = left + CORNER_SIZE;
//        float innerBottom = bottom + CORNER_SIZE;
//        float innerRight = left + width - CORNER_SIZE;
//        float innerTop = bottom + height - CORNER_SIZE;
//
//        float edgeWidth = innerRight - innerLeft;
//        float edgeHeight = innerTop - innerBottom;
//
//        renderCorner(sb, left, bottom, false, true); // bottom left
//        renderCorner(sb, left, innerTop, false, false); // top left
//        renderCorner(sb, innerRight, innerTop, true, false); // top right
//        renderCorner(sb, innerRight, bottom, true, true); // bottom right
//
////        renderEdge(sb, innerLeft, bottom, edgeWidth, CORNER_SIZE, 0, false, true); // bottom edge
////        renderEdge(sb, innerLeft, innerTop, edgeWidth, CORNER_SIZE, 0, false, false); // top edge
////        renderEdge(sb, left, innerBottom, edgeHeight, CORNER_SIZE, 90, false, false); // left edge
////        renderEdge(sb, innerRight, innerBottom + edgeHeight - CORNER_SIZE, edgeHeight, CORNER_SIZE, -90, false, false); // right edge
//
//        sb.setColor(baseColor);
//        sb.draw(ImageMaster.WHITE_SQUARE_IMG, innerLeft * Settings.xScale, innerBottom * Settings.yScale, edgeWidth * Settings.xScale, edgeHeight * Settings.yScale);
//    }
}
