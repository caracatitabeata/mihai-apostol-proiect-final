package org.mihaiaposotlproiectfinal.jdbcThymeleaf.repositories;

import org.mihaiaposotlproiectfinal.jdbcThymeleaf.entities.Client;
import org.mihaiaposotlproiectfinal.jdbcThymeleaf.entities.Product;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductRowCallbackHandler implements RowCallbackHandler {

    //doar pentru produse avem clientId și productName
    private final Map<Long, Client> customerMap;

    public ProductRowCallbackHandler(Map<Long, Client> customerMap) {
        this.customerMap = customerMap;
    }

    public void processRow(ResultSet rs) throws SQLException {
        Long id = rs.getLong("clientId");
        Client client = customerMap.get(id);
        if (client != null) {
            List<Product> productList = client.getProducts();
            if (productList == null) {
                productList = new ArrayList<>();
                client.setProducts(productList);
            }
            Product product = new Product();
            product.setClientId(rs.getLong("clientId"));
            product.setProductName(rs.getString("productName"));
            productList.add(product);
        }
    }
}
