
package com.doublechaintech.inventory.product;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.doublechaintech.inventory.BaseEntity;
import com.doublechaintech.inventory.SmartList;
import com.doublechaintech.inventory.MultipleAccessKey;
import com.doublechaintech.inventory.InventoryUserContext;
import com.doublechaintech.inventory.skuinventory.SkuInventoryDAO;
import com.doublechaintech.inventory.platform.PlatformDAO;


public interface ProductDAO{

	
	public Product load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<Product> productList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public Product present(Product product,Map<String,Object> options) throws Exception;
	public Product clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public Product save(Product product,Map<String,Object> options);
	public SmartList<Product> saveProductList(SmartList<Product> productList,Map<String,Object> options);
	public SmartList<Product> removeProductList(SmartList<Product> productList,Map<String,Object> options);
	public SmartList<Product> findProductWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countProductWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countProductWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String productId, int version) throws Exception;
	public Product disconnectFromAll(String productId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public SkuInventoryDAO getSkuInventoryDAO();
		
	
 	public SmartList<Product> requestCandidateProductForSkuInventory(InventoryUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public Product planToRemoveSkuInventoryList(Product product, String skuInventoryIds[], Map<String,Object> options)throws Exception;


	//disconnect Product with platform in SkuInventory
	public Product planToRemoveSkuInventoryListWithPlatform(Product product, String platformId, Map<String,Object> options)throws Exception;
	public int countSkuInventoryListWithPlatform(String productId, String platformId, Map<String,Object> options)throws Exception;
	
	
	public SmartList<Product> queryList(String sql, Object ... parmeters);
 
 	public SmartList<Product> findProductByPlatform(String platformId, Map<String,Object> options);
 	public int countProductByPlatform(String platformId, Map<String,Object> options);
 	public Map<String, Integer> countProductByPlatformIds(String[] ids, Map<String,Object> options);
 	public SmartList<Product> findProductByPlatform(String platformId, int start, int count, Map<String,Object> options);
 	public void analyzeProductByPlatform(SmartList<Product> resultList, String platformId, Map<String,Object> options);

 
 }


