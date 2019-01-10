
package com.doublechaintech.inventory.product;
import com.doublechaintech.inventory.CommonTokens;
import java.util.Map;
public class ProductTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="product";
	
	public static boolean checkOptions(Map<String,Object> options, String optionToCheck){
		
		if(options==null){
 			return false; //completely no option here
 		}
 		if(options.containsKey(ALL)){
 			//danger, debug only, might load the entire database!, really terrible
 			return true;
 		}
		String ownerKey = getOwnerObjectKey();
		Object ownerObject =(String) options.get(ownerKey);
		if(ownerObject ==  null){
			return false;
		}
		if(!ownerObject.equals(OWNER_OBJECT_NAME)){ //is the owner? 
			return false; 
		}
		
 		if(options.containsKey(optionToCheck)){
 			//options.remove(optionToCheck);
 			//consume the key, can not use any more to extract the data with the same token.			
 			return true;
 		}
 		
 		return false;
	
	}
	protected ProductTokens(){
		//ensure not initialized outside the class
	}
	
	public ProductTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static ProductTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected ProductTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static ProductTokens start(){
		return new ProductTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static ProductTokens allTokens(){
		
		return start()
			.withPlatform()
			.withSkuInventoryList();
	
	}
	public static ProductTokens withoutListsTokens(){
		
		return start()
			.withPlatform();
	
	}
	
	public static Map <String,Object> all(){
		return allTokens().done();
	}
	public static Map <String,Object> withoutLists(){
		return withoutListsTokens().done();
	}
	public static Map <String,Object> empty(){
		return start().done();
	}

	protected static final String PLATFORM = "platform";
	public String getPlatform(){
		return PLATFORM;
	}
	public ProductTokens withPlatform(){		
		addSimpleOptions(PLATFORM);
		return this;
	}
	
	
	protected static final String SKU_INVENTORY_LIST = "skuInventoryList";
	public String getSkuInventoryList(){
		return SKU_INVENTORY_LIST;
	}
	public ProductTokens withSkuInventoryList(){		
		addSimpleOptions(SKU_INVENTORY_LIST);
		return this;
	}
	public ProductTokens analyzeSkuInventoryList(){		
		addSimpleOptions(SKU_INVENTORY_LIST+".anaylze");
		return this;
	}
	public boolean analyzeSkuInventoryListEnabled(){		
		
		return checkOptions(this.options(), SKU_INVENTORY_LIST+".anaylze");
	}
	public ProductTokens extractMoreFromSkuInventoryList(String idsSeperatedWithComma){		
		addSimpleOptions(SKU_INVENTORY_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int skuInventoryListSortCounter = 0;
	public ProductTokens sortSkuInventoryListWith(String field, String descOrAsc){		
		addSortMoreOptions(SKU_INVENTORY_LIST,skuInventoryListSortCounter++, field, descOrAsc);
		return this;
	}
	private int skuInventoryListSearchCounter = 0;
	public ProductTokens searchSkuInventoryListWith(String field, String verb, String value){		
		addSearchMoreOptions(SKU_INVENTORY_LIST,skuInventoryListSearchCounter++, field, verb, value);
		return this;
	}
	
	public ProductTokens searchAllTextOfSkuInventoryList(String verb, String value){	
		String field = "id|name|status";
		addSearchMoreOptions(SKU_INVENTORY_LIST,skuInventoryListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ProductTokens rowsPerPageOfSkuInventoryList(int rowsPerPage){		
		addSimpleOptions(SKU_INVENTORY_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public ProductTokens currentPageNumberOfSkuInventoryList(int currentPageNumber){		
		addSimpleOptions(SKU_INVENTORY_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public ProductTokens retainColumnsOfSkuInventoryList(String[] columns){		
		addSimpleOptions(SKU_INVENTORY_LIST+"RetainColumns",columns);
		return this;
	}
	public ProductTokens excludeColumnsOfSkuInventoryList(String[] columns){		
		addSimpleOptions(SKU_INVENTORY_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	
	public  ProductTokens searchEntireObjectText(String verb, String value){
		
		searchAllTextOfSkuInventoryList(verb, value);	
		return this;
	}
}

