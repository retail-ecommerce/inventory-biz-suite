
package com.doublechaintech.inventory.platform;
import com.doublechaintech.inventory.CommonTokens;
import java.util.Map;
public class PlatformTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="platform";
	
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
	protected PlatformTokens(){
		//ensure not initialized outside the class
	}
	
	public PlatformTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static PlatformTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected PlatformTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static PlatformTokens start(){
		return new PlatformTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static PlatformTokens allTokens(){
		
		return start()
			.withProductList()
			.withSkuInventoryList();
	
	}
	public static PlatformTokens withoutListsTokens(){
		
		return start();
	
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

	protected static final String PRODUCT_LIST = "productList";
	public String getProductList(){
		return PRODUCT_LIST;
	}
	public PlatformTokens withProductList(){		
		addSimpleOptions(PRODUCT_LIST);
		return this;
	}
	public PlatformTokens analyzeProductList(){		
		addSimpleOptions(PRODUCT_LIST+".anaylze");
		return this;
	}
	public boolean analyzeProductListEnabled(){		
		
		return checkOptions(this.options(), PRODUCT_LIST+".anaylze");
	}
	public PlatformTokens extractMoreFromProductList(String idsSeperatedWithComma){		
		addSimpleOptions(PRODUCT_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int productListSortCounter = 0;
	public PlatformTokens sortProductListWith(String field, String descOrAsc){		
		addSortMoreOptions(PRODUCT_LIST,productListSortCounter++, field, descOrAsc);
		return this;
	}
	private int productListSearchCounter = 0;
	public PlatformTokens searchProductListWith(String field, String verb, String value){		
		addSearchMoreOptions(PRODUCT_LIST,productListSearchCounter++, field, verb, value);
		return this;
	}
	
	public PlatformTokens searchAllTextOfProductList(String verb, String value){	
		String field = "id|name|introduction";
		addSearchMoreOptions(PRODUCT_LIST,productListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens rowsPerPageOfProductList(int rowsPerPage){		
		addSimpleOptions(PRODUCT_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public PlatformTokens currentPageNumberOfProductList(int currentPageNumber){		
		addSimpleOptions(PRODUCT_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public PlatformTokens retainColumnsOfProductList(String[] columns){		
		addSimpleOptions(PRODUCT_LIST+"RetainColumns",columns);
		return this;
	}
	public PlatformTokens excludeColumnsOfProductList(String[] columns){		
		addSimpleOptions(PRODUCT_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String SKU_INVENTORY_LIST = "skuInventoryList";
	public String getSkuInventoryList(){
		return SKU_INVENTORY_LIST;
	}
	public PlatformTokens withSkuInventoryList(){		
		addSimpleOptions(SKU_INVENTORY_LIST);
		return this;
	}
	public PlatformTokens analyzeSkuInventoryList(){		
		addSimpleOptions(SKU_INVENTORY_LIST+".anaylze");
		return this;
	}
	public boolean analyzeSkuInventoryListEnabled(){		
		
		return checkOptions(this.options(), SKU_INVENTORY_LIST+".anaylze");
	}
	public PlatformTokens extractMoreFromSkuInventoryList(String idsSeperatedWithComma){		
		addSimpleOptions(SKU_INVENTORY_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int skuInventoryListSortCounter = 0;
	public PlatformTokens sortSkuInventoryListWith(String field, String descOrAsc){		
		addSortMoreOptions(SKU_INVENTORY_LIST,skuInventoryListSortCounter++, field, descOrAsc);
		return this;
	}
	private int skuInventoryListSearchCounter = 0;
	public PlatformTokens searchSkuInventoryListWith(String field, String verb, String value){		
		addSearchMoreOptions(SKU_INVENTORY_LIST,skuInventoryListSearchCounter++, field, verb, value);
		return this;
	}
	
	public PlatformTokens searchAllTextOfSkuInventoryList(String verb, String value){	
		String field = "id|name|status";
		addSearchMoreOptions(SKU_INVENTORY_LIST,skuInventoryListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens rowsPerPageOfSkuInventoryList(int rowsPerPage){		
		addSimpleOptions(SKU_INVENTORY_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public PlatformTokens currentPageNumberOfSkuInventoryList(int currentPageNumber){		
		addSimpleOptions(SKU_INVENTORY_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public PlatformTokens retainColumnsOfSkuInventoryList(String[] columns){		
		addSimpleOptions(SKU_INVENTORY_LIST+"RetainColumns",columns);
		return this;
	}
	public PlatformTokens excludeColumnsOfSkuInventoryList(String[] columns){		
		addSimpleOptions(SKU_INVENTORY_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	
	public  PlatformTokens searchEntireObjectText(String verb, String value){
		
		searchAllTextOfProductList(verb, value);	
		searchAllTextOfSkuInventoryList(verb, value);	
		return this;
	}
}

