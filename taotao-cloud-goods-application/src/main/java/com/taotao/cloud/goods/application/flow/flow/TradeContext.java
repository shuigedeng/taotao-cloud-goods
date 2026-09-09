package com.taotao.cloud.goods.application.flow.flow;

public class TradeContext {

    private boolean endMark = false;

    public void end() {
        this.endMark = true;
    }

    public boolean isEnd() {
        return endMark;
    }

    public String getEndMsg() {
        return "";
    }

    public String tradeNo() {
        return "";
    }
}
