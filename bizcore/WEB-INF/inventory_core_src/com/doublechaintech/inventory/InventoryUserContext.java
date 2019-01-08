package com.doublechaintech.inventory;

public interface InventoryUserContext extends UserContext{
    //define the domain specific user model
	String getLocaleKey(String subject);
	void setChecker(InventoryChecker checker);
	InventoryChecker getChecker();
}

