package com.igate.izone.dao.impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.igate.izone.dao.ActivityDaoIntf;
import com.igate.izone.dao.BaseDao;
import com.igate.izone.dto.ActivityDTO;

@Repository
public class ActivityDaoImpl extends BaseDao implements ActivityDaoIntf {
	
	private static final String findActivityById_SQL = "select id,activityName,activityType,creator,createdDate,description from activity where id=?";
	private static final String createNewActivity_SQL = "insert into activity(activityName,activityType,creator,createdDate,description) values(?,?,?,?)";
	private static final String createNewActivitySharedTo_SQL = "insert into activity_sharing(activityID,sharedTo) values(?,?)";
	private static final String deleteActivityById_SQL = "delete from activity where id = ?";
	private static final String  updateActivity_SQL = "update activity set activityName=?,activityType=?,creator=?,createdDate=?,description=? where id=?";
	//private RowMapper<ActivityDTO> rowMapper;
	
	@Override
	//通过id查找一个活动
	public ActivityDTO findActivityById(Integer id) {
		ActivityDTO activityDTO = jdbcTemplate.queryForObject(
				findActivityById_SQL,
				new Object[]{id},
				new RowMapper<ActivityDTO>(){
			@Override
			public ActivityDTO mapRow(ResultSet rst, int index)
					throws SQLException {
				Integer activityId = rst.getInt("id");
				String activityName = rst.getString("activityName");
				Integer activityType = rst.getInt("acitivityType");
				String creator = rst.getString("creator");
				Date createdDate = rst.getDate("createdDate");
				String description = rst.getString("description");
				if("".equals(activityName) || "".equals(description) || 
				   activityId == null || creator == null || createdDate == null ||
				   activityName == null || description == null){
					return null;
				}else{
					return new ActivityDTO(activityId, activityName,activityType, creator, createdDate, description);
				}
			}
		});
		return activityDTO;
	}

	@Override
	//增加新的活动
	public int createNewActivity(ActivityDTO activityDTO) {
		KeyHolder key = new GeneratedKeyHolder();
		jdbcTemplate.update(
				createNewActivity_SQL, 
				new Object[]{
						activityDTO.getActivityName(),
						activityDTO.getCreator(),
						activityDTO.getCreatedDate(),
						activityDTO.getDescription()},key);
		return key.getKey().intValue();
	}
	
	@Override
	public int createNewActivitySharedTo(int activityID,String[] sharedTo) {
		int updateLines = 0;
		int needToShareLength = sharedTo.length;
		for(String share : sharedTo){
			jdbcTemplate.update(createNewActivitySharedTo_SQL, new Object[]{activityID,share});
			updateLines++;
		}
		if(updateLines == needToShareLength){
			return 1;
		}
		return 0;
	}
	@Override
	//按id删除活动
	public int deleteActivityById(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	//批量删除活动
	public int deleteActivityByBatch(Integer[] ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	//更新活动
	public int updateActivity(ActivityDTO activityDTO) {
		// TODO Auto-generated method stub
		return 0;
	}


}
