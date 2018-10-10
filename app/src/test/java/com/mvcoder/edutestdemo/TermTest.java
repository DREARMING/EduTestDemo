package com.mvcoder.edutestdemo;

import com.google.gson.Gson;
import com.mvcoder.edutestdemo.beans.TermTable;
import com.mvcoder.edutestdemo.utils.GsonUtil;
import com.mvcoder.edutestdemo.utils.MResponse;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TermTest {

    @Test
    public void testTerm(){
        Gson gson = GsonUtil.getInstance()
                .fieldsGson(true,true,"baseObjId");
        MResponse<List<TermTable>> response = new MResponse<>();
        response.setCode(200);
        List<TermTable> termTables = new ArrayList<>();
        termTables.add(getTerm());
        response.setData(termTables);
        String result = gson.toJson(response);
        System.out.println(result);
    }

    private TermTable getTerm(){
        TermTable termTable = new TermTable();
        termTable.setTermId(1);
        termTable.setTermName("2018-2019学年第一学期");
        termTable.setTimeTableId(1);
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.clear();
        startCalendar.set(2018,8,1);
        termTable.setStartDate(startCalendar.getTime());
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.clear();
        endCalendar.set(2019,0,30);

        long time = endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis();
        int days = (int) (time / 1000 / 60 / 60 / 24);
        termTable.setDays(days);
        boolean flag = days % 7 == 0;
        int weeks =  days / 7 + (flag?0:1);
        termTable.setWeekNums(weeks);
        termTable.setEndDate(endCalendar.getTime());
        return termTable;
    }

}
