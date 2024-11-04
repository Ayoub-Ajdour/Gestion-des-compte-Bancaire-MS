package ma.fpl.accountsvc.repository;

import ma.fpl.accountsvc.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

//@Repository
public interface BankRepo extends JpaRepository<BankAccount,String> {
}
