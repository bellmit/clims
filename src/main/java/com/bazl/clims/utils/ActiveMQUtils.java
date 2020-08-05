package com.bazl.clims.utils;
/*

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ScheduledMessage;
import org.apache.activemq.command.ActiveMQDestination;
import org.springframework.util.ObjectUtils;

import javax.jms.*;
import java.io.Serializable;
import java.util.UUID;

*/
/**
 * <b>function:</b> 消息发送者
 *//*

public class ActiveMQUtils
{
    // tcp 地址
   // public static final String BROKER_URL = "failover:(http://localhost:8161)";//http://localhost:8161
    // 默认连接地址
    private static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;
    public static final String DEFAULT_USER = "admin";
    public static final String DEFAULT_PASSWORD = "admin";

    */
/**
     * <b>function:</b> 发送消息
     * @author efang
     * @createDate 2018-12-28 下午12:05:42
     * @param session
     * @param producer
     * @param msgId 自定义msgId
     * @param message 消息体
     * @param delay 延时时间 单位：ms
     * @throws Exception
     *//*

    public static String sendMessage(Session session, MessageProducer producer,String msgId,Object message,long delay) throws Exception {
        */
/*SysUser sysUser = new SysUser();
        sysUser.setUsername("admin");
        sysUser.setPassword("admin");*//*

        if (null == msgId)
            msgId = UUID.randomUUID().toString().replaceAll("-","");

        //需要修改activemq.xml才能生效，在<broker>里添加属性schedulerSupport="true"
        TextMessage tm2 = session.createTextMessage("Send Message After 10 seconds!");
        ObjectMessage objectMessage2 = session.createObjectMessage((Serializable) message);

        long delayTime = 3 * 10 * 1000;
        //设置延时时间，不需要可以不设置
        objectMessage2.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, delay);
        objectMessage2.setStringProperty("selector",msgId);
        objectMessage2.setStringProperty("test","test2");
        System.out.println("msgId : " + msgId);
        producer.send(objectMessage2);

        return msgId;
    }

    */
/**
     * 发送消息
     * @param msgId
     * @param message
     * @param delay
     * @param destinationName
     * @return
     * @throws Exception
     *//*

    public static String sendMsg(String msgId,Object message,long delay,String destinationName) throws Exception {

        Connection connection = null;
        Session session = null;
        try {
            // 创建链接工厂
            ConnectionFactory factory = new ActiveMQConnectionFactory(
                    ActiveMQConnection.DEFAULT_USER,
                    ActiveMQConnection.DEFAULT_PASSWORD, BROKER_URL);
            ((ActiveMQConnectionFactory) factory).setTrustAllPackages(true);
            // 通过工厂创建一个连接
            connection = factory.createConnection();
            // 启动连接
            connection.start();
            // 创建一个session会话
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            // 创建一个消息队列
            Destination destination = session.createQueue(destinationName);
            // 创建消息制作者
            MessageProducer producer = session.createProducer(destination);

            // 设置持久化模式
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            msgId = sendMessage(session, producer,msgId,message,delay);
            // 提交会话
            session.commit();

        } catch (Exception e) {
            throw e;
        } finally {
            // 关闭释放资源
            if (session != null) {
                session.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return msgId;
    }

    */
/**
     * 根据msgId消费该消息，但是不做处理
     * 等同于删除了此消息
     * @param msgId
     *//*

    public static void ClearMessage(String msgId,String destinationName)
    {
        // ConnectionFactory ：连接工厂，JMS 用它创建连接
        ConnectionFactory connectionFactory;
        // Connection ：JMS 客户端到JMS Provider 的连接
        Connection connection = null;
        // Session： 一个发送或接收消息的线程
        Session session;
        // Destination ：消息的目的地;消息发送给谁.
        Destination destination;
        // 消费者，消息接收者
        MessageConsumer consumer;
        connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                BROKER_URL);
        ((ActiveMQConnectionFactory) connectionFactory).setTrustAllPackages(true);
        try {
            // 构造从工厂得到连接对象
            connection = connectionFactory.createConnection();
            // 启动
            connection.start();
            // 获取操作连接
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            // 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
            destination = session.createQueue(destinationName);
           consumer = session.createConsumer(destination);
          //  consumer = session.createConsumer(destination,"selector='" + msgId+"'");
            while (true) {
                ObjectMessage message = (ObjectMessage) consumer.receive(10);
                if (!ObjectUtils.isEmpty(message)) {
                    System.out.println("把消息拿下来但不处理！ messageId = " + message.getStringProperty("selector"));
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection)
                    connection.close();
            } catch (Throwable ignore) {
            }
        }
    }

    */
