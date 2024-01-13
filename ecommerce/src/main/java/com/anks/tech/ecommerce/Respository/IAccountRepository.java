package com.anks.tech.ecommerce.Respository;

import com.anks.tech.ecommerce.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<Account,Integer> {
}
