package com.company.inventory.inventory.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseRest extends ResponseRest{

    private ProductResponse product = new ProductResponse();

}
