/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungndd.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Admin
 */
@Embeddable
public class OrderDetailsPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "IdOrder", nullable = false, length = 50)
    private String idOrder;
    @Basic(optional = false)
    @Column(name = "IdCake", nullable = false, length = 50)
    private String idCake;

    public OrderDetailsPK() {
    }

    public OrderDetailsPK(String idOrder, String idCake) {
        this.idOrder = idOrder;
        this.idCake = idCake;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public String getIdCake() {
        return idCake;
    }

    public void setIdCake(String idCake) {
        this.idCake = idCake;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrder != null ? idOrder.hashCode() : 0);
        hash += (idCake != null ? idCake.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderDetailsPK)) {
            return false;
        }
        OrderDetailsPK other = (OrderDetailsPK) object;
        if ((this.idOrder == null && other.idOrder != null) || (this.idOrder != null && !this.idOrder.equals(other.idOrder))) {
            return false;
        }
        if ((this.idCake == null && other.idCake != null) || (this.idCake != null && !this.idCake.equals(other.idCake))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "trungndd.entities.OrderDetailsPK[ idOrder=" + idOrder + ", idCake=" + idCake + " ]";
    }
    
}
