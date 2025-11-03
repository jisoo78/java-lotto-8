package lotto;

public class LottoPrice {

    private static final int LOTTO_PRICE = 1000;

    private final int price;

    public LottoPrice(String input) {
        int lottoPrice = lottoPrice(input);
        validate(lottoPrice);
        this.price = lottoPrice;
    }

    private int lottoPrice(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 숫자여야 합니다.");
        }
    }

    private void validate(int price) {
        validatePlus(price);
        validateUnit(price);
    }

    private void validatePlus(int price) {
        if (price <= 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 양수여야 합니다.");
        }
    }


    private void validateUnit(int price) {
        if (price % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위로 입력해 주세요.");
        }
    }

    public int getLottoCount() {
        return price / LOTTO_PRICE;
    }

    public int getPrice() {
        return price;
    }
}
