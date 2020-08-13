package org.augustus.entity.dto;

import lombok.Data;
import org.augustus.entity.bo.HeadLine;
import org.augustus.entity.bo.ShopCategory;

import java.util.List;

/**
 * @author LinYongJin
 * @date 2020/8/13 10:55
 */
@Data
public class MainPageInfoDto {
    private List<HeadLine> headLineList;
    private List<ShopCategory> shopCategoryList;
}
