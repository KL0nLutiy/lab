package database.entities;

import database.entities.embeded.AttrObject;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Clob;
import java.sql.Date;

/**
 * Created by Vlad on 24.06.2016.
 */

@Entity
@Table(name="TT_PARAMS")
public class TTParams {

    @EmbeddedId
    private AttrObject attrObject;
    @Column(name="ATTR_ACCESS_TYPE")
    private Long attrAccessType;
    @Column(name="VALUE")
    private String value;
    @Column(name="DATA")
    private String data;
    @Column(name="SHOW_ORDER")
    private Long showOrder;
    @Column(name="DATE_VALUE")
    private Date dateValue;
    @Column(name="PRIORITY")
    private Long priority;

    public TTParams(){

    }

    public TTParams(AttrObject attrObject, Long attrAccessType, String value) {
        this.attrObject = attrObject;
        this.attrAccessType = attrAccessType;
        this.value = value;
    }

    public TTParams(AttrObject attrObject, Long attrAccessType, Date dateValue) {
        this.attrObject = attrObject;
        this.attrAccessType = attrAccessType;
        this.dateValue = dateValue;
    }

    public AttrObject getAttrObject() {
        return attrObject;
    }

    public void setAttrObject(AttrObject attrObject) {
        this.attrObject = attrObject;
    }

    public Long getAttrAccessType() {
        return attrAccessType;
    }

    public void setAttrAccessType(Long attrAccessType) {
        this.attrAccessType = attrAccessType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Long getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Long showOrder) {
        this.showOrder = showOrder;
    }

    public Date getDateValue() {
        return dateValue;
    }

    public void setDateValue(Date dateValue) {
        this.dateValue = dateValue;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }
}
