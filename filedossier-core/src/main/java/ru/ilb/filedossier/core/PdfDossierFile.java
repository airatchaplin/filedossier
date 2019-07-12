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
package ru.ilb.filedossier.core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import ru.ilb.filedossier.context.DossierContextEditor;
import ru.ilb.filedossier.context.DossierContextService;
import ru.ilb.filedossier.entities.Representation;
import ru.ilb.filedossier.entities.Store;

/**
 *
 * @author kuznetsov_me
 */
public class PdfDossierFile extends DossierFileImpl {

    public PdfDossierFile(Store store, String code, String name, boolean required, boolean readonly, boolean hidden,
	    String mediaType, List<Representation> representations, DossierContextService dossierContextService) {
	super(store, code, name, required, readonly, hidden, mediaType, representations, dossierContextService);
	this.representation.setParent(this);
    }

    @Override
    public void setContents(File contentsFile) {
	try {
	    byte[] data = Files.readAllBytes(contentsFile.toPath());

	    if (checkMultipage(contentsFile)) {
		setMultipageContents(data);
	    } else {
		super.setContents(data);
	    }
	} catch (IOException ex) {
	    throw new RuntimeException(ex);
	}
    }

    /**
     * Store page file to a nested FileStore and updates page count
     * 
     * @param data
     */
    private void setMultipageContents(byte[] data) {
	DossierContextEditor contextEditor = new DossierContextEditor(dossierContextService);
	this.store = store.getNestedFileStore(code);

	Optional<String> pageProperty = contextEditor.getProperty("pages", getContextCode());
	int page = pageProperty.isPresent() ? Integer.valueOf(pageProperty.get()) : 0;

	try {
	    store.setContents(Integer.toString(page++), data);
	    contextEditor.putProperty("pages", page++, getContextCode());
	    contextEditor.commit();
	} catch (IOException ex) {
	    throw new RuntimeException(ex);
	}
    }

    @Override
    public byte[] getContents() {
	try {
	    return store.getContents(getStoreFileName());
	} catch (IOException ex) {
	    throw new FileNotExistsException(getStoreFileName());
	}
    }

    /**
     * Returns false if uploaded file isn't image (i.e. not multipage), and true
     * if image.
     * 
     * @param file
     *            uploaded file
     * @return boolean
     */
    private boolean checkMultipage(File file) {
	String mimeType;
	try {
	    mimeType = Files.probeContentType(file.toPath());
	} catch (IOException ex) {
	    throw new FileNotExistsException(file.getName());
	}

	if (mimeType != null && mimeType.contains("image/")) {
	    return true;
	} else {
	    return false;
	}
    }
}