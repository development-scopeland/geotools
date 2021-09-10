package org.geotools.data.shapefile.dbf;

import junit.framework.TestCase;
import org.geotools.data.shapefile.shp.ShapefileInputStreamReader;
import org.junit.Test;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DbaseFileInputStreamReaderTest extends TestCase {

    @Test
    public void testShapeFileDBF() throws IOException {
        File file = new File("D:\\geotools\\modules\\plugin\\shapefile\\src\\test\\resources\\org\\geotools\\data\\shapefile\\test-data\\lsOnePoint\\lsOnePoint.dbf");
        File fileShx = new File("D:\\geotools\\modules\\plugin\\shapefile\\src\\test\\resources\\org\\geotools\\data\\shapefile\\test-data\\lsOnePoint\\lsOnePoint.shx");
        InputStream isShx = new FileInputStream(fileShx);
        InputStream is = new FileInputStream(file);

        DbaseFileInputStreamReader dbaseFileInputStreamReader = new DbaseFileInputStreamReader(is);
        while (dbaseFileInputStreamReader.hasNext()) {
            DbaseFileHeader header = dbaseFileInputStreamReader.getHeader();
            Object[] a = dbaseFileInputStreamReader.readEntry();
            int x = 2;
        }
    }
}