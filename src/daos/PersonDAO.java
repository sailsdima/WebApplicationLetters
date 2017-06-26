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
        Query query = entityManager.createNamedQuery("Person.PeopleWhoReceivedMessageWithTitle");
        query.setParameter("title", title);
        return query.getResultList();
    }

    public List<Person> getPeopleWhoNotReceivedMessageWithTitle(String title) {
        Query query = entityManager.createNamedQuery("Person.PeopleWhoNotReceivedMessageWithTitle");

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

    public List<PersonMessages> getPeopleSentMessagesInfo() {
        TypedQuery<PersonMessages> personMessages = entityManager.createNamedQuery("Person.FindPersonSentMessagesInfo", PersonMessages.class);
        return personMessages.getResultList();
    }

    public List<PersonMessages> getPeopleReceivedMessagesInfo() {
        TypedQuery<PersonMessages> personMessages = entityManager.createNamedQuery("Person.FindPersonReceivedMessagesInfo", PersonMessages.class);
        return personMessages.getResultList();
    }

}
