package cn.Hlmove.service;

import cn.Hlmove.dao.TOrderOrderDao;
import cn.Hlmove.dao.TOrderOrderitemsDao;
import cn.Hlmove.entities.TOrderCartEntity;
import cn.Hlmove.entities.TOrderOrderEntity;
import cn.Hlmove.entities.TOrderOrderitemsEntity;
import cn.Hlmove.entities.TUserReceiveEntity;
import com.sun.corba.se.spi.presentation.rmi.IDLNameTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class TOrderOrderService {

    @Autowired
    TOrderCartService cartService;

    @Autowired
    TOrderOrderDao orderDao;

    @Autowired
    TOrderOrderitemsDao orderitemsDao;

    @Transactional
    public void generateOrder(int userid, TUserReceiveEntity receiver, Integer orderId, double sum){

        try{
            //生成订单
            TOrderOrderEntity orderOrderEntity = new TOrderOrderEntity();
            orderOrderEntity.setOrderid(orderId);
            orderOrderEntity.setUserid(userid);
            orderOrderEntity.setReceivename(receiver.getReceivename());
            orderOrderEntity.setProvince(receiver.getProvince());
            orderOrderEntity.setAddress(receiver.getAddress());
            orderOrderEntity.setTel(receiver.getTel());
            orderOrderEntity.setOrdersum(sum);
            orderOrderEntity.setOrdertime(new Timestamp(new Date().getTime()));
            orderDao.insert(orderOrderEntity);              //insert


            //生成订单明细
            List<TOrderCartEntity> cartEntities = cartService.list(userid);
            for(TOrderCartEntity cartEntity : cartEntities){
                TOrderOrderitemsEntity orderitemsEntity = new TOrderOrderitemsEntity();
                orderitemsEntity.setOrderid(orderId);
                orderitemsEntity.setProductid(cartEntity.getProductid());
                orderitemsEntity.setProductName(cartEntity.getProductName());
                orderitemsEntity.setProductPrice(cartEntity.getProductPrice());
                orderitemsEntity.setBuynum(cartEntity.getProductNum());
                orderitemsDao.insert(orderitemsEntity);         //N个insert
            }

            //清空购物车
            int result = cartService.removeByUserid(userid);    //delete
        }catch (Exception e){
            //事务回滚
            throw new RuntimeException("生成订单过程中，发生异常情况，启动事务回滚，请重新下单...");
        }

    }



    //管理员列表
    public List<TOrderOrderEntity> list(){
        //调用Dao层代码
        List<TOrderOrderEntity> entities = orderDao.select();
        return entities;
    }

    //分页支持
    public List<TOrderOrderEntity> list(int pageIndex, int pageSize){
        //调用Dao层代码
        //根据pageIndex 1、pageSize 3 求出 offset、length

        int offset = (pageIndex-1)*pageSize;
        int length = pageSize;

        List<TOrderOrderEntity> entities = orderDao.selectPager(offset, length);
        return entities;
    }



    //分页支持-返回总记录数
    public int selectRecordCount(){
        return orderDao.selectRecordCount();
    }

    public int insert(TOrderOrderEntity entity){
        //dao insert
        return orderDao.insert(entity);
    }



    public TOrderOrderEntity findById(String id){
        //dao insert
        TOrderOrderEntity entity = orderDao.selectById(id);
        return entity;
    }

    public int modify(TOrderOrderEntity entity){
        //调用Dao层代码
       int result = orderDao.update(entity);
        return result;
    }

    public int  remove(String orderid){
        //调用Dao层代码
    int result = orderDao.delete(orderid);
        return result;
    }










}
