package com.dsproject.order.clients;
 import com.dsproject.order.dto.CustomerDTO;
 import org.springframework.cloud.openfeign.FeignClient;
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CUSTOMER")
public interface CustomerServiceClient {
    @GetMapping("/customer/{id}")
    CustomerDTO getCustomerById(@PathVariable("id") Long id);
}
