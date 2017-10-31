package com.zhenyulaw.jf.web.service.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhenyulaw.jf.common.controller.ResultDTO;
import com.zhenyulaw.jf.entity.Company;
import com.zhenyulaw.jf.entity.User;
import com.zhenyulaw.jf.service.CompanyService;
import com.zhenyulaw.jf.service.UserService;

@RestController
@RequestMapping(value="pay", produces="application/json; charset=UTF-8")
public class PayController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	CompanyService companyService;

	@RequestMapping("login")
	public String login(String phone, String password, HttpServletRequest request) {
//		String token = request.getHeader("token");
		User user = this.userService.loginUser(phone, password);
		
		Company company = this.companyService.getCompany(user.getCompanyId());
		user.setAccount(company.getAccount());
		user.setCompanyLogo(company.getLogo());
		
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.addAttribute("user", user);
		
		return resultDTO.toString();
	}
//	public String aliPay(String amount){
//        //实例化客户端
//        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", alipay_appId, alipay_private_key , "json", charset, alipay_public_key, "RSA2");
//        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
//        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
//        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
//        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
//        model.setSubject("简法"); //商品标题
//        model.setOutTradeNo(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+(int)(Math.random()*90000+10000)); //商家订单编号
//        model.setTimeoutExpress("30m"); //超时关闭该订单时间
//        model.setTotalAmount(amount);  //订单总金额
//        model.setProductCode("QUICK_MSECURITY_PAY"); //销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY
//        request.setBizModel(model);
//        request.setNotifyUrl(alipay_notify_url);  //回调地址
//        String orderStr = "";
//        try {
//                //这里和普通的接口调用不同，使用的是sdkExecute
//                AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
//                orderStr = response.getBody();
//                System.out.println(orderStr);//就是orderString 可以直接给客户端请求，无需再做处理。
//            } catch (AlipayApiException e) {
//                e.printStackTrace();
//        }
//        return orderStr;
//    }
//	
//	public String aliPay_notify(Map requestParams){
//        System.out.println("支付宝支付结果通知"+requestParams.toString());
//        //获取支付宝POST过来反馈信息
//        Map<String,String> params = new HashMap<String,String>();
//        
//        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
//            String name = (String) iter.next();
//            String[] values = (String[]) requestParams.get(name);
//            String valueStr = "";
//            for (int i = 0; i < values.length; i++) {
//                valueStr = (i == values.length - 1) ? valueStr + values[i]
//                            : valueStr + values[i] + ",";
//          }
//          //乱码解决，这段代码在出现乱码时使用。
//          //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
//          params.put(name, valueStr);
//         }
//        //切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
//        //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
//        try {
//            boolean flag = AlipaySignature.rsaCheckV1(params, alipay_public_key, charset, "RSA2");
//            if(flag){
//                if("TRADE_SUCCESS".equals(params.get("trade_status"))){
//                    //付款金额
//                    String amount = params.get("buyer_pay_amount");
//                    //商户订单号
//                    String out_trade_no = params.get("out_trade_no");
//                    //支付宝交易号
//                    String trade_no = params.get("trade_no");
//                    //附加数据
//                    String passback_params = URLDecoder.decode(params.get("passback_params"));
//                    
//                }
//            }
//        } catch (AlipayApiException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return "success";
//    }

}
