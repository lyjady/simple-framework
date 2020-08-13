package org.augustus.service.solo;

import org.augustus.entity.bo.ShopCategory;
import org.augustus.entity.dto.Result;

import java.util.List;

/**
 * @author LinYongJin
 * @date 2020/8/13 10:38
 */
public interface ShopCategoryService {

    Result<Boolean> save(ShopCategory shopCategory);

    Result<Boolean> updateById(ShopCategory shopCategory);

    Result<Boolean> deleteById(int shopCategoryId);

    Result<ShopCategory> findById(int shopCategoryId);

    Result<List<ShopCategory>> find(ShopCategory shopCategory, int pageIndex, int pageSize);
}
