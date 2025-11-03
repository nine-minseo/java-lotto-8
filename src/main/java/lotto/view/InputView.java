package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {
    private static final String PROMPT_PURCHASE_AMOUNT = "구입금액을 입력해 주세요.";
    private static final String PROMPT_WINNING_NUMBERS = "\n당첨 번호를 입력해 주세요.";
    private static final String PROMPT_BONUS_NUMBER = "\n보너스 번호를 입력해 주세요.";

    public static int readPurchaseAmount() {
        System.out.println(PROMPT_PURCHASE_AMOUNT);
        String input = Console.readLine();

        return Integer.parseInt(input);
    }

    public static List<Integer> readWinningNumbers() {
        System.out.println(PROMPT_WINNING_NUMBERS);
        String input = Console.readLine();

        return toInteger(input);
    }

    public static int readBonusNumber() {
        System.out.println(PROMPT_BONUS_NUMBER);
        String input = Console.readLine();

        return Integer.parseInt(input);
    }

    public static List<Integer> toInteger(String winningNumbers) {
        List<String> splittedNumbers = Arrays.asList(winningNumbers.split(","));

        List<Integer> numbers = splittedNumbers.stream()
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        return numbers;
    }
}
