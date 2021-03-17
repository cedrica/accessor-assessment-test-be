package com.cedricleumaleu.accessorassessmenttest.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;

public class FileUtilsTest {
    @Test
    public void writeStringListToCsvFile() throws IOException, URISyntaxException {
    	/*InputStream is = FileUtils.getFileFromResourceAsStream("csv/sample-input.csv");
    	List<String[]> collect = FileUtils.convertInputStreamToList(is);
    	*/ 
    	String[] header = {"Make", "Model", "Description", "Price"};
        String[] record1 = {"Dell", "P3421W", "Dell 34, Curved, USB-C Monitor", "2499.00"};
        String[] record2 = {"Dell", "", "Alienware 38 Curved \"Gaming Monitor\"", "6699.00"};
        String[] record3 = {"Samsung", "", "49\" Dual QHD, QLED, HDR1000", "6199.00"};
        String[] record4 = {"Samsung", "", "Promotion! Special Price\n49\" Dual QHD, QLED, HDR1000", "4999.00"};

        List<String[]> list = new ArrayList<>();
        list.add(header);
        list.add(record1);
        list.add(record2);
        list.add(record3);
        list.add(record4);
        Function<? super String[], ? extends String> f = items -> {
    		String str = Arrays.asList(items).toString();
    		String result = str.replace("[", "").replace("]", "");
    		return result;
    	}; 
    	List<String> collectMapped = list.stream().map(f).collect(Collectors.toList()); 
    	FileUtils.writeStringListToCsvFile(collectMapped, "sample-input.csv");
    }

}
