/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.celantinteractive.updates;

import javax.servlet.http.HttpServletResponse;
import main.java.com.celantinteractive.common.APIEndpoints;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Joshua
 */
@RestController
@RequestMapping(value = APIEndpoints.Categories.UPDATES)
public class UpdatesController {
    
    @RequestMapping(value = APIEndpoints.GET_LATEST_VERSION, method = RequestMethod.GET, consumes = "*/*", produces = "application/json")
    public ResponseUpdate getLatestVersion() {
        
        UpdatesLogic logic = new UpdatesLogic();
        
        return logic.processGetLatestVersion();
    }
    
    @RequestMapping(value = APIEndpoints.GET_INSTALLER, method = RequestMethod.GET, consumes = "*/*", produces = "application/x-msdownload")
    public void getInstaller(HttpServletResponse response) {
        
        UpdatesLogic logic = new UpdatesLogic();
        
        logic.processGetInstaller(response);
    }
    
}
