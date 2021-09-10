package org.geotools.data.shapefile;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.List;

public class ShapeInputStreamToFeatureCollectionTest extends TestCase {

    @Test
    public void test() throws IOException {
        File fileShp = new File("D:\\geotools\\modules\\plugin\\shapefile\\src\\test\\resources\\org\\geotools\\data\\shapefile\\test-data\\lsOnePoint\\lsOnePoint.shp");
        File fileShx = new File("D:\\geotools\\modules\\plugin\\shapefile\\src\\test\\resources\\org\\geotools\\data\\shapefile\\test-data\\lsOnePoint\\lsOnePoint.shx");
        File fileDbf = new File("D:\\geotools\\modules\\plugin\\shapefile\\src\\test\\resources\\org\\geotools\\data\\shapefile\\test-data\\lsOnePoint\\lsOnePoint.dbf");
        InputStream isShx = new FileInputStream(fileShx);
        InputStream isShp = new FileInputStream(fileShp);
        InputStream isDbf = new FileInputStream(fileDbf);
        HashMap<String, String> mapping = new HashMap<String, String>();
        mapping.put("geometry", "F1233");
        mapping.put("Name", "F1234");
        mapping.put("Activity", "F1235");
        mapping.put("Starttime", "F1236");
        mapping.put("Endtime", "F1237");
        mapping.put("CorrStatus", "F1238");
        mapping.put("AggStatus", "F1239");
        mapping.put("Operator", "F1240");
        ShapeInputStreamToFeatureCollection shapeInputStreamToFeatureCollection = new ShapeInputStreamToFeatureCollection();
        List<HashMap<String, String>> datas = shapeInputStreamToFeatureCollection.getFeatrueCollection(isShp, isShx, isDbf, mapping);
        int i =1;


    }
}