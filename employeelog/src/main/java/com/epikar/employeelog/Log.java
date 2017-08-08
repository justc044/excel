package com.epikar.employeelog;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class Log {

    private static Workbook wb;
    private static Sheet s;
    private static FileInputStream fis;
    private static FileOutputStream fos;
    private static Row row;
    private static Cell cell;

    public static void main( String[] args ) throws Exception {

        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet("Office InOut Time");

        // DATE
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c2.add(Calendar.DATE, -1);
        Calendar c3 = Calendar.getInstance();
        c3.add(Calendar.DATE, -2);
        Date t1 = c1.getTime();
        Date t2 = c2.getTime();
        Date t3 = c3.getTime();

        // SAMPLE DATA
        UserLog a = new UserLog("ayeom", t1,"09:00","18:00","Seoul");
        UserLog b = new UserLog("jjun1", t1,"09:00","18:00","Seoul");
        UserLog c = new UserLog("jchoi", t1,"09:00","18:00","Seoul");
        UserLog d = new UserLog("jpark", t1,"09:00","18:00","Seoul");
        UserLog e = new UserLog("ayeom", t2,"09:00","18:00","Seoul");
        UserLog f = new UserLog("jjun1", t2,"09:00","18:00","Seoul");
        UserLog g = new UserLog("jchoi", t2,"09:00","18:00","Seoul");
        UserLog h = new UserLog("jpark", t2,"09:00","18:00","Seoul");
        UserLog i = new UserLog("ayeom", t3,"09:00","18:00","Seoul");
        UserLog j = new UserLog("jjun1", t3,"09:00","18:00","Seoul");
        UserLog k = new UserLog("jchoi", t3,"09:00","18:00","Seoul");
        UserLog l = new UserLog("jpark", t3,"09:00","18:00","Seoul");

        // LIST OF DATA
        Stack<UserLog> data = new Stack<>();
        data.push(a);
        data.push(b);
        data.push(c);
        data.push(d);
        data.push(e);
        data.push(f);
        data.push(g);
        data.push(h);
        data.push(i);
        data.push(j);
        data.push(k);
        data.push(l);

        Collections.sort(data);

        List<Date> dateSet = new ArrayList<>();

        for (UserLog v : data){
            if (!dateSet.contains(v.getWorkday())) {
                dateSet.add(v.getWorkday());
            }
        }

        Collections.sort(dateSet);

        int rownum = 0;
        int id = 1;

        for (Date date : dateSet){

            Date current = date;

            // NEW RECORD
            Row row = sheet.createRow(rownum++);

            // WORKDAY INFO
            row.createCell(0).setCellValue("Workday: " + DateFormat.getDateInstance().format(date));

            // NEW ROW
            Row row3 = sheet.createRow(rownum++);

            // COLUMN 1 - ID
            row3.createCell(0).setCellValue("index");

            // COLUMN 2 - USERNAME
            row3.createCell(1).setCellValue("name");

            // COLUMN 3 - DATE
            row3.createCell(2).setCellValue("date");

            // COLUMN 4 - IN-TIME
            row3.createCell(3).setCellValue("in-time");

            // COLUMN 5 - OUT-TIME
            row3.createCell(4).setCellValue("out-time");

            while (!data.empty() && data.peek().getWorkday().equals(date)){

                UserLog u = data.pop();

                Row row2 = sheet.createRow(rownum++);

                // COLUMN 1 - ID
                row2.createCell(0).setCellValue(id++);

                // COLUMN 2 - USERNAME
                row2.createCell(1).setCellValue(u.getUsername());

                SimpleDateFormat sdf = new SimpleDateFormat();

                // COLUMN 3 - DATE
                row2.createCell(2).setCellValue(sdf.format(u.getWorkday()));

                // COLUMN 4 - IN-TIME
                row2.createCell(3).setCellValue(u.getInTime());

                // COLUMN 5 - OUT-TIME
                row2.createCell(4).setCellValue(u.getOutTime());

            }

            sheet.createRow(rownum++);
        }

        try {
            FileOutputStream out = new FileOutputStream(new File("C:\\Users\\Julia Jun\\Desktop\\" + "UserLog" + ".xlsx"));
            workbook.write(out);
            out.close();
        }
        catch (FileNotFoundException FNFE){
            System.out.println("Close the file");
        }

    }

}
