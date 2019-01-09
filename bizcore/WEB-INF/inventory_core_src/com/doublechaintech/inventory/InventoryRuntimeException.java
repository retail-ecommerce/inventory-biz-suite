
package com.doublechaintech.inventory;


public class InventoryRuntimeException extends RuntimeException {
    static final long serialVersionUID = -1;

    public InventoryRuntimeException() {
        super();
    }


    public InventoryRuntimeException(String message) {
        super(message);
    }


    public InventoryRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }


    public InventoryRuntimeException(Throwable cause) {
        super(cause);
    }


   
}












