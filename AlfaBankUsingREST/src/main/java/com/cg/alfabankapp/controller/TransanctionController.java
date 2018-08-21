package com.cg.alfabankapp.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.alfabankapp.exceptions.InvalidAccountNumberException;
import com.cg.alfabankapp.exceptions.InvalidAccountOrAmountException;
import com.cg.alfabankapp.exceptions.InvalidAmountException;
import com.cg.alfabankapp.service.MoneyMoneyBankService;
import com.cg.bank.framework.account.pojo.BankAccount;

@RestController
public class TransanctionController {

	@Autowired
	MoneyMoneyBankService moneyBankService;
	
	@RequestMapping(value = "/depositTransact/{accountNumber}/{Amount}", method = RequestMethod.PUT)
	public String depositSuccess(@PathVariable int accountNumber, @PathVariable double Amount) throws InvalidAmountException, InvalidAccountOrAmountException, InvalidAccountNumberException
	{		

		if(Amount<=0)
		{
			throw new InvalidAmountException("Amount in Deposit Should Be Greater Than 0");
		}
	    if(moneyBankService.getAccountByAccountNumber(accountNumber) == null)
	    {
	    	 throw new InvalidAccountNumberException("Account Not Found........For Deposit");   	
	    }
		double check = moneyBankService.depositAmount(accountNumber, Amount);
		if (check == 0.0) {
			throw new InvalidAccountOrAmountException("Deposit is not Successfull Please Try Again....");
		} 
		return "depositSuccess";
	}
	
	@RequestMapping(value = "/withdrawTransact/{accountNumber}/{Amount}", method = RequestMethod.PUT)
	public Map<Integer, Integer> withdrawSuccess(@PathVariable int accountNumber,@PathVariable double Amount) throws InvalidAmountException, InvalidAccountNumberException, InvalidAccountOrAmountException
	{
		if(Amount<=0)
		{
			throw new InvalidAmountException("Amount in Withdraw Should Be Greater Than 0");
		}
	    if(moneyBankService.getAccountByAccountNumber(accountNumber) == null)
	    {
	    	 throw new InvalidAccountNumberException("Account Not Found........For Withdraw");   	
	    }
		
		double check = moneyBankService.withdrawAmount(accountNumber, Amount);
		BankAccount bankAccount = moneyBankService.getAccountByAccountNumber(accountNumber);
		
		if (check == -1) {
			throw new InvalidAccountOrAmountException("Withdraw is not Successfull as Withdraw Amount is Greater Than Balance.....");
		} 
		
			Map<Integer, Integer> denomination = new HashMap<Integer, Integer>();
			return giveDenominations(check);
	}
	
	
	@RequestMapping(value = "/fundTransfer/{accountNumberFrom}/{accountNumberTo}/{Amount}", method = RequestMethod.PUT)
	public String fundTransferSuccess(@PathVariable int accountNumberFrom,@PathVariable int accountNumberTo,@PathVariable double Amount) throws InvalidAmountException, InvalidAccountNumberException, InvalidAccountOrAmountException
	{
		if(Amount<=0)
		{
			throw new InvalidAmountException("Amount in Fund Transfer Should Be Greater Than 0");
		}
	    if(moneyBankService.getAccountByAccountNumber(accountNumberFrom) == null || moneyBankService.getAccountByAccountNumber(accountNumberTo) == null)
	    {
	    	 throw new InvalidAccountNumberException("Account Not Found........For Fund Transfer");   	
	    }
	
		double  check = moneyBankService.performFundTransfer(accountNumberTo, accountNumberFrom, Amount);
		if (check == 0.0) {
			throw new InvalidAccountOrAmountException("Fund Transfer is not Successfull.......");
		}
		return "fundTransferSuccess";
	}
	
	private Map<Integer, Integer> giveDenominations(double check) {
		Map<Integer, Integer> denomination = new HashMap<Integer, Integer>();

		if (check >= 2000) {
			denomination.put(2000, (int) check / 2000);
			check %= 2000;
		}
		if (check >= 500) {
			denomination.put(500, (int) check / 500);
			check %= 500;
		}
		if (check >= 200) {
			denomination.put(200, (int) check / 200);
			check %= 200;
		}
		if (check >= 100) {
			denomination.put(100, (int) check / 100);
			check %= 100;
		}
		if (check >= 50) {
			denomination.put(50, (int) check / 50);
			check %= 50;
		}
		if (check >= 10) {
			denomination.put(10, (int) check / 10);
			check %= 10;
		}
		if (check >= 1) {
			denomination.put(1, (int) check / 1);
			check %= 1;
		}
		return denomination;

	
	}
	
	
	
}
