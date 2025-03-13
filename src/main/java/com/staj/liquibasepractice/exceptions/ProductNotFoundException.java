package com.staj.liquibasepractice.exceptions;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends RuntimeException {

  private final String details;

  public ProductNotFoundException() { //findall ile bulmazsa id yok
    super(ErrorMessages.PRODUCT_NOT_FOUND.getMessage());
    this.details = null;
  }
  public ProductNotFoundException(String details) {
    super(ErrorMessages.PRODUCT_NOT_FOUND.getMessage());
    this.details = details;
  }

  public ProductNotFoundException(Long id) {
    super(ErrorMessages.PRODUCT_NOT_FOUND.getMessage());
    this.details = "with the id: " + id;
  }
}
