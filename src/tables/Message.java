package tables;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by dima on 24.06.17.
 */
@Entity
@NamedQueries(
        @NamedQuery(name = "Message.getAll", query = "SELECT m FROM Message m")
)
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
