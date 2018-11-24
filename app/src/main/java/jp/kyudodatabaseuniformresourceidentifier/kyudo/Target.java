package jp.kyudodatabaseuniformresourceidentifier.kyudo;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Target extends RealmObject implements Serializable{
    private float hayaPositionX;
    private float hayaPositionY;
    private float otoyaPositionX;
    private float otoyaPositionY;

    @PrimaryKey
    private int id;

    public float getHayaPositionX(){
        return hayaPositionX;
    }

    public void setHayaPositionX(float hayaPositionX){
        this.hayaPositionX = hayaPositionX;
    }

    public float getHayaPositionY(){
        return hayaPositionY;
    }

    public void setHayaPositionY(float hayaPositionY){
        this.hayaPositionY = hayaPositionY;
    }

    public float getOtoyaPositionX(){
        return otoyaPositionX;
    }

    public void setOtoyaPositionX(float otoyaPositionX){
        this.otoyaPositionX = otoyaPositionX;
    }

    public float getOtoyaPositionY(){
        return otoyaPositionY;
    }

    public void setOtoyaPositionY(float otoyaPositionY){
        this.otoyaPositionY = otoyaPositionY;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }


}
