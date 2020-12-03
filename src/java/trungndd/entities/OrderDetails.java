/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungndd.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "OrderDetails", catalog = "YellowMoonDB", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderDetails.findAll", query = "SELECT o FROM OrderDetails o")
    , @NamedQuery(name = "OrderDetails.findByIdOrder", query = "SELECT o FROM OrderDetails o WHERE o.orderDetailsPK.idOrder = :idOrder")
    , @NamedQuery(name = "OrderDetails.findByIdCake", query = "SELECT o FROM OrderDetails o WHERE o.orderDetailsPK.idCake = :idCake")
    , @NamedQuery(name = "OrderDetails.findByQuantityOrder", query = "SELECT o FROM OrderDetails o WHERE o.quantityOrder = :quantityOrder")
    , @NamedQuery(name = "OrderDetails.findByPrice", query = "SELECT o FROM OrderDetails o WHERE o.price = :price")})
public class OrderDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OrderDetailsPK orderDetailsPK;
    @Column(name = "QuantityOrder")
    private Integer quantityOrder;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Price", precision = 53)
    private Double price;
    @JoinColumn(name = "IdCake", referencedColumnName = "IdCake", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cake cake;
    @JoinColumn(name = "IdOrder", referencedColumnName = "IdOrder", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TblOrder tblOrder;

    public OrderDetails() {
    }

    public OrderDetails(OrderDetailsPK orderDetailsPK) {
        this.orderDetailsPK = orderDetailsPK;
    }

    public OrderDetails(String idOrder, String idCake) {
        this.orderDetailsPK = new OrderDetailsPK(idOrder, idCake);
    }

    public OrderDetails(String idOrder, String idCake, Cake cake, Integer quantityOrder, Double price) {
        this.orderDetailsPK = new OrderDetailsPK(idOrder, idCake);
        this.quantityOrder = quantityOrder;
        this.price = price;
        this.cake = cake;
    }

    public OrderDetailsPK getOrderDetailsPK() {
        return orderDetailsPK;
    }

    public void setOrderDetailsPK(OrderDetailsPK orderDetailsPK) {
        this.orderDetailsPK = orderDetailsPK;
    }

    public Integer getQuantityOrder() {
        return quantityOrder;
    }

    public void setQuantityOrder(Integer quantityOrder) {
        this.quantityOrder = quantityOrder;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Cake getCake() {
        return cake;
    }

    public void setCake(Cake cake) {
        this.cake = cake;
    }

    public TblOrder getTblOrder() {
        return tblOrder;
    }

    public void setTblOrder(TblOrder tblOrder) {
        this.tblOrder = tblOrder;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderDetailsPK != null ? orderDetailsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderDetails)) {
            return false;
        }
        OrderDetails other = (OrderDetails) object;
        if ((this.orderDetailsPK == null && other.orderDetailsPK != null) || (this.orderDetailsPK != null && !this.orderDetailsPK.equals(other.orderDetailsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "trungndd.entities.OrderDetails[ orderDetailsPK=" + orderDetailsPK + " ]";
    }
    
}
