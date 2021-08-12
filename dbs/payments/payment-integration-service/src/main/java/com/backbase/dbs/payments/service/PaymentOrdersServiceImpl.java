package com.backbase.dbs.payments.service;

import com.backbase.buildingblocks.backend.security.auth.config.SecurityContextUtil;
import com.backbase.dbs.payments.constants.PaymentType;
import com.backbase.payments.integration.model.CancelResponse;
import com.backbase.payments.integration.model.PaymentOrdersPostRequestBody;
import com.backbase.payments.integration.model.PaymentOrdersPostResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentOrdersServiceImpl implements PaymentOrdersService {

  private static final Logger LOGGER = LoggerFactory.getLogger(PaymentOrdersServiceImpl.class);

  private SecurityContextUtil util;

  @Autowired
  public PaymentOrdersServiceImpl(SecurityContextUtil util) {
    this.util = util;
  }
  private String getClaim(String claim) {
    return util.getUserTokenClaim(claim, String.class).orElse("");
  }

  /**
   * Make a payment
   *
   * @param paymentOrder request body with payment to make details
   * @return response body with payment made details
   */
  @Override
  public PaymentOrdersPostResponseBody postPaymentOrder(PaymentOrdersPostRequestBody paymentOrder) {

    return PaymentOrdersPostRequestBody.PaymentModeEnum.RECURRING.equals(paymentOrder.getPaymentMode())
            ? makeStandingOrderPayment(paymentOrder)
            : makeNormalPayment(paymentOrder);
  }

  /**
   * Cancel a standing payment order
   *
   * @param bankReferenceId transaction reference number
   * @return response body confirming the acceptance
   */
  @Override
  public CancelResponse postCancelPaymentOrder(String bankReferenceId) {
    return null;
  }


  private PaymentOrdersPostResponseBody makeStandingOrderPayment(
          PaymentOrdersPostRequestBody paymentOrder) {
    PaymentOrdersPostResponseBody response = null;
    //need to call service here for standing order
    return response;
  }

  private PaymentOrdersPostResponseBody makeNormalPayment(
          PaymentOrdersPostRequestBody paymentOrder) {
    PaymentOrdersPostResponseBody response = null;
    //need to call service here for payment
    return response;
  }
}
