
package com.doublechaintech.inventory.skuinventory;
import com.doublechaintech.inventory.CommonTokens;
import java.util.Map;
public class SkuInventoryTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="skuInventory";
	
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
	protected SkuInventoryTokens(){
		//ensure not initialized outside the class
	}
	
	public SkuInventoryTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static SkuInventoryTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected SkuInventoryTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static SkuInventoryTokens start(){
		return new SkuInventoryTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static SkuInventoryTokens allTokens(){
		
		return start()
			.withProduct()
			.withPlatform();
	
	}
	public static SkuInventoryTokens withoutListsTokens(){
		
		return start()
			.withProduct()
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

	protected static final String PRODUCT = "product";
	public String getProduct(){
		return PRODUCT;
	}
	public SkuInventoryTokens withProduct(){		
		addSimpleOptions(PRODUCT);
		return this;
	}
	
	
	protected static final String PLATFORM = "platform";
	public String getPlatform(){
		return PLATFORM;
	}
	public SkuInventoryTokens withPlatform(){		
		addSimpleOptions(PLATFORM);
		return this;
	}
	
	
	
	public  SkuInventoryTokens searchEntireObjectText(String verb, String value){
		
		return this;
	}
}

