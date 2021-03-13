import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User {

    private String firstName;
    private String lastName;

    // User ID number
    private String uuid;

    // The MD5 hash of the user's pin number
    private byte pinHash[];
    private ArrayList<Account> accounts;

    public User(String firstName, String lastName, String pin, Bank theBank) {
        this.firstName = firstName;
        this.lastName = lastName;

        // Store the pin's MD5 hash format for security purpose then to have in original value
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }

        // get a new, unique universal ID (UUID) for the user
        this.uuid=theBank.getNewUserUUID();

        // create empty acount list
        this.accounts= new ArrayList<Account>();

        //print log message
        System.out.printf("New user: %s, %s with ID %s created.\n", lastName,firstName,this.uuid);

    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public String getUUId() {
        return this.uuid;
    }

    public boolean validatePin(String pin) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(pin.getBytes()),this.pinHash);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }
        return false;

    }

    public void printAccountSummary() {
    }

    public String getFirstName() {
        return this.firstName;
    }


}
