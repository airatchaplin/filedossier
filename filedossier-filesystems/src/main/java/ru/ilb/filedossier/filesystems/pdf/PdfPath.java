/*
 Copyright 2012-2013 University of Stavanger, Norway

 Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package ru.ilb.filedossier.filesystems.pdf;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchEvent.Modifier;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Denotes a Pdf Path.
 */
public class PdfPath implements Path {

    private static final String NEED_TO_BE_AN_INSTANCE_OF_PDF_PATH = "Need to be an instance of PdfPath";
//    private static final String PARENT_PATH = "..";
    private static final String PATH_SEP = "/";
    private static final String DEFAULT_ROOT_PATH = PATH_SEP;
    private final String path;
    private final PdfFileSystem fileSystem;

    PdfPath(PdfFileSystem fileSystem, String path) {
        this.fileSystem = fileSystem;
        if (path == null) {
            this.path = DEFAULT_ROOT_PATH;
        } else {
            String p = path.trim();
            if (!p.startsWith(PATH_SEP)) {
                this.path = PATH_SEP + p;
            } else {
                this.path = p;
            }
        }
    }

    @Override
    public FileSystem getFileSystem() {
        return this.fileSystem;
    }

    @Override
    public Path getRoot() {
        if (path.equals(DEFAULT_ROOT_PATH)) {
            return this;
        }
        return new PdfPath(this.fileSystem, DEFAULT_ROOT_PATH);
    }

    @Override
    public boolean isAbsolute() {
        return path.length() > 0 && path.startsWith(PATH_SEP);
    }

    /**
     * @deprecated will be removed in future releases
     * @return the path as String
     */
    @Deprecated
    public String getPathString() {
        return this.path;
    }

    @Override
    public int compareTo(Path other) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean endsWith(Path other) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean endsWith(String other) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Path getFileName() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Path getName(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getNameCount() {
        return 0;
    }

    @Override
    public Path getParent() {
        if (path.equals(DEFAULT_ROOT_PATH)) {
            return null;
        }
        String p1 = this.path;
        if (p1.endsWith(PATH_SEP)) {
            p1 = p1.substring(0, p1.length() - 1);
        }
        int lastSep = p1.lastIndexOf(PATH_SEP);
        if (lastSep > 0) {
            String parentString = p1.substring(0, lastSep + 1);
            return new PdfPath(this.fileSystem, parentString);
        }
        return null;
    }

    @Override
    public Iterator<Path> iterator() {
        List<Path> plist = new LinkedList<>();

        for (Path p = this; p != null; p = p.getParent()) {
            plist.add(0, p);
        }
        return plist.iterator();
    }

    @Override
    public Path normalize() {
        try {
            URI normal = new URI(path).normalize();
            return new PdfPath(this.fileSystem, normal.getPath());
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(path, e);
        }
    }

    @Override
    public WatchKey register(WatchService watcher, Kind<?>... events) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public WatchKey register(WatchService watcher, Kind<?>[] events, Modifier... arg2) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Path relativize(Path other) {
        if (!(other instanceof PdfPath)) {
            throw new IllegalArgumentException(NEED_TO_BE_AN_INSTANCE_OF_PDF_PATH);
        }

        if (!other.getFileSystem().equals(this.getFileSystem())) {
            throw new IllegalArgumentException("Wrong File System Type");
        }

        Path base = this;
        PdfPath current = (PdfPath) other;

        String[] bParts = this.path.split(PATH_SEP);
        String[] cParts = current.path.split(PATH_SEP);

        if (bParts.length > 0 && !base.toString().endsWith(PATH_SEP)) {
            bParts = Arrays.copyOf(bParts, bParts.length - 1);
        }

        int i = 0;
        while (i < bParts.length && i < cParts.length && bParts[i].equals(cParts[i])) {
            i++;
        }

        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < (bParts.length - i); j++) {
            sb.append(PATH_SEP);
        }
        for (int j = i; j < cParts.length; j++) {
            if (j != i) {
                sb.append(PATH_SEP);
            }
            sb.append(cParts[j]);
        }
        return new PdfPath(this.fileSystem, sb.toString());
    }

    @Override
    public Path resolve(Path other) {
        if (other.isAbsolute()) {
            return other;
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public Path resolve(String other) {
        if (other.startsWith(PATH_SEP)) {
            throw new IllegalArgumentException(other);
        }
        StringBuilder resolvedPath = new StringBuilder(this.path);
        if (!this.path.endsWith(PATH_SEP)) {
            resolvedPath.append(PATH_SEP);
        }
        resolvedPath.append(other);
        return new PdfPath(this.fileSystem, resolvedPath.toString());
    }

    @Override
    public Path resolveSibling(Path other) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Path resolveSibling(String other) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean startsWith(Path other) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean startsWith(String other) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Path subpath(int beginIndex, int endindex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Path toAbsolutePath() {
        return this;
    }

    @Override
    public File toFile() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Path toRealPath(LinkOption... options) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public URI toUri() {

        return URI.create(path);
        /*
        String scheme = "file";
        String server = fileSystem.getHost();
        int port = fileSystem.getPort();

        URI sardineUri;
        try {
            sardineUri = new URI(scheme, null, server, port, path, null, null);
            return sardineUri;
        } catch(URISyntaxException e) {
            throw new IOError(e);
        }
         */
    }

    @Override
    public String toString() {
        return path;
    }

}
