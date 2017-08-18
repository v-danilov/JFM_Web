package com.tbrowser.DAO;

import com.tbrowser.Models.TreeFile;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface TreeFileDAO {

    void addTreeFile(TreeFile treeFile);

    boolean updateTreeFile(TreeFile treeFile);

    boolean removeTreeFile(String id);

    void renameTreeFile(String treeItemId, String new_name);

    TreeFile findById(int id);

    List<TreeFile> getChildren(String id);

    List<TreeFile> listTreeFiles();
}
