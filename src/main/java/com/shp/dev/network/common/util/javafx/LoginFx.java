package com.shp.dev.network.common.util.javafx;

import com.shp.dev.network.common.util.MD5;
import com.shp.dev.network.common.util.jdbc.JDBCUtils;
import com.shp.dev.network.model.SysUser;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO  开启登录页面
 * @CreateTime: 2020/9/9 21:39
 * @PackageName: com.shp.dev.network.common.util.javafx
 * @ProjectName: network
 */
public class LoginFx extends Application {

    public static void main(String[] args) {
        //启动javafx Application程序
        Application.launch(LoginFx.class, args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("欢迎登陆");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));// 设置填充, Insets实例设置矩形区域的四边的一组内偏移量
        Text title = new Text("登录");
        title.setFont(Font.font("微软雅黑", FontWeight.NORMAL, 20));
        Label Account = new Label("账号:");
        TextField userAccount = new TextField();
        Label pwd = new Label("密码:");
        PasswordField userPwd = new PasswordField();
        Button confirm = new Button("确定");

        confirm.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                String username = userAccount.getText();
                String password = userPwd.getText();
                String sql = "select * from sys_user where username=?";
                JDBCUtils<SysUser> sysUserJDBCUtils = new JDBCUtils<>();
                SysUser sysUser = (SysUser) sysUserJDBCUtils.queryObject(sql, SysUser.class, username);
                //验证成功！
                if (sysUser != null && MD5.md5(password).equals(sysUser.getPassword())) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("成功提示");
                    alert.setHeaderText(null);
                    alert.setContentText("登录成功");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("错误提示");
                    alert.setHeaderText(null);
                    alert.setContentText("用户名或密码错误,请重新输入");
                    alert.showAndWait();
                    userPwd.clear();
                }
            }
        });

        grid.add(title, 0, 0, 2, 1);
        grid.add(Account, 0, 1);
        grid.add(userAccount, 1, 1);
        grid.add(pwd, 0, 2);
        grid.add(userPwd, 1, 2);
        HBox panel = new HBox(40);
        panel.setAlignment(Pos.BOTTOM_RIGHT);
        panel.getChildren().add(confirm);
        HBox panel2 = new HBox(40);
        panel2.setAlignment(Pos.BOTTOM_RIGHT);
        grid.add(panel, 1, 4);
        grid.add(panel2, 1, 5);
        HBox panel3 = new HBox(20);
        panel3.setAlignment(Pos.BOTTOM_RIGHT);
        grid.add(panel3, 1, 6);
        primaryStage.setScene(new Scene(grid, 350, 300));
        primaryStage.show();
    }
}
