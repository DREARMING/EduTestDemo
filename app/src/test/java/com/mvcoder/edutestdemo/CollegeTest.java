package com.mvcoder.edutestdemo;

import com.google.gson.Gson;
import com.mvcoder.edutestdemo.beans.College;
import com.mvcoder.edutestdemo.beans.Department;
import com.mvcoder.edutestdemo.beans.Grade;
import com.mvcoder.edutestdemo.beans.Major;
import com.mvcoder.edutestdemo.beans.SchoolClass;
import com.mvcoder.edutestdemo.utils.GsonUtil;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CollegeTest {

    @Test
    public void testCollege(){
        Gson gson = GsonUtil.getInstance().fieldsGson(true,true,"baseObjId");
        List<College> collegeList = getCollege();
        String result = gson.toJson(collegeList);
       System.out.println(result);
    }

    private String[] deparmentNames = new String[]{
            "应用经济学",
            /*"统计学系",
            "会计学系","机电工程系",*/"电子与通信工程系"/*,"电气工程与自动化系"*/
    };

    private String[] majorNames = new String[]{
            "金融学","经济学","电子信息工程","通信工程"
    };

    private String[] gradeNames = new String[]{
            "2017级","2018级","2019级","2020级"
    };

    private Date[] dates = new Date[]{
            new Date(2017 - 1900,8,1),
            new Date(2018 - 1900,8,1),
            new Date(2019 - 1900,8,1),
            new Date(2020 - 1900,8,1)
    };


    private List<Grade> getGradeList(){
        int gradeNum = 4;
        int gradeIdStep = 1;

        List<Grade> gradeList = new ArrayList<>();
        for(int n = 0; n < gradeNum; n++){
            Grade grade = new Grade();
            grade.setGradeId(gradeIdStep++);
            int gradeIndex = (int) ((grade.getGradeId() - 1) % 4);
            grade.setGradeName(gradeNames[gradeIndex]);
            grade.setEnrolDate(dates[gradeIndex]);
            gradeList.add(grade);
        }
        return gradeList;
    }

   // private String[] majorNames = new String[]{};

    private List<College> getCollege(){

        int collegeNum = 2;
        int deparmentNum = 1;
        int marjorNum = 2;
        int gradeNum = 4;
        int classNum = 2;

        int majorIdStep = 1;
        int gradeIdStep = 1;
        int classIdStep = 1;
        List<Grade> gradeList = getGradeList();
        List<College> collegeList  = new ArrayList<>();
        for(int i = 0; i < collegeNum; i++){
            College college = new College();
            college.setCollegeId( i + 1);
            college.setCollegeName(college.getCollegeId()== 1? "经济与统计学院":"机械与电气工程学院");
            List<Department> departmentList = new ArrayList<>();
            for(int j=0;j<deparmentNum;j++){
                Department department = new Department();
                int deparmentId = i * deparmentNum + j + 1;
                department.setCollegeId(college.getCollegeId());
                department.setDepartmentId(deparmentId);
                department.setDepartmentName(deparmentNames[deparmentId - 1]);
                List<Major> majorList = new ArrayList<>();
                for(int k = 0; k < marjorNum; k++){
                    Major major = new Major();
                    major.setMajorId(majorIdStep++);
                    major.setMajorName(majorNames[(int) (major.getMajorId() - 1)]);
                    major.setDepartmentId(department.getDepartmentId());
                    major.setStudyYears(4);
                    List<SchoolClass> majorClassList = new ArrayList<>();
                    for(int n = 0; n < gradeList.size(); n++){
                        Grade grade = gradeList.get(n);
                        List<SchoolClass> classList = new ArrayList<>();
                        for(int s = 0; s < classNum; s++){
                            SchoolClass schoolClass = new SchoolClass();
                            schoolClass.setClassId(classIdStep++);
                            schoolClass.setGradeId(grade.getGradeId());
                            schoolClass.setMajorId(major.getMajorId());
                            int classNumber = (int) (schoolClass.getClassId() % classNum);
                            String className = grade.getGradeName() + major.getMajorName() +(classNumber == 0?classNum:classNumber) + "班";
                            schoolClass.setClassName(className);
                            schoolClass.setStuNum(30);
                            classList.add(schoolClass);
                        }
                        grade.setClassList(classList);
                        majorClassList.addAll(classList);
                    }
                    major.setSchoolClassList(majorClassList);
                    majorList.add(major);
                }
                department.setMajorList(majorList);
                departmentList.add(department);
            }
            college.setDepartmentList(departmentList);
            collegeList.add(college);
        }
        return collegeList;
    }
}
