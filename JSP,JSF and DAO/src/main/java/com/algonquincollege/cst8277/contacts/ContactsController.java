package com.algonquincollege.cst8277.contacts;

/** File: ContactsController.java*
 *  Author (original): Vaibhav Jain, course materials (19W) CST 8277*
 *  Created on February 2019
 *  Author: Student 040-884087* 
 *  Description: My Contact Controller class interacts with my view and dao class *
 *  @authors  Vaibhav Jain
 *   */

/**My import statements*/
import java.io.Serializable;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algonquincollege.cst8277.daos.ContactDAO;
import com.algonquincollege.cst8277.daos.ContactDAOImpl;
import com.algonquincollege.cst8277.model.Contact;

@Named("contactsController")
@ApplicationScoped
public class ContactsController implements Serializable {
    /**
     * variables are declared to pass ceratin parameters and also to retrieve some
     * kind of lists
     */

    protected ContactDAO contactsDAO;
    protected List<Contact> contacts;
    protected ContactDAOImpl daoImpl = new ContactDAOImpl();
    private Contact cont;
    /**
     * Constructor using Inject annotaion to inject the ContactDAO class instance
     */

    @Inject
    public ContactsController(ContactDAO contactsDAO) {
        this.contactsDAO = contactsDAO;
    }
    /**
     * The PostConstruct annotation is used on a method  below that needs to be executed after dependency injection 
     * is done to perform initialization.
     * 
     */

    @PostConstruct
    public void getListOfContacts() {
        setContacts(contactsDAO.getContacts());
    }
    /**
     * The method below is  for returning the list of contacts
     */


    public List<Contact> getContacts() {
        return contacts;
    }
    /**
     * This method below is for setting up the list of contacts
     */

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
    /**
     * The method below is returning string which is redirection to update form page where user can update any 
     * information needed by sending details to dao class the detais by id 
     */

    public String getContactDetailById(int id) {
        cont = contactsDAO.getDetails(id);
        return "update-form.xhtml?faces-redirect=true";
    }
    /**
     * Simple getter method for instance of class Contact
     */

    public Contact getCont() {
        return cont;
    }
    /**
     * Simple setter method for instance class Contact
     */

    public void setCont(Contact cont) {
        this.cont = cont;
    }
    
    
    public String updateContactDetails() {
        contactsDAO.updateContact(cont);
        setContacts(contactsDAO.getContacts());
        return "index.xhtml?faces-redirect=true";

    }
    /**
     * This method is for adding the contact to the database which take the contact as its parameter and set all 
     * details by sending it to dao class
     */

    public String AddContactRecord(Contact contact) {
        contactsDAO.saveContacts(contact);
        setContacts(contactsDAO.getContacts());
        return "index.xhtml?faces-redirect=true";
    }
    /**
     * This method below is for deleting the record by Id .
     */

    public String deleteContactRecord(int id) {
        contactsDAO.deleteContact(id);
        setContacts(contactsDAO.getContacts());
        return "index.xhtml?faces-redirect=true";

    }

}
