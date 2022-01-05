package serviceUtil;

import Driver.Testing;
import daolayer.DAOQueries;
import daolayer.Reimbursements;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ReimbursementBuilder {
    ReimbursementBuilder builder;
    DAOQueries queries;
    private StringBuilder idGenerator(String pool, int idLength) {
        StringBuilder returnedID = new StringBuilder();


        Random randomGen = new Random();
        char[] idChar = new char[idLength];
        for (int i = 0; i < idLength; i++) {
            idChar[i] = pool.charAt(randomGen.nextInt(pool.length()));
        }
        for (char c : idChar) {
            returnedID.append(c);
        }
        return returnedID;
    }

    public StringBuilder idBuilder() {
        builder = new ReimbursementBuilder();
        StringBuilder firstTwo = builder.idGenerator("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 2);
        StringBuilder twoNums = builder.idGenerator("0123456789", 2);
        StringBuilder lastTwo = builder.idGenerator("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 2);
        return firstTwo.append(twoNums).append(lastTwo);
    }

    public LocalDate getDate() {
        return LocalDate.now();

    }

    public Reimbursements passStringForID(String variable) {
        return new Reimbursements(variable,
                0,
                null,
                0,
                "",
                "");
    }

    public Reimbursements passStringForStatus(String variable) {

        return new Reimbursements("",
                0,
                null,
                0,
                "",
                "s"
        );
    }
    public List<Reimbursements> compareByStatus(){
        List<Reimbursements> reimbursementsList = new DAOQueries().returnRequests();
        reimbursementsList.sort(Comparator.comparing(Reimbursements::getStatus));
        return reimbursementsList;
    }
    public List<Reimbursements> sortByDate(){
        List<Reimbursements> reimbursementsList = new DAOQueries().returnRequests();
        reimbursementsList.sort(Comparator.comparing(Reimbursements::getSubmittedDate));
        return reimbursementsList;
    }
    public List<Reimbursements> sortByUser(){
     queries = new DAOQueries();
        List<Reimbursements> reimbursementsList = queries.returnRequests();
        reimbursementsList.sort(Comparator.comparing(Reimbursements::getSubmittedBy));
//        reimbursementsList.sort((r1,r2)-> r1.getSubmittedBy() - (r2.getSubmittedBy()));
        return reimbursementsList;
    }
}