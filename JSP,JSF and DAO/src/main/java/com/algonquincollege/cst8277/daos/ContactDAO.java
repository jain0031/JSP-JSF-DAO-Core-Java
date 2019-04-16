package com.algonquincollege.cst8277.daos;

/** File: ContactsController.java*
 *  Author (original): Vaibhav Jain, course materials (19W) CST 8277*
 *  Created on February 2019
 *  Author: I. M. Student 040-884087* 
 *  Description: My ContactDAO Interface is implemented by my ContactsDAOImpl class *
 *  @authors  Vaibhav Jain
 *   */
import java.util.List;

import com.algonquincollege.cst8277.model.Contact;
public interface ContactDAO {


public List<Contact> getContacts();

public Contact getDetails(int id);

public void saveContacts(Contact contact);

public void deleteContact(int id);

public void updateContact(Contact contact);

}
