package lotto;

import java.util.List;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class LottoController {

    private final Input input;
    private LottoPrice lottoPrice;
    private final Output output;

    public LottoController() {
        this.input = new Input();
        this.output = new Output();
    }

    public void run() {
        getPurchaseAmountFromUser();

        int count = lottoPrice.getLottoCount();

        LottoGenerator generator = new LottoGenerator();
        LottoTickets lottoTickets = new LottoTickets(count, generator);

        output.printLottos(count, lottoTickets.getLottos());

        Lotto winningLotto = getWinningLottoFromUser();
        int bonusNumber = getBonusNumberFromUser(winningLotto);

        WinningLotto winning = new WinningLotto(winningLotto, bonusNumber);
        LottoResult result = new LottoResult(lottoTickets, winning);

        printResult(result);
    }

    private Lotto getWinningLottoFromUser() {
        System.out.println("당첨 번호를 입력해 주세요.");
        String inputNumbers = readLine();
        List<Integer> winningNumbers = InputParser.parseWinningNumbers(inputNumbers);
        return new Lotto(winningNumbers);
    }
    private int getBonusNumberFromUser(Lotto winningLotto) {
        System.out.println("보너스 번호를 입력해 주세요.");
        String bonusInput = readLine();
        return InputParser.parseBonusNumber(bonusInput);
    }

    private void printResult(LottoResult result) {
        System.out.println("당첨 통계");
        System.out.println("---");

        result.getStatistics().forEach((rank, count) -> {
            System.out.println(rank.getMessage() + " - " + count + "개");
        });

        double yield = result.calculateYield(lottoPrice.getPrice());
        System.out.println("총 수익률은 " + yield + "%입니다.");
    }

    private void getPurchaseAmountFromUser() {
        while (lottoPrice == null) {
            try {
                String amountInput = input.lottoPrice();
                lottoPrice = new LottoPrice(amountInput);
            } catch (IllegalArgumentException e) {
                input.errorMessage(e.getMessage());
            }
        }
    }


}


