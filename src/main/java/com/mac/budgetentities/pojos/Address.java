/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.budgetentities.pojos;

import com.mac.budgetentities.IdGenerator;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.stereotype.Component;

/**
 *
 * @author Mac
 */
@Component
@Entity
@Table(name = "address", catalog = "finance", schema = "budget", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"address_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a"),
    @NamedQuery(name = "Address.findByAddressId", query = "SELECT a FROM Address a WHERE a.addressId = :addressId"),
    @NamedQuery(name = "Address.findByAddressLine1", query = "SELECT a FROM Address a WHERE a.addressLine1 = :addressLine1"),
    @NamedQuery(name = "Address.findByAddressLine2", query = "SELECT a FROM Address a WHERE a.addressLine2 = :addressLine2"),
    @NamedQuery(name = "Address.findByAddressCity", query = "SELECT a FROM Address a WHERE a.addressCity = :addressCity"),
    @NamedQuery(name = "Address.findByAddressState", query = "SELECT a FROM Address a WHERE a.addressState = :addressState"),
    @NamedQuery(name = "Address.findByAddressZipcode", query = "SELECT a FROM Address a WHERE a.addressZipcode = :addressZipcode")})
public class Address implements Serializable, IdGenerator {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "address_id", nullable = false, length = 32)
    private String addressId;
    @Basic(optional = false)
    @Column(name = "address_line_1", nullable = false, length = 256)
    private String addressLine1;
    @Column(name = "address_line_2", length = 256)
    private String addressLine2;
    @Basic(optional = false)
    @Column(name = "address_city", nullable = false, length = 256)
    private String addressCity;
    @Basic(optional = false)
    @Column(name = "address_state", nullable = false, length = 2)
    private String addressState;
    @Basic(optional = false)
    @Column(name = "address_zipcode", nullable = false, length = 5)
    private String addressZipcode;
    @OneToMany(mappedBy = "billMailAddress")
    private List<Bill> billList;
    @OneToMany(mappedBy = "userAddress")
    private List<User> userList;

    public Address() {
    }

    public Address(String addressId) {
        this.addressId = addressId;
    }

    public Address(String addressId, String addressLine1, String addressCity, String addressState, String addressZipcode) {
        this.addressId = addressId;
        this.addressLine1 = addressLine1;
        this.addressCity = addressCity;
        this.addressState = addressState;
        this.addressZipcode = addressZipcode;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressState() {
        return addressState;
    }

    public void setAddressState(String addressState) {
        this.addressState = addressState;
    }

    public String getAddressZipcode() {
        return addressZipcode;
    }

    public void setAddressZipcode(String addressZipcode) {
        this.addressZipcode = addressZipcode;
    }

    @XmlTransient
    public List<Bill> getBillList() {
        return billList;
    }

    public void setBillList(List<Bill> billList) {
        this.billList = billList;
    }

    @XmlTransient
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (addressId != null ? addressId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        return Objects.equals(addressId, other.addressId);
    }

    @Override
    public String toString() {
        return "com.mac.budgetentities.Address[ addressId=" + addressId + " ]";
    }

    @Override
    public String getGeneratedId() {
        return addressId;
    }

    @Override
    public void generateId() {
        try {
            normalize(this, getClass().getDeclaredFields());
            Field idField = getClass().getDeclaredField("addressId");
            this.generateId(idField, addressLine1, addressCity, 
                    addressState, addressZipcode);
        } catch (IllegalArgumentException | IllegalAccessException 
                | NoSuchFieldException | SecurityException ex) {
            Logger.getLogger(Address.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
