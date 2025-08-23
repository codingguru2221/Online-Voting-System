package com.Voting;

import java.util.*;

class Candidate {
    int id;
    String name;
    int votes;

    Candidate(int id, String name) {
        this.id = id;
        this.name = name;
        this.votes = 0;
    }
}

class Voter {
    int id;
    String name;
    boolean hasVoted;

    Voter(int id, String name) {
        this.id = id;
        this.name = name;
        this.hasVoted = false;
    }
}

public class Main {
    static Scanner sc = new Scanner(System.in);
    static List<Candidate> candidates = new ArrayList<>();
    static List<Voter> voters = new ArrayList<>();

    public static void main(String[] args) {
        // Add sample candidates
        candidates.add(new Candidate(1, "Alice"));
        candidates.add(new Candidate(2, "Bob"));
        candidates.add(new Candidate(3, "Charlie"));

        // Add sample voters
        voters.add(new Voter(101, "John"));
        voters.add(new Voter(102, "Emma"));
        voters.add(new Voter(103, "David"));

        while (true) {
            System.out.println("\n===== ONLINE VOTING SYSTEM =====");
            System.out.println("1. Cast Vote");
            System.out.println("2. View Results");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: castVote(); break;
                case 2: showResults(); break;
                case 3: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    // Method to cast a vote
    public static void castVote() {
        System.out.print("Enter your Voter ID: ");
        int voterId = sc.nextInt();

        Voter voter = null;
        for (Voter v : voters) {
            if (v.id == voterId) {
                voter = v;
                break;
            }
        }

        if (voter == null) {
            System.out.println("Invalid Voter ID!");
            return;
        }

        if (voter.hasVoted) {
            System.out.println("You have already voted!");
            return;
        }

        System.out.println("\n--- Candidates ---");
        for (Candidate c : candidates) {
            System.out.println(c.id + ". " + c.name);
        }
        System.out.print("Enter Candidate ID to vote: ");
        int candidateId = sc.nextInt();

        Candidate selected = null;
        for (Candidate c : candidates) {
            if (c.id == candidateId) {
                selected = c;
                break;
            }
        }

        if (selected != null) {
            selected.votes++;
            voter.hasVoted = true;
            System.out.println("Vote cast successfully for " + selected.name);
        } else {
            System.out.println("Invalid Candidate ID!");
        }
    }

    // Method to display results
    public static void showResults() {
        System.out.println("\n--- Voting Results ---");
        for (Candidate c : candidates) {
            System.out.println(c.name + ": " + c.votes + " votes");
        }
    }
}
