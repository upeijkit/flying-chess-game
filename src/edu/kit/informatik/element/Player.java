package edu.kit.informatik.element;

public class Player {
    private int mId;
    private Color mColor;
    private Token[] mTokens;
    private String mStartPositionId;
    private Cell[] mStartField;
    private int mNumberOfStartField;
    private int mNumberOfTargetField;
    private Cell[] mTargetFieldTrack;

    public Player(int pId, Token[] pTokens, String pStartPositionId) {
        this.mId = pId;
        this.mStartPositionId = pStartPositionId;
        this.mColor = Color.values()[pId];
        this.mTokens = pTokens;
        for (int i = 0; i < this.mTokens.length; i++) {
            this.mTokens[i] = new Token(this.mColor, this);
        }
        this.mNumberOfStartField = this.mTokens.length;
        this.mNumberOfTargetField = this.mTokens.length;
        this.mStartField = new Cell[this.mNumberOfStartField];
        for (int i = 0; i < this.mNumberOfStartField; i++) {
            this.mStartField[i] = new Cell(StartField.values()[this.getId()].getStartFieldId(i));
        }
        this.mTargetFieldTrack = new Cell[this.mNumberOfTargetField];
        for (int i = 0; i < this.mNumberOfTargetField; i++) {
            this.mTargetFieldTrack[i] = new Cell(TargetField.values()[this.getId()].getTargetFieldId(i));
        }

    }

    public void setId(int pId) {
        this.mId = pId;
    }

    public int getId() {
        return this.mId;
    }

    public Token[] getTokens() {
        return this.mTokens;
    }

    public int getNumberOfTokens() {
        return this.mTokens.length;
    }

    public String getStartPositionId() {
        return this.mStartPositionId;
    }

    public Cell[] getStartField() {
        return this.mStartField;
    }

    public String getStartFieldId() {
        return this.getStartField()[0].getId();
    }

    public Cell[] getTargetFieldTrack() {
        return this.mTargetFieldTrack;
    }

    /**
     * public void buildTrack() { int startPosition =
     * Integer.parseInt(this.getStartPositionId()); int numberOfTargetField =
     * TargetField.values()[this.getId()].getNumberOfTargetField(); for (int i =
     * 0; i < this.mTrackLength; i++) { if (i < (this.mTrackLength -
     * startPosition - numberOfTargetField)) {
     * this.mTrack[i].setId(String.valueOf(startPosition + i)); } else if (i <
     * (this.mTrackLength - numberOfTargetField)) {
     * this.mTrack[i].setId(String.valueOf(i - (this.mTrackLength -
     * startPosition - numberOfTargetField))); } else {
     * this.mTrack[i].setId(TargetField.values()[this.getId()]
     * .getTargetFieldId(i - (this.mTrackLength - numberOfTargetField))); } } }
     **/

    public Color getColor() {
        return this.mColor;
    }

}
