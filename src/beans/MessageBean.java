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
    private String tempTitle;
    private String statusResendingMessage;


    public String sentMessage() {
        messageDAO.addMessage(tempMessage);
        if (sendToEverybody)
            messageDAO.sendMessageToEverybody(senderId, tempMessage.getId());
        else
            messageDAO.sendMessage(senderId, receiverId, tempMessage.getId());
        tempMessage.setTitle("");
        tempMessage.setText("");
        return null;
    }

    public String resendMessage() {
        if (messageDAO.resendMessageToEvb(senderId, tempTitle)) {
            statusResendingMessage = "Resent successfully";
        } else
            statusResendingMessage = "Something went wrong. Probably selected used did not sent message with title '" + tempTitle + "'";
        tempTitle = "";
        return null;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
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

    public void setTempTitle(String tempTitle) {
        this.tempTitle = tempTitle;
    }

    public String getTempTitle() {
        return tempTitle;
    }

    public String getStatusResendingMessage() {
        return statusResendingMessage;
    }

    public void setStatusResendingMessage(String statusResendingMessage) {
        this.statusResendingMessage = statusResendingMessage;
    }
}
