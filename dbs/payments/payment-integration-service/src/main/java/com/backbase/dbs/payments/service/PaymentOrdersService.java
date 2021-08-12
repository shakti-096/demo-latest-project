package com.backbase.dbs.payments.service;


import com.backbase.payments.integration.model.CancelResponse;
import com.backbase.payments.integration.model.PaymentOrdersPostRequestBody;
import com.backbase.payments.integration.model.PaymentOrdersPostResponseBody;

public interface PaymentOrdersService {

  /**
   * Make a payment
   *
   * @param paymentOrder request body with payment to make details
   * @return response body with payment made details
   */
  PaymentOrdersPostResponseBody postPaymentOrder(PaymentOrdersPostRequestBody paymentOrder);

  /**
   * Cancel a standing payment order
   *
   * @param bankReferenceId transaction reference number
   * @return response body confirming the acceptance
   */
  CancelResponse postCancelPaymentOrder(String bankReferenceId);
}
