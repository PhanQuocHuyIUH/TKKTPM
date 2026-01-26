package bai2;

public class Main {
    public static void main(String[] args) {
        TaxCalculator calculator =
                new TaxCalculator(new VatTax());

        System.out.println(calculator.calculate(1000));
    }
}
