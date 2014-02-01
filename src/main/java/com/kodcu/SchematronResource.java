package com.kodcu;

import org.xml.sax.SAXException;
import se.uglisch.XmlSchemaNsUris;

import javax.ws.rs.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * Created by usta on 24.12.2013.
 */
@Path("/sch")
public class SchematronResource {

    @POST
    @Consumes("*/*")
    @Produces("*/*;charset=UTF-8")
    public String post(Reader reader)  {

        try( InputStream stream = getClass().getResourceAsStream("/schematron/UBL-TR_Main_Schematron.xml");){

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XmlSchemaNsUris.SCHEMATRON_NS_URI);
            Schema schema = schemaFactory.newSchema(new StreamSource(stream));

            schema.newValidator().validate(new StreamSource(reader));
        } catch (Exception   e) {
           return e.getMessage();
        }


        return "valid";
    }
}
