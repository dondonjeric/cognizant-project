package com.academy.project.helper;

import com.academy.project.model.CommunityAdminAndManager;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.speadsheetml.sheet";
    static String[] HEADERs = {"ID", "CommunityAdminAndManagerName", "CognizantId", "Email", "Password", "RoleType", "IsActive" };
    static String SHEET = "CommunityAdminAndManager";

    public static ByteArrayInputStream excelDownload(List<CommunityAdminAndManager> communityAdminAndManagers) {

        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream();
        ) {

            Sheet sheet = workbook.createSheet(SHEET);

            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            for (CommunityAdminAndManager communityAdminAndManager : communityAdminAndManagers){
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(communityAdminAndManager.getId());
                row.createCell(1).setCellValue(communityAdminAndManager.getName());
                row.createCell(2).setCellValue(communityAdminAndManager.getCognizantId());
                row.createCell(3).setCellValue(communityAdminAndManager.getEmail());
                row.createCell(4).setCellValue(communityAdminAndManager.getPassword());
                row.createCell(5).setCellValue(communityAdminAndManager.getRoleType());
                row.createCell(6).setCellValue(communityAdminAndManager.getIsactive());
            }

            workbook.write(out);
            return  new ByteArrayInputStream(out.toByteArray());
        }catch (IOException e){
            throw new RuntimeException("fail to import data to Excel file: "+e.getMessage());
        }
    }
}
