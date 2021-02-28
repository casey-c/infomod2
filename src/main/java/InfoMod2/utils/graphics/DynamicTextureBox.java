package InfoMod2.utils.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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

    private final TextureRegion TOP_LEFT_CORNER_OUTER, TOP_LEFT_CORNER_INNER, TOP_LEFT_CORNER_BASE;
    private final TextureRegion LEFT_EDGE_OUTER, LEFT_EDGE_INNER, LEFT_EDGE_BASE;

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
        this.TOP_LEFT_CORNER_OUTER = atlas.findRegion("topLeftCornerOuter");
        this.TOP_LEFT_CORNER_INNER = atlas.findRegion("topLeftCornerInner");
        this.TOP_LEFT_CORNER_BASE = atlas.findRegion("topLeftCornerBase");

        this.LEFT_EDGE_OUTER = atlas.findRegion("leftEdgeOuter");
        this.LEFT_EDGE_INNER = atlas.findRegion("leftEdgeInner");
        this.LEFT_EDGE_BASE = atlas.findRegion("leftEdgeBase");

        // Use one of the textures to set the size of each piece of the box
        this.SIZE = LEFT_EDGE_OUTER.getRegionWidth();
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

    public void renderCorner2(SpriteBatch sb, float left, float bottom, float wScale, float hScale, float rot) {
        sb.setColor(outerBevelColor);
        sb.draw(TOP_LEFT_CORNER_OUTER, left * Settings.xScale, bottom * Settings.yScale, 0, 0, SIZE * wScale, SIZE * hScale, 1, 1, rot);

        sb.setColor(innerBevelColor);
        sb.draw(TOP_LEFT_CORNER_INNER, left * Settings.xScale, bottom * Settings.yScale, 0, 0, SIZE * wScale, SIZE * hScale, 1, 1, rot);

        sb.setColor(baseColor);
        sb.draw(TOP_LEFT_CORNER_BASE, left * Settings.xScale, bottom * Settings.yScale, 0, 0, SIZE * wScale, SIZE * hScale, 1, 1, rot);
    }

    private static final Color DEBUG_COLOR = new Color(0.1f, 0.8f, 0.1f, 0.6f);

    public void renderEdge2(SpriteBatch sb, float left, float bottom, float width, float height, float wScale, float hScale, float rot) {
//        sb.setColor(outerBevelColor);
        sb.setColor(Color.RED);
        sb.draw(LEFT_EDGE_OUTER, left * Settings.xScale, bottom * Settings.yScale, 0, 0, width * wScale, height * hScale, 1, 1, rot);

//        sb.setColor(innerBevelColor);
//        sb.draw(LEFT_EDGE_INNER, left * Settings.xScale, bottom * Settings.yScale, 0, 0, width * wScale, height * hScale, 1, 1, rot);
//
//        sb.setColor(baseColor);
//        sb.draw(LEFT_EDGE_BASE, left * Settings.xScale, bottom * Settings.yScale, 0, 0, width * wScale, height * hScale, 1, 1, rot);
    }


    // TODO: move all scaling inside render
    //       then -> make sure inner pieces are square from left/bottom/top/right (e.g. scaled by Settings.scale, not xS/yS)

    public void renderCorner(SpriteBatch sb, float scaledLeft, float scaledBottom, float scaledSize, float rot) {
        sb.setColor(outerBevelColor);
        sb.draw(TOP_LEFT_CORNER_OUTER, scaledLeft, scaledBottom, 0, 0, scaledSize, scaledSize, 1, 1, rot);

        sb.setColor(innerBevelColor);
        sb.draw(TOP_LEFT_CORNER_INNER, scaledLeft, scaledBottom, 0, 0, scaledSize, scaledSize, 1, 1, rot);

        sb.setColor(baseColor);
        sb.draw(TOP_LEFT_CORNER_BASE, scaledLeft, scaledBottom, 0, 0, scaledSize, scaledSize, 1, 1, rot);
    }

    public void renderEdge(SpriteBatch sb, float scaledLeft, float scaledBottom, float scaledWidth, float scaledHeight, float rot) {
        sb.setColor(outerBevelColor);
        sb.draw(LEFT_EDGE_OUTER, scaledLeft, scaledBottom, 0, 0, scaledWidth, scaledHeight, 1, 1, rot);

        sb.setColor(innerBevelColor);
        sb.draw(LEFT_EDGE_INNER, scaledLeft, scaledBottom, 0, 0, scaledWidth, scaledHeight, 1, 1, rot);

        sb.setColor(baseColor);
        sb.draw(LEFT_EDGE_BASE, scaledLeft, scaledBottom, 0, 0, scaledWidth, scaledHeight, 1, 1, rot);
    }


    public void render(SpriteBatch sb, float left, float bottom, float width, float height) {
        // Scaled dimensions
        float scaledCornerSize = SIZE * Settings.scale;
        float scaledWidth = width * Settings.xScale;
        float scaledHeight = height * Settings.yScale;

        // Scaled outer bounds
        float scaledLeft = left * Settings.xScale;
        float scaledBottom = bottom * Settings.yScale;
        float scaledRight = scaledLeft + scaledWidth;
        float scaledTop = scaledBottom + scaledHeight;

        // Scaled inner bounds
        float scaledInnerLeft = scaledLeft + scaledCornerSize;
        float scaledInnerBottom = scaledBottom + scaledCornerSize;
        float scaledInnerRight = scaledRight - scaledCornerSize;
        float scaledInnerTop = scaledTop - scaledCornerSize;

        // Inner dimensions
        float scaledInnerWidth = scaledInnerRight - scaledInnerLeft;
        float scaledInnerHeight = scaledInnerTop - scaledInnerBottom;

        // Corners
        renderCorner(sb, scaledLeft, scaledInnerTop, scaledCornerSize, 0); // top left
        renderCorner(sb, scaledInnerLeft, scaledBottom, scaledCornerSize, 90); // bottom left
        renderCorner(sb, scaledInnerRight + scaledCornerSize, scaledBottom + scaledCornerSize, scaledCornerSize, 180); // bottom left
        renderCorner(sb, scaledInnerRight, scaledInnerTop + scaledCornerSize, scaledCornerSize, -90); // bottom left

        // Edges
        renderEdge(sb, scaledLeft, scaledInnerBottom, scaledCornerSize, scaledInnerHeight, 0); // left edge
        renderEdge(sb, scaledInnerLeft + scaledInnerWidth, scaledBottom, scaledCornerSize, scaledInnerWidth, 90); // bottom edge
        renderEdge(sb, scaledInnerRight + scaledCornerSize, scaledInnerBottom + scaledInnerHeight, scaledCornerSize, scaledInnerHeight, 180); // right edge
        renderEdge(sb, scaledInnerLeft, scaledInnerTop + scaledCornerSize, scaledCornerSize, scaledInnerWidth, -90); // top edge

        // Center
        sb.setColor(baseColor);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, scaledInnerLeft, scaledInnerBottom, scaledInnerWidth, scaledInnerHeight);
    }

    public void render2(SpriteBatch sb, float left, float bottom, float width, float height) {
//        float innerLeft = left + SIZE;
//        float innerBottom = bottom + SIZE;
//        float innerRight = left + width - SIZE;
//        float innerTop = bottom + height - SIZE;
//
//        float edgeWidth = innerRight - innerLeft;
//        float edgeHeight = innerTop - innerBottom;
//
//        renderCorner(sb, left, innerTop, Settings.xScale, Settings.yScale, 0); // top left
//        renderCorner(sb, left + SIZE, bottom, Settings.yScale, Settings.xScale, 90); // bottom left
//        renderCorner(sb, innerRight + SIZE, bottom + SIZE, Settings.xScale, Settings.yScale, 180); // bottom right
//        renderCorner(sb, innerRight, innerTop + SIZE, Settings.yScale, Settings.xScale, -90); // top right
//
//        renderEdge(sb, left, innerBottom, SIZE, edgeHeight, Settings.xScale, Settings.yScale, 0); // left edge
//        renderEdge(sb, innerLeft + edgeWidth, bottom, SIZE, edgeWidth, Settings.yScale, Settings.xScale, 90); // bottom edge
//        renderEdge(sb, innerRight + SIZE, innerBottom + edgeHeight, SIZE, edgeHeight, Settings.xScale, Settings.yScale, 180); // right edge
//        renderEdge(sb, innerLeft, innerTop + SIZE, SIZE, edgeWidth, Settings.yScale, Settings.xScale, -90); // top edge
//
//        // Render center
//        sb.setColor(baseColor);
//        sb.draw(ImageMaster.WHITE_SQUARE_IMG, innerLeft * Settings.xScale, innerBottom * Settings.yScale, edgeWidth * Settings.xScale, edgeHeight * Settings.yScale);
//
//        // Render debug region
//        sb.setColor(DEBUG_COLOR);
//        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 100 * Settings.xScale, 100 * Settings.yScale, SIZE * Settings.xScale, SIZE * Settings.yScale);
//        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 400 * Settings.xScale, 100 * Settings.yScale, SIZE, SIZE);
//
////        renderCorner(sb, left, innerTop, false, false); // top left
////        renderCorner(sb, innerRight, innerTop, true, false); // top right
////        renderCorner(sb, innerRight, bottom, true, true); // bottom right
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
