package database.impl;

import database.entities.TTAttrObjectTypes;
import database.entities.embeded.AttrObjectType;
import database.interfaces.TTAttrObjectTypeInterface;
import org.apache.log4j.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Vlad on 27.06.2016.
 */
@LocalBean
@Stateless(name = "TTAttrObjectTypesLocalSessionEJB")
public class TTAttrObjectTypeImpl implements TTAttrObjectTypeInterface {

    private static final Logger log = Logger.getLogger(TTAttrObjectTypeImpl.class);

    @PersistenceContext(unitName = "objects")
    private EntityManager manager;

    @Override
    public void create(TTAttrObjectTypes attrObjectType)
    {
        log.info("Create attr object type "+attrObjectType.toString());
        manager.persist(attrObjectType);
    }

    @Override
    public void update(TTAttrObjectTypes attrObjectType) {
        log.info("Update attr object type "+attrObjectType.toString());
        manager.merge(attrObjectType);
    }

    @Override
    public void delete(AttrObjectType id) {
        TTAttrObjectTypes attrObjectType = manager.find(TTAttrObjectTypes.class, id);
        log.info("Delete attr object type "+attrObjectType.toString());
        manager.remove(attrObjectType);
    }
}
