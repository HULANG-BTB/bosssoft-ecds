package com.bosssoft.ecds.entity.vo.incomesortvo;


import com.bosssoft.ecds.constant.PageConstant;

import java.io.Serializable;
import java.util.List;

/**
 * @author: Jianbinbing
 * @Date: 2020/8/7 9:16
 */
public class PageVO<E> implements Serializable {
    /**
     * 序列号
     */
    private static final long serialVersionUID = -4973539948210269347L;
    /**
     * 页码
     */
    private Integer pageNo;
    /**
     * 页面大小
     */
    private Integer pageSize;

    /**
     * 总的记录数
     */
    private Integer totalCount;
    /**
     * 返回的查询结果集
     */
    private List<E> resultList;

    public PageVO() {
        super();
    }

    public PageVO(Integer pageNo, Integer pageSize) {
        super();
        setPageNo(pageNo);
        setPageSize(pageSize);
    }


    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        if (pageNo < 1) {
            pageNo = PageConstant.DEFAULT_PAGE_NO;
        }
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize < PageConstant.DEFAULT_PAGE_SIZE) {
            pageSize = PageConstant.DEFAULT_PAGE_SIZE;
        }
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<E> getResultList() {
        return resultList;
    }

    public void setResultList(List<E> resultList) {
        this.resultList = resultList;
    }


    /**
     * 获得总的页码数量
     *
     * @return
     */
    public int getTotalPage() {
        if (totalCount % pageSize > 0) {
            return totalCount / pageSize + 1;
        } else {
            return totalCount / pageSize;
        }
    }


}
