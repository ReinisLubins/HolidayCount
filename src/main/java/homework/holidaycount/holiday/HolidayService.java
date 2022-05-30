package homework.holidaycount.holiday;

import homework.holidaycount.holiday.response.HolidayResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HolidayService {

    private final RestTemplate restTemplate;
    Logger logger = LoggerFactory.getLogger(HolidayService.class);

    public HolidayService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Integer getHolidaysCount(String year, String countryCode) {
        if (year.contains("-")) {
            int holidaysCount = 0;

            String[] yearInterval = year.split("-");
            int firstYear = Integer.parseInt(yearInterval[0]);
            int lastYear = Integer.parseInt(yearInterval[1]);

            for (int i = firstYear; i <= lastYear; i++) {
                holidaysCount += getHolidays(String.valueOf(i), countryCode);
            }

            return holidaysCount;
        } else {
            return getHolidays(year, countryCode);
        }
    }

    private Integer getHolidays(String year, String countryCode) {
        String url = "https://date.nager.at/api/v3/PublicHolidays/" + year + "/" + countryCode;
        HolidayResponse[] holidays = new HolidayResponse[0];
        try {
            ResponseEntity<HolidayResponse[]> response = restTemplate.getForEntity(url, HolidayResponse[].class);
            if (response.getBody() != null) {
                holidays = response.getBody();
            }
        } catch (Exception e) {
            logger.error("Could not retrieve holidays", e);
        }

        return holidays.length;
    }
}
