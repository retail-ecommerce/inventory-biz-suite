
package com.doublechaintech.inventory.userapp;
//import com.doublechaintech.inventory.EntityNotFoundException;
import com.doublechaintech.inventory.InventoryException;
import com.doublechaintech.inventory.Message;
import java.util.List;

public class UserAppManagerException extends InventoryException {
	private static final long serialVersionUID = 1L;
	public UserAppManagerException(String string) {
		super(string);
	}
	public UserAppManagerException(Message message) {
		super(message);
	}
	public UserAppManagerException(List<Message> messageList) {
		super(messageList);
	}

}


