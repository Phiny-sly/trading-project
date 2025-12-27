package com.amalitech.tradingproject.payload;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * Payload for order creation and update operations.
 */
@Data
public class OrderPayload {
    
    @NotEmpty(message = "Order must contain at least one product line")
    @Valid
    private List<ProductLinePayload> listOfProductLines;
}
