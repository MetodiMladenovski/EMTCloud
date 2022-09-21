package mk.finki.ukim.companymanagement.domain.exceptions;

public class CompanyNotFoundException extends RuntimeException{
    public CompanyNotFoundException(){
        super("Company was not found");
    }
}
