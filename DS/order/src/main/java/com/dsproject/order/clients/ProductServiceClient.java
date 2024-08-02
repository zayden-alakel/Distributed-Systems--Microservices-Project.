package com.dsproject.order.clients;

import com.dsproject.order.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "PRODUCT", fallback = ProductServiceClientFallback.class)
public interface ProductServiceClient {
    @GetMapping("/product/{productId}")
    ProductDTO getProductById(@PathVariable("productId") Long productId);
}
