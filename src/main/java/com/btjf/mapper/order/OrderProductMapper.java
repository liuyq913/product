package com.btjf.mapper.order;

import com.btjf.model.order.OrderProduct;
import com.btjf.model.order.OrderProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderProductMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_product
     *
     * @mbg.generated
     */
    long countByExample(OrderProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_product
     *
     * @mbg.generated
     */
    int deleteByExample(OrderProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_product
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_product
     *
     * @mbg.generated
     */
    int insert(OrderProduct record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_product
     *
     * @mbg.generated
     */
    int insertSelective(OrderProduct record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_product
     *
     * @mbg.generated
     */
    List<OrderProduct> selectByExample(OrderProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_product
     *
     * @mbg.generated
     */
    OrderProduct selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_product
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") OrderProduct record, @Param("example") OrderProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_product
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") OrderProduct record, @Param("example") OrderProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_product
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(OrderProduct record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_product
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(OrderProduct record);

    OrderProduct findByOrderNoAndProductNo(@Param("orderNo")String orderNo, @Param("productNo")String productNo);
}