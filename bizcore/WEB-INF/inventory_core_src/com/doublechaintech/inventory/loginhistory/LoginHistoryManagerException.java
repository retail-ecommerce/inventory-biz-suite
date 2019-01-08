
package com.doublechaintech.inventory.loginhistory;
//import com.doublechaintech.inventory.EntityNotFoundException;
import com.doublechaintech.inventory.InventoryException;
import com.doublechaintech.inventory.Message;
import java.util.List;

public class LoginHistoryManagerException extends InventoryException {
	private static final long serialVersionUID = 1L;
	public LoginHistoryManagerException(String string) {
		super(string);
	}
	public LoginHistoryManagerException(Message message) {
		super(message);
	}
	public LoginHistoryManagerException(List<Message> messageList) {
		super(messageList);
	}

}


