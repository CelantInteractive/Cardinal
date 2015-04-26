/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.celantinteractive.updates;

import static com.sun.org.apache.xerces.internal.impl.XMLEntityManager.DEFAULT_BUFFER_SIZE;
import java.io.File;
import javax.servlet.http.HttpServletResponse;
import main.java.com.celantinteractive.common.FileOperations;
import main.java.com.celantinteractive.common.ResponseFrame.StatusCode;

/**
 *
 * @author Joshua
 */
public class UpdatesLogic {
    
    static String MATCHES_REGEX = "setup-(\\d{10,})\\.exe";
    
    public ResponseUpdate processGetLatestVersion() {
        ResponseUpdate response = new ResponseUpdate();
        
        String resourceFolder = System.getProperty("resourcesFolder") + "/updates/";
        
        File folder = new File(resourceFolder);
        File[] listOfFiles = folder.listFiles();
        
        if(listOfFiles.length > 0) {
            String filename = listOfFiles[0].getName();
            if(filename.matches(MATCHES_REGEX)) {
                String versionNum = filename.replaceFirst(MATCHES_REGEX, "$1");
                if(versionNum.length() >= 10) {
                    response.setLatestVersion(versionNum);
                    response.setStatusCode(StatusCode.OK);
                }
            }
        }
        
        return response;
    }
    
    public void processGetInstaller(HttpServletResponse response) {
        
        try {
            
            String resourceFolder = System.getProperty("resourcesFolder") + "/updates/";
            File folder = new File(resourceFolder);
            File[] listOfFiles = folder.listFiles();
            
            if(listOfFiles.length > 0) {
                byte[] file = FileOperations.getFileAsByteArray(listOfFiles[0]);
                
                response.reset();
                response.setBufferSize(DEFAULT_BUFFER_SIZE);
                response.setContentType("application/x-msdownload");
                response.setHeader( "Content-Disposition", "attachment;filename=" + listOfFiles[0].getName());
                response.getOutputStream().write(file);
                
            }
            
        } catch (Exception e) {
            
        }
    }
}
