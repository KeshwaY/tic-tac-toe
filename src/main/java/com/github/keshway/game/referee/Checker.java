package com.github.keshway.game.referee;

import com.github.keshway.databus.component.Index;

import java.util.SortedSet;

interface Checker {
    Move check(SortedSet<Index> moves);
}
