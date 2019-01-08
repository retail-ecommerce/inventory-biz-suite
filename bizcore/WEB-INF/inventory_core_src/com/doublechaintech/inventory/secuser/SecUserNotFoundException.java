
package com.doublechaintech.inventory.secuser;
import com.doublechaintech.inventory.EntityNotFoundException;
public class SecUserNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 1L;
	public SecUserNotFoundException(String string) {
		super(string);
	}

}

