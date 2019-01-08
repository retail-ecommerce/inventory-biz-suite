
package com.doublechaintech.inventory.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import com.terapico.caf.DateTime;
import com.doublechaintech.inventory.BaseEntity;
import com.doublechaintech.inventory.SmartList;
import com.doublechaintech.inventory.KeyValuePair;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.doublechaintech.inventory.platform.Platform;
import com.doublechaintech.inventory.skuinventory.SkuInventory;

@JsonSerialize(using = ProductSerializer.class)
public class Product extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String NAME_PROPERTY                  = "name"              ;
	public static final String INTRODUCTION_PROPERTY          = "introduction"      ;
	public static final String PLATFORM_PROPERTY              = "platform"          ;
	public static final String LAST_UPDATE_TIME_PROPERTY      = "lastUpdateTime"    ;
	public static final String VERSION_PROPERTY               = "version"           ;

	public static final String SKU_INVENTORY_LIST                       = "skuInventoryList"  ;

	public static final String INTERNAL_TYPE="Product";
	public String getInternalType(){
		return INTERNAL_TYPE;
	}
	
	public String getDisplayName(){
	
		String displayName = getName();
		if(displayName!=null){
			return displayName;
		}
		
		return super.getDisplayName();
		
	}

	private static final long serialVersionUID = 1L;
	

	protected		String              	mId                 ;
	protected		String              	mName               ;
	protected		String              	mIntroduction       ;
	protected		Platform            	mPlatform           ;
	protected		DateTime            	mLastUpdateTime     ;
	protected		int                 	mVersion            ;
	
	
	protected		SmartList<SkuInventory>	mSkuInventoryList   ;
	
		
	public 	Product(){
		//lazy load for all the properties
	}
	//disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){
		setPlatform( null );

		this.changed = true;
	}
	
	public 	Product(String name, String introduction, Platform platform, DateTime lastUpdateTime)
	{
		setName(name);
		setIntroduction(introduction);
		setPlatform(platform);
		setLastUpdateTime(lastUpdateTime);

		this.mSkuInventoryList = new SmartList<SkuInventory>();	
	}
	
	//Support for changing the property
	
	public void changeProperty(String property, String newValueExpr) {
     	
		if(NAME_PROPERTY.equals(property)){
			changeNameProperty(newValueExpr);
		}
		if(INTRODUCTION_PROPERTY.equals(property)){
			changeIntroductionProperty(newValueExpr);
		}
		if(LAST_UPDATE_TIME_PROPERTY.equals(property)){
			changeLastUpdateTimeProperty(newValueExpr);
		}

      
	}
    
    
	protected void changeNameProperty(String newValueExpr){
		String oldValue = getName();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateName(newValue);
		this.onChangeProperty(NAME_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			
	protected void changeIntroductionProperty(String newValueExpr){
		String oldValue = getIntroduction();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateIntroduction(newValue);
		this.onChangeProperty(INTRODUCTION_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			
	protected void changeLastUpdateTimeProperty(String newValueExpr){
		DateTime oldValue = getLastUpdateTime();
		DateTime newValue = parseTimestamp(newValueExpr);
		if(equalsTimestamp(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateLastUpdateTime(newValue);
		this.onChangeProperty(LAST_UPDATE_TIME_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			


	
	
	
	public void setId(String id){
		this.mId = trimString(id);;
	}
	public String getId(){
		return this.mId;
	}
	public Product updateId(String id){
		this.mId = trimString(id);;
		this.changed = true;
		return this;
	}
	
	
	public void setName(String name){
		this.mName = trimString(name);;
	}
	public String getName(){
		return this.mName;
	}
	public Product updateName(String name){
		this.mName = trimString(name);;
		this.changed = true;
		return this;
	}
	
	
	public void setIntroduction(String introduction){
		this.mIntroduction = trimString(introduction);;
	}
	public String getIntroduction(){
		return this.mIntroduction;
	}
	public Product updateIntroduction(String introduction){
		this.mIntroduction = trimString(introduction);;
		this.changed = true;
		return this;
	}
	
	
	public void setPlatform(Platform platform){
		this.mPlatform = platform;;
	}
	public Platform getPlatform(){
		return this.mPlatform;
	}
	public Product updatePlatform(Platform platform){
		this.mPlatform = platform;;
		this.changed = true;
		return this;
	}
	
	
	public void clearPlatform(){
		setPlatform ( null );
		this.changed = true;
	}
	
	public void setLastUpdateTime(DateTime lastUpdateTime){
		this.mLastUpdateTime = lastUpdateTime;;
	}
	public DateTime getLastUpdateTime(){
		return this.mLastUpdateTime;
	}
	public Product updateLastUpdateTime(DateTime lastUpdateTime){
		this.mLastUpdateTime = lastUpdateTime;;
		this.changed = true;
		return this;
	}
	
	
	public void setVersion(int version){
		this.mVersion = version;;
	}
	public int getVersion(){
		return this.mVersion;
	}
	public Product updateVersion(int version){
		this.mVersion = version;;
		this.changed = true;
		return this;
	}
	
	

	public  SmartList<SkuInventory> getSkuInventoryList(){
		if(this.mSkuInventoryList == null){
			this.mSkuInventoryList = new SmartList<SkuInventory>();
			this.mSkuInventoryList.setListInternalName (SKU_INVENTORY_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mSkuInventoryList;	
	}
	public  void setSkuInventoryList(SmartList<SkuInventory> skuInventoryList){
		for( SkuInventory skuInventory:skuInventoryList){
			skuInventory.setProduct(this);
		}

		this.mSkuInventoryList = skuInventoryList;
		this.mSkuInventoryList.setListInternalName (SKU_INVENTORY_LIST );
		
	}
	
	public  void addSkuInventory(SkuInventory skuInventory){
		skuInventory.setProduct(this);
		getSkuInventoryList().add(skuInventory);
	}
	public  void addSkuInventoryList(SmartList<SkuInventory> skuInventoryList){
		for( SkuInventory skuInventory:skuInventoryList){
			skuInventory.setProduct(this);
		}
		getSkuInventoryList().addAll(skuInventoryList);
	}
	
	public  SkuInventory removeSkuInventory(SkuInventory skuInventoryIndex){
		
		int index = getSkuInventoryList().indexOf(skuInventoryIndex);
        if(index < 0){
        	String message = "SkuInventory("+skuInventoryIndex.getId()+") with version='"+skuInventoryIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        SkuInventory skuInventory = getSkuInventoryList().get(index);        
        // skuInventory.clearProduct(); //disconnect with Product
        skuInventory.clearFromAll(); //disconnect with Product
		
		boolean result = getSkuInventoryList().planToRemove(skuInventory);
        if(!result){
        	String message = "SkuInventory("+skuInventoryIndex.getId()+") with version='"+skuInventoryIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return skuInventory;
        
	
	}
	//断舍离
	public  void breakWithSkuInventory(SkuInventory skuInventory){
		
		if(skuInventory == null){
			return;
		}
		skuInventory.setProduct(null);
		//getSkuInventoryList().remove();
	
	}
	
	public  boolean hasSkuInventory(SkuInventory skuInventory){
	
		return getSkuInventoryList().contains(skuInventory);
  
	}
	
	public void copySkuInventoryFrom(SkuInventory skuInventory) {

		SkuInventory skuInventoryInList = findTheSkuInventory(skuInventory);
		SkuInventory newSkuInventory = new SkuInventory();
		skuInventoryInList.copyTo(newSkuInventory);
		newSkuInventory.setVersion(0);//will trigger copy
		getSkuInventoryList().add(newSkuInventory);
		addItemToFlexiableObject(COPIED_CHILD, newSkuInventory);
	}
	
	public  SkuInventory findTheSkuInventory(SkuInventory skuInventory){
		
		int index =  getSkuInventoryList().indexOf(skuInventory);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "SkuInventory("+skuInventory.getId()+") with version='"+skuInventory.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getSkuInventoryList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpSkuInventoryList(){
		getSkuInventoryList().clear();
	}
	
	
	


	public void collectRefercences(BaseEntity owner, List<BaseEntity> entityList, String internalType){

		addToEntityList(this, entityList, getPlatform(), internalType);

		
	}
	
	public List<BaseEntity>  collectRefercencesFromLists(String internalType){
		
		List<BaseEntity> entityList = new ArrayList<BaseEntity>();
		collectFromList(this, entityList, getSkuInventoryList(), internalType);

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
		listOfList.add( getSkuInventoryList());
			

		return listOfList;
	}

	
	public List<KeyValuePair> keyValuePairOf(){
		List<KeyValuePair> result =  super.keyValuePairOf();

		appendKeyValuePair(result, ID_PROPERTY, getId());
		appendKeyValuePair(result, NAME_PROPERTY, getName());
		appendKeyValuePair(result, INTRODUCTION_PROPERTY, getIntroduction());
		appendKeyValuePair(result, PLATFORM_PROPERTY, getPlatform());
		appendKeyValuePair(result, LAST_UPDATE_TIME_PROPERTY, getLastUpdateTime());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());
		appendKeyValuePair(result, SKU_INVENTORY_LIST, getSkuInventoryList());
		if(!getSkuInventoryList().isEmpty()){
			appendKeyValuePair(result, "skuInventoryCount", getSkuInventoryList().getTotalCount());
			appendKeyValuePair(result, "skuInventoryCurrentPageNumber", getSkuInventoryList().getCurrentPageNumber());
		}

		
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Product){
		
		
			Product dest =(Product)baseDest;
		
			dest.setId(getId());
			dest.setName(getName());
			dest.setIntroduction(getIntroduction());
			dest.setPlatform(getPlatform());
			dest.setLastUpdateTime(getLastUpdateTime());
			dest.setVersion(getVersion());
			dest.setSkuInventoryList(getSkuInventoryList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("Product{");
		stringBuilder.append("\tid='"+getId()+"';");
		stringBuilder.append("\tname='"+getName()+"';");
		stringBuilder.append("\tintroduction='"+getIntroduction()+"';");
		if(getPlatform() != null ){
 			stringBuilder.append("\tplatform='Platform("+getPlatform().getId()+")';");
 		}
		stringBuilder.append("\tlastUpdateTime='"+getLastUpdateTime()+"';");
		stringBuilder.append("\tversion='"+getVersion()+"';");
		stringBuilder.append("}");

		return stringBuilder.toString();
	}
	
	//provide number calculation function
	

}

