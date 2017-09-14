package com.tbrowser.Controllers;


import com.tbrowser.Models.TreeFile;
import com.tbrowser.Services.TreeFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
        String name = request.getParameter("name");
        String parent = request.getParameter("parent");
        TreeFile addItem = new TreeFile(name, parent);
        System.out.println(addItem);
        System.out.println(addItem.getId() + " | " + name + " | " + parent);
        return true;
    }

    @RequestMapping(value = "/check_name", method = RequestMethod.GET)
    public boolean check_node_name(HttpServletRequest request){
        String node_name = request.getParameter("name");
        String parent_node = request.getParameter("parent");
        TreeFile checkElement = treeFileService.findByName(node_name,parent_node);
        if(checkElement == null){
            TreeFile add_element = new TreeFile(node_name,parent_node);
            treeFileService.addTreeFile(add_element);
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public void updateNode(HttpServletRequest request){

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        treeFileService.renameTreeFile(id,name);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public boolean deleteNode(HttpServletRequest request){

        String id = request.getParameter("id");
        return treeFileService.removeTreeFile(id);
    }
}
