
package com.doublechaintech.inventory.listaccess;
import com.doublechaintech.inventory.EntityNotFoundException;
public class ListAccessNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 1L;
	public ListAccessNotFoundException(String string) {
		super(string);
	}

}

