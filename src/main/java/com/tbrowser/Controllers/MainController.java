package com.tbrowser.Controllers;


import com.tbrowser.Models.TreeFile;
import com.tbrowser.Services.TreeFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {

    private TreeFileService treeFileService;

    @Autowired(required = true)
    public void setTreeFileService(TreeFileService treeFileService) {
        this.treeFileService = treeFileService;
    }

    @PostConstruct
    public void hello(){
        System.out.println("hello");
    }

    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public List<TreeFile> displayTree(HttpServletRequest request) throws InterruptedException{

        Thread update = new Thread() {
            public void run() {
                try {
                    //Delay 2sec
                    Thread.sleep(2000);

                } catch (InterruptedException ie) {
                    System.err.println(ie.getMessage());
                }
            }
        };
        //Start thread
        update.start();
        update.join();

        String id = request.getParameter("id");
        return treeFileService.getChildren(id);

    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public boolean createNode(HttpServletRequest request){

        String id = request.getParameter("id");
        return treeFileService.removeTreeFile(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public boolean updateNode(HttpServletRequest request){

        String id = request.getParameter("id");
        return treeFileService.removeTreeFile(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public boolean deleteNode(HttpServletRequest request){

        String id = request.getParameter("id");
        return treeFileService.removeTreeFile(id);
    }



}
