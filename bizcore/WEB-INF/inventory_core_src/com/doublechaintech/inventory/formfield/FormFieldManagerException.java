
package com.doublechaintech.inventory.formfield;
//import com.doublechaintech.inventory.EntityNotFoundException;
import com.doublechaintech.inventory.InventoryException;
import com.doublechaintech.inventory.Message;
import java.util.List;

public class FormFieldManagerException extends InventoryException {
	private static final long serialVersionUID = 1L;
	public FormFieldManagerException(String string) {
		super(string);
	}
	public FormFieldManagerException(Message message) {
		super(message);
	}
	public FormFieldManagerException(List<Message> messageList) {
		super(messageList);
	}

}


