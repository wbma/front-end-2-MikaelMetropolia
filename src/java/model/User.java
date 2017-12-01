
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ville L
 */
@Entity
@Table(name = "User")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
    , @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id")
    , @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email")
    , @NamedQuery(name = "User.findByAlias", query = "SELECT u FROM User u WHERE u.alias = :alias")
    , @NamedQuery(name = "User.findByPw", query = "SELECT u FROM User u WHERE u.pw = :pw")
    , @NamedQuery(name = "User.findByAdmin", query = "SELECT u FROM User u WHERE u.admin = :admin")
    , @NamedQuery(name = "User.deleteUser", query = "DELETE FROM User u WHERE u.id = :id")})
public class User implements Serializable {

    @JoinTable(name = "Favorite", joinColumns = {
        @JoinColumn(name = "USERID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "COMPID", referencedColumnName = "ID")})
    @ManyToMany
    private Collection<Comp> compCollection;
    
    @JoinTable(name = "Likes", joinColumns = {
        @JoinColumn(name = "USERID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "COMPID", referencedColumnName = "ID")})
    @ManyToMany
    private Collection<Comp> compCollection1;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userid2")
    private Collection<Comment> commentCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adderid2")
    private Collection<Comp> compCollection2;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private int id;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")
    @Basic(optional = false)
    @Column(name = "EMAIL", length = 255)
    private String email;
    @Basic(optional = false)
    @Column(name = "ALIAS", length = 255)
    private String alias;
    @Basic(optional = false)
    @Column(name = "PW", length = 255)
    private String pw;
    @Basic(optional = false)
    @Column(name = "ADMIN")
    private int admin;

    public User() {
    }

    public User(String email, String alias, String pw, int admin) {
        this.email = email;
        this.alias = alias;
        this.pw = pw;
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ((Integer)id).hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        return other.id == this.id;
    }

    @Override
    public String toString() {
        return "model.User[ id=" + id + " ]\n" + "model.User[ email=" + email + " ]\n" + "model.User[ alias=" + alias + " ]\n" + "model.User[ pw=" + pw + " ]\n";
    }

    @XmlTransient
    public Collection<Comp> getCompCollection() {
        return compCollection;
    }

    public void setCompCollection(Collection<Comp> compCollection) {
        this.compCollection = compCollection;
    }

    @XmlTransient
    public Collection<Comp> getCompCollection1() {
        return compCollection1;
    }

    public void setCompCollection1(Collection<Comp> compCollection1) {
        this.compCollection1 = compCollection1;
    }

    @XmlTransient
    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }

    @XmlTransient
    public Collection<Comp> getCompCollection2() {
        return compCollection2;
    }

    public void setCompCollection2(Collection<Comp> compCollection2) {
        this.compCollection2 = compCollection2;
    }
    
} // end class
