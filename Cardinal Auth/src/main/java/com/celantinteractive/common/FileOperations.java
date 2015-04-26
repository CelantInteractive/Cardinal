/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.celantinteractive.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Joshua
 */
public class FileOperations {
    
    public static byte[] getFileAsByteArray(File file) throws FileNotFoundException, IOException {
        
        FileInputStream fileInputStream;
        byte[] bFile = new byte[(int) file.length()];
        
        fileInputStream = new FileInputStream(file);
        fileInputStream.read(bFile);
        fileInputStream.close();
        
        return bFile;
    }
    
}
