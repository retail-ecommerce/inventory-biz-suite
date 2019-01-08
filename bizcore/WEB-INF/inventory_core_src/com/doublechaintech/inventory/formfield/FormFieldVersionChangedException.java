
package com.doublechaintech.inventory.formfield;
import com.doublechaintech.inventory.EntityNotFoundException;

public class FormFieldVersionChangedException extends FormFieldManagerException {
	private static final long serialVersionUID = 1L;
	public FormFieldVersionChangedException(String string) {
		super(string);
	}


}


