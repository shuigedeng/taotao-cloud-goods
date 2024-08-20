package com.taotao.cloud.goods.facade.grpc;

import com.taotao.cloud.goods.api.grpc.GoodsSkuGrpcRequest;
import com.taotao.cloud.goods.api.grpc.GoodsSkuGrpcResponse;
import com.taotao.cloud.goods.api.grpc.GoodsSkuGrpcServiceGrpc.GoodsSkuGrpcServiceImplBase;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.lognet.springboot.grpc.GRpcService;

@GrpcService
public class GoodsSkuGrpcServiceImpl extends GoodsSkuGrpcServiceImplBase {

	@Override
	public void updateBatchById(GoodsSkuGrpcRequest request,
		StreamObserver<GoodsSkuGrpcResponse> responseObserver) {
		super.updateBatchById(request, responseObserver);
	}

	@Override
	public void updateGoodsStuck(GoodsSkuGrpcRequest request,
		StreamObserver<GoodsSkuGrpcResponse> responseObserver) {
		super.updateGoodsStuck(request, responseObserver);
	}

	@Override
	public void getGoodsSkuByIdFromCache(GoodsSkuGrpcRequest request,
		StreamObserver<GoodsSkuGrpcResponse> responseObserver) {
		super.getGoodsSkuByIdFromCache(request, responseObserver);
	}
}
