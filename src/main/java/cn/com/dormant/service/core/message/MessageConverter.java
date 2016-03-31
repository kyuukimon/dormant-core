/*****************************************************************************
 * HISTORY
 *
 * 2014/12/17   Wang Jianguang --Create file and initial implementation
 *
 ****************************************************************************/
package cn.com.dormant.service.core.message;

import cn.com.dormant.service.core.message.base.TopicMessage;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * <code>MessageConverter<code>
 *
 * @description: Message converter between object and bytes, or json and bytes, or xml and bytes
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2014/12/12
 * @version: 1.0
 */
public class MessageConverter {
    public static Object Bytes2Object(byte[] bytes) throws MessageException {
        Object obj = null;
        try {
            ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
            ObjectInputStream oi = new ObjectInputStream(bi);

            obj = oi.readObject();
            bi.close();
            oi.close();
        } catch (Exception e) {
            throw new MessageException("Exception occurred on converting bytes to object",e);
        }
        return obj;
    }

    public static byte[] Object2Bytes(Object obj) throws MessageException {
        byte[] bytes = null;
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);

            bytes = bo.toByteArray();

            bo.close();
            oo.close();
        } catch (Exception e) {
            throw new MessageException("Exception occurred on converting object to bytes",e);
        }
        return bytes;
    }

    public static String Object2Json(Object obj) {
        String ret = null;
        if(obj == null) {
            return ret;
        }

        JSONObject jsonObj = JSONObject.fromObject(obj);
        ret = jsonObj.toString();

        return ret;
    }

    public static Object Json2Object(String jsonStr) {
        JSONObject obj = JSONObject.fromObject(jsonStr);
        return JSONObject.toBean(obj, TopicMessage.class);
    }

    public static String Object2Xml(Object obj) {
        JSONObject jsonObject = JSONObject.fromObject(Object2Json(obj));
        XMLSerializer serializer = new XMLSerializer();
        String xml = serializer.write(jsonObject);
        return xml;
    }

    public static void main(String[] args) {
        Message msg = new Message();

        //todo

        System.out.println(MessageConverter.Object2Xml(msg));

    }
}
