package org.trivialfitness;

public class AmateurUser extends User {

    public AmateurUser(String userId, String name, String address, String email, int averageHeartRate) {
        super(userId, name, address, email, averageHeartRate);
    }


    public AmateurUser() {
        super();
    }

    @Override
    public double calculateFitnessMultiplier() {
       
        double baseMultiplier = 1.0;
        double heartRateEffect = (75 - super.getAverageHeartRate()) / 50.0; 
        return Math.max(0.9, baseMultiplier + heartRateEffect); 
    }


}

