package database.entities;

import database.entities.embeded.AttrObjectType;

import javax.persistence.*;

/**
 * Created by Vlad on 24.06.2016.
 */

@Entity
@Table(name="TT_ATTR_OBJECT_TYPES")
public class TTAttrObjectTypes {

    @EmbeddedId
    private AttrObjectType attrObjectType;
    @Column(name="ISDISPLAYED")
    private Boolean isDisplayed;
    @Column(name="REQUIRED")
    private Boolean isRequired;
    @Column(name="OPTIONS")
    private Long options;
    @Column(name="DEFAULT_VALUE")
    private String defaultValue;
    @Column(name="FLAGS")
    private Long flags;

    public AttrObjectType getAttrObjectType() {
        return attrObjectType;
    }

    public void setAttrObjectEmbedded(AttrObjectType attrObjectType) {
        this.attrObjectType = attrObjectType;
    }

    public Boolean getDisplayed() {
        return isDisplayed;
    }

    public void setDisplayed(Boolean displayed) {
        isDisplayed = displayed;
    }

    public Boolean getRequired() {
        return isRequired;
    }

    public void setRequired(Boolean required) {
        isRequired = required;
    }

    public Long getOptions() {
        return options;
    }

    public void setOptions(Long options) {
        this.options = options;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Long getFlags() {
        return flags;
    }

    public void setFlags(Long flags) {
        this.flags = flags;
    }

    @Override
    public String toString() {
        return "TTAttrObjectTypes{" +
                "attrObjectType=" + attrObjectType +
                ", isDisplayed=" + isDisplayed +
                ", isRequired=" + isRequired +
                ", options=" + options +
                ", defaultValue='" + defaultValue + '\'' +
                ", flags=" + flags +
                '}';
    }
}
