package beans;

import construct.PersonMessages;
import construct.PersonMessagesLengthInfo;
import daos.PersonDAO;
import tables.Person;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Created by dima on 25.06.17.
 */

@Named
@SessionScoped
public class PersonBean implements Serializable{

    @EJB
    private PersonDAO personDAO;

    private Person tempPerson = new Person();
    private String tempTitle;
    private List<Person> tempPeople;

    public List<Person> getPeople(){
        return personDAO.findAll();
    }

    public String addPerson(){
        personDAO.addPerson(tempPerson);
        tempPerson.setName("");

        return null;
    }

    public List<Person> getPeopleWhoReceivedMessageWithTitle(){
        tempPeople = Collections.emptyList();
        tempPeople = personDAO.getPeopleWhoReceivedMessageWithTitle(tempTitle);
        return tempPeople;
    }

    public List<Person> getPeopleWhoNotReceivedMessageWithTitle(){
        tempPeople = Collections.emptyList();
        tempPeople = personDAO.getPeopleWhoNotReceivedMessageWithTitle(tempTitle);
        return tempPeople;
    }

    public List<PersonMessages> getPeopleMessagesInfo() {
        List<PersonMessages> personMessagesInfo = personDAO.getPeopleMessagesInfo();

        return personMessagesInfo;
    }

    public Person getTempPerson() {
        return tempPerson;
    }

    public void setTempPerson(Person tempPerson) {
        this.tempPerson = tempPerson;
    }

    public void setTempTitle(String tempTitle) {
        this.tempTitle = tempTitle;
    }

    public String getTempTitle() {
        return tempTitle;
    }

    public void setTempPeople(List<Person> tempPeople) {
        this.tempPeople = tempPeople;
    }

    public List<Person> getTempPeople() {
        return tempPeople;
    }

    public List<PersonMessagesLengthInfo> getPeopleAndMessagesInfo() {
        return personDAO.getPeopleAndMessagesInfo();
    }

    public PersonMessagesLengthInfo getPersonAndMessageWithLeastMessageLength(){
        return personDAO.getPersonWithLeastMessageLength();
    }
}
