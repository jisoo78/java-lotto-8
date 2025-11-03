package lotto;

import camp.nextstep.edu.missionutils.Console;

public class Input {

    public String lottoPrice() {
        System.out.println("구입금액을 입력해 주세요.");
        return Console.readLine();
    }

    public void errorMessage(String message) {
        System.out.println(message);
    }
}
