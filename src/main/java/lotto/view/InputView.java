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

    public static List<String> readWinningNumbers() {
        System.out.println("당첨 번호를 입력해 주세요.");
        String input = Console.readLine();

        List<String> winningNums = Arrays.stream(input.split(","))
                .map(String::trim)
                .toList();

        return winningNums;
    }

    public static int readBonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요.");
        String input = Console.readLine();

        return Integer.parseInt(input.trim());
    }
}
