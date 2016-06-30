package database.interfaces;

import database.entities.TTParams;
import database.entities.embeded.AttrObject;

import javax.ejb.Local;

/**
 * Created by Vlad on 27.06.2016.
 */
@Local
public interface TTParamsInterface {
    public void create(TTParams params);

    public void update(TTParams params);

    public void delete(AttrObject id);
}
