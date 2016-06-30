package database.interfaces;

import database.entities.TTParams;
import database.entities.TTReferences;
import database.entities.embeded.AttrObject;
import database.entities.embeded.AttrObjectReference;

import javax.ejb.Local;

/**
 * Created by Vlad on 27.06.2016.
 */
@Local
public interface TTReferencesInterface {
    public void create(TTReferences references);

    public void update(TTReferences references);

    public void delete(AttrObjectReference id);
}
