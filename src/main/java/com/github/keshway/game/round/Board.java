package com.github.keshway.game.round;

import com.github.keshway.databus.component.Index;
import com.github.keshway.databus.component.PlayerType;
import com.github.keshway.databus.component.Size;

interface Board {
    void put(PlayerType player, Index index);
    Size size();
}
