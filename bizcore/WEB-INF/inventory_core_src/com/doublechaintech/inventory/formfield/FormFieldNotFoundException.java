
package com.doublechaintech.inventory.formfield;
import com.doublechaintech.inventory.EntityNotFoundException;
public class FormFieldNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 1L;
	public FormFieldNotFoundException(String string) {
		super(string);
	}

}

