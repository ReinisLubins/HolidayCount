package homework.holidaycount.holiday;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.client.MockRestServiceServer;


@AutoConfigureMockMvc
public class HolidayServiceTest {

    static ObjectMapper jsonObjectMapper = new ObjectMapper();

    private MockRestServiceServer mockServer;

    @Test
    int getHolidaysCountTest() {
      return 0;
    }
}
