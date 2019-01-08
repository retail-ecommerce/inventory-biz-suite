
package com.doublechaintech.inventory.userwhitelist;
import com.doublechaintech.inventory.EntityNotFoundException;

public class UserWhiteListVersionChangedException extends UserWhiteListManagerException {
	private static final long serialVersionUID = 1L;
	public UserWhiteListVersionChangedException(String string) {
		super(string);
	}


}


