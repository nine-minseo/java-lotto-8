package lotto;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        List<Integer> copyNumbers = new ArrayList<>(numbers);
        this.numbers = Collections.unmodifiableList(copyNumbers);
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    public List<Integer> getNumbers() {
        return this.numbers;
    }
}
