package com.backbase.dbs.payments.constants;

public enum FieldConvert {
  SHA("S"),
  OUR("R"),
  BEN("B"),
  ACH("T6"),
  RTGS("T5"),
  RETAIL("P"),
  CORPORATE("C");

  private final String value;

  FieldConvert(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
