package com.pci.workforcemanagement.util;

import com.pci.workforcemanagement.model.Activity;
import com.pci.workforcemanagement.model.Schedule;
import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbInitializer {

    private static final DataRetriever dataRetriever = new DataRetriever();
    private static final String BASE_URL = "http://pizzacabininc.azurewebsites.net/PizzaCabinInc.svc/schedule/";
    private List<Schedule> schedules = new ArrayList<>();

    public Iterable<Schedule> getSchedules(String dateString) throws SQLException {
        var arrayOfSchedules = dataRetriever.getJsonData(BASE_URL + dateString);

        for(var i = 0; i < arrayOfSchedules.size(); i ++) {
            var schedule = new Schedule();
            var activities = new ArrayList<Activity>();

            var scheduleJson = arrayOfSchedules.get(i).getAsJsonObject();

            var date = new Date(Long.parseLong(scheduleJson.get("Date").toString().substring(7, 20)));
            var personId = scheduleJson.get("PersonId").toString().replace("\"", "");
            var name = scheduleJson.get("Name").toString().replace("\"", "");
            var isFullDayAbsence = Boolean.parseBoolean(scheduleJson.get("IsFullDayAbsence").toString());
            var contractTimeMinutes = Integer.parseInt(scheduleJson.get("ContractTimeMinutes").toString());
            var projection = scheduleJson.get("Projection").getAsJsonArray();

            var activity = new Activity();
            for (var j = 0; j < projection.size(); j ++) {
                var activityJson = projection.get(j).getAsJsonObject();

                var color = activityJson.get("Color").toString().replace("\"", "");
                var description = activityJson.get("Description").toString().replace("\"", "");
                var start = new Date(Long.parseLong(activityJson.get("Start").toString().substring(7, 20)));
                var minutes = Integer.parseInt(activityJson.get("minutes").toString());

                activity.setColor(color);
                activity.setDescription(description);
                activity.setStart(start);
                activity.setMinutes(minutes);
                activities.add(activity);
            }
            schedule.setDate(date);
            schedule.setContractTimeMinutes(contractTimeMinutes);
            schedule.setFullDayAbsence(isFullDayAbsence);
            schedule.setPersonId(personId);
            schedule.setName(name);
            schedule.setActivities(activities);
            schedules.add(schedule);
        }

        return schedules;
    }
}
