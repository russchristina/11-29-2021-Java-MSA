package Driver;

import daolayer.DAOQueries;
import daolayer.Reimbursements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import serviceUtil.ReimbursementBuilder;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Testing {


    public static void main(String[] args) {
        Logger loginLogger = LoggerFactory.getLogger("LoginLogger");
        Logger exceptions = LoggerFactory.getLogger("EXCEPTIONS");
//
        ReimbursementBuilder builder = new ReimbursementBuilder();
//
//        Testing testing = new Testing();
//            Date date = Date.valueOf(builder.getDate());
//            String ID =String.valueOf(builder.idBuilder());
//        final String STATUSAPPROVED = "Approved";
//        final String STATUSDENIED = "Denied";
//        Reimbursements reimbursements = new Reimbursements(
//                "TE97VV", 1, date, 50000, "Because I want money", STATUSAPPROVED);
//       Reimbursements testingClass = builder.passStringForStatus(STATUSAPPROVED);
//
//       try{
//           queries.deleteRequest(reimbursements);
//       }catch (Exception e){
//           System.out.println("An error occured. Please try again.");
//           exceptions.debug(e.getMessage(),e);
//       }
//        System.out.println(reimbursementsList);
        DAOQueries queries = new DAOQueries();

        System.out.println(builder.sortByUser());
        }
//        queries.returnMasterList();
//        try {
//            UserSpecs check = new UserSpecs(1, "John","last", "IndenturedServant123","hateithere",false);
//            UserSpecs pass = queries.gatherByUsernane(check);
//            if (pass.getManager()) System.out.println(pass);
//            else System.out.println("u aint no mf admin");
//
//        }catch (NullPointerException e){
//
//            loginLogger.info(e.getMessage(),e);
//
//        }


//        System.out.println(time);


        //        userSpecsList.add(pass);
//        for(UserSpecs specs : userSpecsList){
//            System.out.println(specs);
//        }
    }

