package com.github.keshway.demo;

import com.github.keshway.databus.DataBus;
import com.github.keshway.databus.component.*;
import com.github.keshway.databus.event.ConfigurationEvent;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class DefaultDemoFacade implements DemoFacade {

    @FunctionalInterface
    interface TriFunction<T,U,S, R> {
        R apply(T t, U u, S s);
    }

    private final Map<String, TriFunction<Length, Size, WinCondition, WinningSequences>> sequences;

    public DefaultDemoFacade() {
        this.sequences = Map.of(
                "H", WinSequenceFactory::horizontal,
                "V", WinSequenceFactory::vertical,
                "D", WinSequenceFactory::diagonal,
                "A", WinSequenceFactory::antiDiagonal
        );
    }

    @Override
    public void initialize(DataBus<ConfigurationEvent> configurationBus, String[] args) {
        try {
            Length length = getLength(args);
            Height height = getHeight(args);
            BoardDimensions dimensions = new BoardDimensions(height, length);
            WinCondition winCondition = winCondition(dimensions, args);
            PlayerType starts = player(args, 4);
            PlayerType shouldWin = player(args, 5);
            Index index = indexWinIndex(dimensions, args);
            String direction = direction(args);
            WinningSequences winningSequences = sequences.get(direction).apply(length, dimensions.size(), winCondition);
            Optional<Set<Index>> possible = winningSequences.sequences()
                    .stream()
                    .filter(s -> s.contains(index))
                    .findFirst();
            if (possible.isEmpty()) {
                System.out.println("Win not possible");
                return;
            }
            PlayOrder playOrder = new PlayOrder(starts);
            DemoConfigurer configurer = new DemoConfigurer(playOrder, shouldWin, new WinSequence(possible.get()), dimensions);
            GameConfiguration configuration = new GameConfiguration(
                    new PlayOrder(starts),
                    new RoundCount(3),
                    winCondition,
                    dimensions
            );
            ConfigurationEvent configurationEvent = new ConfigurationEvent(configuration, configurer);
            configurationBus.publish(configurationEvent);
        } catch (IllegalStateException | NumberFormatException e) {
            System.err.println("Invalid parameters. Correct order: " +
                    "--demo <LENGTH: 3-31> <HEIGHT: 3-31> <WIN_SEQUENCE> <STARTS: X, O>" +
                    " <WINNER: X, O> <WIN_INDEX> <DIRECTION: H, V, D, A>");
        }
    }

    private Length getLength(String[] args) {
        int length = Integer.parseInt(args[1]);
        if (length < 3 || length > 31) throw new IllegalStateException();
        return new Length(length);
    }

    private Height getHeight(String[] args) {
        int height = Integer.parseInt(args[2]);
        if (height < 3 || height > 31) throw new IllegalStateException();
        return new Height(height);
    }

    private WinCondition winCondition(BoardDimensions boardDimensions, String[] args) {
        int winCondition = Integer.parseInt(args[3]);
        if (winCondition > Math.max(boardDimensions.length().value(), boardDimensions.height().value()))
            throw new IllegalStateException();
        return new WinCondition(winCondition);
    }

    private PlayerType player(String[] args, int index) {
        String sign = args[index].toUpperCase(Locale.ROOT);
        if (!sign.equals("X") && !sign.equals("O")) throw new IllegalStateException();
        return sign.equals("X") ? PlayerType.PLAYER_X : PlayerType.PLAYER_O;
    }

    private Index indexWinIndex(BoardDimensions boardDimensions, String[] args) {
        int index = Integer.parseInt(args[6]);
        if (index < 0 || index > boardDimensions.size().value()) throw new IllegalStateException();
        return new Index(index);
    }

    private String direction(String[] args) {
        String direction = args[7].toUpperCase(Locale.ROOT);
        if (!sequences.containsKey(direction)) throw new IllegalStateException();
        return direction;
    }
}
