package ma.fpl.accountsvc.clients;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import ma.fpl.accountsvc.module.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CUSTOMER-SVC")
public interface CustomerRestClient {
    @GetMapping("/customers/{id}")
    @CircuitBreaker(name = "customerService",fallbackMethod = "getDefaultCustomer")
    Customer findCustomerById(@PathVariable Long id);
    @GetMapping("/customers")
    @CircuitBreaker(name = "customerSvc",fallbackMethod = "getAllCustomer")
    List<Customer> allCustomers();
    default Customer getDefaultCustomer(Long id,Exception e){
        Customer customer=new Customer();
        customer.setId(id);
        customer.setFirstName("Not Vailable");
        customer.setLastName("Not Vailable");
        customer.setEmail("Not Vailable");
        return customer;
    }
    default List<Customer> getAllCustomer(Exception e){
        return List.of();
    }
}
