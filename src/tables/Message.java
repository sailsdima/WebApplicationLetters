package tables;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by dima on 24.06.17.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Message.FindAll", query = "SELECT m FROM Message m"),
        @NamedQuery(name = "Message.findMessageIdBySenderIdAndTitle",
        query = "select s.message.id from SentMessage s where s.sender.id = :senderId and s.message.title = :tempTitle")
})
public class Message {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Basic
    private String title;

    @Basic
    private String text;

    @OneToOne(mappedBy = "message", optional = false)
    private SentMessage sentMessage;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }



    public SentMessage getSentMessage() {
        return sentMessage;
    }

    public void setSentMessage(SentMessage sentMessage) {
        this.sentMessage = sentMessage;
    }
}
