package com.HemlockStudiosWebsite.events;

import com.HemlockStudiosWebsite.entity.Product;

public class ProductMadeEvent {
    private final Product productMade;

    public ProductMadeEvent(Product productMade) {
        this.productMade = productMade;
    }

    public Product getProductMade() {
        return productMade;
    }
}
