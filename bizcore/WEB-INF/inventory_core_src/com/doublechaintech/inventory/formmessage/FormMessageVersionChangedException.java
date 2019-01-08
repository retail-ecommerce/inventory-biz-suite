
package com.doublechaintech.inventory.formmessage;
import com.doublechaintech.inventory.EntityNotFoundException;

public class FormMessageVersionChangedException extends FormMessageManagerException {
	private static final long serialVersionUID = 1L;
	public FormMessageVersionChangedException(String string) {
		super(string);
	}


}


