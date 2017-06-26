package construct;

import tables.Person;

/**
 * Created by dima on 26.06.17.
 */
public class PersonMessages {

    private Person person;
    private long messagesCount;

    public PersonMessages(Person person, long messagesCount) {
        this.person = person;
        this.messagesCount = messagesCount;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public long getMessagesCount() {
        return messagesCount;
    }

    public void setMessagesCount(long messagesCount) {
        this.messagesCount = messagesCount;
    }
}
