package com.jluzh.sell.repository;

import com.jluzh.sell.dataobject.OrderDetail;
import com.jluzh.sell.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.crypto.Data;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void saveTest() {
        ProductInfo product = productInfoRepository.findById("002").orElse(null);
        OrderDetail orderDetail = new OrderDetail(
                "2",
                "1",
                product.getProductId(),
                product.getProductName(),
                product.getProductPrice(),
                1,
                product.getProductIcon()
        );

        OrderDetail detail = repository.save(orderDetail);
        Assert.assertNotNull(detail);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> detailList = repository.findByOrderId("1");
        Assert.assertNotEquals(0,detailList.size());
    }


    @Test
    public void test01(){
        List<Integer> s=new ArrayList<>();
        for(int i=0;i<=10;i++){
            s.add(i);
        }
        List<Integer> a =new ArrayList<>();
        a = s.subList(0, 5);
        System.out.println(a);
        a.clear();
        System.out.println(s);
        System.out.println(a);
    }

    @Test
    public  void test02(){
        SimpleDateFormat  df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        System.out.println(date.getTime());
    }

    @Test
    public void test03(){
        String a1="a";

        String a="aa";
        String b="bb";

        List<String> list=new ArrayList<>();
        list.add(a);
        list.add(b);
        System.out.println(a.contains(a1));
    }
    @Test
    public void test04(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String nowDate = dateFormat.format(new Date());
//        String a = "2020-01-01".replaceAll("-","");
//        System.out.println(a);
        System.out.println(nowDate);
        System.out.println("20200131".compareTo("20200202"));
    }

    @Test
    public void test05()throws Exception{
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //FBEI测评起始时间
        Date fbeiDateTime = dateFormat.parse("2020-01-12 08:30");
        String fbeiTime = dateFormat.format(fbeiDateTime);
        System.out.println(fbeiTime);
        //FBEI测评结束时间
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(dateFormat.parse(fbeiTime));//fbeiTime
        calendar2.add(Calendar.MINUTE, Integer.parseInt("120"));//结束时间
        String fbeiTimeEnd = dateFormat.format(calendar2.getTime());
        System.out.println(fbeiTimeEnd);

    }
}