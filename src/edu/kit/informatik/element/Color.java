package edu.kit.informatik.element;

public enum Color {
    RED("red"), BLUE("blue"), GREEN("green"), YELLOW("yellow");

    private String mDescription;

    private Color(String pDescription) {
        this.mDescription = pDescription;
    }

    public String getDescription() {
        return this.mDescription;
    }

    @Override
    public String toString() {
        return this.getDescription();
    }

}
