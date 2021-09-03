package com.crasoftinc.jmatrailers.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Document(collection = "Trailer")
public class OwnerTypeModel {
//    @NotNull(message = "OwnerStatusModel-status should not be null")
//    @Pattern(regexp = "^(STATUS_DRIVER)|(STATUS_COMPANY_OWNER)$")
    private OwnerStatusModel status;
//    @NotNull(message = "OwnerStatusModel-name should not be null")
    private String name;

    public OwnerTypeModel(OwnerStatusModel status, String name) {
        this.status = status;
        this.name = name;
    }
}
