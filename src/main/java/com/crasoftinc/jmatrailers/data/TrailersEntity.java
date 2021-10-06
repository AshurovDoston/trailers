package com.crasoftinc.jmatrailers.data;

import com.crasoftinc.jmatrailers.models.LocationModel;
import com.crasoftinc.jmatrailers.models.OwnerTypeModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "trailers")
public class TrailersEntity {
  private String id;

  private String companyId;

  private String vin;

  private boolean isAssigned;

  private LocationModel location;

  private String driverId;

  private OwnerTypeModel ownerType;

  private String notes;

  public TrailersEntity(String id, String companyId, String vin, LocationModel location,
                        String driverId, OwnerTypeModel ownerType) {
    this.id = id;
    this.companyId = companyId;
    this.vin = vin;
    this.location = location;
    this.driverId = driverId;
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
