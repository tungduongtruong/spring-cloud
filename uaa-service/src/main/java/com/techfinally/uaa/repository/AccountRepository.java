package com.techfinally.uaa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.techfinally.uaa.model.entity.Account;

/**
 * @author Truong Duong
 */
@Repository
@Transactional
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByUsername(String username);

}
