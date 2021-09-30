package org.geotools.data.shapefile.shp;

import java.io.*;
import junit.framework.TestCase;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.junit.Test;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

public class ShapefileOutputStreamWriterTest extends TestCase {

    @Test
    public void testWriterShape() throws ParseException, IOException {
        ShapefileOutputStreamWriter shapefileOutputStreamWriter = new ShapefileOutputStreamWriter();
        GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();

        WKTReader reader = new WKTReader(geometryFactory);

        MultiLineString point = (MultiLineString) reader.read("MULTILINESTRING ((10 10, 20 20))");
        MultiLineString point1 =
                (MultiLineString) reader.read("MULTILINESTRING ((10 10, 20 20), (15 15, 30 15))");
        GeometryCollection geometryCollection =
                geometryFactory.createGeometryCollection(new Geometry[] {point, point1});

        ByteArrayOutputStream[] out =
                shapefileOutputStreamWriter.writeOutputStream(geometryCollection, ShapeType.ARC);
        // InputStream J = new O
        FileOutputStream f = new FileOutputStream("testDennis.shp");
        f.write(out[0].toByteArray());
        FileOutputStream f1 = new FileOutputStream("testDennis.shx");
        f1.write(out[1].toByteArray());
    }
}
