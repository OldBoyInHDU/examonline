package com.nyzs.examonline.service;

import com.nyzs.examonline.bean.EmployeeGrades;
import com.nyzs.examonline.bean.ExamPaper;
import com.nyzs.examonline.dao.ExamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：RukiHuang
 * @description：TODO
 * @date ：2022/3/20 16:46
 */

@Service
public class ExamService {

    @Autowired
    ExamDao examDao;


    public String getExamDate() throws Exception {
        return examDao.getExamDate();
    }

    public EmployeeGrades getExamStatus(String num, String examdate) throws Exception {
        return examDao.getExamStatus(num, examdate);
    }

    public List<ExamPaper> generateExamPaper(String examdate) throws Exception {
        return examDao.generateExamPaper(examdate);
    }

    public List<ExamPaper> getExamPaperByExamDate(String examdate) throws Exception {
        return examDao.getExamPaperByExamDate(examdate);
    }
}
