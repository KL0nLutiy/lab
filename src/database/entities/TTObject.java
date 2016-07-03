package database.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Vlad on 24.06.2016.
 */
@Entity
@Table(name="TT_OBJECTS")
public class TTObject implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="OBJECT_ID")
    private Long objectId;
    @Column(name="PARENT_ID")
    private Long parentId;
    @Column(name="OBJECT_TYPE_ID")
    private Long objectTypeId;
    @Column(name="OBJECT_CLASS_ID")
    private Long objectClassId;
    @Column(name="PROJECT_ID")
    private Long projectId;
    @Column(name="NAME")
    private String name;
    @Column(name="DESCRIPTION")
    private String description;
    @Column(name="ORDER_NUMBER")
    private Long orderNumber;
    @Column(name="VERSION")
    private Long version;

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getObjectTypeId() {
        return objectTypeId;
    }

    public void setObjectTypeId(Long objectTypeId) {
        this.objectTypeId = objectTypeId;
    }

    public Long getObjectClassId() {
        return objectClassId;
    }

    public void setObjectClassId(Long objectClassId) {
        this.objectClassId = objectClassId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "TTObject{" +
                "objectId=" + objectId +
                ", parentId=" + parentId +
                ", objectTypeId=" + objectTypeId +
                ", objectClassId=" + objectClassId +
                ", projectId=" + projectId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", orderNumber=" + orderNumber +
                ", version=" + version +
                '}';
    }
}
