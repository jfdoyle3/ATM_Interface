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
        this.uuid=theBank.getNewAccountUUID();
        this.transactions=new ArrayList<Transaction>();


    }

    public String getUUId() {
        return this.uuid;
    }
}
