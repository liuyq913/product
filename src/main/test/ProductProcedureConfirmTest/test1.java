package ProductProcedureConfirmTest;

import com.btjf.controller.order.vo.WorkShopVo;
import com.btjf.service.order.ProductionProcedureService;
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

        ProductionProcedureService productionProcedureService = (ProductionProcedureService) f.getBean("productionProcedureService");

        List<WorkShopVo.Procedure> procedures =  productionProcedureService.getConfigProcedure("下料车间","P201908200001");
        System.out.println(procedures);
    }

}
