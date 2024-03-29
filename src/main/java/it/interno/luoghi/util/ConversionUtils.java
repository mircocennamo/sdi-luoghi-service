package it.interno.luoghi.util;

import it.interno.luoghi.exception.BlobConversionException;
import org.apache.commons.lang3.StringUtils;

import javax.sql.rowset.serial.SerialBlob;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class ConversionUtils {

    public static String convertBlobToString(Blob immagine){

        if(immagine == null)
            return null;

        InputStream is = null;
        ByteArrayOutputStream os = null;

        try{
            is = immagine.getBinaryStream();
            os = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int byteRead = -1;

            while((byteRead = is.read(buffer)) != -1){
                os.write(buffer, 0, byteRead);
            }

        } catch (SQLException | IOException e) {
            throw new BlobConversionException();
        }

        byte[] byteArray = os.toByteArray();
        return new String(byteArray, StandardCharsets.UTF_8);
    }

    public static SerialBlob convertStringToBlob(String immagine){

        if(StringUtils.isBlank(immagine))
            return null;

        SerialBlob sb = null;

        try {
            sb = new SerialBlob(immagine.getBytes());
        } catch (SQLException e) {
            throw new BlobConversionException();
        }

        return sb;
    }

    public static String dateToString(Date date){

        SimpleDateFormat dateFormat = (SimpleDateFormat)SimpleDateFormat.getDateInstance();
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Rome"));
        dateFormat.applyPattern("dd/MM/yyyy");

        return date == null ? null : dateFormat.format(date).replace('/', '-');
    }

    public static String dateToString(LocalDate date, char carattDaSostituire, char carattSostitutivo){

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date == null ? null : date.format(dateFormatter).replace(carattDaSostituire, carattSostitutivo);

    }

    public static String splitHourWithoutSeconds(String hour){
        return StringUtils.isBlank(hour) ? null : hour.split(":")[0] + ":" + hour.split(":")[1];
    }


    public static LocalDate timestampToLocalDate(Timestamp timestamp){
        return timestamp.toLocalDateTime().toLocalDate();
    }

}
