package com.doublechaintech.inventory.product;
import com.doublechaintech.inventory.BaseForm;
import com.doublechaintech.inventory.genericform.GenericForm;
import com.doublechaintech.inventory.formfield.FormField;
import com.doublechaintech.inventory.formaction.FormAction;
import com.doublechaintech.inventory.formmessage.FormMessage;
import com.doublechaintech.inventory.formfieldmessage.FormFieldMessage;



public class ProductForm extends BaseForm {
	
	
	public ProductForm withTitle(String title){
		this.setId(System.currentTimeMillis()+"");
		this.setTitle(title);
		return this;
	}
	
	
	

	public ProductForm productIdField(String parameterName, String initValue){
		FormField field = idFromProduct(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ProductForm productIdField(String initValue){
		return productIdField("productId",initValue);
	}
	public ProductForm productIdField(){
		return productIdField("productId","");
	}


	public ProductForm nameField(String parameterName, String initValue){
		FormField field = nameFromProduct(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ProductForm nameField(String initValue){
		return nameField("name",initValue);
	}
	public ProductForm nameField(){
		return nameField("name","");
	}


	public ProductForm introductionField(String parameterName, String initValue){
		FormField field = introductionFromProduct(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ProductForm introductionField(String initValue){
		return introductionField("introduction",initValue);
	}
	public ProductForm introductionField(){
		return introductionField("introduction","");
	}


	public ProductForm platformIdField(String parameterName, String initValue){
		FormField field = platformIdFromProduct(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ProductForm platformIdField(String initValue){
		return platformIdField("platformId",initValue);
	}
	public ProductForm platformIdField(){
		return platformIdField("platformId","");
	}


	public ProductForm lastUpdateTimeField(String parameterName, String initValue){
		FormField field = lastUpdateTimeFromProduct(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ProductForm lastUpdateTimeField(String initValue){
		return lastUpdateTimeField("lastUpdateTime",initValue);
	}
	public ProductForm lastUpdateTimeField(){
		return lastUpdateTimeField("lastUpdateTime","");
	}

	
	


	public ProductForm platformIdFieldOfPlatform(String parameterName, String initValue){
		FormField field =  idFromPlatform(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public ProductForm platformIdFieldOfPlatform(String initValue){
		return platformIdFieldOfPlatform("platformId",initValue);
	}
	public ProductForm platformIdFieldOfPlatform(){
		return platformIdFieldOfPlatform("platformId","");
	}


	public ProductForm nameFieldOfPlatform(String parameterName, String initValue){
		FormField field =  nameFromPlatform(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public ProductForm nameFieldOfPlatform(String initValue){
		return nameFieldOfPlatform("name",initValue);
	}
	public ProductForm nameFieldOfPlatform(){
		return nameFieldOfPlatform("name","");
	}


	public ProductForm introductionFieldOfPlatform(String parameterName, String initValue){
		FormField field =  introductionFromPlatform(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public ProductForm introductionFieldOfPlatform(String initValue){
		return introductionFieldOfPlatform("introduction",initValue);
	}
	public ProductForm introductionFieldOfPlatform(){
		return introductionFieldOfPlatform("introduction","");
	}


	public ProductForm currentVersionFieldOfPlatform(String parameterName, String initValue){
		FormField field =  currentVersionFromPlatform(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public ProductForm currentVersionFieldOfPlatform(String initValue){
		return currentVersionFieldOfPlatform("currentVersion",initValue);
	}
	public ProductForm currentVersionFieldOfPlatform(){
		return currentVersionFieldOfPlatform("currentVersion","");
	}

	



	public ProductForm skuInventoryIdFieldForSkuInventory(String parameterName, String initValue){
		FormField field =  idFromSkuInventory(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ProductForm skuInventoryIdFieldForSkuInventory(String initValue){
		return skuInventoryIdFieldForSkuInventory("skuInventoryId",initValue);
	}
	public ProductForm skuInventoryIdFieldForSkuInventory(){
		return skuInventoryIdFieldForSkuInventory("skuInventoryId","");
	}


	public ProductForm nameFieldForSkuInventory(String parameterName, String initValue){
		FormField field =  nameFromSkuInventory(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ProductForm nameFieldForSkuInventory(String initValue){
		return nameFieldForSkuInventory("name",initValue);
	}
	public ProductForm nameFieldForSkuInventory(){
		return nameFieldForSkuInventory("name","");
	}


	public ProductForm stockLevelFieldForSkuInventory(String parameterName, String initValue){
		FormField field =  stockLevelFromSkuInventory(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ProductForm stockLevelFieldForSkuInventory(String initValue){
		return stockLevelFieldForSkuInventory("stockLevel",initValue);
	}
	public ProductForm stockLevelFieldForSkuInventory(){
		return stockLevelFieldForSkuInventory("stockLevel","");
	}


	public ProductForm backorderLevelFieldForSkuInventory(String parameterName, String initValue){
		FormField field =  backorderLevelFromSkuInventory(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ProductForm backorderLevelFieldForSkuInventory(String initValue){
		return backorderLevelFieldForSkuInventory("backorderLevel",initValue);
	}
	public ProductForm backorderLevelFieldForSkuInventory(){
		return backorderLevelFieldForSkuInventory("backorderLevel","");
	}


	public ProductForm preorderLevelFieldForSkuInventory(String parameterName, String initValue){
		FormField field =  preorderLevelFromSkuInventory(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ProductForm preorderLevelFieldForSkuInventory(String initValue){
		return preorderLevelFieldForSkuInventory("preorderLevel",initValue);
	}
	public ProductForm preorderLevelFieldForSkuInventory(){
		return preorderLevelFieldForSkuInventory("preorderLevel","");
	}


	public ProductForm stockThresholdFieldForSkuInventory(String parameterName, String initValue){
		FormField field =  stockThresholdFromSkuInventory(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ProductForm stockThresholdFieldForSkuInventory(String initValue){
		return stockThresholdFieldForSkuInventory("stockThreshold",initValue);
	}
	public ProductForm stockThresholdFieldForSkuInventory(){
		return stockThresholdFieldForSkuInventory("stockThreshold","");
	}


	public ProductForm backorderThresholFieldForSkuInventory(String parameterName, String initValue){
		FormField field =  backorderThresholFromSkuInventory(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ProductForm backorderThresholFieldForSkuInventory(String initValue){
		return backorderThresholFieldForSkuInventory("backorderThreshol",initValue);
	}
	public ProductForm backorderThresholFieldForSkuInventory(){
		return backorderThresholFieldForSkuInventory("backorderThreshol","");
	}


	public ProductForm preorderThresholFieldForSkuInventory(String parameterName, String initValue){
		FormField field =  preorderThresholFromSkuInventory(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ProductForm preorderThresholFieldForSkuInventory(String initValue){
		return preorderThresholFieldForSkuInventory("preorderThreshol",initValue);
	}
	public ProductForm preorderThresholFieldForSkuInventory(){
		return preorderThresholFieldForSkuInventory("preorderThreshol","");
	}


	public ProductForm statusFieldForSkuInventory(String parameterName, String initValue){
		FormField field =  statusFromSkuInventory(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ProductForm statusFieldForSkuInventory(String initValue){
		return statusFieldForSkuInventory("status",initValue);
	}
	public ProductForm statusFieldForSkuInventory(){
		return statusFieldForSkuInventory("status","");
	}


	public ProductForm productIdFieldForSkuInventory(String parameterName, String initValue){
		FormField field =  productIdFromSkuInventory(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ProductForm productIdFieldForSkuInventory(String initValue){
		return productIdFieldForSkuInventory("productId",initValue);
	}
	public ProductForm productIdFieldForSkuInventory(){
		return productIdFieldForSkuInventory("productId","");
	}


	public ProductForm platformIdFieldForSkuInventory(String parameterName, String initValue){
		FormField field =  platformIdFromSkuInventory(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ProductForm platformIdFieldForSkuInventory(String initValue){
		return platformIdFieldForSkuInventory("platformId",initValue);
	}
	public ProductForm platformIdFieldForSkuInventory(){
		return platformIdFieldForSkuInventory("platformId","");
	}

	

	
 	public ProductForm transferToAnotherPlatformAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("transferToAnotherPlatform/productId/");
		this.addFormAction(action);
		return this;
	}

 

	public ProductForm showAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("genericFormManager/show/title/desc/");
		this.addFormAction(action);
		return this;
	}
	
	
}


