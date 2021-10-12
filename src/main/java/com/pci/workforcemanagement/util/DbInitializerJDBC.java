package com.pci.workforcemanagement.util;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbInitializerJDBC {

    private static final DataRetriever dataRetriever = new DataRetriever();
    private static final String URL = "http://pizzacabininc.azurewebsites.net/PizzaCabinInc.svc/schedule/2015-12-14";

    public void writeToDatabase() throws SQLException {
        var arrayOfSchedules = dataRetriever.getJsonData(URL);

        for(var i = 0; i < arrayOfSchedules.size(); i ++) {
            var scheduleJson = arrayOfSchedules.get(i).getAsJsonObject();

            var date = new Date(Long.parseLong(scheduleJson.get("Date").toString().substring(7, 20)));
            var personId = scheduleJson.get("PersonId").toString().replace("\"", "");
            var name = scheduleJson.get("Name").toString().replace("\"", "");
            var isFullDayAbsence = Boolean.parseBoolean(scheduleJson.get("IsFullDayAbsence").toString());
            var contractTimeMinutes = Integer.parseInt(scheduleJson.get("ContractTimeMinutes").toString());
            var projection = scheduleJson.get("Projection").getAsJsonArray();

            var conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3308/pizza_club?useSSL=false&serverTimezone=UTC&max_allowed_packet=15728640&useUnicode=yes&characterEncoding=GBK&allowPublicKeyRetrieval=true",
                    "pizza",
                    "admin");
            var insertScheduleSql = "INSERT INTO schedule (`contract_time_minutes`, `date`, `is_full_day_absence`, `name`, `person_id`) VALUES(?, ?, ?, ?, ?)";
            try (var ps = conn.prepareStatement(insertScheduleSql)) {
                ps.setInt(1, contractTimeMinutes);
                ps.setDate(2, date);
                ps.setBoolean(3, isFullDayAbsence);
                ps.setString(4, name);
                ps.setString(5, personId);
                ps.executeUpdate();
            }

            var getLastIdSql = "SELECT max(id) FROM schedule";
            var lastScheduleId = 0;
            var lastActivityIds = new ArrayList<Integer>();
            try(var ps = conn.prepareStatement(getLastIdSql)) {
                var resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    lastScheduleId = resultSet.getInt("max(id)");
                }
            }

            for (var j = 0; j < projection.size(); j ++) {
                var activityJson = projection.get(j).getAsJsonObject();

                var color = activityJson.get("Color").toString().replace("\"", "");
                var description = activityJson.get("Description").toString().replace("\"", "");
                var start = new Date(Long.parseLong(activityJson.get("Start").toString().substring(7, 20)));
                var minutes = Integer.parseInt(activityJson.get("minutes").toString());

                var insertActivitySql = "INSERT INTO activity (`color`, `description`, `minutes`, `start`) VALUES (?, ?, ?, ?)";
                try(var ps = conn.prepareStatement(insertActivitySql)){
                    ps.setString(1, color);
                    ps.setString(2, description);
                    ps.setInt(3, minutes);
                    ps.setDate(4, start);
                    ps.executeUpdate();
                }

                var getLastActivityIdSql = "SELECT max(id) FROM activity";
                var lastActivityId = 0;
                try (var ps = conn.prepareStatement(getLastActivityIdSql)) {
                    var resultSet = ps.executeQuery();
                    while (resultSet.next()) {
                        lastActivityId = resultSet.getInt("max(id)");
                    }
                }
                lastActivityIds.add(lastActivityId);
            }

            var insertScheduleActivitiesSQl = "INSERT INTO schedule_activities (`schedule_id`, `activities_id`) VALUES (?, ?)";
            for (var k = 0; k < lastActivityIds.size(); k ++) {
                try(var ps = conn.prepareStatement(insertScheduleActivitiesSQl)) {
                    ps.setInt(1, lastScheduleId);
                    ps.setInt(2, lastActivityIds.get(k));
                    ps.executeUpdate();
                }
            }
        }
    }
}
