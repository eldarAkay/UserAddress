package com.mustafaiev.service;

import com.mustafaiev.domain.Address;
import com.mustafaiev.domain.User;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

/**
 * Service for processing Users
 *
 * @author Eldar Mustafaiev
 */
@Service("userService")
@Transactional
public class UserService {

    protected static Logger logger = Logger.getLogger("service");

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    /**
     * Retrieves all users
     */
    public List<User> getAll(Integer addressId) {
        logger.debug("Retrieving all users");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM  User WHERE address.id=" + addressId);

        return query.list();
    }

    /**
     * Retrieves all users
     */
    public List<User> getAll() {
        logger.debug("Retrieving all credit cards");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM  User");

        return query.list();
    }

    public List<User> getSearchedList(String firstName, String lastName, String email) {
        logger.debug("Retrieving searched users");
        Session session = sessionFactory.getCurrentSession();
        Query query;
        String hql = "FROM User";
        int count = 0;

        if (firstName != null && !firstName.isEmpty()) {
            count++;
            hql = hql + editHqlQuery(count);
            hql = hql + "firstName like:firstName";

        }
        if (lastName != null && !lastName.isEmpty()) {
            count++;
            hql = hql + editHqlQuery(count);
            hql = hql + "lastName like:lastName";

        }
        if (email != null && !email.isEmpty()) {
            count++;
            hql = hql + editHqlQuery(count);
            hql = hql + "email like:email";
        }

        query = session.createQuery(hql);

        if (hql.contains("firstName")) {
            query.setParameter("firstName", "%" + firstName + "%");

        }
        if (hql.contains("lastName")) {
            query.setParameter("lastName", "%" + lastName + "%");

        }
        if (hql.contains("email")) {
            query.setParameter("email", "%" + email + "%");

        }

        return query.list();
    }

    public String editHqlQuery(int count) {
        String hqlText = "";

        if (count == 1) {
            hqlText = " WHERE ";
        }
        if (count == 2 || count == 3) {
            hqlText = " AND ";
        }

        return hqlText;
    }

    /**
     * Retrieves a single user
     */
    public User get(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, id);

        return user;
    }

    /**
     * Adds a new user
     */
    public void add(Integer addressId, User user) {
        logger.debug("Adding new credit card");
        Session session = sessionFactory.getCurrentSession();
        Address existingAddress = (Address) session.get(Address.class, addressId);
        user.setAddress(existingAddress);
        session.save(user);
    }

    public void add(User user) {
        logger.debug("Adding new user");
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    /**
     * Deletes an existing user
     */
    public void delete(Integer id) {
        logger.debug("Deleting existing user");

        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, id);
        session.delete(user);
    }

    /**
     * Deletes all credit cards based on the address's id
     */
    public void deleteAll(Integer addressId) {
        logger.debug("Deleting existing credit cards based on address's id");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("DELETE FROM User WHERE address.id=" + addressId);
        query.executeUpdate();
    }

    /**
     * Edits an existing user
     */
    public void edit(User user) {
        logger.debug("Editing existing user");
        Session session = sessionFactory.getCurrentSession();
        User existingUser = (User) session.get(User.class, user.getId());

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setPatronymic(user.getPatronymic());
        existingUser.setPhone(user.getPhone());
        existingUser.setEmail(user.getEmail());
        existingUser.setBirthDate(user.getBirthDate());
        existingUser.setAddress(null);
        session.save(existingUser);
    }

    public void edit(Integer addressId, User user) {
        logger.debug("Editing existing user");
        Session session = sessionFactory.getCurrentSession();
        User existingUser = (User) session.get(User.class, user.getId());

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setPatronymic(user.getPatronymic());
        existingUser.setPhone(user.getPhone());
        existingUser.setEmail(user.getEmail());
        existingUser.setBirthDate(user.getBirthDate());

        Address existingAddress = (Address) session.get(Address.class, addressId);
        existingUser.setAddress(existingAddress);
        session.save(existingUser);
    }
}