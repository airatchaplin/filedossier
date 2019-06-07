/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ilb.filedossier.entities;

import java.io.IOException;

/**
 *
 * @author slavb
 */
public interface Store {

    byte[] getContents(String key) throws IOException;

    void setContents(String key, byte[] contents) throws IOException;

    boolean isExist(String key);

//    DossierContext getContext(String key) throws IOException;
//
//    void setContext(String key, DossierContext context) throws IOException;

}