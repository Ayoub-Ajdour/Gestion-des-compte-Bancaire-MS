package ma.fpl.accountsvc.entity;

import jakarta.persistence.*;
import lombok.*;
import ma.fpl.accountsvc.enums.AccountType;
import ma.fpl.accountsvc.module.Customer;

@Entity @Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class BankAccount {
    @Id
    private String id;
    private Long customerId;
    private double balance;
    private String currency;
    @Transient
    private Customer customer;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;



}
