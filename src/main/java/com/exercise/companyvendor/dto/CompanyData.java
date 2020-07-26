package com.exercise.companyvendor.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonPropertyOrder({"id", "name", "is_active", "created_at", "updated_at", "UNLOCODE", "place_identity_id",
        "vendor_place_id"})
public class CompanyData extends GenericData implements Serializable {

    private String isActive;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;
    private String placeIdentityId;
    private Long vendorPlaceId;

    @JsonCreator
    public CompanyData(
            @JsonProperty("id") Long id,
            @JsonProperty("name") String name,
            @JsonProperty("is_active") String isActive,
            @JsonProperty("created_at")
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss") Date createdAt,
            @JsonProperty("updated_at")
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss") Date updatedAt,
            @JsonProperty("UNLOCODE") String unlocode,
            @JsonProperty("place_identity_id") String placeIdentityId,
            @JsonProperty("vendor_place_id") Long vendorPlaceId) {
        super(id, name, unlocode);
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.placeIdentityId = placeIdentityId;
        this.vendorPlaceId = vendorPlaceId;
    }

    /**
     * Copy Constructor to clone objects
     *
     * @param copy object to clone
     */
    public CompanyData(CompanyData copy) {
        this(copy.getId(), copy.getName(), copy.getIsActive(), copy.getCreatedAt(), copy.getUpdatedAt(),
                copy.getUnlocode(), copy.getPlaceIdentityId(), copy.getVendorPlaceId());
    }
}
