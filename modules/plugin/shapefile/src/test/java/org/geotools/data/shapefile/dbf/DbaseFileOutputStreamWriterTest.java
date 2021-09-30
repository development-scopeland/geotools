package org.geotools.data.shapefile.dbf;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import junit.framework.TestCase;
import org.geotools.data.shapefile.shp.ShapeType;
import org.geotools.data.shapefile.shp.ShapefileOutputStreamWriter;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.junit.Test;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

public class DbaseFileOutputStreamWriterTest extends TestCase {

    @Test
    public void testWriterDbf() throws ParseException, IOException {
        ShapefileOutputStreamWriter shapefileOutputStreamWriter = new ShapefileOutputStreamWriter();
        GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();

        WKTReader reader = new WKTReader(geometryFactory);

        Point point = (Point) reader.read("POINT (10 10)");
        Point point1 = (Point) reader.read("POINT (20 20)");
        GeometryCollection geometryCollection =
                geometryFactory.createGeometryCollection(new Geometry[] {point, point1});
        DbaseFileHeaderOutputStream dbaseFileHeaderOutputStream = new DbaseFileHeaderOutputStream();
        dbaseFileHeaderOutputStream.addColumn("testString", 'C', 20, 0);
        dbaseFileHeaderOutputStream.addColumn("testNumber", 'N', 20, 0);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DbaseFileOutputStreamWriter dbaseFileOutputStreamWriter =
                new DbaseFileOutputStreamWriter(
                        dbaseFileHeaderOutputStream, byteArrayOutputStream, null, null);
        dbaseFileOutputStreamWriter.write(new Object[] {"Test1", 2345});
        dbaseFileOutputStreamWriter.write(new Object[] {"Test", 33234});

        ByteArrayOutputStream[] out =
                shapefileOutputStreamWriter.writeOutputStream(geometryCollection, ShapeType.POINT);
        // InputStream J = new O
        dbaseFileOutputStreamWriter.close();
        FileOutputStream f = new FileOutputStream("testDennis.shp");
        f.write(out[0].toByteArray());
        FileOutputStream f1 = new FileOutputStream("testDennis.shx");
        f1.write(out[1].toByteArray());
        FileOutputStream f2 = new FileOutputStream("testDennis.dbf");
        f2.write(byteArrayOutputStream.toByteArray());
        DbaseFileHeader dbaseFileHeader = new DbaseFileHeader();
        dbaseFileHeader.addColumn("test", 'C', 20, 0);
        // File f5 = new File("testDennis.dbf");
        // WritableByteChannel writableByteChannel = new FileOutputStream(f5).getChannel();
        // DbaseFileWriter db = new DbaseFileWriter(dbaseFileHeader, writableByteChannel);
        // Charset charset = Charset.defaultCharset();
        // TimeZone timeZone = TimeZone.getDefault();
        // DbaseFileWriter.FieldFormatter v = new DbaseFileWriter.FieldFormatter(charset, timeZone,
        // false);

        // db.write(new Object[]{ v.getFieldString(20, "3445")});
        // db.write(new Object[]{ v.getFieldString(20, "2000")});
        // db.close();
    }
}
