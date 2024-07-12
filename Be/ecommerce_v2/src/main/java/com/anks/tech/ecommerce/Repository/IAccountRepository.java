package com.anks.tech.ecommerce.Repository;

import com.anks.tech.ecommerce.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface IAccountRepository extends JpaRepository<Account,Integer>, JpaSpecificationExecutor<Account> {
    Optional<Account> findByUsername (String username);
    Optional<Account> findByEmail(String email);
    Account findAccountByUsername (String username);

    boolean existsByEmail(String email);
    boolean existsByUsername(String userName);
}
