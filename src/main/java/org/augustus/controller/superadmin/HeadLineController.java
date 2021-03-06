package org.augustus.controller.superadmin;

import demo.annotation.AddressInfo;
import org.augustus.entity.bo.HeadLine;
import org.augustus.entity.dto.Result;
import org.augustus.service.solo.HeadLineService;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.inject.Autowird;

import java.util.List;

/**
 * @author LinYongJin
 * @date 2020/8/13 15:02
 */
@Controller
public class HeadLineController {

    @Autowird
    private HeadLineService headLineService;

    public HeadLineService getHeadLineService() {
        return headLineService;
    }

    public Result<Boolean> save() {
        return headLineService.save(new HeadLine());
    }

    public Result<Boolean> updateById() {
        return headLineService.updateById(new HeadLine());
    }

    public Result<Boolean> deleteById(int headLine) {
        return headLineService.deleteById(headLine);
    }

    public Result<HeadLine> findById(int headLine) {
        return headLineService.findById(headLine);
    }

    public Result<List<HeadLine>> find() {
       return headLineService.find(new HeadLine(), 1, 10);
    }

    public void m() {
        System.out.println("---------");
    }
}
