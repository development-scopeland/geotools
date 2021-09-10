package org.geotools.data.shapefile.shp;

import junit.framework.TestCase;
import org.junit.Test;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;

import java.io.*;

public class ShapefileInputStreamReaderTest extends TestCase {

    @Test
    public void testShapeFile() throws IOException {
        File file = new File("D:\\geotools\\modules\\plugin\\shapefile\\src\\test\\resources\\org\\geotools\\data\\shapefile\\test-data\\lsOnePoint\\lsOnePoint.shp");
        InputStream is = new FileInputStream(file);

        ShapefileInputStreamReader shapefileInputStreamReader = new ShapefileInputStreamReader(new GeometryFactory(), is, null);
        while (shapefileInputStreamReader.hasNext()) {
            Geometry shape = (Geometry) shapefileInputStreamReader.nextRecord().shape();
            int x = 2;
        }
    }

    @Test
    public void testShapeFileSHX() throws IOException {
        File file = new File("D:\\geotools\\modules\\plugin\\shapefile\\src\\test\\resources\\org\\geotools\\data\\shapefile\\test-data\\lsOnePoint\\lsOnePoint.shp");
        File fileShx = new File("D:\\geotools\\modules\\plugin\\shapefile\\src\\test\\resources\\org\\geotools\\data\\shapefile\\test-data\\lsOnePoint\\lsOnePoint.shx");
        InputStream isShx = new FileInputStream(fileShx);
        InputStream is = new FileInputStream(file);

        ShapefileInputStreamReader shapefileInputStreamReader = new ShapefileInputStreamReader(new GeometryFactory(), is, isShx);
        while (shapefileInputStreamReader.hasNext()) {
            Geometry shape = (Geometry) shapefileInputStreamReader.nextRecord().shape();
            int x = 2;
        }
    }
}