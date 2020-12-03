/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungndd.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "Cake", catalog = "YellowMoonDB", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cake.findAll", query = "SELECT c FROM Cake c")
    , @NamedQuery(name = "Cake.findByIdCake", query = "SELECT c FROM Cake c WHERE c.idCake = :idCake")
    , @NamedQuery(name = "Cake.findByNameCake", query = "SELECT c FROM Cake c WHERE c.nameCake = :nameCake")
    , @NamedQuery(name = "Cake.findByImageCake", query = "SELECT c FROM Cake c WHERE c.imageCake = :imageCake")
    , @NamedQuery(name = "Cake.findByPrice", query = "SELECT c FROM Cake c WHERE c.price = :price")
    , @NamedQuery(name = "Cake.findByDescCake", query = "SELECT c FROM Cake c WHERE c.descCake = :descCake")
    , @NamedQuery(name = "Cake.findByCreateDate", query = "SELECT c FROM Cake c WHERE c.createDate = :createDate")
    , @NamedQuery(name = "Cake.findByExpirationDate", query = "SELECT c FROM Cake c WHERE c.expirationDate = :expirationDate")
    , @NamedQuery(name = "Cake.findByLastUpdateDate", query = "SELECT c FROM Cake c WHERE c.lastUpdateDate = :lastUpdateDate")
    , @NamedQuery(name = "Cake.findByLastUpdateUser", query = "SELECT c FROM Cake c WHERE c.lastUpdateUser = :lastUpdateUser")
    , @NamedQuery(name = "Cake.findByQuantity", query = "SELECT c FROM Cake c WHERE c.quantity = :quantity")
    , @NamedQuery(name = "Cake.findByStatus", query = "SELECT c FROM Cake c WHERE c.status = :status")
    ,@NamedQuery(name = "Cake.getAllAvailable", query = "SELECT c FROM Cake c WHERE c.status = 'active' AND c.quantity > 0 ORDER BY c.createDate DESC")})
public class Cake implements Serializable {

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Price", precision = 53)
    private Double price;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IdCake", nullable = false, length = 50)
    private String idCake;
    @Basic(optional = false)
    @Column(name = "NameCake", nullable = false, length = 100)
    private String nameCake;
    @Column(name = "ImageCake", length = 100)
    private String imageCake;
    @Column(name = "DescCake", length = 1000)
    private String descCake;
    @Column(name = "CreateDate", length = 10)
    private String createDate;
    @Column(name = "ExpirationDate", length = 10)
    private String expirationDate;
    @Column(name = "LastUpdateDate", length = 10)
    private String lastUpdateDate;
    @Column(name = "LastUpdateUser", length = 50)
    private String lastUpdateUser;
    @Column(name = "Quantity")
    private Integer quantity;
    @Column(name = "Status", length = 100)
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cake")
    private Collection<OrderDetails> orderDetailsCollection;
    @JoinColumn(name = "IdCategory", referencedColumnName = "IdCategory")
    @ManyToOne
    private Category idCategory;

    public Cake() {
    }

    public Cake(String idCake) {
        this.idCake = idCake;
    }

    public Cake(String idCake, String nameCake) {
        this.idCake = idCake;
        this.nameCake = nameCake;
    }

    public Cake(String idCake, String nameCake, String imageCake, Double price,
            String descCake, String createDate, String expirationDate,
            Integer quantity, String status, Category idCategory) {
        this.price = price;
        this.idCake = idCake;
        this.nameCake = nameCake;
        this.imageCake = imageCake;
        this.descCake = descCake;
        this.createDate = createDate;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
        this.status = status;
        this.idCategory = idCategory;
    }

    public String getIdCake() {
        return idCake;
    }

    public void setIdCake(String idCake) {
        this.idCake = idCake;
    }

    public String getNameCake() {
        return nameCake;
    }

    public void setNameCake(String nameCake) {
        this.nameCake = nameCake;
    }

    public String getImageCake() {
        return imageCake;
    }

    public void setImageCake(String imageCake) {
        this.imageCake = imageCake;
    }

    public String getDescCake() {
        return descCake;
    }

    public void setDescCake(String descCake) {
        this.descCake = descCake;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<OrderDetails> getOrderDetailsCollection() {
        return orderDetailsCollection;
    }

    public void setOrderDetailsCollection(Collection<OrderDetails> orderDetailsCollection) {
        this.orderDetailsCollection = orderDetailsCollection;
    }

    public Category getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Category idCategory) {
        this.idCategory = idCategory;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCake != null ? idCake.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cake)) {
            return false;
        }
        Cake other = (Cake) object;
        if ((this.idCake == null && other.idCake != null) || (this.idCake != null && !this.idCake.equals(other.idCake))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "trungndd.entities.Cake[ idCake=" + idCake + " ]";
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
