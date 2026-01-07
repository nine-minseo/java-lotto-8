package lotto.view;

import java.util.List;

public class InputValidator {
    public static void validateHasInput(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 입력이 비어있습니다.");
        }
    }
}