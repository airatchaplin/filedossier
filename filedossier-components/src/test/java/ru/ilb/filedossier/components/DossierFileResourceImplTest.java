/*
 * Copyright 2019 kuznetsov_me.
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
package ru.ilb.filedossier.components;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;
import org.apache.cxf.jaxrs.provider.json.JsonMapObjectProvider;
import org.apache.cxf.transport.http.HTTPConduit;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import ru.ilb.filedossier.api.DossierFileResource;
import ru.ilb.filedossier.api.DossiersResource;
import ru.ilb.filedossier.view.DossierFileView;
import ru.ilb.filedossier.view.DossierView;

/**
 *
 * @author kuznetsov_me
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DossierFileResourceImplTest {

    private static final String BROWSER_ACCEPT = "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8";

    private DossiersResource resource;

    @LocalServerPort
    private Integer randomPort;

    @Inject
    private JsonMapObjectProvider jsonMapObjectProvider;

    private DossiersResource getDossiersResource() {
        if (resource == null) {
            String port = randomPort.toString();
            String resourceUri = "http://localhost:" + port + "/web";
            System.out.println("resourceUri=" + resourceUri);
            resource = JAXRSClientFactory.create(resourceUri, DossiersResource.class,
                    Arrays.asList(jsonMapObjectProvider));

            configureTimeout(resource, 1000 * 300);
        }
        return resource;

    }

    /**
     * ?????????????????? ???????????????? ?????????? ???????????????? ?? ??????????????????
     *
     * @param resource
     * @param receiveTimeout
     */
    private void configureTimeout(Object resource, int receiveTimeout) {
        WebClient webClient = WebClient.fromClient(WebClient.client(resource));

        HTTPConduit conduit = WebClient.getConfig(webClient).getHttpConduit();
        //conduit.getClient().setConnectionTimeout(1000 * 3);
        conduit.getClient().setReceiveTimeout(receiveTimeout);

    }

    private DossierFileResource getDossierFileResource(String name) {
        return getDossiersResource().getDossierResource("teststorekey", "testmodel", "TEST", "mode1")
                .getDossierFileResource(name);
    }

    /**
     * Test of getDossierResource method, of class DossiersResourceImpl.
     *
     * @throws java.net.URISyntaxException
     */
    @org.junit.Test
    public void testAUploadContents() throws URISyntaxException {

        DossierFileResource fileResource = getDossierFileResource("image1");
        File file = Paths.get(getClass().getClassLoader().getResource("page1.jpg").toURI()).toFile();
        //fileResource.publish();
        List<Attachment> atts = new LinkedList<Attachment>();
        atts.add(new Attachment("root", "image/jpeg", file));
        MultipartBody body = new MultipartBody(atts, true);
        fileResource.publish(body);
    }

    @org.junit.Test
    public void testBUploadContentsMulti() throws URISyntaxException {

        DossierFileResource fileResource = getDossierFileResource("image1");
        File file = Paths.get(getClass().getClassLoader().getResource("page1.jpg").toURI()).toFile();

        List<Attachment> atts = new LinkedList<Attachment>();
        atts.add(new Attachment("root", "image/jpeg", file));
        atts.add(new Attachment("secondFile", "image/jpeg", file));
        atts.add(new Attachment("thirdFile", "image/jpeg", file));
        MultipartBody body = new MultipartBody(atts, true);
        fileResource.publish(body);
    }

    @org.junit.Test
    public void testUpdateContents() throws URISyntaxException, IOException, ParseException, InterruptedException {
        DossiersResource dossiersResource = getDossiersResource();
        DossierView dossierView = dossiersResource
                .getDossierResource("teststorekey", "testmodel", "TEST", "mode1")
                .getDossier(Collections.emptyList());
        DossierFileView dfv = dossierView.getDossierFiles().stream().filter(x -> x.getCode().equals("image1")).findFirst().orElse(null);
        File file = Paths.get(getClass().getClassLoader().getResource("page1.jpg").toURI()).toFile();
        List<Attachment> updateAtts = new LinkedList<Attachment>();
        updateAtts.add(new Attachment("updatefile", "image/jpeg", file));
        DossierFileResource dossierFileResource = dossiersResource.getDossierResource("teststorekey", "testmodel", "TEST", "mode1").getDossierFileResource("image1");
        Thread.sleep(1000);
        dossierFileResource.update(new MultipartBody(updateAtts, true));
        DossiersResource updatedDossiersResource = getDossiersResource();
        DossierView updatedDossierView = updatedDossiersResource
                .getDossierResource("teststorekey", "testmodel", "TEST", "mode1")
                .getDossier(Collections.emptyList());
        DossierFileView updatedDfv = updatedDossierView.getDossierFiles().stream().filter(x -> x.getCode().equals("image1")).findFirst().orElse(null);
        Assert.assertNotEquals(dfv.getLastModified(), updatedDfv.getLastModified());
    }

    @org.junit.Test
    public void testCGetContents() {

        DossierFileResource fileResource = getDossierFileResource("fairpricecalc");
        Response response = fileResource.download(null, null, BROWSER_ACCEPT);
        Assert.assertEquals("application/vnd.oasis.opendocument.spreadsheet",
                response.getMediaType().toString());

        response = fileResource.download(null, null, "application/xml");
        Assert.assertEquals("application/xml",
                response.getMediaType().toString());

        fileResource = getDossierFileResource("jurnals");
        response = fileResource.download(null, null, BROWSER_ACCEPT);
        Assert.assertEquals("application/pdf", response.getMediaType().toString());
    }
}
