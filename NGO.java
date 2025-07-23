package NGos;

import java.util.*;
import java.util.InputMismatchException;

// implementation using linkedlist
class Donation {
    int donationId;
    String nameOfDonar;
    String contactInfo;
    double amountDonated;
    Donation next;

    public Donation(int donationId, String nameOfDonar, String contactInfo, double amountDonated) {
        this.donationId = donationId;
        this.nameOfDonar = nameOfDonar;
        this.contactInfo = contactInfo;
        this.amountDonated = amountDonated;
        this.next = null;
    }

    public void displayDonationDetails() {
        System.out.println("Donation ID: " + this.donationId + ", Name: " + this.nameOfDonar +
                ", Contact: " + this.contactInfo + ", Amount: Rs " + this.amountDonated);
    }
}

class Donations {
    private Donation head;

    public Donations() {
        head = null;
    }

    // Method to add a donation
    public void addDonation() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter Donor ID: ");
            int donationId = scanner.nextInt();
            scanner.nextLine(); // consume newline

            System.out.print("Enter Donor Name: ");
            String nameOfDonar = scanner.nextLine();

            System.out.print("Enter Contact Info: ");
            String contactInfo = scanner.nextLine();

            System.out.print("Enter Amount Donated: ");
            double amount = scanner.nextDouble();

            Donation newDonation = new Donation(donationId, nameOfDonar, contactInfo, amount);
            newDonation.next = head;
            head = newDonation;

