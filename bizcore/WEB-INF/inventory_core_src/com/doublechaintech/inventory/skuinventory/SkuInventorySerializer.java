package com.doublechaintech.inventory.skuinventory;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.doublechaintech.inventory.InventoryObjectPlainCustomSerializer;
public class SkuInventorySerializer extends InventoryObjectPlainCustomSerializer<SkuInventory>{

	@Override
	public void serialize(SkuInventory skuInventory, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, skuInventory, provider);
		
	}
}


