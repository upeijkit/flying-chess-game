package edu.kit.informatik.element;

public enum TargetField {
    RED_TARGET_FIELD("AR", "BR", "CR", "DR"), BLUE_TARGET_FIELD("AB", "BB", "CB", "DB"), GREEN_TARGET_FIELD("AG", "BG",
            "CG", "DG"), YELLOW_TARGET_FIELD("AY", "BY", "CY", "DY");

    private String[] mValues;

    private TargetField(String... pValues) {
        this.mValues = pValues;
    }

    public int getNumberOfTargetField() {
        return this.mValues.length;
    }

    public String getTargetFieldId(int pIndex) {
        return this.mValues[pIndex];
    }
}
