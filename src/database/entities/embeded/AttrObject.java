package database.entities.embeded;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Vlad on 27.06.2016.
 */

@Embeddable
@Table(name="TT_PARAMS")
public class AttrObject implements Serializable {
    @Column(name="ATTR_ID")
    private Long atttId;
    @Column(name="OBJECT_ID")
    private Long objectId;

    public AttrObject(){

    }

    public AttrObject(Long atttId, Long objectId) {
        this.atttId = atttId;
        this.objectId = objectId;
    }

    public Long getAtttId() {
        return atttId;
    }

    public Long getObjectId() {
        return objectId;
    }
}
