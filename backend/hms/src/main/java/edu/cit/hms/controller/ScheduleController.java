package edu.cit.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.cit.hms.entity.ScheduleEntity;
import edu.cit.hms.service.ScheduleService;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/all")
    public List<ScheduleEntity> getSchedules() {
        return scheduleService.getSchedules();
    }

    @GetMapping("/{id}")
    public ScheduleEntity getScheduleById(@PathVariable int id) {
        return scheduleService.getScheduleById(id);
    }

    @PostMapping("/add")
    public ScheduleEntity createSchedule(@RequestBody ScheduleEntity schedule) {
        return scheduleService.createSchedule(schedule);
    }

    @PutMapping("/update/{id}")
    public ScheduleEntity updateSchedule(@PathVariable int scheduleId, @RequestBody ScheduleEntity schedule) {
        return scheduleService.updateSchedule(scheduleId, schedule);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSchedule(@PathVariable int id) {
        scheduleService.deleteSchedule(id);
    }
}
