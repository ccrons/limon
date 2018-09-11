package com.limon.http.coder;
/**
 * 消息类型
 * @author gqf
 *
 */
public class MessageType{
    //1、    登录
    public static final String LoginReq = "LoginReq";
    public static final String LoginRsp = "LoginRsp";
    //2、    注册
    public static final String RegisterReq = "RegisterReq";
    public static final String RegisterRsp = "RegisterRsp";
    //3、    版本更新
    public static final String AppUpdateCheckReq = "AppUpdateCheckReq";
    public static final String AppUpdateCheckRsp = "AppUpdateCheckRsp";
    //4、    修改密码
    public static final String UpdatePwdReq = "UpdatePwdReq";
    public static final String UpdatePwdRsp = "UpdatePwdRsp";
    //1、    新增收货地址
    public static final String AddAddressReq = "AddAddressReq";
    public static final String AddAddressRsp = "AddAddressRsp";
    //2、    修改收货地址
    public static final String UpdateAddressReq = "UpdateAddressReq";
    public static final String UpdateAddressRsp = "UpdateAddressRsp";
    //3、    删除收货地址
    public static final String DelAddressReq = "DelAddressReq";
    public static final String DelAddressRsp = "DelAddressRsp";
    //4、    获取全部收货地址
    public static final String GetAllAddressReq = "GetAllAddressReq";
    public static final String GetAllAddressRsp = "GetAllAddressRsp";
    //5、    获取指定收货地址
    public static final String GetAddressReq = "GetAddressReq";
    public static final String GetAddressRsp = "GetAddressRsp";
    //6、    获取附近店铺
    public static final String GetNearShopReq = "GetNearShopReq";
    public static final String GetNearShopRsp = "GetNearShopRsp";
    //7、    搜索店铺
    public static final String SearchShopReq = "SearchShopReq";
    public static final String SearchShopRsp = "SearchShopRsp";
    //8、    获取指定店铺商品类型
    public static final String GetGoodsTypeReq = "GetGoodsTypeReq";
    public static final String GetGoodsTypeRsp = "GetGoodsTypeRsp";
    //9、    获取指定店铺商品
    public static final String GetGoodsByTypeReq = "GetGoodsByTypeReq";
    public static final String GetGoodsByTypeRsp = "GetGoodsByTypeRsp";
    //10、 获取商品详细信息
    public static final String GetGoodsInfoReq = "GetGoodsInfoReq";
    public static final String GetGoodsInfoRsp = "GetGoodsInfoRsp";
    //11、 添加商品到购物车
    public static final String AddCarReq = "AddCarReq";
    public static final String AddCarRsp = "AddCarRsp";
    //12、 删除购物车商品
    public static final String DelCarReq = "DelCarReq";
    public static final String DelCarRsp = "DelCarRsp";
    //13、 获取购物车商品
    public static final String GetCarGoodsReq = "GetCarGoodsReq";
    public static final String GetCarGoodsRsp = "GetCarGoodsRsp";
    //14、 提交订单
    public static final String SubmitOrderReq = "SubmitOrderReq";
    public static final String SubmitOrderRsp = "SubmitOrderRsp";
    //15、 删除订单
    public static final String DelOrderReq = "DelOrderReq";
    public static final String DelOrderRsp = "DelOrderRsp";
    //16、 订单支付成功通知
    public static final String PayOrderReq = "PayOrderReq";
    public static final String PayOrderRsp = "PayOrderRsp";
    //17、 获取待付款订单
    public static final String GetWPayOrderReq = "GetWPayOrderReq";
    public static final String GetWPayOrderRsp = "GetWPayOrderRsp";
    //18、 获取待收货订单
    public static final String GetWReceiveOrderReq = "GetWReceiveOrderReq";
    public static final String GetWReceiveOrderRsp = "GetWReceiveOrderRsp";
    //19、 获取历史订单
    public static final String GetHistoryOrderReq = "GetHistoryOrderReq";
    public static final String GetHistoryOrderRsp = "GetHistoryOrderRsp";
    //20、 收藏商品
    public static final String FavGoodsReq = "FavGoodsReq";
    public static final String FavGoodsRsp = "FavGoodsRsp";
    //21、 获取收藏商品
    public static final String GetFavGoodsReq = "GetFavGoodsReq";
    public static final String GetFavGoodsRsp = "GetFavGoodsRsp";
    //22、 删除收藏商品
    public static final String DelFavGoodsReq = "DelFavGoodsReq";
    public static final String DelFavGoodsRsp = "DelFavGoodsRsp";
    //23、 获取自营店信息
    public static final String NmSelfShopReq = "NmSelfShopReq";
    public static final String NmSelfShopRsp = "NmSelfShopRsp";
    //24、 修改购物车商品数量
    public static final String UpdateCarReq = "UpdateCarReq";
    public static final String UpdateCarRsp = "UpdateCarRsp";
    //25、 修改订单状态
    public static final String UpdateOrderReq = "UpdateOrderReq";
    public static final String UpdateOrderRsp = "UpdateOrderRsp";
    //26、 获取推送消息
    public static final String GetMessageReq = "GetMessageReq";
    public static final String GetMessageRsp = "GetMessageRsp";
    //27、 提交意见反馈
    public static final String FeedBackReq = "FeedBackReq";
    public static final String FeedBackRsp = "FeedBackRsp";
    //28、 搜索商品
    public static final String SearchGoodsReq = "SearchGoodsReq";
    public static final String SearchGoodsRsp = "SearchGoodsRsp";
    //29、 发送验证码
    public static final String SendCheckCodeReq = "SendCheckCodeReq";
    public static final String SendCheckCodeRsp = "SendCheckCodeRsp";
    //30、 找回密码
    public static final String RetrievePwdReq="RetrievePwdReq";
    public static final String RetrievePwdRsp="RetrievePwdRsp";
    //31、 设置社区店铺收货地址
    public static final String SetShopAddressReq="SetShopAddressReq";
    public static final String SetShopAddressRsp="SetShopAddressRsp";
    //32、 获取社区店铺收货地址
    public static final String GetShopAddressReq="GetShopAddressReq";
    public static final String GetShopAddressRsp="GetShopAddressRsp";
    //33、 获取便利店广告信息
    public static final String GetShopAdReq="GetShopAdReq";
    public static final String GetShopAdRsp="GetShopAdRsp";
    //34、 获取店铺信息
    public static final String GetShopInfoReq="GetShopInfoReq";
    public static final String GetShopInfoRsp="GetShopInfoRsp";
    //35、 获取省列表信息
    public static final String GetProvinceReq="GetProvinceReq";
    public static final String GetProvinceRsp="GetProvinceRsp";
    //36、 获取市列表信息
    public static final String GetCityReq="GetCityReq";
    public static final String GetCityRsp="GetCityRsp";
    //37、 获取市辖区列表信息
    public static final String GetDistrictReq="GetDistrictReq";
    public static final String GetDistrictRsp="GetDistrictRsp";
    //38、 获取指定辖区店铺列表
    public static final String GetShopReq="GetShopReq";
    public static final String GetShopRsp="GetShopRsp";
    //39、 获取指定店铺商品类型一级列表
    public static final String GetGoodsFTypeReq="GetGoodsFTypeReq";
    public static final String GetGoodsFTypeRsp="GetGoodsFTypeRsp";
    //40、 获取个人资料
    public static final String GetPerInfoReq="GetPerInfoReq";
    public static final String GetPerInfoRsp="GetPerInfoRsp";
    //41、 设置个人资料
    public static final String SetPerInfoReq="SetPerInfoReq";
    public static final String SetPerInfoRsp="SetPerInfoRsp";
    //42、 设置个人头像
    public static final String SetHeadImgReq="SetHeadImgReq";
    public static final String SetHeadImgRsp="SetHeadImgRsp";
    //43、 清空购物车
    public static final String DelCarAllReq="DelCarAllReq";
    public static final String DelCarAllRsp="DelCarAllRsp";
    //44、 获取首页招募令、0元购、限时抢购信息
    public static final String GetHpInfoReq="GetHpInfoReq";
    public static final String GetHpInfoRsp="GetHpInfoRsp";
    //45、 获取用户已支付订单数
    public static final String GetUserOrderNumReq="GetUserOrderNumReq";
    public static final String GetUserOrderNumRsp="GetUserOrderNumRsp";
    //46、 获取店铺限时抢购商品购买数
    public static final String GetShopGoodsBuyNumReq="GetShopGoodsBuyNumReq";
    public static final String GetShopGoodsBuyNumRsp="GetShopGoodsBuyNumRsp";
    
