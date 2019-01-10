
package com.doublechaintech.inventory.platform;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.inventory.InventoryUserContext;
import com.doublechaintech.inventory.BaseEntity;
import com.doublechaintech.inventory.SmartList;

public interface PlatformManager{

		

	public Platform createPlatform(InventoryUserContext userContext, String name, String introduction, String currentVersion) throws Exception;	
	public Platform updatePlatform(InventoryUserContext userContext,String platformId, int platformVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public Platform loadPlatform(InventoryUserContext userContext, String platformId, String [] tokensExpr) throws Exception;
	public Platform internalSavePlatform(InventoryUserContext userContext, Platform platform) throws Exception;
	public Platform internalSavePlatform(InventoryUserContext userContext, Platform platform,Map<String,Object>option) throws Exception;
	


	public void delete(InventoryUserContext userContext, String platformId, int version) throws Exception;
	public int deleteAll(InventoryUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(InventoryUserContext userContext, Platform newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	

	//public  ProductManager getProductManager(InventoryUserContext userContext, String platformId, String name, String introduction ,String [] tokensExpr)  throws Exception;
	
	public  Platform addProduct(InventoryUserContext userContext, String platformId, String name, String introduction , String [] tokensExpr)  throws Exception;
	public  Platform removeProduct(InventoryUserContext userContext, String platformId, String productId, int productVersion,String [] tokensExpr)  throws Exception;
	public  Platform updateProduct(InventoryUserContext userContext, String platformId, String productId, int productVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  SkuInventoryManager getSkuInventoryManager(InventoryUserContext userContext, String platformId, String name, int stockLevel, int backorderLevel, int preorderLevel, int stockThreshold, int backorderThreshol, int preorderThreshol, String status, String productId ,String [] tokensExpr)  throws Exception;
	
	public  Platform addSkuInventory(InventoryUserContext userContext, String platformId, String name, int stockLevel, int backorderLevel, int preorderLevel, int stockThreshold, int backorderThreshol, int preorderThreshol, String status, String productId , String [] tokensExpr)  throws Exception;
	public  Platform removeSkuInventory(InventoryUserContext userContext, String platformId, String skuInventoryId, int skuInventoryVersion,String [] tokensExpr)  throws Exception;
	public  Platform updateSkuInventory(InventoryUserContext userContext, String platformId, String skuInventoryId, int skuInventoryVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


