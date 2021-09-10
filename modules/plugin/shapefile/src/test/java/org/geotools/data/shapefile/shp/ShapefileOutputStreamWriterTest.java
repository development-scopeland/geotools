package org.geotools.data.shapefile.shp;

import junit.framework.TestCase;
import org.geotools.geometry.jts.GeometryCollector;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.junit.Test;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.opengis.geometry.Geometry;

import java.io.*;

public class ShapefileOutputStreamWriterTest extends TestCase {

    @Test
    public void testWriterShape() throws ParseException, IOException {
       ShapefileOutputStreamWriter shapefileOutputStreamWriter = new ShapefileOutputStreamWriter();
        GeometryCollector gc = new GeometryCollector();
        GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();

        WKTReader reader = new WKTReader(geometryFactory);

        Point point = (Point) reader.read("POINT (1 1)");
        gc.add(point);
        ByteArrayOutputStream[] out = shapefileOutputStreamWriter.writeOutputStream(gc.collect(), ShapeType.POINT);
        //InputStream J = new O
        FileOutputStream f = new FileOutputStream("testDennis.shp");
        f.write(out[0].toByteArray());

    }
}