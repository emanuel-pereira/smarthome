package smarthome.model.readers;

import smarthome.model.GeographicalArea;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface FileReaderGeoArea {

    List<GeographicalArea> loadData(Path path) throws org.json.simple.parser.ParseException, java.text.ParseException, IOException, ParserConfigurationException;
}
