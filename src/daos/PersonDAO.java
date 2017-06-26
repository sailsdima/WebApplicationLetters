package daos;

import construct.PersonMessages;
import construct.PersonMessagesLengthInfo;
import tables.Person;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by dima on 25.06.17.
 */

@Stateless
public class PersonDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Person> findAll() {
        return entityManager.createNamedQuery("Person.FindAll").getResultList();
    }

    public Person addPerson(Person p) {
        entityManager.persist(p);
        return p;
    }

    public List<Person> getPeopleWhoReceivedMessageWithTitle(String title) {
        Query query = entityManager.createQuery("SELECT distinct p FROM Person p, Message m, SentMessage s WHERE p.id = s.receiver.id AND s.message.id = m.id AND m.title = :title");

        query.setParameter("title", title);
        return query.getResultList();
    }

    public List<Person> getPeopleWhoNotReceivedMessageWithTitle(String title) {
        Query query = entityManager.createQuery("select distinct p1 from Person p1 where p1.id not in (SELECT p.id FROM Person p, Message m, SentMessage s WHERE p.id = s.receiver.id AND s.message.id = m.id AND m.title = :title)");

        query.setParameter("title", title);
        return query.getResultList();
    }


    public List<PersonMessagesLengthInfo> getPeopleAndMessagesInfo() {
        Query query =  entityManager.createNamedQuery("Person.FindPersonAndMessagesInfo");
        List<PersonMessagesLengthInfo> messagesLengthList = query.getResultList();

        return messagesLengthList;
    }

    //P.S написать нормальный запрос получилось только на sql.
    public PersonMessagesLengthInfo getPersonWithLeastMessageLength(){
        return
                Collections.min(getPeopleAndMessagesInfo(), Comparator.comparingInt(PersonMessagesLengthInfo::getMessageLength));
    }

    public List<PersonMessages> getPeopleMessagesInfo() {
        TypedQuery<PersonMessages> personMessages = entityManager.createNamedQuery("Person.FindPersonMessagesInfo", PersonMessages.class);
        return personMessages.getResultList();
    }

}
