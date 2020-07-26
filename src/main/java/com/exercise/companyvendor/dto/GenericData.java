package com.exercise.companyvendor.dto;

import lombok.*;

@Data
public abstract class GenericData {

    private Long id;
    private String name;
    private String unlocode;

    protected GenericData(Long id, String name, String unlocode) {
        this.id = id;
        this.name = name;
        this.unlocode = unlocode;
    }
}
