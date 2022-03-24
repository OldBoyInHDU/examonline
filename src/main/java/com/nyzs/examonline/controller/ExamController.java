package com.nyzs.examonline.controller;

import com.nyzs.examonline.bean.EmployeeGrades;
import com.nyzs.examonline.bean.ExamPaper;
import com.nyzs.examonline.bean.dto.ResponseResult;
import com.nyzs.examonline.service.ExamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ：RukiHuang
 * @description：TODO
 * @date ：2022/3/20 16:44
 */
@RestController
public class ExamController {

    /**
     * logger.
     */
    private static Logger logger = LoggerFactory.getLogger(ExamController.class);


    @Autowired
    ExamService examService;

    @RequestMapping("/examdate")
    public ResponseResult getExamDate() {

        try {
            logger.info(examService.getExamDate());
            return ResponseResult.ok(examService.getExamDate());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseResult.failed("获取考试期数失败");
    }

    @RequestMapping("/examstatus")
    public ResponseResult getExamStatus(String num, String examdate) {
        try {
            return ResponseResult.ok(examService.getExamStatus(num, examdate));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseResult.failed("获取员工考试状态失败");
    }


    @RequestMapping(value = "/generateExamPaper", method = RequestMethod.POST)
    public ResponseResult generateExamPaper(String examdate) {

        try {
            examService.generateExamPaper(examdate);
            System.out.println("post考试期数" + examdate);
            //生成试卷后，修改考试期数
            examService.updateExamDate(examdate);
            return ResponseResult.ok("生成试卷成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseResult.failed("生成试卷失败");
    }

    @RequestMapping("/getExamPaperByExamDate")
    public ResponseResult getExamPaperByExamDate(String examdate) {
        try {
            return ResponseResult.ok(examService.getExamPaperByExamDate(examdate));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseResult.failed("通过考试期数获取试卷失败");
    }

    @RequestMapping(value = "/recordGrades", method = RequestMethod.POST)
    public ResponseResult recordGrades(String employeeNum, String employeeName, String examdate, int grades) {
        try {
            System.out.println(employeeNum);
            System.out.println(employeeName);
            System.out.println(examdate);
            System.out.println(grades);
            examService.recordGrades(new EmployeeGrades(employeeNum, employeeName, examdate, grades));
            return ResponseResult.ok("成绩记录成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseResult.failed("成绩记录失败");
    }

}
