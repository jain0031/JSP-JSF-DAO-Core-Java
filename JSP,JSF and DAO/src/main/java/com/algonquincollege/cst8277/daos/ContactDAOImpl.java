package com.algonquincollege.cst8277.daos;
/** File: ContactsController.java*
 *  Author (original): Vaibhav Jain, course materials (19W) CST 8277*
 *  Created on February 2019
 *  Author:Student 040-884087* 
 *  Description: My ContactDAOImpl class makes connecton with database and perform the functions asked by user like create,update ,delete and read *
 *  @authors  Vaibhav Jain
 *   */
import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.sql.DataSource;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import com.algonquincollege.cst8277.model.Contact;

@Named
@ApplicationScoped

public class ContactDAOImpl implements Serializable, ContactDAO {
    /**
     * variables are declared to pass ceratin parameters and also to retrieve some
     * kind of lists
     */

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final String GET_ALL_CONTACTS = "SELECT * from CONTACTS";
    private static final String SAVE_CONTACTS = "INSERT INTO CONTACTS (first_name,last_name, email, street, postal, city, country) values (?, ?, ?, ?, ?,?,?)";
    private static final String UPDATE_CONTACTS = "update contacts set first_name=?, last_name=?, email=?, street=?, postal=?, city=?,country=? where id= ?";
    private static final String DELETE_CONTACTS = "delete from contacts where id = ";
    private static final String GET_CONTACT_BY_ID = "SELECT * from CONTACTS where id = ?";

    @Resource(name = "jdbc/ContactsDb")
    protected DataSource contactsDb;
    private List<Contact> contacts = null;
    /* (non-Javadoc)
     * @see com.algonquincollege.cst8277.daos.ContactDAO#getContacts()
     * returns list of contacts which is then shown on our index file
     */
    public List<Contact> getContacts() {
        contacts = new ArrayList<>();
        logger.info("retrieving list of contacts");
        try (Connection con = contactsDb.getConnection();
                PreparedStatement pstmt = con.prepareStatement(GET_ALL_CONTACTS);) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Contact newContact = new Contact();
                newContact.setId(rs.getInt("id"));
                newContact.setFirstName(rs.getString("first_name"));
                newContact.setLastName(rs.getString("last_name"));
                newContact.setEmail(rs.getString("email"));
                newContact.setStreet(rs.getString("street"));
                newContact.setPostal(rs.getString("postal"));
                newContact.setCity(rs.getString("city"));
                newContact.setCountry(rs.getString("country"));
                contacts.add(newContact);
            }
        } catch (SQLException e) {
            logger.error("something went wrong accessing Contacts Db", e);
        }
        return contacts;
    }

    
    /* (non-Javadoc)
     * @see com.algonquincollege.cst8277.daos.ContactDAO#getDetails(int)
     * this method is used to get details by id whenever user press update it pass the id into that and return the details
     * on form page
     */
    public Contact getDetails(int id) {
        Contact newContact = new Contact();

        try (Connection con = contactsDb.getConnection();
                PreparedStatement pstmt = con.prepareStatement(GET_CONTACT_BY_ID);) {
            logger.info("Getting contact detail of  id " + id);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                newContact.setId(rs.getInt("id"));
                newContact.setFirstName(rs.getString("first_name"));
                newContact.setLastName(rs.getString("last_name"));
                newContact.setEmail(rs.getString("email"));
                newContact.setStreet(rs.getString("street"));
                newContact.setPostal(rs.getString("postal"));
                newContact.setCity(rs.getString("city"));
                newContact.setCountry(rs.getString("country"));
            }
        } catch (SQLException e) {
            logger.error("something went wrong accessing Contacts Db", e);
        }
        return newContact;

    }

    /* (non-Javadoc)
     * @see com.algonquincollege.cst8277.daos.ContactDAO#deleteContact(int)
     * this method is for deleting the record when user press delete it takes id as parameter and run the sql query on that
     */
    public void deleteContact(int id) {
        try (Connection con = contactsDb.getConnection();
                PreparedStatement pstmt = con.prepareStatement(DELETE_CONTACTS + id);) {
            logger.info("deleting contacts");

            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("something went wrong accessing Contacts Db", e);

        }
    }

    /* (non-Javadoc)
     * @see com.algonquincollege.cst8277.daos.ContactDAO#saveContacts(com.algonquincollege.cst8277.model.Contact)
     * This method is taking model class Contact instance to inser the fields to database using query
     * and using return generated key statement for returning the resulting id's on database
     */
    public void saveContacts(Contact newContact) {

        try (Connection con = contactsDb.getConnection();
                PreparedStatement pstmt = con.prepareStatement(SAVE_CONTACTS, Statement.RETURN_GENERATED_KEYS);) {
            logger.info("Creating contacts");
            pstmt.setString(1, newContact.getFirstName());
            pstmt.setString(2, newContact.getLastName());
            pstmt.setString(3, newContact.getEmail());
            pstmt.setString(4, newContact.getStreet());
            pstmt.setString(5, newContact.getPostal());
            pstmt.setString(6, newContact.getCity());
            pstmt.setString(7, newContact.getCountry());
//        pstmt.executeQuery();
            pstmt.executeUpdate();
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                newContact.setId(id);
            }
        } catch (SQLException e) {
            logger.error("something went wrong accessing Contacts Db", e);
        }

    }

    /* (non-Javadoc)
     * @see com.algonquincollege.cst8277.daos.ContactDAO#updateContact(com.algonquincollege.cst8277.model.Contact)
     * This method is taking model class instance and using update sql statement for updating the contact
     */
    @Override
    public void updateContact(Contact contact) {
        Connection con;
        try {
            logger.info("Updating contacts");
            con = contactsDb.getConnection();
            PreparedStatement pstmt = con.prepareStatement(UPDATE_CONTACTS, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, contact.getFirstName());
            pstmt.setString(2, contact.getLastName());
            pstmt.setString(3, contact.getEmail());
            pstmt.setString(4, contact.getStreet());
            pstmt.setString(5, contact.getPostal());
            pstmt.setString(6, contact.getCity());
            pstmt.setString(7, contact.getCountry());
            pstmt.setInt(8, contact.getId());
            // pstmt.executeQuery();
            pstmt.executeUpdate();

        } catch (SQLException e) {
            logger.error("something went wrong accessing Contacts Db", e);
        }

    }
}
