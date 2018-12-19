package com.example.yp.addresslist;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class UtilDAO {
    private DatabaseUtil du;
    private SQLiteDatabase db;

    public UtilDAO(Context context){
        du = new DatabaseUtil(context);
        //获得可写入的权限
        db = du.getWritableDatabase();
    }


    /**
     * 添加数据
     * 这里调用了帮助类的insert方法，
     * 第一个参数传入表名，
     * 第二个参数暂时用不到传入null，
     * 第三个参数传入ContentValues对象，
     * ContentValues对象提供了put()方法重载，用于向ContentValues中添加数据。
     * */
    public void addData(String tableName,String[] key,String[] values){
        ContentValues contentValues = new ContentValues();
        for(int i = 0; i < key.length; i ++){
            contentValues.put(key[i],values[i]);
        }
        db.insert(tableName,null,contentValues);
        contentValues.clear();
    }

    /**
     * 删除数据
     * 调用delete方法，
     * 第一个参数传入表名，
     * 第二个参数写条件where语句,通常写列名，不写的话删除所有行，
     * 第三个传入列名对应的值，最后返回删除的行数。
     * */
    public int delData(String where,String[] values){
        int del_data;
        del_data = db.delete("UserInfo",where,values);
        return del_data;
    }

    /**
     * 修改数据
     * 我这里调用了execSQL方法直接写SQL语句，封装的方法为update请自行搜索，
     * 这里第一个值是SQL语句没什么好说的，第二个值为传入的条件值，对应SQL语句里的？通配符。
     * */
    public void update(String[] values){
        db.execSQL("update UserInfo set userName=?,userPhone=? where userName=? ",values);
    }

    /**
     * 查询数据
     * 查询代码就比较多了，先调用rawQuery方法查询出结果，
     * 再把值赋给Cursor对象，它是一个游标，通过while判断它指向的行下面是否还有数据，
     * 若有则把当前行的值存储到User实体类里，然后添加到List数组链表中，循环下一次。
     * 这里用到List是因为这需要返回给前台的ListView做数据源。
     * */
    public List<User> inquireData(){
        List<User> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select userName,userPhone" +
                " from UserInfo",null);
        while(cursor.moveToNext()){
            String name = cursor.getString(0);
            String phone = cursor.getString(1);

            User user = new User();
            user.setName(name);
            user.setPhone(phone);

            list.add(user);
        }

        return list;
    }

    /**
     * 关闭数据库连接
     * */
    public void getClose(){
        if(db != null){
            db.close();
        }
    }
}
