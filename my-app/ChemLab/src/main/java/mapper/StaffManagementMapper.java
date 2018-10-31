package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.StaffManagement;


public interface StaffManagementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StaffManagement record);

    int insertSelective(StaffManagement record);

    StaffManagement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StaffManagement record);

    int updateByPrimaryKey(StaffManagement record);
    
    StaffManagement selectByUniqueField(@Param("key")String ukfield,@Param("value")Object keyword);
    
    StaffManagement selectLike(@Param("value")String keyword);//@Param将变量keyword作为参数value传入Mapper.xml文件
    
    void deleteByUniqueField(@Param("key")String ukfield,@Param("value")Object keyword);
    
    boolean exists(@Param("key")String ukfield,@Param("value")Object keyword)throws Exception;
    
    Map selectAll();
}