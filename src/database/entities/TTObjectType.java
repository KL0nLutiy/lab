package database.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Vlad on 21.06.2016.
 */
@Entity
@Table(name="TT_OBJECT_TYPES")
public class TTObjectType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="OBJECT_TYPE_ID")
    private Long objectTypeId;
    @Column(name="PARENT_ID")
    private Long parentId;
    @Column(name="NAME")
    private String name;
    @Column(name="DESCRIPTION")
    private String description;
    @Column(name="ISCLASS")
    private Boolean isClass;
    @Column(name="ISSYSTEM")
    private Boolean isSystem;
    @Column(name="ISSEARCHABLE")
    private Boolean isSearchable;
    @Column(name="ALIAS")
    private String alias;
    @Column(name="FLAGS")
    private Long flags;
    @Column(name="PROPERTIES")
    private String properties;
    @Column(name="ISABSTRACT")
    private Boolean isAbstract;
    @Column(name="INTERNAL_NAME")
    private String internalName;

    public Long getObjectTypeId() {
        return objectTypeId;
    }

    public void setObjectTypeId(Long objectTypeId) {
        this.objectTypeId = objectTypeId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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

    public Boolean isClass() {
        return isClass;
    }

    public void setClass(Boolean isClass) {
        this.isClass = isClass;
    }

    public Boolean isSystem() {
        return isSystem;
    }

    public void setSystem(Boolean isSystem) {
        this.isSystem = isSystem;
    }

    public Boolean getSearchable() {
        return isSearchable;
    }

    public void setSearchable(Boolean searchable) {
        isSearchable = searchable;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Long getFlags() {
        return flags;
    }

    public void setFlags(Long flags) {
        this.flags = flags;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public Boolean IsAbstract() {
        return isAbstract;
    }

    public void setAbstract(Boolean isAbstract) {
        this.isAbstract = isAbstract;
    }

    public String getInternalName() {
        return internalName;
    }

    public void setInternalName(String internalName) {
        this.internalName = internalName;
    }

    @Override
    public String toString() {
        return "TTObjectType{" +
                "objectTypeId=" + objectTypeId +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isClass=" + isClass +
                ", isSystem=" + isSystem +
                ", isSearchable=" + isSearchable +
                ", alias='" + alias + '\'' +
                ", flags=" + flags +
                ", properties='" + properties + '\'' +
                ", isAbstract=" + isAbstract +
                ", internalName='" + internalName + '\'' +
                '}';
    }
}
