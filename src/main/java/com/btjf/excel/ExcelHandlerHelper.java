package com.btjf.excel;

import com.btjf.business.common.exception.BusinessException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by yj on 2019/7/31.
 */
@Component
public class ExcelHandlerHelper {

    @Resource
    private PmInExcelHandler pmInExcelHandlerl;

    public BaseExcelHandler getHandler(Integer type) throws BusinessException {
        switch (type){
            case 1:
                //材料导入
                break;
            case 2:
                //入库导入
                return pmInExcelHandlerl;

            default:
                throw new BusinessException("文件类型不存在");
        }

        return null;
    }
}
