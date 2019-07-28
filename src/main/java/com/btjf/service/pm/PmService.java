package com.btjf.service.pm;

import com.btjf.common.page.Page;
import com.btjf.mapper.pm.PmMapper;
import com.btjf.model.pm.Pm;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liuyq on 2019/7/28.
 */
@Service
public class PmService {

    @Resource
    private PmMapper pmMapper;

    public Page<Pm> findListPage(String pmNo, String name, String type, Page page) {
        PageHelper.startPage(page.getPage(), page.getRp());
        List<Pm> pmList = pmMapper.findList(pmNo, name, type);
        PageInfo pageInfo = new PageInfo(pmList);
        pageInfo.setList(pmList);
        return new Page<>(pageInfo);
    }

    public Integer deleteByID(List<Integer> integers) {
        return pmMapper.deleteByID(integers);
    }
}
