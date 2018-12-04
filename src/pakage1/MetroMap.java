package pakage1;

import java.util.Scanner;
import java.util.List;

public class MetroMap {
    private static Scanner scanner;
    private static MultiGraphADT graph;

    public static void main(String args[]) {
        scanner = new Scanner(System.in);
        graph = new MultiGraph();

        System.out.println("**********************************************");
        System.out.println("* * * Welcome to the Boston Metro System * * *");
        System.out.println("**********************************************");

        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("Esteemed user, select an option by typing in a number:");
            System.out.println("  1. Add a new station");
            System.out.println("  2. Add a new track segment");
            System.out.println("  3. View all the stations");
            System.out.println("  4. View all the track segments");
            System.out.println("  5. Plan a route");
            System.out.println("  6. Exit");

            String optionStr = scanner.nextLine();
            int option = -1;

            try {
                option = Integer.parseInt(optionStr);
            }
            catch (NumberFormatException e) {
                // Ignore this
            }

            if (option < 1 || option > 6) {
                System.out.println("That's not a valid choice, please try again.");
                continue;
            }

            switch (option) {
                case 1: addStation(); break;
                case 2: addTrack(); break;
                case 3: allStations(); break;
                case 4: allTracks(); break;
                case 5: findRoute(); break;
                case 6: running = false; break;
            }
        }
        System.out.println();
        System.out.println("Have a safe trip! 🚆");
    }


    private static void addTrack() {
        System.out.println("Adding a new track segment. Please enter the two stations it connects:");
        Node stationA = pickStation("Enter station A: ");
        Node stationB = pickStation("Enter station B: ");

        if (stationA == stationB) {
            System.out.println("The two stations cannot be the same!");
            return; // just bail out and let them try again
        }

        System.out.print("Enter the name of the line: ");
        String lineName = scanner.nextLine().trim();

        // TODO: check if these two stations are already connected on this line
        //   (i.e. if an edge already exists)

        graph.addEdge(lineName, stationA.getId(), stationB.getId());
        System.out.println("Track added.");
    }

    private static void addStation() {
        int id;

        System.out.print("Enter Station ID (numeric): ");

        String idStr = scanner.nextLine();

        //Enforce a valid numeric (integer) ID
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            System.out.println("You must enter a number.");
            return; // just bail out and let them try again
        }

        System.out.print("Enter Station Name: ");

        //Enforce a non-empty station name
        String name = scanner.nextLine().trim();

        if (name.isEmpty())
        {
            System.out.println("You must enter a name.");
            return; // Returns users to main menu and lets them try again
        }

        //Sends user a message if station ID already exists
        if(graph.addNode(id, name)) {
            System.out.println("Station added.");
        }
        else {
            System.out.println("There is already a station with this Id.");
            return; // Returns users to main menu and lets them try again
        }
    }

    private static void allTracks() {
        System.out.println("Track connections in the Metro System:");

        //Send user a message if there are no tracks
        if(graph.allEdges().isEmpty()) {
            System.out.println("There are no tracks available to show.");
            return;
        }
        for (EdgeADT edge : graph.allEdges()) {
            System.out.println(edge.getNodeA().getValue() + " <-> " + edge.getNodeB().getValue() + " (" + edge.getLabel() + ")");
        }
    }

    private static void allStations() {
        System.out.println("Stations in the Metro System:");

        //Sends user a message if ther are no stations
        if(graph.allNodes().isEmpty()) {
            System.out.println("There are no stations available to show.");
            return;
        }
        else {
            for (Node node : graph.allNodes()) {
                System.out.println("[" + node.getId() + "] " + node.getValue());
            }
        }
    }

    private static void findRoute() {
        Node startStation = pickStation("Enter the start station: ");
        Node endStation = pickStation("Enter the end station: ");

        if (startStation == endStation) {
            System.out.println("The two stations cannot be the same!");
            return; // just bail out and let them try again
        }

        List<Edge> route = graph.findPath(startStation, endStation);

        // the route always starts at startStation, obviously
        System.out.println("Your route is:");
        System.out.println(" - " + startStation.getValue());

        Node previous = startStation;
        for (EdgeADT edge : route) {
            // because edges are bi-directional, we must keep in mind that the 'next'
            // station on the route could be either A or B depending on what the previous
            // node we saw was
            if (edge.getNodeA() == previous) {
                // we're going from A to B
                System.out.println(" - " + edge.getNodeB().getValue());
                previous = edge.getNodeB();
            } else {
                // we're going from B to A
                System.out.println(" - " + edge.getNodeA().getValue());
                previous = edge.getNodeA();
            }
        }
    }

    private static Node pickStation(String prompt) {
        // we'll loop until we find a valid station name

        while (true) {
            System.out.print(prompt);
            String enteredName = scanner.nextLine().trim();

            for (Node node : graph.allNodes()) {
                String checkName = (String)node.getValue();
                if (checkName.equalsIgnoreCase(enteredName))
                    return node;
            }

            // if we got here, we didn't find a matching station
            System.out.println("Could not find a station named '" + enteredName + "', please try again.");
        }
    }

}