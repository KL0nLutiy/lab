package database.entities;

import database.entities.embeded.AttrObjectReference;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Vlad on 24.06.2016.
 */

@Entity
@Table(name="TT_REFERENCES")
public class TTReferences {

    @EmbeddedId
    private AttrObjectReference attrObjectReference;
    @Column(name="SHOW_ORDER")
    private Long showOrder;
    @Column(name="PRIORITY")
    private Long priority;
    @Column(name="ATTR_ACCESS_TYPE")
    private Long attrAccessType;

    public TTReferences() {

    }

    public TTReferences(AttrObjectReference attrObjectReference, Long attrAccessType) {
        this.attrObjectReference = attrObjectReference;
        this.attrAccessType = attrAccessType;
    }

    public AttrObjectReference getAttrObjectReference() {
        return attrObjectReference;
    }

    public Long getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Long showOrder) {
        this.showOrder = showOrder;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public Long getAttrAccessType() {
        return attrAccessType;
    }

    public void setAttrAccessType(Long attrAccessType) {
        this.attrAccessType = attrAccessType;
    }

    @Override
    public String toString() {
        return "TTReferences{" +
                "attrObjectReference=" + attrObjectReference +
                ", showOrder=" + showOrder +
                ", priority=" + priority +
                ", attrAccessType=" + attrAccessType +
                '}';
    }
}
