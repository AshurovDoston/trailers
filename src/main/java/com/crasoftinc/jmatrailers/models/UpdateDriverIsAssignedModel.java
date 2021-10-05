package com.crasoftinc.jmatrailers.models;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDriverIsAssignedModel {
  @NotNull(message = "isAssigned should not be null")
  private boolean isAssigned;
}
