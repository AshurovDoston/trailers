package com.crasoftinc.jmatrailers.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Trailer")
public class DefaultTrailerModel {
    private String id;

    private String company_id;

    private LocationModel location;

    private OwnerTypeModel owner_type;

    private String notes;

    public DefaultTrailerModel(String id, String company_id, LocationModel location, OwnerTypeModel owner_type) {
        this.id = id;
        this.company_id = company_id;
        this.location = location;
        this.owner_type = owner_type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public LocationModel getLocation() {
        return location;
    }

    public void setLocation(LocationModel location) {
        this.location = location;
    }

    public OwnerTypeModel getOwner_type() {
        return owner_type;
    }

    public void setOwner_type(OwnerTypeModel owner_type) {
        this.owner_type = owner_type;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
