package InfoMod2.utils.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class DynamicTextureBox {
    private final TextureRegion TOP_LEFT_CORNER_OUTER, TOP_LEFT_CORNER_INNER, TOP_LEFT_CORNER_BASE;
    private final TextureRegion LEFT_EDGE_OUTER, LEFT_EDGE_INNER, LEFT_EDGE_BASE;

    private final int SIZE;

    private Color outerBevelColor, innerBevelColor, baseColor;

    public DynamicTextureBox(String atlasInternalPath) {
        TextureAtlas atlas = new TextureAtlas(atlasInternalPath);

        // Cache textures from the atlas
        this.TOP_LEFT_CORNER_OUTER = atlas.findRegion("topLeftCornerOuter");
        this.TOP_LEFT_CORNER_INNER = atlas.findRegion("topLeftCornerInner");
        this.TOP_LEFT_CORNER_BASE = atlas.findRegion("topLeftCornerBase");

        this.LEFT_EDGE_OUTER = atlas.findRegion("leftEdgeOuter");
        this.LEFT_EDGE_INNER = atlas.findRegion("leftEdgeInner");
        this.LEFT_EDGE_BASE = atlas.findRegion("leftEdgeBase");

        // Use one of the textures to set the size of each piece of the box
        this.SIZE = LEFT_EDGE_OUTER.getRegionWidth();
    }

    public DynamicTextureBox withColors(Color outerBevel, Color innerBevel, Color baseColor) {
        this.outerBevelColor = outerBevel;
        this.innerBevelColor = innerBevel;
        this.baseColor = baseColor;
        return this;
    }

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
}
