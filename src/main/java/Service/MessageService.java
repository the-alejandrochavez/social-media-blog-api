package Service;

import DAO.MessageDAO;
import DAO.AccountDAO;
import Model.Message;

public class MessageService {
    public MessageDAO mesDAO;
    public AccountDAO accDAO;

    public MessageService(){
        mesDAO = new MessageDAO();
        accDAO = new AccountDAO();
    }

    public Message newMessage(Message message) {
        if(message.getMessage_text().length() > 0 && message.getMessage_text().length() < 255) {
            if(accDAO.getAccountById(message.getPosted_by()) != null) {
                return mesDAO.createMessage(message);
            }
        }
        return null;
    }
    
}
