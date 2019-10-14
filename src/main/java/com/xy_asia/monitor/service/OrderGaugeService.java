package com.xy_asia.monitor.service;

import com.xy_asia.monitor.metrics.MainMetricsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class OrderGaugeService {

    /**
     * xone datasource
     */
    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    /**
     * sone datasource
     */
    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate2;


    /**
     * sx datasource
     */
    @Autowired
    @Qualifier("shaxianJdbcTemplate")
    private JdbcTemplate sxjdbcTemplate;

    @Autowired
    private MainMetricsBean mainMetricsBean;

    public void setGauge() {

        LocalDate date = LocalDate.now();
        LocalDate yesterDay = date.minusDays(1);
        LocalDateTime tenMinusAgo = LocalDateTime.now().minusMinutes(10);
        //获取昨天的日期
        System.out.println(yesterDay);

        String sxExceptionOrderSql = "SELECT count(*) FROM St_Order WHERE  State = 2 AND RecTime >= ? AND RecTime < ?";

        String exceptionOrderSql = "SELECT count(*) FROM St_OrderQueue WHERE State >= 1 AND RecTime > ?";

        String longExceptionOrderSql = "SELECT count(*) FROM St_OrderQueue WHERE State = 0 AND RecTime > ? AND RecTime < ?";


        int sxcount = sxjdbcTemplate.queryForObject(sxExceptionOrderSql, Integer.class, new Object[]{
                yesterDay,tenMinusAgo
        });
        System.out.println("shaxian is " + sxcount);

        mainMetricsBean.setShaxianExceptionOrder(sxcount);

        int count = jdbcTemplate.queryForObject(exceptionOrderSql, Integer.class, new Object[]{
                yesterDay
        });

        System.out.println("hls is " + count);

        mainMetricsBean.setHlsExceptionOrder(count);


        int count2 = jdbcTemplate.queryForObject(longExceptionOrderSql, Integer.class, new Object[]{
                yesterDay,tenMinusAgo
        });


         System.out.println("hls long is " + count2);


        mainMetricsBean.setHlsLongExceptionOrder(count2);

        //---------------------------------------------------------------------------------------------------------------------------------------

        count = jdbcTemplate2.queryForObject(exceptionOrderSql, Integer.class, new Object[]{
                yesterDay
        });

        System.out.println("sone is " + count);

        mainMetricsBean.setSoneExceptionOrder(count);


         count2 = jdbcTemplate2.queryForObject(longExceptionOrderSql, Integer.class, new Object[]{
                yesterDay,tenMinusAgo
        });


        System.out.println("sone long is " + count2);

        mainMetricsBean.setSoneLongExceptionOrder(count2);



        System.out.println(" 时间是："+yesterDay+"和"+tenMinusAgo+"shaxian long is " + sxcount);

        mainMetricsBean.setSoneLongExceptionOrder(sxcount);
    }
}
