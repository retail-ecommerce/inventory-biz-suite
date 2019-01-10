
package com.doublechaintech.inventory.skuinventory;

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
import com.doublechaintech.inventory.platform.Platform;

import com.doublechaintech.inventory.product.CandidateProduct;
import com.doublechaintech.inventory.platform.CandidatePlatform;







public class SkuInventoryManagerImpl extends CustomInventoryCheckerManager implements SkuInventoryManager {
	
	private static final String SERVICE_TYPE = "SkuInventory";
	
	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}
	
	
	protected void throwExceptionWithMessage(String value) throws SkuInventoryManagerException{
	
		Message message = new Message();
		message.setBody(value);
		throw new SkuInventoryManagerException(message);

	}
	
	

 	protected SkuInventory saveSkuInventory(InventoryUserContext userContext, SkuInventory skuInventory, String [] tokensExpr) throws Exception{	
 		//return getSkuInventoryDAO().save(skuInventory, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveSkuInventory(userContext, skuInventory, tokens);
 	}
 	
 	protected SkuInventory saveSkuInventoryDetail(InventoryUserContext userContext, SkuInventory skuInventory) throws Exception{	

 		
 		return saveSkuInventory(userContext, skuInventory, allTokens());
 	}
 	
 	public SkuInventory loadSkuInventory(InventoryUserContext userContext, String skuInventoryId, String [] tokensExpr) throws Exception{				
 
 		userContext.getChecker().checkIdOfSkuInventory(skuInventoryId);
		userContext.getChecker().throwExceptionIfHasErrors( SkuInventoryManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		SkuInventory skuInventory = loadSkuInventory( userContext, skuInventoryId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,skuInventory, tokens);
 	}
 	
 	
 	 public SkuInventory searchSkuInventory(InventoryUserContext userContext, String skuInventoryId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		userContext.getChecker().checkIdOfSkuInventory(skuInventoryId);
		userContext.getChecker().throwExceptionIfHasErrors( SkuInventoryManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		SkuInventory skuInventory = loadSkuInventory( userContext, skuInventoryId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,skuInventory, tokens);
 	}
 	
 	

 	protected SkuInventory present(InventoryUserContext userContext, SkuInventory skuInventory, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,skuInventory,tokens);
		
		
		SkuInventory  skuInventoryToPresent = userContext.getDAOGroup().getSkuInventoryDAO().present(skuInventory, tokens);
		
		List<BaseEntity> entityListToNaming = skuInventoryToPresent.collectRefercencesFromLists();
		userContext.getDAOGroup().getSkuInventoryDAO().alias(entityListToNaming);
		
		return  skuInventoryToPresent;
		
		
	}
 
 	
 	
 	public SkuInventory loadSkuInventoryDetail(InventoryUserContext userContext, String skuInventoryId) throws Exception{	
 		SkuInventory skuInventory = loadSkuInventory( userContext, skuInventoryId, allTokens());
 		return present(userContext,skuInventory, allTokens());
		
 	}
 	
 	public Object view(InventoryUserContext userContext, String skuInventoryId) throws Exception{	
 		SkuInventory skuInventory = loadSkuInventory( userContext, skuInventoryId, viewTokens());
 		return present(userContext,skuInventory, allTokens());
		
 	}
 	protected SkuInventory saveSkuInventory(InventoryUserContext userContext, SkuInventory skuInventory, Map<String,Object>tokens) throws Exception{	
 		return userContext.getDAOGroup().getSkuInventoryDAO().save(skuInventory, tokens);
 	}
 	protected SkuInventory loadSkuInventory(InventoryUserContext userContext, String skuInventoryId, Map<String,Object>tokens) throws Exception{	
		userContext.getChecker().checkIdOfSkuInventory(skuInventoryId);
		userContext.getChecker().throwExceptionIfHasErrors( SkuInventoryManagerException.class);

 
 		return userContext.getDAOGroup().getSkuInventoryDAO().load(skuInventoryId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(InventoryUserContext userContext, SkuInventory skuInventory, Map<String, Object> tokens){
		super.addActions(userContext, skuInventory, tokens);
		
		addAction(userContext, skuInventory, tokens,"@create","createSkuInventory","createSkuInventory/","main","primary");
		addAction(userContext, skuInventory, tokens,"@update","updateSkuInventory","updateSkuInventory/"+skuInventory.getId()+"/","main","primary");
		addAction(userContext, skuInventory, tokens,"@copy","cloneSkuInventory","cloneSkuInventory/"+skuInventory.getId()+"/","main","primary");
		
		addAction(userContext, skuInventory, tokens,"sku_inventory.transfer_to_product","transferToAnotherProduct","transferToAnotherProduct/"+skuInventory.getId()+"/","main","primary");
		addAction(userContext, skuInventory, tokens,"sku_inventory.transfer_to_platform","transferToAnotherPlatform","transferToAnotherPlatform/"+skuInventory.getId()+"/","main","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(InventoryUserContext userContext, SkuInventory skuInventory, Map<String, Object> tokens){
	
 	
 	
 
 	
 	


	public SkuInventory createSkuInventory(InventoryUserContext userContext,String name, int stockLevel, int backorderLevel, int preorderLevel, int stockThreshold, int backorderThreshol, int preorderThreshol, String status, String productId, String platformId) throws Exception
	{
		
		

		

		userContext.getChecker().checkNameOfSkuInventory(name);
		userContext.getChecker().checkStockLevelOfSkuInventory(stockLevel);
		userContext.getChecker().checkBackorderLevelOfSkuInventory(backorderLevel);
		userContext.getChecker().checkPreorderLevelOfSkuInventory(preorderLevel);
		userContext.getChecker().checkStockThresholdOfSkuInventory(stockThreshold);
		userContext.getChecker().checkBackorderThresholOfSkuInventory(backorderThreshol);
		userContext.getChecker().checkPreorderThresholOfSkuInventory(preorderThreshol);
		userContext.getChecker().checkStatusOfSkuInventory(status);
	
		userContext.getChecker().throwExceptionIfHasErrors(SkuInventoryManagerException.class);


		SkuInventory skuInventory=createNewSkuInventory();	

		skuInventory.setName(name);
		skuInventory.setStockLevel(stockLevel);
		skuInventory.setBackorderLevel(backorderLevel);
		skuInventory.setPreorderLevel(preorderLevel);
		skuInventory.setStockThreshold(stockThreshold);
		skuInventory.setBackorderThreshol(backorderThreshol);
		skuInventory.setPreorderThreshol(preorderThreshol);
		skuInventory.setStatus(status);
			
		Product product = loadProduct(userContext, productId,emptyOptions());
		skuInventory.setProduct(product);
		
		
			
		Platform platform = loadPlatform(userContext, platformId,emptyOptions());
		skuInventory.setPlatform(platform);
		
		

		skuInventory = saveSkuInventory(userContext, skuInventory, emptyOptions());
		
		onNewInstanceCreated(userContext, skuInventory);
		return skuInventory;

		
	}
	protected SkuInventory createNewSkuInventory() 
	{
		
		return new SkuInventory();		
	}
	
	protected void checkParamsForUpdatingSkuInventory(InventoryUserContext userContext,String skuInventoryId, int skuInventoryVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		userContext.getChecker().checkIdOfSkuInventory(skuInventoryId);
		userContext.getChecker().checkVersionOfSkuInventory( skuInventoryVersion);
		

		if(SkuInventory.NAME_PROPERTY.equals(property)){
			userContext.getChecker().checkNameOfSkuInventory(parseString(newValueExpr));
		}
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

				

		
	
		userContext.getChecker().throwExceptionIfHasErrors(SkuInventoryManagerException.class);
	
		
	}
	
	
	
	public SkuInventory clone(InventoryUserContext userContext, String fromSkuInventoryId) throws Exception{
		
		return userContext.getDAOGroup().getSkuInventoryDAO().clone(fromSkuInventoryId, this.allTokens());
	}
	
	public SkuInventory internalSaveSkuInventory(InventoryUserContext userContext, SkuInventory skuInventory) throws Exception 
	{
		return internalSaveSkuInventory(userContext, skuInventory, allTokens());

	}
	public SkuInventory internalSaveSkuInventory(InventoryUserContext userContext, SkuInventory skuInventory, Map<String,Object> options) throws Exception 
	{
		//checkParamsForUpdatingSkuInventory(userContext, skuInventoryId, skuInventoryVersion, property, newValueExpr, tokensExpr);
		
		
		synchronized(skuInventory){ 
			//will be good when the skuInventory loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to SkuInventory.
			
			
			skuInventory = saveSkuInventory(userContext, skuInventory, options);
			return skuInventory;
			
		}

	}
	
	public SkuInventory updateSkuInventory(InventoryUserContext userContext,String skuInventoryId, int skuInventoryVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingSkuInventory(userContext, skuInventoryId, skuInventoryVersion, property, newValueExpr, tokensExpr);
		
		
		
		SkuInventory skuInventory = loadSkuInventory(userContext, skuInventoryId, allTokens());
		if(skuInventory.getVersion() != skuInventoryVersion){
			String message = "The target version("+skuInventory.getVersion()+") is not equals to version("+skuInventoryVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(skuInventory){ 
			//will be good when the skuInventory loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to SkuInventory.
			
			skuInventory.changeProperty(property, newValueExpr);
			skuInventory = saveSkuInventory(userContext, skuInventory, tokens().done());
			return present(userContext,skuInventory, mergedAllTokens(tokensExpr));
			//return saveSkuInventory(userContext, skuInventory, tokens().done());
		}

	}
	
	public SkuInventory updateSkuInventoryProperty(InventoryUserContext userContext,String skuInventoryId, int skuInventoryVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingSkuInventory(userContext, skuInventoryId, skuInventoryVersion, property, newValueExpr, tokensExpr);
		
		SkuInventory skuInventory = loadSkuInventory(userContext, skuInventoryId, allTokens());
		if(skuInventory.getVersion() != skuInventoryVersion){
			String message = "The target version("+skuInventory.getVersion()+") is not equals to version("+skuInventoryVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(skuInventory){ 
			//will be good when the skuInventory loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to SkuInventory.
			
			skuInventory.changeProperty(property, newValueExpr);
			
			skuInventory = saveSkuInventory(userContext, skuInventory, tokens().done());
			return present(userContext,skuInventory, mergedAllTokens(tokensExpr));
			//return saveSkuInventory(userContext, skuInventory, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}
	
	protected SkuInventoryTokens tokens(){
		return SkuInventoryTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return SkuInventoryTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return SkuInventoryTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherProduct(InventoryUserContext userContext, String skuInventoryId, String anotherProductId) throws Exception
 	{
 		
 		userContext.getChecker().checkIdOfSkuInventory(skuInventoryId);
 		userContext.getChecker().checkIdOfProduct(anotherProductId);//check for optional reference
 		userContext.getChecker().throwExceptionIfHasErrors(SkuInventoryManagerException.class);
 		
 	}
 	public SkuInventory transferToAnotherProduct(InventoryUserContext userContext, String skuInventoryId, String anotherProductId) throws Exception
 	{
 		checkParamsForTransferingAnotherProduct(userContext, skuInventoryId,anotherProductId);
 
		SkuInventory skuInventory = loadSkuInventory(userContext, skuInventoryId, allTokens());	
		synchronized(skuInventory){
			//will be good when the skuInventory loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Product product = loadProduct(userContext, anotherProductId, emptyOptions());		
			skuInventory.updateProduct(product);		
			skuInventory = saveSkuInventory(userContext, skuInventory, emptyOptions());
			
			return present(userContext,skuInventory, allTokens());
			
		}

 	}
 	
	 	
 	
 	
	public CandidateProduct requestCandidateProduct(InventoryUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateProduct result = new CandidateProduct();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");
		
		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<Product> candidateList = userContext.getDAOGroup().getProductDAO().requestCandidateProductForSkuInventory(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}
 	
 	protected void checkParamsForTransferingAnotherPlatform(InventoryUserContext userContext, String skuInventoryId, String anotherPlatformId) throws Exception
 	{
 		
 		userContext.getChecker().checkIdOfSkuInventory(skuInventoryId);
 		userContext.getChecker().checkIdOfPlatform(anotherPlatformId);//check for optional reference
 		userContext.getChecker().throwExceptionIfHasErrors(SkuInventoryManagerException.class);
 		
 	}
 	public SkuInventory transferToAnotherPlatform(InventoryUserContext userContext, String skuInventoryId, String anotherPlatformId) throws Exception
 	{
 		checkParamsForTransferingAnotherPlatform(userContext, skuInventoryId,anotherPlatformId);
 
		SkuInventory skuInventory = loadSkuInventory(userContext, skuInventoryId, allTokens());	
		synchronized(skuInventory){
			//will be good when the skuInventory loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Platform platform = loadPlatform(userContext, anotherPlatformId, emptyOptions());		
			skuInventory.updatePlatform(platform);		
			skuInventory = saveSkuInventory(userContext, skuInventory, emptyOptions());
			
			return present(userContext,skuInventory, allTokens());
			
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
		SmartList<Platform> candidateList = userContext.getDAOGroup().getPlatformDAO().requestCandidatePlatformForSkuInventory(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}
 	
 //--------------------------------------------------------------
	
	 	
 	protected Product loadProduct(InventoryUserContext userContext, String newProductId, Map<String,Object> options) throws Exception
 	{
		
 		return userContext.getDAOGroup().getProductDAO().load(newProductId, options);
 	}
 	
 	
 	
	
	 	
 	protected Platform loadPlatform(InventoryUserContext userContext, String newPlatformId, Map<String,Object> options) throws Exception
 	{
		
 		return userContext.getDAOGroup().getPlatformDAO().load(newPlatformId, options);
 	}
 	
 	
 	
	
	//--------------------------------------------------------------

	public void delete(InventoryUserContext userContext, String skuInventoryId, int skuInventoryVersion) throws Exception {
		//deleteInternal(userContext, skuInventoryId, skuInventoryVersion);		
	}
	protected void deleteInternal(InventoryUserContext userContext,
			String skuInventoryId, int skuInventoryVersion) throws Exception{
			
		userContext.getDAOGroup().getSkuInventoryDAO().delete(skuInventoryId, skuInventoryVersion);
	}
	
	public SkuInventory forgetByAll(InventoryUserContext userContext, String skuInventoryId, int skuInventoryVersion) throws Exception {
		return forgetByAllInternal(userContext, skuInventoryId, skuInventoryVersion);		
	}
	protected SkuInventory forgetByAllInternal(InventoryUserContext userContext,
			String skuInventoryId, int skuInventoryVersion) throws Exception{
			
		return userContext.getDAOGroup().getSkuInventoryDAO().disconnectFromAll(skuInventoryId, skuInventoryVersion);
	}
	

	
	public int deleteAll(InventoryUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new SkuInventoryManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}
	
	
	protected int deleteAllInternal(InventoryUserContext userContext) throws Exception{
		return userContext.getDAOGroup().getSkuInventoryDAO().deleteAll();
	}


	
	
	
	
	

	public void onNewInstanceCreated(InventoryUserContext userContext, SkuInventory newCreated) throws Exception{
		ensureRelationInGraph(userContext, newCreated);
		sendCreationEvent(userContext, newCreated);
	}

}


