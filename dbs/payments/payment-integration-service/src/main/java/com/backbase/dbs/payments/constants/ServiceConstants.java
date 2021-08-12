package com.backbase.dbs.payments.constants;

public class ServiceConstants {

  private ServiceConstants() {
  }

  public static final String ACOM = "ACOM";
  public static final String NBI_TRANSACTION_TYPE = "omnichannel";
  public static final String SERVICE_CODE_CTBA = "CTBA001";
  public static final String SERVICE_CODE_CDTO002 = "CDTO002";
  public static final String BRANCH_CODE_JO0010002 = "JO0010002";
  public static final String BRANCH_CODE_101 = "101";
  public static final String PURPOSE_OF_PAYMENT = "purposeOfPayment";
  public static final String DOMESTIC_TRANSFER_SERVICE_CODE = "CDT0001";
  public static final String DOMESTIC_STANDING_ORDER_TRANSFER_SERVICE_CODE = "CDTO002";
  public static final String CBOJ_WRITTEN_REQUEST = "N";
  public static final String COMMISSION_TYPE_JOD = "RTGSLCY";
  public static final String COMMISSION_TYPE_OTHER = "RTGSFCY";
  public static final String COMMISSION_TYPE_2 = "RMFULLCOMM";
  public static final String COMMISSION_TYPE_3 = "OUTSWIFTCHG";
  public static final String SWIFT_MT_SENT_TO = "RCVBK";
  public static final String SW_PREFIX = "SW-";
  public static final String ACC_PREFIX = "/ACC/";
  public static final String INTERNATIONAL_TRANSFER_SERVICE_CODE = "CIT0001";
  public static final String INTERNATIONAL_COMMISSION_TYPE_1 = "OTRMTCHGPLS";
  public static final String INTERNATIONAL_COMMISSION_TYPE_3 = "OUTSWIFTCHG";
  public static final String INTERNATIONAL_SWIFT_MT_SENT_TO = "CRCUST";
  public static final String AND_PREFIX = "//AND";
  public static final String EMPTY_STRING = "";
  public static final String DASH_SYMBOL = "-";
  public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
  public static final String DATE_PATTERN_SIMPLE = "yyyyMMdd";
  public static final String BIC = "BIC";
  public static final String CHARGE_TYPE = "chargeType";
  public static final String PAYMENT_DETAILS = "paymentDetails";
  public static final String BANK_COUNTRY = "bankCountry";
  public static final String RELATIONSHIP_WITH_BENEFICIARY_CLAIM = "relationshipWithBeneficiary";
  public static final String RELATIONSHIP_WITH_BENEFICIARY_CUSTOM_CLAIM = "relationshipWithBeneficiaryCustom";
  public static final String ROUTING_NUMBER = "routingNumber";
  public static final String URGENCY_FLAG = "urgencyFlag";
  public static final String DEBIT_CURRENCY = "debitCurrency";
  public static final String JOD = "JOD";
  public static final String IQD = "IQD";
  public static final String ZERO = "0";
  public static final String CBOJ = "CBOJ";
  public static final String NBI = "NBI";
  public static final String OMNI_CHANNEL = "OMNICHL";
  public static final String NBI_WRITTEN_REQUEST = "n";
  public static final int DEBUG_FLAG = 1;
  public static final int AUTHORIZE_LEVEL = 0;
  public static final String PROCESS_TYPE = "Y";
  public static final String CANCEL_TRANSFER_SERVICE_CODE_CBOJ = "DTO0001";
  public static final String CANCEL_TRANSFER_SERVICE_CODE_NBI = "DTO0003";
  public static final String HEADER_BINDING_BANK_ID_KEY = "BankID";
  public static final String INTERNATIONAL_STANDING_TRANSFER_SERVICE_CODE = "CITO001";
  public static final String EMPTY_BANK_REFERENCE_ID = "NO_REFERENCE_WAS_PROVIDED";

  // Claims
  public static final String BANK_ID = "BankID";
  public static final String CUSTOMER_ID = "CustomerID";
  public static final String USER_TYPE = "UserType";
}
