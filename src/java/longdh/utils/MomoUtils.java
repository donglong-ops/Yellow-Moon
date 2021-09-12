/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longdh.utils;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;



/**
 *
 * @author Dong Long
 */
public class MomoUtils {

    public static final String partnerCode = "MOMOIQA420180417";
    public static final String accessKey = "Q8gbQHeDesB2Xs0t";
    public static final String sceretKey = "PPuDXq1KowPT1ftR8DvlQTHhC03aul17";
    public static final String apiEndpoint = "https://test-payment.momo.vn/gw_payment/transactionProcessor";
    public static final String returnUrl = "http://localhost:8084/Lab01_Yellow_Moon/FinishPayMomoController";
    public static final String requestType = "captureMoMoWallet";

    public static MomoResponse requestPayment(
            String amount,
            String orderId,
            String orderInfo,
            String extraData) throws Exception {

        String data = "partnerCode=" + partnerCode
                + "&accessKey=" + accessKey
                + "&requestId=" + orderId
                + "&amount=" + amount
                + "&orderId=" + orderId
                + "&orderInfo=" + orderInfo
                + "&returnUrl=" + returnUrl
                + "&notifyUrl=" + returnUrl
                + "&extraData=" + extraData;
        String signature = Util.encode(sceretKey, data);

        MomoRequest requestEntity = new MomoRequest(
                partnerCode,
                accessKey,
                orderId,
                amount,
                orderId,
                orderInfo,
                returnUrl,
                returnUrl,
                requestType,
                signature,
                extraData
        );

        StringEntity entity = new StringEntity(
                new Gson().toJson(requestEntity)
        );

        HttpResponse response = HttpRequestWrapper.postMethod(apiEndpoint, entity);

        String responseString = new BasicResponseHandler().handleResponse(response);
        MomoResponse momoRes = new Gson().fromJson(responseString, MomoResponse.class);
        return momoRes;
    }
}