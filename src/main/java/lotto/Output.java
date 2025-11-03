package lotto;

import java.util.List;

public class Output {

    public void printLottos(int count, List<Lotto> lottos) {
        System.out.println(count + "개를 구매했습니다.");
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    // 로또번호목록
    private void printLottoNumbers(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }
    }
}
