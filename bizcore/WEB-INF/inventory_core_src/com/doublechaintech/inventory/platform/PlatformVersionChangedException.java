
package com.doublechaintech.inventory.platform;
import com.doublechaintech.inventory.EntityNotFoundException;

public class PlatformVersionChangedException extends PlatformManagerException {
	private static final long serialVersionUID = 1L;
	public PlatformVersionChangedException(String string) {
		super(string);
	}


}


