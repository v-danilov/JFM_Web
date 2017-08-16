package com.tbrowser.DAO;

import com.tbrowser.Models.TreeFile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.slf4j.Logger;


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
        TreeFile treeFile = (TreeFile) session.load(TreeFile.class, new String(id));
        if (treeFile != null) {
            try {
                session.delete(treeFile);
                logger.info("File successfully deleted. Id of deleted file: " + id);
                return true;
            } catch (Exception e) {
                return false;
            }
    }
        return false;
    };

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
