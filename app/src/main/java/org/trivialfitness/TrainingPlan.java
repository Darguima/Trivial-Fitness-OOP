package org.trivialfitness;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.io.Serializable;

public class TrainingPlan implements Serializable {
    private LocalDate date;
    private List<Activitie> activities; 

    public TrainingPlan(LocalDate date) {
        this.date = date;
        this.activities = new ArrayList<>();
    }

    public TrainingPlan() {
        this.date = LocalDate.now();
        this.activities = new ArrayList<>();
    }


    public void addActivity(Activitie activity) {
        this.activities.add(activity.clone());
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Activitie> getActivities() {
      
        return activities.stream().map(Activitie::clone).collect(Collectors.toList());
    }

    public void setActivities(List<Activitie> activities) {

        this.activities = activities.stream().map(Activitie::clone).collect(Collectors.toList());
    }

    public TrainingPlan clone() {

        TrainingPlan trainingPlan = new TrainingPlan(this.date);
        trainingPlan.setActivities(this.activities);
        return trainingPlan;
    }
}
