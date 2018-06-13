package edu.kit.informatik.element.board;

import java.util.Map;
import java.util.TreeMap;

import edu.kit.informatik.element.Cell;
import edu.kit.informatik.element.Cube;
import edu.kit.informatik.element.Player;
import edu.kit.informatik.element.Token;

public class Board {
    private Player[] mPlayers;
    private Cell[] mRegularTrack;
    private boolean mIsActive = false;
    private int mCurrentActivePlayerIndex = 0;
    private Cube mCube;
    private boolean mMoveToTrack = false;
    private Map<String, String> mMoveChance;
    private boolean hasWon = false;

    public Board(Player[] pPlayers, int pRegularTrackLength, int pMaxIndex, int pMinIndex) {
        this.mMoveChance = new TreeMap<>();
        this.mPlayers = pPlayers;
        this.mRegularTrack = new Cell[pRegularTrackLength];
        this.mCube = new Cube(pMaxIndex, pMinIndex);
        for (int i = 0; i < pRegularTrackLength; i++) {
            this.mRegularTrack[i] = new Cell(String.valueOf(i));
        }
    }

    public int getRegularTrackLength() {
        return this.mRegularTrack.length;
    }

    public Player getPlayerByIndex(int pIndex) {
        return this.mPlayers[pIndex];
    }

    public Player[] getPlayers() {
        return this.mPlayers;
    }

    public boolean gameStatus() {
        return this.mIsActive;
    }

    public Map<String, String> getMoveChance() {
        return this.mMoveChance;
    }

    public void startGame() {
        this.mIsActive = true;
        for (Player cPlayer : this.mPlayers) {
            for (int i = 0; i < cPlayer.getStartField().length; i++) {
                cPlayer.getStartField()[i].placeToken(cPlayer.getTokens()[i]);
                cPlayer.getTokens()[i].setPosition(cPlayer.getStartField()[i]);
            }
        }
    }

    public void startGame(String[][] pActualPosition) {
        this.mIsActive = true;
        for (int i = 0; i < this.mPlayers.length; i++) {
            for (int j = 0; j < this.getPlayerByIndex(i).getNumberOfTokens(); j++) {
                if (this.mPlayers[i].getStartFieldId().equals(pActualPosition[i][j])) {
                    for (Cell cCell : this.mPlayers[i].getStartField()) {
                        if (!cCell.hasToken()) {
                            cCell.placeToken(this.mPlayers[i].getTokens()[j]);
                            this.mPlayers[i].getTokens()[j].setPosition(cCell);
                            break;
                        }
                    }
                }

                for (Cell cCell : this.mPlayers[i].getTargetFieldTrack()) {
                    if (!cCell.hasToken() && cCell.getId().equals(pActualPosition[i][j])) {
                        cCell.placeToken(this.mPlayers[i].getTokens()[j]);
                        this.mPlayers[i].getTokens()[j].setPosition(cCell);
                        break;
                    }
                }

                for (Cell cCell : this.mRegularTrack) {
                    if (!cCell.hasToken() && cCell.getId().equals(pActualPosition[i][j])) {
                        cCell.placeToken(this.mPlayers[i].getTokens()[j]);
                        this.mPlayers[i].getTokens()[j].setPosition(cCell);
                        break;
                    }
                }
            }
        }

    }

    public Player getCurrentActivePlayer() {
        return this.mPlayers[this.mCurrentActivePlayerIndex];
    }

