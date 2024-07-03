package org.baseclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClass {
	public static RequestSpecification requestSpecification;

	public static void requestObject(String baseURI) {
		RestAssured.baseURI = baseURI;
		requestSpecification = RestAssured.given();
	}

	public static void attachQueryParam(String parameterName, String parameterValue) {
		requestSpecification.queryParam(parameterName, parameterValue);

	}

	public static void attachPathParam(String parameterName, String parameterValue) {
		requestSpecification.pathParam(parameterName, parameterValue);

	}

	public static Response responseObject(String requesttype, String endPoint) {
		switch (requesttype) {
		case "GET": {
			return requestSpecification.request(Method.GET, endPoint);

		}
		case "POST": {
			return requestSpecification.request(Method.POST, endPoint);

		}
		case "PUT": {
			return requestSpecification.request(Method.PUT, endPoint);

		}

		default: {
			return requestSpecification.request(Method.DELETE, endPoint);

		}

		}
	}

	public static void getResponseCode(Response response) {
		System.out.println(response.getStatusCode());

	}

	public static void getResponseBody(Response response) {
		System.out.println(response.getBody().asPrettyString());
	}

	public static void attachToken(String userName, String password) {
		requestSpecification.auth().basic(userName, password);

	}

	public static void attachToken(String accessToken) {
		requestSpecification.auth().oauth2(accessToken);
	}
    public static String readExcell(int rownum ,int cellnum) throws FileNotFoundException, IOException {
    	Workbook book = new XSSFWorkbook(new FileInputStream(new File("C:\\Testing\\APIJsonMaven\\src\\test\\resources\\APIBook1.xlsx")));
    			return book.getSheet("Sheet1").getRow(rownum).getCell(cellnum).getStringCellValue();
    	
    }
}
