package edu.kit.informatik.element;

import edu.kit.informatik.element.board.Board;

public class MainClass {

    private static final int CUBE_MAX_INDEX = 6;
    private static final int CUBE_MIN_INDEX = 1;
    private static final int REGULAR_TRACK_LENGTH = 40;

    public MainClass() {

    }

    public static void main(String[] args) {
        Player[] players = new Player[] {new Player(0, new Token[4], String.valueOf(0)),
                new Player(1, new Token[4], String.valueOf(10)), new Player(2, new Token[4], String.valueOf(20)),
                new Player(3, new Token[4], String.valueOf(30)) };
        Board board = new Board(players, REGULAR_TRACK_LENGTH, CUBE_MAX_INDEX, CUBE_MIN_INDEX);
        CommandLine.StartInteractiveSequence(board);
    }
}
