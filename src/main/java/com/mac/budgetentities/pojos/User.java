/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.budgetentities.pojos;

import com.mac.budgetentities.Addressable;
import com.mac.budgetentities.IdGenerator;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Table(name = "user", catalog = "finance", schema = "budget", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"user_id"}),
    @UniqueConstraint(columnNames = {"user_email"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUserId", query = "SELECT u FROM User u WHERE u.userId = :userId"),
    @NamedQuery(name = "User.findByUserFname", query = "SELECT u FROM User u WHERE u.userFname = :userFname"),
    @NamedQuery(name = "User.findByUserLname", query = "SELECT u FROM User u WHERE u.userLname = :userLname"),
    @NamedQuery(name = "User.findByUserPhone", query = "SELECT u FROM User u WHERE u.userPhone = :userPhone"),
    @NamedQuery(name = "User.findByUserEmail", query = "SELECT u FROM User u WHERE u.userEmail = :userEmail"),
    @NamedQuery(name = "User.findByUserPreferredContact", query = "SELECT u FROM User u WHERE u.userPreferredContact = :userPreferredContact")})
public class User implements Serializable, IdGenerator, Addressable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "user_id", nullable = false, length = 32)
    private String userId;
    @Basic(optional = false)
    @Column(name = "user_fname", nullable = false, length = 128)
    private String userFname;
    @Basic(optional = false)
    @Column(name = "user_lname", nullable = false, length = 128)
    private String userLname;
    @Column(name = "user_phone", length = 10)
    private String userPhone;
    @Basic(optional = false)
    @Column(name = "user_email", nullable = false, length = 2147483647)
    private String userEmail;
    @Column(name = "user_preferred_contact", length = 5)
    private String userPreferredContact;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "incomeUserId")
    private List<Income> incomeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "billOwner")
    private List<Bill> billList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paymentUserId")
    private List<Payment> paymentList;
    @JoinColumn(name = "user_address", referencedColumnName = "address_id")
    @ManyToOne
    private Address userAddress;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paycheckOwner")
    private List<Paycheck> paycheckList;

    public User() {
    }

    public User(String userId) {
        this.userId = userId;
    }

    public User(String userId, String userFname, String userLname, String userEmail) {
        this.userId = userId;
        this.userFname = userFname;
        this.userLname = userLname;
        this.userEmail = userEmail;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserFname() {
        return userFname;
    }

    public void setUserFname(String userFname) {
        this.userFname = userFname;
    }

    public String getUserLname() {
        return userLname;
    }

    public void setUserLname(String userLname) {
        this.userLname = userLname;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPreferredContact() {
        return userPreferredContact;
    }

    public void setUserPreferredContact(String userPreferredContact) {
        this.userPreferredContact = userPreferredContact;
    }

    @XmlTransient
    public List<Income> getIncomeList() {
        return incomeList;
    }

    public void setIncomeList(List<Income> incomeList) {
        this.incomeList = incomeList;
    }

    @XmlTransient
    public List<Bill> getBillList() {
        return billList;
    }

    public void setBillList(List<Bill> billList) {
        this.billList = billList;
    }

    @XmlTransient
    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }

    public Address getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(Address userAddress) {
        this.userAddress = userAddress;
    }

    @XmlTransient
    public List<Paycheck> getPaycheckList() {
        return paycheckList;
    }

    public void setPaycheckList(List<Paycheck> paycheckList) {
        this.paycheckList = paycheckList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        return Objects.equals(userId, other.userId);
    }

    @Override
    public String toString() {
        return "com.mac.budgetentities.User[ userId=" + userId + " ]";
    }

    @Override
    public String getGeneratedId() {
        return userId;
    }

    @Override
    public void setAddress(Address address) {
        this.setUserAddress(address);
    }

    @Override
    public Address getAddress() {
        return this.getUserAddress();
    }

    @Override
    public void generateId() {
        try {
            normalize(this, getClass().getDeclaredFields());
            Field idField = getClass().getDeclaredField("userId");
            generateId(idField, userFname, userLname, userEmail);
        } catch (IllegalArgumentException | IllegalAccessException 
                | NoSuchFieldException | SecurityException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
