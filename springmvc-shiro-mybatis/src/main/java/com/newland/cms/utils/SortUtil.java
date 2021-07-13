package com.newland.cms.utils;

import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.newland.cms.domain.FebsConstant;
import com.newland.cms.domain.QueryRequest;

/**
 * 处理排序工具类
 */
@SuppressWarnings("unchecked")
public class SortUtil {
    /**
     * 处理排序（分页情况下） for mybatis-plus
     *
     * @param request           QueryRequest
     * @param page              Page
     * @param defaultSort       默认排序的字段
     * @param defaultOrder      默认排序规则
     * @param camelToUnderscore 是否开启驼峰转下划线
     */
    public static void handlePageSort(QueryRequest request, Page page, String defaultSort, String defaultOrder, boolean camelToUnderscore) {
        page.setCurrent(request.getPageNum());
        page.setSize(request.getPageSize());
        String sortField = request.getSortField();
        if (camelToUnderscore) {
            sortField = FebsUtil.camelToUnderscore(sortField);
            defaultSort = FebsUtil.camelToUnderscore(defaultSort);
        }
        if (!StringUtils.isEmpty(request.getSortField())
                && !StringUtils.isEmpty(request.getSortOrder())
                && !"undefined".equalsIgnoreCase(request.getSortField())
                && !"undefined".equalsIgnoreCase(request.getSortOrder())) {
            if (FebsConstant.ORDER_DESC.equals(request.getSortOrder()))
                page.addOrder(OrderItem.desc(sortField));
//                page.setDesc(sortField);
            else
                page.addOrder(OrderItem.asc(sortField));
//                page.setAsc(sortField);
        } else {
            if (!StringUtils.isEmpty(defaultSort)) {
                if (FebsConstant.ORDER_DESC.equals(defaultOrder))
                    page.addOrder(OrderItem.desc(defaultSort));
//                    page.setDesc(defaultSort);
                else
                    page.addOrder(OrderItem.asc(defaultSort));
//                    page.setAsc(defaultSort);
            }
        }
    }

    /**
     * 处理排序 for mybatis-plus
     *
     * @param request QueryRequest
     * @param page    Page
     */
    public static void handlePageSort(QueryRequest request, Page page) {
        handlePageSort(request, page, null, null, false);
    }

    /**
     * 处理排序 for mybatis-plus
     *
     * @param request           QueryRequest
     * @param page              Page
     * @param camelToUnderscore 是否开启驼峰转下划线
     */
    public static void handlePageSort(QueryRequest request, Page page, boolean camelToUnderscore) {
        handlePageSort(request, page, null, null, camelToUnderscore);
    }

    /**
     * 处理排序 for mybatis-plus
     *
     * @param request           QueryRequest
     * @param wrapper           wrapper
     * @param defaultSort       默认排序的字段
     * @param defaultOrder      默认排序规则
     * @param camelToUnderscore 是否开启驼峰转下划线
     */
    public static void handleWrapperSort(QueryRequest request, QueryWrapper wrapper, String defaultSort, String defaultOrder, boolean camelToUnderscore) {
        String sortField = request.getSortField();
        if (camelToUnderscore) {
            sortField = FebsUtil.camelToUnderscore(sortField);
            defaultSort = FebsUtil.camelToUnderscore(defaultSort);
        }
        if (!StringUtils.isEmpty(request.getSortField())
                && !StringUtils.isEmpty(request.getSortOrder())
                && !"undefined".equalsIgnoreCase(request.getSortField())
                && !"undefined".equalsIgnoreCase(request.getSortOrder())) {
            if (FebsConstant.ORDER_DESC.equals(request.getSortOrder()))
                wrapper.orderByDesc(sortField);
            else
                wrapper.orderByAsc(sortField);
        } else {
            if (!StringUtils.isEmpty(defaultSort)) {
                if (FebsConstant.ORDER_DESC.equals(defaultOrder))
                    wrapper.orderByDesc(defaultSort);
                else
                    wrapper.orderByAsc(defaultSort);
            }
        }
    }

    /**
     * 处理排序 for mybatis-plus
     *
     * @param request QueryRequest
     * @param wrapper wrapper
     */
    public static void handleWrapperSort(QueryRequest request, QueryWrapper wrapper) {
        handleWrapperSort(request, wrapper, null, null, false);
    }

    /**
     * 处理排序 for mybatis-plus
     *
     * @param request           QueryRequest
     * @param wrapper           wrapper
     * @param camelToUnderscore 是否开启驼峰转下划线
     */
    public static void handleWrapperSort(QueryRequest request, QueryWrapper wrapper, boolean camelToUnderscore) {
        handleWrapperSort(request, wrapper, null, null, camelToUnderscore);
    }
}
