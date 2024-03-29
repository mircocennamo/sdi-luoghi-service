package it.interno.luoghi.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateDeserializer extends StdDeserializer<LocalDate> {

    private static final long serialVersionUID = -4188060148610032532L;

    @Autowired
    private MessageSource messageSource;

    protected LocalDateDeserializer() {
        super(LocalDate.class);
    }

    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(jsonParser.readValueAs(String.class), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }catch (Exception exception) {
            String message = messageSource.getMessage("errore.campo.formatoNonValido", new Object[]{jsonParser.getCurrentName()}, LocaleContextHolder.getLocale());
            throw new IOException(message);
        }

        return localDate;
    }

}
