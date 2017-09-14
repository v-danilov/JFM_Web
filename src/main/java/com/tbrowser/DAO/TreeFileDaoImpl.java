package com.tbrowser.DAO;

import com.tbrowser.Models.TreeFile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;


@Repository
public class TreeFileDaoImpl implements TreeFileDAO {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(TreeFileDAO.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void addTreeFile(TreeFile treeFile){
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(treeFile);
        logger.info("File successfully added. File info: " + treeFile);
    };


    public boolean updateTreeFile(TreeFile treeFile){
        Session session = this.sessionFactory.getCurrentSession();
        session.update(treeFile);
        logger.info("File successfully updated. File info: " + treeFile);
        return true;
    };

    public boolean removeTreeFile(String id) {
        Session session = this.sessionFactory.getCurrentSession();
        int int_id = Integer.parseInt(id);
        TreeFile treeFile = (TreeFile) session.load(TreeFile.class, new Integer(int_id));
        if (treeFile != null) {
            try {
                session.createQuery("delete from TreeFile where parent =:p_id").setParameter("p_id",id);
                session.delete(treeFile);
                logger.info("File successfully deleted. Id of deleted file: " + id);
                return true;
            } catch (Exception e) {
                return false;
            }
    }
        return false;
    };

    public void renameTreeFile(String treeItemId, String new_name){
        TreeFile element = findById(Integer.parseInt(treeItemId));
        if(checkDuplicates(element,new_name)){
            element.setText(new_name);
            updateTreeFile(element);
        }
    }

    public boolean checkDuplicates(TreeFile treeFile, String new_name){
        Session session = this.sessionFactory.getCurrentSession();

        //REWORK to int
        /*int number = ((Long)session.createQuery("select count(*) from TreeFile WHERE parent =:p_id and text =:name ")
                .setParameter("p_id", treeFile.getParent())
                .setParameter("name", new_name).uniqueResult()).intValue();*/

        //Solution with list
        List<TreeFile> number = (List<TreeFile>) session.createQuery("from TreeFile WHERE parent =:p_id and text =:name ")
                .setParameter("p_id", treeFile.getParent())
                .setParameter("name", new_name).list();
        if(number.isEmpty()) {
            return true;
        }

        return false;
    }

    public TreeFile findById(int treeItemId){
        Session session = this.sessionFactory.getCurrentSession();
        return  (TreeFile) session.load(TreeFile.class, new Integer(treeItemId));
    }

    public TreeFile findByName(String name, String parent){
        Session session = this.sessionFactory.getCurrentSession();
        TreeFile tf = (TreeFile) session.createQuery("from TreeFile where text =:text_name and parent =:parent_id")
                .setParameter("text_name", name)
                .setParameter("parent_id", parent).uniqueResult();
        return tf;
    }


    public List<TreeFile> getChildren(String treeItemId){

        Session session = this.sessionFactory.getCurrentSession();
        List<TreeFile> childrenFiles = (List<TreeFile>) session.createQuery("from TreeFile where parent =:tiId")
                .setParameter("tiId", treeItemId).list();

        return childrenFiles;
    }

    public List<TreeFile> listTreeFiles(){
        Session session = this.sessionFactory.getCurrentSession();
        List<TreeFile> tFileList = (List<TreeFile>) session.createQuery("from TreeFile").list();

        for(TreeFile file: tFileList){
            logger.info("TreeFile list: " + file);
        }

        return tFileList;
    };
}
