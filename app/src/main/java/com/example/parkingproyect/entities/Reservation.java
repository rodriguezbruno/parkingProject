package com.example.parkingproyect.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Reservation implements Parcelable {

    public String securityCode;
    public long startDateTime;
    public long endDateTime;
    public int parkingLot;

    public Reservation(String securityCode, long startDateTime, long endDateTime, int parkingLot) {
        this.securityCode = securityCode;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.parkingLot = parkingLot;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public long getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(long startDateTime) {
        this.startDateTime = startDateTime;
    }

    public long getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(long endDateTime) {
        this.endDateTime = endDateTime;
    }

    public int getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(int parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.securityCode);
        dest.writeLong(this.startDateTime);
        dest.writeLong(this.endDateTime);
        dest.writeInt(this.parkingLot);
    }

    public void readFromParcel(Parcel source) {
        this.securityCode = source.readString();
        this.startDateTime = source.readLong();
        this.endDateTime = source.readLong();
        this.parkingLot = source.readInt();
    }

    public Reservation() {
    }

    protected Reservation(Parcel in) {
        this.securityCode = in.readString();
        this.startDateTime = in.readLong();
        this.endDateTime = in.readLong();
        this.parkingLot = in.readInt();
    }

    public static final Parcelable.Creator<Reservation> CREATOR = new Parcelable.Creator<Reservation>() {
        @Override
        public Reservation createFromParcel(Parcel source) {
            return new Reservation(source);
        }

        @Override
        public Reservation[] newArray(int size) {
            return new Reservation[size];
        }
    };
}
