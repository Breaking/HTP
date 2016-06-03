package pvt.finalproject;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


import pvt.finalproject.model.Status;
import pvt.finalproject.model.Weather;

public class Main {

    private static final String XML = "xml";
    private static final String JSON = "json";
    private static Manager manager = new Manager();

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        boolean validInput = false;
        int selection = 0;

        if (loadData(scan)) {

            System.out.println("Current update server: " + manager.getName());
            System.out.println("Current update: " + manager.getCurrentUpdate().toString());

            while (!validInput) {
                System.out.println("Menu:");
                System.out.println("1. Show weather by month");
                System.out.println("2. Show all weather in sorted order");
                System.out.println("3. Search by city");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");

                try {
                    selection = scan.nextInt();
                    scan.skip(".*");
                    switch (selection) {
                        case 1:
                            String month = "";
                            System.out.print("Type month in small case (for example - 'may'): ");
                            month = scan.next();
                            List<Weather> foundWeathers = new ArrayList<Weather>();

                            foundWeathers = manager.findWeatherByMonth(month);

                            if (foundWeathers.size() == 0){
                                System.out.println("No data for this month");
                            } else{
                                System.out.println("-------------------------------------------------");
                                for(int i = 0;  i < foundWeathers.size(); i++){
                                    //for(int j = 0; j < foundWeathers.get(i).getLocation().size(); j++ ){
                                    System.out.println("Location: " + foundWeathers.get(i).getLocation());
                                    //}
                                    System.out.println("Date: " + foundWeathers.get(i).getDate().toString());
                                    System.out.println("Title: " + foundWeathers.get(i).getTitle());
                                    System.out.println("Description: " + foundWeathers.get(i).getDescription());
                                    System.out.println("Temperature max: "+ foundWeathers.get(i).getTemp_max());
                                    System.out.println("Temperature min: "+ foundWeathers.get(i).getTemp_min());
                                    System.out.println("Humidity: " + foundWeathers.get(i).getHumidity());
                                    System.out.println("-------------------------------------------------");
                                }
                            }

                            //validInput = true;
                            break;
                        case 2:
                            //validInput = true;
                            break;
                        case 3:
                            String city = "";
                            System.out.print("Type city with first letter in upper case (for example - 'Minsk'): ");
                            city = scan.next();
                            List<Weather> foundCity = new ArrayList<Weather>();

                            foundCity = manager.findCity(city);

                            if (foundCity.size() == 0){
                                System.out.println("No weather info for current city");
                            }
                            else{
                                System.out.println("-------------------------------------------------");
                                for(int i = 0;  i < foundCity.size(); i++){
                                    System.out.println("Date: " + foundCity.get(i).getDate().toString());
                                    System.out.println("Title: " + foundCity.get(i).getTitle());
                                    System.out.println("Description: " + foundCity.get(i).getDescription());
                                    System.out.println("Temperature max: "+ foundCity.get(i).getTemp_max());
                                    System.out.println("Temperature min: "+ foundCity.get(i).getTemp_min());
                                    System.out.println("Humidity: " + foundCity.get(i).getHumidity());
                                    System.out.println("-------------------------------------------------");
                                }
                            }

                            //validInput = true;
                            break;
                        case 0:
                            validInput = true;
                            break;
                        default:
                            System.out.println("Wrong input!! Check the menu");
                            break;
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Wrong input! Check the menu");
                    scan.nextLine();
                    continue;

                } catch (Exception e) {
                    System.out.println("Something wrong; ");
                    scan.nextLine();
                    continue;
                }
            }
        }
        scan.close();
    }

    private static boolean loadData(Scanner scan) {
        String input = "";
        Status returnStatus;
        boolean validInput = false;

        while (!validInput) {
            System.out.println("Type 'xml' or 'json' for loading data");
            input = scan.nextLine();

            if (XML.equals(input) || JSON.equals(input)) {
                validInput = true;
                //Manager manager = new Manager();
                returnStatus = manager.loadData(input);

                System.out.println(returnStatus.getMessage());

                return returnStatus.isSuccessfull();

            } else {
                System.out.println("Wrong input");
            }
            // Manager manager = new Manager();
            // manager.loadData();

        }

        return false;
    }

}
