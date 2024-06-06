import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Car {

    private String carID;
    private String brand;
    private String model;
    private double basePrisePerDay;
    private boolean isAvailable;

    public Car (String carId, String brand, String model, double basePrisePerDay){
        this.carID = carId;
        this.brand = brand;
        this.model = model;
        this.basePrisePerDay = basePrisePerDay;
        this.isAvailable = true;
    }

    public String getCarID() {
        return carID;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double caluculatePrise(int rentalDays){
        return basePrisePerDay * rentalDays;
    }

    public boolean isAvailable(){
        return isAvailable;
    }

    public void rent(){
        isAvailable = false;
    }

    public void returnCar(){
        isAvailable = true;
    }
}

class Customer{

    private String customerID;
    private String name;

    public Customer (String customerID, String name){
        this.customerID = customerID;
        this.name = name;
    }
    public String getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }
}

class Rental {

    private Car car;
    private Customer customer;
    private int days;

    public Rental(Car car, Customer customer, int days) {
        this.car = car;
        this.customer = customer;
        this.days = days;
    }

    public Car getCar() {
        return car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getDays() {
        return days;
    }
}

class CarRenalSystem{

    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;

    public CarRenalSystem(){
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addCar(Car car){
        cars.add(car);
    }

    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    public void rentCar(Car car, Customer customer, int days){
        if (car.isAvailable()){
            car.rent();
            rentals.add(new Rental(car, customer, days));
        }else {
            System.out.println("This Car is Not Available for Rent, You Can Choose Another Car");
        }
    }

    public void returnCar(Car car){
        Rental rentalToRemove = null;
       for (Rental rental : rentals){
           if (rental.getCar() == car){
               rentalToRemove = rental;
               break;
           }
           if (rentalToRemove != null){
               rentals.remove(rentalToRemove);
               car.returnCar();
           }else {
               System.out.println("Car Was Not Rented");
           }
       }
    }

    public void menu(){

        Scanner sc = new Scanner(System.in);

        while (true){
            System.out.println("\n----  Car Rental System ----\n");
            System.out.println("1. Rent a car");
            System.out.println("2. Return Car");
            System.out.println("3. Exit");
            System.out.println("\nEnter Your Choice : ");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1){

                System.out.println("\n == Rent a Car == \n");
                System.out.println("Enter Your Name : ");
                String customerName = sc.nextLine();

                System.out.println("\n== Available Cars ==");
                for (Car car : cars){
                    if (car.isAvailable()){
                        System.out.println(car.getCarID() + " " + car.getBrand() + " " + car.getModel());
                    }
                }

                System.out.println("\nEnter the car ID you want to rent : ");
                String carID = sc.nextLine();

                System.out.println("Enter the number of days you want to rent the car :");
                int rentalDays = sc.nextInt();
                sc.nextLine();

                Customer customer = new Customer("CUS" + (customers.size() + 1 ), customerName);
                addCustomer(customer);

                Car selectedCar = null;
                for (Car car : cars){
                    if (car.getCarID().equals(carID) && car.isAvailable()) {
                        selectedCar = car;
                        break;
                    }
                }

                if (selectedCar != null){
                    double totalPrise = selectedCar.caluculatePrise(rentalDays);
                    System.out.println("\n== Rental Information ==\n");
                    System.out.println("Customer Name : " + customer.getName());
                    System.out.println("Customer ID : " + customer.getCustomerID());
                    System.out.println("Car : " + selectedCar.getBrand() + " " + selectedCar.getModel());
                    System.out.println("Rental Days : " + rentalDays);
                    System.out.printf("Total Prise $%.2f%n", totalPrise);

                    System.out.println("\nConfirm Rental (Y/N) : ");
                    String confirm = sc.nextLine();

                    if (confirm.equalsIgnoreCase("Y")){
                        rentCar(selectedCar, customer, rentalDays);
                        System.out.println("\nCar Rented Successfully ");
                    }else {
                        System.out.println("Rental Canceled");
                    }
                }else {
                    System.out.println("\nInvalid car selection or Car is not Available for Rent");
                }

            } else if (choice == 2) {

                System.out.println("== Return Car ==");
                System.out.println("Enter the Car ID you want to return : ");
                String carID = sc.nextLine();

                Car carToReturn = null;
                for (Car car : cars){
                    if (car.getCarID().equals(carID) && !car.isAvailable()){
                        carToReturn = car;
                        break;
                    }
                }

                if (carToReturn != null){
                    Customer availavleCustomer = null;
                    for (Rental rental : rentals){
                        if (rental.getCar() == carToReturn){
                            availavleCustomer = rental.getCustomer();
                            break;
                        }
                    }

                    if (availavleCustomer != null){
                        returnCar(carToReturn);
                        System.out.println("Car Returned Successfully by : " + availavleCustomer.getName());
                    }else {
                        System.out.println("Car was not rented or Rental information is missing");
                    }
                }else {
                    System.out.println("\nInvalid Car Id or Car was not rented");
                }

            }else if (choice == 3){
                break;
            }else {
                System.out.println("\nInvalid Choice. Please Enter a valid option.");
            }
        }

    }


}
public class Main {
    public static void main(String[] args) {

        CarRenalSystem rentalSystem = new CarRenalSystem();

        Car car1 = new Car("C001", "Toyota", "Camry", 60.0); // Different base price per day for each car
        Car car2 = new Car("C002", "Honda", "Accord", 70.0);
        Car car3 = new Car("C003", "Mahindra", "Thar", 150.0);
        rentalSystem.addCar(car1);
        rentalSystem.addCar(car2);
        rentalSystem.addCar(car3);

        rentalSystem.menu();
    }
}