package bookshoppublisher;

import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
public class BookshopPublisherImp implements BookshopPublisher {
	
	private String userArray[] = {"user","bsemina","Admin","user0","Arsenal"};

	@Override
	public String checkPassword(String password) {
		 
		int digits = 0;
		int upperCase = 0;
		char charactor;
		
		if (password.length() < 8) {   
            return "Password must contain at least 8 charactors " ;  
        } else {   
           
            for (int i = 0; i < password.length(); i++) {  
            	charactor = password.charAt(i);  
                if (Character.isUpperCase(charactor)) {          
                	upperCase++;  
                } else if (Character.isDigit(charactor)) {  
                	digits++;    
                }  
            } 
            
            if (upperCase < 1) {
            	return "Password must contain at least 1 Uppercase charactor";
            }
            else if (digits < 1) {
            	return "Password must contain at least 1 Digit";
            	
            }else {
            	return "OK"; 
            }
        }                                                                                                                                                                                               
	}
	
	public byte[] genarateHashedPass(String password) throws Exception {
		
		SecureRandom secRandom = new SecureRandom();
		byte[] salt = new byte[16];
		secRandom.nextBytes(salt);
		
		KeySpec specObj = new PBEKeySpec(password.toCharArray(), salt, 65536, 64);
		SecretKeyFactory keyFac = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		
		byte[] hashedPass = keyFac.generateSecret(specObj).getEncoded();
		Base64.Encoder enc = Base64.getEncoder();
		//System.out.printf("hash: %s%n", enc.encodeToString(hashedPass));
		
		return hashedPass;
	}
	
	public Boolean checkUser(String user) {
		
		List<String> userList = Arrays.asList(userArray);
		
		if(userList.contains(user)){
            System.out.println("Error User Already Exists !!!");
            
            return true;
        }
		return false;
	}
}
