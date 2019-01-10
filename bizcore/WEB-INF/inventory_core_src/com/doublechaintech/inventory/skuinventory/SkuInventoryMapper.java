
package com.doublechaintech.inventory.skuinventory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.inventory.BaseRowMapper;
import com.doublechaintech.inventory.product.Product;
import com.doublechaintech.inventory.platform.Platform;

public class SkuInventoryMapper extends BaseRowMapper<SkuInventory>{
	
	protected SkuInventory internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		SkuInventory skuInventory = getSkuInventory();		
		 		
 		setId(skuInventory, rs, rowNumber); 		
 		setName(skuInventory, rs, rowNumber); 		
 		setStockLevel(skuInventory, rs, rowNumber); 		
 		setBackorderLevel(skuInventory, rs, rowNumber); 		
 		setPreorderLevel(skuInventory, rs, rowNumber); 		
 		setStockThreshold(skuInventory, rs, rowNumber); 		
 		setBackorderThreshol(skuInventory, rs, rowNumber); 		
 		setPreorderThreshol(skuInventory, rs, rowNumber); 		
 		setStatus(skuInventory, rs, rowNumber); 		
 		setProduct(skuInventory, rs, rowNumber); 		
 		setPlatform(skuInventory, rs, rowNumber); 		
 		setVersion(skuInventory, rs, rowNumber);

		return skuInventory;
	}
	
	protected SkuInventory getSkuInventory(){
		return new SkuInventory();
	}		
		
	protected void setId(SkuInventory skuInventory, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String id = rs.getString(SkuInventoryTable.COLUMN_ID);
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		skuInventory.setId(id);
	}
		
	protected void setName(SkuInventory skuInventory, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String name = rs.getString(SkuInventoryTable.COLUMN_NAME);
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		skuInventory.setName(name);
	}
		
	protected void setStockLevel(SkuInventory skuInventory, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Integer stockLevel = rs.getInt(SkuInventoryTable.COLUMN_STOCK_LEVEL);
		if(stockLevel == null){
			//do nothing when nothing found in database
			return;
		}
		
		skuInventory.setStockLevel(stockLevel);
	}
		
	protected void setBackorderLevel(SkuInventory skuInventory, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Integer backorderLevel = rs.getInt(SkuInventoryTable.COLUMN_BACKORDER_LEVEL);
		if(backorderLevel == null){
			//do nothing when nothing found in database
			return;
		}
		
		skuInventory.setBackorderLevel(backorderLevel);
	}
		
	protected void setPreorderLevel(SkuInventory skuInventory, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Integer preorderLevel = rs.getInt(SkuInventoryTable.COLUMN_PREORDER_LEVEL);
		if(preorderLevel == null){
			//do nothing when nothing found in database
			return;
		}
		
		skuInventory.setPreorderLevel(preorderLevel);
	}
		
	protected void setStockThreshold(SkuInventory skuInventory, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Integer stockThreshold = rs.getInt(SkuInventoryTable.COLUMN_STOCK_THRESHOLD);
		if(stockThreshold == null){
			//do nothing when nothing found in database
			return;
		}
		
		skuInventory.setStockThreshold(stockThreshold);
	}
		
	protected void setBackorderThreshol(SkuInventory skuInventory, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Integer backorderThreshol = rs.getInt(SkuInventoryTable.COLUMN_BACKORDER_THRESHOL);
		if(backorderThreshol == null){
			//do nothing when nothing found in database
			return;
		}
		
		skuInventory.setBackorderThreshol(backorderThreshol);
	}
		
	protected void setPreorderThreshol(SkuInventory skuInventory, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Integer preorderThreshol = rs.getInt(SkuInventoryTable.COLUMN_PREORDER_THRESHOL);
		if(preorderThreshol == null){
			//do nothing when nothing found in database
			return;
		}
		
		skuInventory.setPreorderThreshol(preorderThreshol);
	}
		
	protected void setStatus(SkuInventory skuInventory, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String status = rs.getString(SkuInventoryTable.COLUMN_STATUS);
		if(status == null){
			//do nothing when nothing found in database
			return;
		}
		
		skuInventory.setStatus(status);
	}
		 		
 	protected void setProduct(SkuInventory skuInventory, ResultSet rs, int rowNumber) throws SQLException{
 		String productId = rs.getString(SkuInventoryTable.COLUMN_PRODUCT);
 		if( productId == null){
 			return;
 		}
 		if( productId.isEmpty()){
 			return;
 		}
 		Product product = skuInventory.getProduct();
 		if( product != null ){
 			//if the root object 'skuInventory' already have the property, just set the id for it;
 			product.setId(productId);
 			
 			return;
 		}
 		skuInventory.setProduct(createEmptyProduct(productId));
 	}
 	 		
 	protected void setPlatform(SkuInventory skuInventory, ResultSet rs, int rowNumber) throws SQLException{
 		String platformId = rs.getString(SkuInventoryTable.COLUMN_PLATFORM);
 		if( platformId == null){
 			return;
 		}
 		if( platformId.isEmpty()){
 			return;
 		}
 		Platform platform = skuInventory.getPlatform();
 		if( platform != null ){
 			//if the root object 'skuInventory' already have the property, just set the id for it;
 			platform.setId(platformId);
 			
 			return;
 		}
 		skuInventory.setPlatform(createEmptyPlatform(platformId));
 	}
 	
	protected void setVersion(SkuInventory skuInventory, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Integer version = rs.getInt(SkuInventoryTable.COLUMN_VERSION);
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		skuInventory.setVersion(version);
	}
		
		

 	protected Product  createEmptyProduct(String productId){
 		Product product = new Product();
 		product.setId(productId);
 		product.setVersion(Integer.MAX_VALUE);
 		return product;
 	}
 	
 	protected Platform  createEmptyPlatform(String platformId){
 		Platform platform = new Platform();
 		platform.setId(platformId);
 		platform.setVersion(Integer.MAX_VALUE);
 		return platform;
 	}
 	
}


