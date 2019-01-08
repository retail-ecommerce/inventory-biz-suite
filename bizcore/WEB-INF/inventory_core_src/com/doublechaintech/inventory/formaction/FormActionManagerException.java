
package com.doublechaintech.inventory.formaction;
//import com.doublechaintech.inventory.EntityNotFoundException;
import com.doublechaintech.inventory.InventoryException;
import com.doublechaintech.inventory.Message;
import java.util.List;

public class FormActionManagerException extends InventoryException {
	private static final long serialVersionUID = 1L;
	public FormActionManagerException(String string) {
		super(string);
	}
	public FormActionManagerException(Message message) {
		super(message);
	}
	public FormActionManagerException(List<Message> messageList) {
		super(messageList);
	}

}












