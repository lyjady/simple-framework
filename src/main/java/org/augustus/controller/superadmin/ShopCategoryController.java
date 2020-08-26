package org.augustus.controller.superadmin;

import org.augustus.entity.bo.ShopCategory;
import org.augustus.entity.dto.Result;
import org.augustus.service.solo.ShopCategoryService;
import org.simpleframework.core.annotation.Controller;

import java.util.List;

/**
 * @author LinYongJin
 * @date 2020/8/13 15:03
 */
@Controller
public class ShopCategoryController {

    private ShopCategoryService shopCategoryService;

    public Result<Boolean> save() {
        return shopCategoryService.save(new ShopCategory());
    }

    public Result<Boolean> updateById() {
        return shopCategoryService.updateById(new ShopCategory());
    }

    public Result<Boolean> deleteById(int shopCategory) {
        return shopCategoryService.deleteById(shopCategory);
    }

    public Result<ShopCategory> findById(int shopCategory) {
        return shopCategoryService.findById(shopCategory);
    }

    public Result<List<ShopCategory>> find() {
        return shopCategoryService.find(new ShopCategory(), 1, 10);
    }
}
