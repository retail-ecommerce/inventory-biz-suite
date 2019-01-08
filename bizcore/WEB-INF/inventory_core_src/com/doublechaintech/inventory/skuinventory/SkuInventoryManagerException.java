
package com.doublechaintech.inventory.skuinventory;
//import com.doublechaintech.inventory.EntityNotFoundException;
import com.doublechaintech.inventory.InventoryException;
import com.doublechaintech.inventory.Message;
import java.util.List;

public class SkuInventoryManagerException extends InventoryException {
	private static final long serialVersionUID = 1L;
	public SkuInventoryManagerException(String string) {
		super(string);
	}
	public SkuInventoryManagerException(Message message) {
		super(message);
	}
	public SkuInventoryManagerException(List<Message> messageList) {
		super(messageList);
	}

}