    public void roll(int pIndex) {
        int startPosition = Integer.parseInt(this.getCurrentActivePlayer().getStartPositionId());
        for (Token cToken : this.getCurrentActivePlayer().getTokens()) {
            if (pIndex == this.mCube.getMaxIndex()) {
                if (!this.mMoveToTrack
                        && cToken.getPosition().equals(this.getCurrentActivePlayer().getStartFieldId())) {
                    if (this.mRegularTrack[startPosition].hasToken()) {
                        if (this.findTargetPosition(startPosition) instanceof Integer) {
                            int targetIndex = Integer.parseInt(String.valueOf(findTargetPosition(startPosition)));
                            if (targetIndex - this.mCube.getMaxIndex() < 0) {
                                this.mMoveChance.put(
                                        String.valueOf(targetIndex - this.mCube.getMaxIndex() + startPosition),
                                        String.valueOf(this.findTargetPosition(startPosition)));
                                this.mMoveToTrack = true;
                            } else {
                                this.mMoveChance.put(String.valueOf(targetIndex - this.mCube.getMaxIndex()),
                                        String.valueOf(this.findTargetPosition(startPosition)));
                                this.mMoveToTrack = true;
                            }
                        } else {
                            for (int i = 0; i < this.getCurrentActivePlayer().getTargetFieldTrack().length; i++) {
                                if (this.getCurrentActivePlayer().getTargetFieldTrack()[i]
                                        .equals(String.valueOf(this.findTargetPosition(startPosition)))) {
                                    this.mMoveChance.put(String.valueOf(startPosition + i - this.mCube.getMaxIndex()),
                                            String.valueOf(this.findTargetPosition(startPosition)));
                                    this.mMoveToTrack = true;
                                }
                            }
                        }
                    } else {
                        this.mMoveChance.put(cToken.getPosition().toString(),
                                this.getCurrentActivePlayer().getStartPositionId());
                        this.mMoveToTrack = true;
                    }
                }
            }
            if ((!this.mMoveToTrack)) {
                for (int i = 0; i < this.getRegularTrackLength(); i++) {
                    if (this.mRegularTrack[i].getId().equals(cToken.getPosition().toString())) {
                        if (String.valueOf(startPosition).equals(this.mPlayers[0].getStartPositionId())) {
                            if (i + pIndex < this.getRegularTrackLength()) {
                                if (!this.mRegularTrack[i + pIndex].hasToken() || !this.mRegularTrack[i + pIndex]
                                        .getToken().getColor().equals(cToken.getColor())) {
                                    this.mMoveChance.put(cToken.getPosition().toString(),
                                            this.mRegularTrack[i + pIndex].toString());
                                }
                            } else if (!this.getCurrentActivePlayer().getTargetFieldTrack()[i + pIndex
                                    - this.getRegularTrackLength()].hasToken()) {
                                this.mMoveChance.put(cToken.getPosition().toString(), this.getCurrentActivePlayer()
                                        .getTargetFieldTrack()[i + pIndex - this.getRegularTrackLength()].toString());
                            }
                        } else if (i < startPosition) {
                            if (i + pIndex < startPosition && (!this.mRegularTrack[i + pIndex].hasToken()
                                    || (this.mRegularTrack[i + pIndex].hasToken() && !this.mRegularTrack[i + pIndex]
                                            .getToken().getColor().equals(cToken.getColor())))) {
                                this.mMoveChance.put(cToken.getPosition().toString(),
                                        this.mRegularTrack[i + pIndex].getId());
                            } else if (i + pIndex < startPosition - 1 + this.mCube.getMaxIndex()
                                    && !this.getCurrentActivePlayer().getTargetFieldTrack()[i + pIndex - startPosition]
                                            .hasToken()) {
                                this.mMoveChance.put(cToken.getPosition().toString(),
                                        this.getCurrentActivePlayer().getTargetFieldTrack()[i + pIndex - startPosition]
                                                .toString());
                            }
                        } else if (i + pIndex < this.getRegularTrackLength()
                                && (!this.mRegularTrack[i + pIndex].hasToken()
                                        || (this.mRegularTrack[i + pIndex].hasToken() && !this.mRegularTrack[i + pIndex]
                                                .getToken().getColor().equals(cToken.getColor())))) {
                            this.mMoveChance.put(cToken.getPosition().toString(),
                                    this.mRegularTrack[i + pIndex].getId());
                        } else if (!this.mRegularTrack[i + pIndex - this.getRegularTrackLength()].hasToken()
                                || !this.mRegularTrack[i + pIndex - this.getRegularTrackLength()].getToken().getColor()
                                        .equals(cToken.getColor())) {
                            this.mMoveChance.put(cToken.getPosition().toString(),
                                    this.mRegularTrack[i + pIndex - this.getRegularTrackLength()].getId());
                        }
                    } else {

                    }
                }
            }
        }

        if (this.mMoveChance.isEmpty()) {
            changeCurrentActivePlayer();
        }
    }