/**
     * 删除队列
     * 如果有consumer连在队列上，删除队列会失败！
     * 所以不建议使用
     * 这个函数有进程阻塞问题
     * @param url
     * @param queueName
     *//*

    public static void ClearQueue(String url,String queueName)
    {
        ActiveMQConnection con = null;
        try {
            con = (ActiveMQConnection) new ActiveMQConnectionFactory(url).createConnection();
            Destination queue=null;
            queue = con.createSession(false, Session.AUTO_ACKNOWLEDGE).createQueue(queueName);
            con.start();
            con.destroyDestination((ActiveMQDestination) queue);
        } catch (JMSException e)
        {
            e.printStackTrace();
        } finally {
            try
            {
                con.stop();
            } catch(Exception e)
            {
                e.printStackTrace();
            }
        }//end finally
    }//end func

    */
/*
     * Active MQ后台管理页面，用户名密码分别为admin,admin
     * http://127.0.0.1:8161/admin/
     * *//*

    public static void main(String[] args) throws Exception {

        //删除队列
        // ClearQueue(BROKER_URL,"HelloWorld1");

        //发送消息示例

          //  sendMsg("111", "内容1234", 0, "test001");

        //清除消息
        //ClearMessage("723984837693673475","HelloWorld1");
        //创建文本消息
        //createTextMsg("723984837693673475","t2", 10L);
        //createTextMsg("723984837693673473","t2",null);
        //根据内容删除文本消息
          //--//cancelTextMsg("723984837693673473");
*/
/*        SysUser sysUser = new SysUser();
        sysUser.setUsername("123");

        SysUser sysUser2 = new SysUser();
        sysUser2.setUsername("123");
            String t2 = createObjectMsg(sysUser, "t21", 10L);
            String t21 = createObjectMsg(sysUser2, "t22", 10L);

            System.out.println("11  " + t2);
            System.out.println("22  " + t21);*//*


       //cancelObjectMsg("723984837693673475");
    }
    */
/**
     * 创建文本消息
     *
     * @param msg             消息体
     * @param destinationName 队列名称
     * @param delay           延时时间，单位毫秒
     * @throws JMSException
     *//*

    public static void createTextMsg(String msg, String destinationName, Long delay) throws JMSException {
        Connection connection = getConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(destinationName);
        MessageProducer producer = session.createProducer(destination);
        TextMessage message = session.createTextMessage(msg);
        if (!ObjectUtils.isEmpty(delay)) {
            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, delay.longValue());
        }
        producer.send(message);
        closeAll(connection, session);
    }

    */
/**
     * 发送实体消息，返回模拟的消息id
     *
     * @param objMsg
     * @param destinationName
     * @param delay
     * @return
     * @throws JMSException
     *//*

    public static String createObjectMsg(Object objMsg, String destinationName, Long delay) throws JMSException {
        String msgId = UUID.randomUUID().toString().replaceAll("-", "");
        Connection connection = getConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(destinationName);
        MessageProducer producer = session.createProducer(destination);
        ObjectMessage message = session.createObjectMessage((Serializable) objMsg);
        //设置选择器，用来模拟自定义msgId
        message.setStringProperty("selector", msgId);
        if (!ObjectUtils.isEmpty(delay)) {
            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, delay.longValue());
        }
        producer.send(message);
        closeAll(connection, session);
        return msgId;
    }


    */
