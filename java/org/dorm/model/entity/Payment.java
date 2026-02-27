package org.dorm.model.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 缴费记录实体类
 * 用于存储学生的住宿费缴费信息
 */
public class Payment {
    private String paymentId;    // 缴费记录ID，唯一标识
    private String studentId;    // 关联学号
    private BigDecimal amount;   // 缴费金额
    private Date paymentDate;    // 缴费日期
    private String semester;     // 缴费学期
    private String status;       // 缴费状态：未缴/已缴

    // 构造函数
    public Payment() {}

    public Payment(String paymentId, String studentId, BigDecimal amount,
                  Date paymentDate, String semester, String status) {
        this.paymentId = paymentId;
        this.studentId = studentId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.semester = semester;
        this.status = status;
    }

    // Getter和Setter方法
    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId='" + paymentId + '\'' +
                ", studentId='" + studentId + '\'' +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                ", semester='" + semester + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}