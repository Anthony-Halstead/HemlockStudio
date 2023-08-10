/**
 * The RSAKeyProperties class is a utility class that generates and stores RSA public and private keys.
 */
package com.HemlockStudiosWebsite.utils;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import org.springframework.stereotype.Component;
@Component
public class RSAKeyProperties {
private RSAPublicKey publicKey;
private RSAPrivateKey privateKey;
// The `public RSAKeyProperties()` is a constructor method for the `RSAKeyProperties` class.
public RSAKeyProperties(){
KeyPair pair = KeyGeneratorUtility.generateRsaKey();
this.publicKey = (RSAPublicKey) pair.getPublic();
this.privateKey = (RSAPrivateKey) pair.getPrivate();
}
/**
 * The function returns the public key of an RSA encryption.
 * 
 * @return The method is returning an RSAPublicKey object.
 */
public RSAPublicKey getPublicKey(){
return this.publicKey;
}
/**
 * The function sets the value of the publicKey variable to the provided RSAPublicKey.
 * 
 * @param publicKey The publicKey parameter is of type RSAPublicKey, which is a class representing an
 * RSA public key.
 */
public void setPublicKey(RSAPublicKey publicKey){
this.publicKey = publicKey;
}
/**
 * The function returns the private key of an RSA encryption.
 * 
 * @return The method is returning an RSAPrivateKey object.
 */
public RSAPrivateKey getPrivateKey(){
return this.privateKey;
}
/**
 * The function sets the private key for an RSA encryption.
 * 
 * @param privateKey The private key is an instance of the RSAPrivateKey class. It is used in
 * asymmetric encryption algorithms, such as RSA, to decrypt data that has been encrypted using the
 * corresponding public key.
 */
public void setPrivateKey(RSAPrivateKey privateKey){
this.privateKey = privateKey;
}
}