    public void move(String pCurrentPosition, String pTargetPosition) {
        Cell currentCell = new Cell();
        Cell targetCell = new Cell();
        for (Cell cCell : this.mRegularTrack) {
            if (cCell.getId().equals(pCurrentPosition)) {
                currentCell = cCell;
            }
            if (cCell.getId().equals(pTargetPosition)) {
                targetCell = cCell;
            }
        }
        for (Cell cCell : this.getCurrentActivePlayer().getStartField()) {
            if (cCell.getId().equals(pCurrentPosition)) {
                currentCell = cCell;
            }
            if (cCell.getId().equals(pTargetPosition)) {
                targetCell = cCell;
            }
        }
        for (Cell cCell : this.getCurrentActivePlayer().getTargetFieldTrack()) {
            if (cCell.getId().equals(pCurrentPosition)) {
                currentCell = cCell;
            }
            if (cCell.getId().equals(pTargetPosition)) {
                targetCell = cCell;
            }
        }
        currentCell.removeToken();
        targetCell.placeToken(currentCell.getToken());
        hasWon = hasWon();
        if (this.mMoveToTrack) {
            changeCurrentActivePlayer();
        }
        this.mMoveToTrack = false;
    }

    public boolean hasWon() {
        for (Cell cCell : this.getCurrentActivePlayer().getTargetFieldTrack()) {
            if (!cCell.hasToken()) {
                return false;
            }
        }
        return true;
    }

    public Object findTargetPosition(int pId) {
        int startPosition = Integer.parseInt(this.getCurrentActivePlayer().getStartPositionId());
        if (!this.mRegularTrack[pId].hasToken()
                || !this.getCurrentActivePlayer().getColor().equals(this.mRegularTrack[pId].getToken().getColor())) {
            return pId;
        }
        if (startPosition == Integer.parseInt(this.mPlayers[0].getStartPositionId())) {
            if (pId + this.mCube.getMaxIndex() < this.getRegularTrackLength()) {
                findTargetPosition(pId + this.mCube.getMaxIndex());
            } else {
                int index = pId + this.mCube.getMaxIndex() - this.getRegularTrackLength();
                return (this.getCurrentActivePlayer().getTargetFieldTrack()[index].hasToken() ? String.valueOf(-1)
                        : this.getCurrentActivePlayer().getTargetFieldTrack()[index].getId());
            }
        } else if (pId + this.mCube.getMaxIndex() < this.getRegularTrackLength()) {
            return this.findTargetPosition(pId + this.mCube.getMaxIndex());
        } else if (pId + this.mCube.getMaxIndex() - this.getRegularTrackLength() < startPosition) {
            findTargetPosition(pId + this.mCube.getMaxIndex() - this.getRegularTrackLength());
        } else {
            int index = (pId + this.mCube.getMaxIndex() - this.getRegularTrackLength()) - startPosition;
            return (this.getCurrentActivePlayer().getTargetFieldTrack()[index].hasToken() ? -1
                    : this.getCurrentActivePlayer().getTargetFieldTrack()[index].getId());
        }
        return 333;
    }

    public void changeCurrentActivePlayer() {
        changeCurrentActivePlayer(false);
    }

    public void changeCurrentActivePlayer(Boolean pDirection) {
        if (!pDirection) {
            if (this.mCurrentActivePlayerIndex < this.mPlayers.length) {
                this.mCurrentActivePlayerIndex++;
            } else
                this.mCurrentActivePlayerIndex = this.mCurrentActivePlayerIndex % (this.mPlayers.length - 1);
        }
    }

    public String printPosition() {
        String out = "";
        for (Player cPlayer : this.mPlayers) {
            String line = "";
            for (Token cToken : cPlayer.getTokens()) {
                line += (line.isEmpty() ? "" : " ") + cToken.getPosition().toString();
            }
            out += (out.isEmpty() ? "" : "\n") + line;
        }
        out += "\n" + this.getCurrentActivePlayer().getColor().toString();
        return out;
    }

}
