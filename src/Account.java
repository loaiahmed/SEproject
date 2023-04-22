import java.util.*;

public class Account {
    private String userName;
    private final int accountID;
    private String emailAddress;
    private String password;
    static int count = 0;

    public Account(String userName, String emailAddress, String password) {
        this.accountID = count;
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.password = password;
        count++;
    }

    public Account(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.accountID = count;
        this.password = password;
        count++;
    }

    public Account() {
        this.accountID = count;
        count++;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAccountID() {
        return accountID;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountID == account.accountID && Objects.equals(userName, account.userName) && Objects.equals(emailAddress, account.emailAddress) && Objects.equals(password, account.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, accountID, emailAddress, password);
    }

    @Override
    public String toString() {
        return "Account{" +
                "userName='" + userName + '\'' +
                ", accountID=" + accountID +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
