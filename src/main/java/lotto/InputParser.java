package lotto;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class InputParser {
    public static List<Integer> parseWinningNumbers(String input) {
        String[] numberStrings = input.split(",");

        return Arrays.stream(numberStrings)
                .map(String::trim)
                .map(InputParser::parseNumber)
                .collect(Collectors.toList());
    }

    public static int parseBonusNumber(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 번호는 숫자여야 합니다.");
        }
    }

    private static int parseNumber(String numberSting) {
        try {
            return Integer.parseInt(numberSting);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 숫자만 입력 가능합니다.");
        }
    }
}
