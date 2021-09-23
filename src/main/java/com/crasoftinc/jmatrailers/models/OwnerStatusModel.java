package com.crasoftinc.jmatrailers.models;


import com.fasterxml.jackson.annotation.JsonValue;

public enum OwnerStatusModel {
  ROOT("STATUS_DRIVER"),
  SUPERUSER("STATUS_COMPANY_OWNER");

  String slug;

  OwnerStatusModel(String slug) {
    this.slug = slug;
  }

  @JsonValue
  public String getSlug() {
    return slug;
  }

}