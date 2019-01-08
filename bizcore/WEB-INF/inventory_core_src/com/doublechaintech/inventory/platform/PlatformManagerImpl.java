
package com.doublechaintech.inventory.platform;

import java.util.Date;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.math.BigDecimal;
import com.terapico.caf.DateTime;
import com.doublechaintech.inventory.BaseEntity;


import com.doublechaintech.inventory.Message;
import com.doublechaintech.inventory.SmartList;
import com.doublechaintech.inventory.MultipleAccessKey;

import com.doublechaintech.inventory.InventoryUserContext;
//import com.doublechaintech.inventory.BaseManagerImpl;
import com.doublechaintech.inventory.InventoryCheckerManager;
import com.doublechaintech.inventory.CustomInventoryCheckerManager;

import com.doublechaintech.inventory.product.Product;
import com.doublechaintech.inventory.skuinventory.SkuInventory;


import com.doublechaintech.inventory.product.Product;
import com.doublechaintech.inventory.platform.Platform;






public class PlatformManagerImpl extends CustomInventoryCheckerManager implements PlatformManager {
	
	private static final String SERVICE_TYPE = "Platform";
	
	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}
	
	
	protected void throwExceptionWithMessage(String value) throws PlatformManagerException{
	
		Message message = new Message();
		message.setBody(value);
		throw new PlatformManagerException(message);

	}
	
	

 	protected Platform savePlatform(InventoryUserContext userContext, Platform platform, String [] tokensExpr) throws Exception{	
 		//return getPlatformDAO().save(platform, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return savePlatform(userContext, platform, tokens);
 	}
 	
 	protected Platform savePlatformDetail(InventoryUserContext userContext, Platform platform) throws Exception{	

 		
 		return savePlatform(userContext, platform, allTokens());
 	}
 	
 	public Platform loadPlatform(InventoryUserContext userContext, String platformId, String [] tokensExpr) throws Exception{				
 
 		userContext.getChecker().checkIdOfPlatform(platformId);
		userContext.getChecker().throwExceptionIfHasErrors( PlatformManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		Platform platform = loadPlatform( userContext, platformId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,platform, tokens);
 	}
 	
 	
 	 public Platform searchPlatform(InventoryUserContext userContext, String platformId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		userContext.getChecker().checkIdOfPlatform(platformId);
		userContext.getChecker().throwExceptionIfHasErrors( PlatformManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		Platform platform = loadPlatform( userContext, platformId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,platform, tokens);
 	}
 	
 	

 	protected Platform present(InventoryUserContext userContext, Platform platform, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,platform,tokens);
		
		
		Platform  platformToPresent = userContext.getDAOGroup().getPlatformDAO().present(platform, tokens);
		
		List<BaseEntity> entityListToNaming = platformToPresent.collectRefercencesFromLists();
		userContext.getDAOGroup().getPlatformDAO().alias(entityListToNaming);
		
		return  platformToPresent;
		
		
	}
 
 	
 	
 	public Platform loadPlatformDetail(InventoryUserContext userContext, String platformId) throws Exception{	
 		Platform platform = loadPlatform( userContext, platformId, allTokens());
 		return present(userContext,platform, allTokens());
		
 	}
 	
 	public Object view(InventoryUserContext userContext, String platformId) throws Exception{	
 		Platform platform = loadPlatform( userContext, platformId, viewTokens());
 		return present(userContext,platform, allTokens());
		
 	}
 	protected Platform savePlatform(InventoryUserContext userContext, Platform platform, Map<String,Object>tokens) throws Exception{	
 		return userContext.getDAOGroup().getPlatformDAO().save(platform, tokens);
 	}
 	protected Platform loadPlatform(InventoryUserContext userContext, String platformId, Map<String,Object>tokens) throws Exception{	
		userContext.getChecker().checkIdOfPlatform(platformId);
		userContext.getChecker().throwExceptionIfHasErrors( PlatformManagerException.class);

 
 		return userContext.getDAOGroup().getPlatformDAO().load(platformId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(InventoryUserContext userContext, Platform platform, Map<String, Object> tokens){
		super.addActions(userContext, platform, tokens);
		
		addAction(userContext, platform, tokens,"@create","createPlatform","createPlatform/","main","primary");
		addAction(userContext, platform, tokens,"@update","updatePlatform","updatePlatform/"+platform.getId()+"/","main","primary");
		addAction(userContext, platform, tokens,"@copy","clonePlatform","clonePlatform/"+platform.getId()+"/","main","primary");
		
		addAction(userContext, platform, tokens,"platform.addProduct","addProduct","addProduct/"+platform.getId()+"/","productList","primary");
		addAction(userContext, platform, tokens,"platform.removeProduct","removeProduct","removeProduct/"+platform.getId()+"/","productList","primary");
		addAction(userContext, platform, tokens,"platform.updateProduct","updateProduct","updateProduct/"+platform.getId()+"/","productList","primary");
		addAction(userContext, platform, tokens,"platform.copyProductFrom","copyProductFrom","copyProductFrom/"+platform.getId()+"/","productList","primary");
		addAction(userContext, platform, tokens,"platform.addSkuInventory","addSkuInventory","addSkuInventory/"+platform.getId()+"/","skuInventoryList","primary");
		addAction(userContext, platform, tokens,"platform.removeSkuInventory","removeSkuInventory","removeSkuInventory/"+platform.getId()+"/","skuInventoryList","primary");
		addAction(userContext, platform, tokens,"platform.updateSkuInventory","updateSkuInventory","updateSkuInventory/"+platform.getId()+"/","skuInventoryList","primary");
		addAction(userContext, platform, tokens,"platform.copySkuInventoryFrom","copySkuInventoryFrom","copySkuInventoryFrom/"+platform.getId()+"/","skuInventoryList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(InventoryUserContext userContext, Platform platform, Map<String, Object> tokens){
	
 	
 	
 
 	
 	


	public Platform createPlatform(InventoryUserContext userContext,String name, String introduction, String currentVersion) throws Exception
	{
		
		

		

		userContext.getChecker().checkNameOfPlatform(name);
		userContext.getChecker().checkIntroductionOfPlatform(introduction);
		userContext.getChecker().checkCurrentVersionOfPlatform(currentVersion);
	
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);


		Platform platform=createNewPlatform();	

		platform.setName(name);
		platform.setIntroduction(introduction);
		platform.setCurrentVersion(currentVersion);

		platform = savePlatform(userContext, platform, emptyOptions());
		
		onNewInstanceCreated(userContext, platform);
		return platform;

		
	}
	protected Platform createNewPlatform() 
	{
		
		return new Platform();		
	}
	
	protected void checkParamsForUpdatingPlatform(InventoryUserContext userContext,String platformId, int platformVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		userContext.getChecker().checkIdOfPlatform(platformId);
		userContext.getChecker().checkVersionOfPlatform( platformVersion);
		

		if(Platform.NAME_PROPERTY.equals(property)){
			userContext.getChecker().checkNameOfPlatform(parseString(newValueExpr));
		}
		if(Platform.INTRODUCTION_PROPERTY.equals(property)){
			userContext.getChecker().checkIntroductionOfPlatform(parseString(newValueExpr));
		}
		if(Platform.CURRENT_VERSION_PROPERTY.equals(property)){
			userContext.getChecker().checkCurrentVersionOfPlatform(parseString(newValueExpr));
		}
	
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
	
		
	}
	
	
	
	public Platform clone(InventoryUserContext userContext, String fromPlatformId) throws Exception{
		
		return userContext.getDAOGroup().getPlatformDAO().clone(fromPlatformId, this.allTokens());
	}
	
	public Platform internalSavePlatform(InventoryUserContext userContext, Platform platform) throws Exception 
	{
		return internalSavePlatform(userContext, platform, allTokens());

	}
	public Platform internalSavePlatform(InventoryUserContext userContext, Platform platform, Map<String,Object> options) throws Exception 
	{
		//checkParamsForUpdatingPlatform(userContext, platformId, platformVersion, property, newValueExpr, tokensExpr);
		
		
		synchronized(platform){ 
			//will be good when the platform loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Platform.
			
			
			platform = savePlatform(userContext, platform, options);
			return platform;
			
		}

	}
	
	public Platform updatePlatform(InventoryUserContext userContext,String platformId, int platformVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingPlatform(userContext, platformId, platformVersion, property, newValueExpr, tokensExpr);
		
		
		
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		if(platform.getVersion() != platformVersion){
			String message = "The target version("+platform.getVersion()+") is not equals to version("+platformVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(platform){ 
			//will be good when the platform loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Platform.
			
			platform.changeProperty(property, newValueExpr);
			platform = savePlatform(userContext, platform, tokens().done());
			return present(userContext,platform, mergedAllTokens(tokensExpr));
			//return savePlatform(userContext, platform, tokens().done());
		}

	}
	
	public Platform updatePlatformProperty(InventoryUserContext userContext,String platformId, int platformVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingPlatform(userContext, platformId, platformVersion, property, newValueExpr, tokensExpr);
		
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		if(platform.getVersion() != platformVersion){
			String message = "The target version("+platform.getVersion()+") is not equals to version("+platformVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(platform){ 
			//will be good when the platform loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Platform.
			
			platform.changeProperty(property, newValueExpr);
			
			platform = savePlatform(userContext, platform, tokens().done());
			return present(userContext,platform, mergedAllTokens(tokensExpr));
			//return savePlatform(userContext, platform, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}
	
	protected PlatformTokens tokens(){
		return PlatformTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return PlatformTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortProductListWith("id","desc")
		.sortSkuInventoryListWith("id","desc")
		.done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return PlatformTokens.mergeAll(tokens).done();
	}
	
//--------------------------------------------------------------
	
	//--------------------------------------------------------------

	public void delete(InventoryUserContext userContext, String platformId, int platformVersion) throws Exception {
		//deleteInternal(userContext, platformId, platformVersion);		
	}
	protected void deleteInternal(InventoryUserContext userContext,
			String platformId, int platformVersion) throws Exception{
			
		userContext.getDAOGroup().getPlatformDAO().delete(platformId, platformVersion);
	}
	
	public Platform forgetByAll(InventoryUserContext userContext, String platformId, int platformVersion) throws Exception {
		return forgetByAllInternal(userContext, platformId, platformVersion);		
	}
	protected Platform forgetByAllInternal(InventoryUserContext userContext,
			String platformId, int platformVersion) throws Exception{
			
		return userContext.getDAOGroup().getPlatformDAO().disconnectFromAll(platformId, platformVersion);
	}
	

	
	public int deleteAll(InventoryUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new PlatformManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}
	
	
	protected int deleteAllInternal(InventoryUserContext userContext) throws Exception{
		return userContext.getDAOGroup().getPlatformDAO().deleteAll();
	}


	//disconnect Platform with product in SkuInventory
	protected Platform breakWithSkuInventoryByProduct(InventoryUserContext userContext, String platformId, String productId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			Platform platform = loadPlatform(userContext, platformId, allTokens());

			synchronized(platform){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				userContext.getDAOGroup().getPlatformDAO().planToRemoveSkuInventoryListWithProduct(platform, productId, this.emptyOptions());

				platform = savePlatform(userContext, platform, tokens().withSkuInventoryList().done());
				return platform;
			}
	}
	
	
	
	
	

	protected void checkParamsForAddingProduct(InventoryUserContext userContext, String platformId, String name, String introduction,String [] tokensExpr) throws Exception{
		
		

		
		
		userContext.getChecker().checkIdOfPlatform(platformId);

		
		userContext.getChecker().checkNameOfProduct(name);
		
		userContext.getChecker().checkIntroductionOfProduct(introduction);
	
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);

	
	}
	public  Platform addProduct(InventoryUserContext userContext, String platformId, String name, String introduction, String [] tokensExpr) throws Exception
	{	
		
		checkParamsForAddingProduct(userContext,platformId,name, introduction,tokensExpr);
		
		Product product = createProduct(userContext,name, introduction);
		
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){ 
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.addProduct( product );		
			platform = savePlatform(userContext, platform, tokens().withProductList().done());
			
			userContext.getManagerGroup().getProductManager().onNewInstanceCreated(userContext, product);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingProductProperties(InventoryUserContext userContext, String platformId,String id,String name,String introduction,String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfPlatform(platformId);
		userContext.getChecker().checkIdOfProduct(id);
		
		userContext.getChecker().checkNameOfProduct( name);
		userContext.getChecker().checkIntroductionOfProduct( introduction);

		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
		
	}
	public  Platform updateProductProperties(InventoryUserContext userContext, String platformId, String id,String name,String introduction, String [] tokensExpr) throws Exception
	{	
		checkParamsForUpdatingProductProperties(userContext,platformId,id,name,introduction,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withProductListList()
				.searchProductListWith(Product.ID_PROPERTY, "is", id).done();
		
		Platform platformToUpdate = loadPlatform(userContext, platformId, options);
		
		if(platformToUpdate.getProductList().isEmpty()){
			throw new PlatformManagerException("Product is NOT FOUND with id: '"+id+"'");
		}
		
		Product item = platformToUpdate.getProductList().first();
		
		item.updateName( name );
		item.updateIntroduction( introduction );

		
		//checkParamsForAddingProduct(userContext,platformId,name, code, used,tokensExpr);
		Platform platform = savePlatform(userContext, platformToUpdate, tokens().withProductList().done());
		synchronized(platform){ 
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}
	
	
	protected Product createProduct(InventoryUserContext userContext, String name, String introduction) throws Exception{

		Product product = new Product();
		
		
		product.setName(name);		
		product.setIntroduction(introduction);		
		product.setLastUpdateTime(userContext.now());
	
		
		return product;
	
		
	}
	
	protected Product createIndexedProduct(String id, int version){

		Product product = new Product();
		product.setId(id);
		product.setVersion(version);
		return product;			
		
	}
	
	protected void checkParamsForRemovingProductList(InventoryUserContext userContext, String platformId, 
			String productIds[],String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfPlatform(platformId);
		for(String productId: productIds){
			userContext.getChecker().checkIdOfProduct(productId);
		}
		
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
		
	}
	public  Platform removeProductList(InventoryUserContext userContext, String platformId, 
			String productIds[],String [] tokensExpr) throws Exception{
			
			checkParamsForRemovingProductList(userContext, platformId,  productIds, tokensExpr);
			
			
			Platform platform = loadPlatform(userContext, platformId, allTokens());
			synchronized(platform){ 
				//Will be good when the platform loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				userContext.getDAOGroup().getPlatformDAO().planToRemoveProductList(platform, productIds, allTokens());
				platform = savePlatform(userContext, platform, tokens().withProductList().done());
				deleteRelationListInGraph(userContext, platform.getProductList());
				return present(userContext,platform, mergedAllTokens(tokensExpr));
			}
	}
	
	protected void checkParamsForRemovingProduct(InventoryUserContext userContext, String platformId, 
		String productId, int productVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfPlatform( platformId);
		userContext.getChecker().checkIdOfProduct(productId);
		userContext.getChecker().checkVersionOfProduct(productVersion);
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
	
	}
	public  Platform removeProduct(InventoryUserContext userContext, String platformId, 
		String productId, int productVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForRemovingProduct(userContext,platformId, productId, productVersion,tokensExpr);
		
		Product product = createIndexedProduct(productId, productVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){ 
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.removeProduct( product );		
			platform = savePlatform(userContext, platform, tokens().withProductList().done());
			deleteRelationInGraph(userContext, product);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
		
		
	}
	protected void checkParamsForCopyingProduct(InventoryUserContext userContext, String platformId, 
		String productId, int productVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfPlatform( platformId);
		userContext.getChecker().checkIdOfProduct(productId);
		userContext.getChecker().checkVersionOfProduct(productVersion);
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
	
	}
	public  Platform copyProductFrom(InventoryUserContext userContext, String platformId, 
		String productId, int productVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForCopyingProduct(userContext,platformId, productId, productVersion,tokensExpr);
		
		Product product = createIndexedProduct(productId, productVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){ 
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			
			product.updateLastUpdateTime(userContext.now());
			
			platform.copyProductFrom( product );		
			platform = savePlatform(userContext, platform, tokens().withProductList().done());
			
			userContext.getManagerGroup().getProductManager().onNewInstanceCreated(userContext, (Product)platform.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
		
	}
	
	protected void checkParamsForUpdatingProduct(InventoryUserContext userContext, String platformId, String productId, int productVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		userContext.getChecker().checkIdOfPlatform(platformId);
		userContext.getChecker().checkIdOfProduct(productId);
		userContext.getChecker().checkVersionOfProduct(productVersion);
		

		if(Product.NAME_PROPERTY.equals(property)){
			userContext.getChecker().checkNameOfProduct(parseString(newValueExpr));
		}
		
		if(Product.INTRODUCTION_PROPERTY.equals(property)){
			userContext.getChecker().checkIntroductionOfProduct(parseString(newValueExpr));
		}
		
	
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
	
	}
	
	public  Platform updateProduct(InventoryUserContext userContext, String platformId, String productId, int productVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{
		
		checkParamsForUpdatingProduct(userContext, platformId, productId, productVersion, property, newValueExpr,  tokensExpr);
		
		Map<String,Object> loadTokens = this.tokens().withProductList().searchProductListWith(Product.ID_PROPERTY, "eq", productId).done();
		
		
		
		Platform platform = loadPlatform(userContext, platformId, loadTokens);
		
		synchronized(platform){ 
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//platform.removeProduct( product );	
			//make changes to AcceleraterAccount.
			Product productIndex = createIndexedProduct(productId, productVersion);
		
			Product product = platform.findTheProduct(productIndex);
			if(product == null){
				throw new PlatformManagerException(product+" is NOT FOUND" );
			}
			
			product.changeProperty(property, newValueExpr);
			product.updateLastUpdateTime(userContext.now());
			platform = savePlatform(userContext, platform, tokens().withProductList().done());
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/
	



	protected void checkParamsForAddingSkuInventory(InventoryUserContext userContext, String platformId, int stockLevel, int backorderLevel, int preorderLevel, int stockThreshold, int backorderThreshol, int preorderThreshol, String status, String productId,String [] tokensExpr) throws Exception{
		
		

		
		
		userContext.getChecker().checkIdOfPlatform(platformId);

		
		userContext.getChecker().checkStockLevelOfSkuInventory(stockLevel);
		
		userContext.getChecker().checkBackorderLevelOfSkuInventory(backorderLevel);
		
		userContext.getChecker().checkPreorderLevelOfSkuInventory(preorderLevel);
		
		userContext.getChecker().checkStockThresholdOfSkuInventory(stockThreshold);
		
		userContext.getChecker().checkBackorderThresholOfSkuInventory(backorderThreshol);
		
		userContext.getChecker().checkPreorderThresholOfSkuInventory(preorderThreshol);
		
		userContext.getChecker().checkStatusOfSkuInventory(status);
		
		userContext.getChecker().checkProductIdOfSkuInventory(productId);
	
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);

	
	}
	public  Platform addSkuInventory(InventoryUserContext userContext, String platformId, int stockLevel, int backorderLevel, int preorderLevel, int stockThreshold, int backorderThreshol, int preorderThreshol, String status, String productId, String [] tokensExpr) throws Exception
	{	
		
		checkParamsForAddingSkuInventory(userContext,platformId,stockLevel, backorderLevel, preorderLevel, stockThreshold, backorderThreshol, preorderThreshol, status, productId,tokensExpr);
		
		SkuInventory skuInventory = createSkuInventory(userContext,stockLevel, backorderLevel, preorderLevel, stockThreshold, backorderThreshol, preorderThreshol, status, productId);
		
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){ 
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.addSkuInventory( skuInventory );		
			platform = savePlatform(userContext, platform, tokens().withSkuInventoryList().done());
			
			userContext.getManagerGroup().getSkuInventoryManager().onNewInstanceCreated(userContext, skuInventory);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingSkuInventoryProperties(InventoryUserContext userContext, String platformId,String id,int stockLevel,int backorderLevel,int preorderLevel,int stockThreshold,int backorderThreshol,int preorderThreshol,String status,String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfPlatform(platformId);
		userContext.getChecker().checkIdOfSkuInventory(id);
		
		userContext.getChecker().checkStockLevelOfSkuInventory( stockLevel);
		userContext.getChecker().checkBackorderLevelOfSkuInventory( backorderLevel);
		userContext.getChecker().checkPreorderLevelOfSkuInventory( preorderLevel);
		userContext.getChecker().checkStockThresholdOfSkuInventory( stockThreshold);
		userContext.getChecker().checkBackorderThresholOfSkuInventory( backorderThreshol);
		userContext.getChecker().checkPreorderThresholOfSkuInventory( preorderThreshol);
		userContext.getChecker().checkStatusOfSkuInventory( status);

		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
		
	}
	public  Platform updateSkuInventoryProperties(InventoryUserContext userContext, String platformId, String id,int stockLevel,int backorderLevel,int preorderLevel,int stockThreshold,int backorderThreshol,int preorderThreshol,String status, String [] tokensExpr) throws Exception
	{	
		checkParamsForUpdatingSkuInventoryProperties(userContext,platformId,id,stockLevel,backorderLevel,preorderLevel,stockThreshold,backorderThreshol,preorderThreshol,status,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withSkuInventoryListList()
				.searchSkuInventoryListWith(SkuInventory.ID_PROPERTY, "is", id).done();
		
		Platform platformToUpdate = loadPlatform(userContext, platformId, options);
		
		if(platformToUpdate.getSkuInventoryList().isEmpty()){
			throw new PlatformManagerException("SkuInventory is NOT FOUND with id: '"+id+"'");
		}
		
		SkuInventory item = platformToUpdate.getSkuInventoryList().first();
		
		item.updateStockLevel( stockLevel );
		item.updateBackorderLevel( backorderLevel );
		item.updatePreorderLevel( preorderLevel );
		item.updateStockThreshold( stockThreshold );
		item.updateBackorderThreshol( backorderThreshol );
		item.updatePreorderThreshol( preorderThreshol );
		item.updateStatus( status );

		
		//checkParamsForAddingSkuInventory(userContext,platformId,name, code, used,tokensExpr);
		Platform platform = savePlatform(userContext, platformToUpdate, tokens().withSkuInventoryList().done());
		synchronized(platform){ 
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}
	
	
	protected SkuInventory createSkuInventory(InventoryUserContext userContext, int stockLevel, int backorderLevel, int preorderLevel, int stockThreshold, int backorderThreshol, int preorderThreshol, String status, String productId) throws Exception{

		SkuInventory skuInventory = new SkuInventory();
		
		
		skuInventory.setStockLevel(stockLevel);		
		skuInventory.setBackorderLevel(backorderLevel);		
		skuInventory.setPreorderLevel(preorderLevel);		
		skuInventory.setStockThreshold(stockThreshold);		
		skuInventory.setBackorderThreshol(backorderThreshol);		
		skuInventory.setPreorderThreshol(preorderThreshol);		
		skuInventory.setStatus(status);		
		Product  product = new Product();
		product.setId(productId);		
		skuInventory.setProduct(product);
	
		
		return skuInventory;
	
		
	}
	
	protected SkuInventory createIndexedSkuInventory(String id, int version){

		SkuInventory skuInventory = new SkuInventory();
		skuInventory.setId(id);
		skuInventory.setVersion(version);
		return skuInventory;			
		
	}
	
	protected void checkParamsForRemovingSkuInventoryList(InventoryUserContext userContext, String platformId, 
			String skuInventoryIds[],String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfPlatform(platformId);
		for(String skuInventoryId: skuInventoryIds){
			userContext.getChecker().checkIdOfSkuInventory(skuInventoryId);
		}
		
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
		
	}
	public  Platform removeSkuInventoryList(InventoryUserContext userContext, String platformId, 
			String skuInventoryIds[],String [] tokensExpr) throws Exception{
			
			checkParamsForRemovingSkuInventoryList(userContext, platformId,  skuInventoryIds, tokensExpr);
			
			
			Platform platform = loadPlatform(userContext, platformId, allTokens());
			synchronized(platform){ 
				//Will be good when the platform loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				userContext.getDAOGroup().getPlatformDAO().planToRemoveSkuInventoryList(platform, skuInventoryIds, allTokens());
				platform = savePlatform(userContext, platform, tokens().withSkuInventoryList().done());
				deleteRelationListInGraph(userContext, platform.getSkuInventoryList());
				return present(userContext,platform, mergedAllTokens(tokensExpr));
			}
	}
	
	protected void checkParamsForRemovingSkuInventory(InventoryUserContext userContext, String platformId, 
		String skuInventoryId, int skuInventoryVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfPlatform( platformId);
		userContext.getChecker().checkIdOfSkuInventory(skuInventoryId);
		userContext.getChecker().checkVersionOfSkuInventory(skuInventoryVersion);
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
	
	}
	public  Platform removeSkuInventory(InventoryUserContext userContext, String platformId, 
		String skuInventoryId, int skuInventoryVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForRemovingSkuInventory(userContext,platformId, skuInventoryId, skuInventoryVersion,tokensExpr);
		
		SkuInventory skuInventory = createIndexedSkuInventory(skuInventoryId, skuInventoryVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){ 
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.removeSkuInventory( skuInventory );		
			platform = savePlatform(userContext, platform, tokens().withSkuInventoryList().done());
			deleteRelationInGraph(userContext, skuInventory);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
		
		
	}
	protected void checkParamsForCopyingSkuInventory(InventoryUserContext userContext, String platformId, 
		String skuInventoryId, int skuInventoryVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfPlatform( platformId);
		userContext.getChecker().checkIdOfSkuInventory(skuInventoryId);
		userContext.getChecker().checkVersionOfSkuInventory(skuInventoryVersion);
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
	
	}
	public  Platform copySkuInventoryFrom(InventoryUserContext userContext, String platformId, 
		String skuInventoryId, int skuInventoryVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForCopyingSkuInventory(userContext,platformId, skuInventoryId, skuInventoryVersion,tokensExpr);
		
		SkuInventory skuInventory = createIndexedSkuInventory(skuInventoryId, skuInventoryVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){ 
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			
			
			
			platform.copySkuInventoryFrom( skuInventory );		
			platform = savePlatform(userContext, platform, tokens().withSkuInventoryList().done());
			
			userContext.getManagerGroup().getSkuInventoryManager().onNewInstanceCreated(userContext, (SkuInventory)platform.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
		
	}
	
	protected void checkParamsForUpdatingSkuInventory(InventoryUserContext userContext, String platformId, String skuInventoryId, int skuInventoryVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		userContext.getChecker().checkIdOfPlatform(platformId);
		userContext.getChecker().checkIdOfSkuInventory(skuInventoryId);
		userContext.getChecker().checkVersionOfSkuInventory(skuInventoryVersion);
		

		if(SkuInventory.STOCK_LEVEL_PROPERTY.equals(property)){
			userContext.getChecker().checkStockLevelOfSkuInventory(parseInt(newValueExpr));
		}
		
		if(SkuInventory.BACKORDER_LEVEL_PROPERTY.equals(property)){
			userContext.getChecker().checkBackorderLevelOfSkuInventory(parseInt(newValueExpr));
		}
		
		if(SkuInventory.PREORDER_LEVEL_PROPERTY.equals(property)){
			userContext.getChecker().checkPreorderLevelOfSkuInventory(parseInt(newValueExpr));
		}
		
		if(SkuInventory.STOCK_THRESHOLD_PROPERTY.equals(property)){
			userContext.getChecker().checkStockThresholdOfSkuInventory(parseInt(newValueExpr));
		}
		
		if(SkuInventory.BACKORDER_THRESHOL_PROPERTY.equals(property)){
			userContext.getChecker().checkBackorderThresholOfSkuInventory(parseInt(newValueExpr));
		}
		
		if(SkuInventory.PREORDER_THRESHOL_PROPERTY.equals(property)){
			userContext.getChecker().checkPreorderThresholOfSkuInventory(parseInt(newValueExpr));
		}
		
		if(SkuInventory.STATUS_PROPERTY.equals(property)){
			userContext.getChecker().checkStatusOfSkuInventory(parseString(newValueExpr));
		}
		
	
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
	
	}
	
	public  Platform updateSkuInventory(InventoryUserContext userContext, String platformId, String skuInventoryId, int skuInventoryVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{
		
		checkParamsForUpdatingSkuInventory(userContext, platformId, skuInventoryId, skuInventoryVersion, property, newValueExpr,  tokensExpr);
		
		Map<String,Object> loadTokens = this.tokens().withSkuInventoryList().searchSkuInventoryListWith(SkuInventory.ID_PROPERTY, "eq", skuInventoryId).done();
		
		
		
		Platform platform = loadPlatform(userContext, platformId, loadTokens);
		
		synchronized(platform){ 
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//platform.removeSkuInventory( skuInventory );	
			//make changes to AcceleraterAccount.
			SkuInventory skuInventoryIndex = createIndexedSkuInventory(skuInventoryId, skuInventoryVersion);
		
			SkuInventory skuInventory = platform.findTheSkuInventory(skuInventoryIndex);
			if(skuInventory == null){
				throw new PlatformManagerException(skuInventory+" is NOT FOUND" );
			}
			
			skuInventory.changeProperty(property, newValueExpr);
			
			platform = savePlatform(userContext, platform, tokens().withSkuInventoryList().done());
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/
	



	public void onNewInstanceCreated(InventoryUserContext userContext, Platform newCreated) throws Exception{
		ensureRelationInGraph(userContext, newCreated);
		sendCreationEvent(userContext, newCreated);
	}

}


