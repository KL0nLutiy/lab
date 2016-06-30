package database.interfaces;

import database.entities.TTObjectType;

import javax.ejb.Local;

/**
 * Created by Vlad on 24.06.2016.
 */

@Local
public interface TTObjectTypeInterface {
    public long create(TTObjectType objectType);

    public void update(TTObjectType objectType);

    public void delete(long id);
}
