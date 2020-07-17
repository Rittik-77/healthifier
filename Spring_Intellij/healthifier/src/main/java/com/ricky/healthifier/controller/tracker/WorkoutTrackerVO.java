package com.ricky.healthifier.controller.tracker;

import java.util.Date;

public class WorkoutTrackerVO {

    private Integer id;
    private Date date;
    private String workoutName;
    private Double duration;
    private String email;

    public WorkoutTrackerVO() {

    }

    public WorkoutTrackerVO(Date date, String workoutName, Double duration, String email) {
        this.date = date;
        this.workoutName = workoutName;
        this.duration = duration;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "WorkoutTrackerVO{" +
                "id=" + id +
                ", date=" + date +
                ", workoutName='" + workoutName + '\'' +
                ", duration=" + duration +
                ", email='" + email + '\'' +
                '}';
    }
}
