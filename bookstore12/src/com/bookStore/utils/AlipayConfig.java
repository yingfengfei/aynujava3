package com.bookStore.utils;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016091100484793";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCMJOp07s9OC8W0mAH6oI2TT+lq9RNa0I9WX1OPB6HruNhDnulUW+j4EHMU5ofkOf9ITwwXP9uUHnVMuCnWgoj8D0vEVuj8QVNpma1G8HG7qlY3H/UlRjMTpfMXMKBf7fC21cidLC3Ay5lSbx/o5rdA8FBoD+WWQhJqSPiKwPPCRPj/EwBNJQipyR23PPxK7z6Bn3r1nXQXhnM5EEyP/rtrEEDJMyss/27SWTPOws349yYW8CFz3St7nxJYfj3WAEZCkO1r652BADD1844tGzCAJZG9gRVSFq9O1+GgvAayqyl5LrW8MDXIYYKRbUl66v4NYFZj+7v8gNvJL5kAEfmvAgMBAAECggEAZaRwl5aMrCJFphGJfejsRadzt6s/+mHBU/eXdukmOvy5fXHH8PMoKKJllQcOEC0wRZVvQ32BGBBvKtF3JqlOCOJfJWuTV0FZ6gEXd5f0JRQJq7l15y7e5KU7hdoytuCv8StkcPoVxPrlnLAUCKssxeCFsLO25B/Pk16RRLFlGcQmqHoj3HXWyDCAYVMmb7kqUsCtoTQ3XjxOuax5YXdIPl37aMnciDMGMAvm3luc39X2XATMOeMLShkkmoyHtT26X/pP5SEwjkJ8CdROZnhYgj2e94pm+cXXO+4V6bJpq4lXYfKfNKXmQDfuw2nt4Oj7DmfWgyFY8Sv9I3T/3CBdIQKBgQD7c5Rxslk+Z40A03IQP6lvkyIQ720A8SHohMjlhO4KmAwj6XXf6x2telvLy/WCpNQ5CQ3eKv+fQDOpd5ma/q41iAwh73lgonboiFQaZFbkba+zx5IOF8Y1zTonRjLE1ods4cu7YrPlLqQ+1Od/wdqolBTTfskSDAYq9BSaoHiAXQKBgQCOreUUhp9OnD82R5WJ8Qj1c5yg4pFvXd5i0RYz2H/S5aOcREEGTrPa4Kve1QJGCC5EfPdPNIhLVliyCp3VF56s+TpR8P12rFLlIhGAb9InsWVhPWK+590KLp+4t7Af+7S6vhFYBivgtuAxIF9Y/h3R+e+7R6bj1ZwSD4qHBFyxewKBgQDiCV866alB6NIfGPSjWKcWbnwkHyasvxbNL3hsopddnJrlOXvF41QVWzXjuKAlZciE0PfPbHXHkW4ffCzv4Nt3sam+Y8iHruQXWl3IL8TQqS1V/iP0WTjLqoYJQhhnS7YHkd6Tv0GZ1ti8xqSn+vp1qlAiYwmbGIX+3NUcvv4z3QKBgBqjhP3MFLpMmdC8lMLoac/KAhhXtOeiEv9IImkTpuntThrC85EF6LaY8/3qrz38EQdgfZwgJsJKIM09BjLe8hVBgw4BfJFHo0J8ld/xAL/AczkwiNsz1yUrh06pnQybpianjf1tSYvvGPSTIrou18p4a7X/rr0jCLymgiqrOdM5AoGAB5wBhut+tq/P4WpLkabo77uZhppUP7vcgshpxFBWBgQ2wnln3xw64N1wABKgmLvpqQeMboglwuNSsZwb7X70pNks36z8ZXQBHZ0Uza//WXAQMB8TrHWdEimUFN4GCELltVJJZq8liKO/En77HteqnMfdKb8DxJuDb/55TJyMl6Q=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzQLmfXW07dpi0UuVfojPeEUA21L16mUQOF1yfg1dG+MY/9zH9+jIQmqBA8OBsovSW9aKCBBP1dTIwuMVHQGDssjneZj1Jw2OdU1CaJ2k+JgG1f7AH4/aZBBuit60WcikIHkq4cnfSke/AvjtObsn0fZgltODngnL7ivKE8G7fuEnEOauQKifYDXEWzFO0fllH65m2NpVXkdN7ReUQYjtY7wP5UQGtxGYTDvOUD3j6prcWkzg2HaPui/OSUhYYVaDX47JbOXU2OAgWNT/FE5ar2I0c/Pe0uBc/QXO5HWK259hTFhdAKHqyIDg9NfFy/RAFdo5uVUfY67w+lljihL0gQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8888/bookstore8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8888/bookstore8/client/cart/paysuccess.do";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

