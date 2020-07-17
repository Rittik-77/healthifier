package com.ricky.healthifier.entity.tracker;

import com.ricky.healthifier.entity.user.UserDTO;
import com.ricky.healthifier.entity.workout.WorkoutDTO;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "WorkoutTrackerDTO")
@Table(name = "workout_tracker")
public class WorkoutTrackerDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "date")
    private Date date;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "workout_name")
    private WorkoutDTO workoutDTO;

    @Column(name = "duration")
    private double duration;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "email")
    private UserDTO userDTO;

    public WorkoutTrackerDTO() {

    }

    public WorkoutTrackerDTO(Date date, WorkoutDTO workoutDTO, double duration, UserDTO userDTO) {
        this.date = date;
        this.workoutDTO = workoutDTO;
        this.duration = duration;
        this.userDTO = userDTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public WorkoutDTO getWorkoutDTO() {
        return workoutDTO;
    }

    public void setWorkoutDTO(WorkoutDTO workoutDTO) {
        this.workoutDTO = workoutDTO;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @Override
    public String toString() {
        return "WorkoutTrackerDTO{" +
                "id=" + id +
                ", date=" + date +
                ", workoutDTO=" + workoutDTO +
                ", duration=" + duration +
                ", userDTO=" + userDTO +
                '}';
    }
}
