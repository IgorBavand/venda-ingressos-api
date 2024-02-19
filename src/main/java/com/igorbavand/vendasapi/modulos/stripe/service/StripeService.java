package com.igorbavand.vendasapi.modulos.stripe.service;

import com.igorbavand.vendasapi.modulos.autenticacao.model.Usuario;
import com.igorbavand.vendasapi.modulos.ingresso.dto.IngressoRequest;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.CustomerSearchResult;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.param.CustomerSearchParams;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {

    private static final int CEM = 100;

    @Value("${app-config.stripe.secretKey}")
    private String stripeSecretKey;

    public Customer registerCustomer(Usuario cliente) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        Map<String, Object> params = new HashMap<>();
        params.put("name", cliente.getNome());
        params.put("email", cliente.getLogin());

        return Customer.create(params);
    }

    public Product registerProduct(IngressoRequest ingressoRequest) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        ProductCreateParams paramsProduct =
                ProductCreateParams.builder()
                        .setName(ingressoRequest.getDescricao())
                        .build();

        return Product.create(paramsProduct);
    }

    public Price registerPrice(String productId, Double price) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        PriceCreateParams paramsPrice =
                PriceCreateParams.builder()
                        .setProduct(productId)
                        .setUnitAmount(converterParaCentavos(price))
                        .setCurrency("BRL")
                        .build();

        return Price.create(paramsPrice);
    }

    public CustomerSearchResult searchCustomerById(String customerId) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        CustomerSearchParams params =
                CustomerSearchParams
                        .builder()
                        .setQuery("id:'" + customerId + "'")
                        .build();

        return Customer.search(params);
    }

    private Long converterParaCentavos(Double valor) {
        int centavos = (int) (valor * CEM);
        return (long) centavos;
    }
}
