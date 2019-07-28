package com.btjf.factory.Task;

import com.btjf.model.pm.Pm;
import com.btjf.service.pm.PmService;
import com.btjf.util.SpringBeanUtil;
import com.google.common.collect.Lists;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * Created by liuyq on 2019/7/24.
 */
public class InsertTask<T> extends RecursiveTask<Integer> {

    private static final Integer MAX_INSTER_LENGTH = 100;

    private static final Logger logger = LoggerFactory.getLogger(InsertTask.class);

    private List<T> entryList;

    private Class clazz; //domo


    public InsertTask(List<T> list, Class clazz) {
        this.entryList = list;
        this.clazz = clazz;
    }

    /**
     * 拆分insert   不用事务，导入
     *
     * @return
     */
    @Override
    protected Integer compute() {
        if (CollectionUtils.isEmpty(entryList)) {
            return NumberUtils.INTEGER_ZERO;
        }

        if (entryList.size() <= MAX_INSTER_LENGTH) {
            try {
                insert(entryList);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return NumberUtils.INTEGER_ZERO;
        }
        //任务量大于100 拆分任务并执行
        List<T> newList = Lists.newArrayList();
        List<InsertTask> task = Lists.newArrayList();
        entryList.stream().filter(t -> t != null).forEach(t -> {
            newList.add(t);
            if (newList.size() >= MAX_INSTER_LENGTH) {
                List<T> newInsertList = Lists.newArrayList();
                newInsertList.addAll(newList);
                InsertTask taskNew = new InsertTask(newInsertList, this.clazz);
                taskNew.fork(); //执行任务
                task.add(taskNew);
                newList.clear();
            }
        });
        //最后剩下的任务
        InsertTask taskNew = new InsertTask(newList, this.clazz);
        taskNew.fork(); //执行任务
        task.add(taskNew);

        for (InsertTask task1 : task) {
            task1.join();
        }
        return NumberUtils.INTEGER_ZERO;
    }

    /**
     * 按不同的class 来insert
     *
     * @param list 拆分后的list
     */
    private void insert(List<T> list) {
        if (this.clazz == PmService.class) {
            SpringBeanUtil.getBean(PmService.class).saveList((List<Pm>) list);
        }
    }
}
