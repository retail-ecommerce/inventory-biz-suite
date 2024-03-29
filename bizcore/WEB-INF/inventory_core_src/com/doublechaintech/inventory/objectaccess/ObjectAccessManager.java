
package com.doublechaintech.inventory.objectaccess;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.inventory.InventoryUserContext;
import com.doublechaintech.inventory.BaseEntity;
import com.doublechaintech.inventory.SmartList;

public interface ObjectAccessManager{

		

	public ObjectAccess createObjectAccess(InventoryUserContext userContext, String name, String objectType, String list1, String list2, String list3, String list4, String list5, String list6, String list7, String list8, String list9, String appId) throws Exception;	
	public ObjectAccess updateObjectAccess(InventoryUserContext userContext,String objectAccessId, int objectAccessVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public ObjectAccess loadObjectAccess(InventoryUserContext userContext, String objectAccessId, String [] tokensExpr) throws Exception;
	public ObjectAccess internalSaveObjectAccess(InventoryUserContext userContext, ObjectAccess objectAccess) throws Exception;
	public ObjectAccess internalSaveObjectAccess(InventoryUserContext userContext, ObjectAccess objectAccess,Map<String,Object>option) throws Exception;
	
	public ObjectAccess transferToAnotherApp(InventoryUserContext userContext, String objectAccessId, String anotherAppId)  throws Exception;
 

	public void delete(InventoryUserContext userContext, String objectAccessId, int version) throws Exception;
	public int deleteAll(InventoryUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(InventoryUserContext userContext, ObjectAccess newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	



}


