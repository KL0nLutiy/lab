package database.impl;

import database.DBWorker;
import database.entities.TTObject;
import database.interfaces.TTObjectInterface;
import org.apache.log4j.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Vlad on 24.06.2016.
 */
@LocalBean
@Stateless(name = "TTObjectLocalSessionEJB")
public class TTObjectImpl implements TTObjectInterface {

    private static final Logger log = Logger.getLogger(TTObjectImpl.class);

    @PersistenceContext(unitName = "objects")
    private EntityManager manager;

    @Override
    public long create(TTObject object) {
        DBWorker dbWorker = new DBWorker();
        long id = dbWorker.getId();
        object.setObjectId(id);
        object.setObjectClassId(id);
        object.setVersion(1L);
        //dbWorker.close();
        log.info("Create object"+object.toString());
        manager.persist(object);
        return id;
    }

    @Override
    public void update(TTObject object) {
        object.setObjectClassId(object.getObjectId());
        log.info("Update object"+object.toString());
        manager.merge(object);
    }

    @Override
    public void delete(long id) {
        TTObject object = manager.find(TTObject.class, id);
        log.info("Delete object"+object.toString());
        manager.remove(object);
    }
}
