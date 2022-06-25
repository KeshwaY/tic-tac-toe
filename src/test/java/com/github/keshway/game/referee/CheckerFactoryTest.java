package com.github.keshway.game.referee;

import com.github.keshway.databus.component.Index;
import com.github.keshway.databus.component.Length;
import com.github.keshway.databus.component.Size;
import com.github.keshway.databus.component.WinCondition;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckerFactoryTest {

    @Test
    void shouldProperlyValidateHorizontalIndexes() {
        // given
        Length length = new Length(3);
        Size size = new Size(9);
        WinCondition winCondition = new WinCondition(3);
        List<SortedSet<Index>> expected = List.of(
                new TreeSet<>(),
                new TreeSet<>(),
                new TreeSet<>()
        );
        expected.get(0).addAll(Set.of(
                new Index(0),
                new Index(1),
                new Index(2)
        ));
        expected.get(1).addAll(Set.of(
                new Index(3),
                new Index(4),
                new Index(5)
        ));
        expected.get(2).addAll(Set.of(
                new Index(6),
                new Index(7),
                new Index(8)
        ));
        CheckerFactory underTest = new CheckerFactory();
        // when
        WinChecker winSequence = underTest.horizontal(length, size, winCondition);
        // then
        assertThat(winSequence.check(expected.get(0)).won()).isTrue();
        assertThat(winSequence.check(expected.get(1)).won()).isTrue();
        assertThat(winSequence.check(expected.get(2)).won()).isTrue();
    }

    @Test
    void shouldProperlyValidateVerticalIndexes() {
        // given
        Length length = new Length(3);
        Size size = new Size(9);
        WinCondition winCondition = new WinCondition(3);
        List<SortedSet<Index>> expected = List.of(
                new TreeSet<>(),
                new TreeSet<>(),
                new TreeSet<>()
        );
        expected.get(0).addAll(Set.of(
                new Index(0),
                new Index(3),
                new Index(6)
        ));
        expected.get(1).addAll(Set.of(
                new Index(1),
                new Index(4),
                new Index(7)
        ));
        expected.get(2).addAll(Set.of(
                new Index(2),
                new Index(5),
                new Index(8)
        ));
        CheckerFactory underTest = new CheckerFactory();
        // when
        WinChecker winSequence = underTest.vertical(length, size, winCondition);
        // then
        assertThat(winSequence.check(expected.get(0)).won()).isTrue();
        assertThat(winSequence.check(expected.get(1)).won()).isTrue();
        assertThat(winSequence.check(expected.get(2)).won()).isTrue();
    }

    @Test
    void shouldProperlyValidateDiagonalIndexes() {
        // given
        Length length = new Length(3);
        Size size = new Size(9);
        WinCondition winCondition = new WinCondition(3);
        SortedSet<Index> expected = new TreeSet<>(Set.of(
                new Index(0),
                new Index(4),
                new Index(8)
        ));
        CheckerFactory underTest = new CheckerFactory();
        // when
        WinChecker winSequence = underTest.diagonal(length, size, winCondition);
        // then
        assertThat(winSequence.check(expected).won()).isTrue();
    }

    @Test
    void shouldProperlyValidateAntiDiagonalIndexes() {
        // given
        Length length = new Length(3);
        Size size = new Size(9);
        WinCondition winCondition = new WinCondition(3);
        SortedSet<Index> expected = new TreeSet<>(Set.of(
                new Index(2),
                new Index(4),
                new Index(6)
        ));
        CheckerFactory underTest = new CheckerFactory();
        // when
        WinChecker winSequence = underTest.antiDiagonal(length, size, winCondition);
        // then
        assertThat(winSequence.check(expected).won()).isTrue();
    }

}