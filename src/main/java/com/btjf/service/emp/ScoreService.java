package com.btjf.service.emp;

import com.btjf.mapper.emp.ScoreMapper;
import com.btjf.model.emp.EmpSalaryMonthly;
import com.btjf.model.emp.Score;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.stream.Stream;

/**
 * Created by liuyq on 2019/9/10.
 */
@Service
public class ScoreService {

    @Resource
    private ScoreMapper scoreMapper;

    @Resource
    private EmpSalaryMonthlyService empSalaryMonthlyService;

    public Score getByNameAndYearMonth(String name, String yearMonth) {
        return scoreMapper.getByNameAndYearMonth(name, yearMonth);
    }

    public Integer saveOrUpate(Score score) {
        if (score == null) return 0;
        Score score1 = this.getByNameAndYearMonth(score.getEmpName(), score.getYearMonth());
        if (null != score1) {
            if (null == score.getCheckworkScore()) { //导入的是另外三个分
                score1.setFiveScore(score.getFiveScore());
                score1.setCoordinationScore(score.getCoordinationScore());
                score1.setQualityScore(score.getQualityScore());
            } else {
                score1.setCheckworkScore(score.getCheckworkScore());
            }
            scoreMapper.updateByPrimaryKey(score1);
            saveSubsidy(score1);
        } else {
            scoreMapper.insertSelective(score);
        }
        return score.getId();
    }

    public Integer saveSubsidy(Score score) {
        if (score.getQualityScore() != null && score.getCheckworkScore() != null && score.getCoordinationScore() != null
                && score.getFiveScore() != null) {
            //平均值
            BigDecimal avgSorce = BigDecimal.valueOf(Stream.of(score.getCheckworkScore(),
                                                     score.getQualityScore(),
                                                     score.getCoordinationScore(),
                                                     score.getFiveScore())
                    .mapToDouble(BigDecimal::doubleValue).average().getAsDouble());
            Score score1 = new Score();
            score1.setId(score.getId());
            score1.setScore(avgSorce);
            scoreMapper.updateByPrimaryKey(score1);

            //更新考勤表里的分
            EmpSalaryMonthly empSalaryMonthly = empSalaryMonthlyService.getByYearMonthAndName(score.getYearMonth(), score.getEmpName());
            if(null != empSalaryMonthly){
                EmpSalaryMonthly empSalaryMonthly1 = new EmpSalaryMonthly();
                empSalaryMonthly1.setId(empSalaryMonthly.getId());
                empSalaryMonthly1.setScore(avgSorce);
                empSalaryMonthly1.setLastModifyTime(new Date());
                empSalaryMonthlyService.update(empSalaryMonthly1);
            }
            //todo 各种补贴
            //固定住房补贴
        }
        return null;
    }
}
