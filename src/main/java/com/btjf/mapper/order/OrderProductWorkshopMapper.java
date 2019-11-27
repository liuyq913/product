package com.btjf.mapper.order;

import com.btjf.model.order.OrderProductWorkshop;
import com.btjf.model.order.OrderProductWorkshopExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderProductWorkshopMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_product_workshop
     *
     * @mbg.generated
     */
    long countByExample(OrderProductWorkshopExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_product_workshop
     *
     * @mbg.generated
     */
    int deleteByExample(OrderProductWorkshopExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_product_workshop
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_product_workshop
     *
     * @mbg.generated
     */
    int insert(OrderProductWorkshop record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_product_workshop
     *
     * @mbg.generated
     */
    int insertSelective(OrderProductWorkshop record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_product_workshop
     *
     * @mbg.generated
     */
    List<OrderProductWorkshop> selectByExample(OrderProductWorkshopExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_product_workshop
     *
     * @mbg.generated
     */
    OrderProductWorkshop selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_product_workshop
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") OrderProductWorkshop record, @Param("example") OrderProductWorkshopExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_product_workshop
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") OrderProductWorkshop record, @Param("example") OrderProductWorkshopExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_product_workshop
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(OrderProductWorkshop record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_product_workshop
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(OrderProductWorkshop record);
}