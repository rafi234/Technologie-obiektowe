public class Currency {

    private final String nazwa_waluty;
    private final String kod_waluty;
    private final double przelicznik;
    private final double kurs_sredni;

    Currency(String nazwa_waluty, double przelicznik, String kod_waluty, double kurs_sredni) {
        this.kod_waluty = kod_waluty;
        this.kurs_sredni = kurs_sredni;
        this.nazwa_waluty = nazwa_waluty;
        this.przelicznik = przelicznik;
    }

    @Override
    public String toString() {
        return "Nazwa waluty: " + this.nazwa_waluty + "\n" +
                "Kod waluty: " + this.kod_waluty + "\n" +
                "Średni kurs: " + this.kurs_sredni + "\n" +
                "Przelicznik: " + this.przelicznik + "\n";
    }

    public String getNazwa_waluty(){
        return this.nazwa_waluty;
    }

    public String getKod_waluty(){
        return this.kod_waluty;
    }

    public double getPrzelicznik(){
        return this.przelicznik;
    }

    public double getKurs_sredni(){
        return this.kurs_sredni;
    }

    public Boolean checkCurrencyCode(String currencyName, Currency currency){
        return currency.kod_waluty.equals(currencyName);
    }
}
