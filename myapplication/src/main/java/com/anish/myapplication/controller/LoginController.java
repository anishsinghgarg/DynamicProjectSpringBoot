package com.anish.myapplication.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.anish.myapplication.modal.LoginModal;
import com.anish.myapplication.services.LoginServices;

@Controller
public class LoginController {
	// Save the uploaded file to this folder
	private static String UPLOADED_FOLDER = "D://";
	@Autowired
	LoginServices loginServices;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome() {
		System.out.println("Welcome :-)");

		return "welcome";
	}

	@RequestMapping(value = "/validateUser", method = RequestMethod.POST)
	public ModelAndView validateUser(LoginModal model) {
		String viewJsp = "welcome";

		model = loginServices.validateLogin(model);
		if (model != null)
			viewJsp = "home";
		return new ModelAndView(viewJsp, "model", model);
	}

	@RequestMapping(value = "/uploadSheet", method = RequestMethod.POST)
	public String singleFileUpload(@RequestParam("fileToUpload") MultipartFile fileToUpload,
			RedirectAttributes redirectAttributes) {

		if (fileToUpload.isEmpty()) {
			System.out.println("Please select a file to upload");
			return "welcome";
		}
		try {
			// Get the file and save it somewhere
			byte[] bytes = fileToUpload.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + fileToUpload.getOriginalFilename());
			Files.write(path, bytes);
			System.out.println("You successfully uploaded '" + fileToUpload.getOriginalFilename() + "'");

			try {

				FileInputStream excelFile = new FileInputStream(new File(UPLOADED_FOLDER + fileToUpload.getOriginalFilename()));
				Workbook workbook = new XSSFWorkbook(excelFile);
				Sheet datatypeSheet = workbook.getSheetAt(0);
				Iterator<Row> iterator = datatypeSheet.iterator();

				while (iterator.hasNext()) {

					Row currentRow = iterator.next();
					Iterator<Cell> cellIterator = currentRow.iterator();

					while (cellIterator.hasNext()) {
						Cell currentCell = cellIterator.next();
						if (currentCell.getCellTypeEnum() == CellType.STRING) {
							System.out.print(currentCell.getStringCellValue() + "--");
						} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							System.out.print(currentCell.getNumericCellValue() + "--");
						}
					}
					System.out.println();

				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "welcome";
	}

}
