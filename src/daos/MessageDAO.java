package daos;

import tables.Message;
import tables.Person;
import tables.SentMessage;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * Created by dima on 25.06.17.
 */

@Stateless
public class MessageDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Message> getAll() {
        return entityManager.createNamedQuery("Message.FindAll").getResultList();
    }

    public Message addMessage(Message message) {
        entityManager.persist(message);
        return message;
    }

    public boolean sendMessage(int senderId, int receiverId, int messageId) {
        Person sender = entityManager.find(Person.class, senderId);
        Person receiver = entityManager.find(Person.class, receiverId);
        Message message = entityManager.find(Message.class, messageId);


        if (sender == null || receiver == null || message == null)
            return false;

        SentMessage sentMessage = new SentMessage();
        sentMessage.setSender(sender);
        sentMessage.setReceiver(receiver);
        sentMessage.setMessage(message);
        sentMessage.setDispatchDate(new Date());

        entityManager.persist(sentMessage);

        return true;
    }

    public boolean sendMessageToEverybody(int senderId, int messageId) {

        List<Person> people = entityManager.createNamedQuery("Person.FindAll").getResultList();

        for (Person p : people) {
            if (senderId != p.getId())
                if (!sendMessage(senderId, p.getId(), messageId))
                    return false;
        }


        return true;
    }


    public boolean resendMessageToEvb(int senderId, String tempTitle) {


        Query query = entityManager.createNamedQuery("Message.findMessageIdBySenderIdAndTitle");
        query.setParameter("senderId", senderId);
        query.setParameter("tempTitle", tempTitle);

        List<Integer> resultList = query.getResultList();

        if (resultList.isEmpty())
            return false;

        return sendMessageToEverybody(senderId, resultList.get(0));
    }
}
