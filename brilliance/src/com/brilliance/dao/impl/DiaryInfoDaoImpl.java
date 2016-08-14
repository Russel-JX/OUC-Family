package com.brilliance.dao.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.brilliance.base.BriException;
import com.brilliance.base.impl.BaseDaoImpl;
import com.brilliance.dao.DiaryInfoDao;
import com.brilliance.po.DiaryInfo;
import com.brilliance.utils.Constants;
import com.brilliance.utils.Tools;

@Repository
public class DiaryInfoDaoImpl extends BaseDaoImpl<DiaryInfo> implements DiaryInfoDao {

	@Autowired
	private HibernateTemplate readHibernateTemplate;
	
	public void addDiaryInfo(DiaryInfo diaryInfo){
		save(diaryInfo);
	}
	
//	public DiaryInfo getDiaryInfoById(DiaryInfo diaryInfo) throws BriException{
//		
//	}
	
	//获取当天的日记 - 一个
	public DiaryInfo getDiaryInfoByDate(DiaryInfo diaryInfo) throws BriException{
		String hql=" from DiaryInfo a where a.diaryDate =:diaryDate";
		//String hql=" from DiaryInfo a where a.id =:id";
		Query query = getReadSession().createQuery(hql);
		query.setParameter("diaryDate", diaryInfo.getDiaryDate());
		//query.setParameter("id", diaryInfo.getId());
		@SuppressWarnings("unchecked")
		List<DiaryInfo> list = query.list();
		if(list==null || list.size()==0){
			return null;
		}
		return list.get(0);
	}
	
	@SuppressWarnings("deprecation")
	public List<DiaryInfo> getDiaryList(DiaryInfo diaryInfo) throws BriException, ParseException{
		StringBuilder hql = new StringBuilder();
		hql.append("select a.id,a.diaryType,a.deleteFlag,a.diaryTitle,a.source,a.author,a.diaryDate,a.content,a.love, "
				+ " a.dislove,a.memo, a.createTime from DiaryInfo a where a.deleteFlag=1 ");
		
		if(!StringUtils.isEmpty(diaryInfo.getDiaryType())){
			hql.append(" and a.diaryType = :diaryType");
		}
		if(!StringUtils.isEmpty(diaryInfo.getDiaryTitle())){
			hql.append(" and a.diaryTitle like:diaryTitle");
		}
		if(!StringUtils.isEmpty(diaryInfo.getAuthor())){
			hql.append(" and a.author like :author");
		}
		if(!StringUtils.isEmpty(diaryInfo.getSource())){
			hql.append(" and a.source like :source");
		}
		if(!Tools.isNull(diaryInfo.getDiaryDate())){
			hql.append(" and a.diaryDate=:diaryDate");
		}
		if(!Tools.isNull(diaryInfo.getDiaryStartDate())){
			hql.append(" and a.diaryDate>=:diaryStartDate");
		}
		if(!Tools.isNull(diaryInfo.getDiaryEndDate())){
			hql.append(" and a.diaryDate<=:diaryEndDate");
		}
		
		List<DiaryInfo> lstDiary = new ArrayList<DiaryInfo >();
		Query query = getReadSession().createQuery(hql.toString());
		
		
		if(!StringUtils.isEmpty(diaryInfo.getDiaryType())){
			query.setParameter("diaryType", diaryInfo.getDiaryType());
		}
		if(!StringUtils.isEmpty(diaryInfo.getDiaryTitle())){
			query.setParameter("diaryTitle", "%"+diaryInfo.getDiaryTitle()+"%");
		}
		if(!StringUtils.isEmpty(diaryInfo.getAuthor())){
			query.setParameter("author", "%"+diaryInfo.getAuthor()+"%");
		}
		if(!StringUtils.isEmpty(diaryInfo.getSource())){
			query.setParameter("source", "%"+diaryInfo.getSource()+"%");
		}
		if(!Tools.isNull(diaryInfo.getDiaryDate())){
			query.setParameter("diaryDate", diaryInfo.getDiaryDate());
		}
		if(!Tools.isNull(diaryInfo.getDiaryStartDate())){
			query.setParameter("diaryStartDate", diaryInfo.getDiaryStartDate());
		}
		if(!Tools.isNull(diaryInfo.getDiaryEndDate())){
			query.setParameter("diaryEndDate", diaryInfo.getDiaryEndDate());
		}
		
		//加上悲观锁
		query.setLockMode("diaryList", LockMode.UPGRADE);
		
		List<?> list = query.list();

		if(!CollectionUtils.isEmpty(list)){
			Iterator<?> itor = list.iterator();
			while (itor.hasNext()){
				Object[] obj = (Object[]) itor.next();
				DiaryInfo info = new DiaryInfo();
				
				info.setId(Integer.parseInt(obj[0].toString()));
				info.setDiaryType(String.valueOf(obj[1]));
				info.setDeleteFlag(String.valueOf(obj[2]));
				info.setDiaryTitle(String.valueOf(obj[3]));
				info.setSource(String.valueOf(obj[4]));
				info.setAuthor(String.valueOf(obj[5]));
				info.setDiaryDate(Tools.parseToDate(String.valueOf(obj[6]), Constants.DATE_PATTEN));
				
				info.setContent(String.valueOf(obj[7]));
				info.setLove(String.valueOf(obj[8]));
				info.setDislove(String.valueOf(obj[9]));
				info.setMemo(String.valueOf(obj[10]));
				info.setCreateTime(Tools.parseToDate(String.valueOf(obj[11]), Constants.DATE_PATTEN_SEC));
				
				lstDiary.add(info);
			}
		}
		return lstDiary;
	}
	
	public int deleteDiaryInfo(Integer[] diaryIds){
		String hql = "delete from DiaryInfo where id in:diaryIds";
		Query query = getReadSession().createQuery(hql);
		query.setParameterList("diaryIds", diaryIds);
		return query.executeUpdate();
	}
	
	public void updateDiaryInfo(DiaryInfo diaryInfo){
		update(diaryInfo);
	}
	
}
