package database.impl;

import database.entities.TTParams;
import database.entities.TTReferences;
import database.entities.embeded.AttrObjectReference;
import database.interfaces.TTReferencesInterface;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Vlad on 27.06.2016.
 */
@LocalBean
@Stateless(name = "TTReferencesLocalSessionEJB")
public class TTReferencesImpl implements TTReferencesInterface {

    @PersistenceContext(unitName = "objects")
    private EntityManager manager;

    @Override
    public void create(TTReferences references) {
        manager.persist(references);
    }

    @Override
    public void update(TTReferences references) {
        manager.merge(references);
    }

    @Override
    public void delete(AttrObjectReference id) {
        TTReferences references = manager.find(TTReferences.class, id);
        manager.remove(references);
    }
}
