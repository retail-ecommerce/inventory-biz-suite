
package com.doublechaintech.inventory.userwhitelist;
import com.doublechaintech.inventory.EntityNotFoundException;
public class UserWhiteListNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 1L;
	public UserWhiteListNotFoundException(String string) {
		super(string);
	}

}

