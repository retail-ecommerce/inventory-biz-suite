package com.doublechaintech.inventory.secuser;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.doublechaintech.inventory.InventoryObjectPlainCustomSerializer;
public class SecUserSerializer extends InventoryObjectPlainCustomSerializer<SecUser>{

	@Override
	public void serialize(SecUser secUser, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, secUser, provider);
		
	}
}


