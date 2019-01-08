
package com.doublechaintech.inventory.product;

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

import com.doublechaintech.inventory.platform.Platform;
import com.doublechaintech.inventory.skuinventory.SkuInventory;

import com.doublechaintech.inventory.platform.CandidatePlatform;

import com.doublechaintech.inventory.product.Product;
import com.doublechaintech.inventory.platform.Platform;






public class ProductManagerImpl extends CustomInventoryCheckerManager implements ProductManager {
	
	private static final String SERVICE_TYPE = "Product";
	
	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}
	
	
	protected void throwExceptionWithMessage(String value) throws ProductManagerException{
	
		Message message = new Message();
		message.setBody(value);
		throw new ProductManagerException(message);

	}
	
	

 	protected Product saveProduct(InventoryUserContext userContext, Product product, String [] tokensExpr) throws Exception{	
 		//return getProductDAO().save(product, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveProduct(userContext, product, tokens);
 	}
 	
 	protected Product saveProductDetail(InventoryUserContext userContext, Product product) throws Exception{	

 		
 		return saveProduct(userContext, product, allTokens());
 	}
 	
 	public Product loadProduct(InventoryUserContext userContext, String productId, String [] tokensExpr) throws Exception{				
 
 		userContext.getChecker().checkIdOfProduct(productId);
		userContext.getChecker().throwExceptionIfHasErrors( ProductManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		Product product = loadProduct( userContext, productId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,product, tokens);
 	}
 	
 	
 	 public Product searchProduct(InventoryUserContext userContext, String productId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		userContext.getChecker().checkIdOfProduct(productId);
		userContext.getChecker().throwExceptionIfHasErrors( ProductManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		Product product = loadProduct( userContext, productId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,product, tokens);
 	}
 	
 	

 	protected Product present(InventoryUserContext userContext, Product product, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,product,tokens);
		
		
		Product  productToPresent = userContext.getDAOGroup().getProductDAO().present(product, tokens);
		
		List<BaseEntity> entityListToNaming = productToPresent.collectRefercencesFromLists();
		userContext.getDAOGroup().getProductDAO().alias(entityListToNaming);
		
		return  productToPresent;
		
		
	}
 
 	
 	
 	public Product loadProductDetail(InventoryUserContext userContext, String productId) throws Exception{	
 		Product product = loadProduct( userContext, productId, allTokens());
 		return present(userContext,product, allTokens());
		
 	}
 	
 	public Object view(InventoryUserContext userContext, String productId) throws Exception{	
 		Product product = loadProduct( userContext, productId, viewTokens());
 		return present(userContext,product, allTokens());
		
 	}
 	protected Product saveProduct(InventoryUserContext userContext, Product product, Map<String,Object>tokens) throws Exception{	
 		return userContext.getDAOGroup().getProductDAO().save(product, tokens);
 	}
 	protected Product loadProduct(InventoryUserContext userContext, String productId, Map<String,Object>tokens) throws Exception{	
		userContext.getChecker().checkIdOfProduct(productId);
		userContext.getChecker().throwExceptionIfHasErrors( ProductManagerException.class);

 
 		return userContext.getDAOGroup().getProductDAO().load(productId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(InventoryUserContext userContext, Product product, Map<String, Object> tokens){
		super.addActions(userContext, product, tokens);
		
		addAction(userContext, product, tokens,"@create","createProduct","createProduct/","main","primary");
		addAction(userContext, product, tokens,"@update","updateProduct","updateProduct/"+product.getId()+"/","main","primary");
		addAction(userContext, product, tokens,"@copy","cloneProduct","cloneProduct/"+product.getId()+"/","main","primary");
		
		addAction(userContext, product, tokens,"product.transfer_to_platform","transferToAnotherPlatform","transferToAnotherPlatform/"+product.getId()+"/","main","primary");
		addAction(userContext, product, tokens,"product.addSkuInventory","addSkuInventory","addSkuInventory/"+product.getId()+"/","skuInventoryList","primary");
		addAction(userContext, product, tokens,"product.removeSkuInventory","removeSkuInventory","removeSkuInventory/"+product.getId()+"/","skuInventoryList","primary");
		addAction(userContext, product, tokens,"product.updateSkuInventory","updateSkuInventory","updateSkuInventory/"+product.getId()+"/","skuInventoryList","primary");
		addAction(userContext, product, tokens,"product.copySkuInventoryFrom","copySkuInventoryFrom","copySkuInventoryFrom/"+product.getId()+"/","skuInventoryList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(InventoryUserContext userContext, Product product, Map<String, Object> tokens){
	
 	
 	
 
 	
 	


	public Product createProduct(InventoryUserContext userContext,String name, String introduction, String platformId) throws Exception
	{
		
		

		

		userContext.getChecker().checkNameOfProduct(name);
		userContext.getChecker().checkIntroductionOfProduct(introduction);
	
		userContext.getChecker().throwExceptionIfHasErrors(ProductManagerException.class);


		Product product=createNewProduct();	

		product.setName(name);
		product.setIntroduction(introduction);
			
		Platform platform = loadPlatform(userContext, platformId,emptyOptions());
		product.setPlatform(platform);
		
		
		product.setLastUpdateTime(userContext.now());

		product = saveProduct(userContext, product, emptyOptions());
		
		onNewInstanceCreated(userContext, product);
		return product;

		
	}
	protected Product createNewProduct() 
	{
		
		return new Product();		
	}
	
	protected void checkParamsForUpdatingProduct(InventoryUserContext userContext,String productId, int productVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		userContext.getChecker().checkIdOfProduct(productId);
		userContext.getChecker().checkVersionOfProduct( productVersion);
		

		if(Product.NAME_PROPERTY.equals(property)){
			userContext.getChecker().checkNameOfProduct(parseString(newValueExpr));
		}
		if(Product.INTRODUCTION_PROPERTY.equals(property)){
			userContext.getChecker().checkIntroductionOfProduct(parseString(newValueExpr));
		}		

		
	
		userContext.getChecker().throwExceptionIfHasErrors(ProductManagerException.class);
	
		
	}
	
	
	
	public Product clone(InventoryUserContext userContext, String fromProductId) throws Exception{
		
		return userContext.getDAOGroup().getProductDAO().clone(fromProductId, this.allTokens());
	}
	
	public Product internalSaveProduct(InventoryUserContext userContext, Product product) throws Exception 
	{
		return internalSaveProduct(userContext, product, allTokens());

	}
	public Product internalSaveProduct(InventoryUserContext userContext, Product product, Map<String,Object> options) throws Exception 
	{
		//checkParamsForUpdatingProduct(userContext, productId, productVersion, property, newValueExpr, tokensExpr);
		
		
		synchronized(product){ 
			//will be good when the product loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Product.
			
			
			product = saveProduct(userContext, product, options);
			return product;
			
		}

	}
	
	public Product updateProduct(InventoryUserContext userContext,String productId, int productVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingProduct(userContext, productId, productVersion, property, newValueExpr, tokensExpr);
		
		
		
		Product product = loadProduct(userContext, productId, allTokens());
		if(product.getVersion() != productVersion){
			String message = "The target version("+product.getVersion()+") is not equals to version("+productVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(product){ 
			//will be good when the product loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Product.
			product.updateLastUpdateTime(userContext.now());
			product.changeProperty(property, newValueExpr);
			product = saveProduct(userContext, product, tokens().done());
			return present(userContext,product, mergedAllTokens(tokensExpr));
			//return saveProduct(userContext, product, tokens().done());
		}

	}
	
	public Product updateProductProperty(InventoryUserContext userContext,String productId, int productVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingProduct(userContext, productId, productVersion, property, newValueExpr, tokensExpr);
		
		Product product = loadProduct(userContext, productId, allTokens());
		if(product.getVersion() != productVersion){
			String message = "The target version("+product.getVersion()+") is not equals to version("+productVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(product){ 
			//will be good when the product loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Product.
			
			product.changeProperty(property, newValueExpr);
			product.updateLastUpdateTime(userContext.now());
			product = saveProduct(userContext, product, tokens().done());
			return present(userContext,product, mergedAllTokens(tokensExpr));
			//return saveProduct(userContext, product, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}
	
	protected ProductTokens tokens(){
		return ProductTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return ProductTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortSkuInventoryListWith("id","desc")
		.done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return ProductTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherPlatform(InventoryUserContext userContext, String productId, String anotherPlatformId) throws Exception
 	{
 		
 		userContext.getChecker().checkIdOfProduct(productId);
 		userContext.getChecker().checkIdOfPlatform(anotherPlatformId);//check for optional reference
 		userContext.getChecker().throwExceptionIfHasErrors(ProductManagerException.class);
 		
 	}
 	public Product transferToAnotherPlatform(InventoryUserContext userContext, String productId, String anotherPlatformId) throws Exception
 	{
 		checkParamsForTransferingAnotherPlatform(userContext, productId,anotherPlatformId);
 
		Product product = loadProduct(userContext, productId, allTokens());	
		synchronized(product){
			//will be good when the product loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Platform platform = loadPlatform(userContext, anotherPlatformId, emptyOptions());		
			product.updatePlatform(platform);		
			product = saveProduct(userContext, product, emptyOptions());
			
			return present(userContext,product, allTokens());
			
		}

 	}
 	
	 	
 	
 	
	public CandidatePlatform requestCandidatePlatform(InventoryUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidatePlatform result = new CandidatePlatform();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");
		
		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<Platform> candidateList = userContext.getDAOGroup().getPlatformDAO().requestCandidatePlatformForProduct(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}
 	
 //--------------------------------------------------------------
	
	 	
 	protected Platform loadPlatform(InventoryUserContext userContext, String newPlatformId, Map<String,Object> options) throws Exception
 	{
		
 		return userContext.getDAOGroup().getPlatformDAO().load(newPlatformId, options);
 	}
 	
 	
 	
	
	//--------------------------------------------------------------

	public void delete(InventoryUserContext userContext, String productId, int productVersion) throws Exception {
		//deleteInternal(userContext, productId, productVersion);		
	}
	protected void deleteInternal(InventoryUserContext userContext,
			String productId, int productVersion) throws Exception{
			
		userContext.getDAOGroup().getProductDAO().delete(productId, productVersion);
	}
	
	public Product forgetByAll(InventoryUserContext userContext, String productId, int productVersion) throws Exception {
		return forgetByAllInternal(userContext, productId, productVersion);		
	}
	protected Product forgetByAllInternal(InventoryUserContext userContext,
			String productId, int productVersion) throws Exception{
			
		return userContext.getDAOGroup().getProductDAO().disconnectFromAll(productId, productVersion);
	}
	

	
	public int deleteAll(InventoryUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new ProductManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}
	
	
	protected int deleteAllInternal(InventoryUserContext userContext) throws Exception{
		return userContext.getDAOGroup().getProductDAO().deleteAll();
	}


	//disconnect Product with platform in SkuInventory
	protected Product breakWithSkuInventoryByPlatform(InventoryUserContext userContext, String productId, String platformId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			Product product = loadProduct(userContext, productId, allTokens());

			synchronized(product){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				userContext.getDAOGroup().getProductDAO().planToRemoveSkuInventoryListWithPlatform(product, platformId, this.emptyOptions());

				product = saveProduct(userContext, product, tokens().withSkuInventoryList().done());
				return product;
			}
	}
	
	
	
	
	

	protected void checkParamsForAddingSkuInventory(InventoryUserContext userContext, String productId, int stockLevel, int backorderLevel, int preorderLevel, int stockThreshold, int backorderThreshol, int preorderThreshol, String status, String platformId,String [] tokensExpr) throws Exception{
		
		

		
		
		userContext.getChecker().checkIdOfProduct(productId);

		
		userContext.getChecker().checkStockLevelOfSkuInventory(stockLevel);
		
		userContext.getChecker().checkBackorderLevelOfSkuInventory(backorderLevel);
		
		userContext.getChecker().checkPreorderLevelOfSkuInventory(preorderLevel);
		
		userContext.getChecker().checkStockThresholdOfSkuInventory(stockThreshold);
		
		userContext.getChecker().checkBackorderThresholOfSkuInventory(backorderThreshol);
		
		userContext.getChecker().checkPreorderThresholOfSkuInventory(preorderThreshol);
		
		userContext.getChecker().checkStatusOfSkuInventory(status);
		
		userContext.getChecker().checkPlatformIdOfSkuInventory(platformId);
	
		userContext.getChecker().throwExceptionIfHasErrors(ProductManagerException.class);

	
	}
	public  Product addSkuInventory(InventoryUserContext userContext, String productId, int stockLevel, int backorderLevel, int preorderLevel, int stockThreshold, int backorderThreshol, int preorderThreshol, String status, String platformId, String [] tokensExpr) throws Exception
	{	
		
		checkParamsForAddingSkuInventory(userContext,productId,stockLevel, backorderLevel, preorderLevel, stockThreshold, backorderThreshol, preorderThreshol, status, platformId,tokensExpr);
		
		SkuInventory skuInventory = createSkuInventory(userContext,stockLevel, backorderLevel, preorderLevel, stockThreshold, backorderThreshol, preorderThreshol, status, platformId);
		
		Product product = loadProduct(userContext, productId, allTokens());
		synchronized(product){ 
			//Will be good when the product loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			product.addSkuInventory( skuInventory );		
			product = saveProduct(userContext, product, tokens().withSkuInventoryList().done());
			
			userContext.getManagerGroup().getSkuInventoryManager().onNewInstanceCreated(userContext, skuInventory);
			return present(userContext,product, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingSkuInventoryProperties(InventoryUserContext userContext, String productId,String id,int stockLevel,int backorderLevel,int preorderLevel,int stockThreshold,int backorderThreshol,int preorderThreshol,String status,String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfProduct(productId);
		userContext.getChecker().checkIdOfSkuInventory(id);
		
		userContext.getChecker().checkStockLevelOfSkuInventory( stockLevel);
		userContext.getChecker().checkBackorderLevelOfSkuInventory( backorderLevel);
		userContext.getChecker().checkPreorderLevelOfSkuInventory( preorderLevel);
		userContext.getChecker().checkStockThresholdOfSkuInventory( stockThreshold);
		userContext.getChecker().checkBackorderThresholOfSkuInventory( backorderThreshol);
		userContext.getChecker().checkPreorderThresholOfSkuInventory( preorderThreshol);
		userContext.getChecker().checkStatusOfSkuInventory( status);

		userContext.getChecker().throwExceptionIfHasErrors(ProductManagerException.class);
		
	}
	public  Product updateSkuInventoryProperties(InventoryUserContext userContext, String productId, String id,int stockLevel,int backorderLevel,int preorderLevel,int stockThreshold,int backorderThreshol,int preorderThreshol,String status, String [] tokensExpr) throws Exception
	{	
		checkParamsForUpdatingSkuInventoryProperties(userContext,productId,id,stockLevel,backorderLevel,preorderLevel,stockThreshold,backorderThreshol,preorderThreshol,status,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withSkuInventoryListList()
				.searchSkuInventoryListWith(SkuInventory.ID_PROPERTY, "is", id).done();
		
		Product productToUpdate = loadProduct(userContext, productId, options);
		
		if(productToUpdate.getSkuInventoryList().isEmpty()){
			throw new ProductManagerException("SkuInventory is NOT FOUND with id: '"+id+"'");
		}
		
		SkuInventory item = productToUpdate.getSkuInventoryList().first();
		
		item.updateStockLevel( stockLevel );
		item.updateBackorderLevel( backorderLevel );
		item.updatePreorderLevel( preorderLevel );
		item.updateStockThreshold( stockThreshold );
		item.updateBackorderThreshol( backorderThreshol );
		item.updatePreorderThreshol( preorderThreshol );
		item.updateStatus( status );

		
		//checkParamsForAddingSkuInventory(userContext,productId,name, code, used,tokensExpr);
		Product product = saveProduct(userContext, productToUpdate, tokens().withSkuInventoryList().done());
		synchronized(product){ 
			return present(userContext,product, mergedAllTokens(tokensExpr));
		}
	}
	
	
	protected SkuInventory createSkuInventory(InventoryUserContext userContext, int stockLevel, int backorderLevel, int preorderLevel, int stockThreshold, int backorderThreshol, int preorderThreshol, String status, String platformId) throws Exception{

		SkuInventory skuInventory = new SkuInventory();
		
		
		skuInventory.setStockLevel(stockLevel);		
		skuInventory.setBackorderLevel(backorderLevel);		
		skuInventory.setPreorderLevel(preorderLevel);		
		skuInventory.setStockThreshold(stockThreshold);		
		skuInventory.setBackorderThreshol(backorderThreshol);		
		skuInventory.setPreorderThreshol(preorderThreshol);		
		skuInventory.setStatus(status);		
		Platform  platform = new Platform();
		platform.setId(platformId);		
		skuInventory.setPlatform(platform);
	
		
		return skuInventory;
	
		
	}
	
	protected SkuInventory createIndexedSkuInventory(String id, int version){

		SkuInventory skuInventory = new SkuInventory();
		skuInventory.setId(id);
		skuInventory.setVersion(version);
		return skuInventory;			
		
	}
	
	protected void checkParamsForRemovingSkuInventoryList(InventoryUserContext userContext, String productId, 
			String skuInventoryIds[],String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfProduct(productId);
		for(String skuInventoryId: skuInventoryIds){
			userContext.getChecker().checkIdOfSkuInventory(skuInventoryId);
		}
		
		userContext.getChecker().throwExceptionIfHasErrors(ProductManagerException.class);
		
	}
	public  Product removeSkuInventoryList(InventoryUserContext userContext, String productId, 
			String skuInventoryIds[],String [] tokensExpr) throws Exception{
			
			checkParamsForRemovingSkuInventoryList(userContext, productId,  skuInventoryIds, tokensExpr);
			
			
			Product product = loadProduct(userContext, productId, allTokens());
			synchronized(product){ 
				//Will be good when the product loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				userContext.getDAOGroup().getProductDAO().planToRemoveSkuInventoryList(product, skuInventoryIds, allTokens());
				product = saveProduct(userContext, product, tokens().withSkuInventoryList().done());
				deleteRelationListInGraph(userContext, product.getSkuInventoryList());
				return present(userContext,product, mergedAllTokens(tokensExpr));
			}
	}
	
	protected void checkParamsForRemovingSkuInventory(InventoryUserContext userContext, String productId, 
		String skuInventoryId, int skuInventoryVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfProduct( productId);
		userContext.getChecker().checkIdOfSkuInventory(skuInventoryId);
		userContext.getChecker().checkVersionOfSkuInventory(skuInventoryVersion);
		userContext.getChecker().throwExceptionIfHasErrors(ProductManagerException.class);
	
	}
	public  Product removeSkuInventory(InventoryUserContext userContext, String productId, 
		String skuInventoryId, int skuInventoryVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForRemovingSkuInventory(userContext,productId, skuInventoryId, skuInventoryVersion,tokensExpr);
		
		SkuInventory skuInventory = createIndexedSkuInventory(skuInventoryId, skuInventoryVersion);
		Product product = loadProduct(userContext, productId, allTokens());
		synchronized(product){ 
			//Will be good when the product loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			product.removeSkuInventory( skuInventory );		
			product = saveProduct(userContext, product, tokens().withSkuInventoryList().done());
			deleteRelationInGraph(userContext, skuInventory);
			return present(userContext,product, mergedAllTokens(tokensExpr));
		}
		
		
	}
	protected void checkParamsForCopyingSkuInventory(InventoryUserContext userContext, String productId, 
		String skuInventoryId, int skuInventoryVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfProduct( productId);
		userContext.getChecker().checkIdOfSkuInventory(skuInventoryId);
		userContext.getChecker().checkVersionOfSkuInventory(skuInventoryVersion);
		userContext.getChecker().throwExceptionIfHasErrors(ProductManagerException.class);
	
	}
	public  Product copySkuInventoryFrom(InventoryUserContext userContext, String productId, 
		String skuInventoryId, int skuInventoryVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForCopyingSkuInventory(userContext,productId, skuInventoryId, skuInventoryVersion,tokensExpr);
		
		SkuInventory skuInventory = createIndexedSkuInventory(skuInventoryId, skuInventoryVersion);
		Product product = loadProduct(userContext, productId, allTokens());
		synchronized(product){ 
			//Will be good when the product loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			
			
			
			product.copySkuInventoryFrom( skuInventory );		
			product = saveProduct(userContext, product, tokens().withSkuInventoryList().done());
			
			userContext.getManagerGroup().getSkuInventoryManager().onNewInstanceCreated(userContext, (SkuInventory)product.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,product, mergedAllTokens(tokensExpr));
		}
		
	}
	
	protected void checkParamsForUpdatingSkuInventory(InventoryUserContext userContext, String productId, String skuInventoryId, int skuInventoryVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		userContext.getChecker().checkIdOfProduct(productId);
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
		
	
		userContext.getChecker().throwExceptionIfHasErrors(ProductManagerException.class);
	
	}
	
	public  Product updateSkuInventory(InventoryUserContext userContext, String productId, String skuInventoryId, int skuInventoryVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{
		
		checkParamsForUpdatingSkuInventory(userContext, productId, skuInventoryId, skuInventoryVersion, property, newValueExpr,  tokensExpr);
		
		Map<String,Object> loadTokens = this.tokens().withSkuInventoryList().searchSkuInventoryListWith(SkuInventory.ID_PROPERTY, "eq", skuInventoryId).done();
		
		
		
		Product product = loadProduct(userContext, productId, loadTokens);
		
		synchronized(product){ 
			//Will be good when the product loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//product.removeSkuInventory( skuInventory );	
			//make changes to AcceleraterAccount.
			SkuInventory skuInventoryIndex = createIndexedSkuInventory(skuInventoryId, skuInventoryVersion);
		
			SkuInventory skuInventory = product.findTheSkuInventory(skuInventoryIndex);
			if(skuInventory == null){
				throw new ProductManagerException(skuInventory+" is NOT FOUND" );
			}
			
			skuInventory.changeProperty(property, newValueExpr);
			
			product = saveProduct(userContext, product, tokens().withSkuInventoryList().done());
			return present(userContext,product, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/
	



	public void onNewInstanceCreated(InventoryUserContext userContext, Product newCreated) throws Exception{
		ensureRelationInGraph(userContext, newCreated);
		sendCreationEvent(userContext, newCreated);
	}

}


