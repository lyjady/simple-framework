package org.augustus.entity.bo;

import lombok.Data;

import java.util.Date;

/**
 * @author LinYongJin
 * @date 2020/8/10 20:26
 */
@Data
public class HeadLine {

    private Long lineId;

    private String lineName;

    private  String lineLink;

    private String lineImg;

    private Integer priority;

    private Integer enableStatus;

    private Date createTime;

    private Date lastEditTime;
}
