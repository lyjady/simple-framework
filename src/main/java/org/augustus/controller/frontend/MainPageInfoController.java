package org.augustus.controller.frontend;

import org.augustus.entity.dto.MainPageInfoDto;
import org.augustus.entity.dto.Result;
import org.augustus.service.combine.HeadLineShopCategoryCombineService;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.inject.Autowird;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author LinYongJin
 * @date 2020/8/13 15:01
 */
@Controller
public class MainPageInfoController {

    @Autowird
    private HeadLineShopCategoryCombineService headLineShopCategoryCombineService;

    public Result<MainPageInfoDto> findMagePageInfo(HttpServletRequest request, HttpServletResponse response) {
        return headLineShopCategoryCombineService.findMainPageInfo();
    }
}
