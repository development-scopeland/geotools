package org.geotools.data.shapefile;

import org.geotools.data.shapefile.dbf.DbaseFileHeader;
import org.geotools.data.shapefile.dbf.DbaseFileInputStreamReader;
import org.geotools.data.shapefile.shp.ShapefileInputStreamReader;
import org.geotools.feature.FeatureCollection;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShapeInputStreamToFeatureCollection {

    public List<HashMap<String, String>> getFeatrueCollection(InputStream shp, InputStream shx, InputStream dbf, HashMap<String, String> mapping) throws IOException {
        ShapefileInputStreamReader shapefileInputStreamReader = new ShapefileInputStreamReader(new GeometryFactory(), shp, shx);
        DbaseFileInputStreamReader dbaseFileInputStreamReader = new DbaseFileInputStreamReader(dbf);
        List<HashMap<String, String>> rows = new ArrayList<HashMap<String, String>>();
        while (dbaseFileInputStreamReader.hasNext() && shapefileInputStreamReader.hasNext()) {
            HashMap<String, String> row = new HashMap<String, String >();
            Geometry shape = (Geometry) shapefileInputStreamReader.nextRecord().shape();
            row.put(mapping.get("geometry"), shape.toString());
            DbaseFileHeader header = dbaseFileInputStreamReader.getHeader();
            Object[] entry = dbaseFileInputStreamReader.readEntry();
            for(int index= 0; index < header.getNumFields(); index++){
                row.put(mapping.get(header.getFieldName(index)), entry[index].toString());
            }
            rows.add(row);

        }
        return rows;

    }
}
