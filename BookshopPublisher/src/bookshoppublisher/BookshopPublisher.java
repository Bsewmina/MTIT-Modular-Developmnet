package bookshoppublisher;

public interface BookshopPublisher {

	public String checkPassword(String password);

	public  byte[] genarateHashedPass(String password) throws Exception;

	public Boolean checkUser(String userName);
}
