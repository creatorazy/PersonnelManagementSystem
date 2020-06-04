package com.pms.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Staff)实体类
 *
 * @author makejava
 * @since 2020-05-15 11:06:41
 */
public class Staff implements Serializable {
    private static final long serialVersionUID = -33717012766523738L;
    
    private Integer id;
    
    private String name;
    
    private String sex;
    
    private String tel;
    
    private String email;
    
    private Integer pId;
    
    private String education;
    
    private Integer dId;
    
    private String contactAddress;
    
    private String idCard;
    
    private Date cDate;

    private Position position;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPId() {
        return pId;
    }

    public void setPId(Integer pId) {
        this.pId = pId;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Integer getDId() {
        return dId;
    }

    public void setDId(Integer dId) {
        this.dId = dId;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Date getCDate() {
        return cDate;
    }

    public void setCDate(Date cDate) {
        this.cDate = cDate;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", pId=" + pId +
                ", education='" + education + '\'' +
                ", dId=" + dId +
                ", contactAddress='" + contactAddress + '\'' +
                ", idCard='" + idCard + '\'' +
                ", cDate=" + cDate +
                ", position=" + position +
                '}';
    }
}