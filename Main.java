import java.util.Scanner;

public class Main {

    private final static Scanner input = new Scanner(System.in);

    private static BankAccount account;

    public static void main(String[] args) {

        System.out.println("Digite o valor do deposito inicial:");
        var initialDeposit = input.nextDouble();
        account = new BankAccount(initialDeposit);

        var option = -1;

        do{
            System.out.println("===Escolha uma das opcoes===");
            System.out.println("1 - Consultar saldo");
            System.out.println("2 - Consultar cheque especial");
            System.out.println("3 - Depositar dinheiro");
            System.out.println("4 - Sacar dinheiro");
            System.out.println("5 - Pagar um boleto");
            System.out.println("6 - Verificar se a conta está usando cheque especial");
            System.out.println("0 - Sair");
            option = input.nextInt();

            switch(option) {
                case 1 -> System.out.println("Saldo atual: R$ " + account.getBalance());
                case 2 -> System.out.println("Cheque especial disponivel: R$ " + account.getOverdraftAvailable());
                case 3 -> deposit();
                case 4 -> withdraw();
                case 5 -> payBill();
                case 6 -> isUsingOverdraft();
                case 0 -> System.exit(0);
                default -> System.out.println("Opcao invalida");
            }
        } while(true);
    }

    private static void deposit() {
        System.out.println("Digite o valor do deposito:");
        var amount = input.nextDouble();
        account.deposit(amount);
    }

    private static void withdraw() {
        System.out.println("Digite o valor do saque:");
        var amount = input.nextDouble();
        account.withdraw(amount);
    }

    private static void payBill() {
        System.out.println("Digite o valor da conta:");
        var amount = input.nextDouble();
        account.payBill(amount);
    }

    private static void isUsingOverdraft() {
        if (account.isUsingOverdraft())
            System.out.println("Voce esta usando o cheque especial");
        else
            System.out.println("Voce nao esta usando o cheque especial");
    }
}
