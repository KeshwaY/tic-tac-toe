package com.github.keshway.game;

import com.github.keshway.databus.ComparableEventListener;
import com.github.keshway.databus.event.GameEvent;
import org.tinylog.Logger;

class GameLogger implements ComparableEventListener<GameEvent> {

    @Override
    public void accept(GameEvent event) {
        Logger.info(event);
    }

    @Override
    public int compareTo(ComparableEventListener<GameEvent> o) {
        return 100;
    }
}
