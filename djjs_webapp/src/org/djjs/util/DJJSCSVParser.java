package org.djjs.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.djjs.model.NewMemberVO;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.exception.SuperCsvConstraintViolationException;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.prefs.CsvPreference;

public class DJJSCSVParser {

	private DJJSCSVParser() {
	}

	public static DJJSCSVParser parser;

	/**
	 * @param args
	 * @throws Exception
	 */
	/*public static void main(String[] args) throws Exception {
		String error = parseCSV(new File("C:/temp_contact.csv"), new ArrayList<NewMemberVO>());
		System.out.println(error);
	}*/

	public static  String parseCSV(File csvFile, List<NewMemberVO> list) {
		CsvBeanReader reader = null; 
		String errorMsg = "";
		try {
			reader = new CsvBeanReader(new FileReader(csvFile), CsvPreference.STANDARD_PREFERENCE);
			reader.getHeader(true);
			final CellProcessor[] processors = getProcessors();
			final String[] nameMapping = getMapping();
			NewMemberVO vo = null;
			
			do {
				try {
				vo = reader.read(NewMemberVO.class, nameMapping, processors);
				
				//System.out.println("row is read " + reader.getLineNumber());
				if(null!=vo){
					list.add(vo);
				}
				
				}catch (SuperCsvConstraintViolationException e) {
					errorMsg += "<p>line number: " + e.getCsvContext().getLineNumber() + ", column "+ e.getCsvContext().getColumnNumber() + " has error.</p>";
					continue;
				}
			} while (vo!=null);			
			System.out.println("Size "+ list.size());
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}

		return errorMsg;

	}

	private static String[] getMapping() {
		
		return new String[] {
			null,
			"relatedToText",
			"selectSwami",
			"isvip",
			"firstNameText",
			"lastNameText",
			"sex",
			"dateOfBirth",
			"countryCodes",
			"stateCodes",
			"districtCodes",
			"townNameText",
			"fullAddText",
			"fullofficeAddressText",
			"phoneMobileText",
			"emailText",
			"qualificationText",
			"occupationText",
			"designation",
			"deekshaDate",
			"deekshaAshramText",
			"reference",
			"otherProfText"
		};		
	}
	
	private static CellProcessor[] getProcessors() {
		final CellProcessor[] processor = new CellProcessor[] {
				new Optional(), // S. No.
				new NotNull(), // Branch 
				new NotNull(), // gyani 
				new NotNull(), //vip
				new NotNull(), //first name 
				new NotNull(), // last name
				new NotNull(), // sex
				new NotNull(), // DOB
				new NotNull(),//country
				new NotNull(),// state
				new NotNull(), //district
				new Optional(), // town/village
				new Optional(), //full residential address
				new Optional(), // official adress
				new Optional(), // mobile
				new Optional(),// email
				new Optional(),//academic
				new Optional(),//profession
				new Optional(), // designation
				new Optional(),// date of gyan
				new Optional(), //gyan address
				new Optional(),// reference
				new Optional() //otherProfText

		};
		return processor;
	}

	

	public DJJSCSVParser getInstance() {
		if (null == parser) {
			parser = new DJJSCSVParser();
		}
		return parser;
	}

}
