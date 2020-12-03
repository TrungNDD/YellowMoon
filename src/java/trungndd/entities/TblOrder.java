/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungndd.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Vector;
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
@Table(name = "tblOrder", catalog = "YellowMoonDB", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblOrder.findAll", query = "SELECT t FROM TblOrder t")
    , @NamedQuery(name = "TblOrder.findByIdOrder", query = "SELECT t FROM TblOrder t WHERE t.idOrder = :idOrder")
    , @NamedQuery(name = "TblOrder.findByOrderDate", query = "SELECT t FROM TblOrder t WHERE t.orderDate = :orderDate")
    , @NamedQuery(name = "TblOrder.findByFullname", query = "SELECT t FROM TblOrder t WHERE t.fullname = :fullname")
    , @NamedQuery(name = "TblOrder.findByPhone", query = "SELECT t FROM TblOrder t WHERE t.phone = :phone")
    , @NamedQuery(name = "TblOrder.findByShipAddress", query = "SELECT t FROM TblOrder t WHERE t.shipAddress = :shipAddress")
    , @NamedQuery(name = "TblOrder.findByPaymentMethod", query = "SELECT t FROM TblOrder t WHERE t.paymentMethod = :paymentMethod")
    , @NamedQuery(name = "TblOrder.findByPaymentStatus", query = "SELECT t FROM TblOrder t WHERE t.paymentStatus = :paymentStatus")
    , @NamedQuery(name = "TblOrder.findByOrderStatus", query = "SELECT t FROM TblOrder t WHERE t.orderStatus = :orderStatus")
    , @NamedQuery(name = "TblOrder.findByTotal", query = "SELECT t FROM TblOrder t WHERE t.total = :total")
    , @NamedQuery(name = "TblOrder.findByEmail", query = "SELECT t FROM TblOrder t WHERE t.email = :email")})
public class TblOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IdOrder", nullable = false, length = 50)
    private String idOrder;
    @Column(name = "OrderDate", length = 10, insertable = false)
    private String orderDate;
    @Column(name = "Fullname", length = 100)
    private String fullname;
    @Column(name = "Phone", length = 20)
    private String phone;
    @Column(name = "ShipAddress", length = 100)
    private String shipAddress;
    @Column(name = "PaymentMethod", length = 100)
    private String paymentMethod;
    @Column(name = "PaymentStatus", length = 100)
    private String paymentStatus;
    @Column(name = "OrderStatus", length = 100)
    private String orderStatus;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Total", precision = 53)
    private Double total;
    @JoinColumn(name = "Email", referencedColumnName = "Email")
    @ManyToOne
    private Account email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblOrder")
    private Collection<OrderDetails> orderDetailsCollection;

    public TblOrder() {
    }

    public TblOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public TblOrder(String fullname, String phone, String shipAddress, String paymentMethod, Double total, Account email) {
        this.fullname = fullname;
        this.phone = phone;
        this.shipAddress = shipAddress;
        this.paymentMethod = paymentMethod;
        this.total = total;
        this.email = email;
    }
    
    

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Account getEmail() {
        return email;
    }

    public void setEmail(Account email) {
        this.email = email;
    }

    @XmlTransient
    public Collection<OrderDetails> getOrderDetailsCollection() {
        return orderDetailsCollection;
    }

    public void setOrderDetailsCollection(Collection<OrderDetails> orderDetailsCollection) {
        this.orderDetailsCollection = orderDetailsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrder != null ? idOrder.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblOrder)) {
            return false;
        }
        TblOrder other = (TblOrder) object;
        if ((this.idOrder == null && other.idOrder != null) || (this.idOrder != null && !this.idOrder.equals(other.idOrder))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "trungndd.entities.TblOrder[ idOrder=" + idOrder + " ]";
    }

    public void addOrderDetails(OrderDetails orderDetail) {
        if (orderDetailsCollection == null) {
            orderDetailsCollection = new Vector<>();
        }
        
        orderDetail.setTblOrder(this);
        orderDetailsCollection.add(orderDetail);
    }
    
}
