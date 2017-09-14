package com.tbrowser.Models;
import javax.persistence.*;

@Entity
@Table(name = "FILES")
public class TreeFile {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "text")
    private String text;

    @Column(name = "parent")
    private String parent;

    @Transient
    private boolean children = true;


    public TreeFile(String text, String parent) {
        this.text = text;
        this.parent = parent;
        this.children = true;
    }

    public TreeFile() {
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getParent() {
        return parent;
    }

    public boolean isChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "TreeFile{" +
                "id='" + id + '\'' +
                ", parent='" + parent + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TreeFile treeFile = (TreeFile) o;

        if (id != treeFile.id) return false;
        if (children != treeFile.children) return false;
        if (!text.equals(treeFile.text)) return false;
        return parent.equals(treeFile.parent);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + text.hashCode();
        result = 31 * result + parent.hashCode();
        result = 31 * result + (children ? 1 : 0);
        return result;
    }
}