            System.out.println("Donation added successfully!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid numeric values for ID and amount.");
            scanner.nextLine(); // clear the invalid input
        }
    }


    // Method to display all donations
    public void displayAllDonations() {
        if (head == null) {
            System.out.println("No donations recorded.");
            return;
        }

        System.out.println("Displaying all donations:");
        Donation current = head;
        while (current != null) {
            current.displayDonationDetails();
            current = current.next;
        }
    }

    // Method to search and update donation by ID
    public void updateDonation() {  //update phoneNumber and amount donated
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Donor ID to update: ");
        int donationId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Donation donation = searchDonation(donationId);
        if (donation != null) {
            System.out.print("Enter new Contact Info: ");
            donation.contactInfo = scanner.nextLine();
            System.out.print("Enter new Amount Donated: ");
            donation.amountDonated = scanner.nextDouble();
            System.out.println("Donation updated successfully!");
        } else {
            System.out.println("Donation record not found for ID: " + donationId);
        }
    }

    // Helper method to search for a donation by ID
    private Donation searchDonation(int donationId) {
        Donation current = head;
        while (current != null) {
            if (current.donationId == donationId) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    // Method to sort donations by amount using quick sort
    public void quickSortDonationsByAmount() {
        head = quickSort(head, null);
        System.out.println("Donations sorted by amount.");
    }

    private Donation quickSort(Donation start, Donation end) {
        if (start == end || start == null || start.next == end) {
            return start;
        }

        Donation[] partitioned = partition(start, end);
        Donation sortedStart = quickSort(partitioned[0], partitioned[1]);
        partitioned[2].next = quickSort(partitioned[3], end);
        return sortedStart;
    }

    // Helper method for partitioning
    private Donation[] partition(Donation start, Donation end) {
        double pivot = start.amountDonated;
        Donation lessHead = new Donation(0, "", "", 0), lessTail = lessHead;
        Donation equalHead = start, equalTail = equalHead;
        Donation greaterHead = new Donation(0, "", "", 0), greaterTail = greaterHead;
        Donation current = start.next;

        while (current != end) {
            if (current.amountDonated < pivot) {
                lessTail.next = current;
                lessTail = lessTail.next;
            } else if (current.amountDonated == pivot) {
                equalTail.next = current;
                equalTail = equalTail.next;
            } else {
                greaterTail.next = current;
                greaterTail = greaterTail.next;
            }
            current = current.next;
        }

        lessTail.next = equalHead;
        equalTail.next = greaterHead.next;
        greaterTail.next = end;

        return new Donation[]{lessHead.next, equalHead, equalTail, greaterHead.next};
    }

    // Method to calculate the total donation amount
    public double totalDonationAmount() {
        double total = 0;
        Donation current = head;
        while (current != null) {
            total += current.amountDonated;
            current = current.next;
        }
        return total;
    }
}
//class Donations {
//    private Donation head;
//
//    public void addDonation() {
//        Scanner scanner = new Scanner(System.in);
//        try {
//            System.out.print("Enter Donor ID: ");
//            int donationId = scanner.nextInt();
//            scanner.nextLine();
//            System.out.print("Enter Donor Name: ");
//            String name = scanner.nextLine();
//            System.out.print("Enter Contact Info: ");
//            String contact = scanner.nextLine();
//            System.out.print("Enter Amount Donated: ");
//            double amount = scanner.nextDouble();
//
//            Donation newDonation = new Donation(donationId, name, contact, amount);
//            newDonation.next = head;
//            head = newDonation;
//
//            System.out.println("Donation added successfully.");
//        } catch (InputMismatchException e) {
//            System.out.println("Invalid input. Please enter correct data.");
//            scanner.nextLine();
//        }
//    }
//
//    public void displayAllDonations() {
//        if (head == null) {
//            System.out.println("No donations recorded.");
//            return;
//        }
//        Donation current = head;
//        while (current != null) {
//            current.displayDonationDetails();
//            current = current.next;
//        }
//    }
//
//    public void updateDonationById() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter Donor ID to update: ");
//        int id = scanner.nextInt();
//        scanner.nextLine();
//        Donation donation = searchDonation(id);
//        if (donation != null) {
//            System.out.print("Enter new Contact Info: ");
//            donation.contactInfo = scanner.nextLine();
//            System.out.print("Enter new Donation Amount: ");
//            donation.amountDonated = scanner.nextDouble();
//            System.out.println("Donation updated.");
//        } else {
//            System.out.println("Donation not found.");
//        }
//    }
//
//    public void displayDonationsById() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter Donor ID to search: ");
//        int id = scanner.nextInt();
//        Donation current = head;
//        boolean found = false;
//        while (current != null) {
//            if (current.donationId == id) {
//                current.displayDonationDetails();
//                found = true;
//            }
//            current = current.next;
//        }
//        if (!found) System.out.println("No donations found for this ID.");
//    }
//
//    private Donation searchDonation(int id) {
//        Donation current = head;
//        while (current != null) {
//            if (current.donationId == id) return current;
//            current = current.next;
//        }
//        return null;
//    }
//
//    public double totalDonationAmount() {
//        double total = 0;
//        Donation current = head;
//        while (current != null) {
//            total += current.amountDonated;
//            current = current.next;
//        }
//        return total;
//    }
//
//    public void sortDonationsByAmount() {
//        head = quickSort(head, null);
//        System.out.println("Donations sorted by amount.");
//    }
//
//    private Donation quickSort(Donation start, Donation end) {
//        if (start == end || start == null || start.next == end)
//            return start;
//
//        Donation[] parts = partition(start, end);
//        Donation sortedStart = quickSort(parts[0], parts[1]);
//        parts[2].next = quickSort(parts[3], end);
//        return sortedStart;
//    }
//
//    private Donation[] partition(Donation start, Donation end) {
//        double pivot = start.amountDonated;
//        Donation lessHead = new Donation(0, "", "", 0), lessTail = lessHead;
//        Donation equalHead = start, equalTail = equalHead;
//        Donation greaterHead = new Donation(0, "", "", 0), greaterTail = greaterHead;
//        Donation current = start.next;
//
//        while (current != end) {
//            if (current.amountDonated < pivot) {
//                lessTail.next = current;
//                lessTail = current;
//            } else if (current.amountDonated == pivot) {
//                equalTail.next = current;
//                equalTail = current;
//            } else {
//                greaterTail.next = current;
//                greaterTail = current;
//            }
//            current = current.next;
//        }
//
//        lessTail.next = equalHead;
//        equalTail.next = greaterHead.next;
//        greaterTail.next = end;
//
//        return new Donation[]{lessHead.next, equalHead, equalTail, greaterHead.next};
//    }
//}
// Volunteer class to represent each volunteer
class Volunteer {
    int id;
    String name;
    String skills;

    // Constructor to initialize Volunteer
    public Volunteer(int id, String name, String skills) {
        this.id = id;
        this.name = name;
        this.skills = skills;
    }

    // Method to display volunteer details
    public void displayVolunteerDetails() {
        System.out.println("ID: " + this.id + ", Name: " + this.name + ", Skills: " + this.skills);
    }
}

// VolunteerQueue class to manage volunteers using a queue (array-based)
class VolunteerQueue {
    private Volunteer[] queue;
    private int front, rear, size;
    private static final int MAX_SIZE = 10;

    public VolunteerQueue() {
        queue = new Volunteer[MAX_SIZE];
        front = rear = -1;
        size = 0;
    }

    // Method to add a volunteer to the queue (takes user inputs)
    public void addVolunteer() {
        if (size == MAX_SIZE) {
            System.out.println("Queue is full. Cannot add more volunteers.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        try {
            // Taking user inputs for volunteer details
            System.out.print("Enter Volunteer ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();  // Consume the leftover newline character

            System.out.print("Enter Volunteer Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Volunteer Skills: ");
            String skills = scanner.nextLine();

            Volunteer newVolunteer = new Volunteer(id, name, skills);

            if (rear == -1) {  // If the queue is empty
                front = rear = 0;
            } else {
                rear = (rear + 1) % MAX_SIZE;  // Circular queue logic
            }
            queue[rear] = newVolunteer;
            size++;
            System.out.println("Volunteer added: " + name);

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct data types.");
            scanner.nextLine();  // Clear the invalid input from the scanner
        }
    }

    // Method to display all volunteers
    public void viewAllVolunteers() {
        if (size == 0) {
            System.out.println("No volunteers in the queue.");
        } else {
            System.out.println("Listing all volunteers: ");
            for (int i = 0; i < size; i++) {
                int index = (front + i) % MAX_SIZE;
                try {
                    if (queue[index] != null) {
                        queue[index].displayVolunteerDetails();
                    } else {
                        System.out.println("Error: Null volunteer at position " + index);
                    }
                } catch (NullPointerException e) {
                    System.out.println("Error: Attempted to access a null volunteer.");
                }
            }
        }
    }

    // Method to search for a volunteer by ID
    public Volunteer searchVolunteerById(int id) {
        for (int i = 0; i < size; i++) {
            int index = (front + i) % MAX_SIZE;
            if (queue[index] != null && queue[index].id == id) {
                return queue[index];
            }
        }
        System.out.println("Volunteer with ID " + id + " not found.");
        return null;
    }
}
// Event class with attributes: event-id, date, location, and status
class Event {
    int eventId;
    String date;
    String location;
    Volunteer assignedVolunteer; // Volunteer assigned to the event

    // Constructor to initialize the Event object
    public Event(int eventId, String date, String location) {
        this.eventId = eventId;
        this.date = date;
        this.location = location;
        this.assignedVolunteer = null; // Initially, no volunteer is assigned
    }

    // Method to display event details
    public void displayEventDetails() {
        System.out.println("Event ID: " + this.eventId);
        System.out.println("Date: " + this.date);
        System.out.println("Location: " + this.location);
        if (assignedVolunteer != null) {
            System.out.println("Assigned Volunteer: " + assignedVolunteer.name);
        } else {
            System.out.println("No volunteer assigned.");
        }
    }

    // Method to update event details
    public void updateEventDetails(String date, String location) {
        this.date = date;
        this.location = location;
        System.out.println("Event details updated.");
    }
}

// EventList class to manage a list of events (LinkedList)
class EventList {
    private EventNode start; // Start node of the linked list

    // Constructor to initialize the event list
    public EventList() {
        this.start = null;
    }

    // Method to add an event to the list
    public void createEvent() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Event ID: ");
        int eventId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter Event Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        System.out.print("Enter Event Location: ");
        String location = scanner.nextLine();


        Event newEvent = new Event(eventId, date, location);

        // Create a new node to insert into the linked list
        EventNode newNode = new EventNode(newEvent);

        // If the list is empty, start points to the new node
        if (start == null) {
            start = newNode;
        } else {
            EventNode ptr = start;
            // Traverse the list to reach the last node
            while (ptr.link != null) {
                ptr = ptr.link;
            }
            ptr.link = newNode; // Attach new node at the end
        }

        System.out.println("Event added successfully.");
    }

    // Method to display all events
    public void displayAllEvents() {
        if (start == null) {
            System.out.println("No events in the list.");
            return;
        }

        EventNode ptr = start;
        while (ptr != null) {
            ptr.event.displayEventDetails();
            ptr = ptr.link;
        }
    }

    // Method to search for an event by Event ID
    public void searchEvent() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Event ID to search: ");
        int eventId = scanner.nextInt();

        EventNode ptr = start;
        boolean found = false;

        while (ptr != null) {
            if (ptr.event.eventId == eventId) {
                ptr.event.displayEventDetails();
                found = true;
                break;
            }
            ptr = ptr.link;
        }

        if (!found) {
            System.out.println("Event with ID " + eventId + " not found.");
        }
    }

    // Method to assign a volunteer to an event based on skills
    public void assignVolunteerToEvent(VolunteerQueue volunteerQueue) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Event ID to assign a volunteer: ");
        int eventId = scanner.nextInt();
        scanner.nextLine();
        EventNode ptr = start;
        boolean foundEvent = false;

        // Find the event
        while (ptr != null) {
            if (ptr.event.eventId == eventId) {
                foundEvent = true;
                System.out.println("Event found: ");
                ptr.event.displayEventDetails();
                break;
            }
            ptr = ptr.link;
        }

        if (!foundEvent) {
            System.out.println("Event with ID " + eventId + " not found.");
            return;
        }

        // Display volunteer list
        volunteerQueue.viewAllVolunteers();

        System.out.print("Enter Volunteer ID to assign: ");
        int volunteerId = scanner.nextInt();

        // Search for the volunteer
        Volunteer volunteer = volunteerQueue.searchVolunteerById(volunteerId);

        if (volunteer != null) {
            ptr.event.assignedVolunteer = volunteer;
            System.out.println("Volunteer assigned to the event.");
        } else {
            System.out.println("Volunteer not found.");
        }
    }

    // Method to update event details
    public void updateEventDetails() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Event ID to update: ");
        int eventId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        EventNode ptr = start;
        boolean foundEvent = false;

        while (ptr != null) {
            if (ptr.event.eventId == eventId) {
                foundEvent = true;
                System.out.println("Event found: ");
                ptr.event.displayEventDetails();

                // Get new details from the user
                System.out.print("Enter new Event Date (YYYY-MM-DD): ");
                String newDate = scanner.nextLine();
                System.out.print("Enter new Event Location: ");
                String newLocation = scanner.nextLine();

                // Update the event details
                ptr.event.updateEventDetails(newDate, newLocation);
                break;
            }
            ptr = ptr.link;
        }

        if (!foundEvent) {
            System.out.println("Event with ID " + eventId + " not found.");
        }
    }
}

