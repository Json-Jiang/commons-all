package com.jjson.common.util.zip;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @author jiangjunshen
 * @date 10:36 上午 2020/4/16
 */
public class ZipUtils {

    /**
     * 使用gzip进行压缩
     */
    public static String gzip(String primStr) {
        if (primStr == null || primStr.length() == 0) {
            return primStr;
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        GZIPOutputStream gzip = null;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(primStr.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (gzip != null) {
                try {
                    gzip.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return new BASE64Encoder().encode(out.toByteArray());
    }

    /**
     * <p>Description:使用gzip进行解压缩</p>
     *
     * @param compressedStr
     * @return
     */
    public static String gunzip(String compressedStr) {
        if (compressedStr == null) {
            return null;
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = null;
        GZIPInputStream ginzip = null;
        byte[] compressed = null;
        String decompressed = null;
        try {
            compressed = new BASE64Decoder().decodeBuffer(compressedStr);
            in = new ByteArrayInputStream(compressed);
            ginzip = new GZIPInputStream(in);

            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = ginzip.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed = out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ginzip != null) {
                try {
                    ginzip.close();
                } catch (IOException e) {
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }

        return decompressed;
    }

    /**
     * 使用zip进行压缩
     *
     * @param str 压缩前的文本
     * @return 返回压缩后的文本
     */
    public static String zip(String str) {
        if (str == null)
            return null;
        byte[] compressed;
        ByteArrayOutputStream out = null;
        ZipOutputStream zout = null;
        String compressedStr = null;
        try {
            out = new ByteArrayOutputStream();
            zout = new ZipOutputStream(out);
            zout.putNextEntry(new ZipEntry("0"));
            zout.write(str.getBytes());
            zout.closeEntry();
            compressed = out.toByteArray();
            compressedStr = new BASE64Encoder().encodeBuffer(compressed);
            
        } catch (IOException e) {
            compressed = null;
        } finally {
            if (zout != null) {
                try {
                    zout.close();
                } catch (IOException e) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }
        return compressedStr;
    }

    /**
     * 使用zip进行解压缩
     *
     * @param compressedStr 压缩后的文本
     * @return 解压后的字符串
     */
    public static String unzip(String compressedStr) {
        if (compressedStr == null) {
            return null;
        }

        ByteArrayOutputStream out = null;
        ByteArrayInputStream in = null;
        ZipInputStream zin = null;
        String decompressed = null;
        try {
            byte[] compressed = new sun.misc.BASE64Decoder().decodeBuffer(compressedStr);
            out = new ByteArrayOutputStream();
            in = new ByteArrayInputStream(compressed);
            zin = new ZipInputStream(in);
            zin.getNextEntry();
            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = zin.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed = out.toString();
        } catch (IOException e) {
            decompressed = null;
        } finally {
            if (zin != null) {
                try {
                    zin.close();
                } catch (IOException e) {
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }
        return decompressed;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(zip("[项目招标文件][projectItemService][cn.gov.zcy.tender.project.service.impl.ProjectItemServiceImpl.detail][finish]return:ProjectItemModel(projectItemId=5c4577f1906a3d19, projectId=5c4577f17e2a3d17, projectNumber=PYCG200326009, projectName=平阳县人民医院智慧病房系统项目, purchaser=平阳县人民医院, itemId=2, itemContent=平阳县人民医院医护看板系统, itemNumber=临[2020]652号-002, itemAmount=42000000, itemBond=0, itemBzj=, unit=套, quantity=不限, specificationDescription=1, priceLimit=null, backlogId=null, statusNo=01, statusNoName=待开标, bidNotifyStatus=0, bidNotifyStatusName=未就绪, bidSendTime=null, bidNum=1.0, bidType=1, biddingMethod=1, biddingMethodName=综合评分法, announceType=1, announceTypeName=后唱标, qualificationMan=2, qualificationManName=采购代理机构, biddingInputType=2, biddingInputTypeName=评审小组录入, selected=0, modifiable=0, isShowQuoteEnd=false, isShow=0, quoteDecimalSet=2, techDecimalSet=2, techComputeFormula=1, techComputeFormulaName=各专家打分总分平均, quoteComputeFormula=1, quoteComputeFormulaName=自动计算报价得分, remark=null, qualificationReviews=null, complianceReviews=null, techBizReviews=null, techBizReviewsList=null, quotationItemBiddingModels=null, expertOpinions=null, compareNote=null, otherRemark=null, advise=null, abolishReason=null, biddingMenuNo=1, expertMenuNo=null, purchasePlanNo=临[2020]652号-002, hitSupplierList=null, unHitSupplierList=null, depositFailBid=false, isCommited=0, isHasUnion=0, ifShowCorrect=1, ifShowPurchase=1, isObjectiveModify=1, isOpenQuote=0, isFileInfo=0, purchaseNoticeId=null, signNum=0, isFailBid=0, ifTechDark=0, isExpertSign=null, isShowExceptionButton=false, quoteSignatureStartTime=null, quoteSignatureEndTime=null, quoteSignatureOriginalFileId=null, quoteSignedFileId=null, isShowBusinessScoreField=0)"));
    }
}
