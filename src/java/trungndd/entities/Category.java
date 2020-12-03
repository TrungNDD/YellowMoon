/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungndd.entities;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "Category", catalog = "YellowMoonDB", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
    , @NamedQuery(name = "Category.findByIdCategory", query = "SELECT c FROM Category c WHERE c.idCategory = :idCategory")
    , @NamedQuery(name = "Category.findByNameCate", query = "SELECT c FROM Category c WHERE c.nameCate = :nameCate")})
public class Category implements Serializable,JsonSerializer<Category>{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IdCategory", nullable = false, length = 50)
    private String idCategory;
    @Basic(optional = false)
    @Column(name = "NameCate", nullable = false, length = 100)
    private String nameCate;
    @OneToMany(mappedBy = "idCategory")
    private Collection<Cake> cakeCollection;

    public Category() {
    }

    public Category(String idCategory) {
        this.idCategory = idCategory;
    }

    public Category(String idCategory, String nameCate) {
        this.idCategory = idCategory;
        this.nameCate = nameCate;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameCate() {
        return nameCate;
    }

    public void setNameCate(String nameCate) {
        this.nameCate = nameCate;
    }

    @XmlTransient
    public Collection<Cake> getCakeCollection() {
        return cakeCollection;
    }

    public void setCakeCollection(Collection<Cake> cakeCollection) {
        this.cakeCollection = cakeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategory != null ? idCategory.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Category)) {
            return false;
        }
        Category other = (Category) object;
        if ((this.idCategory == null && other.idCategory != null) || (this.idCategory != null && !this.idCategory.equals(other.idCategory))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "trungndd.entities.Category[ idCategory=" + idCategory + " ]";
    }

    @Override
    public JsonElement serialize(Category t, Type type, JsonSerializationContext jsc) {
        return new JsonPrimitive(t.nameCate);
    }
}
