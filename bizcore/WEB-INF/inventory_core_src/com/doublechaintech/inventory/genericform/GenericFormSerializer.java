package com.doublechaintech.inventory.genericform;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.doublechaintech.inventory.InventoryObjectPlainCustomSerializer;
public class GenericFormSerializer extends InventoryObjectPlainCustomSerializer<GenericForm>{

	@Override
	public void serialize(GenericForm genericForm, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, genericForm, provider);
		
	}
}


