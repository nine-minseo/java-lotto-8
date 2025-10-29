package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String PROMPT_PURCHASE_AMOUNT = "구입금액을 입력해 주세요.";

    public static String getPurchaseAmount() {
        System.out.println(PROMPT_PURCHASE_AMOUNT);

        return Console.readLine();
    }
}
