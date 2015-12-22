package org.caeit.cs.model.dky.service;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: caeit
 * Date: 12-12-28
 * Time: 下午5:05
 * To change this template use File | Settings | File Templates.
 */
class ServiceJsonDeserializer extends JsonDeserializer<Service> {
    @Override
    public Service deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Service service = new Service();
        service.properties = jsonParser.readValueAs(new TypeReference<HashMap<String, String>>() {
        });
        return service;
    }
}
