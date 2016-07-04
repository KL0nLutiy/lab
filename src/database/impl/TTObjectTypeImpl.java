package database.impl;

import database.DBWorker;
import database.interfaces.TTObjectTypeInterface;
import database.entities.TTObjectType;
import org.apache.log4j.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Vlad on 24.06.2016.
 */
@LocalBean
@Stateless(name = "TTObjectTypeLocalSessionEJB")
public class TTObjectTypeImpl implements TTObjectTypeInterface{

    private static final Logger log = Logger.getLogger(TTObjectTypeImpl.class);

    @PersistenceContext(unitName = "objects")
    private EntityManager manager;

    @Override
    public long create(TTObjectType objectType) {
        DBWorker dbWorker = new DBWorker();
        long id = dbWorker.getId();
        objectType.setObjectTypeId(id);
        log.info("Create object type "+objectType.toString());
        manager.persist(objectType);
        return id;
    }

    @Override
    public void update(TTObjectType objectType) {
        log.info("Update object type "+objectType.toString());
        manager.merge(objectType);
    }

    @Override
    public void delete(long id) {
        TTObjectType objectType = manager.find(TTObjectType.class, id);
        log.info("Remove object type "+objectType.toString());
        manager.remove(objectType);
    }
}
