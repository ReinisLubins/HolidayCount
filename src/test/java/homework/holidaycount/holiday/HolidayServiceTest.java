package homework.holidaycount.holiday;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

public class HolidayServiceTest {

    @Test
    void getHolidaysCountTest() {
        RestTemplate restTemplate = new RestTemplate();
        HolidayService holidayService = new HolidayService(restTemplate);

        Assertions.assertEquals(14, holidayService.getHolidaysCount("2021", "LV"));
        Assertions.assertEquals(224, holidayService.getHolidaysCount("2007-2022", "LV"));
    }
}
