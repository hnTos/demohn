package com.hn.example.demohn.web.circulDepend;

/**
 * @Author: liyb
 * @Date: 2019/6/10
 * @description
 */

import org.springframework.aop.framework.AopProxy;

import java.lang.reflect.Modifier;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class TestProxy {

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        //每月5号发送上月的差错汇总
        //if (calendar.get(Calendar.DAY_OF_MONTH) == 5) {
             calendar.set(Calendar.MONTH,1);
            calendar.set(Calendar.DAY_OF_MONTH,1);
            System.out.println(calendar.getTime());
            calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)-1);
            Date beginDate = calendar.getTime();
            System.out.println(beginDate);
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            Date endDate = calendar.getTime();
            System.out.println(endDate);
            //Map data = reconciliationEmailManager.reconciliation("1528647831",beginDate,date);
        //}
		/*ClassProxy proxy = new ClassProxy();
		AopProxy bookProxy = (AopProxy) proxy.bind(new ClassProxy());
		Object obj= bookProxy.getProxy();
	    int i = bookProxy.getClass().getModifiers();
        String mods = Modifier.toString(i);
        System.out.println("Class modifiers: " + mods);*/
		/*CglibProxy cglib = new CglibProxy();
		ClassProxy bookCglib=(ClassProxy)cglib.getInstance(new ClassProxy());
		bookCglib.getProxy();
        System.out.println("Class modifiers: " +  Modifier.toString(bookCglib.getClass().getModifiers()));*/
		/*ClassProxy bookCglib2=(ClassProxy)cglib.getInstance(bookCglib);
		bookCglib2.getProxy();*/

        //JDK代理后再进行一层jdk代理，测试结果不可以，循环调用，造成代理的死循环
		/*ClassProxy proxy = new ClassProxy();
		ClassProxy proxyclass = new ClassProxy();
		Object obj = proxy.bind(new ClassProxy());
		String mods = Modifier.toString(obj.getClass().getModifiers());
		System.out.println("Class modifiers: " + mods);
		AopSonProxy bookProxy2 = (AopSonProxy) proxyclass.bind(obj);
		String mods2 = Modifier.toString(bookProxy2.getClass().getModifiers());
		System.out.println("Class modifiers: " + mods2);
		bookProxy2.getProxy();*/


        //jdk代理后再用cglib进行代理，测试结果不行，因为jdk代理后的类是public final类型的，cglib不能对final的类进行代理
		/*ClassProxy proxy = new ClassProxy();
		AopProxy bookProxy = (AopProxy) proxy.bind(new ClassProxy());
		bookProxy.getProxy();
	    int i = bookProxy.getClass().getModifiers();
        String mods = Modifier.toString(i);
        System.out.println("Class modifiers: " + mods);
		CglibProxy cglib = new CglibProxy();
		ClassProxy bookCglib=(ClassProxy)cglib.getInstance(bookProxy);
		bookCglib.getProxy();*/

        //CGlib代理后再进行cglib代理，测试结果不可以  Caused by: java.lang.ClassFormatError: Duplicate method name&signature
		/*CglibProxy cglib = new CglibProxy();
		ClassProxy bookCglib=(ClassProxy)cglib.getInstance(new ClassProxy());

		ClassProxy bookCglib2=(ClassProxy)cglib.getInstance(bookCglib);
		bookCglib2.getProxy();*/

        //CGlib代理后再进行jdk代理，测试结果不可以，因为CGlib代理后已经改变了类的签名，而jdk必须知道类的接口和实现
        /*CglibProxy cglib = new CglibProxy();
        ClassProxy bookCglib=(ClassProxy)cglib.getInstance(new ClassProxy());
        bookCglib.getProxy();
        String mods = Modifier.toString(bookCglib.getClass().getModifiers());
        System.out.println("Class modifiers: " + mods);
        ClassProxy proxy = new ClassProxy();
        Object obj =proxy.bind(bookCglib);
        System.out.println( "------------"+Modifier.toString(obj.getClass().getModifiers()));

       Object object =  cglib.getInstance(bookCglib);
        *///System.out.println( "------------"+Modifier.toString(object.getClass().getModifiers()));

    }


}
