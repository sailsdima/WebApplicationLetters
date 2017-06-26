package tables;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by dima on 25.06.17.
 */

@Entity
public class SentMessage {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Basic
    private Date dispatchDate;

    @ManyToOne(optional = false)
    private Person receiver;

    @ManyToOne(optional = false)
    private Person sender;

    @OneToOne(optional = false)
    private Message message;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Date getDispatchDate() {
        return dispatchDate;
    }

    public void setDispatchDate(Date dispatchDate) {
        this.dispatchDate = dispatchDate;
    }

    public Person getSender() {
        return sender;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }



    public Person getReceiver() {
        return receiver;
    }

    public void setReceiver(Person receiver) {
        this.receiver = receiver;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
