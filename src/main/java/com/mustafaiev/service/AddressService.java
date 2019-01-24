package com.mustafaiev.service;

import com.mustafaiev.domain.Address;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

/**
 * Service for processing Addresses
 *
 * @author Mustafaiev Eldar
 */
@Service("addressService")
@Transactional
public class AddressService {

    protected static Logger logger = Logger.getLogger("service");

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    /**
     * Retrieves all addresses
     *
     * @return a list of addresses
     */
    public List<Address> getAll() {
        logger.debug("Retrieving all addresses");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM  Address");

        return query.list();
    }

    /**
     * Retrieves searched addresses
     *
     * @return a list of addresses
     */
    public List<Address> getSearchedList(String region, String city, String street) {
        logger.debug("Retrieving searched addresses");

        Session session = sessionFactory.getCurrentSession();
        Query query;
        String hql = "FROM Address";

        int count = 0;

        if (region != null && !region.isEmpty()) {
            count++;
            hql = hql + editHqlQuery(count);
            hql = hql + "region like:region";

        }
        if (city != null && !city.isEmpty()) {
            count++;
            hql = hql + editHqlQuery(count);
            hql = hql + "city like:city";

        }
        if (street != null && !street.isEmpty()) {
            count++;
            hql = hql + editHqlQuery(count);
            hql = hql + "street like:street";
        }

        query = session.createQuery(hql);

        if(hql.contains("region")){
            query.setParameter("region", "%"+region+"%");

        }
        if(hql.contains("city")){
            query.setParameter("city", "%"+city+"%");

        }
        if(hql.contains("street")){
            query.setParameter("street", "%"+street+"%");

        }

        return query.list();
    }


    /**
     * Adds text to HQL query
     * @param count
     * @return
     */
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
     * Retrieves a single address
     */
    public Address get(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Address address = (Address) session.get(Address.class, id);

        return address;
    }

    /**
     * Adds a new address
     */
    public void add(Address address) {
        logger.debug("Adding new address");
        Session session = sessionFactory.getCurrentSession();

        session.save(address);
    }

    /**
     * Deletes an existing address
     *
     * @param id the id of the existing address
     */
    public void delete(Integer id) {
        logger.debug("Deleting existing address");
        Session session = sessionFactory.getCurrentSession();
        Address address = (Address) session.get(Address.class, id);

        session.delete(address);
    }

    /**
     * Edits an existing address
     */
    public void edit(Address address) {
        logger.debug("Editing existing address");
        Session session = sessionFactory.getCurrentSession();
        Address existingAddress = (Address) session.get(Address.class, address.getId());

        existingAddress.setRegion(address.getRegion());
        existingAddress.setCity(address.getCity());
        existingAddress.setStreet(address.getStreet());
        existingAddress.setHomeNumber(address.getHomeNumber());
        existingAddress.setFlatNumber(address.getFlatNumber());

        session.save(existingAddress);
    }
}
