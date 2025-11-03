package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.regex.Pattern;

public class InputView {
    private static final String PROMPT_PURCHASE_AMOUNT = "구입금액을 입력해 주세요.";
    private static final String PROMPT_WINNING_NUMBERS = "\n당첨 번호를 입력해 주세요.";
    private static final String PROMPT_BONUS_NUMBER = "\n보너스 번호를 입력해 주세요.";

    public static int readPurchaseAmount() {
        System.out.println(PROMPT_PURCHASE_AMOUNT);
        String input = Console.readLine();

        validatePurchaseAmountFormat(input);
        return parsePurchaseAmount(input);
    }

    public static List<Integer> readWinningNumbers() {
        System.out.println(PROMPT_WINNING_NUMBERS);
        String input = Console.readLine();

        return toInteger(input);
    }

    public static int readBonusNumber() {
        System.out.println(PROMPT_BONUS_NUMBER);
        String input = Console.readLine();

        validateBonusNumberFormat(input);
        return parseAndValidateBonusNumber(input);
    }

    public static List<Integer> toInteger(String winningNumbers) {
        validateWinningNumbersFormat(winningNumbers);

        List<String> splittedNumbers = Arrays.asList(winningNumbers.split(","));
        return parseWinningNumbers(splittedNumbers);
    }

    private static void validatePurchaseAmountFormat(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 1,000원 단위로 로또 구입 금액을 입력해야 합니다.");
        }
        if (input.contains(" ")) {
            throw new IllegalArgumentException("[ERROR] 로또 구입 금액에 공백이 없어야 합니다.");
        }
    }

    private static int parsePurchaseAmount(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 로또 구입 금액은 문자가 아닌 숫자여야 합니다.");
        }
    }

    private static void validateWinningNumbersFormat(String winningNumbers) {
        if (winningNumbers.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 1부터 45 사이의 로또 번호 6개를 입력해야 합니다.");
        }
        if (winningNumbers.contains(";")) {
            throw new IllegalArgumentException("[ERROR] 콤마(,)로 구분한 로또 번호를 입력해야 합니다.");
        }
        String pattern = "^[^,]+(,[^,]+)*$";
        if (!Pattern.matches(pattern, winningNumbers)) {
            throw new IllegalArgumentException("[ERROR] 콤마(,)로 구분한 로또 번호를 입력해야 합니다.");
        }
    }

    private static List<Integer> parseWinningNumbers(List<String> splittedNumbers) {
        try {
            return splittedNumbers.stream()
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 숫자로만 구성되어야 합니다.");
        }
    }

    private static void validateBonusNumberFormat(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호 1개를 입력해야 합니다.");
        }
    }

    private static void validateBonusNumberRange(int bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    private static int parseAndValidateBonusNumber(String input) {
        try {
            int bonusNumber = Integer.parseInt(input);
            validateBonusNumberRange(bonusNumber); // 범위 검증 분리
            return bonusNumber;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 문자가 아닌 1부터 45 사이의 숫자여야 합니다.");
        }
    }
}
