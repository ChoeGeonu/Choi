package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Model.StudentVO;

public class StudentExcel {
	public boolean xlsxWiter(List<StudentVO> list, String saveDir) {
		// 워크북 생성
		XSSFWorkbook workbook = new XSSFWorkbook();
		// 워크시트 생성
		XSSFSheet sheet = workbook.createSheet();
		// 행 생성
		XSSFRow row = sheet.createRow(0);
		// 셀 생성
		XSSFCell cell;
		// 헤더 정보 구성
		cell = row.createCell(0);
		cell.setCellValue("번호");
		cell = row.createCell(1);
		cell.setCellValue("이름");
		cell = row.createCell(2);
		cell.setCellValue("학년");
		cell = row.createCell(3);
		cell.setCellValue("반");
		cell = row.createCell(4);
		cell.setCellValue("출석번호");
		/*
		 * cell = row.createCell(5); cell.setCellValue("출석여부");
		 */

		// 리스트의 size 만큼 row 생성
		StudentVO vo;
		for (int rowldx = 0; rowldx < list.size(); rowldx++) {
			vo = list.get(rowldx);

			// 행 생성
			row = sheet.createRow(rowldx + 1);

			cell = row.createCell(0);
			cell.setCellValue(vo.getS_no());// 학생 코드
			cell = row.createCell(1);
			cell.setCellValue(vo.getS_name());// 학생 이름
			cell = row.createCell(2);
			cell.setCellValue(vo.getS_year());// 학년
			cell = row.createCell(3);
			cell.setCellValue(vo.getS_ban());// 반
			cell = row.createCell(4);
			cell.setCellValue(vo.getS_number());
			// 출석번호
			/*
			 * cell = row.createCell(0); cell.setCellValue(vo.getNo());//출석여부
			 */ }

		// 입력된 내용 파일 쓰기
		String strReportPDFName = "student_" + System.currentTimeMillis() + ".xlsx";
		File file = new File(saveDir + "\\" + strReportPDFName);
		FileOutputStream fos = null;

		boolean saveSuccess;
		saveSuccess = false;
		try {
			fos = new FileOutputStream(file);
			if (fos != null) {
				workbook.write(fos);
				saveSuccess = true;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (workbook != null)
					workbook.close();
				if (fos != null)
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return saveSuccess;

	}

}
