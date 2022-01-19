package Driver;

import daolayer.DAOQueries;
import daolayer.Reimbursements;
import daolayer.UserSpecs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import Driver.serviceUtil.ReimbursementBuilder;

import java.util.*;

public class Testing {
String[] myArray;

    public static void main(String[] args) {
        Logger loginLogger = LoggerFactory.getLogger("LoginLogger");
        Logger exceptions = LoggerFactory.getLogger("EXCEPTIONS");
        ReimbursementBuilder builder = new ReimbursementBuilder();
//        queries.returnJoinedList();
        Testing testing = new Testing();
//        Date date = Date.valueOf(builder.getDate());
        String ID = String.valueOf(builder.idBuilder());
        final String STATUSAPPROVED = "Approved";
        final String STATUSDENIED = "Denied";
    UserSpecs specs = new UserSpecs(0, "null", "null", "IndenturedServant123","password", false);
    List<UserSpecs> specsList = builder.validateUserService(specs);
        Reimbursements requests = new Reimbursements();

            UserSpecs userSpecs1 = new UserSpecs(1, "firstName", "lastName",
                    "Login","Pass",true);
        System.out.println(specsList);
            //        try{
//            System.out.println(requests.getSubmittedBy());
//            System.out.println(requests.getSubmittedBy() + "" + queries.returnSum(requests.getSubmittedBy()));
//        }catch (Exception e){
//            exceptions.error(e.getMessage(),e);
//        }
//        System.out.println(new DAOQueries().validateUser(specs.getUserLogin(),specs.getUserPass()));


//        System.out.println(builder.requestsByUserService(requests));

//        try {
//
//      Integer a = 0;
//            Reimbursements reimbursements = new Reimbursements("null","",date,
//                    10000,"Because maaaan",
//            "null");
//
//        System.out.println(queries.returnMasterList());

//
//        } catch (Exception e) {
//           exceptions.debug(e.getMessage(),e);
////       }
////        System.out.println(reimbursementsList);
//
//        }
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
}
