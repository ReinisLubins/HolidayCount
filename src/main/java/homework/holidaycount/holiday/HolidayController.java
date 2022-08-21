package homework.holidaycount.holiday;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolidayController {
    private final HolidayService holidayService;

    public HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @GetMapping("/myperfectapp/holidaycount/{countryCode}/{yearInterval}")
    public Integer holidayCount(@PathVariable String countryCode, @PathVariable String yearInterval) {
        return holidayService.getHolidaysCount(yearInterval, countryCode);
    }
}
//working code
