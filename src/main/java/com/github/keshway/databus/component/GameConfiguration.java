package com.github.keshway.databus.component;

public record GameConfiguration(
        PlayOrder playOrder,
        RoundCount roundCount,
        WinCondition winCondition,
        BoardDimensions boardDimensions
) {
}
