package construct;

import tables.Message;
import tables.Person;

/**
 * Created by dima on 26.06.17.
 */
public class PersonMessagesLengthInfo {

    private Person person;
    private Message message;
    private int messageLength;

    public PersonMessagesLengthInfo(Person person, Message message, int messageLength) {
        this.person = person;
        this.message = message;
        this.messageLength = messageLength;
    }

    public int getMessageLength() {
        return messageLength;
    }

    public void setMessageLength(int messageLength) {
        this.messageLength = messageLength;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
