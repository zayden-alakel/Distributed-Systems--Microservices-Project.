package com.dsproject.order.clients;
import com.dsproject.order.dto.BusinessDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "BUSINESS")
public interface BusinessServiceClient {
    @GetMapping("/business/{id}")
    BusinessDTO getBusinessById(@PathVariable("id") Long id);
}
