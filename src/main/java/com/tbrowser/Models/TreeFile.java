package com.tbrowser.Models;
import javax.persistence.*;

@Entity
@Table(name = "FILES")
public class TreeFile {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

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

    public String getId() {
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
}
