package com.HemlockStudiosWebsite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HemlockStudiosWebsite.entity.CreditCard;
import com.HemlockStudiosWebsite.repo.CreditCardRepo;

@Service
public class CreditCardService {
    @Autowired
    private CreditCardRepo creditCardRepo;

    public CreditCard getCreditCardById(Integer creditCardId) {
        return creditCardRepo.findById(creditCardId)
        .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public CreditCard save(CreditCard creditCard){
        return creditCardRepo.save(creditCard);
    }

    public CreditCard update(CreditCard creditCard) throws Exception
    {
        if(creditCard.getId() != null)
        {
        return creditCardRepo.save(creditCard);
        }

        throw new Exception("Account does not exist, id not present");
    }

    public CreditCard findById(Integer creditCardId) throws Error
    {
        //Find by is another predefined repo function, you can always find by the 
        //primary key. When you use findById, its good practice to use the isPresent in an if check first
        //It will tell you if a object was actually found, with true or false values, true if found, 
        //It will always return an Optional object, the creditCard will be there if found, null if not
        //If found, give the object back with a .get() to grab it off the optional object
        if(creditCardRepo.findById(creditCardId).isPresent()){
            return creditCardRepo.findById(creditCardId).get();
        }
        //if creditCard is not present, throw error so front end can handle it
        throw new Error("CreditCard was not found");
    }

    public void deleteCreditCardById(Integer creditCardId) {
        creditCardRepo.deleteById(creditCardId);
    }

    public List<CreditCard> findAll() {
        return creditCardRepo.findAll();
    }
  
    public CreditCard createCreditCard(String cardNumber, String cardHolderName, String expirationMonth, String expirationYear, String cvv) {
      
        CreditCard creditCard = new CreditCard();
        creditCard.setCardNumber(cardNumber);
        creditCard.setCardHolderName(cardHolderName);
        creditCard.setExpirationMonth(expirationMonth);
        creditCard.setExpirationYear(expirationYear);
        creditCard.setCvv(cvv);

        return creditCardRepo.save(creditCard);
    }

    public void updateCreditCard(Integer creditCardId, String cardNumber, String expirationMonth, String expirationYear,
            String cardHolderName, String cvv) {

         CreditCard cardToUpdate = creditCardRepo.findById(creditCardId)
        .orElseThrow(() -> new RuntimeException("Product not found"));

        cardToUpdate.setCardHolderName(cardHolderName);
        cardToUpdate.setCardNumber(cardNumber);
        cardToUpdate.setExpirationMonth(expirationMonth);
        cardToUpdate.setExpirationYear(expirationYear);
        cardToUpdate.setCvv(cvv);

        creditCardRepo.save(cardToUpdate);
    }

}
