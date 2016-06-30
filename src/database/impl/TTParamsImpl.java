package database.impl;

import database.entities.TTParams;
import database.entities.embeded.AttrObject;
import database.interfaces.TTParamsInterface;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Vlad on 27.06.2016.
 */
@LocalBean
@Stateless(name = "TTParamsLocalSessionEJB")
public class TTParamsImpl implements TTParamsInterface {

    @PersistenceContext(unitName = "objects")
    private EntityManager manager;

    @Override
    public void create(TTParams params) {
        manager.persist(params);
    }

    @Override
    public void update(TTParams params) {
        manager.merge(params);
    }

    @Override
    public void delete(AttrObject id) {
        TTParams params = manager.find(TTParams.class, id);
        manager.remove(params);
    }
}