/**
     * 根据内容删除文本消息
     *
     * @param msg
     * @throws JMSException
     *//*

    public static void cancelTextMsg(String msg) throws JMSException {
        Connection connection = getConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create the Browse Destination and the Reply To location
        Destination requestBrowse = session.createTopic(ScheduledMessage.AMQ_SCHEDULER_MANAGEMENT_DESTINATION);
        Destination browseDest = session.createTemporaryQueue();

        // Create the "Browser"
        MessageConsumer browser = session.createConsumer(browseDest);

        connection.start();

        // Send the browse request
        MessageProducer producer = session.createProducer(requestBrowse);
        TextMessage request = session.createTextMessage();
        request.setStringProperty(ScheduledMessage.AMQ_SCHEDULER_ACTION,
                ScheduledMessage.AMQ_SCHEDULER_ACTION_BROWSE);
        request.setJMSReplyTo(browseDest);
        producer.send(request);

        Message scheduled = browser.receive();
        while (scheduled != null) {
            TextMessage textScheduled = (TextMessage) scheduled;
            if (textScheduled.getText().equals(msg)) {
                Message remove = getMessage(session, scheduled);
                producer.send(remove);
            }
            scheduled = browser.receive();
        }
        closeAll(connection, session);

    }

    */
/**
     * 根据自定义msg消息删除对象消息
     *
     * @param msgId
     * @throws JMSException
     *//*

    public static void cancelObjectMsg(String msgId) throws JMSException {
        Connection connection = getConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create the Browse Destination and the Reply To location
        Destination requestBrowse = session.createTopic(ScheduledMessage.AMQ_SCHEDULER_MANAGEMENT_DESTINATION);
        Destination browseDest = session.createTemporaryQueue();

        // Create the "Browser"
        MessageConsumer browser = session.createConsumer(browseDest);

        connection.start();

        // Send the browse request
        MessageProducer producer = session.createProducer(requestBrowse);
        TextMessage request = session.createTextMessage();
        request.setStringProperty(ScheduledMessage.AMQ_SCHEDULER_ACTION,
                ScheduledMessage.AMQ_SCHEDULER_ACTION_BROWSE);
        request.setJMSReplyTo(browseDest);
        producer.send(request);

        Message scheduled = browser.receive();
        while (scheduled != null) {
//            TextMessage textScheduled = (TextMessage)scheduled;

            ObjectMessage objectMessage = (ObjectMessage) scheduled;
            String selector = objectMessage.getStringProperty("selector");
            if (selector.equals(msgId)) {
                Message remove = getMessage(session, scheduled);
                producer.send(remove);
            }
            scheduled = browser.receive();
        }
        closeAll(connection, session);

    }

    */
/**
     * 获取要删除的消息
     *
     * @param session
     * @param scheduled
     * @return
     * @throws JMSException
     *//*

    private static Message getMessage(Session session, Message scheduled) throws JMSException {
        Message remove = session.createMessage();
        remove.setStringProperty(ScheduledMessage.AMQ_SCHEDULER_ACTION,
                ScheduledMessage.AMQ_SCHEDULER_ACTION_REMOVE);
        String scheduleId = scheduled.getStringProperty(ScheduledMessage.AMQ_SCHEDULED_ID);
        System.out.println(scheduleId);
        remove.setStringProperty(ScheduledMessage.AMQ_SCHEDULED_ID, scheduleId);
        return remove;
    }

    */
/**
     * 获取连接
     *
     * @return
     * @throws JMSException
     *//*

    private static Connection getConnection() throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD, BROKER_URL);
        ((ActiveMQConnectionFactory) factory).setTrustAllPackages(true);
        return factory.createConnection();
    }

    */
/**
     * 关闭资源
     *
     * @param connection
     * @param session
     * @throws JMSException
     *//*

    private static void closeAll(Connection connection, Session session) throws JMSException {
        // 关闭释放资源
        if (session != null) {
            session.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}

*/
