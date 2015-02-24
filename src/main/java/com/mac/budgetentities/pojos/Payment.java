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
@Table(name = "payment", catalog = "finance", schema = "budget")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Payment.findAll", query = "SELECT p FROM Payment p"),
    @NamedQuery(name = "Payment.findByPaymentId", query = "SELECT p FROM Payment p WHERE p.paymentId = :paymentId"),
    @NamedQuery(name = "Payment.findByPaymentDueDate", query = "SELECT p FROM Payment p WHERE p.paymentDueDate = :paymentDueDate"),
    @NamedQuery(name = "Payment.findByPaymentFilingDate", query = "SELECT p FROM Payment p WHERE p.paymentFilingDate = :paymentFilingDate"),
    @NamedQuery(name = "Payment.findByPaymentLastNotificationDate", query = "SELECT p FROM Payment p WHERE p.paymentLastNotificationDate = :paymentLastNotificationDate"),
    @NamedQuery(name = "Payment.findByPaymentPaidDate", query = "SELECT p FROM Payment p WHERE p.paymentPaidDate = :paymentPaidDate"),
    @NamedQuery(name = "Payment.findByPaymentQrcodeLocation", query = "SELECT p FROM Payment p WHERE p.paymentQrcodeLocation = :paymentQrcodeLocation")})
public class Payment implements Serializable, IdGenerator {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "payment_id", nullable = false, length = 32)
    private String paymentId;
    @Basic(optional = false)
    @Column(name = "payment_due_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date paymentDueDate;
    @Basic(optional = false)
    @Column(name = "payment_filing_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date paymentFilingDate;
    @Column(name = "payment_last_notification_date")
    @Temporal(TemporalType.DATE)
    private Date paymentLastNotificationDate;
    @Column(name = "payment_paid_date")
    @Temporal(TemporalType.DATE)
    private Date paymentPaidDate;
    @Column(name = "payment_qrcode_location", length = 2147483647)
    private String paymentQrcodeLocation;
    @JoinColumn(name = "payment_bill_id", referencedColumnName = "bill_id", nullable = false)
    @ManyToOne(optional = false)
    private Bill paymentBillId;
    @JoinColumn(name = "payment_user_id", referencedColumnName = "user_id", nullable = false)
    @ManyToOne(optional = false)
    private User paymentUserId;

    public Payment() {
    }

    public Payment(String paymentId) {
        this.paymentId = paymentId;
    }

    public Payment(String paymentId, Date paymentDueDate, Date paymentFilingDate) {
        this.paymentId = paymentId;
        this.paymentDueDate = paymentDueDate;
        this.paymentFilingDate = paymentFilingDate;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Date getPaymentDueDate() {
        return paymentDueDate;
    }

    public void setPaymentDueDate(Date paymentDueDate) {
        this.paymentDueDate = paymentDueDate;
    }

    public Date getPaymentFilingDate() {
        return paymentFilingDate;
    }

    public void setPaymentFilingDate(Date paymentFilingDate) {
        this.paymentFilingDate = paymentFilingDate;
    }

    public Date getPaymentLastNotificationDate() {
        return paymentLastNotificationDate;
    }

    public void setPaymentLastNotificationDate(Date paymentLastNotificationDate) {
        this.paymentLastNotificationDate = paymentLastNotificationDate;
    }

    public Date getPaymentPaidDate() {
        return paymentPaidDate;
    }

    public void setPaymentPaidDate(Date paymentPaidDate) {
        this.paymentPaidDate = paymentPaidDate;
    }

    public String getPaymentQrcodeLocation() {
        return paymentQrcodeLocation;
    }

    public void setPaymentQrcodeLocation(String paymentQrcodeLocation) {
        this.paymentQrcodeLocation = paymentQrcodeLocation;
    }

    public Bill getPaymentBillId() {
        return paymentBillId;
    }

    public void setPaymentBillId(Bill paymentBillId) {
        this.paymentBillId = paymentBillId;
    }

    public User getPaymentUserId() {
        return paymentUserId;
    }

    public void setPaymentUserId(User paymentUserId) {
        this.paymentUserId = paymentUserId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paymentId != null ? paymentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Payment)) {
            return false;
        }
        Payment other = (Payment) object;
        return Objects.equals(paymentId, other.paymentId);
    }

    @Override
    public String toString() {
        return "com.mac.budgetentities.Payment[ paymentId=" + paymentId + " ]";
    }

    @Override
    public String getGeneratedId() {
        return paymentId;
    }

    @Override
    public void generateId() {
        if (Objects.isNull(paymentId) || paymentId.isEmpty()) {
            if (Objects.nonNull(paymentBillId) && Objects.nonNull(paymentUserId)) {
                try {
                    normalize(this, getClass().getDeclaredFields());
                    Field idField = getClass().getDeclaredField("paymentId");
                    generateId(idField, paymentBillId.getBillId(),
                            paymentUserId.getUserId(), String.valueOf(paymentDueDate.getTime()),
                            String.valueOf(paymentFilingDate.getTime()));
                } catch (IllegalArgumentException | IllegalAccessException 
                        | NoSuchFieldException | SecurityException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
