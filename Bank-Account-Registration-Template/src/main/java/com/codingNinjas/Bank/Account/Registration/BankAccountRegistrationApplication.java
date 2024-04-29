package com.codingNinjas.Bank.Account.Registration;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@SpringBootApplication
public class BankAccountRegistrationApplication {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		System.out.println("Welcome to Account Registration Application!");

		List<Account> accounts = new ArrayList<>();

		System.out.println("Please enter your name:");
		String name = scanner.nextLine();

		while (true) {
			System.out.println("Do you want to add an account?\n1. Yes\n2. No ");
			int input = scanner.nextInt();
			if (input == 1) {
				System.out.println("Please select the account type:\n1. Current\n2. Savings");
				int accountType = scanner.nextInt();
				if (accountType == 1) {
					currentAccount currentAccount = context.getBean("currentAccount", currentAccount.class);
					System.out.println("Enter the opening balance:");
					double openingBalance = scanner.nextDouble();
					currentAccount.addBalance(openingBalance);
					accounts.add(currentAccount);
				} else if (accountType == 2) {
					savingsAccount savingsAccount = context.getBean("savingsAccount", savingsAccount.class);
					System.out.println("Enter the opening balance:");
					double openingBalance = scanner.nextDouble();
					savingsAccount.addBalance(openingBalance);
					accounts.add(savingsAccount);
				}
			} else if (input == 2) {
				break;
			} else {
				System.out.println("Invalid Choice");
				return;
			}
		}

		System.out.println(name + ", here is the list of your accounts:");
		for (Account account : accounts) {
			System.out.println(account.getAccountType() + " Account opening balance: " + account.getBalance() + " Reference Id @" + Integer.toHexString(account.hashCode()));
		}

		context.close();
	}
}
