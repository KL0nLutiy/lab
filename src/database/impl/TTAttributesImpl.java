package database.impl;

import database.DBWorker;
import database.entities.TTAttributes;
import database.interfaces.TTAttributeInterface;
import org.apache.log4j.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Vlad on 24.06.2016.
 */
@LocalBean
@Stateless(name = "TTAttributesLocalSessionEJB")
public class TTAttributesImpl implements TTAttributeInterface {

    private static final Logger log = Logger.getLogger(TTAttributesImpl.class);

    @PersistenceContext(unitName = "objects")
    private EntityManager manager;


    @Override
    public long create(TTAttributes attributes) {
        DBWorker dbWorker = new DBWorker();
        long id = dbWorker.getId();
        dbWorker.close();
        attributes.setAttrId(id);
        log.info("Create attribute "+attributes.toString());
        manager.persist(attributes);
        return id;
    }

    @Override
    public void update(TTAttributes attributes) {
        log.info("Update attribute "+attributes.toString());
        manager.merge(attributes);
    }

    @Override
        public void delete(long id) {
        TTAttributes attributes = manager.find(TTAttributes.class, id);
        log.info("Delete attribute "+attributes.toString());
        manager.remove(attributes);
    }
}
