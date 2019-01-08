
package com.doublechaintech.inventory;
import java.util.Map;

import com.doublechaintech.inventory.platform.Platform;
import com.doublechaintech.inventory.product.Product;
import com.doublechaintech.inventory.skuinventory.SkuInventory;
import com.doublechaintech.inventory.userdomain.UserDomain;
import com.doublechaintech.inventory.userwhitelist.UserWhiteList;
import com.doublechaintech.inventory.secuser.SecUser;
import com.doublechaintech.inventory.secuserblocking.SecUserBlocking;
import com.doublechaintech.inventory.userapp.UserApp;
import com.doublechaintech.inventory.listaccess.ListAccess;
import com.doublechaintech.inventory.objectaccess.ObjectAccess;
import com.doublechaintech.inventory.loginhistory.LoginHistory;
import com.doublechaintech.inventory.genericform.GenericForm;
import com.doublechaintech.inventory.formmessage.FormMessage;
import com.doublechaintech.inventory.formfieldmessage.FormFieldMessage;
import com.doublechaintech.inventory.formfield.FormField;
import com.doublechaintech.inventory.formaction.FormAction;

public class BeanFactoryImpl{


	public Platform createPlatform(Map<String,Object> options){
		return new Platform();
	}


	public Product createProduct(Map<String,Object> options){
		return new Product();
	}


	public SkuInventory createSkuInventory(Map<String,Object> options){
		return new SkuInventory();
	}


	public UserDomain createUserDomain(Map<String,Object> options){
		return new UserDomain();
	}


	public UserWhiteList createUserWhiteList(Map<String,Object> options){
		return new UserWhiteList();
	}


	public SecUser createSecUser(Map<String,Object> options){
		return new SecUser();
	}


	public SecUserBlocking createSecUserBlocking(Map<String,Object> options){
		return new SecUserBlocking();
	}


	public UserApp createUserApp(Map<String,Object> options){
		return new UserApp();
	}


	public ListAccess createListAccess(Map<String,Object> options){
		return new ListAccess();
	}


	public ObjectAccess createObjectAccess(Map<String,Object> options){
		return new ObjectAccess();
	}


	public LoginHistory createLoginHistory(Map<String,Object> options){
		return new LoginHistory();
	}


	public GenericForm createGenericForm(Map<String,Object> options){
		return new GenericForm();
	}


	public FormMessage createFormMessage(Map<String,Object> options){
		return new FormMessage();
	}


	public FormFieldMessage createFormFieldMessage(Map<String,Object> options){
		return new FormFieldMessage();
	}


	public FormField createFormField(Map<String,Object> options){
		return new FormField();
	}


	public FormAction createFormAction(Map<String,Object> options){
		return new FormAction();
	}





}










