package edu.kit.informatik.element;

public class Cube {
    private int mMaxIndex;
    private int mMinIndex;

    public Cube(int pMaxIndex, int pMinIndex) {
        this.mMaxIndex = pMaxIndex;
        this.mMinIndex = pMinIndex;
    }

    public int getMaxIndex() {
        return this.mMaxIndex;
    }

    public int getMinIndex() {
        return this.mMinIndex;
    }
}
