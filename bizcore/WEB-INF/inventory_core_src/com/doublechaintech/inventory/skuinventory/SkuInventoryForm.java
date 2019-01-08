package com.doublechaintech.inventory.skuinventory;
import com.doublechaintech.inventory.BaseForm;
import com.doublechaintech.inventory.genericform.GenericForm;
import com.doublechaintech.inventory.formfield.FormField;
import com.doublechaintech.inventory.formaction.FormAction;
import com.doublechaintech.inventory.formmessage.FormMessage;
import com.doublechaintech.inventory.formfieldmessage.FormFieldMessage;



public class SkuInventoryForm extends BaseForm {
	
	
	public SkuInventoryForm withTitle(String title){
		this.setId(System.currentTimeMillis()+"");
		this.setTitle(title);
		return this;
	}
	
	
	

	public SkuInventoryForm skuInventoryIdField(String parameterName, String initValue){
		FormField field = idFromSkuInventory(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public SkuInventoryForm skuInventoryIdField(String initValue){
		return skuInventoryIdField("skuInventoryId",initValue);
	}
	public SkuInventoryForm skuInventoryIdField(){
		return skuInventoryIdField("skuInventoryId","");
	}


	public SkuInventoryForm stockLevelField(String parameterName, String initValue){
		FormField field = stockLevelFromSkuInventory(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public SkuInventoryForm stockLevelField(String initValue){
		return stockLevelField("stockLevel",initValue);
	}
	public SkuInventoryForm stockLevelField(){
		return stockLevelField("stockLevel","");
	}


	public SkuInventoryForm backorderLevelField(String parameterName, String initValue){
		FormField field = backorderLevelFromSkuInventory(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public SkuInventoryForm backorderLevelField(String initValue){
		return backorderLevelField("backorderLevel",initValue);
	}
	public SkuInventoryForm backorderLevelField(){
		return backorderLevelField("backorderLevel","");
	}


	public SkuInventoryForm preorderLevelField(String parameterName, String initValue){
		FormField field = preorderLevelFromSkuInventory(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public SkuInventoryForm preorderLevelField(String initValue){
		return preorderLevelField("preorderLevel",initValue);
	}
	public SkuInventoryForm preorderLevelField(){
		return preorderLevelField("preorderLevel","");
	}


	public SkuInventoryForm stockThresholdField(String parameterName, String initValue){
		FormField field = stockThresholdFromSkuInventory(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public SkuInventoryForm stockThresholdField(String initValue){
		return stockThresholdField("stockThreshold",initValue);
	}
	public SkuInventoryForm stockThresholdField(){
		return stockThresholdField("stockThreshold","");
	}


	public SkuInventoryForm backorderThresholField(String parameterName, String initValue){
		FormField field = backorderThresholFromSkuInventory(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public SkuInventoryForm backorderThresholField(String initValue){
		return backorderThresholField("backorderThreshol",initValue);
	}
	public SkuInventoryForm backorderThresholField(){
		return backorderThresholField("backorderThreshol","");
	}


	public SkuInventoryForm preorderThresholField(String parameterName, String initValue){
		FormField field = preorderThresholFromSkuInventory(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public SkuInventoryForm preorderThresholField(String initValue){
		return preorderThresholField("preorderThreshol",initValue);
	}
	public SkuInventoryForm preorderThresholField(){
		return preorderThresholField("preorderThreshol","");
	}


	public SkuInventoryForm statusField(String parameterName, String initValue){
		FormField field = statusFromSkuInventory(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public SkuInventoryForm statusField(String initValue){
		return statusField("status",initValue);
	}
	public SkuInventoryForm statusField(){
		return statusField("status","");
	}


	public SkuInventoryForm productIdField(String parameterName, String initValue){
		FormField field = productIdFromSkuInventory(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public SkuInventoryForm productIdField(String initValue){
		return productIdField("productId",initValue);
	}
	public SkuInventoryForm productIdField(){
		return productIdField("productId","");
	}


	public SkuInventoryForm platformIdField(String parameterName, String initValue){
		FormField field = platformIdFromSkuInventory(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public SkuInventoryForm platformIdField(String initValue){
		return platformIdField("platformId",initValue);
	}
	public SkuInventoryForm platformIdField(){
		return platformIdField("platformId","");
	}

	
	


	public SkuInventoryForm productIdFieldOfProduct(String parameterName, String initValue){
		FormField field =  idFromProduct(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public SkuInventoryForm productIdFieldOfProduct(String initValue){
		return productIdFieldOfProduct("productId",initValue);
	}
	public SkuInventoryForm productIdFieldOfProduct(){
		return productIdFieldOfProduct("productId","");
	}


	public SkuInventoryForm nameFieldOfProduct(String parameterName, String initValue){
		FormField field =  nameFromProduct(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public SkuInventoryForm nameFieldOfProduct(String initValue){
		return nameFieldOfProduct("name",initValue);
	}
	public SkuInventoryForm nameFieldOfProduct(){
		return nameFieldOfProduct("name","");
	}


	public SkuInventoryForm introductionFieldOfProduct(String parameterName, String initValue){
		FormField field =  introductionFromProduct(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public SkuInventoryForm introductionFieldOfProduct(String initValue){
		return introductionFieldOfProduct("introduction",initValue);
	}
	public SkuInventoryForm introductionFieldOfProduct(){
		return introductionFieldOfProduct("introduction","");
	}


	public SkuInventoryForm platformIdFieldOfProduct(String parameterName, String initValue){
		FormField field =  platformIdFromProduct(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public SkuInventoryForm platformIdFieldOfProduct(String initValue){
		return platformIdFieldOfProduct("platformId",initValue);
	}
	public SkuInventoryForm platformIdFieldOfProduct(){
		return platformIdFieldOfProduct("platformId","");
	}


	public SkuInventoryForm lastUpdateTimeFieldOfProduct(String parameterName, String initValue){
		FormField field =  lastUpdateTimeFromProduct(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public SkuInventoryForm lastUpdateTimeFieldOfProduct(String initValue){
		return lastUpdateTimeFieldOfProduct("lastUpdateTime",initValue);
	}
	public SkuInventoryForm lastUpdateTimeFieldOfProduct(){
		return lastUpdateTimeFieldOfProduct("lastUpdateTime","");
	}


	public SkuInventoryForm platformIdFieldOfPlatform(String parameterName, String initValue){
		FormField field =  idFromPlatform(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public SkuInventoryForm platformIdFieldOfPlatform(String initValue){
		return platformIdFieldOfPlatform("platformId",initValue);
	}
	public SkuInventoryForm platformIdFieldOfPlatform(){
		return platformIdFieldOfPlatform("platformId","");
	}


	public SkuInventoryForm nameFieldOfPlatform(String parameterName, String initValue){
		FormField field =  nameFromPlatform(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public SkuInventoryForm nameFieldOfPlatform(String initValue){
		return nameFieldOfPlatform("name",initValue);
	}
	public SkuInventoryForm nameFieldOfPlatform(){
		return nameFieldOfPlatform("name","");
	}


	public SkuInventoryForm introductionFieldOfPlatform(String parameterName, String initValue){
		FormField field =  introductionFromPlatform(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public SkuInventoryForm introductionFieldOfPlatform(String initValue){
		return introductionFieldOfPlatform("introduction",initValue);
	}
	public SkuInventoryForm introductionFieldOfPlatform(){
		return introductionFieldOfPlatform("introduction","");
	}


	public SkuInventoryForm currentVersionFieldOfPlatform(String parameterName, String initValue){
		FormField field =  currentVersionFromPlatform(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public SkuInventoryForm currentVersionFieldOfPlatform(String initValue){
		return currentVersionFieldOfPlatform("currentVersion",initValue);
	}
	public SkuInventoryForm currentVersionFieldOfPlatform(){
		return currentVersionFieldOfPlatform("currentVersion","");
	}

	


	

	
 	public SkuInventoryForm transferToAnotherProductAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("transferToAnotherProduct/skuInventoryId/");
		this.addFormAction(action);
		return this;
	}

 	
 	public SkuInventoryForm transferToAnotherPlatformAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("transferToAnotherPlatform/skuInventoryId/");
		this.addFormAction(action);
		return this;
	}

 

	public SkuInventoryForm showAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("genericFormManager/show/title/desc/");
		this.addFormAction(action);
		return this;
	}
	
	
}


