package DataObjects;

public class Customer {
    private static final int MAX_NAME_LENGTH = 15;
    private static final int MIN_NAME_LENGTH = 2;
    private static final String NAME_LENGTH_WARNING = "name must be " + MIN_NAME_LENGTH + "-" + MAX_NAME_LENGTH + " long.";
    
    private String customerId;
    private String firstName;
    private String lastName;
    
    //-------------------//
    //--- Constructor ---//
    //-------------------//
    public Customer(String customerId, String firstName, String lastName){
        setCustomerId(customerId);
        setFullName(firstName,lastName);
    }
       
    //----------------------//
    //--- Setter Methods ---//
    //----------------------//
    
    public final void setCustomerId(String customerId){
        if(customerId == null)
            throw new IllegalArgumentException("Customer ID May Not Be Null");
        this.customerId = customerId;
    }
    
    public final void setFullName(String firstName, String lastName){
        setFirstName(firstName); setLastName(lastName);
    }
    
    public final void setFirstName(String firstName){
        if(firstName == null || !nameIsProperLength(firstName))
            throw new IllegalArgumentException("First " + NAME_LENGTH_WARNING);
        this.firstName = firstName;
    }
    
    public final void setLastName(String lastName){
        if(lastName == null || !nameIsProperLength(lastName))
            throw new IllegalArgumentException("Last " + NAME_LENGTH_WARNING);
        this.lastName = lastName;
    }
    
    //----------------------//
    //--- Getter Methods ---//
    //----------------------//
    
    public final String getCustomerId(){return customerId;}
    public final String getFullName(){return firstName + " " + lastName;}
    public final String getFirstName(){return firstName;}
    public final String getLastName(){return lastName;}
    
    //----------------------//
    //-- Validater Methods -//
    //----------------------//
    
    private final boolean nameIsProperLength(String testName){
        return testName.length() >= MIN_NAME_LENGTH && testName.length() <= MAX_NAME_LENGTH;
    }
    
}
