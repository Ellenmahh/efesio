package efesio.com.br.app.rest.json;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

import java.io.IOException;

public class LocalDateModule extends  SimpleModule {
	
	private static final String PATTERN = "yyyy-MM-dd";

	private static final long serialVersionUID = 6515376101564607110L;

	public LocalDateModule(){
		super("LocalDateModule");
		addSerializer(new Serializer(LocalDate.class));
		addDeserializer(LocalDate.class, new Desserializer(LocalDate.class));
	}

	public static class Desserializer extends StdScalarDeserializer<LocalDate> {

		private static final long serialVersionUID = 510123016741535924L;

		protected Desserializer(Class<LocalDate> vc) {
			super(vc);
		}

		@SuppressWarnings("deprecation")
		@Override
		public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			JsonToken t = jp.getCurrentToken();
			if (t == JsonToken.VALUE_NUMBER_INT) {
				return new LocalDate(jp.getLongValue());
			}
			
			if (t == JsonToken.VALUE_STRING) {
				String str = jp.getText().trim();
				if (str.length() == 0) {
					return null;
				}
				return DateTimeFormat.forPattern(PATTERN).parseLocalDate(str);
			}
			throw ctxt.mappingException(getValueClass());
		}
	}

	public static class Serializer extends StdSerializer <LocalDate>{

		private static final long serialVersionUID = 8078782858146092360L;

		protected Serializer(Class<LocalDate> t) {
			super(t);
		}

		@Override
		public void serialize(LocalDate value, JsonGenerator jgen, SerializerProvider provider)throws IOException, JsonGenerationException{
			if (provider.isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)) {
				jgen.writeNumber(value.toDate().getTime());
			} else {
				jgen.writeString(value.toString(PATTERN));
			}
		}

		@Override
		public JsonNode getSchema(SerializerProvider provider, java.lang.reflect.Type typeHint){
			return createSchemaNode(provider.isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)?
					"number" : "string", true);
		}

	}

}

