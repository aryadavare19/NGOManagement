### Code Summary

The program consists of several classes that work together to handle donations, manage volunteers and events, and process requests for resources in an inventory. Below is a breakdown of the main functionalities, organized by their respective categories, and the data structures used for each.

---

### Main Functionalities Implemented

#### Donations Management  
**Classes and Methods**  
Donations: This class handles all donation-related functions.  

**Data Structure**  
ArrayList of Donation objects.  

**Functionalities**  
- addDonation(): Adds a new donation.  
- displayAllDonations(): Displays all donations.  
- updateDonation(): Allows updating donation details.  
- quickSortDonationsByAmount(): Sorts donations by amount using Quick Sort.  
- totalDonationAmount(): Calculates the total amount of all donations.  

---

#### Volunteers and Events Management  
**Classes and Methods**  
VolunteerQueue: Manages volunteers using a queue structure.  
EventList: Manages events using a linked list.  

**Data Structure**  
Queue (LinkedList) of Volunteer objects for VolunteerQueue.  
LinkedList of Event objects for EventList.  

**Functionalities**  
- VolunteerQueue:  
  - addVolunteer(): Adds a volunteer to the queue.  
  - viewAllVolunteers(): Displays all volunteers in the queue.  

- EventList:  
  - createEvent(): Creates a new event.  
  - displayAllEvents(): Displays all events.  
  - searchEvent(): Searches for an event by name.  
  - assignVolunteerToEvent(VolunteerQueue): Assigns a volunteer to an event.  
  - updateEventDetails(): Updates details of a specific event.  

---

#### Inventory and Request Management  
**Classes and Methods**  
InventoryBST: Represents the inventory as a Binary Search Tree (BST).  
ResourceAllocation: Manages requests and links with the inventory to allocate resources.  

**Data Structure**  
BST of BSTNode objects containing StockItem for InventoryBST.  
PriorityQueue of Request objects and ArrayList of Beneficiary objects for ResourceAllocation.  

**Functionalities**  
- InventoryBST:  
  - insert(StockItem): Adds a new stock item to the BST.  
  - search(String itemName): Searches for a stock item by name.  
  - updateQuantityOrRemove(String itemName, int quantityChange): Updates the quantity or removes the item if the quantity reaches zero.  
  - displayInventory(): Displays all stock items in an in-order traversal.  

- ResourceAllocation:  
  - addRequest(int priority, String itemRequested, int quantity): Adds a new request to the priority queue and tries to fulfill it from available stock.  
  - addStock(String itemName, int quantity): Adds stock and attempts to fulfill any pending requests.  
  - fulfillPendingRequests(): Processes pending requests based on available stock.  
  - listPendingRequests(): Lists all pending requests in order of priority.  
  - listBeneficiaries(): Lists all beneficiaries who have received resources.  

---

### Data Structures Used  

1. **ArrayList** (in Donations and ResourceAllocation):  
   - Used to store donations and beneficiaries, allowing dynamic resizing and easy iteration over donations and beneficiary lists.  

2. **LinkedList** (in VolunteerQueue and EventList):  
   - Queue: For managing volunteers in a first-in, first-out (FIFO) structure.  
   - Event List: Allows adding, displaying, searching, and modifying events.  

3. **Binary Search Tree (BST)** (in InventoryBST):  
   - Manages stock items, enabling efficient searching, insertion, and in-order traversal for displaying items.  
   - Each node (BSTNode) holds a StockItem, representing an item’s name and quantity.  

4. **Priority Queue** (in ResourceAllocation):  
   - Stores requests in a way that prioritizes higher-priority requests.  
   - Implemented with a comparator to manage request fulfillment based on priority levels.  

5. **Scanner** (in NGOManagement main program):  
   - Used to receive and process user input in the console-based interface.  

---

### Summary of the Full Program Workflow  

1. **Main Menu**  
   - Presents three main sections for managing donations, volunteers and events, and stock requests.  

2. **Donations Management**  
   - Allows users to add, update, view, sort, and calculate donations.  

3. **Volunteers and Events Management**  
   - Provides options to manage volunteers in a queue and events in a linked list.  
   - Enables volunteer assignment to events.  

4. **Requests and Inventory Management**  
   - Manages requests using a priority queue.  
   - Manages stock as a BST, supporting efficient addition, search, and removal.  
   - Tries to fulfill requests automatically when stock is added.  
