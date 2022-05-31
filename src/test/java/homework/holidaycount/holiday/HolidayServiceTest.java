package homework.holidaycount.holiday;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class HolidayServiceTest {

    @Mock
    HolidayRestService holidayRestService;

    @InjectMocks
    HolidayService holidayService;

    @Captor
    ArgumentCaptor<Integer> yearCaptor;

    @Captor
    ArgumentCaptor<String> countryCaptor;

    @Test
    void successfulScenarioTest() {
        Mockito.when(holidayRestService.getHolidays(1999, "LV")).thenReturn(10);
        Mockito.when(holidayRestService.getHolidays(2000, "LV")).thenReturn(13);
        Mockito.when(holidayRestService.getHolidays(2001, "LV")).thenReturn(11);
        Mockito.when(holidayRestService.getHolidays(2002, "LV")).thenReturn(12);

        Integer count = holidayService.getHolidaysCount("1999-2002", "LV");

        Assertions.assertEquals(46, count);
        Mockito.verify(holidayRestService, Mockito.times(4)).getHolidays(yearCaptor.capture(), countryCaptor.capture());

        List<String> countriesCalled = countryCaptor.getAllValues();
        List<Integer> yearsCalled = yearCaptor.getAllValues();

        Assertions.assertEquals(Arrays.asList("LV", "LV", "LV", "LV"), countriesCalled);
        Assertions.assertEquals(Arrays.asList(1999, 2000, 2001, 2002), yearsCalled);
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
