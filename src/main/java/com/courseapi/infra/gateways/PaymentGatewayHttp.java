package com.courseapi.infra.gateways;

import org.springframework.stereotype.Component;

import com.courseapi.application.gateway.PaymentGateway;

@Component
public class PaymentGatewayHttp implements PaymentGateway {

  // @Autowired
  // private Http http;

  @Override
  public Output processePayment(Input input) {
    // try {
    // var output = this.http.execute(input);
    // return output.data;
    // } catch (Exception e) {
    // System.out.println("Something goes wrong: " + e.getMessage());
    // return null;
    // }
    return null;
  }

}
