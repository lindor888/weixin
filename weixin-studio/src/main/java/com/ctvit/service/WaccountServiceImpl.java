package com.ctvit.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ctvit.bean.Waccount;
import com.ctvit.dao.WaccountMapper;
import com.ctvit.dao.QueryDataBean;
import com.ctvit.util.KeyGenerator;

public class WaccountServiceImpl implements WaccountMapper {
	
    private	WaccountMapper waccountmapper;
    private KeyGenerator keyGenerator;
	private SqlSessionFactory sqlSessionFactory;

	public int deleteByPrimaryKey(String waccountId) {
		// TODO Auto-generated method stub
		return waccountmapper.deleteByPrimaryKey(waccountId);
	}

	public int insert(Waccount record) throws Exception {
		// TODO Auto-generated method stub
		
	 Byte i=0;
	 Byte j=0;
	 
	 record.setState(i);
	 record.setUrgentState(j);
	  String id = keyGenerator.getKey(record);
	  record.setWaccountId(id);
		
		return waccountmapper.insert(record);
	}
	
	public Waccount checknameBywaccount(Waccount record){
		
		
		return waccountmapper.checknameBywaccount(record);
	}

	public int insertSelective(Waccount record) throws Exception {
		// TODO Auto-generated method stub
		return waccountmapper.insertSelective(record);
	}

	public Waccount selectByPrimaryKey(String waccountId) {
		// TODO Auto-generated method stub
		return waccountmapper.selectByPrimaryKey(waccountId);
	}

	public int updateByPrimaryKeySelective(Waccount record) throws Exception {
		// TODO Auto-generated method stub
		return waccountmapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Waccount record) throws Exception {
		// TODO Auto-generated method stub
		
		
		
		return waccountmapper.updateByPrimaryKey(record);
	}
	
	
	
	public Waccount selectBynoReplyContent(String waccountId) {
		// TODO Auto-generated method stub
		SqlSession session = sqlSessionFactory.openSession();
		
		Waccount  account =null;
			try {
				
				  account = (Waccount)session.selectOne("com.ctvit.dao.WaccountMapper.selectBynoReplyContent",waccountId);
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				session.close();
			}
		
		
		return account;
	}
	
	

	public Waccount selectBywelcomcontent(String waccountId) {
		// TODO Auto-generated method stub
		
		
SqlSession session = sqlSessionFactory.openSession();
		
		Waccount  account =null;
			try {
				
				  account = (Waccount)session.selectOne("com.ctvit.dao.WaccountMapper.selectBywelcomcontent",waccountId);
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				session.close();
			}
		
		
		return account;
		
	}
	
	
	

	public List<Waccount> selectByaccount(QueryDataBean query) {
		// TODO Auto-generated method stub
				int page = query.getPage();
				int rows = query.getRows();
				int start = (page-1)*rows;
				query.setPage(start);
				
				List result = null;
				SqlSession session = sqlSessionFactory.openSession();
			//	query = setQueryConditions(query);
				try {
					if(query.isPagination()){
						result = session.selectList("com.ctvit.dao.WaccountMapper.selectByaccountpage",query);
					}else{
						result = session.selectList("com.ctvit.dao.WaccountMapper.selectByaccount",query);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					session.close();
				}
				return result;
	}

	public List<Waccount>	selectByExample(QueryDataBean query){
		// TODO Auto-generated method stub
		int page = query.getPage();
		int rows = query.getRows();
		int start = (page-1)*rows;
		query.setPage(start);
		
		List result = null;
		SqlSession session = sqlSessionFactory.openSession();
	//	query = setQueryConditions(query);
		try {
			if(query.isPagination()){
				result = session.selectList("com.ctvit.dao.WaccountMapper.selectByExamplepage",query);
			}else{
				result = session.selectList("com.ctvit.dao.WaccountMapper.selectByExample",query);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return result;
	}
	public List<Waccount> selectWaccountList()throws Exception{
		List<Waccount> list=waccountmapper.selectWaccountList();
		return list;
	}
	public List<Waccount> selectWaccountListbyAccountId(String accountId)throws Exception{
		List<Waccount> list=waccountmapper.selectWaccountListbyAccountId(accountId);
		return list;
	}
	
	public WaccountMapper getWaccountmapper() {
		return waccountmapper;
	}

	public void setWaccountmapper(WaccountMapper waccountmapper) {
		this.waccountmapper = waccountmapper;
	}

	public KeyGenerator getKeyGenerator() {
		return keyGenerator;
	}

	public void setKeyGenerator(KeyGenerator keyGenerator) {
		this.keyGenerator = keyGenerator;
	}

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	
	
	
}
