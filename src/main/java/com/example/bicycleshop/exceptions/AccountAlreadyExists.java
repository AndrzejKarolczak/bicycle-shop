package com.example.bicycleshop.exceptions;

public class AccountAlreadyExists extends RuntimeException {
	public AccountAlreadyExists(String login){
		super("Użytkownik " + login + " jest już zarejestrowany w systemie");
	}
}
