package com.medplus.online_shopping.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OnlineShoppingExceptionHandler {
	@ExceptionHandler(value = CustomerNotFoundException.class)
	public ResponseEntity<Object> exception(CustomerNotFoundException e) {
		return new ResponseEntity<Object>("Invalid customer Id ,customer not found! ENTER CORRECTLY ", HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value = OrderNotFoundException.class)
	public ResponseEntity<Object> exception1(OrderNotFoundException e) {
		return new ResponseEntity<Object>("Invalid order Id ,order not found! ENTER CORRECTLY ", HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value = InvalidPasswordException.class)
	public ResponseEntity<Object> exception2(InvalidPasswordException e) {
		return new ResponseEntity<Object>("Invalid Password! ENTER CORRECTLY ", HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value = ProductNotFoundException.class)
	public ResponseEntity<Object> exception3(ProductNotFoundException e) {
		return new ResponseEntity<Object>("product not available", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = ProductQuantityException.class)
	public ResponseEntity<Object> exception4(ProductQuantityException e) {
		return new ResponseEntity<Object>("availabe quantity is less tha your order!", HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value = NumberFormatException.class)
	public ResponseEntity<Object> exception5(NumberFormatException e) {
		return new ResponseEntity<Object>("entered value is not number ", HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value = TransactionNotFoundException.class)
	public ResponseEntity<Object> exception6(TransactionNotFoundException e) {
		return new ResponseEntity<Object>("Invalid Transaction Id ,transaction not found! ENTER CORRECTLY ", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = TransactionListEmptyException.class)
	public ResponseEntity<Object> exception7(TransactionListEmptyException e) {
		return new ResponseEntity<Object>("There are no transactions", HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value = WishlistEmptyException.class)
	public ResponseEntity<Object> exception8(WishlistEmptyException e) {
		return new ResponseEntity<Object>("Wishlist is Empty Add Item ", HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value = CartEmptyException.class)
	public ResponseEntity<Object> exception9(CartEmptyException e) {
		return new ResponseEntity<Object>("Cart is Empty Add Item", HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value = AddressEmptyException.class)
	public ResponseEntity<Object> exception10(AddressEmptyException e) {
		return new ResponseEntity<Object>("Your address is Empty! Add Address", HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value = ProductNotAvailableInCartException.class)
	public ResponseEntity<Object> exception11(ProductNotAvailableInCartException e) {
		return new ResponseEntity<Object>("This product is not available in Your Cart! Add Product", HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value = ProductNotAvailableInWishListException.class)
	public ResponseEntity<Object> exception12(ProductNotAvailableInWishListException e) {
		return new ResponseEntity<Object>("This product is not available in Your Cart! Add Product", HttpStatus.NOT_FOUND);
	}
	
	

}
