public class BankAccount {

    private double balance;
    private double overdraftAvailable;
    private double overdraftLimit;
    private double overdraftUsed;


    public BankAccount(double initialDeposit) {
        this.balance = initialDeposit;

        if (initialDeposit <= 500.0) this.overdraftLimit = 50.0;

        else overdraftLimit = initialDeposit * 0.5;

        this.overdraftAvailable = this.overdraftLimit;
        this.overdraftUsed = 0;
    }

    public double getBalance() {
        return balance;
    }

    public double getOverdraftAvailable() {
        return overdraftAvailable;
    }

    public boolean isUsingOverdraft() {
        return overdraftUsed > 0; //Se maior que 0, entao retorna true
    }


    public void deposit(double amount) {
        if (amount < 0) {
            System.out.println("Valor invalido para deposito");
            return;
        }

        if (overdraftUsed > 0) {
            var fee = overdraftUsed * 0.2;
            var totalDebt = overdraftUsed + fee;

            if (amount > overdraftUsed) {
                amount -= totalDebt;
                overdraftUsed = 0;
                overdraftAvailable = overdraftLimit;
                System.out.println("Cheque especial quitado com taxa de R$ " + fee);
            } else {
                overdraftAvailable = overdraftLimit - overdraftUsed;
                System.out.println("Parte do cheque especial foi quitado");
                return; //Encerra o metodo, sem somar nada ao saldo, pois o deposito foi usado apenas para reduzir a divida
            }
        }

        balance += amount;
        System.out.println("Deposito realizado com sucesso");
    }

    public void withdraw(double amount) {

       if (amount < 0) {
           System.out.println("Valor invalido para saque");
           return;
       }

       if (amount <= balance) {
            balance -= amount;
       } else {
            var totalAvailable = balance + overdraftAvailable;

            if (amount <= totalAvailable) {
                var remaining = amount - balance;
                balance = 0;
                overdraftAvailable -= remaining;
                overdraftUsed += remaining;
                System.out.println("Saque realizado. Usando R$ " + remaining + " do cheque especial");
            } else {
                System.out.println("Saldo insuficiente. Incluindo o cheque especial");
            }
       }
    }

    public void payBill(double amount) {
        System.out.println("Tentando pagar boleto no valor de R$ " + amount);
        withdraw(amount);
    }

}
