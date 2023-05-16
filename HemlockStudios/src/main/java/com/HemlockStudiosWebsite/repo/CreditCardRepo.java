package com.HemlockStudiosWebsite.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.HemlockStudiosWebsite.entity.CreditCard;

public interface CreditCardRepo extends JpaRepository<CreditCard, Integer> {

}