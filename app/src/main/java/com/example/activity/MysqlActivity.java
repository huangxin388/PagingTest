package com.example.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.admin.myapplication.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlActivity extends AppCompatActivity {

    private Button btnConnect;
    private Runnable runnable;
    public static final String userName = "font_readonly";
    public static final String password = "duanVlife";
    public static final String url="jdbc:mysql://192.168.2.28:3306/DB_User?useSSL=true&serverTimezone=GMT%2B8";
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 1) {
                Bundle data=new Bundle();
                data=msg.getData();
                Log.d("xxx","username = " + data.getString("username"));
                Log.d("xxx","password = " + data.getString("password"));
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysql);

        btnConnect = findViewById(R.id.btn_connect);
        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(runnable).start();
            }
        });


        runnable = new Runnable() {
            private Connection con = null;
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");//加载数据库驱动
                    Log.d("xxx","数据库驱动加载成功");
                    con = DriverManager.getConnection(url,userName,password);
                    Log.d("xxx","已成功的与数据库MySQL建立连接！！");
                    //getConnection();
                    //testConnection(con);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    /**
     *
     * @param conn
     */
    public void testConnection(Connection conn) {
        try {
            String sql = "select * from user";//查询表名为“user”的所有内容
            Statement stmt = conn.createStatement();//创建Statement
            ResultSet rs = stmt.executeQuery(sql);
            Bundle bundle=new Bundle();
            while (rs.next()) {
                bundle.clear();
                bundle.putString("username",rs.getString("username"));
                bundle.putString("password",rs.getString("password"));
                Message msg=new Message();
                msg.what = 1;
                msg.setData(bundle);
                mHandler.sendMessage(msg);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
