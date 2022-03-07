package me.leewooooo.hellobitecode;

public class Class {

    int numberOfMaxAttendees;
    int numberOfEnrollment;

    public boolean isEnrollmentFull() {
        if(numberOfMaxAttendees == 0){
            return false;
        }

        if (numberOfEnrollment < numberOfMaxAttendees){
            return false;
        }

        return true;
    }
}
