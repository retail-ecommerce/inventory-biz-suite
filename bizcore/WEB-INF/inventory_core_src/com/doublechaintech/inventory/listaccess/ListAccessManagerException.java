
package com.doublechaintech.inventory.listaccess;
//import com.doublechaintech.inventory.EntityNotFoundException;
import com.doublechaintech.inventory.InventoryException;
import com.doublechaintech.inventory.Message;
import java.util.List;

public class ListAccessManagerException extends InventoryException {
	private static final long serialVersionUID = 1L;
	public ListAccessManagerException(String string) {
		super(string);
	}
	public ListAccessManagerException(Message message) {
		super(message);
	}
	public ListAccessManagerException(List<Message> messageList) {
		super(messageList);
	}

}


