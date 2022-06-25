package com.github.keshway.game.referee;

import com.github.keshway.databus.ComparableEventListener;
import com.github.keshway.databus.event.GameEvent;

abstract class BaseReferee implements Referee {
    @Override
    public int compareTo(ComparableEventListener<GameEvent> o) {
        return 3;
    }
}
