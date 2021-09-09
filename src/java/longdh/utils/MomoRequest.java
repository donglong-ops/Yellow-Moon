/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longdh.utils;

/**
 *
 * @author Dong Long
 */
public class MomoRequest {

    private String partnerCode, accessKey, requestId, amount, orderId, orderInfo, returnUrl, notifyUrl, requestType, signature, extraData;

    public MomoRequest() {
    }

    public MomoRequest(
            String partnerCode,
            String accessKey,
            String requestId, String amount, String orderId, String orderInfo, String returnUrl, String notifyUrl, String requestType, String signature, String extraData) {
        this.partnerCode = partnerCode;
        this.accessKey = accessKey;
        this.requestId = requestId;
        this.amount = amount;
        this.orderId = orderId;
        this.orderInfo = orderInfo;
        this.returnUrl = returnUrl;
        this.notifyUrl = notifyUrl;
        this.requestType = requestType;
        this.signature = signature;
        this.extraData = extraData;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getExtraData() {
        return extraData;
    }

    public void setExtraData(String extraData) {
        this.extraData = extraData;
    }
}
