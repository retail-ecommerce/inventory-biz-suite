
package com.doublechaintech.inventory.platform;
import com.doublechaintech.inventory.EntityNotFoundException;
public class PlatformNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 1L;
	public PlatformNotFoundException(String string) {
		super(string);
	}

}

