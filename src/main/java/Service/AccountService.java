package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    public AccountDAO accDAO;

    public AccountService(){
        accDAO = new AccountDAO();
    }

    public Account addAccount(Account account) {
        if(accDAO.getAccountById(account.getAccount_id()) == null && account.getPassword().length() > 4 && account.getUsername().length() > 0) {
            return accDAO.createAccount(account);
        }
        return null;
    }

    public Account accountExists(Account account) {
        if(accDAO.checkAccount(account.getUsername(), account.getPassword()) != null) {
            return accDAO.checkAccount(account.getUsername(), account.getPassword());
        }
        return null;
    }
}
