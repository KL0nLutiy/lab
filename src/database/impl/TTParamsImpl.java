package database.impl;

import database.entities.TTParams;
import database.entities.embeded.AttrObject;
import database.interfaces.TTParamsInterface;
import org.apache.log4j.Logger;

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

    private static final Logger log = Logger.getLogger(TTParamsImpl.class);

    @PersistenceContext(unitName = "objects")
    private EntityManager manager;

    @Override
    public void create(TTParams params) {
        log.info("Create param "+params.toString());
        manager.persist(params);
    }

    @Override
    public void update(TTParams params) {
        log.info("Update param "+params.toString());
        manager.merge(params);
    }

    @Override
    public void delete(AttrObject id) {
        TTParams params = manager.find(TTParams.class, id);
        log.info("Delete param "+params.toString());
        manager.remove(params);
    }
}
