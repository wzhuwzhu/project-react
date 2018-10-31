package function;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.*;
import function.*;
import entity.StaffManagement;
import mapper.StaffManagementMapper;
import web.StaffManagementArgumentObject;

@Service
public class StaffManagementFunctionImpl implements StaffManagementFunction{
	@Autowired
    StaffManagementMapper smMapper; //鍒涘缓瀵硅薄锛岃皟鐢∕apper鏂规硶
	
	@Override
	public	StaffManagement findStaffByName(String name){
        //1 楠岃瘉鍙傛暟
        name=AssertThrowUtil.assertNotBlank("Name cannot be blank!",name);
		//2 涓氬姟澶勭悊
		StaffManagement staff=smMapper.selectByUniqueField("name", name); 
		
		if(staff==null){
			throw new ThisSystemException("Cannot find this staff["+name+"].");
		}
		//3 灏佽涓氬姟缁撴灉
		return staff;
	};
	
	@Override
	public	StaffManagement findStaffById(Integer id){
        id=AssertThrowUtil.assertNotBlank("Id cannot be blank!",id);
		StaffManagement staff=smMapper.selectByUniqueField("id", id); 
		if(staff==null){
			throw new ThisSystemException("Cannot find this staff["+id+"].");
		}
		return staff;
	};
	
	@Override
	public	StaffManagement addStaff(StaffManagementArgumentObject staffManagementArgumentObject) throws Exception{
		//1 鍙傛暟楠岃瘉
				if(staffManagementArgumentObject==null){
					throw new IllegalArgumentException("argumentObjec涓嶈兘涓虹┖");
				}
				//1.1 楠岃瘉鏄惁蹇呴』杈撳叆瀛楁
				String name=AssertThrowUtil.assertNotBlank("Name cannot be blank!",staffManagementArgumentObject.getName());
				//1.2 楠岃瘉鍚嶅瓧鏄惁宸插瓨鍦�
				AssertThrowUtil.assertFalse("Staff already exists!",smMapper.exists("name",name));
				//1.3楠岃瘉鏃ユ湡鏍煎紡骞惰幏鍙栨棩鏈�
				Date yearOfGraduation = AssertThrowUtil.assertDatePatternAndParse("Illegal input!","\\d{4}",staffManagementArgumentObject.getYearOfGraduation(),new String[]{"yyyy"});
				Date enrolledSince = AssertThrowUtil.assertDatePatternAndParse("Illegal input!","\\d{4}",staffManagementArgumentObject.getEnrolledSince(),new String[]{"yyyy"});
				Date yearOfBirth = AssertThrowUtil.assertDatePatternAndParse("Illegal input!","\\d{8}",staffManagementArgumentObject.getEnrolledSince(),new String[]{"yyyymmdd"});
				
				//2 涓氬姟澶勭悊
				StaffManagement staff=new StaffManagement();
								
				boolean gender="1".equals(staffManagementArgumentObject.getGender());
				
				staff.setName(name);
				staff.setGender(gender);
				staff.setDepartment(staffManagementArgumentObject.getDepartment());
				staff.setPosition(staffManagementArgumentObject.getPosition());
				staff.setPermission(staffManagementArgumentObject.getPermission());
				staff.setTechnicalTitle(staffManagementArgumentObject.getTechnicalTitle());
				staff.setDegree(staffManagementArgumentObject.getDegree());
				staff.setGraduatedFrom(staffManagementArgumentObject.getGraduatedFrom());
				staff.setMajor(staffManagementArgumentObject.getMajor());
				staff.setYearOfGraduation(yearOfGraduation);
				staff.setEnrolledSince(enrolledSince);
				staff.setPhoneNo(staffManagementArgumentObject.getPhoneNo());
				staff.setHometown(staffManagementArgumentObject.getHometown());
				staff.setYearOfBirth(yearOfBirth);
				staff.setRemark(staffManagementArgumentObject.getRemark());
				
				//3 灏佽涓氬姟缁撴灉
				smMapper.insertSelective(staff);
				return staff;
	}
	/*
	 闇�鍦╲iew鐣岄潰鎵цCRUD鍛戒护锛屽 
	SqlSession session = MyBatisUtil.getSession();
	StaffManagementArgumentObject staffManagementAO
	staffManagementAO.setName(textfieldName);
	staffManagementAO.setGender(textfieldGender);
	...
	
	StaffManagement staff = StaffManagementFuncitonImpl.addStaff(staffManagementAO);
	session.updateByPrimaryKeySelective(staff);
	MyBatisUtil.closeSession();
	*/
	
	@Override
	public	StaffManagement updateStaff(StaffManagementArgumentObject staffManagementArgumentObject) throws Exception{
		//1 鍙傛暟楠岃瘉
				if(staffManagementArgumentObject==null){
					throw new IllegalArgumentException("argumentObjec涓嶈兘涓虹┖");
				}
				//1.1 楠岃瘉鍚嶅瓧鏄惁宸插瓨鍦�
				String name = staffManagementArgumentObject.getName();
				AssertThrowUtil.assertTrue("Staff doesn't exist!",smMapper.exists("name",name));
				//1.2楠岃瘉鏃ユ湡鏍煎紡锛屽苟鑾峰彇鏃ユ湡
				Date yearOfGraduation = AssertThrowUtil.assertDatePatternAndParse("Illegal input!","\\d{4}",staffManagementArgumentObject.getYearOfGraduation(),new String[]{"yyyy"});
				Date enrolledSince = AssertThrowUtil.assertDatePatternAndParse("Illegal input!","\\d{4}",staffManagementArgumentObject.getEnrolledSince(),new String[]{"yyyy"});
				Date yearOfBirth = AssertThrowUtil.assertDatePatternAndParse("Illegal input!","\\d{8}",staffManagementArgumentObject.getEnrolledSince(),new String[]{"yyyymmdd"});
				
				//2 涓氬姟澶勭悊
				StaffManagement staff=new StaffManagement();
								
				boolean gender="1".equals(staffManagementArgumentObject.getGender());
				
				staff.setName(name);
				staff.setGender(gender);
				staff.setDepartment(staffManagementArgumentObject.getDepartment());
				staff.setPosition(staffManagementArgumentObject.getPosition());
				staff.setPermission(staffManagementArgumentObject.getPermission());
				staff.setTechnicalTitle(staffManagementArgumentObject.getTechnicalTitle());
				staff.setDegree(staffManagementArgumentObject.getDegree());
				staff.setGraduatedFrom(staffManagementArgumentObject.getGraduatedFrom());
				staff.setMajor(staffManagementArgumentObject.getMajor());
				staff.setYearOfGraduation(yearOfGraduation);
				staff.setEnrolledSince(enrolledSince);
				staff.setPhoneNo(staffManagementArgumentObject.getPhoneNo());
				staff.setHometown(staffManagementArgumentObject.getHometown());
				staff.setYearOfBirth(yearOfBirth);
				staff.setRemark(staffManagementArgumentObject.getRemark());
				
				//3 灏佽涓氬姟缁撴灉
				smMapper.updateByPrimaryKeySelective(staff);
				return staff;
	}
	
	@Override
	Map findallStaff(){
		Map staff = smMapper.selectAll();
		return staff;
	}
	
	@Override
	void deleteById(Integer id) {
		smMapper.deleteByPrimaryKey(id);
	}
}
