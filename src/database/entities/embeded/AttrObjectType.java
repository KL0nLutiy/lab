package database.entities.embeded;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Vlad on 27.06.2016.
 */

@Embeddable
@Table(name="TT_ATTR_OBJECT_TYPES")
public class AttrObjectType implements Serializable {
    @Column(name="ATTR_ID")
    private Long atttId;
    @Column(name="OBJECT_TYPE_ID")
    private Long objectTypeId;

    public AttrObjectType(){
    }

    public AttrObjectType(Long atttId, Long objectTypeId) {
        this.atttId = atttId;
        this.objectTypeId = objectTypeId;
    }

    public Long getAtttId() {
        return atttId;
    }

    public Long getObjectTypeId() {
        return objectTypeId;
    }
}
