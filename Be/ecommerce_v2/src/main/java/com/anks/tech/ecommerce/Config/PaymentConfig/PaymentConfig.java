package com.anks.tech.ecommerce.Config.PaymentConfig;

import jakarta.servlet.http.HttpServletRequest;

import java.util.*;

public class PaymentConfig {
    public static String vnp_PayUrl = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
    public static String vnp_ReturnUrl = "http://localhost:8080/api/v1/payment-callback";
    public static String vnp_TmnCode = "UTCPMPYJ";
    public static String secretKey = "DZYNJSCVZPXFXETMMHNAECYSGXSRJZQS";
    public static String vnp_ApiUrl = "https://sandbox.vnpayment.vn/merchant_webapi/api/transaction";

}
