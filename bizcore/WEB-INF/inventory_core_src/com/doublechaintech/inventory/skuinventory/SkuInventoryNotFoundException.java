
package com.doublechaintech.inventory.skuinventory;
import com.doublechaintech.inventory.EntityNotFoundException;
public class SkuInventoryNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 1L;
	public SkuInventoryNotFoundException(String string) {
		super(string);
	}

}

