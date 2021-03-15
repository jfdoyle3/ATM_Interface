import java.util.Scanner;

public class ATM {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Bank theBank = new Bank("Bank of Drausin");

        User aUser = theBank.addUser("John", "Doe", "1234");

        Account newAccount = new Account("Checking", aUser, theBank);
        aUser.addAccount(newAccount);
        theBank.addAccount(newAccount);

        User curUser;
        while (true) {
            curUser = ATM.mainMenuPrompt(theBank, sc);

            ATM.printUserMenu(curUser, sc);
        }
    }


    public static User mainMenuPrompt(Bank theBank, Scanner sc) {
        String userID;
        String pin;
        User authUser;

        do {
            System.out.printf("\n\nWelcome to %s\n\n", theBank.getName());
            System.out.println("Enter user ID: ");
            userID = sc.nextLine();
            System.out.println("Enter pin: ");
            pin = sc.nextLine();

            authUser = theBank.userLogin(userID, pin);
            if (authUser == null) {
                System.out.println("Incorrect user ID/pin combination.\nPlease try again");
            }

        } while (authUser == null);
        return authUser;
    }

    public static void printUserMenu(User theUser, Scanner sc) {

        theUser.printAccountsSummary();

        int choice;
        do {
            System.out.printf("Welcome %s, what would you like to do?\n", theUser.getFirstName());
            System.out.println("  1) Show account transaction history");
            System.out.println("  2) Withdraw");
            System.out.println("  3) Deposit");
            System.out.println("  4) Transfer");
            System.out.println("  5) Quit");
            System.out.println("\nEnter choice:  ");
            choice = sc.nextInt();

            if (choice < 1 || choice > 5) {
                System.out.println("Invalid choice. Please choose 1-5");
            }
        } while (choice < 1 || choice > 5);

        switch (choice) {
            case 1:
                ATM.showTransHistory(theUser, sc);
                break;
            case 2:
                ATM.withdrawFunds(theUser, sc);
                break;
            case 3:
                ATM.depositFunds(theUser, sc);
                break;
            case 4:
                ATM.transferFunds(theUser, sc);
                break;
        }

        if (choice != 5) {
            ATM.printUserMenu(theUser, sc);
        }

    }

    private static void showTransHistory(User theUser, Scanner sc) {
        int account;
        do {
            System.out.printf("Enter the number (1-%d) of the account\n" +
                    "whose transaction you want to see: ", theUser.numAccounts());
            account = sc.nextInt() - 1;
            if (account < 0 || account >= theUser.numAccounts()) {
                System.out.println("Invalid Account. Please try again.");
            }
        } while (account < 0 || account >= theUser.numAccounts());
        theUser.printAcctTransHistory(account);
    }

    private static void transferFunds(User theUser, Scanner sc) {
        int fromAccount;
        int toAccount;
        double amount;
        double accountBalance;
        do {
            System.out.println("Enter the number (1-%d) of the account\n" +
                    "to transfer from: ");
            fromAccount = sc.nextInt() - 1;
            if (fromAccount < 0 || fromAccount >= theUser.numAccounts()) {
                System.out.println("Invalid Account. Please try again.");
            }
        } while (fromAccount < 0 || fromAccount >= theUser.numAccounts());
        accountBalance = theUser.getAccountBalance(fromAccount);

        do {
            System.out.println("Enter the number (1-%d) of the account\n" +
                    "to transfer to: ");
            toAccount = sc.nextInt() - 1;
            if (toAccount < 0 || toAccount >= theUser.numAccounts()) {
                System.out.println("Invalid Account. Please try again.");
            }
        } while (toAccount < 0 || toAccount >= theUser.numAccounts());


        do {
            System.out.printf("Enter the amount to transfer (max $%.02f): $", accountBalance);
            amount = sc.nextDouble();
            if (amount < 0) {
                System.out.println("Amount must be greater than zero.");
            } else if (amount > accountBalance) {
                System.out.printf("Amount must not be greater than\n" +
                        "balance of $%.02f.\n", accountBalance);
            }
        } while (amount < 0 || amount > accountBalance);
        theUser.addAccountTransaction(fromAccount, -1 * amount, String.format(
                "Transfer to account %s", theUser.getAccountUUId(toAccount)));
        theUser.addAccountTransaction(toAccount, amount, String.format(
                "Transfer to account %s", theUser.getAccountUUId(fromAccount)));

    }

    private static void withdrawFunds(User theUser, Scanner sc) {
        int fromAccount;
        double amount;
        double accountBalance;
        String memo;

        do {
            System.out.println("Enter the number (1-%d) of the account\n" +
                    "to transfer from: ");
            fromAccount = sc.nextInt() - 1;
            if (fromAccount < 0 || fromAccount >= theUser.numAccounts()) {
                System.out.println("Invalid Account. Please try again.");
            }
        } while (fromAccount < 0 || fromAccount >= theUser.numAccounts());
        accountBalance = theUser.getAccountBalance(fromAccount);

        do {
            System.out.printf("Enter the amount to transfer (max $%.02f): $", accountBalance);
            amount = sc.nextDouble();
            if (amount < 0) {
                System.out.println("Amount must be greater than zero.");
            } else if (amount > accountBalance) {
                System.out.printf("Amount must not be greater than\n" +
                        "balance of $%.02f.\n", accountBalance);
            }
        } while (amount < 0 || amount > accountBalance);

        do {
            System.out.printf("Enter the amount to transfer (max $%.02f): $", accountBalance);
            amount = sc.nextDouble();
            if (amount < 0) {
                System.out.println("Amount must be greater than zero.");
            } else if (amount > accountBalance) {
                System.out.printf("Amount must not be greater than\n" +
                        "balance of $%.02f.\n", accountBalance);
            }
        } while (amount < 0 || amount > accountBalance);

        sc.nextLine();
        System.out.println("Enter a memo: ");
        memo=sc.nextLine();

        theUser.addAccountTransaction(fromAccount,-1*amount, memo);

    }

    private static void depositFunds(User theUser, Scanner sc) {
        int toAccount;
        double amount;
        double accountBalance;
        String memo;

        do {
            System.out.println("Enter the number (1-%d) of the account\n" +
                    "to transfer from: ");
            toAccount = sc.nextInt() - 1;
            if (toAccount < 0 || toAccount >= theUser.numAccounts()) {
                System.out.println("Invalid Account. Please try again.");
            }
        } while (toAccount < 0 || toAccount >= theUser.numAccounts());
        accountBalance = theUser.getAccountBalance(toAccount);

        do {
            System.out.printf("Enter the amount to transfer (max $%.02f): $", accountBalance);
            amount = sc.nextDouble();
            if (amount < 0) {
                System.out.println("Amount must be greater than zero.");
            } else if (amount > accountBalance) {
                System.out.printf("Amount must not be greater than\n" +
                        "balance of $%.02f.\n", accountBalance);
            }
        } while (amount < 0 || amount > accountBalance);

        do {
            System.out.printf("Enter the amount to transfer (max $%.02f): $", accountBalance);
            amount = sc.nextDouble();
            if (amount < 0) {
                System.out.println("Amount must be greater than zero.");
            } else if (amount > accountBalance) {
                System.out.printf("Amount must not be greater than\n" +
                        "balance of $%.02f.\n", accountBalance);
            }
        } while (amount < 0 || amount > accountBalance);

        sc.nextLine();
        System.out.println("Enter a memo: ");
        memo=sc.nextLine();

        theUser.addAccountTransaction(toAccount,amount, memo);
    }


}
