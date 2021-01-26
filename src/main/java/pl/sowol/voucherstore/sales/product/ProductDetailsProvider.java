package pl.sowol.voucherstore.sales.product;

import pl.sowol.voucherstore.sales.product.ProductDetails;

public interface ProductDetailsProvider {
    ProductDetails getByProductId(String productId);
}
