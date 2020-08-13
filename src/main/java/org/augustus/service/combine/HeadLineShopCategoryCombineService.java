package org.augustus.service.combine;

import org.augustus.entity.dto.MainPageInfoDto;
import org.augustus.entity.dto.Result;

/**
 * @author LinYongJin
 * @date 2020/8/13 11:03
 */
public interface HeadLineShopCategoryCombineService {

    /**
     * 主页查询
     * @return
     */
    Result<MainPageInfoDto> findMainPageInfo();
}
