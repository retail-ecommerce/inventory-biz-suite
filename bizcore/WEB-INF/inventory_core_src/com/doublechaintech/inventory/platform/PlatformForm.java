package com.doublechaintech.inventory.platform;
import com.doublechaintech.inventory.BaseForm;
import com.doublechaintech.inventory.genericform.GenericForm;
import com.doublechaintech.inventory.formfield.FormField;
import com.doublechaintech.inventory.formaction.FormAction;
import com.doublechaintech.inventory.formmessage.FormMessage;
import com.doublechaintech.inventory.formfieldmessage.FormFieldMessage;



public class PlatformForm extends BaseForm {
	
	
	public PlatformForm withTitle(String title){
		this.setId(System.currentTimeMillis()+"");
		this.setTitle(title);
		return this;
	}
	
	
	

	public PlatformForm platformIdField(String parameterName, String initValue){
		FormField field = idFromPlatform(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm platformIdField(String initValue){
		return platformIdField("platformId",initValue);
	}
	public PlatformForm platformIdField(){
		return platformIdField("platformId","");
	}


	public PlatformForm nameField(String parameterName, String initValue){
		FormField field = nameFromPlatform(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm nameField(String initValue){
		return nameField("name",initValue);
	}
	public PlatformForm nameField(){
		return nameField("name","");
	}


	public PlatformForm introductionField(String parameterName, String initValue){
		FormField field = introductionFromPlatform(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm introductionField(String initValue){
		return introductionField("introduction",initValue);
	}
	public PlatformForm introductionField(){
		return introductionField("introduction","");
	}


	public PlatformForm currentVersionField(String parameterName, String initValue){
		FormField field = currentVersionFromPlatform(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm currentVersionField(String initValue){
		return currentVersionField("currentVersion",initValue);
	}
	public PlatformForm currentVersionField(){
		return currentVersionField("currentVersion","");
	}

	
	

	



	public PlatformForm productIdFieldForProduct(String parameterName, String initValue){
		FormField field =  idFromProduct(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm productIdFieldForProduct(String initValue){
		return productIdFieldForProduct("productId",initValue);
	}
	public PlatformForm productIdFieldForProduct(){
		return productIdFieldForProduct("productId","");
	}


	public PlatformForm nameFieldForProduct(String parameterName, String initValue){
		FormField field =  nameFromProduct(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm nameFieldForProduct(String initValue){
		return nameFieldForProduct("name",initValue);
	}
	public PlatformForm nameFieldForProduct(){
		return nameFieldForProduct("name","");
	}


	public PlatformForm introductionFieldForProduct(String parameterName, String initValue){
		FormField field =  introductionFromProduct(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm introductionFieldForProduct(String initValue){
		return introductionFieldForProduct("introduction",initValue);
	}
	public PlatformForm introductionFieldForProduct(){
		return introductionFieldForProduct("introduction","");
	}


	public PlatformForm platformIdFieldForProduct(String parameterName, String initValue){
		FormField field =  platformIdFromProduct(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm platformIdFieldForProduct(String initValue){
		return platformIdFieldForProduct("platformId",initValue);
	}
	public PlatformForm platformIdFieldForProduct(){
		return platformIdFieldForProduct("platformId","");
	}


	public PlatformForm lastUpdateTimeFieldForProduct(String parameterName, String initValue){
		FormField field =  lastUpdateTimeFromProduct(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm lastUpdateTimeFieldForProduct(String initValue){
		return lastUpdateTimeFieldForProduct("lastUpdateTime",initValue);
	}
	public PlatformForm lastUpdateTimeFieldForProduct(){
		return lastUpdateTimeFieldForProduct("lastUpdateTime","");
	}


	public PlatformForm skuInventoryIdFieldForSkuInventory(String parameterName, String initValue){
		FormField field =  idFromSkuInventory(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm skuInventoryIdFieldForSkuInventory(String initValue){
		return skuInventoryIdFieldForSkuInventory("skuInventoryId",initValue);
	}
	public PlatformForm skuInventoryIdFieldForSkuInventory(){
		return skuInventoryIdFieldForSkuInventory("skuInventoryId","");
	}


	public PlatformForm nameFieldForSkuInventory(String parameterName, String initValue){
		FormField field =  nameFromSkuInventory(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm nameFieldForSkuInventory(String initValue){
		return nameFieldForSkuInventory("name",initValue);
	}
	public PlatformForm nameFieldForSkuInventory(){
		return nameFieldForSkuInventory("name","");
	}


	public PlatformForm stockLevelFieldForSkuInventory(String parameterName, String initValue){
		FormField field =  stockLevelFromSkuInventory(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm stockLevelFieldForSkuInventory(String initValue){
		return stockLevelFieldForSkuInventory("stockLevel",initValue);
	}
	public PlatformForm stockLevelFieldForSkuInventory(){
		return stockLevelFieldForSkuInventory("stockLevel","");
	}


	public PlatformForm backorderLevelFieldForSkuInventory(String parameterName, String initValue){
		FormField field =  backorderLevelFromSkuInventory(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm backorderLevelFieldForSkuInventory(String initValue){
		return backorderLevelFieldForSkuInventory("backorderLevel",initValue);
	}
	public PlatformForm backorderLevelFieldForSkuInventory(){
		return backorderLevelFieldForSkuInventory("backorderLevel","");
	}


	public PlatformForm preorderLevelFieldForSkuInventory(String parameterName, String initValue){
		FormField field =  preorderLevelFromSkuInventory(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm preorderLevelFieldForSkuInventory(String initValue){
		return preorderLevelFieldForSkuInventory("preorderLevel",initValue);
	}
	public PlatformForm preorderLevelFieldForSkuInventory(){
		return preorderLevelFieldForSkuInventory("preorderLevel","");
	}


	public PlatformForm stockThresholdFieldForSkuInventory(String parameterName, String initValue){
		FormField field =  stockThresholdFromSkuInventory(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm stockThresholdFieldForSkuInventory(String initValue){
		return stockThresholdFieldForSkuInventory("stockThreshold",initValue);
	}
	public PlatformForm stockThresholdFieldForSkuInventory(){
		return stockThresholdFieldForSkuInventory("stockThreshold","");
	}


	public PlatformForm backorderThresholFieldForSkuInventory(String parameterName, String initValue){
		FormField field =  backorderThresholFromSkuInventory(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm backorderThresholFieldForSkuInventory(String initValue){
		return backorderThresholFieldForSkuInventory("backorderThreshol",initValue);
	}
	public PlatformForm backorderThresholFieldForSkuInventory(){
		return backorderThresholFieldForSkuInventory("backorderThreshol","");
	}


	public PlatformForm preorderThresholFieldForSkuInventory(String parameterName, String initValue){
		FormField field =  preorderThresholFromSkuInventory(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm preorderThresholFieldForSkuInventory(String initValue){
		return preorderThresholFieldForSkuInventory("preorderThreshol",initValue);
	}
	public PlatformForm preorderThresholFieldForSkuInventory(){
		return preorderThresholFieldForSkuInventory("preorderThreshol","");
	}


	public PlatformForm statusFieldForSkuInventory(String parameterName, String initValue){
		FormField field =  statusFromSkuInventory(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm statusFieldForSkuInventory(String initValue){
		return statusFieldForSkuInventory("status",initValue);
	}
	public PlatformForm statusFieldForSkuInventory(){
		return statusFieldForSkuInventory("status","");
	}


	public PlatformForm productIdFieldForSkuInventory(String parameterName, String initValue){
		FormField field =  productIdFromSkuInventory(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm productIdFieldForSkuInventory(String initValue){
		return productIdFieldForSkuInventory("productId",initValue);
	}
	public PlatformForm productIdFieldForSkuInventory(){
		return productIdFieldForSkuInventory("productId","");
	}


	public PlatformForm platformIdFieldForSkuInventory(String parameterName, String initValue){
		FormField field =  platformIdFromSkuInventory(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm platformIdFieldForSkuInventory(String initValue){
		return platformIdFieldForSkuInventory("platformId",initValue);
	}
	public PlatformForm platformIdFieldForSkuInventory(){
		return platformIdFieldForSkuInventory("platformId","");
	}

	



	public PlatformForm showAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("genericFormManager/show/title/desc/");
		this.addFormAction(action);
		return this;
	}
	
	
}


