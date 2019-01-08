
package com.doublechaintech.inventory.formmessage;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.inventory.InventoryUserContext;
import com.doublechaintech.inventory.BaseEntity;
import com.doublechaintech.inventory.SmartList;

public interface FormMessageManager{

		

	public FormMessage createFormMessage(InventoryUserContext userContext, String title, String formId, String level) throws Exception;	
	public FormMessage updateFormMessage(InventoryUserContext userContext,String formMessageId, int formMessageVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public FormMessage loadFormMessage(InventoryUserContext userContext, String formMessageId, String [] tokensExpr) throws Exception;
	public FormMessage internalSaveFormMessage(InventoryUserContext userContext, FormMessage formMessage) throws Exception;
	public FormMessage internalSaveFormMessage(InventoryUserContext userContext, FormMessage formMessage,Map<String,Object>option) throws Exception;
	
	public FormMessage transferToAnotherForm(InventoryUserContext userContext, String formMessageId, String anotherFormId)  throws Exception;
 

	public void delete(InventoryUserContext userContext, String formMessageId, int version) throws Exception;
	public int deleteAll(InventoryUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(InventoryUserContext userContext, FormMessage newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	



}


