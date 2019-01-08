
package com.doublechaintech.inventory.platform;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.math.BigDecimal;
import com.doublechaintech.inventory.InventoryNamingServiceDAO;
import com.doublechaintech.inventory.BaseEntity;
import com.doublechaintech.inventory.SmartList;
import com.doublechaintech.inventory.AccessKey;
import com.doublechaintech.inventory.DateKey;
import com.doublechaintech.inventory.StatsInfo;
import com.doublechaintech.inventory.StatsItem;

import com.doublechaintech.inventory.MultipleAccessKey;
import com.doublechaintech.inventory.InventoryUserContext;


import com.doublechaintech.inventory.product.Product;
import com.doublechaintech.inventory.skuinventory.SkuInventory;

import com.doublechaintech.inventory.skuinventory.SkuInventoryDAO;
import com.doublechaintech.inventory.product.ProductDAO;



import org.springframework.dao.EmptyResultDataAccessException;

public class PlatformJDBCTemplateDAO extends InventoryNamingServiceDAO implements PlatformDAO{


			
		
	
  	private  ProductDAO  productDAO;
 	public void setProductDAO(ProductDAO pProductDAO){
 	
 		if(pProductDAO == null){
 			throw new IllegalStateException("Do not try to set productDAO to null.");
 		}
	 	this.productDAO = pProductDAO;
 	}
 	public ProductDAO getProductDAO(){
 		if(this.productDAO == null){
 			throw new IllegalStateException("The productDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.productDAO;
 	}	
 	
			
		
	
  	private  SkuInventoryDAO  skuInventoryDAO;
 	public void setSkuInventoryDAO(SkuInventoryDAO pSkuInventoryDAO){
 	
 		if(pSkuInventoryDAO == null){
 			throw new IllegalStateException("Do not try to set skuInventoryDAO to null.");
 		}
	 	this.skuInventoryDAO = pSkuInventoryDAO;
 	}
 	public SkuInventoryDAO getSkuInventoryDAO(){
 		if(this.skuInventoryDAO == null){
 			throw new IllegalStateException("The skuInventoryDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.skuInventoryDAO;
 	}	
 	
			
		

	
	/*
	protected Platform load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalPlatform(accessKey, options);
	}
	*/
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public Platform load(String id,Map<String,Object> options) throws Exception{
		return loadInternalPlatform(PlatformTable.withId(id), options);
	}
	
	
	
	public Platform save(Platform platform,Map<String,Object> options){
		
		String methodName="save(Platform platform,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(platform, methodName, "platform");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalPlatform(platform,options);
	}
	public Platform clone(String platformId, Map<String,Object> options) throws Exception{
	
		return clone(PlatformTable.withId(platformId),options);
	}
	
	protected Platform clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String platformId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		Platform newPlatform = loadInternalPlatform(accessKey, options);
		newPlatform.setVersion(0);
		
		
 		
 		if(isSaveProductListEnabled(options)){
 			for(Product item: newPlatform.getProductList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSaveSkuInventoryListEnabled(options)){
 			for(SkuInventory item: newPlatform.getSkuInventoryList()){
 				item.setVersion(0);
 			}
 		}
		

		
		saveInternalPlatform(newPlatform,options);
		
		return newPlatform;
	}
	
	
	
	

	protected void throwIfHasException(String platformId,int version,int count) throws Exception{
		if (count == 1) {
			throw new PlatformVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new PlatformNotFoundException(
					"The " + this.getTableName() + "(" + platformId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String platformId, int version) throws Exception{
	
		String methodName="delete(String platformId, int version)";
		assertMethodArgumentNotNull(platformId, methodName, "platformId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{platformId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(platformId,version);
		}
		
	
	}
	
	
	
	
	

	public Platform disconnectFromAll(String platformId, int version) throws Exception{
	
		
		Platform platform = loadInternalPlatform(PlatformTable.withId(platformId), emptyOptions());
		platform.clearFromAll();
		this.savePlatform(platform);
		return platform;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return PlatformTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "platform";
	}
	@Override
	protected String getBeanName() {
		
		return "platform";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return PlatformTokens.checkOptions(options, optionToCheck);
	
	}


		
	
	protected boolean isExtractProductListEnabled(Map<String,Object> options){		
 		return checkOptions(options,PlatformTokens.PRODUCT_LIST);
 	}
 	protected boolean isAnalyzeProductListEnabled(Map<String,Object> options){		
 		return checkOptions(options,PlatformTokens.PRODUCT_LIST+".analyze");
 	}

	protected boolean isSaveProductListEnabled(Map<String,Object> options){
		return checkOptions(options, PlatformTokens.PRODUCT_LIST);
		
 	}
 	
		
	
	protected boolean isExtractSkuInventoryListEnabled(Map<String,Object> options){		
 		return checkOptions(options,PlatformTokens.SKU_INVENTORY_LIST);
 	}
 	protected boolean isAnalyzeSkuInventoryListEnabled(Map<String,Object> options){		
 		return checkOptions(options,PlatformTokens.SKU_INVENTORY_LIST+".analyze");
 	}

	protected boolean isSaveSkuInventoryListEnabled(Map<String,Object> options){
		return checkOptions(options, PlatformTokens.SKU_INVENTORY_LIST);
		
 	}
 	
		

	

	protected PlatformMapper getPlatformMapper(){
		return new PlatformMapper();
	}

	
	
	protected Platform extractPlatform(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			Platform platform = loadSingleObject(accessKey, getPlatformMapper());
			return platform;
		}catch(EmptyResultDataAccessException e){
			throw new PlatformNotFoundException("Platform("+accessKey+") is not found!");
		}

	}

	
	

	protected Platform loadInternalPlatform(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		Platform platform = extractPlatform(accessKey, loadOptions);

		
		if(isExtractProductListEnabled(loadOptions)){
	 		extractProductList(platform, loadOptions);
 		}	
 		if(isAnalyzeProductListEnabled(loadOptions)){
	 		// analyzeProductList(platform, loadOptions);
 		}
 		
		
		if(isExtractSkuInventoryListEnabled(loadOptions)){
	 		extractSkuInventoryList(platform, loadOptions);
 		}	
 		if(isAnalyzeSkuInventoryListEnabled(loadOptions)){
	 		// analyzeSkuInventoryList(platform, loadOptions);
 		}
 		
		
		return platform;
		
	}

	
		
	protected void enhanceProductList(SmartList<Product> productList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Platform extractProductList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<Product> productList = getProductDAO().findProductByPlatform(platform.getId(),options);
		if(productList != null){
			enhanceProductList(productList,options);
			platform.setProductList(productList);
		}
		
		return platform;
	
	}	
	
	protected Platform analyzeProductList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<Product> productList = platform.getProductList();
		if(productList != null){
			getProductDAO().analyzeProductByPlatform(productList, platform.getId(), options);
			
		}
		
		return platform;
	
	}	
	
		
	protected void enhanceSkuInventoryList(SmartList<SkuInventory> skuInventoryList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Platform extractSkuInventoryList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<SkuInventory> skuInventoryList = getSkuInventoryDAO().findSkuInventoryByPlatform(platform.getId(),options);
		if(skuInventoryList != null){
			enhanceSkuInventoryList(skuInventoryList,options);
			platform.setSkuInventoryList(skuInventoryList);
		}
		
		return platform;
	
	}	
	
	protected Platform analyzeSkuInventoryList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<SkuInventory> skuInventoryList = platform.getSkuInventoryList();
		if(skuInventoryList != null){
			getSkuInventoryDAO().analyzeSkuInventoryByPlatform(skuInventoryList, platform.getId(), options);
			
		}
		
		return platform;
	
	}	
	
		
		
 	
		
		
		

	

	protected Platform savePlatform(Platform  platform){
		
		if(!platform.isChanged()){
			return platform;
		}
		
		
		String SQL=this.getSavePlatformSQL(platform);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSavePlatformParameters(platform);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		platform.incVersion();
		return platform;
	
	}
	public SmartList<Platform> savePlatformList(SmartList<Platform> platformList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitPlatformList(platformList);
		
		batchPlatformCreate((List<Platform>)lists[CREATE_LIST_INDEX]);
		
		batchPlatformUpdate((List<Platform>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(Platform platform:platformList){
			if(platform.isChanged()){
				platform.incVersion();
			}
			
		
		}
		
		
		return platformList;
	}

	public SmartList<Platform> removePlatformList(SmartList<Platform> platformList,Map<String,Object> options){
		
		
		super.removeList(platformList, options);
		
		return platformList;
		
		
	}
	
	protected List<Object[]> preparePlatformBatchCreateArgs(List<Platform> platformList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(Platform platform:platformList ){
			Object [] parameters = preparePlatformCreateParameters(platform);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> preparePlatformBatchUpdateArgs(List<Platform> platformList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(Platform platform:platformList ){
			if(!platform.isChanged()){
				continue;
			}
			Object [] parameters = preparePlatformUpdateParameters(platform);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchPlatformCreate(List<Platform> platformList){
		String SQL=getCreateSQL();
		List<Object[]> args=preparePlatformBatchCreateArgs(platformList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchPlatformUpdate(List<Platform> platformList){
		String SQL=getUpdateSQL();
		List<Object[]> args=preparePlatformBatchUpdateArgs(platformList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitPlatformList(List<Platform> platformList){
		
		List<Platform> platformCreateList=new ArrayList<Platform>();
		List<Platform> platformUpdateList=new ArrayList<Platform>();
		
		for(Platform platform: platformList){
			if(isUpdateRequest(platform)){
				platformUpdateList.add( platform);
				continue;
			}
			platformCreateList.add(platform);
		}
		
		return new Object[]{platformCreateList,platformUpdateList};
	}
	
	protected boolean isUpdateRequest(Platform platform){
 		return platform.getVersion() > 0;
 	}
 	protected String getSavePlatformSQL(Platform platform){
 		if(isUpdateRequest(platform)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSavePlatformParameters(Platform platform){
 		if(isUpdateRequest(platform) ){
 			return preparePlatformUpdateParameters(platform);
 		}
 		return preparePlatformCreateParameters(platform);
 	}
 	protected Object[] preparePlatformUpdateParameters(Platform platform){
 		Object[] parameters = new Object[6];
 
 		parameters[0] = platform.getName();
 		parameters[1] = platform.getIntroduction();
 		parameters[2] = platform.getCurrentVersion();		
 		parameters[3] = platform.nextVersion();
 		parameters[4] = platform.getId();
 		parameters[5] = platform.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] preparePlatformCreateParameters(Platform platform){
		Object[] parameters = new Object[4];
		String newPlatformId=getNextId();
		platform.setId(newPlatformId);
		parameters[0] =  platform.getId();
 
 		parameters[1] = platform.getName();
 		parameters[2] = platform.getIntroduction();
 		parameters[3] = platform.getCurrentVersion();		
 				
 		return parameters;
 	}
 	
	protected Platform saveInternalPlatform(Platform platform, Map<String,Object> options){
		
		savePlatform(platform);

		
		if(isSaveProductListEnabled(options)){
	 		saveProductList(platform, options);
	 		//removeProductList(platform, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSaveSkuInventoryListEnabled(options)){
	 		saveSkuInventoryList(platform, options);
	 		//removeSkuInventoryList(platform, options);
	 		//Not delete the record
	 		
 		}		
		
		return platform;
		
	}
	
	
	
	//======================================================================================
	

	
	public Platform planToRemoveProductList(Platform platform, String productIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Product.PLATFORM_PROPERTY, platform.getId());
		key.put(Product.ID_PROPERTY, productIds);
		
		SmartList<Product> externalProductList = getProductDAO().
				findProductWithKey(key, options);
		if(externalProductList == null){
			return platform;
		}
		if(externalProductList.isEmpty()){
			return platform;
		}
		
		for(Product product: externalProductList){

			product.clearFromAll();
		}
		
		
		SmartList<Product> productList = platform.getProductList();		
		productList.addAllToRemoveList(externalProductList);
		return platform;	
	
	}


	public Platform planToRemoveSkuInventoryList(Platform platform, String skuInventoryIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(SkuInventory.PLATFORM_PROPERTY, platform.getId());
		key.put(SkuInventory.ID_PROPERTY, skuInventoryIds);
		
		SmartList<SkuInventory> externalSkuInventoryList = getSkuInventoryDAO().
				findSkuInventoryWithKey(key, options);
		if(externalSkuInventoryList == null){
			return platform;
		}
		if(externalSkuInventoryList.isEmpty()){
			return platform;
		}
		
		for(SkuInventory skuInventory: externalSkuInventoryList){

			skuInventory.clearFromAll();
		}
		
		
		SmartList<SkuInventory> skuInventoryList = platform.getSkuInventoryList();		
		skuInventoryList.addAllToRemoveList(externalSkuInventoryList);
		return platform;	
	
	}


	//disconnect Platform with product in SkuInventory
	public Platform planToRemoveSkuInventoryListWithProduct(Platform platform, String productId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(SkuInventory.PLATFORM_PROPERTY, platform.getId());
		key.put(SkuInventory.PRODUCT_PROPERTY, productId);
		
		SmartList<SkuInventory> externalSkuInventoryList = getSkuInventoryDAO().
				findSkuInventoryWithKey(key, options);
		if(externalSkuInventoryList == null){
			return platform;
		}
		if(externalSkuInventoryList.isEmpty()){
			return platform;
		}
		
		for(SkuInventory skuInventory: externalSkuInventoryList){
			skuInventory.clearProduct();
			skuInventory.clearPlatform();
			
		}
		
		
		SmartList<SkuInventory> skuInventoryList = platform.getSkuInventoryList();		
		skuInventoryList.addAllToRemoveList(externalSkuInventoryList);
		return platform;
	}
	
	public int countSkuInventoryListWithProduct(String platformId, String productId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(SkuInventory.PLATFORM_PROPERTY, platformId);
		key.put(SkuInventory.PRODUCT_PROPERTY, productId);
		
		int count = getSkuInventoryDAO().countSkuInventoryWithKey(key, options);
		return count;
	}
	

		
	protected Platform saveProductList(Platform platform, Map<String,Object> options){
		
		
		
		
		SmartList<Product> productList = platform.getProductList();
		if(productList == null){
			//null list means nothing
			return platform;
		}
		SmartList<Product> mergedUpdateProductList = new SmartList<Product>();
		
		
		mergedUpdateProductList.addAll(productList); 
		if(productList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateProductList.addAll(productList.getToRemoveList());
			productList.removeAll(productList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getProductDAO().saveProductList(mergedUpdateProductList,options);
		
		if(productList.getToRemoveList() != null){
			productList.removeAll(productList.getToRemoveList());
		}
		
		
		return platform;
	
	}
	
	protected Platform removeProductList(Platform platform, Map<String,Object> options){
	
	
		SmartList<Product> productList = platform.getProductList();
		if(productList == null){
			return platform;
		}	
	
		SmartList<Product> toRemoveProductList = productList.getToRemoveList();
		
		if(toRemoveProductList == null){
			return platform;
		}
		if(toRemoveProductList.isEmpty()){
			return platform;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getProductDAO().removeProductList(toRemoveProductList,options);
		
		return platform;
	
	}
	
	

 	
 	
	
	
	
		
	protected Platform saveSkuInventoryList(Platform platform, Map<String,Object> options){
		
		
		
		
		SmartList<SkuInventory> skuInventoryList = platform.getSkuInventoryList();
		if(skuInventoryList == null){
			//null list means nothing
			return platform;
		}
		SmartList<SkuInventory> mergedUpdateSkuInventoryList = new SmartList<SkuInventory>();
		
		
		mergedUpdateSkuInventoryList.addAll(skuInventoryList); 
		if(skuInventoryList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateSkuInventoryList.addAll(skuInventoryList.getToRemoveList());
			skuInventoryList.removeAll(skuInventoryList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getSkuInventoryDAO().saveSkuInventoryList(mergedUpdateSkuInventoryList,options);
		
		if(skuInventoryList.getToRemoveList() != null){
			skuInventoryList.removeAll(skuInventoryList.getToRemoveList());
		}
		
		
		return platform;
	
	}
	
	protected Platform removeSkuInventoryList(Platform platform, Map<String,Object> options){
	
	
		SmartList<SkuInventory> skuInventoryList = platform.getSkuInventoryList();
		if(skuInventoryList == null){
			return platform;
		}	
	
		SmartList<SkuInventory> toRemoveSkuInventoryList = skuInventoryList.getToRemoveList();
		
		if(toRemoveSkuInventoryList == null){
			return platform;
		}
		if(toRemoveSkuInventoryList.isEmpty()){
			return platform;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getSkuInventoryDAO().removeSkuInventoryList(toRemoveSkuInventoryList,options);
		
		return platform;
	
	}
	
	

 	
 	
	
	
	
		

	public Platform present(Platform platform,Map<String, Object> options){
	
		presentProductList(platform,options);
		presentSkuInventoryList(platform,options);

		return platform;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected Platform presentProductList(
			Platform platform,
			Map<String, Object> options) {

		SmartList<Product> productList = platform.getProductList();		
				SmartList<Product> newList= presentSubList(platform.getId(),
				productList,
				options,
				getProductDAO()::countProductByPlatform,
				getProductDAO()::findProductByPlatform
				);

		
		platform.setProductList(newList);
		

		return platform;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected Platform presentSkuInventoryList(
			Platform platform,
			Map<String, Object> options) {

		SmartList<SkuInventory> skuInventoryList = platform.getSkuInventoryList();		
				SmartList<SkuInventory> newList= presentSubList(platform.getId(),
				skuInventoryList,
				options,
				getSkuInventoryDAO()::countSkuInventoryByPlatform,
				getSkuInventoryDAO()::findSkuInventoryByPlatform
				);

		
		platform.setSkuInventoryList(newList);
		

		return platform;
	}			
		

	
    public SmartList<Platform> requestCandidatePlatformForProduct(InventoryUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(PlatformTable.COLUMN_NAME, filterKey, pageNo, pageSize, getPlatformMapper());
    }
		
    public SmartList<Platform> requestCandidatePlatformForSkuInventory(InventoryUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(PlatformTable.COLUMN_NAME, filterKey, pageNo, pageSize, getPlatformMapper());
    }
		

	protected String getTableName(){
		return PlatformTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<Platform> platformList) {		
		this.enhanceListInternal(platformList, this.getPlatformMapper());
	}
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<Platform> platformList = ownerEntity.collectRefsWithType(Platform.INTERNAL_TYPE);
		this.enhanceList(platformList);
		
	}
	
	@Override
	public SmartList<Platform> findPlatformWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getPlatformMapper());

	}
	@Override
	public int countPlatformWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countPlatformWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<Platform> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getPlatformMapper());
	}
}


