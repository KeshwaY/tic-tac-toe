package com.github.keshway.game.round;

import com.github.keshway.databus.ComparableEventListener;
import com.github.keshway.databus.event.GameEvent;

interface Round extends ComparableEventListener<GameEvent> {
    Round next();
}
