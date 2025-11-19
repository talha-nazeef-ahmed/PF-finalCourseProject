import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LabProject {
    final static int INITIAL_CAPACITY = 1000;
    final static int LOG_FIELDS = 6;
    final static int MESSAGE = 0;
    final static int TYPE = 1;
    final static int SEVERITY = 2;
    final static int SOURCE = 3;
    final static int CATEGORY = 4;
    final static int TIMESTAMP = 5;
    static String[][] logs;
    static int logCount = 0;
    static String sessionStartTime;
    static Scanner input = new Scanner(System.in);

    public static void main (String [] args){
        sessionStartTime = generateTimestamp();
        initializeSystem();
        displayWelcomeBanner();
        mainMenuLoop();
        System.out.println("Program ended successfully!");
    }

    public static void displayWelcomeBanner(){
        System.out.println("*******************************************");
        System.out.println("***     LOG MANAGEMENT SYSTEM v1.0      ***");
        System.out.println("*******************************************");
        System.out.println();
        System.out.printf("Session Started: %s\n", generateTimestamp());
        System.out.println("Authors: Talha Nazeef Ahmed & Nishat Mehdi");
        System.out.println();
        System.out.println("===========================================");
        System.out.println("           WELCOME TO THE SYSTEM           ");
        System.out.println("===========================================");
        System.out.println();
        System.out.println("system initialized");
        System.out.println("ready to manage logs");
        System.out.println();
        pauseAndContinue();
    }

    public static void initializeSystem(){
        System.out.println("Initializing system...");
        logs = new String[INITIAL_CAPACITY][LOG_FIELDS];
        System.out.printf("2D array allocated (capacity =  %d logs, %d fields\n)", INITIAL_CAPACITY, LOG_FIELDS);
        System.out.println("log counter initialized");
        System.out.println();
    }

    public static void pauseAndContinue(){
        System.out.println("Press Enter to continue...");
        input.nextLine();
        System.out.println();
    }

    public static String generateTimestamp(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy || HH:mm");
        return LocalDateTime.now().format(format);
    }

    public static void mainMenuLoop(){
        while (true){
            System.out.println("=======================================");
            System.out.println("               MAIN MENU               ");
            System.out.println("=======================================");
            System.out.printf("Current Session: %s | Logs: %d\n", generateTimestamp(), logCount);
            System.out.println();
            System.out.println("1. Add New Log Entry");
            System.out.println("2. View All Logs");
            System.out.println("3. Search Logs");
            System.out.println("4. Delete Logs");
            System.out.println("5. Edit Existing Log");
            System.out.println("6. Statistics Dashboard");
            System.out.println("7. Trend Analysis");
            System.out.println("8. Find Anomalies");
            System.out.println("9. Sort Logs");
            System.out.println("10. Filter Logs");
            System.out.println("11. Backup System");
            System.out.println("12. Restore Backup");
            System.out.println("13. Compare Backups");
            System.out.println("14. System Diagnostics");
            System.out.println("15. Help & Documentation");
            System.out.println("0. Exit");
            System.out.println();
            int choice = getMenuChoice();
            if (choice == 0) {
                shutdownSystem();
                break;
            }
            switch (choice){
                case 1 -> addLog();
                case 2 -> viewAllLogs();
                case 3 -> { System.out.println(">>> Search Logs - Coming soon!");
                    pauseAndContinue();
                }
                case 4 -> { System.out.println(">>> Delete Logs - Coming soon!");
                    pauseAndContinue();
                }
                case 5 -> { System.out.println(">>> Edit Logs - Coming soon!");
                    pauseAndContinue();
                }
                case 6 -> { System.out.println(">>> Statistics - Coming soon!");
                    pauseAndContinue();
                }
                case 7 -> { System.out.println(">>> Trends - Coming soon!");
                    pauseAndContinue();
                }
                case 8 -> { System.out.println(">>> Find Anomalies - Coming soon!");
                    pauseAndContinue();
                }
                case 9 -> { System.out.println(">>> Sort Logs - Coming soon!");
                    pauseAndContinue();
                }
                case 10 -> { System.out.println(">>> Filter Logs - Coming soon!");
                    pauseAndContinue();
                }
                case 11 -> { System.out.println(">>> Backup System - Coming soon!");
                    pauseAndContinue();
                }
                case 12 -> { System.out.println(">>> Restore Backup - Coming soon!");
                    pauseAndContinue();
                }
                case 13 -> {System.out.println(">>> Compare Backups - Coming soon!");
                    pauseAndContinue();
                }
                case 14 -> { System.out.println(">>> Diagnostics - Coming soon!");
                    pauseAndContinue();
                }
                case 15 -> { System.out.println(">>> Help - Coming soon!");
                    pauseAndContinue();
                }
                default -> { System.out.println("ERROR: Invalid choice! Please Enter 0-15");
                    pauseAndContinue();
                }
            }
        }
    }

    public static int getMenuChoice(){
        int choice = -1;
        while (choice < 0){
            System.out.print("Enter your choice (0-15): ");
            String userInput = input.nextLine();
            try {
                choice = Integer.parseInt(userInput);
                if (choice < 0 || choice > 15) {
                    System.out.println("Please Enter a number between 0 and 15");
                    choice = -1;
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid Input! Please Enter a valid number");
                choice = -1;
            }
        }
        return choice;
    }

    public static void shutdownSystem(){
        String sessionEndTime = generateTimestamp();
        System.out.println();
        System.out.println("==========================================");
        System.out.println("            SESSION SUMMARY               ");
        System.out.println("==========================================");
        System.out.printf("Session Started: %s\n", sessionStartTime);
        System.out.printf("Session Ended: %s\n", sessionEndTime);
        System.out.println("Duration: Coming soon...");
        System.out.println();
        System.out.printf("Logs in System: %d\n", logCount);
        System.out.println("------------------------------------------");
        System.out.println();
        System.out.println("Thank you for using Log Management System!");
        System.out.println("Goodbye, Talha Nazeef Ahmed & Nishat Mehdi!");
        System.out.println("------------------------------------------");
        System.out.println();
    }

    public static void addLog(){
        System.out.println("==========================================");
        System.out.println("           ADDING NEW LOG ENTRY           ");
        System.out.println("==========================================");
        System.out.println();
        String message = inputMessage();
        String type = inputType();
        int severity = determineSeverity(type);
        String source = identifySource();
        String category = assignCategory();
        System.out.println();
        System.out.println("------------------------------------------");
        System.out.println("           REVIEW BEFORE SAVING           ");
        System.out.println("------------------------------------------");
        System.out.printf("Timestamp: %s\n", generateTimestamp());
        System.out.printf("Type: %s\n", type);
        System.out.printf("Severity: %d\n", severity);
        System.out.printf("Source: %s\n", source);
        System.out.printf("Category: %s\n", category);
        System.out.printf("Message: %s\n", message);
        System.out.println();
        System.out.println("Save this log? (Y/N)");
        String confirmation = input.nextLine().toUpperCase();
        if(confirmation.equals("Y")){
            int logID = saveLogToArrays(message, type, severity, source, category);
            System.out.println("Log Saved Successfully!");
            System.out.printf("-> Log ID: #%04d\n", logID);
            System.out.printf("-> Total Logs: %d\n", logCount);
            pauseAndContinue();
        }
        else{
            System.out.println("Log not saved!");
            pauseAndContinue();
        }
    }

    public static String inputMessage(){
        String message = "";
        while(message.length() < 10){
            System.out.println("Step 1/5: Enter log message");
            System.out.println("(Must be 10-500 characters)");
            System.out.print("-> ");
            message = input.nextLine().trim();
            if (message.isEmpty())
                System.out.println("Message cannot be empty!");
            else if (message.length() < 10)
                System.out.println("Message too short! minimum length 10 characters");
            else if (message.length() > 500) {
                System.out.println("Message too long! maximum length 500 characters");
                message = "";
            }
        }
        System.out.printf("Message accepted (%d characters)\n", message.length());
        return message;
    }

    public static String inputType(){
        String type = "";
        while (type.isEmpty()){
            System.out.println("Step 2/5: Select log type");
            System.out.println("1. Info (General Information)");
            System.out.println("2. Warning (Potential issue)");
            System.out.println("3. Error (Something failed)");
            System.out.println("4. Critical (Immediate attention needed)");
            System.out.println("5. Debug (Development info)");
            System.out.print("-> ");
            String choice = input.nextLine().toUpperCase();
            switch (choice) {
                case "1", "INFO" -> type = "INFO";
                case "2", "WARNING" -> type = "WARNING";
                case "3", "ERROR" -> type = "ERROR";
                case "4", "CRITICAL" -> type = "CRITICAL";
                case "5", "DEBUG" -> type = "DEBUG";
                default -> System.out.println("Invalid type! choose 1-5 or name");
            }
        }
        System.out.printf("Type set to %s\n", type);
        return type;
    }

    public static int determineSeverity(String type){
        int severity;
        switch (type) {
            case "CRITICAL" -> severity = 5;
            case "ERROR" -> severity = 3;
            case "WARNING" -> severity = 2;
            default -> severity = 1;
        }
        System.out.printf("Step 3/5: Auto-assigned severity: %d (based on %s)\n", severity, type);
        while (true) {
            System.out.println("Do you want to override (Y/N)?");
            System.out.print("-> ");
            String choice = input.nextLine().trim().toUpperCase();
            if ((!choice.equals("N")) && (!choice.equals("Y")))
                System.out.println("Enter a valid value as y or n");
            else if (choice.equals("Y")) {
                while (true) {
                    System.out.print("Write the value for the severity (1-5): ");
                    String userInput = input.nextLine().trim();
                    try {
                        severity = Integer.parseInt(userInput);
                        if (severity >= 1 && severity <= 5)
                            break;
                        else
                            System.out.println("Enter a value between 1 to 5");
                    } catch (NumberFormatException e) {
                        System.out.println("Enter a valid number");
                    }
                }
                System.out.printf("Severity set to %d\n", severity);
                break;
            }
            else
                break;
        }
        return severity;
    }

    public static String identifySource(){
        String source = "";
        do {
            System.out.println("Step 4/5: Select Source System");
            System.out.println("1. Authentication Server\n2. Database Server\n3. Web Server\n4. API Gateway\n5. Network Monitor\n6. File System");
            String choice = input.nextLine().trim().toUpperCase();
            switch (choice) {
                case "1", "AUTHENTICATION SERVER" -> source = "Authentication Server";
                case "2", "DATABASE SERVER" -> source = "Database Server";
                case "3", "WEB SERVER" -> source = "Web Server";
                case "4", "API GATEWAY" -> source = "API Gateway";
                case "5", "NETWORK MONITOR" -> source = "Network Monitor";
                case "6", "FILE SYSTEM" -> source = "File System";
                default -> System.out.println("Enter a valid choice (1-6) or the name");
            }
        } while (source.isEmpty());
        System.out.printf("Source set to %s\n", source);
        return source;
    }

    public static String assignCategory(){
        System.out.println("Step 5/5: Add Category ");
        System.out.println("1. Security\n2. Performance\n3. Network\n4. Application\n5. Database ");
        System.out.println("Enter Your Choice: 1-5 ");
        int userChoice = input.nextInt();
        input.nextLine(); // consume newline
        while(true){
            if (userChoice>=1 && userChoice<=5){
                break;
            }
            else{
                System.out.print("Invalid Choice, Enter between 1 and 5 ");
                userChoice = input.nextInt();
                input.nextLine(); // consume newline
            }
        }
        String returnStr = switch (userChoice) {
            case 1 -> "Security";
            case 2 -> "Performance";
            case 3 -> "Network";
            case 4 -> "Application";
            case 5 -> "Database";
            default -> "Uncategorized";
        };
        System.out.printf("Category set to %s\n", returnStr);
        return returnStr;
    }

    public static int saveLogToArrays(String message, String type, int severity, String source, String category){
        logs[logCount][MESSAGE] = message;
        logs[logCount][TYPE] = type;
        logs[logCount][SEVERITY] = String.valueOf(severity);
        logs[logCount][SOURCE] = source;
        logs[logCount][CATEGORY] = category;
        logs[logCount][TIMESTAMP] = generateTimestamp();
        logCount++;
        return logCount;
    }

    public static void viewAllLogs(){
        if (logCount == 0)
            System.out.println("No logs in the system yet! Add some logs first");
        else {
            System.out.println("======================================");
            System.out.println("            ALL SYSTEM LOGS           ");
            System.out.println("======================================");
            System.out.printf("Total logs: %d\n", logCount);
            for(int i = 0; i < logCount; i++){
                formatLogEntry(i);
            }
        }
        pauseAndContinue();
    }

    public static void formatLogEntry(int index){
        System.out.println("------------------------------------------");
        System.out.printf("| Log ID: #%04d\n", index + 1);
        System.out.println("------------------------------------------");
        System.out.printf("| Timestamp: %s\n", logs[index][TIMESTAMP]);
        System.out.printf("| Type: %s\n", logs[index][TYPE]);
        System.out.printf("| Severity: %s\n", logs[index][SEVERITY]);
        System.out.printf("| Source: %s\n", logs[index][SOURCE]);
        System.out.printf("| Category: %s\n", logs[index][CATEGORY]);
        System.out.printf("| Message: %s\n", logs[index][MESSAGE]);
        System.out.println("------------------------------------------");
        System.out.println();
    }

    public static void displaySearchResults(int[] matchIndices, int matchCount){
        if (matchCount == 0)
            System.out.println("No logs Found!");
        else {
            System.out.printf("Found %d matching log(s)\n", matchCount);
            for (int i = 0; i < matchCount; i++){
                formatLogEntry(matchIndices[i]);
            }
            System.out.println("=======================================");
            System.out.printf("Search complete: %d log(s) found\n", matchCount);
            System.out.println("=======================================");
        }
        pauseAndContinue();
    }
}
