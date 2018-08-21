package com.cg.alfabankapp.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.alfabankapp.exceptions.NoAccountFoundException;
import com.cg.alfabankapp.factory.MMBankFactory;
import com.cg.alfabankapp.service.MoneyMoneyBankService;
import com.cg.bank.framework.account.pojo.BankAccount;

@RestController
public class BankAccountController {
    @Autowired
	MMBankFactory mmBankFactory;
    @Autowired
	MoneyMoneyBankService mmBankService;
	
   private Map<String,Object> accountDetails = new HashMap<String,Object>();
    
   @RequestMapping(value = "/add", method = RequestMethod.POST)
   
   //Adding an initial Account
   public String addInitial()
   {
	   accountDetails.put("accountHolderName", "Alfaiz");
	   
	    String dob = "1996-08-14";
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(dob, formatter);
		accountDetails.put("dateOfBirth", date);
		accountDetails.put("gender", "Male");
		accountDetails.put("contactNumber", "7001230644");
		accountDetails.put("houseNo", "13B");
		accountDetails.put("street", "Agarwal Tola");
		accountDetails.put("city", "Patna");
		accountDetails.put("state", "Bihar");
		accountDetails.put("pincode", "800007");
		accountDetails.put("email", "alfaiz.ullah123@gmail.com");
		accountDetails.put("nationality", "Indian");
		accountDetails.put("accountType", "SavingAccount");
		accountDetails.put("salaried", true);
		
		accountDetails.put("accountBalance", "12000");
		
		mmBankService.addBankAccount(mmBankFactory.createNewSavingsAccount(accountDetails));
		
	return "Added SuccessFully";
	
   }

   //Adding An account
   @RequestMapping(value = "/createAccount", method = RequestMethod.POST )
   public String createAccount(@RequestBody Map<String, Object> map)
   {
	   if (map.get("accountType").toString().equals("savingAccount")) {
		   mmBankService.addBankAccount(mmBankFactory.createNewSavingsAccount(map));

		} else {
			
			mmBankService.addBankAccount(mmBankFactory.createNewCurrentAccount(map));
		}   
	return "Added SuccessFully Dynamically";
	  
   }
  
   @RequestMapping("/viewAllCustomers")
   public Collection<BankAccount> viewAllCustomers() throws NoAccountFoundException
   {
	   if(mmBankService.getBankAccounts().size()==0)
	   {
		   throw new NoAccountFoundException("No Accounts to Display......");
	   }
	   
   	return mmBankService.getBankAccounts();
   }

   @RequestMapping("/searchAccount/{accountToBeSearched}")
	public BankAccount searchAccount(@PathVariable int accountToBeSearched) throws AccountNotFoundException
	{
	    if(mmBankService.getAccountByAccountNumber(accountToBeSearched) != null)
	    {
	    	return mmBankService.getAccountByAccountNumber(accountToBeSearched);    	
	    }
	    throw new AccountNotFoundException("AccountNumber "+accountToBeSearched+" Not Found.......For Search");
	}
   

   @RequestMapping(value="/update" , method = RequestMethod.PUT)
    public String updateAccount(@RequestBody Map<String, Object> upMapint ) throws AccountNotFoundException
    {
	  int accountToBeSearched  = Integer.parseInt(upMapint.get("accountNumber").toString());

	    if(mmBankService.getAccountByAccountNumber(accountToBeSearched) != null)
	    {
	    	mmBankService.updateAccount(upMapint);
	 	   return "SuccessFully Updated .............";
	    }
	   
	    throw new AccountNotFoundException("AccountNumber "+accountToBeSearched+" Not Found........For Update");
	   
    }
   
   @RequestMapping(value="/account/{accountToBeDeleted}" , method = RequestMethod.DELETE)
   public String deleteAccount(@PathVariable int accountToBeDeleted) throws AccountNotFoundException
   {
	   if(mmBankService.getAccountByAccountNumber(accountToBeDeleted) != null)
	    {
	    	 mmBankService.removeBankAccountByAccountNumber(accountToBeDeleted);
	    	 return "SuccessFully Deleted";
	    }
	   throw new AccountNotFoundException("AccountNumber "+accountToBeDeleted+" Not Found.......For Delete");
	}
}
   


