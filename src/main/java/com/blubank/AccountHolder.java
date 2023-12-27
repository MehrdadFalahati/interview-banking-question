package banking;

/**
 * Abstract Account Holder.
 */
public abstract class AccountHolder {
    private int idNumber;
    
    /**
     * @param idNumber The holder unique ID.
     */
    protected AccountHolder(int idNumber) {
        this.idNumber = idNumber;
    }

    public int getIdNumber() {
        return idNumber;
    }
}
