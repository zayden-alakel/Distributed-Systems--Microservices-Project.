package com.dsproject.order.clients;
import com.dsproject.order.dto.ProductDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceClientFallback implements ProductServiceClient {

    @Override
    public ProductDTO getProductById(Long productId) {
        // Return a default or placeholder ProductDTO object
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(productId);
        productDTO.setName("Unavailable");
        productDTO.setPrice(0.0);
        return productDTO;
    }
}