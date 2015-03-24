import co.edu.eafit.conferre.data.base.FactoryDAO;
import co.edu.eafit.conferre.data.dao.RenterDAO;
import co.edu.eafit.conferre.data.to.RenterTO;


public class Main {
  
  public static void main(String [] args) {
    RenterTO renter = new RenterTO();
    renter.setName("Santiago Vanegas Gil");
    renter.setIdentification("1037639183");
    renter.setPhoneNumber("3176565594");
    renter.setEmail("svanega4@eafit.edu.co");
    renter.setPassword("123");
    renter.setMale(true);
    RenterDAO renterDAO = FactoryDAO.createRenterDAO();
    RenterTO result = (RenterTO) renterDAO.create(renter);
    System.out.println("Created, id: " + result.getId());
  }
}
