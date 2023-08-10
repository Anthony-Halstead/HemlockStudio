/**
 * The KeyGeneratorUtility class generates a RSA key pair with a key size of 2048 bits.
 */
package com.HemlockStudiosWebsite.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
public class KeyGeneratorUtility {
/**
 * The function generates a RSA key pair with a key size of 2048 bits.
 * 
 * @return The method is returning a KeyPair object.
 */
public static KeyPair generateRsaKey(){
KeyPair keyPair;
try{
KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
keyPairGenerator.initialize(2048);
keyPair = keyPairGenerator.generateKeyPair();
}catch(Exception e){
throw new IllegalStateException();
}
return keyPair;
}
}