package io.deepankumarpn.voteridregistration.data.local.roomdb.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "voters_table")
public class VoterEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "createdAt")
    public String createdAt;

    @ColumnInfo(name = "firstName")
    public String firstName;

    @ColumnInfo(name = "lastName")
    public String lastName;

    @ColumnInfo(name = "dob")
    public String dob;

    @ColumnInfo(name = "mobilenumber")
    public String mobilenumber;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "address")
    public String address;

    @ColumnInfo(name = "taluk")
    public String taluk;

    @ColumnInfo(name = "district")
    public String district;

    @ColumnInfo(name = "state")
    public String state;
}