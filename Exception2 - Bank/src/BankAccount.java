class BankAccount {
    private String numerKonta;
    private String wlasciciel;
    private double saldo;

    public BankAccount(String numerKonta, String wlasciciel, double saldo){
        this.numerKonta = numerKonta;
        this.wlasciciel = wlasciciel;
        this.saldo = saldo;
    }

    public void wplata(double kwota) throws IllegalArgumentException{
        if (kwota <= 0){
            throw new IllegalArgumentException("Kwota wpłaty musi być większa od zera.");
        }
        saldo += kwota;
    }

    public void wyplata(double kwota) throws IllegalArgumentException{
        if (kwota <= 0){
            throw new IllegalArgumentException("Kwota wypłaty musi być większa od zera.");
        }
        if (kwota > saldo){
            throw new IllegalArgumentException("Brak wystarczających środków na koncie.");
        }
        saldo -= kwota;
    }
    public String getNumerKonta(){
        return numerKonta;
    }
    public String getWlasciciel(){
        return wlasciciel;
    }
    public double getSaldo(){
        return saldo;
    }
}
