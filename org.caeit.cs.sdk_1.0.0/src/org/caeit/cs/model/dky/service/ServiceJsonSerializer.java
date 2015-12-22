package org.caeit.cs.model.dky.service;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

class ServiceJsonSerializer extends JsonSerializer<Service> {

    @Override
    public void serialize(Service o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeObject(o.properties);
    }
}