// Node class for event linked list
class EventNode {
    Event event;
    EventNode link;

    // Constructor to initialize the node with an event
    public EventNode(Event event) {
        this.event = event;
        this.link = null;
    }
}

class StockItem {
    String itemName;
    int quantity;

    public StockItem(String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Item Name: " + itemName + ", Quantity: " + quantity;
    }
}

// Node for Binary Search Tree (BST)
class BSTNode {
    StockItem item;
    BSTNode left, right;

    public BSTNode(StockItem item) {
        this.item = item;
        left = right = null;
    }
}

// Binary Search Tree (BST) for stock inventory
class InventoryBST {
    private BSTNode root;

    public InventoryBST() {
        root = null;
    }

    // Insert a new stock item into BST
    public void insert(StockItem item) {
        root = insertRec(root, item);
    }

    private BSTNode insertRec(BSTNode root, StockItem item) {
        if (root == null) {
            return new BSTNode(item);
        }

        if (item.itemName.compareTo(root.item.itemName) < 0) {
            root.left = insertRec(root.left, item);
        } else if (item.itemName.compareTo(root.item.itemName) > 0) {
            root.right = insertRec(root.right, item);
        }
        return root;
    }

    // Search for a stock item in BST
    public StockItem search(String itemName) {
        return searchRec(root, itemName);
    }

