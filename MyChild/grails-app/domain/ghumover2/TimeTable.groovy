package ghumover2

import java.sql.Timestamp

class TimeTable {

    Subject subject
    CalendarDate date
    Teacher teacher
    Timestamp startTime
    Timestamp endTime
    static belongsTo = [grade:Grade]
    static constraints = {
      startTime(nullable: true)
      endTime(nullable: true)
    }
}
