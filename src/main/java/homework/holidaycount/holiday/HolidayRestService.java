package homework.holidaycount.holiday;

import homework.holidaycount.holiday.response.HolidayResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HolidayRestService {
    private final RestTemplate restTemplate;
    Logger logger = LoggerFactory.getLogger(HolidayRestService.class);

    public HolidayRestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Integer getHolidays(int year, String countryCode) {
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
