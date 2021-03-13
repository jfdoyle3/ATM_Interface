import java.util.Scanner;

public class ATM {

    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);

        Bank theBank=new Bank("Bank of Drausin");

        User aUser= theBank.addUser("John", "Doe", "1234");

        Account newAccount=new Account("Checking", aUser, theBank);
        aUser.addAccount(newAccount);
        theBank.addAccount(newAccount);

        User curUser;
        while(true){
            curUser=ATM.mainMenuPrompt(theBank, sc);
            
            ATM.printUserMenu(curUser, sc);
        }
    }



    public static User mainMenuPrompt(Bank theBank, Scanner sc) {
        String userID;
        String pin;
        User authUser;

        do{
            System.out.printf("\n\nWelcome to %s\n\n", theBank.getName());
            System.out.println("Enter user ID: ");
            userID=sc.nextLine();
            System.out.println("Enter pin: ");
            pin=sc.nextLine();

            authUser=theBank.userLogin(userID,pin);
            if(authUser==null){
                System.out.println("Incorrect user ID/pin combination.\nPlease try again");
            }

        }while(authUser==null);
        return authUser;
    }
    public  static void printUserMenu(User theUser, Scanner sc) {

        theUser.printAccountSummary();

        int choice;
        do{
            System.out.printf("Welcome %s, what would you like to do?",theUser.getFirstName());
            System.out.println("  1) Show account transaction history");
            System.out.println("  2) Withdrawal");
            System.out.println("  3) Deposit");
            System.out.println("  4) Transfer");
            System.out.println("  5) Quit");
            System.out.println("\nEnter choice:  ");
            choice=sc.nextInt();

            if(choice<1 || choice>5){
                System.out.println("Invalid choice. Please choose 1-5");
            }
        } while (choice<1 || choice>5);

        switch (choice){
            case 1:
                ATM.showTransHistory(theUser,sc);
                break;
            case 2:
                ATM.withdrawFunds(theUser,sc);
                break;
            case 3:
                ATM.depositFunds(theUser,sc);
                break;
            case 4:
                ATM.transferFunds(theUser,sc);
                break;
        }

        if(choice !=5){
            ATM.printUserMenu(theUser, sc);
        }

    }

    private static void transferFunds(User theUser, Scanner sc) {
    }

    private static void depositFunds(User theUser, Scanner sc) {
    }

    private static void withdrawFunds(User theUser, Scanner sc) {
    }

    private static void showTransHistory(User theUser, Scanner sc) {
    }
}