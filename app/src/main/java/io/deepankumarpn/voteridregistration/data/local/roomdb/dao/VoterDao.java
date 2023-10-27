package io.deepankumarpn.voteridregistration.data.local.roomdb.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.Date;
import java.util.List;

import io.deepankumarpn.voteridregistration.data.local.roomdb.entity.VoterEntity;
@Dao
public interface  VoterDao {
    @Insert
    void insertVoter(VoterEntity voterEntity);

    @Query("SELECT COUNT(*) FROM voters_table")
    int getVotersCount();

    @Query("SELECT COUNT(*) FROM voters_table WHERE createdAt = :createdAt")
    int getVotersCountByCreatedAt(String createdAt);

    @Query("SELECT * FROM voters_table")
    List<VoterEntity> getAllVoters();

}
