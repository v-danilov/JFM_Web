package com.tbrowser.Services;

import com.tbrowser.DAO.TreeFileDAO;
import com.tbrowser.Models.TreeFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TreeFileServiceImpl implements TreeFileService {

    private TreeFileDAO treeFileDao;

    public void setTreeFileDao(TreeFileDAO treeFileDao) {this.treeFileDao = treeFileDao;}

    @Transactional
    public void addTreeFile(TreeFile treeFile){this.treeFileDao.addTreeFile(treeFile);}

    @Transactional
    public boolean updateTreeFile(TreeFile treeFile){return this.treeFileDao.updateTreeFile(treeFile);}

    @Transactional
    public boolean removeTreeFile(String id){return this.treeFileDao.removeTreeFile(id);}

    @Transactional
    public TreeFile findById(int id){return this.treeFileDao.findById(id);}

    @Transactional
    public TreeFile findByName(String name, String parent){return treeFileDao.findByName(name, parent);}

    @Transactional
    public void updateTreeFileWithDuplicatesCheck(TreeFile treeFile){
            if(treeFileDao.checkDuplicates(treeFile)){
                System.out.println("Check passed for " + treeFile);
                updateTreeFile(treeFile);
                System.out.println("Update");
            }else{
                System.out.println();
            }
        };

    @Transactional
    public List<TreeFile> getChildren(String id){return  this.treeFileDao.getChildren(id);}

    @Transactional
    public List<TreeFile> listTreeFiles(){return  this.treeFileDao.listTreeFiles();}

}
