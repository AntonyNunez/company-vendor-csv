package com.exercise.companyvendor.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonPropertyOrder({"PLACE ID", "PLACE NAME", "LATITUDE", "UNLOCODE"})
public class VendorData extends GenericData implements Serializable {

    private String latitude;

    @JsonCreator
    public VendorData(
            @JsonProperty("PLACE ID") Long id,
            @JsonProperty("PLACE NAME") String name,
            @JsonProperty("LATITUDE") String latitude,
            @JsonProperty("UNLOCODE") String unlocode) {
        super(id, name, unlocode);
        this.latitude = latitude;
    }
}
