package database.entities.embeded;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Vlad on 27.06.2016.
 */
@Embeddable
@Table(name="TT_REFERENCES")
public class AttrObjectReference implements Serializable {

    @Column(name="ATTR_ID")
    private Long atttId;
    @Column(name="OBJECT_ID")
    private Long objectId;
    @Column(name="REFERENCE")
    private Long references;

    public AttrObjectReference(){

    }

    public AttrObjectReference(Long atttId, Long objectId, Long references) {
        this.atttId = atttId;
        this.objectId = objectId;
        this.references = references;
    }

    public Long getAtttId() {
        return atttId;
    }

    public Long getObjectId() {
        return objectId;
    }

    public Long getReferences() {
        return references;
    }
}
