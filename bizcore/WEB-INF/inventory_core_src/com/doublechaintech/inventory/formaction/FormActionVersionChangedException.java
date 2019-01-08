
package com.doublechaintech.inventory.formaction;
import com.doublechaintech.inventory.EntityNotFoundException;

public class FormActionVersionChangedException extends FormActionManagerException {
	private static final long serialVersionUID = 1L;
	public FormActionVersionChangedException(String string) {
		super(string);
	}


}


