package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;

public class InputView {
    public static int readPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = Console.readLine();

        return Integer.parseInt(input.trim());
    }

    public static List<Integer> readWinningNumbers() {
        System.out.println("\n당첨 번호를 입력해 주세요.");
        String input = Console.readLine();

        List<Integer> winningNums = Arrays.stream(input.split(","))
                .map(s -> s.trim())
                .map(s -> Integer.parseInt(s))
                .toList();

        return winningNums;
    }

    public static int readBonusNumber() {
        System.out.println("\n보너스 번호를 입력해 주세요.");
        String input = Console.readLine();

        return Integer.parseInt(input.trim());
    }
}
