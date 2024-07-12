package com.anks.tech.ecommerce.Controller.Payment;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/payment", produces = "application/json")
@ResponseBody
@Validated
@Tag(name = "Payment Controller")
public class PaymentController {
}
