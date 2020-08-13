package org.augustus.controller.frontend;

import org.augustus.entity.dto.MainPageInfoDto;
import org.augustus.entity.dto.Result;
import org.augustus.service.combine.HeadLineShopCategoryCombineService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author LinYongJin
 * @date 2020/8/13 15:01
 */
public class MainPageInfoController {

    private HeadLineShopCategoryCombineService headLineShopCategoryCombineService;

    public Result<MainPageInfoDto> findMagePageInfo(HttpServletRequest request, HttpServletResponse response) {
        return headLineShopCategoryCombineService.findMainPageInfo();
    }
}
