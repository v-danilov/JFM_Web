package com.tbrowser.DAO;

import com.tbrowser.Models.TreeFile;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface TreeFileDAO {

    void addTreeFile(TreeFile treeFile);

    boolean updateTreeFile(TreeFile treeFile);

    boolean removeTreeFile(String id);

    //void updateTreeFileWithDuplicatesCheck(TreeFile treeFile);

    boolean checkDuplicates(TreeFile treeFile);

    TreeFile findById(int id);

    TreeFile findByName(String name, String parent);

    List<TreeFile> getChildren(String id);

    List<TreeFile> listTreeFiles();
}
