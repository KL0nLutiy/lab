package database.interfaces;


import database.entities.TTAttributes;

import javax.ejb.Local;

/**
 * Created by Vlad on 24.06.2016.
 */
@Local
public interface TTAttributeInterface {
    public long create(TTAttributes attributes);

    public void update(TTAttributes attributes);

    public void delete(long id);
}
