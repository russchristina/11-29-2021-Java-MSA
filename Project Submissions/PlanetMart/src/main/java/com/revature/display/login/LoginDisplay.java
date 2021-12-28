package com.revature.display.login;

import com.revature.display.utility.CreateShapes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class LoginDisplay {

    CreateShapes createShapes = new CreateShapes();
    private final Logger errorLogger = LoggerFactory.getLogger("errorLogger");

    public void opening(){
        File fileArt = new File("src/main/resources/PlanetArt1.txt");
        FileReader fileReaderArt = null;
        BufferedReader bufferedReader = null;
        try {
            fileReaderArt = new FileReader(fileArt);
            bufferedReader = new BufferedReader(fileReaderArt);
            while(bufferedReader.ready()){
                String line = bufferedReader.readLine();
                System.out.println(line);
                Thread.sleep(60);
            }

        } catch (InterruptedException | IOException e) {
            errorLogger.error(String.valueOf(e));
        } finally{
            try {
                fileReaderArt.close();
                bufferedReader.close();
            } catch (IOException e) {
                errorLogger.error(String.valueOf(e));
            }
        }

    }

    public void printWelcomeOptions() {

        System.out.println(createShapes.border);
        System.out.println(createShapes.indent + "1. LOGIN TO ACCOUNT");
        System.out.println(createShapes.indent + "2. CREATE NEW CUSTOMER ACCOUNT");
        System.out.println(createShapes.indent + "3. EXIT APPLICATION");
        System.out.println(createShapes.border);

    }

    public void printLoginDisplayUsername() {
        System.out.println(createShapes.border);
        System.out.println(createShapes.indent + "LOGIN");
        System.out.println(createShapes.indent + "TYPE N TO RETURN");
        System.out.println(createShapes.border);
        System.out.print(createShapes.indent + "USERNAME:");
    }

    public void printLoginDisplayPassword() {
        System.out.print(createShapes.indent + "PASSWORD:");
    }

    public void printCreateAccountDisplay() {
        System.out.println(createShapes.border);
        System.out.println(createShapes.indent + "CREATING CUSTOMER ACCOUNT");
        System.out.println(createShapes.indent + "1. CONTINUE");
        System.out.println(createShapes.indent + "2. RETURN TO LOGIN");
        System.out.println(createShapes.border);

    }

    public void printCreateEmployeeAccountDisplay() {
        System.out.println(createShapes.indent + "CREATE EMPLOYEE ACCOUNT");
        System.out.println(createShapes.border);
        System.out.println(createShapes.indent + "1. Continue to account Creation");
        System.out.println(createShapes.indent + "2. Return");
        System.out.println(createShapes.border);

    }


    public void printCreateAdminAccountDisplay() {
        System.out.println(createShapes.indent + "CREATE ADMIN ACCOUNT");
        System.out.println(createShapes.border);
        System.out.println(createShapes.indent + "1. Continue to account Creation");
        System.out.println(createShapes.indent + "2. Return");
        System.out.println(createShapes.border);
    }
}