    /****************************商家版接口********************************/
    //1、 商家登录
    public static final String ShopLoginReq="ShopLoginReq";
    public static final String ShopLoginRsp="ShopLoginRsp";
    //2、 商家修改喵喵
    public static final String ShopUpdatePwdReq="ShopUpdatePwdReq";
    public static final String ShopUpdatePwdRsp="ShopUpdatePwdRsp";
    //3、 获取全部商品列表
    public static final String GetAllGoodsByShopReq="GetAllGoodsByShopReq";
    public static final String GetAllGoodsByShopRsp="GetAllGoodsByShopRsp";
    //4、 获取已上架商品列表
    public static final String GetMyGoodsByShopReq="GetMyGoodsByShopReq";
    public static final String GetMyGoodsByShopRsp="GetMyGoodsByShopRsp";
    //5、 商品批量上架
    public static final String ShopGoodsUpReq="ShopGoodsUpReq";
    public static final String ShopGoodsUpRsp="ShopGoodsUpRsp";
    //6、 商品批量下架
    public static final String ShopGoodsDownReq="ShopGoodsDownReq";
    public static final String ShopGoodsDownRsp="ShopGoodsDownRsp";
    //7、 上架商品价格、库存修改
    public static final String ShopGoodsUpdateReq="ShopGoodsUpdateReq";
    public static final String ShopGoodsUpdateRsp="ShopGoodsUpdateRsp";
    //8、 获取待处理订单列表
    public static final String GetWSendOrderReq="GetWSendOrderReq";
    public static final String GetWSendOrderRsp="GetWSendOrderRsp";
    //9、 获取派送中订单列表
    public static final String GetSendingOrderReq="GetSendingOrderReq";
    public static final String GetSendingOrderRsp="GetSendingOrderRsp";
    //10、 获取已完成订单列表
    public static final String GetSendedOrderReq="GetSendedOrderReq";
    public static final String GetSendedOrderRsp="GetSendedOrderRsp";
    //11、 更改订单状态
    public static final String ShopUpdateOrderReq="ShopUpdateOrderReq";
    public static final String ShopUpdateOrderRsp="ShopUpdateOrderRsp";
    //12、 获取销售额统计
    public static final String GetTotalOrderReq="GetTotalOrderReq";
    public static final String GetTotalOrderRsp="GetTotalOrderRsp";
    //13、 获取商品信息
    public static final String GetShopGoodsInfoReq="GetShopGoodsInfoReq";
    public static final String GetShopGoodsInfoRsp="GetShopGoodsInfoRsp";
}
