package org.mihaiaposotlproiectfinal.jdbcThymeleaf.entities;

import lombok.Data;

@Data
public class Product {

    Long productId;
    String productName;
    Integer productSize;
    String productType;
    Long clientId;

}
