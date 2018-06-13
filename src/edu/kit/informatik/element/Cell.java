package edu.kit.informatik.element;

public class Cell {
    private String mId;
    private Token mToken = null;
    private boolean mhasToken = false;

    public Cell(String pId) {
        this.mId = pId;
    }

    public Cell() {

    }

    public void setId(String pId) {
        this.mId = pId;
    }

    public void placeToken(Token pToken) {
        this.mToken = pToken;
        this.mhasToken = true;
    }

    public void removeToken() {
        this.mToken = null;
    }

    public boolean hasToken() {
        return this.mhasToken;
    }

    public String getId() {
        return this.mId;
    }

    public Token getToken() {
        return this.mToken;
    }

    @Override
    public String toString() {
        return this.getId();
    }
}
