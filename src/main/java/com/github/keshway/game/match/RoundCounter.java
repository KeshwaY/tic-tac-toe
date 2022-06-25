package com.github.keshway.game.match;

import com.github.keshway.databus.component.Round;
import com.github.keshway.databus.component.RoundCount;

class RoundCounter {

    private Round currentCount;
    private final RoundCount maxCount;

    RoundCounter(RoundCount maxCount) {
        this.currentCount = new Round(0);
        this.maxCount = maxCount;
    }

    void increment() {
        this.currentCount = currentCount.increment();
    }

    GameStatus checkIfGameShouldEnd() {
        return currentCount.value() == maxCount.value() ?
                new GameStatus(true) : new GameStatus(false);
    }

}
