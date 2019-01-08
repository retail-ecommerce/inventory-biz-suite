package com.doublechaintech.inventory;


import com.doublechaintech.inventory.platform.PlatformManager;

import com.doublechaintech.inventory.product.ProductManager;

import com.doublechaintech.inventory.skuinventory.SkuInventoryManager;

import com.doublechaintech.inventory.userdomain.UserDomainManager;

import com.doublechaintech.inventory.userwhitelist.UserWhiteListManager;

import com.doublechaintech.inventory.secuser.SecUserManager;

import com.doublechaintech.inventory.secuserblocking.SecUserBlockingManager;

import com.doublechaintech.inventory.userapp.UserAppManager;

import com.doublechaintech.inventory.listaccess.ListAccessManager;

import com.doublechaintech.inventory.objectaccess.ObjectAccessManager;

import com.doublechaintech.inventory.loginhistory.LoginHistoryManager;

import com.doublechaintech.inventory.genericform.GenericFormManager;

import com.doublechaintech.inventory.formmessage.FormMessageManager;

import com.doublechaintech.inventory.formfieldmessage.FormFieldMessageManager;

import com.doublechaintech.inventory.formfield.FormFieldManager;

import com.doublechaintech.inventory.formaction.FormActionManager;


public class ManagerGroup {

	protected PlatformManager platformManager;

	protected ProductManager productManager;

	protected SkuInventoryManager skuInventoryManager;

	protected UserDomainManager userDomainManager;

	protected UserWhiteListManager userWhiteListManager;

	protected SecUserManager secUserManager;

	protected SecUserBlockingManager secUserBlockingManager;

	protected UserAppManager userAppManager;

	protected ListAccessManager listAccessManager;

	protected ObjectAccessManager objectAccessManager;

	protected LoginHistoryManager loginHistoryManager;

	protected GenericFormManager genericFormManager;

	protected FormMessageManager formMessageManager;

	protected FormFieldMessageManager formFieldMessageManager;

	protected FormFieldManager formFieldManager;

	protected FormActionManager formActionManager;

	

	public PlatformManager getPlatformManager(){
		return this.platformManager;
	}
	public void setPlatformManager(PlatformManager manager){
		this.platformManager = manager;
	}


	public ProductManager getProductManager(){
		return this.productManager;
	}
	public void setProductManager(ProductManager manager){
		this.productManager = manager;
	}


	public SkuInventoryManager getSkuInventoryManager(){
		return this.skuInventoryManager;
	}
	public void setSkuInventoryManager(SkuInventoryManager manager){
		this.skuInventoryManager = manager;
	}


	public UserDomainManager getUserDomainManager(){
		return this.userDomainManager;
	}
	public void setUserDomainManager(UserDomainManager manager){
		this.userDomainManager = manager;
	}


	public UserWhiteListManager getUserWhiteListManager(){
		return this.userWhiteListManager;
	}
	public void setUserWhiteListManager(UserWhiteListManager manager){
		this.userWhiteListManager = manager;
	}


	public SecUserManager getSecUserManager(){
		return this.secUserManager;
	}
	public void setSecUserManager(SecUserManager manager){
		this.secUserManager = manager;
	}


	public SecUserBlockingManager getSecUserBlockingManager(){
		return this.secUserBlockingManager;
	}
	public void setSecUserBlockingManager(SecUserBlockingManager manager){
		this.secUserBlockingManager = manager;
	}


	public UserAppManager getUserAppManager(){
		return this.userAppManager;
	}
	public void setUserAppManager(UserAppManager manager){
		this.userAppManager = manager;
	}


	public ListAccessManager getListAccessManager(){
		return this.listAccessManager;
	}
	public void setListAccessManager(ListAccessManager manager){
		this.listAccessManager = manager;
	}


	public ObjectAccessManager getObjectAccessManager(){
		return this.objectAccessManager;
	}
	public void setObjectAccessManager(ObjectAccessManager manager){
		this.objectAccessManager = manager;
	}


	public LoginHistoryManager getLoginHistoryManager(){
		return this.loginHistoryManager;
	}
	public void setLoginHistoryManager(LoginHistoryManager manager){
		this.loginHistoryManager = manager;
	}


	public GenericFormManager getGenericFormManager(){
		return this.genericFormManager;
	}
	public void setGenericFormManager(GenericFormManager manager){
		this.genericFormManager = manager;
	}


	public FormMessageManager getFormMessageManager(){
		return this.formMessageManager;
	}
	public void setFormMessageManager(FormMessageManager manager){
		this.formMessageManager = manager;
	}


	public FormFieldMessageManager getFormFieldMessageManager(){
		return this.formFieldMessageManager;
	}
	public void setFormFieldMessageManager(FormFieldMessageManager manager){
		this.formFieldMessageManager = manager;
	}


	public FormFieldManager getFormFieldManager(){
		return this.formFieldManager;
	}
	public void setFormFieldManager(FormFieldManager manager){
		this.formFieldManager = manager;
	}


	public FormActionManager getFormActionManager(){
		return this.formActionManager;
	}
	public void setFormActionManager(FormActionManager manager){
		this.formActionManager = manager;
	}


}









