package com.restaurant.order.model.dtos.customers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequestDTO {

    private String name;
    private String email;
    private String phone;

}
