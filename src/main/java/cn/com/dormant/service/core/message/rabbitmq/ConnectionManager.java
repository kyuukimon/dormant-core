/*****************************************************************************
 * HISTORY
 *
 * 2014/12/17   Wang Jianguang --Create file and initial implementation
 *
 ****************************************************************************/
package cn.com.dormant.service.core.message.rabbitmq;

import cn.com.dormant.service.core.message.MessageConfig;
import cn.com.dormant.service.core.message.base.BaseMessageCenter;
import cn.com.dormant.service.core.message.MessageException;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <code>ConnectionManager<code>
 *
 * @description: Whatever publish message or receiving message, all publisher and listener need to build connection
 * to rabbitmq server. Building connection is one time consuming operation and impact program's performance. So we want
 * to reuse connection to improve some performance and manage these connection.
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2014/12/12
 * @version: 1.0
 */
public class ConnectionManager {
    private List<MsgConnection> pool = new ArrayList<MsgConnection>();

    private int maxPoolSize  = 100;

    private final static ConnectionManager connectionManager = new ConnectionManager();

    private ConnectionManager() {

    }

    public synchronized static ConnectionManager getInstance() {
        return connectionManager;
    }

    public MsgConnection getConnection() throws MessageException {
        MsgConnection connection = null;
        boolean found = false;
        for(int i = pool.size()-1; i >= 0; i--) {
            MsgConnection msgConn = pool.get(i);
            if(msgConn != null && !msgConn.inUsed) {
                connection = msgConn;
                found = true;
                break;
            }
        }

        if(!found &&  pool.size() < maxPoolSize) {
            connection = new MsgConnection();
            connection.open();
            pool.add(connection);
        }
        return connection;
    }

    public void destroy() throws MessageException{
        for(int i = pool.size()-1; i >= 0; i--) {
            MsgConnection msgConn = pool.get(i);
            if(msgConn != null) {
                msgConn.disuse();
                msgConn.close();
            }
            pool.remove(i);
        }
    }

    class MsgConnection {
        private Connection connection = null;

        private boolean inUsed = false;

        private MsgConnection() {
        }

        public Connection get() {
            return connection;
        }

        private void open() throws MessageException{
            ConnectionFactory factory = getConnectionFactory();
            try {
                this.connection = factory.newConnection();
            }catch (IOException e) {
                this.connection = null;
                throw new MessageException("Cannot connect AMQP server", e);
            }
        }

        private ConnectionFactory getConnectionFactory() {
            ConnectionFactory factory = new ConnectionFactory();
            MessageConfig config = BaseMessageCenter.getInstance().getConfig();
            factory.setHost(config.getHost());
            factory.setPort(config.getPort());
            factory.setUsername(config.getUserName());
            factory.setPassword(config.getPassword());
            factory.setVirtualHost(config.getVirtualHost());

            return factory;
        }

        public void use() {
            inUsed = true;
        }

        public void disuse() {
            inUsed = false;
        }

        public boolean inUsed() {
            return inUsed;
        }

        private void close() throws MessageException{
            if(this.connection != null) {
                try {
                    this.connection.close();
                }catch (IOException e) {
                    throw new MessageException("Fail to close connection", e);
                }
            }
        }
    }
}
