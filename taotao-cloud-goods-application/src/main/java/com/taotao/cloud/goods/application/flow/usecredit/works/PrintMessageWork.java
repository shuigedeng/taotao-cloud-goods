package com.taotao.cloud.goods.application.flow.usecredit.works;


import com.taotao.boot.flowengine.easywork.context.WorkContext;
import com.taotao.boot.flowengine.easywork.work.Work;

public class PrintMessageWork implements Work {

    private final String message;

    public PrintMessageWork(String message) {
        this.message = message;
    }

    @Override
    public String execute(WorkContext workContext) {
        System.out.println(message);
        return message;
    }
}
