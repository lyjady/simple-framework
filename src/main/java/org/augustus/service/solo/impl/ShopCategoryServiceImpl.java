package org.augustus.service.solo.impl;

import org.augustus.entity.bo.ShopCategory;
import org.augustus.entity.dto.Result;
import org.augustus.service.solo.ShopCategoryService;
import org.simpleframework.core.annotation.Service;

import java.util.List;

/**
 * @author LinYongJin
 * @date 2020/8/13 10:58
 */
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {

    @Override
    public Result<Boolean> save(ShopCategory shopCategory) {
        return null;
    }

    @Override
    public Result<Boolean> updateById(ShopCategory shopCategory) {
        return null;
    }

    @Override
    public Result<Boolean> deleteById(int shopCategoryId) {
        return null;
    }

    @Override
    public Result<ShopCategory> findById(int shopCategoryId) {
        return null;
    }

    @Override
    public Result<List<ShopCategory>> find(ShopCategory shopCategory, int pageIndex, int pageSize) {
        return null;
    }
}
