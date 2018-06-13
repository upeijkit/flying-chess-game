package edu.kit.informatik.element;

import java.util.Map.Entry;

import edu.kit.informatik.element.board.Board;

public class CommandLine {
    public CommandLine() {

    }

    private static final String START = "start";
    private static final String PRINT = "print";
    private static final String ROLL = "roll";
    private static final String TEST = "test";

    public static void StartInteractiveSequence(Board pBoard) {
        while (true) {
            String command = Terminal.readLine();
            String[] parts = command.split("\\s", 2);
            switch (parts[0]) {
            case CommandLine.START:
                if (parts.length == 1)
                    CommandLine.start(pBoard);
                else
                    CommandLine.start(pBoard, parts[1]);
                break;
            case CommandLine.PRINT:
                CommandLine.print(pBoard);
                break;
            case CommandLine.ROLL:
                CommandLine.roll(pBoard, parts[1]);
                break;
            case CommandLine.TEST:
                Terminal.printLine(String.valueOf(pBoard.findTargetPosition(Integer.parseInt(parts[1]))));
                break;
            }
        }
    }

    private static void start(Board pBoard) {
        pBoard.startGame();
        Terminal.printLine("OK");
    }

    private static void start(Board pBoard, String param) {
        String[] parts = param.split(";", 4);
        String[][] actualPositions = new String[pBoard.getPlayers().length][pBoard.getPlayerByIndex(0)
                .getNumberOfTokens()];
        for (int i = 0; i < pBoard.getPlayers().length; i++) {
            String[] cPositions = parts[i].split(",", 4);
            for (int j = 0; j < pBoard.getPlayerByIndex(i).getNumberOfTokens(); j++) {
                actualPositions[i][j] = cPositions[j];
            }
        }
        pBoard.startGame(actualPositions);
        Terminal.printLine("OK");
    }

    private static void roll(Board pBoard, String param) {
        pBoard.roll(Integer.parseInt(param));
        if (pBoard.getMoveChance().isEmpty()) {
            Terminal.printLine(pBoard.getCurrentActivePlayer().getColor().toString());
        } else {
            for (Entry<String, String> cEntry : pBoard.getMoveChance().entrySet()) {
                Terminal.printLine(cEntry.getKey() + "-" + cEntry.getValue());
            }
            Terminal.printLine(pBoard.getCurrentActivePlayer().getColor().toString());
        }

    }

    private static void print(Board pBoard) {
        Terminal.printLine(pBoard.printPosition());
    }

}
