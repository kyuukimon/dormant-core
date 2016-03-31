package cn.com.dormant.service.core.misc;

import cn.com.dormant.service.core.SysException;
import net.sf.json.JSONObject;
import org.codehaus.jackson.map.ObjectMapper;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <code>CommonUtils<code>
 *
 * @description: This class includes some common util methods and maybe called by lots of place in system
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/03/11
 * @version: 1.0
 */
public class CommonUtils {
    /**
     * Get current system date
     *
     * @return date string with format: YYYYMMDD
     */
    public static String getCurrDate() {
        SimpleDateFormat objStdFormat = new SimpleDateFormat("yyyyMMdd");
        Date CurrDate = new Date(System.currentTimeMillis());
        return objStdFormat.format(CurrDate);
    }

    /**
     * Get date string from given date with <code>Date</code> type
     *
     * @param date
     * @return date string with format: YYYYMMDD
     */
    public static String getDate(Date date) {
        return null;
    }

    /**
     * Get date string from given date with <code>String</code> type
     *
     * @param date "YYYY-MM-DD"
     * @return date string with format: YYYYMMDD
     */
    public static String getDate(String date) {
        if (date == null || date.equals("")) {
            return "";
        }
        if (date.length() == 8) {
            return date;
        }
        if (date.length() != 10) {
            return "";
        }

        return date.substring(0, 4) + date.substring(5, 7)
                + date.substring(8, 10);

    }

    /**
     *
     * @param date YYYY-MM-DD
     * @return
     */
    public static String maskChineseDate(String date) {
        if (date == null || date.equals("")) {
            return "";
        }
        if (date.length() != 10) {
            return "";
        }

        return date.substring(0, 4) +"年" + date.substring(5, 7) + "月"
                + date.substring(8, 10) + "日";

    }

    /**
     * 格式化月份
     *
     * @param date "YYYY-MM"
     * @return "YYYYMM"
     */
    public static String getMonth(String date) {
        if (date == null || date.equals("")) {
            return "";
        }
        if (date.length() == 6) {
            return date;
        }
        if (date.length() != 7) {
            return "";
        }

        return date.substring(0, 4) + date.substring(5, 7);

    }

