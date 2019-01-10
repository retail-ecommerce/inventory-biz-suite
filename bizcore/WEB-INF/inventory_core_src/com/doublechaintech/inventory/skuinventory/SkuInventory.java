
package com.doublechaintech.inventory.skuinventory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import com.terapico.caf.DateTime;
import com.doublechaintech.inventory.BaseEntity;
import com.doublechaintech.inventory.SmartList;
import com.doublechaintech.inventory.KeyValuePair;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.doublechaintech.inventory.product.Product;
import com.doublechaintech.inventory.platform.Platform;

@JsonSerialize(using = SkuInventorySerializer.class)
public class SkuInventory extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String NAME_PROPERTY                  = "name"              ;
	public static final String STOCK_LEVEL_PROPERTY           = "stockLevel"        ;
	public static final String BACKORDER_LEVEL_PROPERTY       = "backorderLevel"    ;
	public static final String PREORDER_LEVEL_PROPERTY        = "preorderLevel"     ;
	public static final String STOCK_THRESHOLD_PROPERTY       = "stockThreshold"    ;
	public static final String BACKORDER_THRESHOL_PROPERTY    = "backorderThreshol" ;
	public static final String PREORDER_THRESHOL_PROPERTY     = "preorderThreshol"  ;
	public static final String STATUS_PROPERTY                = "status"            ;
	public static final String PRODUCT_PROPERTY               = "product"           ;
	public static final String PLATFORM_PROPERTY              = "platform"          ;
	public static final String VERSION_PROPERTY               = "version"           ;


	public static final String INTERNAL_TYPE="SkuInventory";
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
	protected		int                 	mStockLevel         ;
	protected		int                 	mBackorderLevel     ;
	protected		int                 	mPreorderLevel      ;
	protected		int                 	mStockThreshold     ;
	protected		int                 	mBackorderThreshol  ;
	protected		int                 	mPreorderThreshol   ;
	protected		String              	mStatus             ;
	protected		Product             	mProduct            ;
	protected		Platform            	mPlatform           ;
	protected		int                 	mVersion            ;
	
	
	
		
	public 	SkuInventory(){
		//lazy load for all the properties
	}
	//disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){
		setProduct( null );
		setPlatform( null );

		this.changed = true;
	}
	
	public 	SkuInventory(String name, int stockLevel, int backorderLevel, int preorderLevel, int stockThreshold, int backorderThreshol, int preorderThreshol, String status, Product product, Platform platform)
	{
		setName(name);
		setStockLevel(stockLevel);
		setBackorderLevel(backorderLevel);
		setPreorderLevel(preorderLevel);
		setStockThreshold(stockThreshold);
		setBackorderThreshol(backorderThreshol);
		setPreorderThreshol(preorderThreshol);
		setStatus(status);
		setProduct(product);
		setPlatform(platform);
	
	}
	
	//Support for changing the property
	
	public void changeProperty(String property, String newValueExpr) {
     	
		if(NAME_PROPERTY.equals(property)){
			changeNameProperty(newValueExpr);
		}
		if(STOCK_LEVEL_PROPERTY.equals(property)){
			changeStockLevelProperty(newValueExpr);
		}
		if(BACKORDER_LEVEL_PROPERTY.equals(property)){
			changeBackorderLevelProperty(newValueExpr);
		}
		if(PREORDER_LEVEL_PROPERTY.equals(property)){
			changePreorderLevelProperty(newValueExpr);
		}
		if(STOCK_THRESHOLD_PROPERTY.equals(property)){
			changeStockThresholdProperty(newValueExpr);
		}
		if(BACKORDER_THRESHOL_PROPERTY.equals(property)){
			changeBackorderThresholProperty(newValueExpr);
		}
		if(PREORDER_THRESHOL_PROPERTY.equals(property)){
			changePreorderThresholProperty(newValueExpr);
		}
		if(STATUS_PROPERTY.equals(property)){
			changeStatusProperty(newValueExpr);
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
			
			
			
	protected void changeStockLevelProperty(String newValueExpr){
		int oldValue = getStockLevel();
		int newValue = parseInt(newValueExpr);
		if(equalsInt(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateStockLevel(newValue);
		this.onChangeProperty(STOCK_LEVEL_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			
	protected void changeBackorderLevelProperty(String newValueExpr){
		int oldValue = getBackorderLevel();
		int newValue = parseInt(newValueExpr);
		if(equalsInt(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateBackorderLevel(newValue);
		this.onChangeProperty(BACKORDER_LEVEL_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			
	protected void changePreorderLevelProperty(String newValueExpr){
		int oldValue = getPreorderLevel();
		int newValue = parseInt(newValueExpr);
		if(equalsInt(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updatePreorderLevel(newValue);
		this.onChangeProperty(PREORDER_LEVEL_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			
	protected void changeStockThresholdProperty(String newValueExpr){
		int oldValue = getStockThreshold();
		int newValue = parseInt(newValueExpr);
		if(equalsInt(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateStockThreshold(newValue);
		this.onChangeProperty(STOCK_THRESHOLD_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			
	protected void changeBackorderThresholProperty(String newValueExpr){
		int oldValue = getBackorderThreshol();
		int newValue = parseInt(newValueExpr);
		if(equalsInt(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateBackorderThreshol(newValue);
		this.onChangeProperty(BACKORDER_THRESHOL_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			
	protected void changePreorderThresholProperty(String newValueExpr){
		int oldValue = getPreorderThreshol();
		int newValue = parseInt(newValueExpr);
		if(equalsInt(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updatePreorderThreshol(newValue);
		this.onChangeProperty(PREORDER_THRESHOL_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			
	protected void changeStatusProperty(String newValueExpr){
		String oldValue = getStatus();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateStatus(newValue);
		this.onChangeProperty(STATUS_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			


	
	
	
	public void setId(String id){
		this.mId = trimString(id);;
	}
	public String getId(){
		return this.mId;
	}
	public SkuInventory updateId(String id){
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
	public SkuInventory updateName(String name){
		this.mName = trimString(name);;
		this.changed = true;
		return this;
	}
	
	
	public void setStockLevel(int stockLevel){
		this.mStockLevel = stockLevel;;
	}
	public int getStockLevel(){
		return this.mStockLevel;
	}
	public SkuInventory updateStockLevel(int stockLevel){
		this.mStockLevel = stockLevel;;
		this.changed = true;
		return this;
	}
	
	
	public void setBackorderLevel(int backorderLevel){
		this.mBackorderLevel = backorderLevel;;
	}
	public int getBackorderLevel(){
		return this.mBackorderLevel;
	}
	public SkuInventory updateBackorderLevel(int backorderLevel){
		this.mBackorderLevel = backorderLevel;;
		this.changed = true;
		return this;
	}
	
	
	public void setPreorderLevel(int preorderLevel){
		this.mPreorderLevel = preorderLevel;;
	}
	public int getPreorderLevel(){
		return this.mPreorderLevel;
	}
	public SkuInventory updatePreorderLevel(int preorderLevel){
		this.mPreorderLevel = preorderLevel;;
		this.changed = true;
		return this;
	}
	
	
	public void setStockThreshold(int stockThreshold){
		this.mStockThreshold = stockThreshold;;
	}
	public int getStockThreshold(){
		return this.mStockThreshold;
	}
	public SkuInventory updateStockThreshold(int stockThreshold){
		this.mStockThreshold = stockThreshold;;
		this.changed = true;
		return this;
	}
	
	
	public void setBackorderThreshol(int backorderThreshol){
		this.mBackorderThreshol = backorderThreshol;;
	}
	public int getBackorderThreshol(){
		return this.mBackorderThreshol;
	}
	public SkuInventory updateBackorderThreshol(int backorderThreshol){
		this.mBackorderThreshol = backorderThreshol;;
		this.changed = true;
		return this;
	}
	
	
	public void setPreorderThreshol(int preorderThreshol){
		this.mPreorderThreshol = preorderThreshol;;
	}
	public int getPreorderThreshol(){
		return this.mPreorderThreshol;
	}
	public SkuInventory updatePreorderThreshol(int preorderThreshol){
		this.mPreorderThreshol = preorderThreshol;;
		this.changed = true;
		return this;
	}
	
	
	public void setStatus(String status){
		this.mStatus = trimString(status);;
	}
	public String getStatus(){
		return this.mStatus;
	}
	public SkuInventory updateStatus(String status){
		this.mStatus = trimString(status);;
		this.changed = true;
		return this;
	}
	
	
	public void setProduct(Product product){
		this.mProduct = product;;
	}
	public Product getProduct(){
		return this.mProduct;
	}
	public SkuInventory updateProduct(Product product){
		this.mProduct = product;;
		this.changed = true;
		return this;
	}
	
	
	public void clearProduct(){
		setProduct ( null );
		this.changed = true;
	}
	
	public void setPlatform(Platform platform){
		this.mPlatform = platform;;
	}
	public Platform getPlatform(){
		return this.mPlatform;
	}
	public SkuInventory updatePlatform(Platform platform){
		this.mPlatform = platform;;
		this.changed = true;
		return this;
	}
	
	
	public void clearPlatform(){
		setPlatform ( null );
		this.changed = true;
	}
	
	public void setVersion(int version){
		this.mVersion = version;;
	}
	public int getVersion(){
		return this.mVersion;
	}
	public SkuInventory updateVersion(int version){
		this.mVersion = version;;
		this.changed = true;
		return this;
	}
	
	

	public void collectRefercences(BaseEntity owner, List<BaseEntity> entityList, String internalType){

		addToEntityList(this, entityList, getProduct(), internalType);
		addToEntityList(this, entityList, getPlatform(), internalType);

		
	}
	
	public List<BaseEntity>  collectRefercencesFromLists(String internalType){
		
		List<BaseEntity> entityList = new ArrayList<BaseEntity>();

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
			

		return listOfList;
	}

	
	public List<KeyValuePair> keyValuePairOf(){
		List<KeyValuePair> result =  super.keyValuePairOf();

		appendKeyValuePair(result, ID_PROPERTY, getId());
		appendKeyValuePair(result, NAME_PROPERTY, getName());
		appendKeyValuePair(result, STOCK_LEVEL_PROPERTY, getStockLevel());
		appendKeyValuePair(result, BACKORDER_LEVEL_PROPERTY, getBackorderLevel());
		appendKeyValuePair(result, PREORDER_LEVEL_PROPERTY, getPreorderLevel());
		appendKeyValuePair(result, STOCK_THRESHOLD_PROPERTY, getStockThreshold());
		appendKeyValuePair(result, BACKORDER_THRESHOL_PROPERTY, getBackorderThreshol());
		appendKeyValuePair(result, PREORDER_THRESHOL_PROPERTY, getPreorderThreshol());
		appendKeyValuePair(result, STATUS_PROPERTY, getStatus());
		appendKeyValuePair(result, PRODUCT_PROPERTY, getProduct());
		appendKeyValuePair(result, PLATFORM_PROPERTY, getPlatform());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());

		
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof SkuInventory){
		
		
			SkuInventory dest =(SkuInventory)baseDest;
		
			dest.setId(getId());
			dest.setName(getName());
			dest.setStockLevel(getStockLevel());
			dest.setBackorderLevel(getBackorderLevel());
			dest.setPreorderLevel(getPreorderLevel());
			dest.setStockThreshold(getStockThreshold());
			dest.setBackorderThreshol(getBackorderThreshol());
			dest.setPreorderThreshol(getPreorderThreshol());
			dest.setStatus(getStatus());
			dest.setProduct(getProduct());
			dest.setPlatform(getPlatform());
			dest.setVersion(getVersion());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("SkuInventory{");
		stringBuilder.append("\tid='"+getId()+"';");
		stringBuilder.append("\tname='"+getName()+"';");
		stringBuilder.append("\tstockLevel='"+getStockLevel()+"';");
		stringBuilder.append("\tbackorderLevel='"+getBackorderLevel()+"';");
		stringBuilder.append("\tpreorderLevel='"+getPreorderLevel()+"';");
		stringBuilder.append("\tstockThreshold='"+getStockThreshold()+"';");
		stringBuilder.append("\tbackorderThreshol='"+getBackorderThreshol()+"';");
		stringBuilder.append("\tpreorderThreshol='"+getPreorderThreshol()+"';");
		stringBuilder.append("\tstatus='"+getStatus()+"';");
		if(getProduct() != null ){
 			stringBuilder.append("\tproduct='Product("+getProduct().getId()+")';");
 		}
		if(getPlatform() != null ){
 			stringBuilder.append("\tplatform='Platform("+getPlatform().getId()+")';");
 		}
		stringBuilder.append("\tversion='"+getVersion()+"';");
		stringBuilder.append("}");

		return stringBuilder.toString();
	}
	
	//provide number calculation function
	
	public void increaseStockLevel(int incStockLevel){
		updateStockLevel(this.mStockLevel +  incStockLevel);
	}
	public void decreaseStockLevel(int decStockLevel){
		updateStockLevel(this.mStockLevel - decStockLevel);
	}
	
	public void increaseBackorderLevel(int incBackorderLevel){
		updateBackorderLevel(this.mBackorderLevel +  incBackorderLevel);
	}
	public void decreaseBackorderLevel(int decBackorderLevel){
		updateBackorderLevel(this.mBackorderLevel - decBackorderLevel);
	}
	
	public void increasePreorderLevel(int incPreorderLevel){
		updatePreorderLevel(this.mPreorderLevel +  incPreorderLevel);
	}
	public void decreasePreorderLevel(int decPreorderLevel){
		updatePreorderLevel(this.mPreorderLevel - decPreorderLevel);
	}
	
	public void increaseStockThreshold(int incStockThreshold){
		updateStockThreshold(this.mStockThreshold +  incStockThreshold);
	}
	public void decreaseStockThreshold(int decStockThreshold){
		updateStockThreshold(this.mStockThreshold - decStockThreshold);
	}
	
	public void increaseBackorderThreshol(int incBackorderThreshol){
		updateBackorderThreshol(this.mBackorderThreshol +  incBackorderThreshol);
	}
	public void decreaseBackorderThreshol(int decBackorderThreshol){
		updateBackorderThreshol(this.mBackorderThreshol - decBackorderThreshol);
	}
	
	public void increasePreorderThreshol(int incPreorderThreshol){
		updatePreorderThreshol(this.mPreorderThreshol +  incPreorderThreshol);
	}
	public void decreasePreorderThreshol(int decPreorderThreshol){
		updatePreorderThreshol(this.mPreorderThreshol - decPreorderThreshol);
	}
	

}

