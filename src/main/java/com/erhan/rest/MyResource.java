package com.erhan.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import com.erhan.rest.util.HibernateUtil;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

	Logger logger = LogManager.getLogger(MyResource.class);
	
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
    	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    	Session session = sessionFactory.openSession();
    	Transaction transaction = session.beginTransaction();
    	logger.info("Transaction status is active = " + transaction.getStatus().isOneOf(TransactionStatus.ACTIVE));
    	transaction.commit();
    	logger.info("Transaction status is active = " + transaction.getStatus().isOneOf(TransactionStatus.ACTIVE));
    	
    	logger.info("getResource is invoked.");
        return "Got it!";
    }
}
