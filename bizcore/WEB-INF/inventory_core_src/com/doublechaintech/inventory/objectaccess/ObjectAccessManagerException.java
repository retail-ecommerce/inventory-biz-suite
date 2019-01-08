
package com.doublechaintech.inventory.objectaccess;
//import com.doublechaintech.inventory.EntityNotFoundException;
import com.doublechaintech.inventory.InventoryException;
import com.doublechaintech.inventory.Message;
import java.util.List;

public class ObjectAccessManagerException extends InventoryException {
	private static final long serialVersionUID = 1L;
	public ObjectAccessManagerException(String string) {
		super(string);
	}
	public ObjectAccessManagerException(Message message) {
		super(message);
	}
	public ObjectAccessManagerException(List<Message> messageList) {
		super(messageList);
	}

}


