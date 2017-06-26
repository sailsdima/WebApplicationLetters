package construct;

import tables.Person;

/**
 * Created by dima on 26.06.17.
 */
public class PersonMessages {

    private Person person;
    private long sentMessages;
    private long receivedMessages;

    public PersonMessages(Person person, long sentMessages) {
        this.person = person;
        this.sentMessages = sentMessages;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public long  getSentMessages() {
        return sentMessages;
    }

    public void setSentMessages(long  sentMessages) {
        this.sentMessages = sentMessages;
    }

    public long  getReceivedMessages() {
        return receivedMessages;
    }

    public void setReceivedMessages(long  receivedMessages) {
        this.receivedMessages = receivedMessages;
    }
}
