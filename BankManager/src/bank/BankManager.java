package bank;

public class BankManager{

    public void printBalance(BankAccount account){
        System.out.println("Saldo użytkownika " + account.owner + ": " + account.balance + " PLN");
    }

    public boolean transfer(BankAccount from, BankAccount to, double amount){
        if (amount > 0 && from.balance >= amount){
            from.setBalance(from.balance - amount);
            to.setBalance(to.balance + amount);
            System.out.println("Przelew: " + amount + " PLN z " + from.owner + " do " + to.owner);
            return true;
        } else{
            System.out.println("Niewystarczające środki lub błędna kwota.");
            return false;
        }
    }
}
