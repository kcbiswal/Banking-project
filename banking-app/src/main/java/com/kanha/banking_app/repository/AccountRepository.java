package com.kanha.banking_app.repository;

import com.kanha.banking_app.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {

}
