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
package ru.ilb.filedossier.filedossier.usecases.upload;

import ru.ilb.filedossier.context.DossierContextService;
import ru.ilb.filedossier.entities.DossierFile;
import ru.ilb.filedossier.mimetype.MimeTypeUtil;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author kuznetsov_me
 */
@Named
public class UploadDocument extends UploadUseCase {

    private UploadScan uploadScan;

    @Inject
    public UploadDocument(DossierContextService contextService, UploadScan uploadScan) {
        super(contextService);
        this.uploadScan = uploadScan;
    }

    public void upload(File document, DossierFile dossierFile, String contextKey) throws
            IOException {

        String mimeType;
        mimeType = MimeTypeUtil.guessMimeTypeFromFile(document);

        if (mimeType.contains("image/")) {
            uploadScan.upload(document, dossierFile);
        } else {
            dossierFile.getRepresentation().setContents(document);
        }
        setUploadTime(contextKey);
    }
}
