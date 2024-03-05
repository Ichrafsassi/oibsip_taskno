package online_reservation_system_task_1;

import java.util.*;

class User {
    private final String userId;
    private final String password;

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }
}

class Reservation {
    private final int id;
    private final String name;
    private final String date;
    private final int numberOfGuests;

    public Reservation(int id, String name, String date, int numberOfGuests) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.numberOfGuests = numberOfGuests;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }
}

class ReservationSystem {
    private final List<Reservation> reservations = new ArrayList<>();
    private int nextId = 1;

    public Reservation makeReservation(String name, String date, int numberOfGuests) {
        Reservation reservation = new Reservation(nextId++, name, date, numberOfGuests);
        reservations.add(reservation);
        return reservation;
    }

    public List<Reservation> getReservations() {
        return Collections.unmodifiableList(reservations);
    }

    public boolean cancelReservation(int id) {
        Iterator<Reservation> iterator = reservations.iterator();
        while (iterator.hasNext()) {
            Reservation reservation = iterator.next();
            if (reservation.getId() == id) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
}

class ReservationSystemUI {
    private final ReservationSystem reservationSystem = new ReservationSystem();
    private final Map<String, String> userCredentials = new HashMap<>();

    public ReservationSystemUI() {
        // Add some sample users (you can replace this with your actual user management)
        userCredentials.put("user1", "password1");
        userCredentials.put("user2", "password2");
    }

    public User authenticateUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        String storedPassword = userCredentials.getOrDefault(userId, "");
        if (storedPassword.equals(password)) {
            return new User(userId, password);
        }
        return null;
    }

    public void start() {
        User authenticatedUser;
        do {
            authenticatedUser = authenticateUser();
            if (authenticatedUser == null) {
                System.out.println("Invalid User ID or Password. Please try again.");
            }
        } while (authenticatedUser == null);

        System.out.println("Welcome, " + authenticatedUser.getUserId() + "!");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Make a reservation");
            System.out.println("2. View all reservations");
            System.out.println("3. Cancel a reservation");
            System.out.println("4. Exit");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Date: ");
                    String date = scanner.nextLine();
                    System.out.print("Number of guests: ");
                    int numberOfGuests = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    Reservation reservation = reservationSystem.makeReservation(name, date, numberOfGuests);
                    System.out.println("Reservation made with ID " + reservation.getId());
                    break;
                case 2:
                    System.out.println("Reservations:");
                    for (Reservation r : reservationSystem.getReservations()) {
                        System.out.println("Reservation ID => " + r.getId());
                        System.out.println("Name Of the customer => " + r.getName());
                        System.out.println("Number Of Guests => " + r.getNumberOfGuests());
                        System.out.println("Date => " + r.getDate());
                    }
                    break;
                case 3:
                    System.out.print("Reservation ID to cancel: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if (reservationSystem.cancelReservation(id)) {
                        System.out.println("Reservation canceled");
                    } else {
                        System.out.println("Reservation not found");
                    }
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice");
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        ReservationSystemUI reservationSystemUI = new ReservationSystemUI();
        reservationSystemUI.start();
    }
}
