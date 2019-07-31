package com.btjf.factory;

import com.btjf.factory.Task.InsertTask;
import com.btjf.model.pm.Pm;
import com.btjf.service.pm.PmService;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by liuyq on 2019/7/28.
 */
public class ExcelImportFactory {

    public Integer savePm(List<Pm> list, boolean isCover){
        InsertTask task = new InsertTask(list, PmService.class);
        task.setCover(isCover);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(task);
        return list.size();
    }
}
