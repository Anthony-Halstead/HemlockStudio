package com.HemlockStudiosWebsite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HemlockStudiosWebsite.entity.Cart;
import com.HemlockStudiosWebsite.entity.CreditCard;
import com.HemlockStudiosWebsite.entity.Customer;
import com.HemlockStudiosWebsite.entity.Product;
import com.HemlockStudiosWebsite.repo.CustomerRepo;

@Service
public class CustomerService {
    
    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    CartService cartService;

    @Autowired
    ProductService productService;

    @Autowired
    CreditCardService creditCardService;

    public List<Customer> getAll()
    {
        return customerRepo.findAll();
    }

    public Customer save(Customer customer){
        return customerRepo.save(customer);
    }

    public Customer update(Customer customer) throws Exception
    {
        if(customer.getId() != null)
        {
        return customerRepo.save(customer);
        }

        throw new Exception("Account does not exist, id not present");
    }

    public Customer findById(Integer customerId) throws Error
    {

        if(customerRepo.findById(customerId).isPresent()){
            return customerRepo.findById(customerId).get();
        }
    
        throw new Error("Customer was not found");
    }

    public Customer signUpAsCustomer(String username, String email, String password, Boolean isRegistered)
    {
        
        
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setEmail(email);
        customer.setPassword(password); 
        customer.setIsSignedUp(isRegistered);
        
        Cart cart = cartService.createCart();
        customer.setCart(cart);

        
        Customer savedCustomer = customerRepo.save(customer);
    
        return savedCustomer;
    }

    public void deleteCustomerById(Integer id) {
        Customer customer = customerRepo.findById(id).orElse(null);
    if (customer != null) {
        
        List<Product> favoriteProducts = customer.getFavoriteProducts();
        if (favoriteProducts != null) {
            favoriteProducts.clear();
            customer.setFavoriteProducts(favoriteProducts);
            customerRepo.save(customer);
        }

        customerRepo.deleteById(id);
    } else {
        throw new IllegalArgumentException("Customer not found.");
    }
    }

    public Customer signInAsCustomer(String username, String email, String password) {
        if (password == null) {
            throw new IllegalArgumentException("Invalid Password, password is null");
        }
        Customer customer = customerRepo.findByUsernameOrEmailAndPassword(username, email, password);
        if(customer == null)
          {throw new IllegalArgumentException("Invalid Username or Email.");}
        else if(!customer.getPassword().equals(password))
          {throw new IllegalArgumentException("Invalid Password");}
        else
          { return customer;}   
    }

    public Customer updateCustomer(Integer id, String username, String email) {
        Customer customer = customerRepo.findById(id).orElse(null);
        
        customer.setUsername(username);
        customer.setEmail(email);
        return customerRepo.save(customer);
    }
    
    public Cart getCartByCustomerId(Integer id) {
        Customer customer = customerRepo.findById(id)
        .orElseThrow(() -> new RuntimeException("Customer not found with id"));
        return customer.getCart();
    }

    public void addProductToFavorites(Integer customerId, Integer productId) {
      Customer customer = customerRepo.findById(customerId)
      .orElseThrow(() -> new RuntimeException("Customer not found"));

      Product product = productService.getProductById(productId);

      customer.getFavoriteProducts().add(product);
      customerRepo.save(customer);
    }

    public void removeProductFromFavorites(Integer customerId, Integer productId) {
        Customer customer = customerRepo.findById(customerId)
        .orElseThrow(() -> new RuntimeException("Customer not found"));
  
        Product product = productService.getProductById(productId);
  
        customer.getFavoriteProducts().remove(product);
        customerRepo.save(customer);
    }

    public Customer getCustomerById(Integer customerId) {
        return customerRepo.findById(customerId)
        .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public Customer addCreditCardToCustomer(Integer customerId, String cardNumber, Integer expirationYear, Integer  expirationMonth, String cardHolderName, String cvv) {
        Customer customer = customerRepo.findById(customerId)
        .orElseThrow(() -> new RuntimeException("Customer not found"));
    
        CreditCard savedCreditCard = creditCardService.createAndAddCreditCard(cardNumber, cardHolderName, expirationMonth, expirationYear, cvv);
    
        customer.getWallet().add(savedCreditCard);
        return customerRepo.save(customer);
    }

    public void removeCreditCardFromCustomer(Integer customerId, Integer creditCardId) {
        Customer customer = customerRepo.findById(customerId)
        .orElseThrow(() -> new RuntimeException("Customer not found"));
    
        CreditCard creditCard = creditCardService.getCreditCardById(creditCardId);
    
        customer.getWallet().remove(creditCard);
        customerRepo.save(customer);

        creditCardService.deleteCreditCardById(creditCardId);
    }
}
