package org.trivialfitness;

public class ProfessionalUser extends User {

    public ProfessionalUser(String userId, String name, String address, String email, int averageHeartRate) {
        super(userId, name, address, email, averageHeartRate);
    }

    public ProfessionalUser() {
        super();
    }

    @Override
    public double calculateFitnessMultiplier() {

        double baseMultiplier = 1.2;
        double heartRateEffect = (60 - this.getAverageHeartRate()) / 50.0;  
        return Math.max(1.0, baseMultiplier + heartRateEffect);
    }


}

