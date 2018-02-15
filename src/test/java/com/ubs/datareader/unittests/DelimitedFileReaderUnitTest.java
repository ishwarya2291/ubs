package com.ubs.datareader.unittests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

import com.ubs.datareader.DelimitedFileReader;
import com.ubs.datareader.ReaderException;

public class DelimitedFileReaderUnitTest {

	@Test
	public void readNullTest() throws ReaderException, IOException {
		//Arrange
		Map<String, String> initArgs = new HashMap<String, String>();
		initArgs.put("fullPathToDelimitedFile", new File( "." ).getCanonicalPath() + "/src/test/java/" + "TESTFILE.DAT");
		initArgs.put("fileDelimitedBy",   "\t");
		
		DelimitedFileReader delimitedFileReader = new DelimitedFileReader(initArgs);
		List<String[]> expectedResult = new ArrayList<>();
		
		String[] expectedArray1 = {"Company Code", "Account", "City", "Country", "Credit Rating", "Currency", "Amount"};
		String[] expectedArray2 = {"2316", "1520670", "Arhus", "DK", "NR", "GBP", "617755137"};
		String[] expectedArray3 = {"2317", "6399028", "Moscow", "RUS", "Aaa-", "CHF", "112170814.9"};
		String[] expectedArray4 = {"2318", "8084107", "Bangalore", "IND", "Aaa+", "CHF", "836211889.9"};
		
		expectedResult.add(expectedArray1);
		expectedResult.add(expectedArray2);
		expectedResult.add(expectedArray3);
		expectedResult.add(expectedArray4);
		
		//Act
		List<String[]> actualResult = delimitedFileReader.read();
		
		//Assert
		assertThat(actualResult.get(0)).isEqualTo(expectedResult.get(0));
		assertThat(actualResult.get(1)).isEqualTo(expectedResult.get(1));
		assertThat(actualResult.get(2)).isEqualTo(expectedResult.get(2));
		assertThat(actualResult.get(3)).isEqualTo(expectedResult.get(3));
	}

}
