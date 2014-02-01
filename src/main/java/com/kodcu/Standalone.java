package com.kodcu;

import se.uglisch.XmlSchemaNsUris;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.InputStream;

/**
 * Created by usta on 01.02.2014.
 */
public class Standalone {

    public static void main(String[] args) {
        try( InputStream UBL = Standalone.class.getResourceAsStream("/sch.xml");
             InputStream fatura = Standalone.class.getResourceAsStream("/xml.xml")){

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XmlSchemaNsUris.SCHEMATRON_NS_URI);
            Schema schema = schemaFactory.newSchema(new StreamSource(UBL));

            schema.newValidator().validate(new StreamSource(fatura));
        } catch (Exception   e) {
            e.printStackTrace();
        }
    }
}
