package InfoMod2.ui.widgets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/*

  Widgets can define the width/height of their content area dynamically - there is built in margin code to make padding
  a bit easier, but widgets are mostly concerned about where their getContentLeft() etc. are located for rendering calls.

  Following convention, all floats are considered unscaled and refer to 1080p space; and should be scaled during the
  final rendering calls only to make calculations and offsets easier.

e.g.

  getLeft()                    getContentRight()
  |                            |
  v                            v
  +---------------------------------+
  |         MARGINS                 |
  |    +-----------------------+    |
  |    |    CONTENT            |    |
  |    +-----------------------+    |
  |                                 |
  +---------------------------------+

  ^
  |
  (x,y) is always bottom left in internal memory


  There are builder methods for constructing a widget. In general, a decent pattern is to construct a widget first,
  giving it enough details to figure out how wide/tall it should be (i.e. it sets its own getPreferredContentWidth()
  and getPreferredContentHeight() functions) and any other widget specific details that it may want to set at
  construction. Once this information is computed / known, it is time for the widget to be "finalized", i.e. the last
  step of a builder pattern, by calling the anchoredAt() function. Normally, this anchoring step will be performed /
  updated by some sort of layout manager to automate the tedious process of placing a widget.

 */

public abstract class AbstractWidget<T extends AbstractWidget<T>> {
    private float marginLeft = 10;
    private float marginRight = 10;
    private float marginTop = 10;
    private float marginBottom = 10;

    private float x, y;

    public abstract float getPreferredContentWidth();
    public abstract float getPreferredContentHeight();

    // --------------------------------------------------------------------------------
    // Some basic builder methods. (Widgets will probably add some of their own)
    // --------------------------------------------------------------------------------

    public void setMargins(float all) { this.marginLeft = this.marginRight = this.marginTop = this.marginBottom = all; }
    public T withMargins(float all) {
        this.setMargins(all);
        return (T)this;
    }

    public void setMargins(float horizontal, float vertical) {
        this.marginLeft = this.marginRight = horizontal;
        this.marginBottom = this.marginTop = vertical;
    }
    public T withMargins(float horizontal, float vertical) {
        this.setMargins(horizontal, vertical);
        return (T)this;
    }

    // --------------------------------------------------------------------------------
    // The final step of the pseudo-builder pattern. Required for rendering.
    // --------------------------------------------------------------------------------

    public T anchoredAt(float x, float y, AnchorPosition anchorPosition) {
        this.x = anchorPosition.isLeft() ? x : (anchorPosition.isCenterX() ? x - 0.5f * getWidth() : x - getWidth());
        this.y = anchorPosition.isBottom() ? y : (anchorPosition.isCenterY() ? y - 0.5f * getHeight() : y - getHeight());

        return (T)this;
    }

    // --------------------------------------------------------------------------------

    // These can be obtained before (x,y) are set by the anchor
    public float getWidth() { return getPreferredContentWidth() + marginLeft + marginRight; }
    public float getHeight() { return getPreferredContentHeight() + marginBottom + marginTop; }

    // These should only be used after setting the anchor position
    public float getContentLeft() { return x + marginLeft; }
    public float getContentRight() { return x + getPreferredContentWidth() + marginLeft + marginRight; }

    public float getContentBottom() { return y + marginBottom; }
    public float getContentTop() { return y + getPreferredContentHeight() + marginBottom + marginTop; }

    // --------------------------------------------------------------------------------

    public abstract void render(SpriteBatch sb);
    public void update() {}
}
