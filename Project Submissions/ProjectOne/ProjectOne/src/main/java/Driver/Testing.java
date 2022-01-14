package Driver;

import daolayer.DAOQueries;
import daolayer.Reimbursements;
import daolayer.UserSpecs;
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
        DAOQueries queries = new DAOQueries();
        ReimbursementBuilder builder = new ReimbursementBuilder();
//        queries.returnJoinedList();
        Testing testing = new Testing();
//        Date date = Date.valueOf(builder.getDate());
        String ID = String.valueOf(builder.idBuilder());
        final String STATUSAPPROVED = "Approved";
        final String STATUSDENIED = "Denied";
    UserSpecs specs = new UserSpecs(0, "null", "null", "IndenturedServant123","hateithere", false);
    List<UserSpecs> specsList = builder.validateUserService(specs);
//        System.out.println(new DAOQueries().validateUser(specs.getUserLogin(),specs.getUserPass()));

    //           Reimbursements requests = new Reimbursements("s", "IndenturedServant123",myDate,0,"","");

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
