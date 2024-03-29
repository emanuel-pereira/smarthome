package smarthome.model.readers;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JSONHouseSensorsTest {

    @Test
    void importDataSizeListTest() throws IOException, ParseException {
        JSONHouseSensors jsonHouseSensors = new JSONHouseSensors();
        Path path = Paths.get("resources_tests/DataSet_sprint06_HouseSensors.json");
        List<String[]> result;
        result = jsonHouseSensors.loadData(path);
        assertEquals(4, result.size());
    }
}
