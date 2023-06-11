package com.HemlockStudiosWebsite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.HemlockStudiosWebsite.entity.CreditCard;
import com.HemlockStudiosWebsite.entity.User;
import com.HemlockStudiosWebsite.repo.CreditCardRepo;

@Service
public class CreditCardService {
    @Autowired
    CreditCardRepo creditCardRepo;
    @Autowired
    UserService userService;

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

    public void setDefaultCreditCard(Integer id)
    {
        User user = userService.findUserByEmail();
        CreditCard currentCard = creditCardRepo.findById(id)
        .orElseThrow(() -> new RuntimeException("Credit card not found"));
        List<CreditCard> cards = user.getWallet();

        for(CreditCard card : cards)
        {
            if(card.getDefaultCard() == true)
            {
                card.setDefaultCard(false);
            }
        }

        currentCard.setDefaultCard(true);
    }

    public CreditCard getDefaultCreditCard()
    {
        User user = userService.findUserByEmail();
        List<CreditCard> cards = user.getWallet();
        CreditCard currentCard = null;
        for(CreditCard card : cards)
        {
            if(card.getDefaultCard() == true)
            {
               currentCard = card;
               break;
            }
        }

       return currentCard;
    }


    public void addCreditCard(String cardNumber, String expirationYear, String  expirationMonth, String cardHolderName, String cvv) {
        // Get authentication
        System.out.println("in the add credit card SERVICE path");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
         Jwt jwt = (Jwt) auth.getPrincipal();
         String email = jwt.getClaim("email");
        User currentUser = userService.findByEmail(email);
         CreditCard savedCreditCard = createCreditCard(cardNumber, cardHolderName, expirationMonth, expirationYear, cvv);
         currentUser.getWallet().add(savedCreditCard);
        userService.save(currentUser);
     }
 
     public void removeCreditCard(Integer creditCardId) {
         System.out.println("In the removeCreditCardPath in user service");
     
         Authentication auth = SecurityContextHolder.getContext().getAuthentication();
         Jwt jwt = (Jwt) auth.getPrincipal();
         String email = jwt.getClaim("email");
        User currentUser = userService.findByEmail(email);
         CreditCard creditCard = getCreditCardById(creditCardId);
         System.out.println("Credit card to delete: " + creditCard);
         
         currentUser.getWallet().remove(creditCard);
         System.out.println("Credit card removed from wallet");
         
         userService.save(currentUser);
         System.out.println("User updated in database");
     
         deleteCreditCardById(creditCardId);
         System.out.println("Credit card deleted from credit card database");
     }
 





}
