package projectzero;

import java.util.List;

public interface UserAccountRepository {

	void createAccount();
	
	void createUser(VirtualDollarsUser user);
	
	public VirtualDollarsAccount getLastAccount();
	
	VirtualDollarsAccount findByAccountId(int id);
	
	VirtualDollarsUser findByUsername(String username);
	
	public VirtualDollarsEmployee findByEmployeeName(String name);
	
	List<VirtualDollarsUser> findUsersByAccountId(int id);
	
	List<VirtualDollarsAccount> findAllAccounts();
	
	List<VirtualDollarsUser> findAllUsers();
	
	void updateAccountData(VirtualDollarsAccount account);
	
	void updateUserData(VirtualDollarsUser user);
	
	void deleteAccount(VirtualDollarsAccount account);
	
	void deleteUser(VirtualDollarsUser user);
} // End interface
