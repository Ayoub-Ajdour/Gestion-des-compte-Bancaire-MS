package ma.fpl.accountsvc;

import ma.fpl.accountsvc.clients.CustomerRestClient;
import ma.fpl.accountsvc.entity.BankAccount;
import ma.fpl.accountsvc.enums.AccountType;
import ma.fpl.accountsvc.repository.BankRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class AccountSvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountSvcApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BankRepo bankRepo, CustomerRestClient customerRestClient){
        return args -> {
            customerRestClient.allCustomers().forEach(c->{
                List<BankAccount> bankAccounts=List.of(
                        BankAccount.builder()
                                .id(UUID.randomUUID().toString())
                                .accountType(AccountType.CURRENT_ACCOUNT)
                                .balance(Math.random()*9000)
                                .currency("MAD")
                                .customerId(c.getId())
                                .build(),
                        BankAccount.builder()
                                .id(UUID.randomUUID().toString())
                                .accountType(AccountType.SAVING_ACCOUNT)
                                .balance(Math.random()*9000)
                                .currency("MAD")
                                .customerId(c.getId())
                                .build()
                );
                bankRepo.saveAll(bankAccounts);
            });

            List<BankAccount> bankAccounts2=List.of(
                    BankAccount.builder()
                            .id(UUID.randomUUID().toString())
                            .accountType(AccountType.CURRENT_ACCOUNT)
                            .balance(Math.random()*9000)
                            .currency("MAD")
                            .customerId(Long.valueOf(1))
                            .build(),
                    BankAccount.builder()
                            .id(UUID.randomUUID().toString())
                            .accountType(AccountType.SAVING_ACCOUNT)
                            .balance(Math.random()*9000)
                            .currency("MAD")
                            .customerId(Long.valueOf(2))
                            .build()
            );
            bankRepo.saveAll(bankAccounts2);
        };
    }
}
