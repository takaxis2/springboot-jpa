package com.example.vuebackend.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    
  private String city;
  private String district;
  
  @Column(name = "address_detail")
  private String detail;

  private String zipCode;

}
