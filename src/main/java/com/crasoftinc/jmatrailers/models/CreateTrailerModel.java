package com.crasoftinc.jmatrailers.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Document(collection = "trailers")
public class CreateTrailerModel {

  @Id
  private String id;

  @NotNull(message = "Company id should be given")
  private String companyId;

  @NotNull(message = "trailer should have VIN number")
  @Size(max = 17, min = 17)
  private String vin;

  //    @NotNull(message = "Trailer should be assigned")
  private boolean isAssigned;
  //TODO: create another route for this patch

  //TODO: create new service for location
  @NotNull(message = "Location should be given")
  private LocationModel location;

  private String driverId;

  private OwnerTypeModel ownerType;

  private String notes;

  public CreateTrailerModel(@JsonProperty("id") String id) {
    this.id = id;
    this.isAssigned = false;
  }

  public CreateTrailerModel(String id, String companyId, boolean isAssigned, String vin,
                            LocationModel location, OwnerTypeModel ownerType) {
    this.id = id;
    this.companyId = companyId;
    this.isAssigned = isAssigned;
    this.vin = vin;
    this.location = location;
    this.ownerType = ownerType;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCompanyId() {
    return companyId;
  }

  public void setCompanyId(String companyId) {
    this.companyId = companyId;
  }

  public String getVin() {
    return vin;
  }

  public void setVin(String vin) {
    this.vin = vin;
  }

  public boolean isAssigned() {
    return isAssigned;
  }

  public void setAssigned(boolean assigned) {
    isAssigned = assigned;
  }

  public LocationModel getLocation() {
    return location;
  }

  public void setLocation(LocationModel location) {
    this.location = location;
  }

  public String getDriverId() {
    return driverId;
  }

  public void setDriverId(String driverId) {
    this.driverId = driverId;
  }

  public OwnerTypeModel getOwnerType() {
    return ownerType;
  }

  public void setOwnerType(OwnerTypeModel ownerType) {
    this.ownerType = ownerType;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }
}
