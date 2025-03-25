package dao;

import entity.Adminmodel;
import exception.*;

public interface Admindao {

	Adminmodel getAdminById(int adminId)throws AdminNotFoundException;
	Adminmodel getAdminByUsername(Adminmodel user)throws AdminNotFoundException;
	boolean registerAdmin(Adminmodel admin);
	boolean updateAdmin(Adminmodel admin);
	boolean deleteAdmin(int adminId);
}
