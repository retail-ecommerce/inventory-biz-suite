
package com.doublechaintech.inventory.skuinventory;

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
import com.doublechaintech.inventory.platform.Platform;

import com.doublechaintech.inventory.platform.PlatformDAO;
import com.doublechaintech.inventory.product.ProductDAO;



import org.springframework.dao.EmptyResultDataAccessException;

public class SkuInventoryJDBCTemplateDAO extends InventoryNamingServiceDAO implements SkuInventoryDAO{
 
 	
 	private  ProductDAO  productDAO;
 	public void setProductDAO(ProductDAO productDAO){
	 	this.productDAO = productDAO;
 	}
 	public ProductDAO getProductDAO(){
	 	return this.productDAO;
 	}
 
 	
 	private  PlatformDAO  platformDAO;
 	public void setPlatformDAO(PlatformDAO platformDAO){
	 	this.platformDAO = platformDAO;
 	}
 	public PlatformDAO getPlatformDAO(){
	 	return this.platformDAO;
 	}


			
		

	
	/*
	protected SkuInventory load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalSkuInventory(accessKey, options);
	}
	*/
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public SkuInventory load(String id,Map<String,Object> options) throws Exception{
		return loadInternalSkuInventory(SkuInventoryTable.withId(id), options);
	}
	
	
	
	public SkuInventory save(SkuInventory skuInventory,Map<String,Object> options){
		
		String methodName="save(SkuInventory skuInventory,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(skuInventory, methodName, "skuInventory");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalSkuInventory(skuInventory,options);
	}
	public SkuInventory clone(String skuInventoryId, Map<String,Object> options) throws Exception{
	
		return clone(SkuInventoryTable.withId(skuInventoryId),options);
	}
	
	protected SkuInventory clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String skuInventoryId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		SkuInventory newSkuInventory = loadInternalSkuInventory(accessKey, options);
		newSkuInventory.setVersion(0);
		
		

		
		saveInternalSkuInventory(newSkuInventory,options);
		
