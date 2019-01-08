
package com.doublechaintech.inventory.secuserblocking;
//import com.doublechaintech.inventory.EntityNotFoundException;
import com.doublechaintech.inventory.InventoryException;
import com.doublechaintech.inventory.Message;
import java.util.List;

public class SecUserBlockingManagerException extends InventoryException {
	private static final long serialVersionUID = 1L;
	public SecUserBlockingManagerException(String string) {
		super(string);
	}
	public SecUserBlockingManagerException(Message message) {
		super(message);
	}
	public SecUserBlockingManagerException(List<Message> messageList) {
		super(messageList);
	}

}


