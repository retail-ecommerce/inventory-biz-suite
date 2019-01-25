
package com.doublechaintech.inventory.product;

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


import com.doublechaintech.inventory.platform.Platform;
import com.doublechaintech.inventory.skuinventory.SkuInventory;

import com.doublechaintech.inventory.skuinventory.SkuInventoryDAO;
import com.doublechaintech.inventory.platform.PlatformDAO;



import org.springframework.dao.EmptyResultDataAccessException;

public class ProductJDBCTemplateDAO extends InventoryNamingServiceDAO implements ProductDAO{
 
 	
 	private  PlatformDAO  platformDAO;
 	public void setPlatformDAO(PlatformDAO platformDAO){
	 	this.platformDAO = platformDAO;
 	}
 	public PlatformDAO getPlatformDAO(){
	 	return this.platformDAO;
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
	protected Product load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalProduct(accessKey, options);
	}
	*/
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public Product load(String id,Map<String,Object> options) throws Exception{
		return loadInternalProduct(ProductTable.withId(id), options);
	}
	
	
	
	public Product save(Product product,Map<String,Object> options){
		
		String methodName="save(Product product,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(product, methodName, "product");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalProduct(product,options);
	}
	public Product clone(String productId, Map<String,Object> options) throws Exception{
	
		return clone(ProductTable.withId(productId),options);
	}
	
	protected Product clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String productId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		Product newProduct = loadInternalProduct(accessKey, options);
		newProduct.setVersion(0);
		
		
 		
 		if(isSaveSkuInventoryListEnabled(options)){
 			for(SkuInventory item: newProduct.getSkuInventoryList()){
 				item.setVersion(0);
 			}
 		}
		

		
		saveInternalProduct(newProduct,options);
		
