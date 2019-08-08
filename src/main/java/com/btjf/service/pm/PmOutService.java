package com.btjf.service.pm;

import com.btjf.common.page.Page;
import com.btjf.mapper.pm.PmOutBillDetailMapper;
import com.btjf.mapper.pm.PmOutBillMapper;
import com.btjf.model.pm.Pm;
import com.btjf.model.pm.PmOutBill;
import com.btjf.model.pm.PmOutBillDetail;
import com.btjf.vo.BillPmVo;
import com.btjf.vo.PmOutBillListVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by liuyq on 2019/7/28.
 */
@Service
public class PmOutService {

    private static final Logger LOGGER = Logger
            .getLogger(PmOutService.class);
    @Resource
    private PmOutBillMapper mapper;
    @Resource
    private PmOutBillDetailMapper pmOutBillDetailMapper;
    @Resource
    private PmService pmService;

    public Page<PmOutBillListVo> findListPage(String billNo, String orderNo, String productNo, Page page) {
        PageHelper.startPage(page.getPage(), page.getRp());
        List<PmOutBillListVo> pmList = mapper.findList(billNo, orderNo, productNo);
        PageInfo pageInfo = new PageInfo(pmList);
        pageInfo.setList(pmList);
        return new Page<>(pageInfo);
    }

    public PmOutBill getByBillNo(String billNo) {
        return mapper.getByBillNo(billNo);
    }

    public List<PmOutBillDetail> getListDetailByBillId(Integer billId) {
        return pmOutBillDetailMapper.getListDetailByBillId(billId);
    }

    public void createBill(PmOutBill pmOutBill, List<BillPmVo> list) {
        mapper.insertSelective(pmOutBill);
        for(int i = 0; i < list.size(); i ++){
            Pm pm = pmService.getByNo(list.get(i).getPmNo());
            PmOutBillDetail pmOutBillDetail = new PmOutBillDetail();
            pmOutBillDetail.setBillId(pmOutBill.getId());
            pmOutBillDetail.setPmId(pm.getId());
            pmOutBillDetail.setPmName(pm.getName());
            pmOutBillDetail.setPmNo(pm.getPmNo());
            pmOutBillDetail.setPerNum(pm.getNum());
            pmOutBillDetail.setPmBatchNo(list.get(i).getPmBatchNo());
            pmOutBillDetail.setNum(BigDecimal.valueOf(list.get(i).getAllowNum()));
            pmOutBillDetail.setBackNum(pmOutBillDetail.getPerNum().subtract(pmOutBillDetail.getNum()));
            pmOutBillDetail.setUnit(pm.getUnit());
            pmOutBillDetail.setRemark(pm.getRemark());
            pm.setIsDelete(0);
            pmOutBillDetail.setCreateTime(new Date());
            pmOutBillDetail.setLastModifyTime(new Date());
            pmOutBillDetail.setOperator(pmOutBill.getOperator());
            pmOutBillDetailMapper.insertSelective(pmOutBillDetail);
            if(pmOutBill.getType() == 1) {//领料单 才减库存
                LOGGER.info("材料编号：" + pm.getPmNo() + "凭单据 " + pmOutBill.getBillNo() + " 出库 " + pmOutBillDetail.getNum());
                Pm pm1 = new Pm();
                pm1.setId(pm.getId());
                pm1.setNum(pmOutBillDetail.getBackNum());
                pm.setLastModifyTime(new Date());
                pmService.updateByID(pm1);
            }
        }

    }


    public List<PmOutBillListVo> findList(String billNo, String orderNo, String productNo) {
        List<PmOutBillListVo> pmList = mapper.findList(billNo, orderNo, productNo);
        return pmList;
    }

//    public List<PmInVo> findList(String pmNo, String name, String type,String startDate,String endDate){
//        List<PmInVo> pmList = pmInMapper.findList(pmNo, name, type,startDate, endDate);
//        return pmList;
//    }
//
//    public void create(PmIn pmIn) {
//        pmInMapper.insertSelective(pmIn);
//    }

}
