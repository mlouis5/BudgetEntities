/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.budgetentities.pojos;

import com.mac.budgetentities.IdGenerator;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.stereotype.Component;

/**
 *
 * @author Mac
 */
@Component
@Entity
@Table(name = "income", catalog = "finance", schema = "budget")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Income.findAll", query = "SELECT i FROM Income i"),
    @NamedQuery(name = "Income.findByIncomeId", query = "SELECT i FROM Income i WHERE i.incomeId = :incomeId"),
    @NamedQuery(name = "Income.findByIncomeDate", query = "SELECT i FROM Income i WHERE i.incomeDate = :incomeDate")})
public class Income implements Serializable, IdGenerator {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "income_id", nullable = false)
    private Integer incomeId;
    @Basic(optional = false)
    @Column(name = "income_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date incomeDate;
    @JoinColumn(name = "income_paycheck_id", referencedColumnName = "paycheck_id", nullable = false)
    @ManyToOne(optional = false)
    private Paycheck incomePaycheckId;
    @JoinColumn(name = "income_user_id", referencedColumnName = "user_id", nullable = false)
    @ManyToOne(optional = false)
    private User incomeUserId;

    public Income() {
    }

    public Income(Integer incomeId) {
        this.incomeId = incomeId;
    }

    public Income(Integer incomeId, Date incomeDate) {
        this.incomeId = incomeId;
        this.incomeDate = incomeDate;
    }

    public Integer getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(Integer incomeId) {
        this.incomeId = incomeId;
    }

    public Date getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(Date incomeDate) {
        this.incomeDate = incomeDate;
    }

    public Paycheck getIncomePaycheckId() {
        return incomePaycheckId;
    }

    public void setIncomePaycheckId(Paycheck incomePaycheckId) {
        this.incomePaycheckId = incomePaycheckId;
    }

    public User getIncomeUserId() {
        return incomeUserId;
    }

    public void setIncomeUserId(User incomeUserId) {
        this.incomeUserId = incomeUserId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (incomeId != null ? incomeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Income)) {
            return false;
        }
        Income other = (Income) object;
        return Objects.equals(incomeId, other.incomeId);
    }

    @Override
    public String toString() {
        return "com.mac.budgetentities.Income[ incomeId=" + incomeId + " ]";
    }

    @Override
    public String getGeneratedId() {
        return String.valueOf(incomeId);
    }

    @Override
    public void generateId() {
        if (Objects.nonNull(incomeUserId) && Objects.nonNull(incomePaycheckId)) {
            try {
                normalize(this, getClass().getDeclaredFields());
                Field idField = getClass().getDeclaredField("incomeId");
                generateId(idField, String.valueOf(incomeDate.getTime()),
                        incomeUserId.getUserId(), incomePaycheckId.getGeneratedId());
            } catch (IllegalArgumentException | IllegalAccessException 
                    | NoSuchFieldException | SecurityException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
