package ma.fpl.accountsvc.web;

import ma.fpl.accountsvc.clients.CustomerRestClient;
import ma.fpl.accountsvc.entity.BankAccount;
import ma.fpl.accountsvc.module.Customer;
import ma.fpl.accountsvc.repository.BankRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BankController {
    private BankRepo bankRepo;
    private CustomerRestClient customerRestClient;

    public BankController(BankRepo bankRepo, CustomerRestClient customerRestClient) {
        this.bankRepo = bankRepo;
        this.customerRestClient = customerRestClient;
    }
    @GetMapping("/bankaccounts")
    public List<BankAccount> customerList(){
        return bankRepo.findAll();
    }
    @GetMapping("/bankaccounts/{id}")
    public BankAccount bankaccountById(@PathVariable String id){
        BankAccount bankAccount= bankRepo.findById(id).get();
        Customer customer=customerRestClient.findCustomerById(bankAccount.getCustomerId());
        bankAccount.setCustomer(customer);
        return bankAccount;
    }

}
