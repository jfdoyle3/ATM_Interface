import java.util.ArrayList;

public class Account {

    // Name: Checking / Savings account
    private String accountName;

    private double balance;

    // Account ID number
    private String uuid;

    private User holder;

    private ArrayList<Transaction> transactions;

    public Account(String accountName, User holder, Bank theBank) {
        this.accountName = accountName;
        this.holder = holder;

        // get new account UUID
        this.uuid = theBank.getNewAccountUUID();
        this.transactions = new ArrayList<Transaction>();
    }

    public String getUUId() {
        return this.uuid;
    }

    public String getSummaryLine(){
        double balance=this.getBalance();
        if(balance>=0){
            return String.format("%s : $%.02f : %s",this.uuid, balance, this.accountName);
        }
        else{
            return String.format("%s : $(%.02f) : %s",this.uuid, balance, this.accountName);
        }
    }

    private double getBalance() {
        double balance=0;
        for(Transaction t : this.transactions){
            balance += t.getAmount();
        }
        return balance;
    }

    public void printTransHistory() {
        System.out.printf("\nTransaction history for account %s\n",this.uuid);
        for(int t=this.transactions.size()-1; t>=0; t--){
            System.out.print(this.transactions.get(t).getSummaryline());
        }
        System.out.println();
    }
}
