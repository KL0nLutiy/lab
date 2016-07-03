package database.impl;

import database.entities.TTParams;
import database.entities.TTReferences;
import database.entities.embeded.AttrObjectReference;
import database.interfaces.TTReferencesInterface;
import org.apache.log4j.Logger;

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

    private static final Logger log = Logger.getLogger(TTReferencesImpl.class);

    @PersistenceContext(unitName = "objects")
    private EntityManager manager;

    @Override
    public void create(TTReferences references) {
        log.info("Create reference "+references.toString());
        manager.persist(references);
    }

    @Override
    public void update(TTReferences references) {
        log.info("Update reference "+references.toString());
        manager.merge(references);
    }

    @Override
    public void delete(AttrObjectReference id) {
        TTReferences references = manager.find(TTReferences.class, id);
        log.info("Delete reference "+references.toString());
        manager.remove(references);
    }
}
