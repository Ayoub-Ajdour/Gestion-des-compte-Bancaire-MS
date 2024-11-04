package ma.fpl.customersvc;

import ma.fpl.customersvc.config.GlobalConfig;
import ma.fpl.customersvc.entity.Customer;
import ma.fpl.customersvc.repository.CustomerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(GlobalConfig.class)
public class CustomerSvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerSvcApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(CustomerRepo customerRepo){
        return args -> {
            List<Customer> customers=List.of(
                    Customer.builder()
                            .email("aa@gmail.com").lastName("aa").firstName("bb").build(),
                    Customer.builder()
                            .email("aa1@gmail.com").lastName("aa1").firstName("bb1").build()
            );
            customerRepo.saveAll(customers);
        };
    }
}
