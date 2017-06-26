package beans;

import daos.MessageDAO;
import tables.Message;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by dima on 25.06.17.
 */

@Named
@SessionScoped
public class MessageBean implements Serializable {

    @EJB
    private MessageDAO messageDAO;

    private Message tempMessage = new Message();

    private int senderId;
    private int receiverId;
    private Boolean sendToEverybody;


    public void sentMessage() {
        messageDAO.addMessage(tempMessage);
        if (sendToEverybody)
            messageDAO.sendMessageToEverybody(senderId, tempMessage.getId());
        else
            messageDAO.sendMessage(senderId, receiverId, tempMessage.getId());
        tempMessage.setTitle("");
        tempMessage.setText("");
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
        System.out.println(senderId);
    }

    public int getSenderId() {
        return senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public MessageDAO getMessageDAO() {
        return messageDAO;
    }

    public void setMessageDAO(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    public Message getTempMessage() {
        return tempMessage;
    }

    public void setTempMessage(Message tempMessage) {
        this.tempMessage = tempMessage;
    }

    public void setSendToEverybody(Boolean sendToEverybody) {
        this.sendToEverybody = sendToEverybody;
    }

    public Boolean getSendToEverybody() {
        return sendToEverybody;
    }
}
