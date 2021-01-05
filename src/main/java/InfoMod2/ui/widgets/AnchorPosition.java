package InfoMod2.ui.widgets;

public enum AnchorPosition {
    LEFT_TOP, CENTER_TOP, RIGHT_TOP,
    LEFT_CENTER, CENTER, RIGHT_CENTER,
    LEFT_BOTTOM, CENTER_BOTTOM, RIGHT_BOTTOM;

    public boolean isLeft() { return this == LEFT_BOTTOM || this == LEFT_CENTER || this == LEFT_TOP; }
    public boolean isRight() { return this == RIGHT_BOTTOM || this == RIGHT_CENTER || this == RIGHT_TOP; }

    public boolean isBottom() { return this == LEFT_BOTTOM || this == CENTER_BOTTOM || this == RIGHT_BOTTOM; }
    public boolean isTop() { return this == LEFT_TOP || this == CENTER_TOP || this == RIGHT_TOP; }

    public boolean isCenterX() { return this == CENTER_TOP || this == CENTER || this == CENTER_BOTTOM; }
    public boolean isCenterY() { return this == LEFT_CENTER || this == CENTER || this == RIGHT_CENTER; }
}
