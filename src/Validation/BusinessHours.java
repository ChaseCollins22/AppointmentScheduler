package Validation;

import java.time.*;
import java.util.TimeZone;

public class BusinessHours {
    public static boolean isInBusinessHours(LocalDateTime Start, LocalDateTime End) {
        //Get Local Timezone
        ZoneId localZoneID = ZoneId.of(TimeZone.getDefault().getID());

        //Convert Input: Start, End to ZonedDateTime
        ZonedDateTime localZDTStart = ZonedDateTime.of(Start, localZoneID);
        ZonedDateTime localZDTEnd = ZonedDateTime.of(End, localZoneID);

        //Convert Input: LocalTime Start to EST
        Instant localToESTStart = localZDTStart.toInstant();
        ZonedDateTime localToESTStartFinal = localToESTStart.atZone(ZoneId.of("America/New_York"));

        //Convert Input: LocalTime End to EST
        Instant localTOUTCEnd = localZDTEnd.toInstant();
        ZonedDateTime localToESTEndFinal = localTOUTCEnd.atZone(ZoneId.of("America/New_York"));

        //Reassign Input: Start, End to LocalDateTime with EST value
        Start = localToESTStartFinal.toLocalDateTime();
        End = localToESTEndFinal.toLocalDateTime();

        LocalTime checkAfterEightAM = LocalTime.parse("07:59:59");
        LocalTime checkBeforeTenPM = LocalTime.parse("22:00:00");

        if (Start.toLocalTime().isAfter(checkAfterEightAM) && End.toLocalTime().isBefore(checkBeforeTenPM)) {
            return true;
        }
        return false;
    }
}