    private StockItem searchRec(BSTNode root, String itemName) {
        if (root == null || root.item.itemName.equals(itemName)) {
            if(root!=null){
                return root.item;

            }else {
                return null;
            }
        }
        if (itemName.compareTo(root.item.itemName) < 0) {
            return searchRec(root.left, itemName);
        }
        return searchRec(root.right, itemName);
    }

    // Update stock quantity or remove item if quantity becomes zero
    public void updateQuantityOrRemove(String itemName, int quantityChange) {
        StockItem item = search(itemName);
        if (item != null) {
            item.quantity += quantityChange;
            if (item.quantity <= 0) {
                root = remove(root, itemName); // Remove item if quantity is zero
            }
        }
    }

    private BSTNode remove(BSTNode root, String itemName) {
        if (root == null) return null;
        if (itemName.compareTo(root.item.itemName) < 0) {
            root.left = remove(root.left, itemName);
        } else if (itemName.compareTo(root.item.itemName) > 0) {
            root.right = remove(root.right, itemName);
        } else {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;
            root.item = findMin(root.right).item;
            root.right = remove(root.right, root.item.itemName);
        }
        return root;
    }

    private BSTNode findMin(BSTNode root) {
        while (root.left != null) root = root.left;
        return root;
    }

    // Display all stock items (In-order traversal)
    public void displayInventory() {
        if (root == null) {
            System.out.println("Inventory is empty.");
        } else {
            System.out.println("Inventory:");
            inorderRec(root);
        }
    }

