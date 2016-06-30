package database.interfaces;

import database.entities.TTAttrObjectTypes;
import database.entities.embeded.AttrObjectType;

import javax.ejb.Local;

/**
 * Created by Vlad on 27.06.2016.
 */

@Local
public interface TTAttrObjectTypeInterface {
    public void create(TTAttrObjectTypes attrObjectType);

    public void update(TTAttrObjectTypes attrObjectType);

    public void delete(AttrObjectType id);
}
