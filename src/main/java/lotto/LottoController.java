package lotto;

public class LottoController {

    private final Input input;
    private LottoPrice lottoPrice;

    public LottoController() {
        this.input = new Input();
    }

    public void run() {
        getPurchaseAmountFromUser();
        System.out.println("\n" + lottoPrice.getLottoCount() + "개를 구매했습니다. (다음 단계 진행)");
    }

    private void getPurchaseAmountFromUser() {
        while (lottoPrice == null) {
            processPurchaseAmountInput();
        }
    }

    private void processPurchaseAmountInput() {
        try {
            String amountInput = input.lottoPrice();
            lottoPrice = new LottoPrice(amountInput);
        } catch (IllegalArgumentException e) {
            input.errorMessage(e.getMessage());
        }
    }

    public LottoPrice getPurchaseAmount() {
        return lottoPrice;
    }
}
