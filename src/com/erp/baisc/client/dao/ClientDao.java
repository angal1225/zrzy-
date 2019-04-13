package com.erp.baisc.client.dao;//package com.erp.baisc.client.dao;
//
//import com.erp.baisc.client.bean.ClientBean;
//import com.formwork.base.dao.BaseDao;
//import com.formwork.db.JdbcParameter;
//import com.formwork.db.daoimp.SqlImpDao;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by IntelliJ IDEA.
// *
// * @author : 徐大伟
// * User: ${和敬清寂}
// * Date: 2019/4/1 0001
// */
//public class ClientDao extends BaseDao {
//
//    public ClientBean queryList(ClientBean peopleBean, SqlImpDao sqlImpDao){
//        List<JdbcParameter> inParams = new ArrayList<JdbcParameter>();
//        String sql = "select a.supplierId, a.supplierName, a.people_sex, "
//                + "a.people_age, a.people_phone, a.people_card, a.people_address "
//                + "from people_info a "
//                + "where 0=0";
//
//        if(peopleBean.getPeople_name() != null && !"".equals(peopleBean.getPeople_name())){
//            sql += " and a.people_name like ?";
//            inParams.add(new JdbcParameter("people_name", Types.VARCHAR, "%" + peopleBean.getPeople_name() + "%"));
//        }
//        if(peopleBean.getPeople_sex() != null && !"".equals(peopleBean.getPeople_sex())){
//            sql += " and a.people_sex = ?";
//            inParams.add(new JdbcParameter("people_sex", Types.VARCHAR, peopleBean.getPeople_sex()));
//        }
//        PageBean pageUtil = this.pageUtil(sql, inParams, peopleBean, sqlImpDao);
//
//        return pageUtil;
//    }
//}
