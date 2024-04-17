package org.example.expensemanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PaymentMethod {
    @JsonIgnore
    private Long paymentMethodId;
    @JsonProperty("paymentMethodName")
    private String name;

    public PaymentMethod() {
    }

    public Long getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(Long paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
