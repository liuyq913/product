package ProductProcedureConfirmTest;

import com.btjf.controller.order.vo.WorkShopVo;
import com.btjf.service.productpm.ProductProcedureService;
import org.junit.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.List;

/**
 * Created by liuyq on 2019/8/22.
 */
public class test1 {
    @Test
    public void test(){
        FileSystemXmlApplicationContext f  = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");

        ProductProcedureService productProcedureService = (ProductProcedureService) f.getBean("productProcedureService");

        List<WorkShopVo.Procedure> procedures =  productProcedureService.getByWorkShopAndProductNo("下料车间","XH00002");
        System.out.println(procedures);
    }

}
