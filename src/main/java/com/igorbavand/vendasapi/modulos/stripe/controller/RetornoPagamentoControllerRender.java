package com.igorbavand.vendasapi.modulos.stripe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("public/ingressos-pagamento")
public class RetornoPagamentoControllerRender {

    @GetMapping("/success")
    public String paymentSuccess(boolean success) {
        return "success";
    }

    @GetMapping("/cancelled")
    public String paymentCancel(boolean canceled) {
        return "cancelled";
    }

}
