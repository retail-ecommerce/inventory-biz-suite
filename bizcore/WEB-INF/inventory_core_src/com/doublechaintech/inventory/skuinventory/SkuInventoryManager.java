
package com.doublechaintech.inventory.skuinventory;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.inventory.InventoryUserContext;
import com.doublechaintech.inventory.BaseEntity;
import com.doublechaintech.inventory.SmartList;

public interface SkuInventoryManager{

		

	public SkuInventory createSkuInventory(InventoryUserContext userContext, String name, int stockLevel, int backorderLevel, int preorderLevel, int stockThreshold, int backorderThreshol, int preorderThreshol, String status, String productId, String platformId) throws Exception;	
	public SkuInventory updateSkuInventory(InventoryUserContext userContext,String skuInventoryId, int skuInventoryVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public SkuInventory loadSkuInventory(InventoryUserContext userContext, String skuInventoryId, String [] tokensExpr) throws Exception;
	public SkuInventory internalSaveSkuInventory(InventoryUserContext userContext, SkuInventory skuInventory) throws Exception;
	public SkuInventory internalSaveSkuInventory(InventoryUserContext userContext, SkuInventory skuInventory,Map<String,Object>option) throws Exception;
	
	public SkuInventory transferToAnotherProduct(InventoryUserContext userContext, String skuInventoryId, String anotherProductId)  throws Exception;
 	public SkuInventory transferToAnotherPlatform(InventoryUserContext userContext, String skuInventoryId, String anotherPlatformId)  throws Exception;
 

	public void delete(InventoryUserContext userContext, String skuInventoryId, int version) throws Exception;
	public int deleteAll(InventoryUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(InventoryUserContext userContext, SkuInventory newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	



}


