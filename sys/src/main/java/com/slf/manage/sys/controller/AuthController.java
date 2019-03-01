package com.slf.manage.sys.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author jftang3
 */
@Slf4j
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private final String validCodeParameter = "checkCode";

    private final String expirationParameter = "expirationTime";

    //验证码过期时间五分钟
    private final int validCodeExpirationTime = 300000;

    @RequestMapping(value = "/login/failure", method = RequestMethod.GET)
    public Map<String, Object> loginFailure(HttpServletRequest request, HttpServletResponse response) {
        log.info("Enter loginFailure");
        Map<String, Object> result = new HashMap<>();
        AuthenticationException ae = (AuthenticationException) request.getSession().getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        result.put("status", 401);
        if(ae != null){
            result.put("message", "登录失败，原因：" + ae.getMessage());
        }else{
            result.put("message", "登陆失败");
        }
        return result;
    }

    @RequestMapping(value = "/login/ajax", method = RequestMethod.GET)
    public Map<String, Object> loginAjax() {
        Map<String, Object> result = new HashMap<>();
        result.put("status", 200);
        result.put("message", "登出成功");
        return result;
    }

    @RequestMapping(value = "/login/login_page", method = RequestMethod.GET)
    public Map<String, Object> loginAjax1() {
        Map<String, Object> result = new HashMap<>();
        result.put("status", 200);
        result.put("message", "用户未登录");
        return result;
    }

    /**
     * 获取验证码图片
     *
     * @param request  request
     * @param response response
     * @throws Exception 异常
     */
        @RequestMapping(value = "/getCheckCode", method = RequestMethod.GET)
    public void checkCode(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 禁止缓存
        response.setHeader("Cache-control", "no-cache");
        response.setHeader("Pargma", "no-cache");
        response.setDateHeader("Expires", -1);

        int width = 110;
        int height = 36;

        // 步骤一 绘制一张内存中图片
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 步骤二  图片绘制背景颜色 -- 通过绘图对象
        Graphics graphics = bufferedImage.getGraphics(); // 得到图像对象  --- 画笔
        // 绘制任何图形之前， 都必须制定一个颜色
        graphics.setColor(new Color(255, 255, 255));
        graphics.fillRect(0, 0, width, height);

        // 步骤三  绘制边框
        graphics.setColor(Color.WHITE);
        graphics.drawRect(0, 0, width - 1, height - 1);

        // 步骤四
        Graphics2D graphics2D = (Graphics2D) graphics;
        // 设置输出字体
        graphics2D.setFont(new Font("Bodoni Bd BT", Font.BOLD, 32));
        // 添加字体平滑效果
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 生成随机数
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            stringBuilder.append(chars.charAt((int) (Math.random() * chars.length())));
        }
        String word = stringBuilder.toString();

        // 定义 X 坐标
        int x = 5;
        Random random = new Random();
        for (int i = 0; i < word.length(); i++) {
            graphics2D.setColor(new Color(0, 0, 0));

            // 获得字母数字
            char c = word.charAt(i);

            // 将c输出到图片
            graphics2D.drawString(String.valueOf(c), x, 28);
            x += 25;
        }

        // 存入会话session
        HttpSession session = request.getSession(true);
        session.removeAttribute("checkCode");
        session.removeAttribute("expirationTime");
        session.setAttribute("checkCode", word.toLowerCase());
        session.setAttribute("expirationTime", System.currentTimeMillis() + validCodeExpirationTime);

        // 将上面图片输出到浏览器 ImageIO
        graphics.dispose();
        ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
    }
}
