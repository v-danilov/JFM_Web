package com.tbrowser.Services;


import com.tbrowser.Models.TreeFile;

import java.util.List;

public interface TreeFileService {

    void addTreeFile(TreeFile treeFile);

    boolean updateTreeFile(TreeFile treeFile);

    boolean removeTreeFile(String id);

    TreeFile findById(int id);

    void renameTreeFile(String treeItemId, String new_name);

    List<TreeFile> getChildren(String id);

    List<TreeFile> listTreeFiles();


}
