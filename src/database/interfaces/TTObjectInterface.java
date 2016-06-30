package database.interfaces;

import database.entities.TTObject;

import javax.ejb.Local;

/**
 * Created by Vlad on 24.06.2016.
 */
@Local
public interface TTObjectInterface {
    public long create(TTObject object);

    public void update(TTObject object);

    public void delete(long id);
}
