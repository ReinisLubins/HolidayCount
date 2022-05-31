package homework.holidaycount.holiday;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class HolidayService {
    Logger logger = LoggerFactory.getLogger(HolidayService.class);

    private final HolidayRestService holidayRestService;

    public HolidayService(HolidayRestService holidayRestService) {
        this.holidayRestService = holidayRestService;
    }

    public Integer getHolidaysCount(String yearInterval, String countryCode) {
        validRequest(yearInterval, countryCode);

        String[] years = yearInterval.split("-");
        int firstYear = Integer.parseInt(years[0]);
        int lastYear = Integer.parseInt(years[1]);

        validateYears(firstYear, lastYear);

        int holidaysCount = 0;

        for (int i = firstYear; i <= lastYear; i++) {
            holidaysCount += holidayRestService.getHolidays(i, countryCode);
        }

        return holidaysCount;
    }

    private void validRequest(String yearInterval, String countryCode) {
        if (!yearInterval.matches("\\d{4}-\\d{4}")) {
            logger.error("Wrong year format was received: " + yearInterval);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong year format!");
        }

        if (!countryCode.matches("[a-zA-Z]{2}")) {
            logger.error("Wrong countryCode format was received: " + countryCode);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong countryCode format!");
        }
    }

    private void validateYears(int from, int to) {
        if (from > to) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong year interval!");
        }
    }
}