    //获得某月的天数
    //参数格式：2008-12-26
    public static int getDaysOfMonth(String strDate) throws Exception {
        int days = 0;
        if (strDate.length() != 10) {
            throw new SysException("Error date format");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = new GregorianCalendar();
        Date date1 = sdf.parse(strDate);
        calendar.setTime(date1);
        days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return days;
    }

    /**
     * Get mask date with format "YYYY-MM-DD"
     *
     * @param date "YYMMDD"
     * @return "YYYY-MM-DD"
     */
    public static String maskDate(String date) {
        if (date == null || date.equals("")) {
            return "";
        } else if (date.length() == 10) {
            return date;
        } else if (date.length() != 8) {
            return "";
        } else {
            return date.substring(0, 4) + "-"
                    + date.substring(4, 6) + "-"
                    + date.substring(6, 8);
        }
    }

    /**
     * 格式化月份
     *
     * @param date "YYYYMM"
     * @return "YYYY-MM"
     */
    public static String maskMonth(String date) {
        if (date == null || date.equals("")) {
            return "";
        } else if (date.length() == 7) {
            return date;
        } else if (date.length() != 6) {
            return "";
        } else {
            return date.substring(0, 4) + "-"
                    + date.substring(4, 6);
        }
    }

    // 检查日期格式是否合法
    // 传入的参数 strCurrDate 要坚持的日期格式 2008-01-01 注意是加"-"
    // 返回值：如果合法就返回true 否则就返回false
    public static boolean isValidDate(String date) {
        if (date == null) {
            return false;
        }
        int len = date.length();
        if (len != 10 || len != 8) {
            return false;
        }

        if (len == 8) {
            date = getDate(date);
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (date.trim().length() != dateFormat.toPattern().length())
            return false;

        dateFormat.setLenient(false);

        try {
            dateFormat.parse(date.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }


    /**
     * Get current system time
     *
     * @return time string with format: HHMMSS
     */
    public static String getCurrTime() {
        SimpleDateFormat objStdFormat = new SimpleDateFormat("HHmmss");
        Date CurrDate = new Date(System.currentTimeMillis());
        return objStdFormat.format(CurrDate);
    }

    /**
     * Get time string from given date with <code>Date</code> type
     *
     * @param date
     * @return time string with format: HHMMSS
     */
    public static String getTime(Date date) {
        return null;
    }

    /**
     * Get time string from given time with <code>String</code> type
     *
     * @param time
     * @return time string with format: HHMMSS
     */
    public static String getTime(String time) {
        if (time == null) {
            return "";
        }
        int len = time.length();
        if (len == 6) {
            return time;
        }
        if (len == 8) {
            return time.substring(0, 2) + time.substring(3, 5)
                    + time.substring(6, 8);
        }

        return null;
    }

    /**
     * @param time "HHMMSS"
     * @return
     */
    public static String maskTime(String time) {
        if (time == null) {
            return null;
        } else if (time.length() == 8) {
            return time;
        } else if (time.length() == 6) {
            return time.substring(0, 2) + ":" + time.substring(2, 4)
                    + ":" + time.substring(4, 6);
        }
        return null;
    }

    /**
     * Get current system datetime
     *
     * @return datetime string with format: YYYYMMDD HHMMSS
     */
    public static String getSystemDatetime() {
        SimpleDateFormat objStdFormat = new SimpleDateFormat("yyyyMMdd HHmmss");
        Date CurrDate = new Date(System.currentTimeMillis());
        return objStdFormat.format(CurrDate);
    }

    /**
     * Get datetime string from given datetime with <code>Date</code> type
     *
     * @param date
     * @return datetime string with format: YYYYMMDDHHMMSS
     */
    public static String getDatetime(Date date) {
        return null;
    }

    /**
     * Get datetime string from given datetime with <code>String</code> type
     *
     * @param datetime
     * @return datetime string with format: YYYYMMDDHHMMSS
     */
    public static String getDatetime(String datetime) {
        return null;
    }

    /**
     * Get date after some days from give date
     *
     * @param date Format: YYYYMMDD
     * @param days
     * @return
     */
    public static String getDate(String date, int days) {
        if (days == 0 || date == null || date.equals("")) {
            return date;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            Date dCurrDate = dateFormat.parse(date);
            Long lDate = dCurrDate.getTime() + Long.valueOf(days) * 24 * 60 * 60 * 1000;
            Date dCurrDate2 = new Date(lDate);
            return dateFormat.format(dCurrDate2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get time after <code>minutes</code> minutes from current time
     *
     * @param currTime : Format: HHMMSS
     * @param minutes  : Unit: minute
     * @return
     */
    public static String getTime(String currTime, int minutes) {
        if (minutes == 0 || currTime == null || currTime.equals("")) {
            return currTime;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("HHmmss");
        try {
            Date dCurrDate = dateFormat.parse(currTime);
            Long lDate = dCurrDate.getTime() + Long.valueOf(minutes) * 60 * 1000;
            Date dCurrDate2 = new Date(lDate);
            return dateFormat.format(dCurrDate2);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static Date addDateSomeDays(Date date, int days) {
        if (date == null) {
            return date;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        date = c.getTime();
        return date;
    }

    public static String addDateSomeDays(String tDate, int days) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyyMMdd").parse(tDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + days);

        String dayAfter = new SimpleDateFormat("yyyyMMdd").format(c.getTime());
        return dayAfter;
    }

    /**
     * 获取当前月的第一天
     *
     * @return Formant:YYYYMMDD
     */
    public static String getFirstDay() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        Date theDate = calendar.getTime();

        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(gcLast.getTime());
        return day_first;
    }

    /**
     * 获取当前月的最后一天
     *
     * @return Formant:YYYYMMDD
     */
    public static String getLastDay() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.DAY_OF_MONTH, cale.getActualMaximum(Calendar.DAY_OF_MONTH));
        String lastDay = df.format(cale.getTime());
        return lastDay;
    }

    /**
     * 获取某月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String lastDayOfMonth = sdf.format(cal.getTime());

        return lastDayOfMonth;
    }

    /**
     * 获取某月的第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static String getFirstDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String firstDayOfMonth = sdf.format(cal.getTime());
        return firstDayOfMonth;
    }

    /**
     * 获取两个时间内的工作日天数
     *
     * @param strStartDate
     * @param strEndDate
     * @return
     */
    public static int getDutyDays(String strStartDate, String strEndDate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        Date startDate = null;
        Date endDate = null;

        try {
            startDate = df.parse(strStartDate);
            endDate = df.parse(strEndDate);
        } catch (ParseException e) {
            System.out.println("非法的日期格式,无法进行转换");
            e.printStackTrace();
        }
        int result = 0;
        while (startDate.compareTo(endDate) <= 0) {
            if (startDate.getDay() != 6 && startDate.getDay() != 0)
                result++;
            startDate.setDate(startDate.getDate() + 1);
        }

        return result;
    }


    /**
     * 日期推算
     *
     * @param currDate 当前日期：“YYYYMMDD”
     * @param period
     * @param unit     参见Calendar相关产量，如Calendar.MONTH 等
     * @return
     */
    public static String reckonDate(String currDate, int period, int unit) {
        if (!(currDate != null && currDate.length() == 8)) {
            return null;
        }

        Date dt = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            dt = sdf.parse(currDate);
        } catch (ParseException e) {
            return null;
        }

        Calendar now = Calendar.getInstance();
        now.setTime(dt);

        now.add(unit, period);
        return sdf.format(now.getTime());
    }

    /**
     * Get diff days
     *
     * @param fromDate
     * @param toDate
     * @return
     */
    public static int getDiffDays(String fromDate, String toDate) {
        int diffDays = 0;
        SimpleDateFormat objStdFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            Date dFromDate = objStdFormat.parse(fromDate);
            Date dToDate = objStdFormat.parse(toDate);
            //确保startday在endday之前
            if (dFromDate.after(dToDate)) {
                Date cal = dFromDate;
                dFromDate = dToDate;
                dToDate = cal;
            }
            //分别得到两个时间的毫秒数
            Long sl = dFromDate.getTime();
            Long el = dToDate.getTime();
            Long ei = el - sl;
            //根据毫秒数计算间隔天数
            diffDays = (int) (ei / (1000 * 60 * 60 * 24));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return diffDays;
    }

    public static float getDiffDays1(String fromDate, String toDate) {
        float diffDays = 0;
        SimpleDateFormat objStdFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            Date dFromDate = objStdFormat.parse(fromDate);
            Date dToDate = objStdFormat.parse(toDate);
            //确保startday在endday之前
            if (dFromDate.after(dToDate)) {
                Date cal = dFromDate;
                dFromDate = dToDate;
                dToDate = cal;
            }
            //分别得到两个时间的毫秒数
            Long sl = dFromDate.getTime();
            Long el = dToDate.getTime();
            Long ei = el - sl;
            //根据毫秒数计算间隔天数
            diffDays = (float) (ei / (1000 * 60 * 60 * 24));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return diffDays;
    }

    public static int getSeqNo(List ls) {
        if (ls == null || ls.size() == 0) {
            return 1;
        } else {
            return ls.size() + 1;
        }
    }

    // 格式化金额
    // 参数 Double dValue 要格式化的金额
    // int iCal 格式化的小数位数
    // 返回值：如果dValue 是null 那么就返回 0 否则就返回他的格式化金额
    public static Double formatAmt(Double dValue, int iCal) {
        if (dValue == null) {
            dValue = 0d;
        }
        BigDecimal b = new BigDecimal(dValue);
        b = b.setScale(iCal, BigDecimal.ROUND_HALF_UP);
        return b.doubleValue();
    }

    public static Float formatAmt(Float value, int iCal) {
        value = value == null ? 0 : value;
        BigDecimal b = new BigDecimal(value);
        b = b.setScale(iCal, BigDecimal.ROUND_HALF_UP);
        return b.floatValue();
    }

    public static String formatAmtSmartly(Double dValue, int iCal) {
        Double calResult = formatAmt(dValue, iCal);
        String calStr = String.valueOf(calResult);
        if (calStr.indexOf(".") == -1) {
            StringBuilder calSb = new StringBuilder(calStr).append(".");
            for (int i = 0; i < iCal; i++) {
                calSb.append("0");
            }
            return calSb.toString();
        }
        String scaleStr = calStr.substring(calStr.indexOf(".") + 1);
        if (scaleStr.length() < iCal) {
            StringBuilder calSb = new StringBuilder(calStr);
            for (int i = 0; i < iCal - scaleStr.length(); i++) {
                calSb.append("0");
            }
            return calSb.toString();
        }
        return calStr;
    }

    public static String formatAmtSmartly(Float dValue, int iCal) {
        Float calResult = formatAmt(dValue, iCal);
        String calStr = String.valueOf(calResult);
        if (calStr.indexOf(".") == -1) {
            StringBuilder calSb = new StringBuilder(calStr).append(".");
            for (int i = 0; i < iCal; i++) {
                calSb.append("0");
            }
            return calSb.toString();
        }
        String scaleStr = calStr.substring(calStr.indexOf(".") + 1);
        if (scaleStr.length() < iCal) {
            StringBuilder calSb = new StringBuilder(calStr);
            for (int i = 0; i < iCal - scaleStr.length(); i++) {
                calSb.append("0");
            }
            return calSb.toString();
        }
        return calStr;
    }

//    /**
//     * 格式化金额，四舍五入保留两位小数
//     * @return
//     */
//    public static Float formatMoney(Float value) {
//        DecimalFormat df = new DecimalFormat("#.00");
//        return Float.parseFloat(df.format(value));
//    }
//
//    /**
//     * 根据制定位数四舍五入
//     * @param value
//     * @param digits
//     */
//    public static Float formatAmount(Float value,int digits) {
//        NumberFormat nf = NumberFormat.getNumberInstance();
//        nf.setMaximumFractionDigits(digits);
//        return Float.parseFloat(nf.format(value));
//    }

    /**
     * 比较两个浮点数是否相等()
     *
     * @param val1
     * @param val2
     * @param decimals 小数位数
     */
    public static boolean equals(Float val1, Float val2, int decimals) {
        val1 = formatAmt(val1, decimals);
        val2 = formatAmt(val2, decimals);
        if (val1.floatValue() == val2.floatValue()) {
            return true;
        }
        return false;
    }

    public static boolean equals(Double val1, Double val2, int decimals) {
        val1 = formatAmt(val1, decimals);
        val2 = formatAmt(val2, decimals);
        if (val1.doubleValue() == val2.doubleValue()) {
            return true;
        }
        return false;
    }

    public static String quotes(String strValue) {
        return "'" + strValue + "'";
    }

    /**
     * @param strDate "YYYYMMDD"
     * @return
     */
    public static Integer getWeekDay(String strDate) {
        Integer nWeekDay = -1;
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat dataFromat = new SimpleDateFormat("yyyyMMdd");
        try {
            Date CurrDate = dataFromat.parse(strDate);
            gc.setTime(CurrDate);
            nWeekDay = gc.get(Calendar.DAY_OF_WEEK);

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return nWeekDay;
    }

    public static String str(Object o) {
        return o == null ? "" : (String) o;
    }

    public static Integer integer(Object o) {
        return o == null ? null : (Integer) o;
    }

    public static Long longg(Object o) {
        return o == null ? null : (Long) o;
    }

    public static Integer integer(String str) {
        if (str == null || str.trim().isEmpty()) {
            return null;
        }
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("0|[-]?[1-9][0-9]*");
        java.util.regex.Matcher match = pattern.matcher(str);
        if (match.matches()) {
            return Integer.valueOf(str);
        }
        return null;
    }

    public static Long longg(String str) {
        if (str == null || str.trim().isEmpty()) {
            return null;
        }
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("0|[-]?[1-9][0-9]*");
        java.util.regex.Matcher match = pattern.matcher(str);
        if (match.matches()) {
            return Long.valueOf(str);
        }
        return null;
    }

    public static String base64(String s) {
        if (s == null) {
            return null;
        }

        BASE64Encoder decoder = new BASE64Encoder();
        try {
            String n = decoder.encodeBuffer(s.getBytes());
            return n.replace("\r\n", "");
        } catch (Exception e) {
            return null;
        }
    }

    public static String debase64(String s) {
        if (s == null) {
            return null;
        }

        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] n = decoder.decodeBuffer(s);
            return new String(n);
        } catch (Exception e) {
            return null;
        }
    }

    /*
     * Method : buildSearchCondition
     * Author : WJG
     * Param  :
     * 		fields -- field list
     * 		values -- value list related to field
     * 		searchType -- search type: true-search accurately, false-search darkly
     */
    public static String buildSearchCondition(String[] fields, String[] values, boolean bAccurate) {
        String strRet = "";
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i] == null || values[i] == null)
                continue;
            if (fields[i].equalsIgnoreCase("") || values[i].equalsIgnoreCase(""))
                continue;

            String filter = "";
            if (bAccurate)
                filter = fields[i] + "=" + quotes(values[i]);
            else
                filter = fields[i] + " like " + quotes("%" + values[i] + "%");

            if (!buf.toString().equalsIgnoreCase("")) {
                filter = " and " + filter;
                buf.append(filter);
            } else {
                buf.append(filter);
            }
        }
        strRet = buf.toString();
        return strRet;
    }

    public static String genExceptionLogMsg(String exceptFrom, String methodName, String cause) {
        String strErrorMsg = "";
        strErrorMsg = "BKEXCEPTION:TYPE(" + exceptFrom + ")&&METHOD(" + methodName + ")&&CAUSE BY:\n" + cause;
        return strErrorMsg;
    }

    public static String genExceptionLogMsg(String exceptFrom, String methodName, String cause, String strSql) {
        String strErrorMsg = "";
        strErrorMsg = "BKEXCEPTION:TYPE(" + exceptFrom + ")&&METHOD(" + methodName + ")&&SQL(" + strSql + ")&&CAUSE BY:\n" + cause;
        return strErrorMsg;
    }

    public static String genExceptionLogMsg(String exceptFrom, String methodName, String cause,
                                            String compId, String billId, String date, String time, String cardId) {
        String strErrorMsg = "";
        String data = "";
        data = str(compId) + "$" + str(billId)
                + "$" + str(date) + "$" + str(time) + "$" + str(cardId);
        strErrorMsg = "BKEXCEPTION:TYPE(" + exceptFrom + ")&&METHOD(" + methodName + ")&&DATA(" + data + ")&&CAUSE BY:\n" + cause;
        return strErrorMsg;
    }

    /**
     * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和public static byte[]
     * hexStr2ByteArr(String strIn) 互为可逆的转换过程
     *
     * @param arrB 需要转换的byte数组
     * @return 转换后的字符串
     * @throws Exception 本方法不处理任何异常，所有异常全部抛出
     */
    public static String byteArr2HexStr(byte[] arrB) throws Exception {
        int iLen = arrB.length;
        // 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
            // 把负数转换为正数
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            // 小于0F的数需要在前面补0
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }

    /**
     * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB)
     * 互为可逆的转换过程
     *
     * @param strIn 需要转换的字符串
     * @return 转换后的byte数组
     * @throws Exception 本方法不处理任何异常，所有异常全部抛出
     */
    public static byte[] hexStr2ByteArr(String strIn) throws Exception {
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;

        // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }

    public static String encrypt(String input) throws Exception {

        SecureRandom sr = new SecureRandom();
        byte rawKeyData[] = "RONDUGAVIN".getBytes();
        DESKeySpec dks = new DESKeySpec(rawKeyData);

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(dks);

        Cipher c = Cipher.getInstance("DES");
        c.init(Cipher.ENCRYPT_MODE, key, sr);
        byte[] cipherByte = c.doFinal(input.getBytes());
        String dec = byteArr2HexStr(cipherByte);
        return dec;

    }

    /**
     * @param input
     * @return
     * @throws Exception
     */
    public static String decrypt(String input) throws Exception {
        if (isEmpty(input)) {
            return "";
        }
        byte[] dec = hexStr2ByteArr(input);

        SecureRandom sr = new SecureRandom();
        byte rawKeyData[] = "RONDUGAVIN".getBytes();

        DESKeySpec dks = new DESKeySpec(rawKeyData);

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");

        SecretKey key = keyFactory.generateSecret(dks);

        Cipher c = Cipher.getInstance("DES");
        c.init(Cipher.DECRYPT_MODE, key, sr);
        byte[] clearByte = c.doFinal(dec);

        return new String(clearByte);
    }

    public static String encrypt(String sourceKey, String input) throws Exception {

        SecureRandom sr = new SecureRandom();
        byte rawKeyData[] = sourceKey.getBytes();
        DESKeySpec dks = new DESKeySpec(rawKeyData);

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(dks);

        Cipher c = Cipher.getInstance("DES");
        c.init(Cipher.ENCRYPT_MODE, key, sr);
        byte[] cipherByte = c.doFinal(input.getBytes());
        String dec = byteArr2HexStr(cipherByte);
        return dec;

    }

    /**
     * @param input
     * @return
     * @throws Exception
     */
    public static String decrypt(String sourceKey, String input) throws Exception {
        if (isEmpty(input)) {
            return "";
        }
        byte[] dec = hexStr2ByteArr(input);

        SecureRandom sr = new SecureRandom();
        byte rawKeyData[] = sourceKey.getBytes();

        DESKeySpec dks = new DESKeySpec(rawKeyData);

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");

        SecretKey key = keyFactory.generateSecret(dks);

        Cipher c = Cipher.getInstance("DES");
        c.init(Cipher.DECRYPT_MODE, key, sr);
        byte[] clearByte = c.doFinal(dec);

        return new String(clearByte);
    }

    /**
     * 获取当前操作系统名称.
     * return 操作系统名称 例如:windows xp,linux 等.
     */
    public static String getOSName() {
        return System.getProperty("os.name").toLowerCase();
    }

    /**
     * 获取unix网卡的mac地址.
     * 非windows的系统默认调用本方法获取.如果有特殊系统请继续扩充新的取mac地址方法.
     *
     * @return mac地址
     */
    public static String getUnixMACAddress() {
        String mac = null;
        BufferedReader bufferedReader = null;
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("ifconfig eth0");// linux下的命令，一般取eth0作为本地主网卡 显示信息中包含有mac地址信息
            bufferedReader = new BufferedReader(new InputStreamReader(process
                    .getInputStream()));
            String line = null;
            int index = -1;
            while ((line = bufferedReader.readLine()) != null) {
                index = line.toLowerCase().indexOf("hwaddr");// 寻找标示字符串[hwaddr]
                if (index >= 0) {// 找到了
                    mac = line.substring(index + "hwaddr".length() + 1).trim();//  取出mac地址并去除2边空格
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            bufferedReader = null;
            process = null;
        }

        return mac;
    }

    /**
     * 获取widnows网卡的mac地址.
     *
     * @return mac地址
     */
    public static String getWindowsMACAddress() {
        String mac = null;
        BufferedReader bufferedReader = null;
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("ipconfig /all");// windows下的命令，显示信息中包含有mac地址信息
            bufferedReader = new BufferedReader(new InputStreamReader(process
                    .getInputStream()));
            String line = null;
            int index = -1;
            while ((line = bufferedReader.readLine()) != null) {
                index = line.toLowerCase().indexOf("physical address");// 寻找标示字符串[physical address]
                if (index >= 0) {// 找到了
                    index = line.indexOf(":");// 寻找":"的位置
                    if (index >= 0) {
                        mac = line.substring(index + 1).trim();//  取出mac地址并去除2边空格
                    }
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            bufferedReader = null;
            process = null;
        }

        return mac;
    }

    public static String getMACAddressEx() {

        String address = "";
        String os = getOSName();
        if (os.startsWith("windows")) {
            address = getWindowsMACAddress();
        } else {
            address = getUnixMACAddress();
        }

        if (address == null || address.equalsIgnoreCase("")) {
            address = "FF-FF-FF-FF-FF-FF";
        }
        //address = getMetaMachineCodeEX();
        return address;
    }

    public static boolean isEmpty(String str) {
        if (str == null || str.trim().isEmpty()) {
            return true;
        }

        return false;
    }

    public static boolean isEmpty(Integer i) {
        if (i == null) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(Collection c) {
        return c == null || c.isEmpty();
    }

    public static boolean isEmpty(Map m) {
        return m == null || m.isEmpty();
    }

    public static int size(Collection c) {
        return c != null ? c.size() : 0;
    }

    public static int size(Map m) {
        return m != null ? m.size() : 0;
    }

    private static int computeListCapacity(int arraySize) {
        return (int) Math.min(5L + arraySize + (arraySize / 10), Integer.MAX_VALUE);
    }

    public static <E> List<E> asList(E... elements) {
        if (elements == null || elements.length == 0) {
            return Collections.emptyList();
        }
        // Avoid integer overflow when a large array is passed in
        int capacity = computeListCapacity(elements.length);
        ArrayList<E> list = new ArrayList<E>(capacity);
        Collections.addAll(list, elements);
        return list;
    }

    public static String[] subArray(String[] arr, int begin, int end) {
        if (begin > end) {
            return arr;
        }
        List<String> list = new ArrayList<String>(end - begin);
        for (; begin <= end; begin++) {
            list.add(arr[begin]);
        }
        return list.toArray(new String[list.size()]);
    }

    public static <E> boolean isEmpty(E... elements) {
        return elements == null || elements.length == 0 || elements[0] == null;
    }

    public static String[] objArrayToStrArray(Object[] objArray) {
        if (objArray == null || objArray.length == 0) {
            return null;
        }
        String[] strArray = new String[objArray.length];
        for (int i = 0; i < objArray.length; i++) {
            strArray[i] = (String) objArray[i];
        }
        return strArray;
    }

    public static <T> T jsonToObject(String json, Class<T> pojoClass) {
        if (isEmpty(json)) {
            return null;
        }
        try {
            JSONObject jsonObject = JSONObject.fromObject(json);
            return (T) jsonObject.toBean(jsonObject, pojoClass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String nullVal(String v) {
        return v == null ? "" : v;
    }

    public static Float nullVal(Float v) {
        return v == null ? 0 : v;
    }

    public static Integer nullVal(Integer v) {
        return v == null ? 0 : v;
    }

    public static Double nullVal(Double v) {
        return v == null ? 0 : v;
    }

    public static String toJson(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static boolean equalZero(Float val) {
        return val.compareTo(0f) == 0;
    }

    public static boolean equalZero(Double val) {
        return val.compareTo(0d) == 0;
    }

    // 判断中文
    public static boolean isChineseChar(String str) {
        boolean temp = false;
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            temp = true;
        }
        return temp;
    }

    public static boolean isMobile(String str) {
        boolean b = false;
        Pattern p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号
        Matcher m = p.matcher(str);
        b = m.matches();
        return b;
    }

    public static <T> boolean hasElement(T[] container, T ele) {
        if (CommonUtils.isEmpty(container) || ele == null) {
            return false;
        }
        for (T t : container) {
            if (ele.equals(t)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception {

        System.out.println(decrypt("f27f085f1e1f570847add8d72d31fc30"));

        String password = "admin";
        try {
            System.out.println(encrypt("admin"));//e7ee6d7e6682f5b1
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("tomorow:"+CommonUtils.addDateSomeDays("20151018", 1));
        String month = "201509";
        int tYear = Integer.parseInt(month.substring(0,4));
        int tMonth = Integer.parseInt(month.substring(4,6));
        String firstDay = CommonUtils.getFirstDayOfMonth(tYear,tMonth);
        String lastDay = CommonUtils.getLastDayOfMonth(tYear,tMonth);
        System.out.println(firstDay+":"+lastDay);
    }
}
