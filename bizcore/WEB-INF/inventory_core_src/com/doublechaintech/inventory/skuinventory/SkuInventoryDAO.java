
package com.doublechaintech.inventory.skuinventory;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.doublechaintech.inventory.BaseEntity;
import com.doublechaintech.inventory.SmartList;
import com.doublechaintech.inventory.MultipleAccessKey;
import com.doublechaintech.inventory.InventoryUserContext;
import com.doublechaintech.inventory.platform.PlatformDAO;
import com.doublechaintech.inventory.product.ProductDAO;


public interface SkuInventoryDAO{

	
	public SkuInventory load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<SkuInventory> skuInventoryList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public SkuInventory present(SkuInventory skuInventory,Map<String,Object> options) throws Exception;
	public SkuInventory clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public SkuInventory save(SkuInventory skuInventory,Map<String,Object> options);
	public SmartList<SkuInventory> saveSkuInventoryList(SmartList<SkuInventory> skuInventoryList,Map<String,Object> options);
	public SmartList<SkuInventory> removeSkuInventoryList(SmartList<SkuInventory> skuInventoryList,Map<String,Object> options);
	public SmartList<SkuInventory> findSkuInventoryWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countSkuInventoryWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countSkuInventoryWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String skuInventoryId, int version) throws Exception;
	public SkuInventory disconnectFromAll(String skuInventoryId, int version) throws Exception;
	public int deleteAll() throws Exception;

	
	
	
	public SmartList<SkuInventory> queryList(String sql, Object ... parmeters);
 
 	public SmartList<SkuInventory> findSkuInventoryByProduct(String productId, Map<String,Object> options);
 	public int countSkuInventoryByProduct(String productId, Map<String,Object> options);
 	public Map<String, Integer> countSkuInventoryByProductIds(String[] ids, Map<String,Object> options);
 	public SmartList<SkuInventory> findSkuInventoryByProduct(String productId, int start, int count, Map<String,Object> options);
 	public void analyzeSkuInventoryByProduct(SmartList<SkuInventory> resultList, String productId, Map<String,Object> options);

 
  
 	public SmartList<SkuInventory> findSkuInventoryByPlatform(String platformId, Map<String,Object> options);
 	public int countSkuInventoryByPlatform(String platformId, Map<String,Object> options);
 	public Map<String, Integer> countSkuInventoryByPlatformIds(String[] ids, Map<String,Object> options);
 	public SmartList<SkuInventory> findSkuInventoryByPlatform(String platformId, int start, int count, Map<String,Object> options);
 	public void analyzeSkuInventoryByPlatform(SmartList<SkuInventory> resultList, String platformId, Map<String,Object> options);

 
 }


