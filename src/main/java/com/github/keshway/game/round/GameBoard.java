package com.github.keshway.game.round;

import com.github.keshway.databus.component.Index;
import com.github.keshway.databus.component.PlayerType;
import com.github.keshway.databus.component.Size;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class GameBoard implements Board {
    private final Map<Index, Cell> board;
    private final Size size;

    GameBoard(Size size) {
        this.size = size;
        this.board = new HashMap<>();
        // TODO: factory
        IntStream.range(0, size.value()).boxed()
                .forEach(i -> board.put(new Index(i), new Empty()));
    }

    @Override
    public void put(PlayerType player, Index index) {
        Cell current = board.get(index);
        Cell newCell = player.equals(PlayerType.PLAYER_X) ?
                current.markAsPlayerX() : current.markAsPlayerO();
        board.put(index, newCell);
    }

    @Override
    public Size size() {
        return size;
    }
}
