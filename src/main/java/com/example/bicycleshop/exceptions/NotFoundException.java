package com.example.bicycleshop.exceptions;

public class NotFoundException extends RuntimeException {
	public NotFoundException(Class<?> type, long id){
		super("Nie znaleziono obiektu " + type.getSimpleName() + " o następującym id '" + id + "'");
	}
}
