package com.github.keshway.demo;


import com.github.keshway.databus.component.Index;
import com.github.keshway.databus.component.Length;
import com.github.keshway.databus.component.Size;
import com.github.keshway.databus.component.WinCondition;
import org.testng.annotations.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@Test
public class WinSequenceFactoryTest {

    @Test
    void shouldCreateValidHorizontalWinningSequence() {
        // given
        Length length = new Length(3);
        Size size = new Size(9);
        WinCondition winCondition = new WinCondition(3);
        WinningSequences expected = new WinningSequences(
                Set.of(
                        Set.of(
                                new Index(0),
                                new Index(1),
                                new Index(2)
                        ),
                        Set.of(
                                new Index(3),
                                new Index(4),
                                new Index(5)
                        ),
                        Set.of(
                                new Index(6),
                                new Index(7),
                                new Index(8)
                        )
                )
        );
        // when
        WinningSequences winSequence = WinSequenceFactory.horizontal(length, size, winCondition);
        // then
        assertThat(winSequence).isEqualTo(expected);
    }

    @Test
    void shouldCreateValidVerticalWinningSequence() {
        // given
        Length length = new Length(3);
        Size size = new Size(9);
        WinCondition winCondition = new WinCondition(3);
        WinningSequences expected = new WinningSequences(
                Set.of(
                        Set.of(
                                new Index(0),
                                new Index(3),
                                new Index(6)
                        ),
                        Set.of(
                                new Index(1),
                                new Index(4),
                                new Index(7)
                        ),
                        Set.of(
                                new Index(2),
                                new Index(5),
                                new Index(8)
                        )
                )
        );
        // when
        WinningSequences winSequence = WinSequenceFactory.vertical(length, size, winCondition);
        // then
        assertThat(winSequence).isEqualTo(expected);
    }

    @Test
    void shouldCreateValidDiagonalWinningSequence() {
        // given
        Length length = new Length(3);
        Size size = new Size(9);
        WinCondition winCondition = new WinCondition(3);
        WinningSequences expected = new WinningSequences(
                Set.of(
                        Set.of(
                                new Index(0),
                                new Index(4),
                                new Index(8)
                        )
                )
        );
        // when
        WinningSequences winSequence = WinSequenceFactory.diagonal(length, size, winCondition);
        // then
        assertThat(winSequence).isEqualTo(expected);
    }


    @Test
    void shouldCreateValidAntiDiagonalWinningSequence() {
        // given
        Length length = new Length(3);
        Size size = new Size(9);
        WinCondition winCondition = new WinCondition(3);
        WinningSequences expected = new WinningSequences(
                Set.of(
                        Set.of(
                                new Index(2),
                                new Index(4),
                                new Index(6)
                        )
                )
        );
        // when
        WinningSequences winSequence = WinSequenceFactory.antiDiagonal(length, size, winCondition);
        // then
        assertThat(winSequence).isEqualTo(expected);
    }

}