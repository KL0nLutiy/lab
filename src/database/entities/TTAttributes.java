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
@Table(name="TT_ATTRIBUTES")
public class TTAttributes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="ATTR_ID")
    private Long attrId;
    @Column(name="NAME")
    private String name;
    @Column(name="ATTR_ACCESS_TYPE")
    private Long attrAccessType;
    @Column(name="ISMULTIPLE")
    private Boolean isMultiple;
    @Column(name="ISEXTGENERATED")
    private Boolean isExtGenerated;
    @Column(name="ISEXTSTORED")
    private Boolean isExtStored;
    @Column(name="ADAPTERNAME")
    private String adapterName;
    @Column(name="PARAMS")
    private String params;
    @Column(name="UNIQUE_LEVEL")
    private Integer uniqueLevel;
    @Column(name="SHOW_ORDER")
    private Long showOrder;
    @Column(name="SHOW_HISTORY")
    private Integer showHistory;
    @Column(name="ISSEARCHABLE")
    private Boolean isSearchable;
    @Column(name="MASK")
    private String mask;
    @Column(name="DEF_VALUE")
    private String defValue;
    @Column(name="FLAGS")
    private Long flags;
    @Column(name="DESCRIPTION")
    private String description;
    @Column(name="PROPERTIES")
    private String properties;
    @Column(name="RULES")
    private Long rules;
    @Column(name="TOOLTIP")
    private String tooltip;
    @Column(name="AV_ADAPTER_NAME")
    private String avAdapterName;
    @Column(name="AV_ADAPTER_PROPERTIES")
    private String avAdapterProperties;
    @Column(name="INTERNAL_NAME")
    private String internalName;

    public TTAttributes() {
    }

    public TTAttributes(String name, Long attrAccessType) {
        this.name = name;
        this.attrAccessType = attrAccessType;
    }

    public Long getAttrId() {
        return attrId;
    }

    public void setAttrId(Long attrId) {
        this.attrId = attrId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAttrAccessType() {
        return attrAccessType;
    }

    public void setAttrAccessType(Long attrAccessType) {
        this.attrAccessType = attrAccessType;
    }

    public Boolean getMultiple() {
        return isMultiple;
    }

    public void setMultiple(Boolean multiple) {
        isMultiple = multiple;
    }

    public Boolean getExtGenerated() {
        return isExtGenerated;
    }

    public void setExtGenerated(Boolean extGenerated) {
        isExtGenerated = extGenerated;
    }

    public Boolean getExtStored() {
        return isExtStored;
    }

    public void setExtStored(Boolean extStored) {
        isExtStored = extStored;
    }

    public String getAdapterName() {
        return adapterName;
    }

    public void setAdapterName(String adapterName) {
        this.adapterName = adapterName;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Integer getUniqueLevel() {
        return uniqueLevel;
    }

    public void setUniqueLevel(Integer uniqueLevel) {
        this.uniqueLevel = uniqueLevel;
    }

    public Long getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Long showOrder) {
        this.showOrder = showOrder;
    }

    public Integer getShowHistory() {
        return showHistory;
    }

    public void setShowHistory(Integer showHistory) {
        this.showHistory = showHistory;
    }

    public Boolean getSearchable() {
        return isSearchable;
    }

    public void setSearchable(Boolean searchable) {
        isSearchable = searchable;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public String getDefValue() {
        return defValue;
    }

    public void setDefValue(String defValue) {
        this.defValue = defValue;
    }

    public Long getFlags() {
        return flags;
    }

    public void setFlags(Long flags) {
        this.flags = flags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public Long getRules() {
        return rules;
    }

    public void setRules(Long rules) {
        this.rules = rules;
    }

    public String getTooltip() {
        return tooltip;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    public String getAvAdapterName() {
        return avAdapterName;
    }

    public void setAvAdapterName(String avAdapterName) {
        this.avAdapterName = avAdapterName;
    }

    public String getAvAdapterProperties() {
        return avAdapterProperties;
    }

    public void setAvAdapterProperties(String avAdapterProperties) {
        this.avAdapterProperties = avAdapterProperties;
    }

    public String getInternalName() {
        return internalName;
    }

    public void setInternalName(String internalName) {
        this.internalName = internalName;
    }

    @Override
    public String toString() {
        return "TTAttributes{" +
                "attrId=" + attrId +
                ", name='" + name + '\'' +
                ", attrAccessType=" + attrAccessType +
                ", isMultiple=" + isMultiple +
                ", isExtGenerated=" + isExtGenerated +
                ", isExtStored=" + isExtStored +
                ", adapterName='" + adapterName + '\'' +
                ", params='" + params + '\'' +
                ", uniqueLevel=" + uniqueLevel +
                ", showOrder=" + showOrder +
                ", showHistory=" + showHistory +
                ", isSearchable=" + isSearchable +
                ", mask='" + mask + '\'' +
                ", defValue='" + defValue + '\'' +
                ", flags=" + flags +
                ", description='" + description + '\'' +
                ", properties='" + properties + '\'' +
                ", rules=" + rules +
                ", tooltip='" + tooltip + '\'' +
                ", avAdapterName='" + avAdapterName + '\'' +
                ", avAdapterProperties='" + avAdapterProperties + '\'' +
                ", internalName='" + internalName + '\'' +
                '}';
    }
}
