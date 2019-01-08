
package com.doublechaintech.inventory.product;
import com.doublechaintech.inventory.EntityNotFoundException;

public class ProductVersionChangedException extends ProductManagerException {
	private static final long serialVersionUID = 1L;
	public ProductVersionChangedException(String string) {
		super(string);
	}


}


