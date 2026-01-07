package lotto;

import java.util.Collections;
import java.util.List;
import javax.print.attribute.standard.NumberUp;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateLottoNumberRange(numbers);
        validate(numbers);
        this.numbers = numbers;
    }

    private void validateLottoNumberRange(List<Integer> numbers) {
        for (int number : numbers) {
            if (number < 1 || number > 45) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1 ~ 45 사이의 숫자여야 합니다.");
            }
        }
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    public int countMatch(Lotto winningNumbers) {
        return (int) numbers.stream()
                .filter(num -> winningNumbers.contains(num))
                .count();
    }

    public boolean contains(int num) {
        return numbers.contains(num);
    }
}
