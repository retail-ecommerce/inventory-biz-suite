
package com.doublechaintech.inventory.product;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.inventory.InventoryUserContext;
import com.doublechaintech.inventory.BaseEntity;
import com.doublechaintech.inventory.SmartList;

public interface ProductManager{

		

	public Product createProduct(InventoryUserContext userContext, String name, String introduction, String platformId) throws Exception;	
	public Product updateProduct(InventoryUserContext userContext,String productId, int productVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public Product loadProduct(InventoryUserContext userContext, String productId, String [] tokensExpr) throws Exception;
	public Product internalSaveProduct(InventoryUserContext userContext, Product product) throws Exception;
	public Product internalSaveProduct(InventoryUserContext userContext, Product product,Map<String,Object>option) throws Exception;
	
	public Product transferToAnotherPlatform(InventoryUserContext userContext, String productId, String anotherPlatformId)  throws Exception;
 

	public void delete(InventoryUserContext userContext, String productId, int version) throws Exception;
	public int deleteAll(InventoryUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(InventoryUserContext userContext, Product newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	

	//public  SkuInventoryManager getSkuInventoryManager(InventoryUserContext userContext, String productId, int stockLevel, int backorderLevel, int preorderLevel, int stockThreshold, int backorderThreshol, int preorderThreshol, String status, String platformId ,String [] tokensExpr)  throws Exception;
	
	public  Product addSkuInventory(InventoryUserContext userContext, String productId, int stockLevel, int backorderLevel, int preorderLevel, int stockThreshold, int backorderThreshol, int preorderThreshol, String status, String platformId , String [] tokensExpr)  throws Exception;
	public  Product removeSkuInventory(InventoryUserContext userContext, String productId, String skuInventoryId, int skuInventoryVersion,String [] tokensExpr)  throws Exception;
	public  Product updateSkuInventory(InventoryUserContext userContext, String productId, String skuInventoryId, int skuInventoryVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


