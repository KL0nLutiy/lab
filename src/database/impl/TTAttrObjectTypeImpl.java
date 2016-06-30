package database.impl;

import database.entities.TTAttrObjectTypes;
import database.entities.embeded.AttrObjectType;
import database.interfaces.TTAttrObjectTypeInterface;

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

    @PersistenceContext(unitName = "objects")
    private EntityManager manager;

    @Override
    public void create(TTAttrObjectTypes attrObjectType) {
        manager.persist(attrObjectType);
    }

    @Override
    public void update(TTAttrObjectTypes attrObjectType) {
        manager.merge(attrObjectType);
    }

    @Override
    public void delete(AttrObjectType id) {
        TTAttrObjectTypes attrObjectType = manager.find(TTAttrObjectTypes.class, id);
        manager.remove(attrObjectType);
    }
}
