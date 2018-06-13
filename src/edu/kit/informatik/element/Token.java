package edu.kit.informatik.element;

public class Token {
    private Color mColor;
    private Cell mPosition;
    private Player mOwner;

    public Token(Color pColor, Player pOwner) {
        this.mColor = pColor;
        this.mOwner = pOwner;
    }

    public void setColor(Color pColor) {
        this.mColor = pColor;
    }

    public void setPosition(Cell pPosition) {
        this.mPosition = pPosition;
    }

    public Cell getPosition() {
        return this.mPosition;
    }

    public Color getColor() {
        return this.mColor;
    }

    public Player getOwner() {
        return this.mOwner;
    }

}
