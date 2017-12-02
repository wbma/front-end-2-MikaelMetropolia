/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ville L
 */
@Entity
@Table(name = "Comp")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comp.findAll", query = "SELECT c FROM Comp c")
    , @NamedQuery(name = "Comp.findById", query = "SELECT c FROM Comp c WHERE c.id = :id")
    , @NamedQuery(name = "Comp.findByTitle", query = "SELECT c FROM Comp c WHERE c.title = :title")
    , @NamedQuery(name = "Comp.findByAuthor", query = "SELECT c FROM Comp c WHERE c.author = :author")
    , @NamedQuery(name = "Comp.findByLength", query = "SELECT c FROM Comp c WHERE c.length = :length")
    , @NamedQuery(name = "Comp.findByYear", query = "SELECT c FROM Comp c WHERE c.year = :year")
    , @NamedQuery(name = "Comp.findByDiff", query = "SELECT c FROM Comp c WHERE c.diff = :diff")
    , @NamedQuery(name = "Comp.findByPages", query = "SELECT c FROM Comp c WHERE c.pages = :pages")
    , @NamedQuery(name = "Comp.findByVideo", query = "SELECT c FROM Comp c WHERE c.video = :video")
    , @NamedQuery(name = "Comp.findByAddtime", query = "SELECT c FROM Comp c WHERE c.addtime = :addtime")
    , @NamedQuery(name = "Comp.findByAdderId", query = "SELECT c FROM Comp c WHERE c.adderid = :adderid")
    , @NamedQuery(name = "Comp.deleteComp", query = "DELETE FROM Comp c WHERE c.id = :id")})
public class Comp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "TITLE")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "AUTHOR")
    private String author;
    @Column(name = "LENGTH")
    private Integer length;
    @Column(name = "YEAR")
    private Integer year;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIFF")
    private int diff;
    @Column(name = "PAGES")
    private Integer pages;
    @Size(max = 255)
    @Column(name = "VIDEO")
    private String video;
    @Lob
    @Column(name = "SHEET")
    private byte[] sheet;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ADDTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addtime;
    
    @ManyToMany(mappedBy = "compCollection") // userids are associated with compids on a 1-to-1 basis(?)
    private Collection<User> userCollection; // userids are associated with compids on a 1-to-1 basis(?)
    
    @ManyToMany(mappedBy = "compCollection1") // why is this here twice??
    private Collection<User> userCollection1; // why is this here twice??
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compid2") // one comp can have many comments
    private Collection<Comment> commentCollection; // one comp can have many comments
    
    @JoinColumn(name = "ADDERID", referencedColumnName = "ID") // Comp has an adderid stat that references id column in 'User' table (foreign key user.id)
    @ManyToOne(optional = false)
    private User adderid2; // it's a User object that CONTAINS the id... But to use int here produces an error and fails to deploy the app!
    
    private int adderid; // this may be highly unwise, but 'adderid' is needed for a User type object (fails to deploy with error otherwise)... 
                          // we should ask Aarne or Patrick about this

    public Comp() {
    }

    public Comp(Integer id) {
        this.id = id;
    }

    public Comp(String title, String author, Integer length, Integer year, int diff, Integer pages, String video, byte[] sheet, Date addtime, int adderid) {
        this.title = title;
        this.author = author;
        this.length = length;
        this.year = year;
        this.diff = diff;
        this.pages = pages;
        this.video = video;
        this.sheet = sheet;
        this.addtime = addtime;
        this.adderid = adderid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public int getDiff() {
        return diff;
    }

    public void setDiff(int diff) {
        this.diff = diff;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public byte[] getSheet() {
        return sheet;
    }

    public void setSheet(byte[] sheet) {
        this.sheet = sheet;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    @XmlTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    @XmlTransient
    public Collection<User> getUserCollection1() {
        return userCollection1;
    }

    public void setUserCollection1(Collection<User> userCollection1) {
        this.userCollection1 = userCollection1;
    }

    @XmlTransient
    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }

    public int getAdderid() {
        return adderid;
    }

    public void setAdderid(int adderid) {
        this.adderid = adderid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comp)) {
            return false;
        }
        Comp other = (Comp) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Comp[ id=" + id + " ]";
    }
    
}
