package org.augustus.service.solo.impl;

import org.augustus.entity.bo.HeadLine;
import org.augustus.entity.bo.ShopCategory;
import org.augustus.entity.dto.MainPageInfoDto;
import org.augustus.entity.dto.Result;
import org.augustus.service.combine.HeadLineShopCategoryCombineService;
import org.augustus.service.solo.HeadLineService;
import org.augustus.service.solo.ShopCategoryService;
import org.simpleframework.core.annotation.Service;

import java.util.List;

/**
 * @author LinYongJin
 * @date 2020/8/13 11:05
 */
@Service
public class HeadLineShopCategoryCombineServiceImpl implements HeadLineShopCategoryCombineService {

    private HeadLineService headLineService;

    private ShopCategoryService shopCategoryService;

    @Override
    public Result<MainPageInfoDto> findMainPageInfo() {
        //1.获取头条列表
        HeadLine headLineCondition = new HeadLine();
        headLineCondition.setEnableStatus(1);
        Result<List<HeadLine>> headLineResult = headLineService.find(headLineCondition, 1, 4);
        //2.获取店铺类别列表
        ShopCategory shopCategoryCondition = new ShopCategory();
        Result<List<ShopCategory>> shopCategoryResult =shopCategoryService.find(shopCategoryCondition, 1, 100);
        //3.合并两者并返回
        return mergeMainPageInfoResult(headLineResult, shopCategoryResult);
    }

    private Result<MainPageInfoDto> mergeMainPageInfoResult(Result<List<HeadLine>> headLineResult, Result<List<ShopCategory>> shopCategoryResult) {
        return  null;
    }
}