		return newProduct;
	}
	
	
	
	

	protected void throwIfHasException(String productId,int version,int count) throws Exception{
		if (count == 1) {
			throw new ProductVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new ProductNotFoundException(
					"The " + this.getTableName() + "(" + productId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String productId, int version) throws Exception{
	
		String methodName="delete(String productId, int version)";
		assertMethodArgumentNotNull(productId, methodName, "productId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{productId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(productId,version);
		}
		
	
	}
	
	
	
	
	

	public Product disconnectFromAll(String productId, int version) throws Exception{
	
		
		Product product = loadInternalProduct(ProductTable.withId(productId), emptyOptions());
		product.clearFromAll();
		this.saveProduct(product);
		return product;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return ProductTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "product";
	}
	@Override
	protected String getBeanName() {
		
		return "product";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return ProductTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractPlatformEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, ProductTokens.PLATFORM);
 	}

 	protected boolean isSavePlatformEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, ProductTokens.PLATFORM);
 	}
 	

 	
 
		
	
	protected boolean isExtractSkuInventoryListEnabled(Map<String,Object> options){		
 		return checkOptions(options,ProductTokens.SKU_INVENTORY_LIST);
 	}
 	protected boolean isAnalyzeSkuInventoryListEnabled(Map<String,Object> options){		
 		return true;
 		//return checkOptions(options,ProductTokens.SKU_INVENTORY_LIST+".analyze");
 	}
	
	protected boolean isSaveSkuInventoryListEnabled(Map<String,Object> options){
		return checkOptions(options, ProductTokens.SKU_INVENTORY_LIST);
		
 	}
 	
		

	

	protected ProductMapper getProductMapper(){
		return new ProductMapper();
	}

	
	
	protected Product extractProduct(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			Product product = loadSingleObject(accessKey, getProductMapper());
			return product;
		}catch(EmptyResultDataAccessException e){
			throw new ProductNotFoundException("Product("+accessKey+") is not found!");
		}

	}

	
	

	protected Product loadInternalProduct(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		Product product = extractProduct(accessKey, loadOptions);
 	
 		if(isExtractPlatformEnabled(loadOptions)){
	 		extractPlatform(product, loadOptions);
 		}
 
		
		if(isExtractSkuInventoryListEnabled(loadOptions)){
	 		extractSkuInventoryList(product, loadOptions);
 		}	
 		if(isAnalyzeSkuInventoryListEnabled(loadOptions)){
	 		analyzeSkuInventoryList(product, loadOptions);
 		}
 		
		
		return product;
		
	}

	 

 	protected Product extractPlatform(Product product, Map<String,Object> options) throws Exception{

		if(product.getPlatform() == null){
			return product;
		}
		String platformId = product.getPlatform().getId();
		if( platformId == null){
			return product;
		}
		Platform platform = getPlatformDAO().load(platformId,options);
		if(platform != null){
			product.setPlatform(platform);
		}
		
 		
 		return product;
 	}
 		
 
		
	protected void enhanceSkuInventoryList(SmartList<SkuInventory> skuInventoryList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Product extractSkuInventoryList(Product product, Map<String,Object> options){
		
		
		if(product == null){
			return null;
		}
		if(product.getId() == null){
			return product;
		}

		
		
		SmartList<SkuInventory> skuInventoryList = getSkuInventoryDAO().findSkuInventoryByProduct(product.getId(),options);
		if(skuInventoryList != null){
			enhanceSkuInventoryList(skuInventoryList,options);
			product.setSkuInventoryList(skuInventoryList);
		}
		
		return product;
	
	}	
	
	protected Product analyzeSkuInventoryList(Product product, Map<String,Object> options){
		
		
		if(product == null){
			return null;
		}
		if(product.getId() == null){
			return product;
		}

		
		
		SmartList<SkuInventory> skuInventoryList = product.getSkuInventoryList();
		if(skuInventoryList != null){
			getSkuInventoryDAO().analyzeSkuInventoryByProduct(skuInventoryList, product.getId(), options);
			
		}
		
		return product;
	
	}	
	
		
		
  	
 	public SmartList<Product> findProductByPlatform(String platformId,Map<String,Object> options){
 	
  		SmartList<Product> resultList = queryWith(ProductTable.COLUMN_PLATFORM, platformId, options, getProductMapper());
		// analyzeProductByPlatform(resultList, platformId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<Product> findProductByPlatform(String platformId, int start, int count,Map<String,Object> options){
 		
 		SmartList<Product> resultList =  queryWithRange(ProductTable.COLUMN_PLATFORM, platformId, options, getProductMapper(), start, count);
 		//analyzeProductByPlatform(resultList, platformId, options);
 		return resultList;
 		
 	}
 	public void analyzeProductByPlatform(SmartList<Product> resultList, String platformId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(Product.PLATFORM_PROPERTY, platformId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem lastUpdateTimeStatsItem = new StatsItem();
		//Product.LAST_UPDATE_TIME_PROPERTY
		lastUpdateTimeStatsItem.setDisplayName("Product");
		lastUpdateTimeStatsItem.setInternalName(formatKeyForDateLine(Product.LAST_UPDATE_TIME_PROPERTY));
		lastUpdateTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(Product.LAST_UPDATE_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(lastUpdateTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countProductByPlatform(String platformId,Map<String,Object> options){

 		return countWith(ProductTable.COLUMN_PLATFORM, platformId, options);
 	}
 	@Override
	public Map<String, Integer> countProductByPlatformIds(String[] ids, Map<String, Object> options) {
		return countWithIds(ProductTable.COLUMN_PLATFORM, ids, options);
	}
 	
 	
		
		
		

	

	protected Product saveProduct(Product  product){
		
		if(!product.isChanged()){
			return product;
		}
		
		
		String SQL=this.getSaveProductSQL(product);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveProductParameters(product);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		product.incVersion();
		return product;
	
	}
	public SmartList<Product> saveProductList(SmartList<Product> productList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitProductList(productList);
		
		batchProductCreate((List<Product>)lists[CREATE_LIST_INDEX]);
		
		batchProductUpdate((List<Product>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(Product product:productList){
			if(product.isChanged()){
				product.incVersion();
			}
			
		
		}
		
		
		return productList;
	}

	public SmartList<Product> removeProductList(SmartList<Product> productList,Map<String,Object> options){
		
		
		super.removeList(productList, options);
		
		return productList;
		
		
	}
	
	protected List<Object[]> prepareProductBatchCreateArgs(List<Product> productList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(Product product:productList ){
			Object [] parameters = prepareProductCreateParameters(product);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareProductBatchUpdateArgs(List<Product> productList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(Product product:productList ){
			if(!product.isChanged()){
				continue;
			}
			Object [] parameters = prepareProductUpdateParameters(product);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchProductCreate(List<Product> productList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareProductBatchCreateArgs(productList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchProductUpdate(List<Product> productList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareProductBatchUpdateArgs(productList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitProductList(List<Product> productList){
		
		List<Product> productCreateList=new ArrayList<Product>();
		List<Product> productUpdateList=new ArrayList<Product>();
		
		for(Product product: productList){
			if(isUpdateRequest(product)){
				productUpdateList.add( product);
				continue;
			}
			productCreateList.add(product);
		}
		
		return new Object[]{productCreateList,productUpdateList};
	}
	
	protected boolean isUpdateRequest(Product product){
 		return product.getVersion() > 0;
 	}
 	protected String getSaveProductSQL(Product product){
 		if(isUpdateRequest(product)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveProductParameters(Product product){
 		if(isUpdateRequest(product) ){
 			return prepareProductUpdateParameters(product);
 		}
 		return prepareProductCreateParameters(product);
 	}
 	protected Object[] prepareProductUpdateParameters(Product product){
 		Object[] parameters = new Object[7];
 
 		parameters[0] = product.getName();
 		parameters[1] = product.getIntroduction(); 	
 		if(product.getPlatform() != null){
 			parameters[2] = product.getPlatform().getId();
 		}
 
 		parameters[3] = product.getLastUpdateTime();		
 		parameters[4] = product.nextVersion();
 		parameters[5] = product.getId();
 		parameters[6] = product.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareProductCreateParameters(Product product){
		Object[] parameters = new Object[5];
		String newProductId=getNextId();
		product.setId(newProductId);
		parameters[0] =  product.getId();
 
 		parameters[1] = product.getName();
 		parameters[2] = product.getIntroduction(); 	
 		if(product.getPlatform() != null){
 			parameters[3] = product.getPlatform().getId();
 		
 		}
 		
 		parameters[4] = product.getLastUpdateTime();		
 				
 		return parameters;
 	}
 	
	protected Product saveInternalProduct(Product product, Map<String,Object> options){
		
		saveProduct(product);
 	
 		if(isSavePlatformEnabled(options)){
	 		savePlatform(product, options);
 		}
 
		
		if(isSaveSkuInventoryListEnabled(options)){
	 		saveSkuInventoryList(product, options);
	 		//removeSkuInventoryList(product, options);
	 		//Not delete the record
	 		
 		}		
		
		return product;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected Product savePlatform(Product product, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(product.getPlatform() == null){
 			return product;//do nothing when it is null
 		}
 		
 		getPlatformDAO().save(product.getPlatform(),options);
 		return product;
 		
 	}
 	
 	
 	
 	 
	
 

	
	public Product planToRemoveSkuInventoryList(Product product, String skuInventoryIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(SkuInventory.PRODUCT_PROPERTY, product.getId());
		key.put(SkuInventory.ID_PROPERTY, skuInventoryIds);
		
		SmartList<SkuInventory> externalSkuInventoryList = getSkuInventoryDAO().
				findSkuInventoryWithKey(key, options);
		if(externalSkuInventoryList == null){
			return product;
		}
		if(externalSkuInventoryList.isEmpty()){
			return product;
		}
		
		for(SkuInventory skuInventory: externalSkuInventoryList){

			skuInventory.clearFromAll();
		}
		
		
		SmartList<SkuInventory> skuInventoryList = product.getSkuInventoryList();		
		skuInventoryList.addAllToRemoveList(externalSkuInventoryList);
		return product;	
	
	}


	//disconnect Product with platform in SkuInventory
	public Product planToRemoveSkuInventoryListWithPlatform(Product product, String platformId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(SkuInventory.PRODUCT_PROPERTY, product.getId());
		key.put(SkuInventory.PLATFORM_PROPERTY, platformId);
		
		SmartList<SkuInventory> externalSkuInventoryList = getSkuInventoryDAO().
				findSkuInventoryWithKey(key, options);
		if(externalSkuInventoryList == null){
			return product;
		}
		if(externalSkuInventoryList.isEmpty()){
			return product;
		}
		
		for(SkuInventory skuInventory: externalSkuInventoryList){
			skuInventory.clearPlatform();
			skuInventory.clearProduct();
			
		}
		
		
		SmartList<SkuInventory> skuInventoryList = product.getSkuInventoryList();		
		skuInventoryList.addAllToRemoveList(externalSkuInventoryList);
		return product;
	}
	
	public int countSkuInventoryListWithPlatform(String productId, String platformId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(SkuInventory.PRODUCT_PROPERTY, productId);
		key.put(SkuInventory.PLATFORM_PROPERTY, platformId);
		
		int count = getSkuInventoryDAO().countSkuInventoryWithKey(key, options);
		return count;
	}
	

		
	protected Product saveSkuInventoryList(Product product, Map<String,Object> options){
		
		
		
		
		SmartList<SkuInventory> skuInventoryList = product.getSkuInventoryList();
		if(skuInventoryList == null){
			//null list means nothing
			return product;
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
		
		
		return product;
	
	}
	
	protected Product removeSkuInventoryList(Product product, Map<String,Object> options){
	
	
		SmartList<SkuInventory> skuInventoryList = product.getSkuInventoryList();
		if(skuInventoryList == null){
			return product;
		}	
	
		SmartList<SkuInventory> toRemoveSkuInventoryList = skuInventoryList.getToRemoveList();
		
		if(toRemoveSkuInventoryList == null){
			return product;
		}
		if(toRemoveSkuInventoryList.isEmpty()){
			return product;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getSkuInventoryDAO().removeSkuInventoryList(toRemoveSkuInventoryList,options);
		
		return product;
	
	}
	
	

 	
 	
	
	
	
		

	public Product present(Product product,Map<String, Object> options){
	
		presentSkuInventoryList(product,options);

		return product;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected Product presentSkuInventoryList(
			Product product,
			Map<String, Object> options) {

		SmartList<SkuInventory> skuInventoryList = product.getSkuInventoryList();		
				SmartList<SkuInventory> newList= presentSubList(product.getId(),
				skuInventoryList,
				options,
				getSkuInventoryDAO()::countSkuInventoryByProduct,
				getSkuInventoryDAO()::findSkuInventoryByProduct
				);

		
		product.setSkuInventoryList(newList);
		

		return product;
	}			
		

	
    public SmartList<Product> requestCandidateProductForSkuInventory(InventoryUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(ProductTable.COLUMN_NAME, filterKey, pageNo, pageSize, getProductMapper());
    }
		

	protected String getTableName(){
		return ProductTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<Product> productList) {		
		this.enhanceListInternal(productList, this.getProductMapper());
	}
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<Product> productList = ownerEntity.collectRefsWithType(Product.INTERNAL_TYPE);
		this.enhanceList(productList);
		
	}
	
	@Override
	public SmartList<Product> findProductWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getProductMapper());

	}
	@Override
	public int countProductWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countProductWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<Product> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getProductMapper());
	}
}


