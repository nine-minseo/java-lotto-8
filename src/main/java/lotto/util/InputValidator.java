package lotto.util;

public class InputValidator {
    public static void validateHasInput(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 입력이 비어있습니다.");
        }
    }

    public static int validateIsNumeric(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자가 아닌 문자가 포함되어 있습니다.");
        }
    }

    public static void validateIsPositive(int input) {
        if (input <= 0) {
            throw new IllegalArgumentException("[ERROR] 양수만 입력할 수 있습니다.");
        }
    }

    public static void validatePurchaseUnit(int amount) {
        if (amount % 1000 != 0) {
            throw new IllegalArgumentException("[Error] 금액은 1,000원 단위여야 합니다.");
        }
    }

    public static void validateNumberRange(int num) {
        if (num < 1 || num > 45) {
            throw new IllegalArgumentException("[ERROR] 1 ~ 45 사이의 숫자여야 합니다.");
        }
    }
}