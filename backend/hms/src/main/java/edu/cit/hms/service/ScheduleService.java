package edu.cit.hms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cit.hms.entity.ScheduleEntity;
import edu.cit.hms.repository.ScheduleRepository;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    public ScheduleEntity createSchedule(ScheduleEntity scheduleEntity) {
        return scheduleRepository.save(scheduleEntity);
    }

    public ScheduleEntity getScheduleById(int scheduleId) {
        return scheduleRepository.findById(scheduleId).orElse(null);
    }

    public List<ScheduleEntity> getSchedules() {
        return scheduleRepository.findAll();
    }

    public ScheduleEntity updateSchedule(int scheduleId, ScheduleEntity newSchedule) {
        ScheduleEntity schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Schedule ID: " + scheduleId + " not found!"));

        // Validate new data
        if(newSchedule.getDoctor() != null) {
            schedule.setDoctor(newSchedule.getDoctor());
        }
        if(newSchedule.getPatient() != null) {
            schedule.setPatient(newSchedule.getPatient());
        }
        if(newSchedule.getAppointmentDate() != null) {
            schedule.setAppointmentDate(newSchedule.getAppointmentDate());
        }
        if(newSchedule.getAppointmentTime() != null) {
            schedule.setAppointmentTime(newSchedule.getAppointmentTime());
        }
        if(newSchedule.getStatus() != null) {
            schedule.setStatus(newSchedule.getStatus());
        }

        return scheduleRepository.save(schedule);
    }

    public String deleteSchedule(int scheduleId) {
        Optional<ScheduleEntity> schedule = scheduleRepository.findById(scheduleId);

        if(schedule.isPresent()) {
            scheduleRepository.deleteById(scheduleId);

            return "Schedule ID: " + scheduleId + " deleted successfully!";
        } else {
            throw new RuntimeException("Schedule ID: " + scheduleId + " not found!");
        }
    }
}
