
package com.doublechaintech.inventory.secuser;
import com.doublechaintech.inventory.EntityNotFoundException;

public class SecUserVersionChangedException extends SecUserManagerException {
	private static final long serialVersionUID = 1L;
	public SecUserVersionChangedException(String string) {
		super(string);
	}


}


