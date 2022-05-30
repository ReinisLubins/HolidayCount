package homework.holidaycount.holiday;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class HolidayServiceTest {

    @Mock
    HolidayRestService holidayRestService;

    @InjectMocks
    HolidayService holidayService;

    @Test
    void getHolidaysCountTest() {

    }

    @Test
    void wrongYearFormatTest() {
        Exception exception = Assertions.assertThrows(ResponseStatusException.class, () -> {
            holidayService.getHolidaysCount("1999-", "LV");
        });

        String expectedMessage = "Wrong year format!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void wrongYearIntervalTest() {
        Exception exception = Assertions.assertThrows(ResponseStatusException.class, () -> {
            holidayService.getHolidaysCount("1999-1990", "LV");
        });

        String expectedMessage = "Wrong year interval!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void wrongCountryCodeFormatTest() {
        Exception exception = Assertions.assertThrows(ResponseStatusException.class, () -> {
            holidayService.getHolidaysCount("1999-2002", "LVD");
        });

        String expectedMessage = "Wrong countryCode format!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