    private void inorderRec(BSTNode root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println(root.item);
            inorderRec(root.right);
        }
    }
}

// Represents a resource request with priority
class Request {
    //int priority;
    String itemRequested;
    int quantity;
    String status;

    public Request( String itemRequested, int quantity) {
        //this.priority = priority;
        this.itemRequested = itemRequested;
        this.quantity = quantity;
        this.status = "Pending";
    }

    @Override
    public String toString() {
        return   " Item: " + itemRequested +
                ", Quantity: " + quantity + ", Status: " + status;
    }
}

// Manages requests, inventory, and beneficiary tracking
class ResourceAllocation {
    Queue<Request> requestQueue;
    InventoryBST inventoryBST;
    List<Beneficiary> beneficiaries;

    public ResourceAllocation(InventoryBST inventoryBST) {
        this.inventoryBST = inventoryBST;
        this.beneficiaries = new ArrayList<>();
        this.requestQueue = new LinkedList<>();
        //this.requestQueue = new PriorityQueue<>(Comparator.comparingInt(r -> -r.priority));
    }

    // Add request and handle stock adjustments
    public void addRequest( String itemRequested, int quantity) {
        Request request = new Request( itemRequested, quantity);
        StockItem item = inventoryBST.search(itemRequested);

        if (item != null) {
            if (item.quantity >= quantity) {
                inventoryBST.updateQuantityOrRemove(itemRequested, -quantity);
                request.status = "Fulfilled";
                beneficiaries.add(new Beneficiary(itemRequested, quantity));
                System.out.println("Request fulfilled directly from stock" );
            } else {
                request.quantity -= item.quantity;
                inventoryBST.updateQuantityOrRemove(itemRequested, -item.quantity);
                requestQueue.offer(request); // Add remaining quantity to queue
                System.out.println("Partially fulfilled the request");
            }
        } else {
            requestQueue.offer(request); // Add request if item not available in stock
            System.out.println("request added successfully");
        }
    }

    // Add stock to the inventory and attempt to fulfill pending requests
    public void addStock(String itemName, int quantity) {
        StockItem item = inventoryBST.search(itemName);
        if (item != null) {
            inventoryBST.updateQuantityOrRemove(itemName, quantity);
            System.out.println("Stock updated for " + itemName);
        } else {
            inventoryBST.insert(new StockItem(itemName, quantity));
            System.out.println("New stock item added: " + itemName);
        }
        fulfillPendingRequests(); // Try to fulfill pending requests after updating stock
    }

    // Fulfill pending requests based on the available stock
    public void fulfillPendingRequests() {
        Queue<Request> remainingRequests = new LinkedList<>();

        while (!requestQueue.isEmpty()) {
            Request request = requestQueue.poll();
            StockItem item = inventoryBST.search(request.itemRequested);

            if (item != null && item.quantity >= request.quantity) {
                inventoryBST.updateQuantityOrRemove(request.itemRequested, -request.quantity);
                request.status = "Fulfilled";
                beneficiaries.add(new Beneficiary(request.itemRequested, request.quantity));
                System.out.println("Pending request fulfilled: " + request);
            } else if (item != null && item.quantity > 0) {
                request.quantity -= item.quantity;
                inventoryBST.updateQuantityOrRemove(request.itemRequested, -item.quantity);
                remainingRequests.offer(request);
                System.out.println("Partially fulfilled request: " + request);
            } else {
                remainingRequests.offer(request);
            }
        }

        requestQueue = remainingRequests;
    }


