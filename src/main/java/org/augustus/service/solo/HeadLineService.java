package org.augustus.service.solo;

import org.augustus.entity.bo.HeadLine;
import org.augustus.entity.dto.Result;

import java.util.List;

/**
 * @author LinYongJin
 * @date 2020/8/13 10:36
 */
public interface HeadLineService {

    Result<Boolean> save(HeadLine headLine);

    Result<Boolean> updateById(HeadLine headLine);

    Result<Boolean> deleteById(int headLine);

    Result<HeadLine> findById(int headLine);

    Result<List<HeadLine>> find(HeadLine headLine, int pageIndex, int pageSize);
}
