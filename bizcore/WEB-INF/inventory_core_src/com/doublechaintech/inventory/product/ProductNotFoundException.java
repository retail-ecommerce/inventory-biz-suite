
package com.doublechaintech.inventory.product;
import com.doublechaintech.inventory.EntityNotFoundException;
public class ProductNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 1L;
	public ProductNotFoundException(String string) {
		super(string);
	}

}

