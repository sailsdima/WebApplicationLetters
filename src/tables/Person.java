package tables;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by dima on 24.06.17.
 */

//SELECT PERSON.ID, sent.mesCount, received.mesCount
//        FROM
//        PERSON,
//        (SELECT PERSON.ID, count(SENTMESSAGE.SENDER_ID) as mesCount
//        from PERSON LEFT JOIN SENTMESSAGE ON PERSON.ID = SENTMESSAGE.SENDER_ID
//        GROUP BY PERSON.ID) as sent,
//        (SELECT PERSON.ID, count(SENTMESSAGE.RECEIVER_ID) as mesCount
//        from PERSON LEFT JOIN SENTMESSAGE ON PERSON.ID = SENTMESSAGE.RECEIVER_ID
//        GROUP BY PERSON.ID) as received
//        WHERE PERSON.ID = sent.ID AND PERSON.ID = received.ID

@Entity
@NamedQueries({
        @NamedQuery(name = "Person.FindAll",
                query = "SELECT p FROM Person p"),


//    SELECT PERSON.ID, sum(length(TEXT)) as ssum from (PERSON INNER JOIN SENTMESSAGE ON PERSON.ID = SENTMESSAGE.SENDER_ID) INNER JOIN MESSAGE ON MESSAGE_ID = MESSAGE.ID
//    GROUP BY PERSON.ID
//    ORDER BY ssum
//    LIMIT 1
        @NamedQuery(name = "Person.PeopleWhoReceivedMessageWithTitle",
                query = "SELECT distinct p FROM Person p, Message m, SentMessage s WHERE p.id = s.receiver.id AND s.message.id = m.id AND m.title = :title"),

        @NamedQuery(name = "Person.PeopleWhoNotReceivedMessageWithTitle",
                query = "select distinct p1 from Person p1 where p1.id not in (SELECT p.id FROM Person p, Message m, SentMessage s WHERE p.id = s.receiver.id AND s.message.id = m.id AND m.title = :title)"),

        @NamedQuery(name = "Person.FindPersonAndMessagesInfo",
                query = "SELECT new construct.PersonMessagesLengthInfo(p, s.message, length(s.message.text)) from Person p, SentMessage s where p.id = s.sender.id"),

        @NamedQuery(name = "Person.FindPersonSentMessagesInfo",
                query = "SELECT NEW construct.PersonMessages(p, count(s.sender)) FROM Person p left outer join SentMessage s where (p.id = s.sender.id) GROUP BY p.id"),

        @NamedQuery(name = "Person.FindPersonReceivedMessagesInfo",
                query = "SELECT NEW construct.PersonMessages(p, count(s.receiver)) FROM Person p left outer join SentMessage s where (p.id = s.receiver.id) GROUP BY p.id"),

})
public class Person {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Basic
    private String name;

    @Basic
    private int birthdayYear;


    @OneToMany(mappedBy = "receiver")
    private Collection<SentMessage> receivedMessages;

    @OneToMany(mappedBy = "sender")
    private Collection<SentMessage> sentMessages;

    public void setSentMessages(Collection<SentMessage> sentMessages) {
        this.sentMessages = sentMessages;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getBirthdayYear() {
        return birthdayYear;
    }

    public void setBirthdayYear(int birthdayYear) {
        this.birthdayYear = birthdayYear;
    }


    public Collection<SentMessage> getSentMessages() {
        return sentMessages;
    }


    public Collection<SentMessage> getReceivedMessages() {
        return receivedMessages;
    }

    public void setReceivedMessages(Collection<SentMessage> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }
}
