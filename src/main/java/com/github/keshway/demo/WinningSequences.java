package com.github.keshway.demo;

import com.github.keshway.databus.component.Index;

import java.util.Collection;
import java.util.Set;

record WinningSequences(Collection<Set<Index>> sequences) {
}
