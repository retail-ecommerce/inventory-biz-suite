
package com.doublechaintech.inventory.platform;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.doublechaintech.inventory.BaseEntity;
import com.doublechaintech.inventory.SmartList;
import com.doublechaintech.inventory.MultipleAccessKey;
import com.doublechaintech.inventory.InventoryUserContext;
import com.doublechaintech.inventory.skuinventory.SkuInventoryDAO;
import com.doublechaintech.inventory.product.ProductDAO;


public interface PlatformDAO{

	
	public Platform load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<Platform> platformList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public Platform present(Platform platform,Map<String,Object> options) throws Exception;
	public Platform clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public Platform save(Platform platform,Map<String,Object> options);
	public SmartList<Platform> savePlatformList(SmartList<Platform> platformList,Map<String,Object> options);
	public SmartList<Platform> removePlatformList(SmartList<Platform> platformList,Map<String,Object> options);
	public SmartList<Platform> findPlatformWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countPlatformWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countPlatformWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String platformId, int version) throws Exception;
	public Platform disconnectFromAll(String platformId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public ProductDAO getProductDAO();
		
	public SkuInventoryDAO getSkuInventoryDAO();
		
	
 	public SmartList<Platform> requestCandidatePlatformForProduct(InventoryUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<Platform> requestCandidatePlatformForSkuInventory(InventoryUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public Platform planToRemoveProductList(Platform platform, String productIds[], Map<String,Object> options)throws Exception;


	public Platform planToRemoveSkuInventoryList(Platform platform, String skuInventoryIds[], Map<String,Object> options)throws Exception;


	//disconnect Platform with product in SkuInventory
	public Platform planToRemoveSkuInventoryListWithProduct(Platform platform, String productId, Map<String,Object> options)throws Exception;
	public int countSkuInventoryListWithProduct(String platformId, String productId, Map<String,Object> options)throws Exception;
	
	
	public SmartList<Platform> queryList(String sql, Object ... parmeters);
}


