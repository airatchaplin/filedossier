/*
 * Copyright 2019 slavb.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ru.ilb.filedossier.representation;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author slavb
 */
public class OdsXsltRepresentationTest {

    public OdsXsltRepresentationTest() {
    }

    /**
     * Test of processContent method, of class OdsXsltRepresentation.
     * @throws java.net.URISyntaxException
     */
    @Test
    public void testProcessContent() throws URISyntaxException, IOException {
        System.out.println("processContent");
        
        URI stylesheet = getClass().getClassLoader().getResource("fairpriceorder/content.xsl").toURI();
        URI dataUri = getClass().getClassLoader().getResource("fairpriceorder/data.xml").toURI();
        URI template = getClass().getClassLoader().getResource("fairpriceorder/template.ods").toURI();
        


        byte[] source = Files.readAllBytes(Paths.get(dataUri));
        OdsXsltRepresentation instance = new OdsXsltRepresentation("application/vnd.oasis.opendocument.spreadsheet", stylesheet, template);
//        URI testUri = getClass().getClassLoader().getResource("fairpriceorder/test.ods").toURI();
//        byte[] expResult = Files.readAllBytes(Paths.get(testUri));
        byte[] result = instance.processContent(source, "application/xml");
        assertNotNull(result);
//        assertArrayEquals(expResult, result);
    }

}