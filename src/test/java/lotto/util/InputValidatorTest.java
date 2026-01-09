package lotto.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputValidatorTest {

    @Test
    void 숫자가_아닌_문자를_입력하면_예외가_발생한다() {
        // given
        String invalidInput = "1000j";

        // when, then
        assertThatThrownBy(() -> InputValidator.validateIsNumeric(invalidInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 구입_금액이_1000원_단위가_아니면_예외가_발생한다() {
        // given
        int invalidAmount = 1500;

        // when, then
        assertThatThrownBy(() -> InputValidator.validatePurchaseUnit(invalidAmount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 빈_값을_입력하면_예외가_발생한다() {
        // given
        String emptyInput = "";

        // when, then
        assertThatThrownBy(() -> InputValidator.validateHasInput(emptyInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 숫자가_양수가_아니면_예외가_발생한다() {
        // given
        int negativeNumber = -1000;

        // when, then
        assertThatThrownBy(() -> InputValidator.validateIsPositive(negativeNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }
}