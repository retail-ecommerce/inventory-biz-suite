
package com.doublechaintech.inventory.listaccess;
import com.doublechaintech.inventory.EntityNotFoundException;

public class ListAccessVersionChangedException extends ListAccessManagerException {
	private static final long serialVersionUID = 1L;
	public ListAccessVersionChangedException(String string) {
		super(string);
	}


}


