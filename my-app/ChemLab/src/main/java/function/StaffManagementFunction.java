package function;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import entity.StaffManagement;
import web.StaffManagementArgumentObject;

public interface StaffManagementFunction {
	StaffManagement findStaffByName(String name);
	
	StaffManagement findStaffById(Integer id);
	
	StaffManagement addStaff(StaffManagementArgumentObject staffManagementArgumentObject) throws Exception;
	
	StaffManagement updateStaff(StaffManagementArgumentObject staffManagementArgumentObject) throws Exception;
	
	Map findAllStaff(StaffManagementArgumentObject staffManagementArgumentObject);
	
	void deleteById(Integer id);

}
