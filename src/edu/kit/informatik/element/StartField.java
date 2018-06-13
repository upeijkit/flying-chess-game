package edu.kit.informatik.element;

public enum StartField {
    RED_START_FIELD("SR", "SR", "SR", "SR"), BLUE_START_FIELD("SB", "SB", "SB", "SB"), GREEN_START_FIELD("SG", "SG",
            "SG", "SG"), YELLOW_START_FIELD("SY", "SY", "SY", "SY");

    private String[] mValues;

    private StartField(String... pValues) {
        this.mValues = pValues;
    }

    public String getStartFieldId(int pIndex) {
        return this.mValues[pIndex];
    }

    public int getNumberOfStartField() {
        return this.mValues.length;
    }
}
