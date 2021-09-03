package com.crasoftinc.jmatrailers.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class UpdateTrailerModel {

    @NotNull(message = "Company id should be given")
    private String company_id;

    @NotNull(message = "Trailer should be assigned")
    private boolean is_assigned;

    @NotNull(message = "Location should be given")
    private LocationModel location;

    @NotNull(message = "Driver id should be given")
    private String driver_id;

    @NotNull(message = "Owner type should be given")
    private OwnerTypeModel owner_type;

    @Null
    private String notes;

//    public UpdateTrailerModel(@JsonProperty("id") String id) {
//        this.id = id;
//    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public boolean getIs_assigned() {
        return is_assigned;
    }

    public void setIs_assigned(boolean is_assigned) {
        this.is_assigned = is_assigned;
    }

    public LocationModel getLocation() {
        return location;
    }

    public void setLocation(LocationModel location) {
        this.location = location;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
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
