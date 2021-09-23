package com.crasoftinc.jmatrailers.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

public class UpdateTrailerModel {

  @NotNull(message = "Company id should be given")
  private String companyId;

  @NotNull(message = "trailer should have VIN number")
  @Size(max = 17, min = 17)
  private String vin;

  @NotNull(message = "Trailer should be assigned")
  private boolean isAssigned;

  @NotNull(message = "Location should be given")
  private LocationModel location;

  @NotNull(message = "Driver id should be given")
  private String driverId;

  @NotNull(message = "Owner type should be given")
  private OwnerTypeModel ownerType;

  private String notes;

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
