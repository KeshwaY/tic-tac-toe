package com.github.keshway.ui;

import com.github.keshway.databus.component.*;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.IntStream;

public class GameView implements ConsoleInputView {
    private final BoardDimensions dimensions;
    private final Map<Index, PlayerType> playerMoves;

    public GameView(BoardDimensions dimensions) {
        this.dimensions = dimensions;
        this.playerMoves = new TreeMap<>();
    }

    public void registerMove(Index index, PlayerType player) {
        playerMoves.put(index, player);
    }

    void render(String scores, PlayerScores playerScores, Players players) {
        System.out.println(scores);
        PlayerScore firstPlayerScore = players.first().player() == PlayerType.PLAYER_X ?
                playerScores.playerX() : playerScores.playerO();
        PlayerScore secondPlayerScore = players.second().player() == PlayerType.PLAYER_X ?
                playerScores.playerX() : playerScores.playerO();
        System.out.println(players.first().name() + ": " + firstPlayerScore.score());
        System.out.println(players.second().name() + ": " + secondPlayerScore.score());
    }

    BoardDimensions getDimensions() {
        return dimensions;
    }

    GameView reset() {
        return new GameView(dimensions);
    }

    @Override
    public void render() {
        StringBuilder builder = new StringBuilder();
        builder.append("-".repeat(5 * dimensions.length().value() + 1)).append(System.lineSeparator());
        IntStream.range(0, dimensions.size().value()).boxed()
                .forEach(i -> {
                    String representation = playerMoves.containsKey(new Index(i)) ?
                            playerMoves.get(new Index(i)).sign().value() : String.valueOf(i);
                    builder
                            .append("|")
                            .append(String.format("%4s", representation));
                    if (i % dimensions.length().value() == dimensions.length().value() - 1) {
                        builder.append("|")
                                .append(System.lineSeparator())
                                .append("-".repeat(5 * dimensions.length().value() + 1)).append(System.lineSeparator());
                    }
                });
        System.out.println(builder);
    }
}
