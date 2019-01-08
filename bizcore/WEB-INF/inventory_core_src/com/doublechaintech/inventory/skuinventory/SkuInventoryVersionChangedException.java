
package com.doublechaintech.inventory.skuinventory;
import com.doublechaintech.inventory.EntityNotFoundException;

public class SkuInventoryVersionChangedException extends SkuInventoryManagerException {
	private static final long serialVersionUID = 1L;
	public SkuInventoryVersionChangedException(String string) {
		super(string);
	}


}


