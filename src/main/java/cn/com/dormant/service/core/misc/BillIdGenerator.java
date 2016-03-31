package cn.com.dormant.service.core.misc;

import cn.com.dormant.service.core.SysException;

/**
 * <code>BillIdGenerator<code>
 *
 * @description:
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/6/8
 * @version: 1.0
 */
public class BillIdGenerator {
    private int prefixLen = 2;
    
    private int suffixLen = 0;
    
    private int seqLen = 4;

    private int billLen = 2+6+4+0; //prefix(2）+ date(6) + seq(4) + suffix(0)
    
    private String prefix = "";

    private String suffix = "";
    
    public BillIdGenerator() {
    }

    public BillIdGenerator(int prefixLen, int suffixLen, int seqLen) {
        this.prefixLen = prefixLen;
        this.suffixLen = suffixLen;
        this.seqLen = seqLen;
        this.billLen = prefixLen + 6 + seqLen + suffixLen;
    }

    public BillIdGenerator(int prefixLen, int suffixLen) {
        this.prefixLen = prefixLen;
        this.suffixLen = suffixLen;
        this.billLen = prefixLen + 6 + seqLen + suffixLen;
    }

    public BillIdGenerator(int prefixLen) {
        this.prefixLen = prefixLen;
        this.billLen = prefixLen + 6 + seqLen + suffixLen;
    }

    public BillIdGenerator(int prefixLen, int suffixLen, int seqLen, String prefix, String suffix) {
        this.prefixLen = prefixLen;
        this.suffixLen = suffixLen;
        this.seqLen = seqLen;
        this.prefix = prefix;
        this.suffix = suffix;
        this.billLen = prefixLen + 6 + seqLen + suffixLen;
    }

    @Deprecated
    public static String generate(String lastBillId) {
        String billId = null;
        Integer seqLen = 4;
        String currDate = CommonUtils.getCurrDate();
        if(CommonUtils.isEmpty(lastBillId)) {
            billId = currDate.substring(2) + "0001";
        } else {
            String datePart = lastBillId.substring(0,6);
            String seqPart = lastBillId.substring(6);
            Integer number = Integer.valueOf(seqPart);
            seqPart = String.valueOf(++number);
            Integer lenDiff = seqLen - seqPart.length();
            if(lenDiff < 0) {
                throw new SysException("Bill ID overflow");
            }

            for(int i = 0; i <  lenDiff; i++) {
                seqPart = "0" + seqPart;
            }
            billId = datePart + seqPart;
        }

        return billId;
    }

    public String generateSeed() {
        if(CommonUtils.nullVal(prefix).length() != this.prefixLen) {
            throw new SysException("The prefix length of bill ID must be " + this.prefixLen);
        }

        if(CommonUtils.nullVal(suffix).length() != this.suffixLen) {
            throw new SysException("The suffix length of bill ID must be " + this.suffixLen);
        }

        StringBuffer billId = new StringBuffer("");
        StringBuffer seqPart = new StringBuffer("");

        for(int i = 1; i <  this.seqLen; i++) {
            seqPart.append("0");
        }
        seqPart.append("1");
        
        String currDate = CommonUtils.getCurrDate();
        billId.append(CommonUtils.nullVal(prefix).toUpperCase())
                .append(currDate.substring(2))
                .append(seqPart)
                .append(CommonUtils.nullVal(suffix).toUpperCase()) ;

        return billId.toString();
    }

    public String generateEx(String lastBillId) {
        if(CommonUtils.isEmpty(lastBillId)) {
            return this.generateSeed();
        }

        int lastBillLen = CommonUtils.nullVal(lastBillId).length();
        if(lastBillLen != this.billLen) {
            return this.generateSeed();
        }

        String billId = null;
        String prefixPart = lastBillId.substring(0,this.prefixLen);
        String datePart = lastBillId.substring(this.prefixLen, this.prefixLen + 6);
        int prefixDatePartLen = this.prefixLen + 6;
//        String prefixDatePart = lastBillId.substring(0,prefixDatePartLen);
        String seqPart = lastBillId.substring(prefixDatePartLen, prefixDatePartLen + this.seqLen);
        String suffixPart = lastBillId.substring(this.billLen - this.suffixLen);
        Integer number = Integer.valueOf(seqPart);
        seqPart = String.valueOf(++number);
        Integer lenDiff = seqLen - seqPart.length();
        if(lenDiff < 0) {
            throw new SysException("Bill ID overflow");
        }

        for(int i = 0; i <  lenDiff; i++) {
            seqPart = "0" + seqPart;
        }
        if(!CommonUtils.isEmpty(this.prefix)) {
            prefixPart = this.prefix;
        }
        if(!CommonUtils.isEmpty(this.suffix)) {
            suffixPart = this.suffix;
        }
        billId = prefixPart + datePart + seqPart + suffixPart;

        return billId;
    }


    public int getPrefixLen() {
        return prefixLen;
    }

    public void setPrefixLen(int prefixLen) {
        this.prefixLen = prefixLen;
    }

    public int getSuffixLen() {
        return suffixLen;
    }

    public void setSuffixLen(int suffixLen) {
        this.suffixLen = suffixLen;
    }

    public int getSeqLen() {
        return seqLen;
    }

    public void setSeqLen(int seqLen) {
        this.seqLen = seqLen;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public static void main(String[] args) {
        BillIdGenerator generator = new BillIdGenerator();
        generator.setPrefix("bb");
        System.out.println(generator.generateEx("BB1602220001"));
    }
}
