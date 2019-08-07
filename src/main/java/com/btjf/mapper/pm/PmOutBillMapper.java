package com.btjf.mapper.pm;

import com.btjf.model.pm.PmOutBill;
import com.btjf.model.pm.PmOutBillExample;
import com.btjf.vo.PmOutBillListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmOutBillMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_PMout_Bill
     *
     * @mbg.generated
     */
    long countByExample(PmOutBillExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_PMout_Bill
     *
     * @mbg.generated
     */
    int deleteByExample(PmOutBillExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_PMout_Bill
     *
     * @mbg.generated
     */
    int insert(PmOutBill record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_PMout_Bill
     *
     * @mbg.generated
     */
    int insertSelective(PmOutBill record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_PMout_Bill
     *
     * @mbg.generated
     */
    List<PmOutBill> selectByExample(PmOutBillExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_PMout_Bill
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") PmOutBill record, @Param("example") PmOutBillExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_PMout_Bill
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") PmOutBill record, @Param("example") PmOutBillExample example);

    List<PmOutBillListVo> findList(@Param("billNo")String billNo, @Param("orderNo")String orderNo,
                                   @Param("productNo")String productNo);

    PmOutBill getByBillNo(@Param("billNo")String billNo);
}