		return newSkuInventory;
	}
	
	
	
	

	protected void throwIfHasException(String skuInventoryId,int version,int count) throws Exception{
		if (count == 1) {
			throw new SkuInventoryVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new SkuInventoryNotFoundException(
					"The " + this.getTableName() + "(" + skuInventoryId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String skuInventoryId, int version) throws Exception{
	
		String methodName="delete(String skuInventoryId, int version)";
		assertMethodArgumentNotNull(skuInventoryId, methodName, "skuInventoryId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{skuInventoryId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(skuInventoryId,version);
		}
		
	
	}
	
	
	
	
	

	public SkuInventory disconnectFromAll(String skuInventoryId, int version) throws Exception{
	
		
		SkuInventory skuInventory = loadInternalSkuInventory(SkuInventoryTable.withId(skuInventoryId), emptyOptions());
		skuInventory.clearFromAll();
		this.saveSkuInventory(skuInventory);
		return skuInventory;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return SkuInventoryTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "sku_inventory";
	}
	@Override
	protected String getBeanName() {
		
		return "skuInventory";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return SkuInventoryTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractProductEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, SkuInventoryTokens.PRODUCT);
 	}

 	protected boolean isSaveProductEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, SkuInventoryTokens.PRODUCT);
 	}
 	

 	
  

 	protected boolean isExtractPlatformEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, SkuInventoryTokens.PLATFORM);
 	}

 	protected boolean isSavePlatformEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, SkuInventoryTokens.PLATFORM);
 	}
 	

 	
 
		

	

	protected SkuInventoryMapper getSkuInventoryMapper(){
		return new SkuInventoryMapper();
	}

	
	
	protected SkuInventory extractSkuInventory(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			SkuInventory skuInventory = loadSingleObject(accessKey, getSkuInventoryMapper());
			return skuInventory;
		}catch(EmptyResultDataAccessException e){
			throw new SkuInventoryNotFoundException("SkuInventory("+accessKey+") is not found!");
		}

	}

	
	

	protected SkuInventory loadInternalSkuInventory(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		SkuInventory skuInventory = extractSkuInventory(accessKey, loadOptions);
 	
 		if(isExtractProductEnabled(loadOptions)){
	 		extractProduct(skuInventory, loadOptions);
 		}
  	
 		if(isExtractPlatformEnabled(loadOptions)){
	 		extractPlatform(skuInventory, loadOptions);
 		}
 
		
		return skuInventory;
		
	}

	 

 	protected SkuInventory extractProduct(SkuInventory skuInventory, Map<String,Object> options) throws Exception{

		if(skuInventory.getProduct() == null){
			return skuInventory;
		}
		String productId = skuInventory.getProduct().getId();
		if( productId == null){
			return skuInventory;
		}
		Product product = getProductDAO().load(productId,options);
		if(product != null){
			skuInventory.setProduct(product);
		}
		
 		
 		return skuInventory;
 	}
 		
  

 	protected SkuInventory extractPlatform(SkuInventory skuInventory, Map<String,Object> options) throws Exception{

		if(skuInventory.getPlatform() == null){
			return skuInventory;
		}
		String platformId = skuInventory.getPlatform().getId();
		if( platformId == null){
			return skuInventory;
		}
		Platform platform = getPlatformDAO().load(platformId,options);
		if(platform != null){
			skuInventory.setPlatform(platform);
		}
		
 		
 		return skuInventory;
 	}
 		
 
		
		
  	
 	public SmartList<SkuInventory> findSkuInventoryByProduct(String productId,Map<String,Object> options){
 	
  		SmartList<SkuInventory> resultList = queryWith(SkuInventoryTable.COLUMN_PRODUCT, productId, options, getSkuInventoryMapper());
		// analyzeSkuInventoryByProduct(resultList, productId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<SkuInventory> findSkuInventoryByProduct(String productId, int start, int count,Map<String,Object> options){
 		
 		SmartList<SkuInventory> resultList =  queryWithRange(SkuInventoryTable.COLUMN_PRODUCT, productId, options, getSkuInventoryMapper(), start, count);
 		//analyzeSkuInventoryByProduct(resultList, productId, options);
 		return resultList;
 		
 	}
 	public void analyzeSkuInventoryByProduct(SmartList<SkuInventory> resultList, String productId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(SkuInventory.PRODUCT_PROPERTY, productId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 		
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countSkuInventoryByProduct(String productId,Map<String,Object> options){

 		return countWith(SkuInventoryTable.COLUMN_PRODUCT, productId, options);
 	}
 	@Override
	public Map<String, Integer> countSkuInventoryByProductIds(String[] ids, Map<String, Object> options) {
		return countWithIds(SkuInventoryTable.COLUMN_PRODUCT, ids, options);
	}
 	
  	
 	public SmartList<SkuInventory> findSkuInventoryByPlatform(String platformId,Map<String,Object> options){
 	
  		SmartList<SkuInventory> resultList = queryWith(SkuInventoryTable.COLUMN_PLATFORM, platformId, options, getSkuInventoryMapper());
		// analyzeSkuInventoryByPlatform(resultList, platformId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<SkuInventory> findSkuInventoryByPlatform(String platformId, int start, int count,Map<String,Object> options){
 		
 		SmartList<SkuInventory> resultList =  queryWithRange(SkuInventoryTable.COLUMN_PLATFORM, platformId, options, getSkuInventoryMapper(), start, count);
 		//analyzeSkuInventoryByPlatform(resultList, platformId, options);
 		return resultList;
 		
 	}
 	public void analyzeSkuInventoryByPlatform(SmartList<SkuInventory> resultList, String platformId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(SkuInventory.PLATFORM_PROPERTY, platformId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 		
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countSkuInventoryByPlatform(String platformId,Map<String,Object> options){

 		return countWith(SkuInventoryTable.COLUMN_PLATFORM, platformId, options);
 	}
 	@Override
	public Map<String, Integer> countSkuInventoryByPlatformIds(String[] ids, Map<String, Object> options) {
		return countWithIds(SkuInventoryTable.COLUMN_PLATFORM, ids, options);
	}
 	
 	
		
		
		

	

	protected SkuInventory saveSkuInventory(SkuInventory  skuInventory){
		
		if(!skuInventory.isChanged()){
			return skuInventory;
		}
		
		
		String SQL=this.getSaveSkuInventorySQL(skuInventory);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveSkuInventoryParameters(skuInventory);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		skuInventory.incVersion();
		return skuInventory;
	
	}
	public SmartList<SkuInventory> saveSkuInventoryList(SmartList<SkuInventory> skuInventoryList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitSkuInventoryList(skuInventoryList);
		
		batchSkuInventoryCreate((List<SkuInventory>)lists[CREATE_LIST_INDEX]);
		
		batchSkuInventoryUpdate((List<SkuInventory>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(SkuInventory skuInventory:skuInventoryList){
			if(skuInventory.isChanged()){
				skuInventory.incVersion();
			}
			
		
		}
		
		
		return skuInventoryList;
	}

	public SmartList<SkuInventory> removeSkuInventoryList(SmartList<SkuInventory> skuInventoryList,Map<String,Object> options){
		
		
		super.removeList(skuInventoryList, options);
		
		return skuInventoryList;
		
		
	}
	
	protected List<Object[]> prepareSkuInventoryBatchCreateArgs(List<SkuInventory> skuInventoryList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(SkuInventory skuInventory:skuInventoryList ){
			Object [] parameters = prepareSkuInventoryCreateParameters(skuInventory);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareSkuInventoryBatchUpdateArgs(List<SkuInventory> skuInventoryList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(SkuInventory skuInventory:skuInventoryList ){
			if(!skuInventory.isChanged()){
				continue;
			}
			Object [] parameters = prepareSkuInventoryUpdateParameters(skuInventory);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchSkuInventoryCreate(List<SkuInventory> skuInventoryList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareSkuInventoryBatchCreateArgs(skuInventoryList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchSkuInventoryUpdate(List<SkuInventory> skuInventoryList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareSkuInventoryBatchUpdateArgs(skuInventoryList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitSkuInventoryList(List<SkuInventory> skuInventoryList){
		
		List<SkuInventory> skuInventoryCreateList=new ArrayList<SkuInventory>();
		List<SkuInventory> skuInventoryUpdateList=new ArrayList<SkuInventory>();
		
		for(SkuInventory skuInventory: skuInventoryList){
			if(isUpdateRequest(skuInventory)){
				skuInventoryUpdateList.add( skuInventory);
				continue;
			}
			skuInventoryCreateList.add(skuInventory);
		}
		
		return new Object[]{skuInventoryCreateList,skuInventoryUpdateList};
	}
	
	protected boolean isUpdateRequest(SkuInventory skuInventory){
 		return skuInventory.getVersion() > 0;
 	}
 	protected String getSaveSkuInventorySQL(SkuInventory skuInventory){
 		if(isUpdateRequest(skuInventory)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveSkuInventoryParameters(SkuInventory skuInventory){
 		if(isUpdateRequest(skuInventory) ){
 			return prepareSkuInventoryUpdateParameters(skuInventory);
 		}
 		return prepareSkuInventoryCreateParameters(skuInventory);
 	}
 	protected Object[] prepareSkuInventoryUpdateParameters(SkuInventory skuInventory){
 		Object[] parameters = new Object[13];
 
 		parameters[0] = skuInventory.getName();
 		parameters[1] = skuInventory.getStockLevel();
 		parameters[2] = skuInventory.getBackorderLevel();
 		parameters[3] = skuInventory.getPreorderLevel();
 		parameters[4] = skuInventory.getStockThreshold();
 		parameters[5] = skuInventory.getBackorderThreshol();
 		parameters[6] = skuInventory.getPreorderThreshol();
 		parameters[7] = skuInventory.getStatus(); 	
 		if(skuInventory.getProduct() != null){
 			parameters[8] = skuInventory.getProduct().getId();
 		}
  	
 		if(skuInventory.getPlatform() != null){
 			parameters[9] = skuInventory.getPlatform().getId();
 		}
 		
 		parameters[10] = skuInventory.nextVersion();
 		parameters[11] = skuInventory.getId();
 		parameters[12] = skuInventory.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareSkuInventoryCreateParameters(SkuInventory skuInventory){
		Object[] parameters = new Object[11];
		String newSkuInventoryId=getNextId();
		skuInventory.setId(newSkuInventoryId);
		parameters[0] =  skuInventory.getId();
 
 		parameters[1] = skuInventory.getName();
 		parameters[2] = skuInventory.getStockLevel();
 		parameters[3] = skuInventory.getBackorderLevel();
 		parameters[4] = skuInventory.getPreorderLevel();
 		parameters[5] = skuInventory.getStockThreshold();
 		parameters[6] = skuInventory.getBackorderThreshol();
 		parameters[7] = skuInventory.getPreorderThreshol();
 		parameters[8] = skuInventory.getStatus(); 	
 		if(skuInventory.getProduct() != null){
 			parameters[9] = skuInventory.getProduct().getId();
 		
 		}
 		 	
 		if(skuInventory.getPlatform() != null){
 			parameters[10] = skuInventory.getPlatform().getId();
 		
 		}
 				
 				
 		return parameters;
 	}
 	
	protected SkuInventory saveInternalSkuInventory(SkuInventory skuInventory, Map<String,Object> options){
		
		saveSkuInventory(skuInventory);
 	
 		if(isSaveProductEnabled(options)){
	 		saveProduct(skuInventory, options);
 		}
  	
 		if(isSavePlatformEnabled(options)){
	 		savePlatform(skuInventory, options);
 		}
 
		
		return skuInventory;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected SkuInventory saveProduct(SkuInventory skuInventory, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(skuInventory.getProduct() == null){
 			return skuInventory;//do nothing when it is null
 		}
 		
 		getProductDAO().save(skuInventory.getProduct(),options);
 		return skuInventory;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected SkuInventory savePlatform(SkuInventory skuInventory, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(skuInventory.getPlatform() == null){
 			return skuInventory;//do nothing when it is null
 		}
 		
 		getPlatformDAO().save(skuInventory.getPlatform(),options);
 		return skuInventory;
 		
 	}
 	
 	
 	
 	 
	
 

	

		

	public SkuInventory present(SkuInventory skuInventory,Map<String, Object> options){
	

		return skuInventory;
	
	}
		

	

	protected String getTableName(){
		return SkuInventoryTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<SkuInventory> skuInventoryList) {		
		this.enhanceListInternal(skuInventoryList, this.getSkuInventoryMapper());
	}
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<SkuInventory> skuInventoryList = ownerEntity.collectRefsWithType(SkuInventory.INTERNAL_TYPE);
		this.enhanceList(skuInventoryList);
		
	}
	
	@Override
	public SmartList<SkuInventory> findSkuInventoryWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getSkuInventoryMapper());

	}
	@Override
	public int countSkuInventoryWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countSkuInventoryWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<SkuInventory> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getSkuInventoryMapper());
	}
}


