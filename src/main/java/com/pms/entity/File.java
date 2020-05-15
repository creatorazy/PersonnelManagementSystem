package com.pms.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (File)实体类
 *
 * @author makejava
 * @since 2020-05-15 11:11:46
 */
public class File implements Serializable {
    private static final long serialVersionUID = 767493727159838176L;
    
    private Integer id;
    
    private String title;
    
    private String content;
    
    private Integer aId;
    
    private Date cDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getAId() {
        return aId;
    }

    public void setAId(Integer aId) {
        this.aId = aId;
    }

    public Date getCDate() {
        return cDate;
    }

    public void setCDate(Date cDate) {
        this.cDate = cDate;
    }

}