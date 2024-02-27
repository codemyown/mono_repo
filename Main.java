import java.util.ArrayList;
import java.util.List;

class Passenger {
    private String name;
    private int passengerNumber;
    private PassengerType type;
    private double balance;
    private List<Activity> activitiesSignedUp;

    public Passenger(String name, int passengerNumber, PassengerType type, double balance) {
        this.name = name;
        this.passengerNumber = passengerNumber;
        this.type = type;
        this.balance = balance;
        this.activitiesSignedUp = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getPassengerNumber() {
        return passengerNumber;
    }

    public PassengerType getType() {
        return type;
    }

    public double getBalance() {
        return balance;
    }

    public List<Activity> getActivitiesSignedUp() {
        return activitiesSignedUp;
    }

    public void signUpForActivity(Activity activity) {
        if (type == PassengerType.STANDARD) {
            if (balance >= activity.getCost()) {
                balance -= activity.getCost();
                activitiesSignedUp.add(activity);
                System.out.println(name + " signed up for " + activity.getName());
            } else {
                System.out.println("Insufficient balance to sign up for " + activity.getName());
            }
        } else if (type == PassengerType.GOLD) {
            double discountedCost = activity.getCost() * 0.9;
            if (balance >= discountedCost) {
                balance -= discountedCost;
                activitiesSignedUp.add(activity);
                System.out.println(name + " signed up for " + activity.getName() + " with 10% discount");
            } else {
                System.out.println("Insufficient balance to sign up for " + activity.getName());
            }
        } else { // PREMIUM
            activitiesSignedUp.add(activity);
            System.out.println(name + " signed up for " + activity.getName() + " as a premium passenger");
        }
    }

    public void printActivitiesSignedUp() {
        System.out.println("Activities signed up by " + name + ":");
        for (Activity activity : activitiesSignedUp) {
            System.out.println("- " + activity.getName() + " at " + activity.getDestination().getName() + ", Cost: "
                    + activity.getCost());
        }
    }
}

enum PassengerType {
    STANDARD, GOLD, PREMIUM
}

class Activity {
    private String name;
    private String description;
    private double cost;
    private int capacity;
    private Destination destination;
    private List<Passenger> passengersSignedUp;

    public Activity(String name, String description, double cost, int capacity, Destination destination) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.capacity = capacity;
        this.destination = destination;
        this.passengersSignedUp = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getCost() {
        return cost;
    }

    public int getCapacity() {
        return capacity;
    }

    public Destination getDestination() {
        return destination;
    }

    public List<Passenger> getPassengersSignedUp() {
        return passengersSignedUp;
    }

    public void addPassenger(Passenger passenger) {
        if (passengersSignedUp.size() < capacity) {
            passengersSignedUp.add(passenger);
            System.out.println(passenger.getName() + " signed up for " + name);
        } else {
            System.out.println("Activity " + name + " at " + destination.getName() + " is already full");
        }
    }
}

class Destination {
    private String name;
    private List<Activity> activities;

    public Destination(String name) {
        this.name = name;
        this.activities = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }
}

class TravelPackage {
    private String name;
    private int passengerCapacity;
    private List<Destination> itinerary;
    private List<Passenger> passengers;

    public TravelPackage(String name, int passengerCapacity) {
        this.name = name;
        this.passengerCapacity = passengerCapacity;
        this.itinerary = new ArrayList<>();
        this.passengers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public List<Destination> getItinerary() {
        return itinerary;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void addDestination(Destination destination) {
        itinerary.add(destination);
    }

    public void addPassenger(Passenger passenger) {
        if (passengers.size() < passengerCapacity) {
            passengers.add(passenger);
            System.out.println(passenger.getName() + " added to " + name);
        } else {
            System.out.println("Cannot add " + passenger.getName() + ", " + name + " is full");
        }
    }

    public void printItinerary() {
        System.out.println("Itinerary for " + name + ":");
        for (Destination destination : itinerary) {
            System.out.println("- " + destination.getName() + ":");
            for (Activity activity : destination.getActivities()) {
                System.out.println("  * " + activity.getName() + ", Cost: " + activity.getCost());
            }
        }
    }

    public void printPassengerList() {
        System.out.println("Passenger list for " + name + ":");
        System.out.println("Capacity: " + passengerCapacity);
        System.out.println("Number of passengers: " + passengers.size());
        for (Passenger passenger : passengers) {
            System.out.println("- " + passenger.getName() + ", Passenger Number: " + passenger.getPassengerNumber());
        }
    }

    public void printPassengerDetails(Passenger passenger) {
        System.out.println("Details for passenger " + passenger.getName() + ":");
        System.out.println("Passenger Number: " + passenger.getPassengerNumber());
        if (passenger.getType() != PassengerType.PREMIUM) {
            System.out.println("Balance: " + passenger.getBalance());
        }
        passenger.printActivitiesSignedUp();
    }

    public void printAvailableActivities() {
        System.out.println("Available activities for " + name + ":");
        for (Destination destination : itinerary) {
            for (Activity activity : destination.getActivities()) {
                int remainingCapacity = activity.getCapacity() - activity.getPassengersSignedUp().size();
                System.out.println("- " + activity.getName() + " at " + destination.getName() + ", Remaining Capacity: "
                        + remainingCapacity);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Create destinations
        Destination destination1 = new Destination("Destination 1");
        Destination destination2 = new Destination("Destination 2");

        // Create activities
        Activity activity1 = new Activity("Activity 1", "Description 1", 100, 3, destination1);
        Activity activity2 = new Activity("Activity 2", "Description 2", 150, 2, destination2);

        // Add activities to destinations
        destination1.addActivity(activity1);
        destination2.addActivity(activity2);

        // Create travel package
        TravelPackage travelPackage = new TravelPackage("Travel Package 1", 2);

        // Add destinations to travel package itinerary
        travelPackage.addDestination(destination1);
        travelPackage.addDestination(destination2);

        // Create passengers
        Passenger passenger1 = new Passenger("Passenger 1", 1, PassengerType.STANDARD, 200);
        Passenger passenger2 = new Passenger("Passenger 2", 2, PassengerType.GOLD, 300);

        // Add passengers to travel package
        travelPackage.addPassenger(passenger1);
        travelPackage.addPassenger(passenger2);

        // Sign up passengers for activities
        passenger1.signUpForActivity(activity1);
        passenger2.signUpForActivity(activity2);

        // Print itinerary, passenger list, and available activities
        travelPackage.printItinerary();
        travelPackage.printPassengerList();
        travelPackage.printPassengerDetails(passenger1);
        travelPackage.printAvailableActivities();
    }
}
