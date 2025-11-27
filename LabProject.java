import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.*;

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
        System.out.println("***             SIEM v1.0               ***");
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
        System.out.printf("2D array allocated (capacity =  %d logs, %d fields)\n", INITIAL_CAPACITY, LOG_FIELDS);
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
            System.out.println("11. Export Logs");
            System.out.println("12. Import Logs");
            System.out.println("13. System Diagnostics");
            System.out.println("14. Help & Documentation");
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
                case 3 -> searchByKeyword();
                case 4 -> deleteLogs();
                case 5 -> { System.out.println(">>> Edit Logs - Coming soon!");
                    pauseAndContinue();
                }
                case 6 -> showStatistics();
                case 7 -> { System.out.println(">>> Trends - Coming soon!");
                    pauseAndContinue();
                }
                case 8 -> anomalyDetection();
                case 9 -> { sortLogsBySeverity();                    
                }
                case 10 -> filterLogs();
                case 11 -> exportLogs();
                case 12 -> importLogs();
                case 13 -> { System.out.println(">>> Diagnostics - Coming soon!");
                    pauseAndContinue();
                }
                case 14 -> { System.out.println(">>> Help - Coming soon!");
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
        System.out.println("Thank you for using Security Information and Event Management System!");
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
            case "ERROR" -> severity = 4;
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

    public static void searchByKeyword(){
        System.out.println("------------------------------------------");
        System.out.println("           SEARCH LOGS BY KEYWORD        ");
        System.out.println("------------------------------------------");
        System.out.print("Enter keyword to search: ");
        String keyword = input.nextLine().trim();
        System.out.println("Searching for "+ keyword +"... ");
        keyword = keyword.toLowerCase();
        int[] matchIndices = new int[logCount];
        int matchCount=0;
        if(!keyword.isEmpty()){
            for(int i=0; i<logCount; i++){
                if(logs[i][MESSAGE].toLowerCase().contains(keyword)){
                    matchIndices[matchCount] = i;
                    matchCount++;
                }
            }
            displaySearchResults(matchIndices, matchCount);
        }else{
            System.out.println("No keyword entered. Search halted.");
            pauseAndContinue();
        }
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
    public static void filterLogs(){
        System.out.println("------------------------------------------");
        System.out.println("            FILTER LOGS MENU              ");
        System.out.println("------------------------------------------");
        System.out.println("1. Filter by Type");
        System.out.println("2. Filter by Severity");
        System.out.println("3. Filter by Source");
        System.out.println("4. Filter by Category");
        System.out.println("0. Back to Main Menu");
        System.out.println();
        System.out.print("Enter your choice (0-4): ");

        String choice = input.nextLine().trim();

        switch(choice){
            case "1" -> filterByType();
            case "2" -> filterBySeverity();
            case "3" -> filterBySource();
            case "4" -> filterByCategory();
            case "0" -> System.out.println("Returning to main menu...");
            default -> {
                System.out.println("Invalid choice!");
                pauseAndContinue();
            }
        }
    }

    public static void filterByType(){
        String type = "";
        int count = 0;
        int [] indices = new int[logCount];
        System.out.println("------------------------------------------");
        System.out.println("            FILTER LOGS BY TYPE           ");
        System.out.println("------------------------------------------");
        if (logCount == 0) {
            System.out.println("No logs in system");
            pauseAndContinue();
        }
        else {
            System.out.println("Select log type to filter:\n1. INFO\n2. WARNING\n3. ERROR\n4. CRITICAL\n5. DEBUG");
            while (true) {
                boolean flag = false;
                System.out.print("Enter your choice (1-5): ");
                String choice = input.nextLine();
                try {
                    int num = Integer.parseInt(choice);
                    if (num > 0 && num <= 5) {
                        switch (num) {
                            case 1 -> type = "INFO";
                            case 2 -> type = "WARNING";
                            case 3 -> type = "ERROR";
                            case 4 -> type = "CRITICAL";
                            case 5 -> type = "DEBUG";
                        }
                        for (int i = 0; i < logCount; i++) {
                            if (logs[i][TYPE].equals(type)) {
                                indices[count] = i;
                                count++;
                            }
                        }
                        System.out.printf("Filtering by type: %s\n", type);
                        displaySearchResults(indices, count);
                        flag = true;
                    }
                    else {
                        System.out.println("Enter a number between 1-5 inclusively!");
                    }
                    if (flag)
                        break;

                } catch (NumberFormatException e) {
                    System.out.println("Enter a valid integer (1-5)!");
                }
            }
        }
    }
    public static void filterBySeverity(){
        int[] indices = new int[logCount];
        int count = 0;
        System.out.println("------------------------------------------");
        System.out.println("         FILTER LOGS BY SEVERITY          ");
        System.out.println("------------------------------------------");
        System.out.println("Severity Levels:\n1. Low\n2. Medium-Low\n3. Medium\n4. Medium-High\n5. High");
        System.out.print("Enter severity level to filter (1-5): ");
        String severity = input.next().trim();
        input.nextLine(); // consume newline
        if (Integer.parseInt(severity)>5 || Integer.parseInt(severity)<1){
            System.out.println("Invalid severity level! Please enter a number between 1 and 5.");
        }
        else {
            for (int i=0; i<logCount; i++){
                if(logs[i][SEVERITY].equals(severity)){
                    indices[count] = i;
                    count++;
                }
            }
        }
        System.out.printf("Filtering by severity: %s\n", severity);
        displaySearchResults(indices, count);
    }
    public static void filterByCategory(){
        int count = 0;
        int [] indices = new int[logCount];
        System.out.println("------------------------------------------ ");
        System.out.println("         FILTER LOGS BY CATEGORY          ");
        System.out.println("------------------------------------------ ");
        System.out.println("Select log category to filter:\n1. Security\n2. Performance\n3. Network\n4. Application\n5. Database");
        int userChoice = input.nextInt();
        input.nextLine(); // consume newline
        String category ="";
        while (true) {
            if(userChoice<1 || userChoice>5){
                System.out.println("Invalid choice! Please enter a number between 1 and 5: ");
                pauseAndContinue();
                break;
            }
            category = switch (userChoice) {
                case 1 -> "Security";
                case 2 -> "Performance";
                case 3 -> "Network";
                case 4 -> "Application";
                case 5 -> "Database";
                default -> category;
            };
            if(logCount == 0){
                System.out.println("No logs in system");                
                pauseAndContinue();
                break;
            }
            else {
                for (int i = 0; i < logCount; i++) {
                    if (logs[i][CATEGORY].equals(category)) {
                        indices[count] = i;
                        count++;
                    }
                }    
                System.out.printf("Filtering by category: %s\n", category);
                displaySearchResults(indices, count);

            }

        }
    }

    public static void filterBySource(){
        String source = "";
        int count = 0;
        int [] indices = new int[logCount];
        System.out.println("------------------------------------------");
        System.out.println("            FILTER LOGS BY SOURCE           ");
        System.out.println("------------------------------------------");
        if (logCount == 0) {
            System.out.println("No logs in system");
            pauseAndContinue();
        }
        else {
            System.out.println("Select log type to filter:\n1. Authentication Server\n2. Database Server\n3. Web Server\n4. API Gateway\n5. Network Monitor\n6. File System");
            while (true) {
                boolean flag = false;
                System.out.print("Enter your choice (1-6): ");
                String choice = input.nextLine();
                try {
                    int num = Integer.parseInt(choice);
                    if (num > 0 && num <= 6) {
                        switch (num) {
                            case 1 -> source = "Authentication Server";
                            case 2 -> source = "Database Server";
                            case 3 -> source = "Web Server";
                            case 4 -> source = "API Gateway";
                            case 5 -> source = "Network Monitor";
                            case 6 -> source = "File System";
                        }
                        for (int i = 0; i < logCount; i++) {
                            if (logs[i][SOURCE].equals(source)) {
                                indices[count] = i;
                                count++;
                            }
                        }
                        System.out.printf("Filtering by type: %s\n", source);
                        displaySearchResults(indices, count);
                        flag = true;
                    }
                    else {
                        System.out.println("Enter a number between 1-6 inclusively!");
                    }
                    if (flag)
                        break;

                } catch (NumberFormatException e) {
                    System.out.println("Enter a valid integer (1-6)!");
                }
            }
        }
    }

    public static void showStatistics(){
        int infoCount = 0, warningCount = 0, errorCount = 0, criticalCount = 0, debugCount = 0;
        int[] severityCount = new int[6];
        System.out.println("========================================");
        System.out.println("          STATISTICS DASHBOARD          ");
        System.out.println("========================================");
        if (logCount == 0)
            System.out.println("No logs to show!");
        else {
            for (int i = 0; i < logCount; i++){
                String type = logs[i][TYPE];
                switch (type){
                    case "INFO" -> infoCount++;
                    case "WARNING" -> warningCount++;
                    case "ERROR" -> errorCount++;
                    case "CRITICAL" -> criticalCount++;
                    case "DEBUG" -> debugCount++;
                }
            }
            for (int i = 0; i < logCount; i++){
                int severity = Integer.parseInt(logs[i][SEVERITY]);
                severityCount[severity]++;
            }
            System.out.printf("Total Logs: %d\n\n", logCount);
            System.out.println("BY TYPE:");
            System.out.println("-------------------------------------");
            String infoPercent = calculatePercentage(infoCount, logCount);
            String warningPercent = calculatePercentage(warningCount, logCount);
            String errorPercent = calculatePercentage(errorCount, logCount);
            String criticalPercent = calculatePercentage(criticalCount, logCount);
            String debugPercent = calculatePercentage(debugCount, logCount);
            System.out.printf("%-10s %2d (%-6s) ", "INFO: ", infoCount, infoPercent);
            generateSimpleChart(infoCount, logCount);
            System.out.println();
            System.out.printf("%-10s %2d (%-6s) ", "WARNING: ", warningCount, warningPercent);
            generateSimpleChart(warningCount, logCount);
            System.out.println();
            System.out.printf("%-10s %2d (%-6s) ", "ERROR: ", errorCount, errorPercent);
            generateSimpleChart(errorCount, logCount);
            System.out.println();
            System.out.printf("%-10s %2d (%-6s) ", "CRITICAL: ", criticalCount, criticalPercent);
            generateSimpleChart(criticalCount, logCount);
            System.out.println();
            System.out.printf("%-10s %2d (%-6s) ", "DEBUG: ", debugCount, debugPercent);
            generateSimpleChart(debugCount, logCount);
            System.out.println("\n");
            System.out.println("BY SEVERITY:");
            System.out.println("-------------------------------------");
            String level1Percent = calculatePercentage(severityCount[1], logCount);
            String level2Percent = calculatePercentage(severityCount[2], logCount);
            String level3Percent = calculatePercentage(severityCount[3], logCount);
            String level4Percent = calculatePercentage(severityCount[4], logCount);
            String level5Percent = calculatePercentage(severityCount[5], logCount);
            System.out.printf("%-20s %2d (%-6s) ", "Level 5 (Critical): ", severityCount[5], level5Percent);
            generateSimpleChart(severityCount[5], logCount);
            System.out.println();
            System.out.printf("%-20s %2d (%-6s) ", "Level 4 (High): ", severityCount[4], level4Percent);
            generateSimpleChart(severityCount[4], logCount);
            System.out.println();
            System.out.printf("%-20s %2d (%-6s) ", "Level 3 (Medium): ", severityCount[3], level3Percent);
            generateSimpleChart(severityCount[3], logCount);
            System.out.println();
            System.out.printf("%-20s %2d (%-6s) ", "Level 2 (Low): ", severityCount[2], level2Percent);
            generateSimpleChart(severityCount[2], logCount);
            System.out.println();
            System.out.printf("%-20s %2d (%-6s) ", "Level 1 (Minimal): ", severityCount[1], level1Percent);
            generateSimpleChart(severityCount[1], logCount);
            System.out.println("\n");
            System.out.println("========================================");
            System.out.printf("Dashboard generated: %s\n", generateTimestamp());
            System.out.println("========================================");
        }
        pauseAndContinue();
    }

    public static String calculatePercentage(int count, int total){
        double percent;
        if (total == 0){
            return "0.0%";
        }
        else {
            percent = (double) (count * 100) / total;
            return String.format("%.1f%%", percent);
        }
    }
    public static void generateSimpleChart(int count, int total){
        int barLength = (int) (((double) count / total) * 20);
        for (int i = 0; i < barLength; i++){
            System.out.print("█");
        }
        for (int i = barLength; i < 20; i++){
            System.out.print("░");
        }
    }
    public static void anomalyDetection(){
        while (true){
            System.out.println("Enter a choice for a scan\n1. Brute-force attack scan\n2. Suspicious activity scan\n0. Exit");
            System.out.print("-> ");
            String choice = input.nextLine();
            try {
                int scan = Integer.parseInt(choice);
                if (scan > 2 || scan < 0)
                    System.out.println("Enter a valid choice between (0-2)");
                else {
                    if (scan == 0)
                        break;
                    else if (scan == 1)
                        detectBruteForce();
                    else
                        detectSuspiciousActivity();
                }
            }
            catch (NumberFormatException e){
                System.out.println("Please enter a valid integer!");
            }

        }
    }

    public static void detectBruteForce(){
        int bruteForceCount = 0;
        int count = 0;
        int initialCheck = 0;
        String[] keywords = {"login failed", "failed login attempt", "authentication failed", "wrong credentials", "retry login", "password does not match","invalid password","incorrect password","access denied","unauthorized access","failed authentication","login attempt failed","bad credentials","authentication error","invalid credentials", "password incorrect","user not found", "account locked", "too many attempts"};
        System.out.println("Analyzing logs for Brute Force Attacks...");
        if (logCount <= 2)
            System.out.println("No possible pattern of brute-force due to low log count");
        else {
            for (int i = 0; i < logCount; i++) {
                for (String word : keywords) {
                    if (logs[i][MESSAGE].toLowerCase().contains(word) && logs[i][TYPE].equals("ERROR")) {
                        if (count == 0)
                            initialCheck = i;
                        count++;
                        break;
                    }
                }
                if (count == 3 && i - initialCheck == 2) {
                    bruteForceCount++;
                    System.out.println("Possible brute-force detected!");
                    System.out.println("-----------------------------------------");
                    System.out.printf("  -> Brute-force no: %d\n", bruteForceCount);
                    System.out.printf("  -> Location: Logs: %d - %d\n\n", initialCheck + 1, i + 1);
                    initialCheck = i;
                    count = 1;
                }
                else if (i - initialCheck == 2 && count < 3) {
                    initialCheck = 0;
                    count = 0;
                }
            }
        }
        System.out.printf("Total brute-force attacks: %d\n", bruteForceCount);
        if (bruteForceCount >= 3)
            System.out.println("Risk assessment: Critical\nImmediate attention is required");
        else if (bruteForceCount > 0)
            System.out.println("Risk assessment: High\nYour system seems insecure, better to take a look!");
        else
            System.out.println("No brute-force detected!");
        pauseAndContinue();
    }

    public static void detectSuspiciousActivity(){
        int sev4 = 0, sev5 = 0, totalSev;
        System.out.println("Analyzing logs for any suspicious activity...");
        if (logCount <= 4)
            System.out.println("No possible pattern of suspicious activity due to low log count");
        else{
            for (int i = 0; i < logCount; i++){
                if (Integer.parseInt(logs[i][SEVERITY]) == 4)
                    sev4++;
                else if (Integer.parseInt(logs[i][SEVERITY]) == 5)
                    sev5++;
            }
            totalSev = sev4 + sev5;
            if (totalSev >= 5){
                System.out.println("Suspicious Activity Detected!");
                System.out.println("--------------------------------------");
                System.out.printf("  -> High Severity Events: %d\n", totalSev);
                System.out.printf("  -> Severity 5 (Critical): %d logs\n", sev5);
                System.out.printf("  -> Severity 4 (High): %d logs\n", sev4);
                System.out.println("--------------------------------------");
            }
            else
                System.out.println("Risk assessment: Normal\nYour system seems secure!");
        }
        pauseAndContinue();
    }
    
public static void sortLogsBySeverity(){
    System.out.println("===============================================");
    System.out.println("           SORTING LOGS BY SEVERITY            ");
    System.out.println("===============================================");
    System.out.println("Sort in ascending or descending order? ");
    System.out.println("1. Ascending (Low to High)");
    System.out.println("2. Descending (High to Low)");
    System.out.print("Enter your choice (1-2): ");

    try {
        String choiceStr = input.next().trim();
        input.nextLine();
        
        int order = Integer.parseInt(choiceStr);
        
        if (order != 1 && order != 2) {
            System.out.println("Invalid choice! Please enter '1' or '2'. Sorting aborted.");
            pauseAndContinue();
            return; 
        }

        if (logCount == 0) {
            System.out.println("No logs to sort in the system.");
            pauseAndContinue();
            return;
        }

        int[] sortedIndices = new int[logCount];
        for (int i = 0; i < logCount; i++) {
            sortedIndices[i] = i;
        }

        for (int i = 0; i < logCount - 1; i++) {
            int bestIndex = i;

            for (int j = i + 1; j < logCount; j++) {
                int bestSeverity = Integer.parseInt(logs[sortedIndices[bestIndex]][SEVERITY]);
                int currentSeverity = Integer.parseInt(logs[sortedIndices[j]][SEVERITY]);

                if (order == 1) {
                    if (currentSeverity < bestSeverity) {
                        bestIndex = j; 
                    }
                } else {
                    if (currentSeverity > bestSeverity) {
                        bestIndex = j; 
                    }
                }
            }
            
            if (bestIndex != i) {
                int temp = sortedIndices[i];
                sortedIndices[i] = sortedIndices[bestIndex];
                sortedIndices[bestIndex] = temp;
            }
        }
        
        System.out.println("======================================");
        System.out.println("            SORTED LOGS               ");
        System.out.println("======================================");
        displaySearchResults(sortedIndices, logCount);

    } catch(NumberFormatException e){
        System.out.println("Invalid input format! Please enter a number (1 or 2). Sorting aborted.");
        pauseAndContinue();
    } catch(Exception e){
        System.out.println("An unexpected error occurred during sorting. Aborting.");
        pauseAndContinue();
    }
}

    public static void exportLogs(){
        if (logCount < 1)
            System.out.println("No logs to export!");
        else {
            String date = generateTimestamp().replace(" || ", "_").replace(":", "-");
            String filename = "SIEM_EXPORT_" + date + ".txt";
            System.out.println("===============================================");
            System.out.println("              EXPORT LOGS TO FILE              ");
            System.out.println("===============================================\n");
            System.out.printf("Total logs to export: %d\n\n", logCount);
            System.out.println("Exporting logs to file...\n\n");
            try {
                PrintWriter writer = new PrintWriter(filename);
                writer.println("===============================================");
                writer.println("             SIEM LOG EXPORT REPORT            ");
                writer.println("===============================================");
                writer.printf("%-20s %s\n", "Export Date:", generateTimestamp());
                writer.printf("%-20s %s\n", "Session Started:", sessionStartTime);
                writer.printf("%-20s %d\n", "Total Logs:", logCount);
                writer.printf("%-20s %s\n", "Authors:", "Talha Nazeef Ahmed & Nishat Mehdi");
                writer.println("================================================\n\n");
                writer.println("LOG ENTRIES:");
                writer.println("------------------------------------------------\n\n");
                for(int i = 0; i < logCount; i++){
                    writer.println("------------------------------------------");
                    writer.printf("| Log ID: #%04d\n", i + 1);
                    writer.println("------------------------------------------");
                    writer.printf("| Timestamp: %s\n", logs[i][TIMESTAMP]);
                    writer.printf("| Type: %s\n", logs[i][TYPE]);
                    writer.printf("| Severity: %s\n", logs[i][SEVERITY]);
                    writer.printf("| Source: %s\n", logs[i][SOURCE]);
                    writer.printf("| Category: %s\n", logs[i][CATEGORY]);
                    writer.printf("| Message: %s\n", logs[i][MESSAGE]);
                    writer.println("------------------------------------------\n\n");
                }
                writer.println("===============================================");
                writer.printf("END OF REPORT - TOTAL LOGS: %d\n", logCount);
                writer.println("===============================================");
                writer.close();
                System.out.printf("Export Successful!\n->File created: %s\n->Logs exported: %d\n\n", filename, logCount);
                System.out.println("===============================================");
                System.out.printf("Export completed: %s\n", generateTimestamp());
                System.out.println("===============================================");
            }
            catch (Exception e){
                System.out.printf("Error exporting logs!\nError details: %s\n", e.getMessage());
            }
        }
        pauseAndContinue();
    }

    public static void importLogs(){
        ArrayList<String> timestamp = new ArrayList<>();
        ArrayList<String> type = new ArrayList<>();
        ArrayList<String> severity = new ArrayList<>();
        ArrayList<String> source = new ArrayList<>();
        ArrayList<String> category = new ArrayList<>();
        ArrayList<String> message = new ArrayList<>();
        System.out.println("Enter the exact file name you want to import: ");
        try {
            String filename = input.nextLine();
            if(!filename.endsWith(".txt")){
                filename = filename + ".txt";
            }
            File file = new File("C:\\Users\\Talha Nazeef Ahmed\\Desktop\\Github\\PF-finalCourseProject\\" + filename);
            Scanner read = new Scanner(file);
            while (read.hasNext()){
                String line = read.nextLine();
                if (line.startsWith("| Timestamp:"))
                    timestamp.add(line.substring(13));
                else if (line.startsWith("| Type:"))
                    type.add(line.substring(8));
                else if (line.startsWith("| Severity:"))
                    severity.add(line.substring(12));
                else if (line.startsWith("| Source:"))
                    source.add(line.substring(10));
                else if (line.startsWith("| Category:"))
                    category.add(line.substring(12));
                else if (line.startsWith("| Message:"))
                    message.add(line.substring(11));
            }
            read.close();
            int length = timestamp.size() + logCount;
            for (int i = logCount; i < length; i++){
                logs[i][TIMESTAMP] = timestamp.get(i - logCount);
                logs[i][TYPE] = type.get(i - logCount);
                logs[i][SEVERITY] = severity.get(i - logCount);
                logs[i][SOURCE] = source.get(i - logCount);
                logs[i][CATEGORY] = category.get(i - logCount);
                logs[i][MESSAGE] = message.get(i - logCount);
            }
            logCount = length;
            System.out.println("Import successful!");
            System.out.printf("Logs imported: %d\n", timestamp.size());
            System.out.printf("Total logs: %d\n", logCount);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found, beware of typos!");
        }
        catch (Exception e) {
            System.out.printf("Import unsuccessful!\nError details: %s", e.getMessage());
        }
        pauseAndContinue();
    }

    public static void deleteLogs(){
        System.out.println("==========================================");
        System.out.println("              DELETE LOGS                 ");
        System.out.println("==========================================\n");
        if (logCount == 0)
            System.out.println("No logs are there to delete!");
        else {
            while (true) {
                System.out.println("Enter the log ID you want to delete: ");
                String id = input.nextLine();
                try {
                    int logNum = Integer.parseInt(id);
                    if (logNum > logCount || logNum < 1)
                        System.out.println("Enter the log ID that exists!");
                    else if (logNum == logCount) {
                        logCount--;
                        System.out.printf("Log ID %d is deleted successfully!\n", logNum);
                        break;
                    }
                    else {
                        for (int i = logNum; i < logCount; i++){
                            for (int j = 0; j < 6; j++){
                                logs[i - 1][j] = logs[i][j];
                            }
                        }
                        logCount--;
                        System.out.printf("Log ID %d is deleted successfully!\n", logNum);
                        break;
                    }
                }
                catch (NumberFormatException e){
                    System.out.println("Enter a valid integer!");
                }
            }
        }
        pauseAndContinue();
    }
}
