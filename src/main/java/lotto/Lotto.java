package lotto;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;
    private static final String ERROR_MESSAGE = "[ERROR] 1부터 45 사이의 중복되지 않은 로또 번호 6개를 입력해야 합니다.";

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        List<Integer> copyNumbers = new ArrayList<>(numbers);
        this.numbers = Collections.unmodifiableList(copyNumbers);
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }

        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != 6) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }

        if (hasOutOfRange(numbers)) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    private boolean hasOutOfRange(List<Integer> numbers) {
        return numbers.stream()
                .anyMatch(number -> number < 1 || number > 45);
    }

    public List<Integer> getNumbers() {
        return this.numbers;
    }
}
