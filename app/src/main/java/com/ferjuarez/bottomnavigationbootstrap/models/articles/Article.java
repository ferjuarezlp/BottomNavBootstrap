package com.ferjuarez.bottomnavigationbootstrap.models.articles;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity(tableName = "article",  indices = {@Index(value = {"relevadorCode"}, unique = true)})
public class Article implements Parcelable{
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo
    private int id;

    @ColumnInfo
    private Long fabricOrder;
    @ColumnInfo
    private Long fabricNumber;
    @ColumnInfo
    private String relevadorCode;

    protected Article(Parcel in) {
        id = in.readInt();
        if (in.readByte() == 0) {
            fabricOrder = null;
        } else {
            fabricOrder = in.readLong();
        }
        if (in.readByte() == 0) {
            fabricNumber = null;
        } else {
            fabricNumber = in.readLong();
        }
        relevadorCode = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        if (fabricOrder == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(fabricOrder);
        }
        if (fabricNumber == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(fabricNumber);
        }
        dest.writeString(relevadorCode);

    }

    private Article(Builder builder) {
        relevadorCode = builder.relevadorCode;
        fabricNumber = builder.fabricNumber;
        fabricOrder = builder.order;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    public Long getFabricNumber() {
        return fabricNumber;
    }

    public void setFabricNumber(Long fabricNumber) {
        this.fabricNumber = fabricNumber;
    }

    public String getRelevadorCode() {
        return relevadorCode;
    }

    public void setRelevadorCode(String relevadorCode) {
        this.relevadorCode = relevadorCode;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }


    public static final class Builder {
        private String relevadorCode;
        private Long fabricNumber;
        private Long order;
        private Long incrementalCode;

        public Builder() {
        }
        public Builder fabricNumber(Long val) {
            fabricNumber = val;
            return this;
        }
        public Builder order(Long val) {
            order = val;
            return this;
        }
        public Builder relevadorCode(String val) {
            relevadorCode = val;
            return this;
        }
        public Builder incrementalCode(Long val) {
            incrementalCode = val;
            return this;
        }
        public Article build() {
            return new Article(this);
        }
    }
}