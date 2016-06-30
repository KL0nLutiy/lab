package database.impl;

import database.DBWorker;
import database.entities.TTAttributes;
import database.interfaces.TTAttributeInterface;

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

    @PersistenceContext(unitName = "objects")
    private EntityManager manager;


    @Override
    public long create(TTAttributes attributes) {
        DBWorker dbWorker = new DBWorker();
        long id = dbWorker.getId();
        dbWorker.close();
        attributes.setAttrId(id);
        manager.persist(attributes);
        return id;
    }

    @Override
    public void update(TTAttributes attributes) {
        manager.merge(attributes);
    }

    @Override
    public void delete(long id) {
        TTAttributes attributes = manager.find(TTAttributes.class, id);
        manager.remove(attributes);
    }
}