    // List all pending requests by priority
    public void listPendingRequests() {
        if (requestQueue.isEmpty()) {
            System.out.println("No pending requests.");
        } else {
            System.out.println("Pending Requests :");
            requestQueue.forEach(System.out::println);
        }
    }

    // List all beneficiaries who received items
    public void listBeneficiaries() {
        if (beneficiaries.isEmpty()) {
            System.out.println("No beneficiaries yet.");
        } else {
            System.out.println("Beneficiaries:");
            beneficiaries.forEach(System.out::println);
        }
    }
}

// Represents a beneficiary record
class Beneficiary {
    String itemReceived;
    int quantity;

    public Beneficiary(String itemReceived, int quantity) {
        this.itemReceived = itemReceived;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Item Received: " + itemReceived + ", Quantity: " + quantity;
    }
}

// Main program
public class NGO {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        InventoryBST inventoryBST = new InventoryBST();
        ResourceAllocation resourceAllocation = new ResourceAllocation(inventoryBST);
        int FirstMainChoice = 0;
        Donations donations = new Donations();
        VolunteerQueue volunteerQueue = new VolunteerQueue();
        EventList eventList = new EventList();
        do {
            System.out.println("\n1.User\n2.Admin\n0.exit\n");
            FirstMainChoice = sc.nextInt();
            sc.nextLine();
            switch (FirstMainChoice) {
                case 0:
                    break;
                case 1:
                    int userChoice = 0;
                    do {
                        System.out.println("1.Donate money \n2.Register as Volunteer\n3.Request\n4.Donate stuff\n0.exit\n");
                        userChoice = sc.nextInt();
                        sc.nextLine();
                        switch (userChoice) {
                            case 0:
                                break;
                            case 1:
                                donations.addDonation();
                                break;
                            case 2:
                                volunteerQueue.addVolunteer();
                                break;
                            case 3:
                                System.out.print("Enter Item Name: ");
                                String itemRequested = sc.nextLine();
                                System.out.print("Enter Quantity: ");
                                int quantity = sc.nextInt();
                                resourceAllocation.addRequest(itemRequested, quantity);
                                break;
                            case 4:
                                System.out.print("Enter Item Name: ");
                                String itemName = sc.nextLine();
                                System.out.print("Enter Quantity to Add: ");
                                int stockQuantity = sc.nextInt();
                                resourceAllocation.addStock(itemName, stockQuantity);
                                break;
                            default:
                                System.out.println("Please enter valid choice");


                        }
                    } while (userChoice != 0);
                    break;
                case 2:
                    int AdminChoice = 0;
                    do {
                        System.out.println("1.Donation Management\n2.Add Events\n3.Inventory Management\n0.back");
                        AdminChoice = sc.nextInt();
                        switch (AdminChoice) {
                            case 1:
                                int dc = 0;
                                do {
                                    System.out.println();
                                    System.out.println("1.add in donations");
                                    System.out.println("2. View All Donations");
                                    System.out.println("3. Update Donation");
                                    System.out.println("4. Sort Donations by Amount");
                                    System.out.println("5. View Total Donation Amount");
                                    System.out.println("0. back");
                                    System.out.print("Enter your choice: ");
                                    dc = sc.nextInt();
                                    switch (dc) {
                                        case 1:
                                            donations.addDonation();
                                            break;
                                        case 2:
                                            donations.displayAllDonations();
                                            break;
                                        case 3:
                                            donations.updateDonation();
                                            break;
                                        case 4:
                                            donations.quickSortDonationsByAmount();
                                            donations.displayAllDonations(); // Display sorted donations
                                            break;
                                        case 5:
                                            double totalAmount = donations.totalDonationAmount();
                                            System.out.println("Total Donation Amount: RS." + totalAmount);
                                            break;
                                        case 0:
                                            break;
                                        default:
                                            System.out.println("Invalid choice. Please try again.");
                                    }
                                } while (dc != 0);
                                break;
                            case 2:

                                int choice2 = 0;
                                do {
                                    System.out.println("\nMenu");
                                    System.out.println("1. Volunteers");
                                    System.out.println("2. Events");
                                    System.out.println("0. back");
                                    System.out.print("Enter your choice: ");
                                    choice2 = sc.nextInt();

                                    switch (choice2) {
                                        case 1:
                                            // Volunteers Menu
                                            while (true) {
                                                System.out.println("\nVolunteers Menu");
                                                System.out.println("1. Add new Volunteer");
                                                System.out.println("2. View All Volunteers");
                                                System.out.println("0. back");
                                                System.out.print("Enter your choice: ");
                                                int volunteerChoice = sc.nextInt();

                                                switch (volunteerChoice) {
                                                    case 1:
                                                        volunteerQueue.addVolunteer();
                                                        break;
                                                    case 2:
                                                        volunteerQueue.viewAllVolunteers();
                                                        break;
                                                    case 0:
                                                        // Exit Volunteers Menu
                                                        break;
                                                    default:
                                                        System.out.println("Invalid choice. Try again.");
                                                }
                                                if (volunteerChoice == 0) {
                                                    break; // Exit loop to go back to Main Menu
                                                }
                                            }
                                            break;

                                        case 2:
                                            // Events Menu
                                            while (true) {
                                                System.out.println("\nEvents Menu");
                                                System.out.println("1. Add new Event");
                                                System.out.println("2. Display All Events");
                                                System.out.println("3. Search Event");
                                                System.out.println("4. Assign Volunteer to Event");
                                                System.out.println("5. Update Event Details");
                                                System.out.println("0. back");
                                                System.out.print("Enter your choice: ");
                                                int eventChoice = sc.nextInt();

                                                switch (eventChoice) {
                                                    case 1:
                                                        eventList.createEvent();
                                                        break;
                                                    case 2:
                                                        eventList.displayAllEvents();
                                                        break;
                                                    case 3:
                                                        eventList.searchEvent();
                                                        break;
                                                    case 4:
                                                        eventList.assignVolunteerToEvent(volunteerQueue);
                                                        break;
                                                    case 5:
                                                        eventList.updateEventDetails();
                                                        break;
                                                    case 0:
                                                        // Exit Events Menu
                                                        break;
                                                    default:
                                                        System.out.println("Invalid choice. Try again.");
                                                }
                                                if (eventChoice == 0) {
                                                    break; // Exit loop to go back to Main Menu
                                                }
                                            }
                                            break;

                                        case 3:
                                            // Exit the program
                                            System.exit(0);
                                            break;

                                        default:
                                            System.out.println("Invalid choice. Try again.");
                                    }
                                } while (choice2 != 0);
                                break;
                            case 3:
                                int choice3 = 0;
                                do {
                                    System.out.println("\nNGO Resource Allocation System");
                                    System.out.println("1. Add requests ");
                                    System.out.println("2. Add Stock Item");
                                    System.out.println("3. List Pending Requests");
                                    System.out.println("4. Display Stock Items");
                                    System.out.println("5. List Beneficiaries");
                                    System.out.println("0. back");
                                    System.out.print("Enter your choice: ");
                                    choice3 = sc.nextInt();
                                    sc.nextLine();

                                    switch (choice3) {
                                        case 0:
                                            break;
                                        case 1:


                                            System.out.print("Enter Item Name: ");
                                            String itemRequested = sc.nextLine();
                                            System.out.print("Enter Quantity: ");
                                            int quantity = sc.nextInt();
                                            resourceAllocation.addRequest(itemRequested, quantity);
                                            break;
                                        case 2:
                                            System.out.print("Enter Item Name: ");
                                            String itemName = sc.nextLine();
                                            System.out.print("Enter Quantity to Add: ");
                                            int stockQuantity = sc.nextInt();
                                            resourceAllocation.addStock(itemName, stockQuantity);
                                            break;
                                        case 3:
                                            resourceAllocation.listPendingRequests();
                                            break;
                                        case 4:
                                            inventoryBST.displayInventory();
                                            break;
                                        case 5:
                                            resourceAllocation.listBeneficiaries();
                                            break;
                                        default:
                                            System.out.println("Invalid choice.");
                                    }
                                } while (choice3 != 0);
                                break;
                            default:
                                System.out.println("Enter valid choice");

                        }
                    } while (AdminChoice != 0);

            }
        } while (FirstMainChoice != 0);






