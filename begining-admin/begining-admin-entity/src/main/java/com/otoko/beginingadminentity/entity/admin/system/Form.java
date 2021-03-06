package com.otoko.beginingadminentity.entity.admin.system;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.otoko.beginingcommon.base.BaseEntity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangjk
 * @description : Form 实体类
 * ---------------------------------
 * @since 2018-11-11
 */
@TableName("s_base_form")
public class Form extends BaseEntity {

    /**
     * 序列化标志
     */
    private static final long serialVersionUID = 1L;


    /**
     * 标题
     */
    @ApiModelProperty("标题")
    @TableField("title")

    private String title;

    /**
     * 数据库表名
     */
    @ApiModelProperty("数据库表名")
    @TableField("collection")

    private String collection;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }


    @Override
    public String toString() {
        return "Form{" +
                ", title=" + title +
                ", collection=" + collection +
                "}";
    }
}
