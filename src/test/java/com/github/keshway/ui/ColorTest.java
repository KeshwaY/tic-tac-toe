package com.github.keshway.ui;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

@Test
public class ColorTest {

    private record ColorTestPair(Color color, String value) {
    }

    @DataProvider
    public Object[][] testColors() {
        return new Object[][] {
                {new ColorTestPair(Color.RESET, "\033[0m")},
                {new ColorTestPair(Color.BLACK, "\033[0;30m")},
                {new ColorTestPair(Color.RED, "\033[0;31m")},
                {new ColorTestPair(Color.GREEN, "\033[0;32m")},
                {new ColorTestPair(Color.YELLOW, "\033[0;33m")},
                {new ColorTestPair(Color.BLUE, "\033[0;34m")},
                {new ColorTestPair(Color.MAGENTA, "\033[0;35m")},
                {new ColorTestPair(Color.CYAN, "\033[0;36m")},
                {new ColorTestPair(Color.WHITE, "\033[0;37m")},
                {new ColorTestPair(Color.BLACK_BOLD, "\033[1;30m")},
                {new ColorTestPair(Color.RED_BOLD, "\033[1;31m")},
                {new ColorTestPair(Color.GREEN_BOLD, "\033[1;32m")},
                {new ColorTestPair(Color.YELLOW_BOLD, "\033[1;33m")},
                {new ColorTestPair(Color.BLUE_BOLD, "\033[1;34m")},
                {new ColorTestPair(Color.MAGENTA_BOLD, "\033[1;35m")},
                {new ColorTestPair(Color.CYAN_BOLD, "\033[1;36m")},
                {new ColorTestPair(Color.WHITE_BOLD, "\033[1;37m")},
                {new ColorTestPair(Color.BLACK_UNDERLINED, "\033[4;30m")},
                {new ColorTestPair(Color.RED_UNDERLINED, "\033[4;31m")},
                {new ColorTestPair(Color.GREEN_UNDERLINED, "\033[4;32m")},
                {new ColorTestPair(Color.YELLOW_UNDERLINED, "\033[4;33m")},
                {new ColorTestPair(Color.BLUE_UNDERLINED, "\033[4;34m")},
                {new ColorTestPair(Color.MAGENTA_UNDERLINED, "\033[4;35m")},
                {new ColorTestPair(Color.CYAN_UNDERLINED, "\033[4;36m")},
                {new ColorTestPair(Color.WHITE_UNDERLINED, "\033[4;37m")},
                {new ColorTestPair(Color.BLACK_BACKGROUND, "\033[40m")},
                {new ColorTestPair(Color.RED_BACKGROUND, "\033[41m")},
                {new ColorTestPair(Color.GREEN_BACKGROUND, "\033[42m")},
                {new ColorTestPair(Color.YELLOW_BACKGROUND, "\033[43m")},
                {new ColorTestPair(Color.BLUE_BACKGROUND, "\033[44m")},
                {new ColorTestPair(Color.MAGENTA_BACKGROUND, "\033[45m")},
                {new ColorTestPair(Color.CYAN_BACKGROUND, "\033[46m")},
                {new ColorTestPair(Color.WHITE_BACKGROUND, "\033[47m")},
                {new ColorTestPair(Color.BLACK_BRIGHT, "\033[0;90m")},
                {new ColorTestPair(Color.RED_BRIGHT, "\033[0;91m")},
                {new ColorTestPair(Color.GREEN_BRIGHT, "\033[0;92m")},
                {new ColorTestPair(Color.YELLOW_BRIGHT, "\033[0;93m")},
                {new ColorTestPair(Color.BLUE_BRIGHT, "\033[0;94m")},
                {new ColorTestPair(Color.MAGENTA_BRIGHT, "\033[0;95m")},
                {new ColorTestPair(Color.CYAN_BRIGHT, "\033[0;96m")},
                {new ColorTestPair(Color.WHITE_BRIGHT, "\033[0;97m")},
                {new ColorTestPair(Color.BLACK_BOLD_BRIGHT, "\033[1;90m")},
                {new ColorTestPair(Color.RED_BOLD_BRIGHT, "\033[1;91m")},
                {new ColorTestPair(Color.GREEN_BOLD_BRIGHT, "\033[1;92m")},
                {new ColorTestPair(Color.YELLOW_BOLD_BRIGHT, "\033[1;93m")},
                {new ColorTestPair(Color.BLUE_BOLD_BRIGHT, "\033[1;94m")},
                {new ColorTestPair(Color.MAGENTA_BOLD_BRIGHT, "\033[1;95m")},
                {new ColorTestPair(Color.CYAN_BOLD_BRIGHT, "\033[1;96m")},
                {new ColorTestPair(Color.WHITE_BOLD_BRIGHT, "\033[1;97m")},
                {new ColorTestPair(Color.BLACK_BACKGROUND_BRIGHT, "\033[0;100m")},
                {new ColorTestPair(Color.RED_BACKGROUND_BRIGHT, "\033[0;101m")},
                {new ColorTestPair(Color.GREEN_BACKGROUND_BRIGHT, "\033[0;102m")},
                {new ColorTestPair(Color.YELLOW_BACKGROUND_BRIGHT, "\033[0;103m")},
                {new ColorTestPair(Color.BLUE_BACKGROUND_BRIGHT, "\033[0;104m")},
                {new ColorTestPair(Color.MAGENTA_BACKGROUND_BRIGHT, "\033[0;105m")},
                {new ColorTestPair(Color.CYAN_BACKGROUND_BRIGHT, "\033[0;106m")},
                {new ColorTestPair(Color.WHITE_BACKGROUND_BRIGHT, "\033[0;107m")},
        };
    }

    @Test(dataProvider = "testColors")
    void shouldHaveResetValue(ColorTestPair... colorTestPairs) {
        // given
        ColorTestPair colorTestPair = colorTestPairs[0];
        Color color = colorTestPair.color;
        String value = colorTestPair.value;
        // when
        String colorValue = color.toString();
        // then
        assertThat(colorValue).isEqualTo(value);
    }
}